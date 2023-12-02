package com.bjtu.model.pcap2;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class MessageVerify extends PcapEntity {

    private static final long serialVersionUID = 1L;

    private String nidMessage;

    private String lMessage;

    private String sendTTrain;

    private String nidEngine;

    private String verifyTTrain;

}
