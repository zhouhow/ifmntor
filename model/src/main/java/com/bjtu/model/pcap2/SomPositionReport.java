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
public class SomPositionReport extends PcapEntity {

    private static final long serialVersionUID = 1L;


    private String nidMessage;

    private String lMessage;

    private String tTrain;

    private String nidEngine;

    private String qStatus;

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



}