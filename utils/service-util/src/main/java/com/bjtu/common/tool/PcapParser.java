package com.bjtu.common.tool;

import com.bjtu.model.pcap1.*;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.bjtu.common.tool.Common.*;
import com.bjtu.common.tool.Enums.*;
import com.bjtu.model.pcap2.*;
import com.bjtu.model.pcap3.*;
import org.apache.commons.lang3.ArrayUtils;

public class PcapParser {
    public static TrainNumberVerify parseTrainNumberVerify(byte[] data) throws IOException {
        TrainNumberVerify trainNumberVerify = new TrainNumberVerify();
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        DataInputStream dis = new DataInputStream(bais);

        byte[] frameBegin = new byte[2]; dis.readFully(frameBegin);
        byte[] mesLen = new byte[2]; dis.readFully(mesLen);
        byte sourcePortCode = dis.readByte();
        byte sourceCommunicationAddressLen = dis.readByte();
        byte[] srcAddress = new byte[4]; dis.readFully(srcAddress);
        byte destinationPortCode = dis.readByte();
        byte destinationCommunicationAddressLen = dis.readByte();
        byte[] dstAddress = new byte[4]; dis.readFully(dstAddress);
        byte serviceType = dis.readByte();
        byte command = dis.readByte();
        byte localBoardAddress = dis.readByte();
        byte featureCodeOne = dis.readByte();
        byte flags = dis.readByte();
        byte versionNumber = dis.readByte();
        byte reservedOne = dis.readByte();
        byte stationNumberExpand = dis.readByte();
        byte[] trainNumType = new byte[4]; dis.readFully(trainNumType);
        byte driverNumberExpand = dis.readByte();
        byte deputyDriverNumberExpand = dis.readByte();
        byte[] reservedTwo = new byte[2]; dis.readFully(reservedTwo);
        byte locomotiveTypeNumberExpand = dis.readByte();
        byte actualRouteNumber = dis.readByte();
        byte[] reservedThree = new byte[11]; dis.readFully(reservedThree);
        byte treplacementPassengerOrCargoOne = dis.readByte();
        byte[] trainNum = new byte[3]; dis.readFully(trainNum);
        byte checkAndOne = dis.readByte();
        byte adressOfThisBoard = dis.readByte();
        byte featureCodeTwo = dis.readByte();
        byte detectionUnitCode = dis.readByte();
        byte[] dateAndTime = new byte[4]; dis.readFully(dateAndTime);
        byte[] speed = new byte[3]; dis.readFully(speed);
        byte locomotiveSignal = dis.readByte();
        byte locomotiveWorkingCondition = dis.readByte();
        byte[] signalMachineNumber = new byte[2]; dis.readFully(signalMachineNumber);
        byte signalMachineType = dis.readByte();
        byte[] mileage = new byte[3]; dis.readFully(mileage);
        byte[] totalWeight = new byte[2]; dis.readFully(totalWeight);
        byte[] lenNote = new byte[2]; dis.readFully(lenNote);
        byte numberOfVehicles = dis.readByte();
        byte treplacementPassengerOrCargoTwo = dis.readByte();
        byte[] trainNum5 = new byte[2]; dis.readFully(trainNum5);
        byte sectionNumber = dis.readByte();
        byte stationNumber = dis.readByte();
        byte[] driverNumber = new byte[2]; dis.readFully(driverNumber);
        byte[] deputyDriverNumber = new byte[2]; dis.readFully(deputyDriverNumber);
        byte[] locomotiveNum = new byte[2]; dis.readFully(locomotiveNum);
        byte locomotiveTypeNumber = dis.readByte();
        byte[] trainTubePressure = new byte[2]; dis.readFully(trainTubePressure);
        byte deviceStatus = dis.readByte();
        byte reservedFour = dis.readByte();
        byte checkAndTwo = dis.readByte();
        byte[] lineNum = new byte[2]; dis.readFully(lineNum);
        byte[] transmissionTotalNumber = new byte[2]; dis.readFully(transmissionTotalNumber);
        byte[] transmissionTotalNumberToLTE = new byte[2]; dis.readFully(transmissionTotalNumberToLTE);
        byte[] currentCarNumberSendTotalNum = new byte[2]; dis.readFully(currentCarNumberSendTotalNum);
        byte[] preReservedOne = new byte[2]; dis.readFully(preReservedOne);
        byte[] ctcDedicatedDomain = new byte[32]; dis.readFully(ctcDedicatedDomain);
        byte preReservedTwo = dis.readByte();
        byte[] followID = new byte[3]; dis.readFully(followID);
        byte communityID = dis.readByte();
        byte positionStatus = dis.readByte();
        byte[] currentLongitude = new byte[5]; dis.readFully(currentLongitude);
        byte[] currentPositionLatitude = new byte[4]; dis.readFully(currentPositionLatitude);
        byte[] pcapTime = new byte[6]; dis.readFully(pcapTime);
        byte[] crcChecksum = new byte[2]; dis.readFully(crcChecksum);
        byte[] endOfFrame = new byte[2]; dis.readFully(endOfFrame);

        trainNumberVerify.setFrameStart(sixteen(frameBegin, false));
        trainNumberVerify.setMessageLength(ten(mesLen, false));
        trainNumberVerify.setSourcePortCode(sixteen(sourcePortCode));
        trainNumberVerify.setSourceCommunicationAddressLength(String.valueOf(sourceCommunicationAddressLen));
        trainNumberVerify.setSourceAddress(toIpAddress(srcAddress));

        trainNumberVerify.setDestinationPortCode(sixteen(destinationPortCode));
        trainNumberVerify.setDestinationCommunicationAddressLength(String.valueOf(destinationCommunicationAddressLen));
        trainNumberVerify.setDestinationAddress(toIpAddress(dstAddress));
        trainNumberVerify.setBusinessType(BusiType.get(serviceType));
        trainNumberVerify.setCommand(CommType.get(command));

        trainNumberVerify.setLocalBoardAddress(sixteen(localBoardAddress));
        trainNumberVerify.setFeatureCodeOne(String.valueOf(featureCodeOne));
        trainNumberVerify.setFlags(sixteen(flags));
        trainNumberVerify.setVersionNumber((int) versionNumber);
        trainNumberVerify.setReservedOne(sixteen(reservedOne));

        trainNumberVerify.setStationNumber((int) stationNumberExpand);
        ArrayUtils.reverse(trainNumType); trainNumberVerify.setTrainNumberType(new String(trainNumType, StandardCharsets.US_ASCII));
        trainNumberVerify.setDriverNumber(sixteen(driverNumberExpand));
        trainNumberVerify.setDeputyDriverNumber(sixteen(deputyDriverNumberExpand));
        trainNumberVerify.setReservedTwo(sixteen(reservedTwo, true));

        trainNumberVerify.setLocomotiveTypeNumber(sixteen(locomotiveTypeNumberExpand));
        trainNumberVerify.setActualRouteNumber(sixteen(actualRouteNumber));
        trainNumberVerify.setReservedThree(sixteen(reservedThree, true));
        trainNumberVerify.setReplacementPassengerOrCargoOne(U1BenBu.get((byte)(treplacementPassengerOrCargoOne & 3)));
        trainNumberVerify.setTrainNumber(ten(trainNum, true));

        trainNumberVerify.setCheckAndOne(sixteen(checkAndOne));
        trainNumberVerify.setAddressOfThisBoard(sixteen(adressOfThisBoard));
        trainNumberVerify.setFeatureCodeTwo(sixteen(featureCodeTwo));
        trainNumberVerify.setDetectionUnitCode(U1CheckUnitType.get((byte)(detectionUnitCode&15)));
        trainNumberVerify.setDateTime(calTime4(dateAndTime));

        trainNumberVerify.setActualSpeed(((speed[1] & 0x3F) << 8) + speed[0]);
        trainNumberVerify.setLocomotiveSignal(((locomotiveSignal & 16) == 0) ? "单灯 " : "多灯 " + U1Signal.get((byte)(locomotiveSignal&15)));
        trainNumberVerify.setLocomotiveWorkingCondition(
                U1Condi.get((byte)(locomotiveWorkingCondition&1))
                +U1Condi.get((byte)(locomotiveWorkingCondition&2))
                +U1Condi.get((byte)(locomotiveWorkingCondition&4))
                +U1Condi.get((byte)(locomotiveWorkingCondition&8))
                +U1Condi.get((byte)(locomotiveWorkingCondition&16))
        );
        trainNumberVerify.setSignalMachineNumber(sixteen(signalMachineNumber, true));
        trainNumberVerify.setSignalMachineType(U1SignalType.get((byte)(signalMachineType & 7)));

        trainNumberVerify.setMileage(calMile(mileage));
        trainNumberVerify.setTotalWeight(ten(totalWeight, true));
        trainNumberVerify.setLengthNote(ten(lenNote, true));
        trainNumberVerify.setNumberOfVehicles((int) numberOfVehicles);
        trainNumberVerify.setReplacementPassengerOrCargoTwo(U1BenBu.get((byte) (treplacementPassengerOrCargoTwo & 3)));

        trainNumberVerify.setTrainNumberFive(ten(trainNum5, true));
        trainNumberVerify.setSectionNumber(String.valueOf(sectionNumber));
        trainNumberVerify.setStationNumberTwo((int) stationNumber);
        trainNumberVerify.setDriverNumberTwo(ten(driverNumber, true));
        trainNumberVerify.setDeputyDriverNumberTwo(ten(deputyDriverNumber, true));

        trainNumberVerify.setLocomotiveNumber(ten(locomotiveNum, true));
        trainNumberVerify.setLocomotiveTypeNumber(String.valueOf(locomotiveTypeNumber));
        trainNumberVerify.setTrainTubePressure(ten(trainTubePressure, true));
        trainNumberVerify.setDeviceStatus(U1DevSta.get(deviceStatus));
        trainNumberVerify.setReservedFour(sixteen(reservedFour));

        trainNumberVerify.setCheckAndTwo(sixteen(checkAndTwo));
        trainNumberVerify.setLineNumber(ten(lineNum, false));
        trainNumberVerify.setTransmissionTotalNumber(ten(transmissionTotalNumberToLTE, false));
        trainNumberVerify.setCurrentCarNumberSendTotalNum(ten(currentCarNumberSendTotalNum, false));
        trainNumberVerify.setPreReservedOne(sixteen(preReservedOne, false));

        trainNumberVerify.setCtcDedicatedDomain(sixteen(ctcDedicatedDomain, false));
        trainNumberVerify.setPreReservedTwo(sixteen(preReservedTwo));
        trainNumberVerify.setFollowId(sixteen(followID, false));
        trainNumberVerify.setCommunityId(sixteen(communityID));
        trainNumberVerify.setPositionStatus(sixteen(positionStatus));

        trainNumberVerify.setCurrentLongitude(ten(currentLongitude, false));
        trainNumberVerify.setCurrentPositionLatitude(ten(currentPositionLatitude, false));
        trainNumberVerify.setPcapTime(calTime6(pcapTime));
        trainNumberVerify.setCrcChecksum(sixteen(crcChecksum, false));
        trainNumberVerify.setEndOfFrame(sixteen(endOfFrame, false));

        return trainNumberVerify;
    }
    public static TrainDispatchCommand parseTrainDispatchCommand(byte[] data) throws IOException {
        TrainDispatchCommand trainDispatchCommand = new TrainDispatchCommand();
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        DataInputStream dis = new DataInputStream(bais);

        byte[] frameBegin = new byte[2]; dis.readFully(frameBegin);
        byte[] mesLen = new byte[2]; dis.readFully(mesLen);
        long length = Cal(mesLen);
        byte sourcePortCode = dis.readByte();
        byte sourceCommunicationAddressLen = dis.readByte();
        byte[] srcAddress = new byte[4]; dis.readFully(srcAddress);
        byte destinationPortCode = dis.readByte();
        byte destinationCommunicationAddressLen = dis.readByte();
        byte[] dstAddress = new byte[4]; dis.readFully(dstAddress);
        byte serviceType = dis.readByte();
        byte command = dis.readByte();
        byte functionCode = dis.readByte();
        byte[] dateYear = new byte[3]; dis.readFully(dateYear);
        byte[] publishTime = new byte[3]; dis.readFully(publishTime);
        byte[] sendTime = new byte[3]; dis.readFully(sendTime);
        byte[] trainNumber = new byte[7]; dis.readFully(trainNumber);
        byte[] locomotiveNum = new byte[8]; dis.readFully(locomotiveNum);
        byte orderNumberLow = dis.readByte();
        byte[] commandInfoNum = new byte[6]; dis.readFully(commandInfoNum);
        byte[] issuersNum = new byte[8]; dis.readFully(issuersNum);
        byte commandStatus = dis.readByte();
        byte orderNumberHigh = dis.readByte();
        byte[] preReserved = new byte[4]; dis.readFully(preReserved);
        byte totalPackage = dis.readByte();
        byte thisPackageNum = dis.readByte();
        byte[] commandText = new byte[(int) (length - 70)]; dis.readFully(commandText);
        byte[] crcChecksum = new byte[2]; dis.readFully(crcChecksum);
        byte[] endOfFrame = new byte[2]; dis.readFully(endOfFrame);

        trainDispatchCommand.setFrameBegin(sixteen(frameBegin, false));
        trainDispatchCommand.setMessageLength(ten(mesLen, false));
        trainDispatchCommand.setSourcePortCode(sixteen(sourcePortCode));
        trainDispatchCommand.setSourceAddressLength(String.valueOf(sourceCommunicationAddressLen));
        trainDispatchCommand.setSourceAddress(toIpAddress(srcAddress));

        trainDispatchCommand.setDestinationPortCode(sixteen(destinationPortCode));
        trainDispatchCommand.setDestinationAddressLength(String.valueOf(destinationCommunicationAddressLen));
        trainDispatchCommand.setDestinationAddress(toIpAddress(dstAddress));
        trainDispatchCommand.setServiceType(BusiType.get(serviceType));
        trainDispatchCommand.setCommand(CommType.get(command));

        trainDispatchCommand.setFunctionCode(FuncType.get(functionCode));
        trainDispatchCommand.setPublishTime(calTime3(dateYear, publishTime));
        trainDispatchCommand.setSendTime(calTime3(dateYear, sendTime));
        trainDispatchCommand.setTrainNumber(new String(trainNumber, StandardCharsets.US_ASCII).trim().replace("-",""));
        trainDispatchCommand.setLocotiveNumber(new String(locomotiveNum, StandardCharsets.US_ASCII).trim().replace("-",""));

        trainDispatchCommand.setOrderNumberLow(sixteen(orderNumberLow));
        trainDispatchCommand.setCommandInfoNumber(new String(commandInfoNum, StandardCharsets.US_ASCII).trim().replace("-",""));
        trainDispatchCommand.setIssuersName(new String(issuersNum, StandardCharsets.UTF_8));
        trainDispatchCommand.setCommandStatus(sixteen(commandStatus));
        trainDispatchCommand.setOrderNumberHigh(sixteen(orderNumberHigh));

        trainDispatchCommand.setPreReserved(sixteen(preReserved, false));
        trainDispatchCommand.setTotalPackage(String.valueOf(totalPackage));
        trainDispatchCommand.setThisPackageNumber(String.valueOf(thisPackageNum));
        trainDispatchCommand.setCommandText(getText(commandText));
        trainDispatchCommand.setCrcCheckSum(sixteen(crcChecksum, false));
        trainDispatchCommand.setEndOfFrame(sixteen(endOfFrame, false));

        return trainDispatchCommand;
    }
    public static TrainDispatchCommandConfirm parseTrainDispatchCommandConfirm(byte[] data) throws IOException {
        TrainDispatchCommandConfirm trainDispatchCommandConfirm = new TrainDispatchCommandConfirm();
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        DataInputStream dis = new DataInputStream(bais);

        byte[] frameBegin = new byte[2]; dis.readFully(frameBegin);
        byte[] mesLen = new byte[2]; dis.readFully(mesLen);
        byte sourcePortCode = dis.readByte();
        byte sourceCommunicationAddressLen = dis.readByte();
        byte[] srcAddress = new byte[4]; dis.readFully(srcAddress);
        byte destinationPortCode = dis.readByte();
        byte destinationCommunicationAddressLen = dis.readByte();
        byte[] dstAddress = new byte[4]; dis.readFully(dstAddress);
        byte serviceType = dis.readByte();
        byte command = dis.readByte();
        byte infoName = dis.readByte();
        byte functionCode = dis.readByte();
        byte[] dateYear = new byte[3]; dis.readFully(dateYear);
        byte[] pcapTime = new byte[3]; dis.readFully(pcapTime);
        byte[] trainNumber = new byte[7]; dis.readFully(trainNumber);
        byte[] locomotiveNum = new byte[8]; dis.readFully(locomotiveNum);
        byte orderNumberLow = dis.readByte();
        byte[] commandInfoNum = new byte[6]; dis.readFully(commandInfoNum);
        byte[] mileage = new byte[3]; dis.readFully(mileage);
        byte[] position_longitude = new byte[5]; dis.readFully(position_longitude);
        byte[] position_latitude = new byte[4]; dis.readFully(position_latitude);
        byte orderNumberHigh = dis.readByte();
        byte[] preReserved = new byte[4]; dis.readFully(preReserved);
        byte packageNumber = dis.readByte();
        byte[] crcChecksum = new byte[2]; dis.readFully(crcChecksum);
        byte[] endOfFrame = new byte[2]; dis.readFully(endOfFrame);

        trainDispatchCommandConfirm.setFrameBegin(sixteen(frameBegin, false));
        trainDispatchCommandConfirm.setMessageLength(ten(mesLen, false));
        trainDispatchCommandConfirm.setSourcePortCode(sixteen(sourcePortCode));
        trainDispatchCommandConfirm.setSourceAddressLength(String.valueOf(sourceCommunicationAddressLen));
        trainDispatchCommandConfirm.setSourceAddress(toIpAddress(srcAddress));

        trainDispatchCommandConfirm.setDestinationPortCode(sixteen(destinationPortCode));
        trainDispatchCommandConfirm.setDestinationAddressLength(String.valueOf(destinationCommunicationAddressLen));
        trainDispatchCommandConfirm.setDestinationAddress(toIpAddress(dstAddress));
        trainDispatchCommandConfirm.setServiceType(BusiType.get(serviceType));
        trainDispatchCommandConfirm.setCommand(CommType.get(command));

        trainDispatchCommandConfirm.setCommandInfoName(FuncType.get(infoName));

        trainDispatchCommandConfirm.setFunctionCode(FuncType.get(functionCode));
        trainDispatchCommandConfirm.setPcapTime(calTime3(dateYear, pcapTime));
        trainDispatchCommandConfirm.setTrainNumber(new String(trainNumber, StandardCharsets.US_ASCII).trim().replace("-",""));
        trainDispatchCommandConfirm.setLocomotiveNumber(new String(locomotiveNum, StandardCharsets.US_ASCII).trim().replace("-",""));

        trainDispatchCommandConfirm.setOrderNumberLow(sixteen(orderNumberLow));
        trainDispatchCommandConfirm.setCommandInfoNumber(new String(commandInfoNum, StandardCharsets.US_ASCII).trim().replace("-",""));
        trainDispatchCommandConfirm.setMileage(calMile(mileage));
        trainDispatchCommandConfirm.setPositionLongitude(calPosition(position_longitude,true));
        trainDispatchCommandConfirm.setPositionLatitude(calPosition(position_longitude,false));

        trainDispatchCommandConfirm.setOrderNumberHigh(sixteen(orderNumberHigh));
        trainDispatchCommandConfirm.setPreReserve(sixteen(preReserved, false));
        trainDispatchCommandConfirm.setPackageNumber(String.valueOf(packageNumber));
        trainDispatchCommandConfirm.setCrcCheckSum(sixteen(crcChecksum, false));
        trainDispatchCommandConfirm.setEndOfFrame(sixteen(endOfFrame, false));

        return trainDispatchCommandConfirm;
    }
    public static C2lact parseC2lact(byte[] data) throws IOException{
        C2lact c2lact = new C2lact();
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        DataInputStream dis = new DataInputStream(bais);

        byte[] frameBegin = new byte[2]; dis.readFully(frameBegin);
        byte[] messageLength = new byte[2]; dis.readFully(messageLength);
        byte messageType = dis.readByte();
        byte[] crcCheck = new byte[2]; dis.readFully(crcCheck);

        c2lact.setFrameBegin(sixteen(frameBegin, true));
        c2lact.setMessageLength(ten(messageLength, true));
        c2lact.setMessageType(sixteen(messageType));
        c2lact.setCrcCheck(sixteen(crcCheck, true));
        return c2lact;
    }
    public static L2cact parseL2cact(byte[] data) throws IOException{
        L2cact l2cact = new L2cact();
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        DataInputStream dis = new DataInputStream(bais);

        byte[] frameBegin = new byte[2]; dis.readFully(frameBegin);
        byte[] messageLength = new byte[2]; dis.readFully(messageLength);
        byte messageType = dis.readByte();
        byte[] crcCheck = new byte[2]; dis.readFully(crcCheck);

        l2cact.setFrameBegin(sixteen(frameBegin, true));
        l2cact.setMessageLength(ten(messageLength, true));
        l2cact.setMessageType(sixteen(messageType));
        l2cact.setCrcCheck(sixteen(crcCheck, true));
        return l2cact;
    }
    public static C2lapp parseC2lapp(byte[] data) throws IOException{
        C2lapp c2lapp = new C2lapp();
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        DataInputStream dis = new DataInputStream(bais);

        byte[] frameBegin = new byte[2]; dis.readFully(frameBegin);
        byte[] messageLength = new byte[2]; dis.readFully(messageLength);
        int length = (int) Cal(messageLength);
        byte messageType = dis.readByte();
        byte servicType = dis.readByte();
        byte addressLength = dis.readByte();
        byte[] address = new byte[10]; dis.readFully(address);
        byte[] content = new byte[length - 19]; dis.readFully(content);
        byte[] crcCheck = new byte[2]; dis.readFully(crcCheck);

        c2lapp.setFrameBegin(sixteen(frameBegin, true));
        c2lapp.setMessageLength(ten(messageLength, true));
        c2lapp.setMessageType(sixteen(messageType));
        c2lapp.setServiceType(sixteen(servicType));
        c2lapp.setAddressLength(String.valueOf(addressLength));

        c2lapp.setAddress(new String(address, StandardCharsets.US_ASCII).trim());
        c2lapp.setContent(new String(content, StandardCharsets.US_ASCII).trim());
        c2lapp.setCrcCheck(sixteen(crcCheck, true));

        return c2lapp;
    }
    public static L2capp parseL2capp(byte[] data) throws IOException{
        L2capp l2capp = new L2capp();
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        DataInputStream dis = new DataInputStream(bais);

        byte[] frameBegin = new byte[2]; dis.readFully(frameBegin);
        byte[] messageLength = new byte[2]; dis.readFully(messageLength);
        int length = (int) Cal(messageLength);
        byte messageType = dis.readByte();
        byte servicType = dis.readByte();
        byte addressLength = dis.readByte();
        byte[] address = new byte[10]; dis.readFully(address);
        byte[] content = new byte[length - 19]; dis.readFully(content);
        byte[] crcCheck = new byte[2]; dis.readFully(crcCheck);

        l2capp.setFrameBegin(sixteen(frameBegin, true));
        l2capp.setMessageLength(ten(messageLength, true));
        l2capp.setMessageType(sixteen(messageType));
        l2capp.setServiceType(sixteen(servicType));
        l2capp.setAddressLength(String.valueOf(addressLength));

        l2capp.setAddress(new String(address, StandardCharsets.US_ASCII).trim());
        l2capp.setContent(new String(content, StandardCharsets.US_ASCII).trim());
        l2capp.setCrcCheck(sixteen(crcCheck, true));

        return l2capp;
    }
    public static TrainData parseTrainData(byte[] data) throws Exception {
        TrainData trainData = new TrainData();
        BitParser bitParser = new BitParser(data);

        trainData.setNidMessage(bitParser.parseBitsToDecimal(8));
        trainData.setLMessage(bitParser.parseBitsToDecimal(10));
        trainData.setTTrain(bitParser.parseTime(32));
        trainData.setNidEngine(bitParser.parseBitsToDecimal(24));

        trainData.setPosNidPacket(bitParser.parseBitsToDecimal(8));
        trainData.setPosLPacket(bitParser.parseBitsToDecimal(13));
        trainData.setPosQScale(parseQScale(new Byte(bitParser.parseBitsToDecimal(2))));
        trainData.setPosNidLrbg(bitParser.parseBitsToDecimal(10) + bitParser.parseBitsToDecimal(14));
        if(trainData.getPosNidPacket().equals("1")) trainData.setPosNidPrvbg(bitParser.parseBitsToDecimal(24));
        trainData.setPosDLrbg(bitParser.parseBitsToDecimal(15));
        trainData.setPosQDirlrbg(QDLrbg.get(new Byte(bitParser.parseBitsToDecimal(2))));
        trainData.setPosQDlrbg(QDLrbg.get(new Byte(bitParser.parseBitsToDecimal(2))));
        trainData.setPosLDoubtover(bitParser.parseBitsToDecimal(15));
        trainData.setPosLDoubtunder(bitParser.parseBitsToDecimal(15));
        String temp = bitParser.parseBitsToDecimal(2);
        trainData.setPosQLength(temp.equals("0") ? "无完整信息" : temp);
        trainData.setPosVTrain(bitParser.parseBitsToDecimal(7) + "km/h");
        trainData.setPosQDirtrain(QDLrbg.get(new Byte(bitParser.parseBitsToDecimal(2))));
        trainData.setPosMLevel(parseCTCSLevel(new Byte(bitParser.parseBitsToDecimal(3))));
        if(trainData.getPosMLevel().equals("CTCS 2级")) trainData.setPosMMode(MMode2.get(new Byte(bitParser.parseBitsToDecimal(4))));
        else trainData.setPosMMode(MMode3.get(new Byte(bitParser.parseBitsToDecimal(4))));
        trainData.setPosNidStm(bitParser.parseBitsToDecimal(8).equals("3") ? "CTCS 2级" : "unknown");

        trainData.setDataNidPacket(bitParser.parseBitsToDecimal(8));
        trainData.setDataLPacket(bitParser.parseBitsToDecimal(13));
        trainData.setDataNidOperational(bitParser.parseBitsToDecimal(32));
        temp = bitParser.parseBitsToDecimal(15);
        trainData.setDataNcTrain(temp.equals("0") ? "基本静态速度曲线" : temp);
        trainData.setDataLTrain(bitParser.parseBitsToDecimal(12));
        trainData.setDataVMaxtrain(bitParser.parseBitsToDecimal(7));
        trainData.setDataMLoadinggauge(bitParser.parseBitsToDecimal(8));
        trainData.setDataMAxleload(parseAxeLoad(new Byte(bitParser.parseBitsToDecimal(7))));
        trainData.setDataMAirtight(bitParser.parseBitsToDecimal(2).equals("0") ? "未安装" : "安装");
        trainData.setDataNIter(bitParser.parseBitsToDecimal(5));
        trainData.setDataStmNIter(bitParser.parseBitsToDecimal(5));
        trainData.setDataNidStm(bitParser.parseBitsToDecimal(8).equals("3") ? "CTCS 2级" : "unknown");
        return trainData;
    }
    public static TrainPositionReport parseTrainPositionReport(byte[] data) throws Exception {
        TrainPositionReport trainPositionReport = new TrainPositionReport();
        BitParser bitParser = new BitParser(data);
        int length = data.length * 8;
        trainPositionReport.setNidMessage(bitParser.parseBitsToDecimal(8));
        trainPositionReport.setLMessage(bitParser.parseBitsToDecimal(10));
        trainPositionReport.setTTrain(bitParser.parseTime(32));
        trainPositionReport.setNidEngine(bitParser.parseBitsToDecimal(24));

        trainPositionReport.setPosNidPacket(bitParser.parseBitsToDecimal(8));
        trainPositionReport.setPosLPacket(bitParser.parseBitsToDecimal(13));
        trainPositionReport.setPosQScale(parseQScale(new Byte(bitParser.parseBitsToDecimal(2))));
        trainPositionReport.setPosNidLrbg(bitParser.parseBitsToDecimal(10) + bitParser.parseBitsToDecimal(14));
        if(trainPositionReport.getPosNidPacket().equals("1")) trainPositionReport.setPosNidPrvbg(bitParser.parseBitsToDecimal(24));
        trainPositionReport.setPosDLrbg(bitParser.parseBitsToDecimal(15));
        trainPositionReport.setPosQDirlrbg(QDLrbg.get(new Byte(bitParser.parseBitsToDecimal(2))));
        trainPositionReport.setPosQDlrbg(QDLrbg.get(new Byte(bitParser.parseBitsToDecimal(2))));
        trainPositionReport.setPosLDoubtover(bitParser.parseBitsToDecimal(15));
        trainPositionReport.setPosLDoubtunder(bitParser.parseBitsToDecimal(15));
        String temp = bitParser.parseBitsToDecimal(2);
        trainPositionReport.setPosQLength(temp.equals("0") ? "无完整信息" : temp);
        trainPositionReport.setPosVTrain(bitParser.parseBitsToDecimal(7) + "km/h");
        trainPositionReport.setPosQDirtrain(QDLrbg.get(new Byte(bitParser.parseBitsToDecimal(2))));
        trainPositionReport.setPosMLevel(parseCTCSLevel(new Byte(bitParser.parseBitsToDecimal(3))));
        if(trainPositionReport.getPosMLevel().equals("CTCS 2级")) trainPositionReport.setPosMMode(MMode2.get(new Byte(bitParser.parseBitsToDecimal(4))));
        else trainPositionReport.setPosMMode(MMode3.get(new Byte(bitParser.parseBitsToDecimal(4))));
        trainPositionReport.setPosNidStm(bitParser.parseBitsToDecimal(8).equals("3") ? "CTCS 2级" : "unknown");

        length -= trainPositionReport.getPosNidPacket().equals("1") ? 220 : 196;

        if(length >= 8){
            trainPositionReport.setErrNidPacket(bitParser.parseBitsToDecimal(8));
            length -= 8;
            if(trainPositionReport.getErrNidPacket().equals("4")){
                trainPositionReport.setErrLPacket(bitParser.parseBitsToDecimal(13));
                trainPositionReport.setErrMError(MError.get(new Byte(bitParser.parseBitsToDecimal(8))));
                length -= 21;
                if(length >= 30){
                    trainPositionReport.setOscNidPacket(bitParser.parseBitsToDecimal(8));
                    trainPositionReport.setOscLPacket(bitParser.parseBitsToDecimal(13));
                    trainPositionReport.setOscNidXuser(bitParser.parseBitsToDecimal(9));
                    length -= 30;
                    if(trainPositionReport.getOscNidXuser().equals("44")){
                        trainPositionReport.setOscContainLPacket(bitParser.parseBitsToDecimal(13));
                        trainPositionReport.setOscMSequencenum(bitParser.parseBitsToDecimal(16));
                        trainPositionReport.setOscMTraintype(parseTrainType(new Byte(bitParser.parseBitsToDecimal(8))));
                        trainPositionReport.setOscNidTrack(
                        "大区" + bitParser.parseBitsToDecimal(7) +
                        "分区" + bitParser.parseBitsToDecimal(3) +
                        "车站" + bitParser.parseBitsToDecimal(6) +
                        "车站" + bitParser.parseBitsToDecimal(3) +
                        "股道" + bitParser.parseBitsToDecimal(5));
                        trainPositionReport.setOscQDir(QDir.get(new Byte(bitParser.parseBitsToDecimal(2))));
                        trainPositionReport.setOscQTb(QTb.get(new Byte(bitParser.parseBitsToDecimal(2))));
                        trainPositionReport.setOscMLdoorcmd(MC.get(new Byte(bitParser.parseBitsToDecimal(2))));
                        trainPositionReport.setOscMRdoorcmd(MC.get(new Byte(bitParser.parseBitsToDecimal(2))));
                        length -= 69;
                        if(length >= 30){
                            trainPositionReport.setOprNidPacket(bitParser.parseBitsToDecimal(8));
                            trainPositionReport.setOprLPacket(bitParser.parseBitsToDecimal(13));
                            trainPositionReport.setOprNidXuser(bitParser.parseBitsToDecimal(9));
                            length -= 30;
                            if(trainPositionReport.getOprNidXuser().equals("45")){
                                trainPositionReport.setOprContainLPacket(bitParser.parseBitsToDecimal(13));
                                trainPositionReport.setOprNidDepaturetrack(
                                "大区" + bitParser.parseBitsToDecimal(7) +
                                "分区" + bitParser.parseBitsToDecimal(3) +
                                "车站" + bitParser.parseBitsToDecimal(6) +
                                "车站" + bitParser.parseBitsToDecimal(3) +
                                "股道" + bitParser.parseBitsToDecimal(5));
                                trainPositionReport.setOprMDeparttime(bitParser.parseTime(32));
                                trainPositionReport.setOprNidArrivaltrack(
                                "大区" + bitParser.parseBitsToDecimal(7) +
                                "分区" + bitParser.parseBitsToDecimal(3) +
                                "车站" + bitParser.parseBitsToDecimal(6) +
                                "车站" + bitParser.parseBitsToDecimal(3) +
                                "股道" + bitParser.parseBitsToDecimal(5));
                                trainPositionReport.setOprMArrivaltime(bitParser.parseTime(32));
                                trainPositionReport.setOprMTask(MTask.get(new Byte(bitParser.parseBitsToDecimal(2))));
                                trainPositionReport.setOprMSkip(MSkip.get(new Byte(bitParser.parseBitsToDecimal(2))));
                                length -= 129;
                                if(length >= 30){
                                    trainPositionReport.setVsNidPacket(bitParser.parseBitsToDecimal(8));
                                    trainPositionReport.setVsLPacket(bitParser.parseBitsToDecimal(13));
                                    trainPositionReport.setVsNidXuser(bitParser.parseBitsToDecimal(9));
                                    trainPositionReport.setVsContainLPacket(bitParser.parseBitsToDecimal(13));
                                    trainPositionReport.setVsNidEngine(bitParser.parseBitsToDecimal(24));
                                    trainPositionReport.setVsNidOperational(bitParser.parseBitsToDecimal(32));
                                    trainPositionReport.setVsNidDriver(bitParser.parseBCD(32));
                                    trainPositionReport.setVsMLevel(parseCTCSLevel(new Byte(bitParser.parseBitsToDecimal(3))));
                                    if(trainPositionReport.getPosMLevel().equals("CTCS 2级"))
                                        trainPositionReport.setVsMMode(MMode2.get(new Byte(bitParser.parseBitsToDecimal(4))));
                                    else
                                        trainPositionReport.setVsMMode(MMode3.get(new Byte(bitParser.parseBitsToDecimal(4))));
                                    trainPositionReport.setVsQStopstatus(QSS.get(new Byte(bitParser.parseBitsToDecimal(4))));
                                    trainPositionReport.setVsMAtoMode(MAM.get(new Byte(bitParser.parseBitsToDecimal(4))));
                                    trainPositionReport.setVsMAtoControlStrategy(MAS.get(new Byte(bitParser.parseBitsToDecimal(4))));
                                    trainPositionReport.setVsMTraintype(parseTrainType(new Byte(bitParser.parseBitsToDecimal(8))));
                                    trainPositionReport.setVsNidTrack(
                                    "大区" + bitParser.parseBitsToDecimal(7) +
                                    "分区" + bitParser.parseBitsToDecimal(3) +
                                    "车站" + bitParser.parseBitsToDecimal(6) +
                                    "车站" + bitParser.parseBitsToDecimal(3) +
                                    "股道" + bitParser.parseBitsToDecimal(5));
                                    trainPositionReport.setVsMDoormode(MDM.get(new Byte(bitParser.parseBitsToDecimal(2))));
                                    trainPositionReport.setVsDoorstatus(MDS.get(new Byte(bitParser.parseBitsToDecimal(2))));
                                    trainPositionReport.setVsMAtoerror(bitParser.parseBitsToDecimal(16));
                                    trainPositionReport.setVsMPosition(bitParser.parseBitsToDecimal(32));
                                }
                            }
                            else {
                                trainPositionReport.setVsNidPacket(trainPositionReport.getOprNidPacket()); trainPositionReport.setOprNidPacket("");
                                trainPositionReport.setVsLPacket(trainPositionReport.getOprLPacket()); trainPositionReport.setOprLPacket("");
                                trainPositionReport.setVsNidXuser(trainPositionReport.getOprNidXuser()); trainPositionReport.setOprNidXuser("");
                                trainPositionReport.setVsContainLPacket(bitParser.parseBitsToDecimal(13));
                                trainPositionReport.setVsNidEngine(bitParser.parseBitsToDecimal(24));
                                trainPositionReport.setVsNidOperational(bitParser.parseBitsToDecimal(32));
                                trainPositionReport.setVsNidDriver(bitParser.parseBCD(32));
                                trainPositionReport.setVsMLevel(parseCTCSLevel(new Byte(bitParser.parseBitsToDecimal(3))));
                                if(trainPositionReport.getPosMLevel().equals("CTCS 2级"))
                                    trainPositionReport.setVsMMode(MMode2.get(new Byte(bitParser.parseBitsToDecimal(4))));
                                else
                                    trainPositionReport.setVsMMode(MMode3.get(new Byte(bitParser.parseBitsToDecimal(4))));
                                trainPositionReport.setVsQStopstatus(QSS.get(new Byte(bitParser.parseBitsToDecimal(4))));
                                trainPositionReport.setVsMAtoMode(MAM.get(new Byte(bitParser.parseBitsToDecimal(4))));
                                trainPositionReport.setVsMAtoControlStrategy(MAS.get(new Byte(bitParser.parseBitsToDecimal(4))));
                                trainPositionReport.setVsMTraintype(parseTrainType(new Byte(bitParser.parseBitsToDecimal(8))));
                                trainPositionReport.setVsNidTrack(
                                        "大区" + bitParser.parseBitsToDecimal(7) +
                                                "分区" + bitParser.parseBitsToDecimal(3) +
                                                "车站" + bitParser.parseBitsToDecimal(6) +
                                                "车站" + bitParser.parseBitsToDecimal(3) +
                                                "股道" + bitParser.parseBitsToDecimal(5));
                                trainPositionReport.setVsMDoormode(MDM.get(new Byte(bitParser.parseBitsToDecimal(2))));
                                trainPositionReport.setVsDoorstatus(MDS.get(new Byte(bitParser.parseBitsToDecimal(2))));
                                trainPositionReport.setVsMAtoerror(bitParser.parseBitsToDecimal(16));
                                trainPositionReport.setVsMPosition(bitParser.parseBitsToDecimal(32));
                            }
                        }
                    }
                    else if(trainPositionReport.getOscNidXuser().equals("45")){
                        trainPositionReport.setOprNidPacket(trainPositionReport.getOscNidPacket()); trainPositionReport.setOscNidPacket("");
                        trainPositionReport.setOprLPacket(trainPositionReport.getOscLPacket()); trainPositionReport.setOscLPacket("");
                        trainPositionReport.setOprNidXuser(trainPositionReport.getOscNidXuser()); trainPositionReport.setOscNidXuser("");
                        trainPositionReport.setOprContainLPacket(bitParser.parseBitsToDecimal(13));
                        trainPositionReport.setOprNidDepaturetrack(
                                "大区" + bitParser.parseBitsToDecimal(7) +
                                        "分区" + bitParser.parseBitsToDecimal(3) +
                                        "车站" + bitParser.parseBitsToDecimal(6) +
                                        "车站" + bitParser.parseBitsToDecimal(3) +
                                        "股道" + bitParser.parseBitsToDecimal(5));
                        trainPositionReport.setOprMDeparttime(bitParser.parseTime(32));
                        trainPositionReport.setOprNidArrivaltrack(
                                "大区" + bitParser.parseBitsToDecimal(7) +
                                        "分区" + bitParser.parseBitsToDecimal(3) +
                                        "车站" + bitParser.parseBitsToDecimal(6) +
                                        "车站" + bitParser.parseBitsToDecimal(3) +
                                        "股道" + bitParser.parseBitsToDecimal(5));
                        trainPositionReport.setOprMArrivaltime(bitParser.parseTime(32));
                        trainPositionReport.setOprMTask(MTask.get(new Byte(bitParser.parseBitsToDecimal(2))));
                        trainPositionReport.setOprMSkip(MSkip.get(new Byte(bitParser.parseBitsToDecimal(2))));
                        length -= 129;
                        if(length >= 30){
                            trainPositionReport.setVsNidPacket(bitParser.parseBitsToDecimal(8));
                            trainPositionReport.setVsLPacket(bitParser.parseBitsToDecimal(13));
                            trainPositionReport.setVsNidXuser(bitParser.parseBitsToDecimal(9));
                            trainPositionReport.setVsContainLPacket(bitParser.parseBitsToDecimal(13));
                            trainPositionReport.setVsNidEngine(bitParser.parseBitsToDecimal(24));
                            trainPositionReport.setVsNidOperational(bitParser.parseBitsToDecimal(32));
                            trainPositionReport.setVsNidDriver(bitParser.parseBCD(32));
                            trainPositionReport.setVsMLevel(parseCTCSLevel(new Byte(bitParser.parseBitsToDecimal(3))));
                            if(trainPositionReport.getPosMLevel().equals("CTCS 2级"))
                                trainPositionReport.setVsMMode(MMode2.get(new Byte(bitParser.parseBitsToDecimal(4))));
                            else
                                trainPositionReport.setVsMMode(MMode3.get(new Byte(bitParser.parseBitsToDecimal(4))));
                            trainPositionReport.setVsQStopstatus(QSS.get(new Byte(bitParser.parseBitsToDecimal(4))));
                            trainPositionReport.setVsMAtoMode(MAM.get(new Byte(bitParser.parseBitsToDecimal(4))));
                            trainPositionReport.setVsMAtoControlStrategy(MAS.get(new Byte(bitParser.parseBitsToDecimal(4))));
                            trainPositionReport.setVsMTraintype(parseTrainType(new Byte(bitParser.parseBitsToDecimal(8))));
                            trainPositionReport.setVsNidTrack(
                                    "大区" + bitParser.parseBitsToDecimal(7) +
                                            "分区" + bitParser.parseBitsToDecimal(3) +
                                            "车站" + bitParser.parseBitsToDecimal(6) +
                                            "车站" + bitParser.parseBitsToDecimal(3) +
                                            "股道" + bitParser.parseBitsToDecimal(5));
                            trainPositionReport.setVsMDoormode(MDM.get(new Byte(bitParser.parseBitsToDecimal(2))));
                            trainPositionReport.setVsDoorstatus(MDS.get(new Byte(bitParser.parseBitsToDecimal(2))));
                            trainPositionReport.setVsMAtoerror(bitParser.parseBitsToDecimal(16));
                            trainPositionReport.setVsMPosition(bitParser.parseBitsToDecimal(32));
                        }
                    }
                    else {
                        trainPositionReport.setVsNidPacket(trainPositionReport.getOscNidPacket()); trainPositionReport.setOscNidPacket("");
                        trainPositionReport.setVsLPacket(trainPositionReport.getOscLPacket()); trainPositionReport.setOscLPacket("");
                        trainPositionReport.setVsNidXuser(trainPositionReport.getOscNidXuser()); trainPositionReport.setOscNidXuser("");
                        trainPositionReport.setVsContainLPacket(bitParser.parseBitsToDecimal(13));
                        trainPositionReport.setVsNidEngine(bitParser.parseBitsToDecimal(24));
                        trainPositionReport.setVsNidOperational(bitParser.parseBitsToDecimal(32));
                        trainPositionReport.setVsNidDriver(bitParser.parseBCD(32));
                        trainPositionReport.setVsMLevel(parseCTCSLevel(new Byte(bitParser.parseBitsToDecimal(3))));
                        if(trainPositionReport.getPosMLevel().equals("CTCS 2级"))
                            trainPositionReport.setVsMMode(MMode2.get(new Byte(bitParser.parseBitsToDecimal(4))));
                        else
                            trainPositionReport.setVsMMode(MMode3.get(new Byte(bitParser.parseBitsToDecimal(4))));
                        trainPositionReport.setVsQStopstatus(QSS.get(new Byte(bitParser.parseBitsToDecimal(4))));
                        trainPositionReport.setVsMAtoMode(MAM.get(new Byte(bitParser.parseBitsToDecimal(4))));
                        trainPositionReport.setVsMAtoControlStrategy(MAS.get(new Byte(bitParser.parseBitsToDecimal(4))));
                        trainPositionReport.setVsMTraintype(parseTrainType(new Byte(bitParser.parseBitsToDecimal(8))));
                        trainPositionReport.setVsNidTrack(
                                "大区" + bitParser.parseBitsToDecimal(7) +
                                        "分区" + bitParser.parseBitsToDecimal(3) +
                                        "车站" + bitParser.parseBitsToDecimal(6) +
                                        "车站" + bitParser.parseBitsToDecimal(3) +
                                        "股道" + bitParser.parseBitsToDecimal(5));
                        trainPositionReport.setVsMDoormode(MDM.get(new Byte(bitParser.parseBitsToDecimal(2))));
                        trainPositionReport.setVsDoorstatus(MDS.get(new Byte(bitParser.parseBitsToDecimal(2))));
                        trainPositionReport.setVsMAtoerror(bitParser.parseBitsToDecimal(16));
                        trainPositionReport.setVsMPosition(bitParser.parseBitsToDecimal(32));
                    }
                }
            }
            else{
                trainPositionReport.setOscNidPacket(trainPositionReport.getErrNidPacket()); trainPositionReport.setErrNidPacket("");
                if(length >= 22){
                    trainPositionReport.setOscLPacket(bitParser.parseBitsToDecimal(13));
                    trainPositionReport.setOscNidXuser(bitParser.parseBitsToDecimal(9));
                    length -= 22;
                    if(trainPositionReport.getOscNidXuser().equals("44")){
                        trainPositionReport.setOscContainLPacket(bitParser.parseBitsToDecimal(13));
                        trainPositionReport.setOscMSequencenum(bitParser.parseBitsToDecimal(16));
                        trainPositionReport.setOscMTraintype(parseTrainType(new Byte(bitParser.parseBitsToDecimal(8))));
                        trainPositionReport.setOscNidTrack(
                                "大区" + bitParser.parseBitsToDecimal(7) +
                                        "分区" + bitParser.parseBitsToDecimal(3) +
                                        "车站" + bitParser.parseBitsToDecimal(6) +
                                        "车站" + bitParser.parseBitsToDecimal(3) +
                                        "股道" + bitParser.parseBitsToDecimal(5));
                        trainPositionReport.setOscQDir(QDir.get(new Byte(bitParser.parseBitsToDecimal(2))));
                        trainPositionReport.setOscQTb(QTb.get(new Byte(bitParser.parseBitsToDecimal(2))));
                        trainPositionReport.setOscMLdoorcmd(MC.get(new Byte(bitParser.parseBitsToDecimal(2))));
                        trainPositionReport.setOscMRdoorcmd(MC.get(new Byte(bitParser.parseBitsToDecimal(2))));
                        length -= 69;
                        if(length >= 30){
                            trainPositionReport.setOprNidPacket(bitParser.parseBitsToDecimal(8));
                            trainPositionReport.setOprLPacket(bitParser.parseBitsToDecimal(13));
                            trainPositionReport.setOprNidXuser(bitParser.parseBitsToDecimal(9));
                            length -= 30;
                            if(trainPositionReport.getOprNidXuser().equals("45")){
                                trainPositionReport.setOprContainLPacket(bitParser.parseBitsToDecimal(13));
                                trainPositionReport.setOprNidDepaturetrack(
                                        "大区" + bitParser.parseBitsToDecimal(7) +
                                                "分区" + bitParser.parseBitsToDecimal(3) +
                                                "车站" + bitParser.parseBitsToDecimal(6) +
                                                "车站" + bitParser.parseBitsToDecimal(3) +
                                                "股道" + bitParser.parseBitsToDecimal(5));
                                trainPositionReport.setOprMDeparttime(bitParser.parseTime(32));
                                trainPositionReport.setOprNidArrivaltrack(
                                        "大区" + bitParser.parseBitsToDecimal(7) +
                                                "分区" + bitParser.parseBitsToDecimal(3) +
                                                "车站" + bitParser.parseBitsToDecimal(6) +
                                                "车站" + bitParser.parseBitsToDecimal(3) +
                                                "股道" + bitParser.parseBitsToDecimal(5));
                                trainPositionReport.setOprMArrivaltime(bitParser.parseTime(32));
                                trainPositionReport.setOprMTask(MTask.get(new Byte(bitParser.parseBitsToDecimal(2))));
                                trainPositionReport.setOprMSkip(MSkip.get(new Byte(bitParser.parseBitsToDecimal(2))));
                                length -= 129;
                                if(length >= 30){
                                    trainPositionReport.setVsNidPacket(bitParser.parseBitsToDecimal(8));
                                    trainPositionReport.setVsLPacket(bitParser.parseBitsToDecimal(13));
                                    trainPositionReport.setVsNidXuser(bitParser.parseBitsToDecimal(9));
                                    trainPositionReport.setVsContainLPacket(bitParser.parseBitsToDecimal(13));
                                    trainPositionReport.setVsNidEngine(bitParser.parseBitsToDecimal(24));
                                    trainPositionReport.setVsNidOperational(bitParser.parseBitsToDecimal(32));
                                    trainPositionReport.setVsNidDriver(bitParser.parseBCD(32));
                                    trainPositionReport.setVsMLevel(parseCTCSLevel(new Byte(bitParser.parseBitsToDecimal(3))));
                                    if(trainPositionReport.getPosMLevel().equals("CTCS 2级"))
                                        trainPositionReport.setVsMMode(MMode2.get(new Byte(bitParser.parseBitsToDecimal(4))));
                                    else
                                        trainPositionReport.setVsMMode(MMode3.get(new Byte(bitParser.parseBitsToDecimal(4))));
                                    trainPositionReport.setVsQStopstatus(QSS.get(new Byte(bitParser.parseBitsToDecimal(4))));
                                    trainPositionReport.setVsMAtoMode(MAM.get(new Byte(bitParser.parseBitsToDecimal(4))));
                                    trainPositionReport.setVsMAtoControlStrategy(MAS.get(new Byte(bitParser.parseBitsToDecimal(4))));
                                    trainPositionReport.setVsMTraintype(parseTrainType(new Byte(bitParser.parseBitsToDecimal(8))));
                                    trainPositionReport.setVsNidTrack(
                                            "大区" + bitParser.parseBitsToDecimal(7) +
                                                    "分区" + bitParser.parseBitsToDecimal(3) +
                                                    "车站" + bitParser.parseBitsToDecimal(6) +
                                                    "车站" + bitParser.parseBitsToDecimal(3) +
                                                    "股道" + bitParser.parseBitsToDecimal(5));
                                    trainPositionReport.setVsMDoormode(MDM.get(new Byte(bitParser.parseBitsToDecimal(2))));
                                    trainPositionReport.setVsDoorstatus(MDS.get(new Byte(bitParser.parseBitsToDecimal(2))));
                                    trainPositionReport.setVsMAtoerror(bitParser.parseBitsToDecimal(16));
                                    trainPositionReport.setVsMPosition(bitParser.parseBitsToDecimal(32));
                                }
                            }else {
                                trainPositionReport.setVsNidPacket(trainPositionReport.getOprNidPacket()); trainPositionReport.setOprNidPacket("");
                                trainPositionReport.setVsLPacket(trainPositionReport.getOprLPacket()); trainPositionReport.setOprLPacket("");
                                trainPositionReport.setVsNidXuser(trainPositionReport.getOprNidXuser()); trainPositionReport.setOprNidXuser("");
                                trainPositionReport.setVsContainLPacket(bitParser.parseBitsToDecimal(13));
                                trainPositionReport.setVsNidEngine(bitParser.parseBitsToDecimal(24));
                                trainPositionReport.setVsNidOperational(bitParser.parseBitsToDecimal(32));
                                trainPositionReport.setVsNidDriver(bitParser.parseBCD(32));
                                trainPositionReport.setVsMLevel(parseCTCSLevel(new Byte(bitParser.parseBitsToDecimal(3))));
                                if(trainPositionReport.getPosMLevel().equals("CTCS 2级"))
                                    trainPositionReport.setVsMMode(MMode2.get(new Byte(bitParser.parseBitsToDecimal(4))));
                                else
                                    trainPositionReport.setVsMMode(MMode3.get(new Byte(bitParser.parseBitsToDecimal(4))));
                                trainPositionReport.setVsQStopstatus(QSS.get(new Byte(bitParser.parseBitsToDecimal(4))));
                                trainPositionReport.setVsMAtoMode(MAM.get(new Byte(bitParser.parseBitsToDecimal(4))));
                                trainPositionReport.setVsMAtoControlStrategy(MAS.get(new Byte(bitParser.parseBitsToDecimal(4))));
                                trainPositionReport.setVsMTraintype(parseTrainType(new Byte(bitParser.parseBitsToDecimal(8))));
                                trainPositionReport.setVsNidTrack(
                                        "大区" + bitParser.parseBitsToDecimal(7) +
                                                "分区" + bitParser.parseBitsToDecimal(3) +
                                                "车站" + bitParser.parseBitsToDecimal(6) +
                                                "车站" + bitParser.parseBitsToDecimal(3) +
                                                "股道" + bitParser.parseBitsToDecimal(5));
                                trainPositionReport.setVsMDoormode(MDM.get(new Byte(bitParser.parseBitsToDecimal(2))));
                                trainPositionReport.setVsDoorstatus(MDS.get(new Byte(bitParser.parseBitsToDecimal(2))));
                                trainPositionReport.setVsMAtoerror(bitParser.parseBitsToDecimal(16));
                                trainPositionReport.setVsMPosition(bitParser.parseBitsToDecimal(32));
                            }
                        }
                    }else if(trainPositionReport.getOscNidXuser().equals("45")){
                        trainPositionReport.setOprNidPacket(trainPositionReport.getOscNidPacket()); trainPositionReport.setOscNidPacket("");
                        trainPositionReport.setOprLPacket(trainPositionReport.getOscLPacket()); trainPositionReport.setOscLPacket("");
                        trainPositionReport.setOprNidXuser(trainPositionReport.getOscNidXuser()); trainPositionReport.setOscNidXuser("");
                        trainPositionReport.setOprContainLPacket(bitParser.parseBitsToDecimal(13));
                        trainPositionReport.setOprNidDepaturetrack(
                                "大区" + bitParser.parseBitsToDecimal(7) +
                                        "分区" + bitParser.parseBitsToDecimal(3) +
                                        "车站" + bitParser.parseBitsToDecimal(6) +
                                        "车站" + bitParser.parseBitsToDecimal(3) +
                                        "股道" + bitParser.parseBitsToDecimal(5));
                        trainPositionReport.setOprMDeparttime(bitParser.parseTime(32));
                        trainPositionReport.setOprNidArrivaltrack(
                                "大区" + bitParser.parseBitsToDecimal(7) +
                                        "分区" + bitParser.parseBitsToDecimal(3) +
                                        "车站" + bitParser.parseBitsToDecimal(6) +
                                        "车站" + bitParser.parseBitsToDecimal(3) +
                                        "股道" + bitParser.parseBitsToDecimal(5));
                        trainPositionReport.setOprMArrivaltime(bitParser.parseTime(32));
                        trainPositionReport.setOprMTask(MTask.get(new Byte(bitParser.parseBitsToDecimal(2))));
                        trainPositionReport.setOprMSkip(MSkip.get(new Byte(bitParser.parseBitsToDecimal(2))));
                        length -= 129;
                        if(length >= 30){
                            trainPositionReport.setVsNidPacket(bitParser.parseBitsToDecimal(8));
                            trainPositionReport.setVsLPacket(bitParser.parseBitsToDecimal(13));
                            trainPositionReport.setVsNidXuser(bitParser.parseBitsToDecimal(9));
                            trainPositionReport.setVsContainLPacket(bitParser.parseBitsToDecimal(13));
                            trainPositionReport.setVsNidEngine(bitParser.parseBitsToDecimal(24));
                            trainPositionReport.setVsNidOperational(bitParser.parseBitsToDecimal(32));
                            trainPositionReport.setVsNidDriver(bitParser.parseBCD(32));
                            trainPositionReport.setVsMLevel(parseCTCSLevel(new Byte(bitParser.parseBitsToDecimal(3))));
                            if(trainPositionReport.getPosMLevel().equals("CTCS 2级"))
                                trainPositionReport.setVsMMode(MMode2.get(new Byte(bitParser.parseBitsToDecimal(4))));
                            else
                                trainPositionReport.setVsMMode(MMode3.get(new Byte(bitParser.parseBitsToDecimal(4))));
                            trainPositionReport.setVsQStopstatus(QSS.get(new Byte(bitParser.parseBitsToDecimal(4))));
                            trainPositionReport.setVsMAtoMode(MAM.get(new Byte(bitParser.parseBitsToDecimal(4))));
                            trainPositionReport.setVsMAtoControlStrategy(MAS.get(new Byte(bitParser.parseBitsToDecimal(4))));
                            trainPositionReport.setVsMTraintype(parseTrainType(new Byte(bitParser.parseBitsToDecimal(8))));
                            trainPositionReport.setVsNidTrack(
                                    "大区" + bitParser.parseBitsToDecimal(7) +
                                            "分区" + bitParser.parseBitsToDecimal(3) +
                                            "车站" + bitParser.parseBitsToDecimal(6) +
                                            "车站" + bitParser.parseBitsToDecimal(3) +
                                            "股道" + bitParser.parseBitsToDecimal(5));
                            trainPositionReport.setVsMDoormode(MDM.get(new Byte(bitParser.parseBitsToDecimal(2))));
                            trainPositionReport.setVsDoorstatus(MDS.get(new Byte(bitParser.parseBitsToDecimal(2))));
                            trainPositionReport.setVsMAtoerror(bitParser.parseBitsToDecimal(16));
                            trainPositionReport.setVsMPosition(bitParser.parseBitsToDecimal(32));
                        }
                    }
                    else {
                        trainPositionReport.setVsNidPacket(trainPositionReport.getOscNidPacket()); trainPositionReport.setOscNidPacket("");
                        trainPositionReport.setVsLPacket(trainPositionReport.getOscLPacket()); trainPositionReport.setOscLPacket("");
                        trainPositionReport.setVsNidXuser(trainPositionReport.getOscNidXuser()); trainPositionReport.setOscNidXuser("");
                        trainPositionReport.setVsContainLPacket(bitParser.parseBitsToDecimal(13));
                        trainPositionReport.setVsNidEngine(bitParser.parseBitsToDecimal(24));
                        trainPositionReport.setVsNidOperational(bitParser.parseBitsToDecimal(32));
                        trainPositionReport.setVsNidDriver(bitParser.parseBCD(32));
                        trainPositionReport.setVsMLevel(parseCTCSLevel(new Byte(bitParser.parseBitsToDecimal(3))));
                        if(trainPositionReport.getPosMLevel().equals("CTCS 2级"))
                            trainPositionReport.setVsMMode(MMode2.get(new Byte(bitParser.parseBitsToDecimal(4))));
                        else
                            trainPositionReport.setVsMMode(MMode3.get(new Byte(bitParser.parseBitsToDecimal(4))));
                        trainPositionReport.setVsQStopstatus(QSS.get(new Byte(bitParser.parseBitsToDecimal(4))));
                        trainPositionReport.setVsMAtoMode(MAM.get(new Byte(bitParser.parseBitsToDecimal(4))));
                        trainPositionReport.setVsMAtoControlStrategy(MAS.get(new Byte(bitParser.parseBitsToDecimal(4))));
                        trainPositionReport.setVsMTraintype(parseTrainType(new Byte(bitParser.parseBitsToDecimal(8))));
                        trainPositionReport.setVsNidTrack(
                                "大区" + bitParser.parseBitsToDecimal(7) +
                                        "分区" + bitParser.parseBitsToDecimal(3) +
                                        "车站" + bitParser.parseBitsToDecimal(6) +
                                        "车站" + bitParser.parseBitsToDecimal(3) +
                                        "股道" + bitParser.parseBitsToDecimal(5));
                        trainPositionReport.setVsMDoormode(MDM.get(new Byte(bitParser.parseBitsToDecimal(2))));
                        trainPositionReport.setVsDoorstatus(MDS.get(new Byte(bitParser.parseBitsToDecimal(2))));
                        trainPositionReport.setVsMAtoerror(bitParser.parseBitsToDecimal(16));
                        trainPositionReport.setVsMPosition(bitParser.parseBitsToDecimal(32));
                    }
                }
            }
        }
        return trainPositionReport;
    }
    public static MessageVerify parseMessageVerify(byte[] data) throws Exception {
        MessageVerify messageVerify = new MessageVerify();
        BitParser bitParser = new BitParser(data);

        messageVerify.setNidMessage(bitParser.parseBitsToDecimal(8));
        messageVerify.setLMessage(bitParser.parseBitsToDecimal(10));
        messageVerify.setSendTTrain(bitParser.parseTime(32));
        messageVerify.setNidEngine(bitParser.parseBitsToDecimal(24));
        messageVerify.setVerifyTTrain(bitParser.parseTime(32));
        return messageVerify;
    }
    public static TaskEnd parseTaskEnd(byte[] data) throws Exception {
        TaskEnd taskEnd = new TaskEnd();
        BitParser bitParser = new BitParser(data);

        taskEnd.setNidMessage(bitParser.parseBitsToDecimal(8));
        taskEnd.setLMessage(bitParser.parseBitsToDecimal(10));
        taskEnd.setTTrain(bitParser.parseTime(32));
        taskEnd.setNidEngine(bitParser.parseBitsToDecimal(24));

        taskEnd.setPosNidPacket(bitParser.parseBitsToDecimal(8));
        taskEnd.setPosLPacket(bitParser.parseBitsToDecimal(13));
        taskEnd.setPosQScale(parseQScale(new Byte(bitParser.parseBitsToDecimal(2))));
        taskEnd.setPosNidLrbg(bitParser.parseBitsToDecimal(10) + bitParser.parseBitsToDecimal(14));
        if(taskEnd.getPosNidPacket().equals("1")) taskEnd.setPosNidPrvbg(bitParser.parseBitsToDecimal(24));
        taskEnd.setPosDLrbg(bitParser.parseBitsToDecimal(15));
        taskEnd.setPosQDirlrbg(QDLrbg.get(new Byte(bitParser.parseBitsToDecimal(2))));
        taskEnd.setPosQDlrbg(QDLrbg.get(new Byte(bitParser.parseBitsToDecimal(2))));
        taskEnd.setPosLDoubtover(bitParser.parseBitsToDecimal(15));
        taskEnd.setPosLDoubtunder(bitParser.parseBitsToDecimal(15));
        String temp = bitParser.parseBitsToDecimal(2);
        taskEnd.setPosQLength(temp.equals("0") ? "无完整信息" : temp);
        taskEnd.setPosVTrain(bitParser.parseBitsToDecimal(7) + "km/h");
        taskEnd.setPosQDirtrain(QDLrbg.get(new Byte(bitParser.parseBitsToDecimal(2))));
        taskEnd.setPosMLevel(parseCTCSLevel(new Byte(bitParser.parseBitsToDecimal(3))));
        if(taskEnd.getPosMLevel().equals("CTCS 2级")) taskEnd.setPosMMode(MMode2.get(new Byte(bitParser.parseBitsToDecimal(4))));
        else taskEnd.setPosMMode(MMode3.get(new Byte(bitParser.parseBitsToDecimal(4))));
        taskEnd.setPosNidStm(bitParser.parseBitsToDecimal(8).equals("3") ? "CTCS 2级" : "unknown");

        return taskEnd;
    }
    public static VersionIncompatible parseVersionIncompatible(byte[] data) throws Exception {
        VersionIncompatible versionIncompatible = new VersionIncompatible();
        BitParser bitParser = new BitParser(data);

        versionIncompatible.setNidMessage(bitParser.parseBitsToDecimal(8));
        versionIncompatible.setLMessage(bitParser.parseBitsToDecimal(10));
        versionIncompatible.setTTrain(bitParser.parseTime(32));
        versionIncompatible.setNidEngine(bitParser.parseBitsToDecimal(24));
        return versionIncompatible;
    }
    public static CommunicationDialogBegin parseCommunicationDialogBegin(byte[] data) throws Exception {
        CommunicationDialogBegin communicationDialogBegin = new CommunicationDialogBegin();
        BitParser bitParser = new BitParser(data);

        communicationDialogBegin.setNidMessage(bitParser.parseBitsToDecimal(8));
        communicationDialogBegin.setLMessage(bitParser.parseBitsToDecimal(10));
        communicationDialogBegin.setTTrain(bitParser.parseTime(32));
        communicationDialogBegin.setNidEngine(bitParser.parseBitsToDecimal(24));
        return communicationDialogBegin;
    }
    public static CommunicationDialogEnd parseCommunicationDialogEnd(byte[] data) throws Exception {
        CommunicationDialogEnd communicationDialogEnd = new CommunicationDialogEnd();
        BitParser bitParser = new BitParser(data);

        communicationDialogEnd.setNidMessage(bitParser.parseBitsToDecimal(8));
        communicationDialogEnd.setLMessage(bitParser.parseBitsToDecimal(10));
        communicationDialogEnd.setTTrain(bitParser.parseTime(32));
        communicationDialogEnd.setNidEngine(bitParser.parseBitsToDecimal(24));
        return communicationDialogEnd;
    }
    public static SomPositionReport parseSomPositionReport(byte[] data) throws Exception {
        SomPositionReport somPositionReport = new SomPositionReport();
        BitParser bitParser = new BitParser(data);

        int length = data.length * 8;
        somPositionReport.setNidMessage(bitParser.parseBitsToDecimal(8));
        somPositionReport.setLMessage(bitParser.parseBitsToDecimal(10));
        somPositionReport.setTTrain(bitParser.parseTime(32));
        somPositionReport.setNidEngine(bitParser.parseBitsToDecimal(24));
        somPositionReport.setQStatus(QS.get(new Byte(bitParser.parseBitsToDecimal(2))));

        somPositionReport.setPosNidPacket(bitParser.parseBitsToDecimal(8));
        somPositionReport.setPosLPacket(bitParser.parseBitsToDecimal(13));
        somPositionReport.setPosQScale(parseQScale(new Byte(bitParser.parseBitsToDecimal(2))));
        somPositionReport.setPosNidLrbg(bitParser.parseBitsToDecimal(10) + bitParser.parseBitsToDecimal(14));
        if(somPositionReport.getPosNidPacket().equals("1")) somPositionReport.setPosNidPrvbg(bitParser.parseBitsToDecimal(24));
        somPositionReport.setPosDLrbg(bitParser.parseBitsToDecimal(15));
        somPositionReport.setPosQDirlrbg(QDLrbg.get(new Byte(bitParser.parseBitsToDecimal(2))));
        somPositionReport.setPosQDlrbg(QDLrbg.get(new Byte(bitParser.parseBitsToDecimal(2))));
        somPositionReport.setPosLDoubtover(bitParser.parseBitsToDecimal(15));
        somPositionReport.setPosLDoubtunder(bitParser.parseBitsToDecimal(15));
        String temp = bitParser.parseBitsToDecimal(2);
        somPositionReport.setPosQLength(temp.equals("0") ? "无完整信息" : temp);
        somPositionReport.setPosVTrain(bitParser.parseBitsToDecimal(7) + "km/h");
        somPositionReport.setPosQDirtrain(QDLrbg.get(new Byte(bitParser.parseBitsToDecimal(2))));
        somPositionReport.setPosMLevel(parseCTCSLevel(new Byte(bitParser.parseBitsToDecimal(3))));
        if(somPositionReport.getPosMLevel().equals("CTCS 2级")) somPositionReport.setPosMMode(MMode2.get(new Byte(bitParser.parseBitsToDecimal(4))));
        else somPositionReport.setPosMMode(MMode3.get(new Byte(bitParser.parseBitsToDecimal(4))));
        somPositionReport.setPosNidStm(bitParser.parseBitsToDecimal(8).equals("3") ? "CTCS 2级" : "unknown");
        length -= somPositionReport.getPosNidPacket().equals("1") ? 220 : 196;

        if(length >= 29) {
            somPositionReport.setErrNidPacket(bitParser.parseBitsToDecimal(8));
            somPositionReport.setErrLPacket(bitParser.parseBitsToDecimal(13));
            somPositionReport.setErrMError(MError.get(new Byte(bitParser.parseBitsToDecimal(8))));
        }
        return somPositionReport;
    }
    public static CommunicationDialogEstablished parseCommunicationDialogEstablished(byte[] data) throws Exception {
        CommunicationDialogEstablished communicationDialogEstablished = new CommunicationDialogEstablished();
        BitParser bitParser = new BitParser(data);

        communicationDialogEstablished.setNidMessage(bitParser.parseBitsToDecimal(8));
        communicationDialogEstablished.setLMessage(bitParser.parseBitsToDecimal(10));
        communicationDialogEstablished.setTTrain(bitParser.parseTime(32));
        communicationDialogEstablished.setNidEngine(bitParser.parseBitsToDecimal(24));
        return communicationDialogEstablished;
    }
    public static TrainDataVerify parseTrainDataVerify(byte[] data) throws Exception {
        TrainDataVerify trainDataVerify = new TrainDataVerify();
        BitParser bitParser = new BitParser(data);

        trainDataVerify.setNidMessage(bitParser.parseBitsToDecimal(8));
        trainDataVerify.setLMessage(bitParser.parseBitsToDecimal(10));
        trainDataVerify.setSendTTrain(bitParser.parseTime(32));
        trainDataVerify.setMAck(bitParser.parseBitsToDecimal(1));
        trainDataVerify.setNidLrbg(bitParser.parseBitsToDecimal(10) + bitParser.parseBitsToDecimal(14));
        trainDataVerify.setVerifyTTrain(bitParser.parseTime(32));
        return trainDataVerify;
    }
    public static CommonMessage parseCommonMessage(byte[] data) throws Exception {
        CommonMessage commonMessage = new CommonMessage();
        BitParser bitParser = new BitParser(data);

        int length = data.length;
        commonMessage.setNidMessage(bitParser.parseBitsToDecimal(8));
        commonMessage.setLMessage(bitParser.parseBitsToDecimal(10));
        commonMessage.setTTrain(bitParser.parseTime(32));
        commonMessage.setMAck(bitParser.parseBitsToDecimal(1));
        commonMessage.setNidLrbg(bitParser.parseBitsToDecimal(10) + bitParser.parseBitsToDecimal(14));
        length -= 79;
        String s;
        while(length > 8){
            String flag = bitParser.parseBitsToDecimal(8);
            switch (flag){
                case "3":
                    commonMessage.setCpNidPacket(flag);
                    commonMessage.setCpQDir(QDir3.get(new Byte(bitParser.parseBitsToDecimal(2))));
                    commonMessage.setCpLPacket(bitParser.parseBitsToDecimal(13));
                    commonMessage.setCpQScale(parseQScale(new Byte(bitParser.parseBitsToDecimal(2))));
                    commonMessage.setCpDValidnv(bitParser.parseBitsToDecimal(15));
                    commonMessage.setCpNIter(bitParser.parseBitsToDecimal(5));
                    commonMessage.setCpNidC(bitParser.parseBitsToDecimal(10));
                    s = bitParser.parseBitsToDecimal(7); commonMessage.setCpVNvshunt(s.equals("8") ? "40km/h" : s);
                    s = bitParser.parseBitsToDecimal(7); commonMessage.setCpVNvstff(s.equals("8") ? "40km/h" : s);
                    s = bitParser.parseBitsToDecimal(7); commonMessage.setCpVNvonsight(s.equals("8") ? "40km/h" : s);
                    s = bitParser.parseBitsToDecimal(7); commonMessage.setCpVNvunfit(s.equals("16") ? "80km/h" : s);
                    commonMessage.setCpVNvrel(bitParser.parseBitsToDecimal(7));
                    commonMessage.setCpDNvroll(bitParser.parseBitsToDecimal(15));
                    s = bitParser.parseBitsToDecimal(1); commonMessage.setCpQNvsrbktrg(s.equals("1") ? "允许" : s);
                    s = bitParser.parseBitsToDecimal(1); commonMessage.setCpQNvemrrls(s.equals("0") ? "仅当停稳" : s);
                    commonMessage.setCpVNvallowovtrp(bitParser.parseBitsToDecimal(7));
                    s = bitParser.parseBitsToDecimal(7); commonMessage.setCpVNvsupovtrp(s.equals("8") ? "40km/h" : s);
                    commonMessage.setCpDNvovtrp(bitParser.parseBitsToDecimal(15));
                    commonMessage.setCpTNvovtrp(bitParser.parseBitsToDecimal(8) + "s");
                    commonMessage.setCpDNvpotrp(bitParser.parseBitsToDecimal(15));
                    s = bitParser.parseBitsToDecimal(2); commonMessage.setCpMNvcontact(s.equals("2") ? "无反应" : s);
                    s = bitParser.parseBitsToDecimal(8); commonMessage.setCpTNvcontact(s.equals("255") ? "不监控" : s);
                    s = bitParser.parseBitsToDecimal(1); commonMessage.setCpMNvderun(s.equals("0") ? "否" : s);
                    s = bitParser.parseBitsToDecimal(15); commonMessage.setCpDNvstff(s.equals("32767") ? "无穷" : s);
                    s = bitParser.parseBitsToDecimal(1); commonMessage.setCpQNvdriverAdhes(s.equals("0") ? "不允许" : s);
                    break;
                case "21":
                    commonMessage.setCcNidPacket(flag);
                    commonMessage.setCcQDir(CcQDir.get(new Byte(bitParser.parseBitsToDecimal(2))));
                    commonMessage.setCcLPacket(bitParser.parseBitsToDecimal(13));
                    commonMessage.setCcQScale(parseQScale(new Byte(bitParser.parseBitsToDecimal(2))));
                    commonMessage.setCcDGradient(bitParser.parseBitsToDecimal(15));
                    commonMessage.setCcQGdir(CcQGDir.get(new Byte(bitParser.parseBitsToDecimal(1))));
                    commonMessage.setCcGA(bitParser.parseBitsToDecimal(8));
                    commonMessage.setCcNIter(bitParser.parseBitsToDecimal(5));
                    commonMessage.setCcDGradientK(bitParser.parseBitsToDecimal(15));
                    commonMessage.setCcQGdirK(CcQGDir.get(new Byte(bitParser.parseBitsToDecimal(1))));
                    commonMessage.setCcGAK(bitParser.parseBitsToDecimal(8));
                    break;
                case "27":
                    commonMessage.setSscNidPacket(flag);
                    commonMessage.setSscQDir(CcQDir.get(new Byte(bitParser.parseBitsToDecimal(2))));
                    commonMessage.setSscLPacket(bitParser.parseBitsToDecimal(13));
                    commonMessage.setSscQScale(parseQScale(new Byte(bitParser.parseBitsToDecimal(2))));
                    commonMessage.setSscDStatic(bitParser.parseBitsToDecimal(15));
                    s = bitParser.parseBitsToDecimal(7); commonMessage.setSscVStatic(s.equals("127") ? "-" : s + "km/h");
                    commonMessage.setSscQFront(QFront.get(new Byte(bitParser.parseBitsToDecimal(1))));
                    commonMessage.setSscNIterType(bitParser.parseBitsToDecimal(5));
                    commonMessage.setSscNIterSpeed(bitParser.parseBitsToDecimal(5));
                    commonMessage.setSscDStaticK(bitParser.parseBitsToDecimal(15));
                    commonMessage.setSscVStaticK(bitParser.parseBitsToDecimal(7));
                    commonMessage.setSscQFrontK(QFront.get(new Byte(bitParser.parseBitsToDecimal(1))));
                    commonMessage.setSscNIterTypeK(bitParser.parseBitsToDecimal(5));
                    break;
                case "58":
                    commonMessage.setPrpLPacket(flag);
                    s = bitParser.parseBitsToDecimal(2); commonMessage.setPrpQDir(s.equals("2") ? "双向有效" : s);
                    commonMessage.setPrpLPacket(bitParser.parseBitsToDecimal(13));
                    commonMessage.setPrpQScale(parseQScale(new Byte(bitParser.parseBitsToDecimal(2))));
                    commonMessage.setPrpTCycloc(bitParser.parseBitsToDecimal(8) + "s");
                    s = bitParser.parseBitsToDecimal(15); commonMessage.setPrpDCycloc(s.equals("32767") ? "不必按周期报告" : s);
                    commonMessage.setPrpMLoc("每通过" + bitParser.parseBitsToDecimal(3) + "个应答器组");
                    commonMessage.setPrpNIter(bitParser.parseBitsToDecimal(5));
                    break;
                case "72":
                    commonMessage.setTsNidPacket(flag);
                    commonMessage.setTsQDir(QDir3.get(new Byte(bitParser.parseBitsToDecimal(2))));
                    commonMessage.setTsLPacket(bitParser.parseBitsToDecimal(13));
                    commonMessage.setTsQScale(parseQScale(new Byte(bitParser.parseBitsToDecimal(2))));
                    commonMessage.setTsQTextclass(QTC.get(new Byte(bitParser.parseBitsToDecimal(2))));
                    commonMessage.setTsQTextdisplay(QTD.get(new Byte(bitParser.parseBitsToDecimal(1))));
                    s = bitParser.parseBitsToDecimal(15); commonMessage.setTsDTextdisplay(s.equals("32767") ? "不受距离限制" : s);
                    s = bitParser.parseBitsToDecimal(4);commonMessage.setTsMModetextdisplay1(s.equals("15") ? "不受距离限制" : s);
                    commonMessage.setTsMLeveltextdisplay1(parseMesLevel(new Byte(bitParser.parseBitsToDecimal(3))));
                    if(commonMessage.getTsMLeveltextdisplay1().equals("C2")){
                        s = bitParser.parseBitsToDecimal(8);
                        commonMessage.setTsNidStm1(s.equals("3") ? "CTCS 2级" : s);
                    }
                    s = bitParser.parseBitsToDecimal(15); commonMessage.setTsLTextdisplay(s.equals("32767") ? "不受距离限制" : s);
                    s = bitParser.parseBitsToDecimal(10); commonMessage.setTsTTextdisplay(s.equals("1023") ? "不受时间限制" : s);
                    s = bitParser.parseBitsToDecimal(4); commonMessage.setTsMModetextdisplay2(s.equals("1023") ? "不受模式限制" : s);
                    commonMessage.setTsMLeveltextdisplay2(parseMesLevel(new Byte(bitParser.parseBitsToDecimal(3))));
                    if(commonMessage.getTsMLeveltextdisplay1().equals("C2")){
                        s = bitParser.parseBitsToDecimal(8);
                        commonMessage.setTsNidStm2(s.equals("3") ? "CTCS 2级" : s);
                    }
                    commonMessage.setTsQTextconfirm(QTextConfirm.get(new Byte(bitParser.parseBitsToDecimal(2))));
                    commonMessage.setTsLText(bitParser.parseBitsToDecimal(8));
                    commonMessage.setTsXText(bitParser.parseBitsToDecimal(8));
                    break;
                case "44":
                    s = bitParser.parseBitsToDecimal(2); String qDir = s.equals("2") ? "双向有效" : s;
                    String lPacket = bitParser.parseBitsToDecimal(13);
                    String nidXuser = bitParser.parseBitsToDecimal(9);
                    switch (nidXuser){
                        case "2":
                            commonMessage.setTslNidPacket(flag);
                            commonMessage.setTslQDir(qDir);
                            commonMessage.setTslLPacket(lPacket);
                            commonMessage.setTslNidXuser(nidXuser);
                            commonMessage.setTslContainQDir(QDir4.get(new Byte(bitParser.parseBitsToDecimal(2))));
                            commonMessage.setTslContainLPacket(bitParser.parseBitsToDecimal(13));
                            commonMessage.setTslQScale(parseQScale(new Byte(bitParser.parseBitsToDecimal(2))));
                            commonMessage.setTslLTsrarea(bitParser.parseBitsToDecimal(15));
                            commonMessage.setTslDTsr(bitParser.parseBitsToDecimal(15));
                            commonMessage.setTslLTsr(bitParser.parseBitsToDecimal(15));
                            commonMessage.setTslQFront(QF.get(new Byte(bitParser.parseBitsToDecimal(1))));
                            commonMessage.setTslVTsr(bitParser.parseBitsToDecimal(7) + "km/h");
                            commonMessage.setTslNIter(bitParser.parseBitsToDecimal(5));
                            for (int i = 0; i < Integer.parseInt(commonMessage.getTslNIter()); i++) {
                                commonMessage.setTslDTsrN(commonMessage.getTslDTsrN() + "," + bitParser.parseBitsToDecimal(15));
                                commonMessage.setTslLTsrN(commonMessage.getTslLTsrN() + "," + bitParser.parseBitsToDecimal(15));
                                commonMessage.setTslQFrontN(commonMessage.getTslQFrontN() + "," + QF.get(new Byte(bitParser.parseBitsToDecimal(1))));
                                commonMessage.setTslVTsrN(commonMessage.getTslVTsrN() + "," + bitParser.parseBitsToDecimal(7) + "km/h");
                            }
                            break;
                        case "12":
                            commonMessage.setAcdmNidPacket(flag);
                            commonMessage.setAcdmQDir(qDir);
                            commonMessage.setAcdmLPacket(lPacket);
                            commonMessage.setAcdmNidXuser(nidXuser);
                            commonMessage.setAcdmContainQDir(QDir4.get(new Byte(bitParser.parseBitsToDecimal(2))));
                            commonMessage.setAcdmContainLPacket(bitParser.parseBitsToDecimal(13));
                            commonMessage.setAcdmQTsrs(QTSRS.get(new Byte(bitParser.parseBitsToDecimal(1))));
                            commonMessage.setAcdmNidC(bitParser.parseBitsToDecimal(10));
                            commonMessage.setAcdmNidTsrs(bitParser.parseBitsToDecimal(14));
                            commonMessage.setAcdmNidRadio(bitParser.parseIPAddress());
                            commonMessage.setAcdmQSleepsession(QSleep.get(new Byte(bitParser.parseBitsToDecimal(1))));
                            break;
                        case "41":
                            commonMessage.setAopNidPacket(flag);
                            commonMessage.setAopQDir(qDir);
                            commonMessage.setAopLPacket(lPacket);
                            commonMessage.setAopNidXuser(nidXuser);
                            s = bitParser.parseBitsToDecimal(2); commonMessage.setAopContainQDir(s.equals("2") ? "双向有效" : s);
                            commonMessage.setAopContainLPacket(bitParser.parseBitsToDecimal(13));
                            commonMessage.setAopMWaysidetime(bitParser.parseTime(32));
                            commonMessage.setAopNidDeparttrack(
                            "大区" + bitParser.parseBitsToDecimal(7) +
                            "分区" + bitParser.parseBitsToDecimal(3) +
                            "车站" + bitParser.parseBitsToDecimal(6) +
                            "车站" + bitParser.parseBitsToDecimal(3) +
                            "股道" + bitParser.parseBitsToDecimal(5));
                            commonMessage.setAopMDeparttime(bitParser.parseTime(32));
                            commonMessage.setAopNidArrivaltrack(
                            "大区" + bitParser.parseBitsToDecimal(7) +
                            "分区" + bitParser.parseBitsToDecimal(3) +
                            "车站" + bitParser.parseBitsToDecimal(6) +
                            "车站" + bitParser.parseBitsToDecimal(3) +
                            "股道" + bitParser.parseBitsToDecimal(5));
                            commonMessage.setAopMArrivaltime(bitParser.parseTime(32));
                            commonMessage.setAopMTask(MTask.get(new Byte(bitParser.parseBitsToDecimal(2))));
                            commonMessage.setAopMSkip(MSkip.get(new Byte(bitParser.parseBitsToDecimal(2))));
                            commonMessage.setAopNIter(bitParser.parseBitsToDecimal(5));
                            for (int i = 0; i < Integer.parseInt(commonMessage.getAopNIter()); i++) {
                                commonMessage.setAopNidDeparttrackI(commonMessage.getAopNidDeparttrackI() +
                                "大区" + bitParser.parseBitsToDecimal(7) +
                                "分区" + bitParser.parseBitsToDecimal(3) +
                                "车站" + bitParser.parseBitsToDecimal(6) +
                                "车站" + bitParser.parseBitsToDecimal(3) +
                                "股道" + bitParser.parseBitsToDecimal(5));
                                commonMessage.setAopMDeparttimeI(commonMessage.getAopMDeparttimeI() + bitParser.parseTime(32));
                                commonMessage.setAopNidArrivaltrackI(commonMessage.getAopNidArrivaltrackI() +
                                "大区" + bitParser.parseBitsToDecimal(7) +
                                "分区" + bitParser.parseBitsToDecimal(3) +
                                "车站" + bitParser.parseBitsToDecimal(6) +
                                "车站" + bitParser.parseBitsToDecimal(3) +
                                "股道" + bitParser.parseBitsToDecimal(5));
                                commonMessage.setAopMArrivaltimeI(commonMessage.getAopMArrivaltimeI() + bitParser.parseTime(32));
                                commonMessage.setAopMTaskI(commonMessage.getAopMTaskI() + MTask.get(new Byte(bitParser.parseBitsToDecimal(2))));
                                commonMessage.setAopMSkipI(commonMessage.getAopMSkipI() + MSkip.get(new Byte(bitParser.parseBitsToDecimal(2))));
                            }
                            break;
                        case "42":
                            commonMessage.setSdNidPacket(flag);
                            commonMessage.setSdQDir(qDir);
                            commonMessage.setSdLPacket(lPacket);
                            commonMessage.setSdNidXuser(nidXuser);
                            commonMessage.setSdContainQDir(QDir4.get(new Byte(bitParser.parseBitsToDecimal(2))));
                            commonMessage.setSdContainLPacket(bitParser.parseBitsToDecimal(13));
                            commonMessage.setSdQScale(parseQScale(new Byte(bitParser.parseBitsToDecimal(2))));
                            commonMessage.setSdLStationdistance(bitParser.parseBitsToDecimal(15));
                            break;
                        case "43":
                            commonMessage.setOscvNidPacket(flag);
                            commonMessage.setOscvQDir(qDir);
                            commonMessage.setOscvLPacket(lPacket);
                            commonMessage.setOscvNidXuser(nidXuser);
                            s = bitParser.parseBitsToDecimal(2); commonMessage.setOscvContainQDir(s.equals("2") ? "双向有效" : s);
                            commonMessage.setOscvContainLPacket(bitParser.parseBitsToDecimal(13));
                            commonMessage.setOscvMSequencennum(bitParser.parseBitsToDecimal(16));
                            commonMessage.setOscvMLpsdstatus(MLP.get(new Byte(bitParser.parseBitsToDecimal(2))));
                            commonMessage.setOscvRpsdstatus(MLP.get(new Byte(bitParser.parseBitsToDecimal(2))));
                            break;
                    }
                    break;
            }
        }

        return commonMessage;
    }
    public static SystemVersion parseSystemVersion(byte[] data) throws Exception {
        SystemVersion systemVersion = new SystemVersion();
        BitParser bitParser = new BitParser(data);

        systemVersion.setNidMessage(bitParser.parseBitsToDecimal(8));
        systemVersion.setLMessage(bitParser.parseBitsToDecimal(10));
        systemVersion.setTTrain(bitParser.parseTime(32));
        systemVersion.setMAck(bitParser.parseBitsToDecimal(1));
        systemVersion.setNidLrbg(bitParser.parseBitsToDecimal(10) + bitParser.parseBitsToDecimal(14));
        systemVersion.setMVersion(bitParser.parseTime(7));
        return systemVersion;
    }
    public static CommunicationDialogEndVerify parseCommunicationDialogEndVerify(byte[] data) throws Exception {
        CommunicationDialogEndVerify communicationDialogEndVerify = new CommunicationDialogEndVerify();
        BitParser bitParser = new BitParser(data);

        communicationDialogEndVerify.setNidMessage(bitParser.parseBitsToDecimal(8));
        communicationDialogEndVerify.setLMessage(bitParser.parseBitsToDecimal(10));
        communicationDialogEndVerify.setTTrain(bitParser.parseTime(32));
        communicationDialogEndVerify.setMAck(bitParser.parseBitsToDecimal(1));
        communicationDialogEndVerify.setNidLrbg(bitParser.parseBitsToDecimal(10) + bitParser.parseBitsToDecimal(14));
        return communicationDialogEndVerify;
    }
    public static AcceptTrain parseAcceptTrain(byte[] data) throws Exception {
        AcceptTrain acceptTrain = new AcceptTrain();
        BitParser bitParser = new BitParser(data);

        acceptTrain.setNidMessage(bitParser.parseBitsToDecimal(8));
        acceptTrain.setLMessage(bitParser.parseBitsToDecimal(10));
        acceptTrain.setTTrain(bitParser.parseTime(32));
        acceptTrain.setMAck(bitParser.parseBitsToDecimal(1));
        acceptTrain.setNidLrbg(bitParser.parseBitsToDecimal(10) + bitParser.parseBitsToDecimal(14));
        return acceptTrain;
    }
}
