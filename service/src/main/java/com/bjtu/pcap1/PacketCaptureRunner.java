package com.bjtu.pcap1;

import com.bjtu.common.tool.*;
import com.bjtu.config.OMConfig;
import com.bjtu.model.base.PcapEntity;
import com.bjtu.model.pcap1.*;
import com.bjtu.model.pcap2.*;
import com.bjtu.model.pcap2.TrainData;
import com.bjtu.model.pcap2.TrainPositionReport;
import com.bjtu.model.pcap3.*;
import com.bjtu.pcap1.service.*;
import com.bjtu.pcap2.service.*;
import com.bjtu.pcap3.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.pcap4j.core.*;
import org.pcap4j.packet.IpV4Packet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.stream.Task;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.bjtu.common.tool.Enums.*;
import javax.websocket.Session;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.*;
import static com.bjtu.common.tool.Common.*;

@Component
public class PacketCaptureRunner implements CommandLineRunner {
    @Autowired
    TraNumVerifyService traNumVerifyService;
    @Autowired
    TrainDispatchCommandConfirmService trainDispatchCommandConfirmService;
    @Autowired
    TrainDispatchCommandService trainDispatchCommandService;
    @Autowired
    C2lactService c2lactService;
    @Autowired
    C2lappService c2lappService;
    @Autowired
    L2cactService l2cactService;
    @Autowired
    L2cappService l2cappService;
    @Autowired
    AlarmService alarmService;
    @Autowired
    TrainDataService trainDataService;
    @Autowired
    TrainPositionReportService trainPositionReportService;
    @Autowired
    MessageVerifyService messageVerifyService;
    @Autowired
    TaskEndService taskEndService;
    @Autowired
    VersionIncompatibleService versionIncompatibleService;
    @Autowired
    CommunicationDialogBeginService communicationDialogBeginService;
    @Autowired
    CommunicationDialogEndService communicationDialogEndService;
    @Autowired
    SomPositionReportService somPositionReportService;
    @Autowired
    CommunicationDialogEstablishedService communicationDialogEstablishedService;

    @Autowired
    TrainDataVerifyService trainDataVerifyService;
    @Autowired
    CommonMessageService commonMessageService;
    @Autowired
    SystemVersionService systemVersionService;
    @Autowired
    CommunicationDialogEndVerifyService communicationDialogEndVerifyService;
    @Autowired
    AcceptTrainService acceptTrainService;
    @Autowired
    private ObjectMapper objectMapper;

    // 记录所有连接到服务器的WebSocketSession
    private static final Set<Session> trainNumberVerifySessions = Collections.synchronizedSet(new HashSet<>());
    private static final Set<Session> trainDispatchCommandSessions = Collections.synchronizedSet(new HashSet<>());
    private static final Set<Session> trainDispatchCommandConfirmSessions = Collections.synchronizedSet(new HashSet<>());
    private static final Set<Session> c2lactSessions = Collections.synchronizedSet(new HashSet<>());
    private static final Set<Session> c2lappSessions = Collections.synchronizedSet(new HashSet<>());
    private static final Set<Session> l2cactSessions = Collections.synchronizedSet(new HashSet<>());
    private static final Set<Session> l2cappSessions = Collections.synchronizedSet(new HashSet<>());
    private static final Set<Session> alarmSessions = Collections.synchronizedSet(new HashSet<>());

    private static final List<PcapEntity> waitingForCheckCommands = Collections.synchronizedList(new LinkedList<>());
    private static final List<Alarm> notConfirmedCommands = Collections.synchronizedList(new LinkedList<>());
    private static final Set<Session> comDiaBeginSessions = Collections.synchronizedSet(new HashSet<>());
    private static final Set<Session> comDiaEndSessions = Collections.synchronizedSet(new HashSet<>());
    private static final Set<Session> comDiaEstSessions = Collections.synchronizedSet(new HashSet<>());
    private static final Set<Session> mesVerifySessions = Collections.synchronizedSet(new HashSet<>());
    private static final Set<Session> somPosRepSessions = Collections.synchronizedSet(new HashSet<>());
    private static final Set<Session> taskEndSessions = Collections.synchronizedSet(new HashSet<>());
    private static final Set<Session> trainDataSessions = Collections.synchronizedSet(new HashSet<>());
    private static final Set<Session> traPosRepSessions = Collections.synchronizedSet(new HashSet<>());
    private static final Set<Session> verIncompSessions = Collections.synchronizedSet(new HashSet<>());
    private static final Set<Session> traDaVerifySessions = Collections.synchronizedSet(new HashSet<>());
    private static final Set<Session> comMesSessions = Collections.synchronizedSet(new HashSet<>());
    private static final Set<Session> sysVerSessions = Collections.synchronizedSet(new HashSet<>());
    private static final Set<Session> accTraSessions = Collections.synchronizedSet(new HashSet<>());
    private static final Set<Session> comDiaEndVerifySessions = Collections.synchronizedSet(new HashSet<>());

    @Override
    public void run(String... args) throws Exception {
        Properties props = new Properties();
        InputStream in = OMConfig.class.getClassLoader().getResourceAsStream("application.properties");
        props.load(in);
        PcapNetworkInterface nif = Pcaps.getDevByName(props.getProperty("nifName"));
        PcapNetworkInterface.PromiscuousMode mode = PcapNetworkInterface.PromiscuousMode.NONPROMISCUOUS;
        PcapHandle handle = nif.openLive(65535, mode, 1);
        handle.setFilter(props.getProperty("filter"), BpfProgram.BpfCompileMode.OPTIMIZE);
        PacketListener listener = packet -> {
            byte[] data = packet.getPayload().getPayload().getPayload().getRawData();
            IpV4Packet.IpV4Header header = packet.getPayload().get(IpV4Packet.class).getHeader();
            //check package type
            DataGramType dataGramType = check(data, header.getProtocol().name());
            //get sip and dip for tcp packages
            String sip = toIpAddress(header.getSrcAddr().getAddress()), dip = toIpAddress(header.getDstAddr().getAddress());
            // 将抓到的包写入数据库
            try {
                switch (dataGramType) {
                    case TraNumVerify:
                    case TraNumVerifyExpand:
                        sendTraNumVerify(data);
                        break;
                    case TraDisCom:
                        sendTraDisCom(data);
                        break;
                    case TraDisComCon:
                        sendTraDisComCon(data);
                        break;
                    case C2lact:
                        sendC2lact(data, sip, dip);
                        break;
                    case L2cact:
                        sendL2cact(data, sip, dip);
                        break;
                    case C2lapp:
                        sendC2lapp(data, sip, dip);
                        break;
                    case L2capp:
                        sendL2capp(data, sip, dip);
                        break;
                    case TraData:
                        sendTraData(data, sip, dip);
                        break;
                    case TraPosRep:
                        sendTraPosRep(data, sip, dip);
                        break;
                    case MesVerify:
                        sendMesVerify(data, sip, dip);
                        break;
                    case TaskEnd:
                        sendTaskEnd(data, sip, dip);
                        break;
                    case VerIncomp:
                        sendVerIncomp(data, sip, dip);
                        break;
                    case ComDiaBegin:
                        sendComDiaBegin(data, sip, dip);
                        break;
                    case ComDiaEnd:
                        sendComDiaEnd(data, sip, dip);
                        break;
                    case SomPosRep:
                        sendSomPosRep(data, sip, dip);
                        break;
                    case ComDiaEst:
                        sendComDiaEst(data, sip, dip);
                        break;
                    case TraDaVerify:
                        sendTradaVerify(data, sip, dip);
                        break;
                    case ComMes:
                        sendComMes(data, sip, dip);
                        break;
                    case SysVer:
                        sendSysVer(data, sip, dip);
                        break;
                    case ComDiaEndVerify:
                        sendComDiaEndVerify(data, sip, dip);
                        break;
                    case AccTra:
                        sendAccTra(data, sip, dip);
                        break;
                    case Unknown:
                        System.out.println("Received Unknown package type!");
                        break;
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        };
        handle.loop(-1, listener);
        handle.close();
    }

    private void sendAccTra(byte[] data, String sip, String dip) throws Exception {
        AcceptTrain acceptTrain = PcapParser.parseAcceptTrain(data);
        acceptTrain.setSourceAddress(sip);
        acceptTrain.setDestinationAddress(dip);
        if(acceptTrainService.save(acceptTrain)) {
            acceptTrain.setCreateTime(new Date());
            synchronized (accTraSessions) {
                for (Session session : accTraSessions) {
                    session.getBasicRemote().sendText(objectMapper.writeValueAsString(acceptTrain));
                }
            }
        }
    }

    private void sendComDiaEndVerify(byte[] data, String sip, String dip) throws Exception {
        CommunicationDialogEndVerify communicationDialogEndVerify = PcapParser.parseCommunicationDialogEndVerify(data);
        communicationDialogEndVerify.setSourceAddress(sip);
        communicationDialogEndVerify.setDestinationAddress(dip);
        if(communicationDialogEndVerifyService.save(communicationDialogEndVerify)) {
            communicationDialogEndVerify.setCreateTime(new Date());
            synchronized (comDiaEndVerifySessions) {
                for (Session session : comDiaEndVerifySessions) {
                    session.getBasicRemote().sendText(objectMapper.writeValueAsString(communicationDialogEndVerify));
                }
            }
        }
    }

    private void sendSysVer(byte[] data, String sip, String dip) throws Exception {
        SystemVersion systemVersion = PcapParser.parseSystemVersion(data);
        systemVersion.setSourceAddress(sip);
        systemVersion.setDestinationAddress(dip);
        if(systemVersionService.save(systemVersion)) {
            systemVersion.setCreateTime(new Date());
            synchronized (sysVerSessions) {
                for (Session session : sysVerSessions) {
                    session.getBasicRemote().sendText(objectMapper.writeValueAsString(systemVersion));
                }
            }
        }
    }

    private void sendComMes(byte[] data, String sip, String dip) throws Exception {
        CommonMessage commonMessage = PcapParser.parseCommonMessage(data);
        commonMessage.setSourceAddress(sip);
        commonMessage.setDestinationAddress(dip);
        if(commonMessageService.save(commonMessage)) {
            commonMessage.setCreateTime(new Date());
            synchronized (comMesSessions) {
                for (Session session : comMesSessions) {
                    session.getBasicRemote().sendText(objectMapper.writeValueAsString(commonMessage));
                }
            }
        }
    }

    private void sendTradaVerify(byte[] data, String sip, String dip) throws Exception {
        TrainDataVerify trainDataVerify = PcapParser.parseTrainDataVerify(data);
        trainDataVerify.setSourceAddress(sip);
        trainDataVerify.setDestinationAddress(dip);
        if(trainDataVerifyService.save(trainDataVerify)) {
            trainDataVerify.setCreateTime(new Date());
            synchronized (traDaVerifySessions) {
                for (Session session : traDaVerifySessions) {
                    session.getBasicRemote().sendText(objectMapper.writeValueAsString(trainDataVerify));
                }
            }
        }
    }

    @Scheduled(fixedDelay = 1000)
    private void setAlarm() throws IOException {
        waitingForCheckCommands.removeIf(entity -> addAlarm(notConfirmedCommands, 15, entity));
        synchronized (alarmSessions) {
            for (Session session : alarmSessions) {
                Iterator<Alarm> iterator = notConfirmedCommands.iterator();
                if(iterator.hasNext()) {
                    Alarm alarm = iterator.next();
                    if(alarmService.save(alarm)){
                        session.getBasicRemote().sendText(objectMapper.writeValueAsString(alarm));
                        iterator.remove();
                    }
                }
            }
        }
    }

    private void sendTraDisComCon(byte[] data) throws IOException {
        TrainDispatchCommandConfirm trainDispatchCommandConfirm = PcapParser.parseTrainDispatchCommandConfirm(data);
        if(trainDispatchCommandConfirmService.save(trainDispatchCommandConfirm)) {
            trainDispatchCommandConfirm.setCreateTime(new Date());
            confirmCommand(waitingForCheckCommands, notConfirmedCommands, trainDispatchCommandConfirm, 15);
            synchronized (trainDispatchCommandConfirmSessions) {
                for (Session session : trainDispatchCommandConfirmSessions) {
                    session.getBasicRemote().sendText(objectMapper.writeValueAsString(trainDispatchCommandConfirm));
                }
            }
        }
    }

    private void sendTraNumVerify(byte[] data) throws IOException {
        TrainNumberVerify trainNumberVerify = PcapParser.parseTrainNumberVerify(data);
        if(traNumVerifyService.save(trainNumberVerify)) {
            trainNumberVerify.setCreateTime(new Date());
            synchronized (trainNumberVerifySessions) {
                for (Session session : trainNumberVerifySessions) {
                    session.getBasicRemote().sendText(objectMapper.writeValueAsString(trainNumberVerify));
                }
            }
        }
    }
    private void sendTraDisCom(byte[] data) throws IOException {
        TrainDispatchCommand trainDispatchCommand = PcapParser.parseTrainDispatchCommand(data);
        if(trainDispatchCommandService.save(trainDispatchCommand)) {
            trainDispatchCommand.setCreateTime(new Date());
            waitingForCheckCommands.add(trainDispatchCommand);//
            synchronized (trainDispatchCommandSessions) {
                for (Session session : trainDispatchCommandSessions) {
                    session.getBasicRemote().sendText(objectMapper.writeValueAsString(trainDispatchCommand));
                }
            }
        }
    }
    private void sendC2lact(byte[] data, String sip, String dip) throws IOException {
        C2lact c2lact = PcapParser.parseC2lact(data);
        c2lact.setSourceAddress(sip);
        c2lact.setDestinationAddress(dip);
        if(c2lactService.save(c2lact)) {
            c2lact.setCreateTime(new Date());
            synchronized (c2lactSessions) {
                for (Session session : c2lactSessions) {
                    session.getBasicRemote().sendText(objectMapper.writeValueAsString(c2lact));
                }
            }
        }
    }
    private void sendC2lapp(byte[] data, String sip, String dip) throws IOException {
        C2lapp c2lapp = PcapParser.parseC2lapp(data);
        c2lapp.setSourceAddress(sip);
        c2lapp.setDestinationAddress(dip);
        if(c2lappService.save(c2lapp)) {
            c2lapp.setCreateTime(new Date());
            synchronized (c2lappSessions) {
                for (Session session : c2lappSessions) {
                    session.getBasicRemote().sendText(objectMapper.writeValueAsString(c2lapp));
                }
            }
        }
    }
    private void sendL2cact(byte[] data, String sip, String dip) throws IOException {
        L2cact l2cact = PcapParser.parseL2cact(data);
        l2cact.setSourceAddress(sip);
        l2cact.setDestinationAddress(dip);
        if(l2cactService.save(l2cact)) {
            l2cact.setCreateTime(new Date());
            synchronized (l2cactSessions) {
                for (Session session : l2cactSessions) {
                    session.getBasicRemote().sendText(objectMapper.writeValueAsString(l2cact));
                }
            }
        }
    }
    private void sendL2capp(byte[] data, String sip, String dip) throws IOException {
        L2capp l2capp = PcapParser.parseL2capp(data);
        l2capp.setSourceAddress(sip);
        l2capp.setDestinationAddress(dip);
        if(l2cappService.save(l2capp)) {
            l2capp.setCreateTime(new Date());
            synchronized (l2cappSessions) {
                for (Session session : l2cappSessions) {
                    session.getBasicRemote().sendText(objectMapper.writeValueAsString(l2capp));
                }
            }
        }
    }
    private void sendTraData(byte[] data, String sip, String dip) throws Exception {
        TrainData trainData = PcapParser.parseTrainData(data);
        trainData.setSourceAddress(sip);
        trainData.setDestinationAddress(dip);
        if(trainDataService.save(trainData)) {
            trainData.setCreateTime(new Date());
            synchronized (trainDataSessions) {
                for (Session session : trainDataSessions) {
                    session.getBasicRemote().sendText(objectMapper.writeValueAsString(trainData));
                }
            }
        }
    }
    private void sendTraPosRep(byte[] data, String sip, String dip) throws Exception {
        TrainPositionReport trainPositionReport = PcapParser.parseTrainPositionReport(data);
        trainPositionReport.setSourceAddress(sip);
        trainPositionReport.setDestinationAddress(dip);
        if(trainPositionReportService.save(trainPositionReport)) {
            trainPositionReport.setCreateTime(new Date());
            synchronized (traPosRepSessions) {
                for (Session session : traPosRepSessions) {
                    session.getBasicRemote().sendText(objectMapper.writeValueAsString(trainPositionReport));
                }
            }
        }
    }

    private void sendMesVerify(byte[] data, String sip, String dip) throws Exception {
        MessageVerify messageVerify = PcapParser.parseMessageVerify(data);
        messageVerify.setSourceAddress(sip);
        messageVerify.setDestinationAddress(dip);
        if(messageVerifyService.save(messageVerify)) {
            messageVerify.setCreateTime(new Date());
            synchronized (mesVerifySessions) {
                for (Session session : mesVerifySessions) {
                    session.getBasicRemote().sendText(objectMapper.writeValueAsString(messageVerify));
                }
            }
        }
    }
    private void sendTaskEnd(byte[] data, String sip, String dip) throws Exception {
        TaskEnd taskEnd = PcapParser.parseTaskEnd(data);
        taskEnd.setSourceAddress(sip);
        taskEnd.setDestinationAddress(dip);
        if(taskEndService.save(taskEnd)) {
            taskEnd.setCreateTime(new Date());
            synchronized (taskEndSessions) {
                for (Session session : taskEndSessions) {
                    session.getBasicRemote().sendText(objectMapper.writeValueAsString(taskEnd));
                }
            }
        }
    }
    private void sendVerIncomp(byte[] data, String sip, String dip) throws Exception {
        VersionIncompatible versionIncompatible = PcapParser.parseVersionIncompatible(data);
        versionIncompatible.setSourceAddress(sip);
        versionIncompatible.setDestinationAddress(dip);
        if(versionIncompatibleService.save(versionIncompatible)) {
            versionIncompatible.setCreateTime(new Date());
            synchronized (verIncompSessions) {
                for (Session session : verIncompSessions) {
                    session.getBasicRemote().sendText(objectMapper.writeValueAsString(versionIncompatible));
                }
            }
        }
    }
    private void sendComDiaBegin(byte[] data, String sip, String dip) throws Exception {
        CommunicationDialogBegin communicationDialogBegin = PcapParser.parseCommunicationDialogBegin(data);
        communicationDialogBegin.setSourceAddress(sip);
        communicationDialogBegin.setDestinationAddress(dip);
        if(communicationDialogBeginService.save(communicationDialogBegin)) {
            communicationDialogBegin.setCreateTime(new Date());
            synchronized (comDiaBeginSessions) {
                for (Session session : comDiaBeginSessions) {
                    session.getBasicRemote().sendText(objectMapper.writeValueAsString(communicationDialogBegin));
                }
            }
        }
    }
    private void sendComDiaEnd(byte[] data, String sip, String dip) throws Exception {
        CommunicationDialogEnd communicationDialogEnd = PcapParser.parseCommunicationDialogEnd(data);
        communicationDialogEnd.setSourceAddress(sip);
        communicationDialogEnd.setDestinationAddress(dip);
        if(communicationDialogEndService.save(communicationDialogEnd)) {
            communicationDialogEnd.setCreateTime(new Date());
            synchronized (comDiaEndSessions) {
                for (Session session : comDiaEndSessions) {
                    session.getBasicRemote().sendText(objectMapper.writeValueAsString(communicationDialogEnd));
                }
            }
        }
    }
    private void sendSomPosRep(byte[] data, String sip, String dip) throws Exception {
        SomPositionReport somPositionReport = PcapParser.parseSomPositionReport(data);
        somPositionReport.setSourceAddress(sip);
        somPositionReport.setDestinationAddress(dip);
        if(somPositionReportService.save(somPositionReport)) {
            somPositionReport.setCreateTime(new Date());
            synchronized (somPosRepSessions) {
                for (Session session : somPosRepSessions) {
                    session.getBasicRemote().sendText(objectMapper.writeValueAsString(somPositionReport));
                }
            }
        }
    }
    private void sendComDiaEst(byte[] data, String sip, String dip) throws Exception {
        CommunicationDialogEstablished communicationDialogEstablished = PcapParser.parseCommunicationDialogEstablished(data);
        communicationDialogEstablished.setSourceAddress(sip);
        communicationDialogEstablished.setDestinationAddress(dip);
        if(communicationDialogEstablishedService.save(communicationDialogEstablished)) {
            communicationDialogEstablished.setCreateTime(new Date());
            synchronized (comDiaEstSessions) {
                for (Session session : comDiaEstSessions) {
                    session.getBasicRemote().sendText(objectMapper.writeValueAsString(communicationDialogEstablished));
                }
            }
        }
    }
    public static void addTrainNumberVerifySession(Session session) {
        trainNumberVerifySessions.add(session);
    }
    public static void removeTrainNumberVerifySession(Session session) {
        trainNumberVerifySessions.remove(session);
    }
    public static void addTrainDispatchCommandSession(Session session) {
        trainDispatchCommandSessions.add(session);
    }
    public static void removeTrainDispatchCommandSession(Session session) {
        trainDispatchCommandSessions.remove(session);
    }
    public static void addTrainDispatchCommandConfirmSession(Session session) {
        trainDispatchCommandConfirmSessions.add(session);
    }
    public static void removeTrainDispatchCommandConfirmSession(Session session) {
        trainDispatchCommandConfirmSessions.remove(session);
    }
    public static void addC2lactSession(Session session) {
        c2lactSessions.add(session);
    }
    public static void removeC2lactSession(Session session) {
        c2lactSessions.remove(session);
    }
    public static void addC2lappSession(Session session) {
        c2lappSessions.add(session);
    }
    public static void removeC2lappSession(Session session) {
        c2lappSessions.remove(session);
    }
    public static void addL2cactSession(Session session) {
        l2cactSessions.add(session);
    }
    public static void removeL2cactSession(Session session) {
        l2cactSessions.remove(session);
    }
    public static void addL2cappSession(Session session) {
        l2cappSessions.add(session);
    }
    public static void removeL2cappSession(Session session) {
        l2cappSessions.remove(session);
    }
    public static void addAlarmSession(Session session) {
        alarmSessions.add(session);
    }
    public static void removeAlarmSession(Session session) {
        alarmSessions.remove(session);
    }

    public static void addComDiaBeginSession(Session session) {
        comDiaBeginSessions.add(session);
    }
    public static void removeComDiaBeginSession(Session session) {
        comDiaBeginSessions.remove(session);
    }
    public static void addComDiaEndSession(Session session) {
        comDiaEndSessions.add(session);
    }
    public static void removeComDiaEndSession(Session session) {
        comDiaEndSessions.remove(session);
    }
    public static void addComDiaEstSession(Session session) {
        comDiaEstSessions.add(session);
    }
    public static void removeComDiaEstSession(Session session) {
        comDiaEstSessions.remove(session);
    }
    public static void addMesVerifySession(Session session) {
        mesVerifySessions.add(session);
    }
    public static void removeMesVerifySession(Session session) {
        mesVerifySessions.remove(session);
    }
    public static void addSomPosRepSession(Session session) {
        somPosRepSessions.add(session);
    }
    public static void removeSomPosRepSession(Session session) {
        somPosRepSessions.remove(session);
    }
    public static void addTaskEndSession(Session session) {
        taskEndSessions.add(session);
    }
    public static void removeTaskEndSession(Session session) {
        taskEndSessions.remove(session);
    }
    public static void addTrainDataSession(Session session) {
        trainDataSessions.add(session);
    }
    public static void removeTrainDataSession(Session session) {
        trainDataSessions.remove(session);
    }
    public static void addTraPosRepSession(Session session) {
        traPosRepSessions.add(session);
    }
    public static void removeTraPosRepSession(Session session) {
        traPosRepSessions.remove(session);
    }
    public static void addVerIncompSession(Session session) {
        verIncompSessions.add(session);
    }
    public static void removeVerIncompSession(Session session) {
        verIncompSessions.remove(session);
    }
    public static void addTraDaVerifySession(Session session) {
        traDaVerifySessions.add(session);
    }
    public static void removeTraDaVerifySession(Session session) {
        traDaVerifySessions.remove(session);
    }
    public static void addComMesSession(Session session) {
        comMesSessions.add(session);
    }
    public static void removeComMesSession(Session session) {
        comMesSessions.remove(session);
    }
    public static void addSysVerSession(Session session) {
        sysVerSessions.add(session);
    }
    public static void removeSysVerSession(Session session) {
        sysVerSessions.remove(session);
    }
    public static void addComDiaEndVerifySession(Session session) {
        comDiaEndVerifySessions.add(session);
    }
    public static void removeComDiaEndVerifySession(Session session) {
        comDiaEndVerifySessions.remove(session);
    }
    public static void addAccTraSession(Session session) {
        accTraSessions.add(session);
    }
    public static void removeAccTraSession(Session session) {
        accTraSessions.remove(session);
    }
}

