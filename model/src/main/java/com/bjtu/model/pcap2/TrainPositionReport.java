package com.bjtu.model.pcap2;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.bjtu.model.base.PcapEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author BJTU
 * @since 2023-05-16
 */
@Data
public class TrainPositionReport extends PcapEntity {

    private static final long serialVersionUID = 1L;



    private String nidMessage;

    private String lMessage;

    private String tTrain;

    private String nidEngine;

    private String posNidPacket;

    private String posLPacket;

    private String posQScale;

    private String posNidLrbg;

    private String posNidPrvbg;

    private String posDLrbg;

    private String posQDirlrbg;

    private String posQDlrbg;

    private String posLDoubtover;

    private String posLDoubtunder;

    private String posQLength;

    private String posVTrain;

    private String posQDirtrain;

    private String posMMode;

    private String posMLevel;

    private String posNidStm;

    private String errNidPacket;

    private String errLPacket;

    private String errMError;

    private String oscNidPacket;

    private String oscLPacket;

    private String oscNidXuser;

    private String oscContainLPacket;

    private String oscMSequencenum;

    private String oscMTraintype;

    private String oscNidTrack;

    private String oscQDir;

    private String oscQTb;

    private String oscMLdoorcmd;

    private String oscMRdoorcmd;

    private String oprNidPacket;

    private String oprLPacket;

    private String oprNidXuser;

    private String oprContainLPacket;

    private String oprNidDepaturetrack;

    private String oprMDeparttime;

    private String oprNidArrivaltrack;

    private String oprMArrivaltime;

    private String oprMTask;

    private String oprMSkip;

    private String vsNidPacket;

    private String vsLPacket;

    private String vsNidXuser;

    private String vsContainLPacket;

    private String vsNidEngine;

    private String vsNidOperational;

    private String vsNidDriver;

    private String vsMLevel;

    private String vsMMode;

    private String vsQStopstatus;

    private String vsMAtoMode;

    private String vsMAtoControlStrategy;

    private String vsMTraintype;

    private String vsNidTrack;

    private String vsMDoormode;

    private String vsDoorstatus;

    private String vsMAtoerror;

    private String vsMPosition;


}
