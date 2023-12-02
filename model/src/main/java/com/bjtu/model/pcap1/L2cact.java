package com.bjtu.model.pcap1;

import com.bjtu.model.base.PcapEntity;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author BJTU
 * @since 2023-04-19
 */
@Data
public class L2cact extends PcapEntity {

    private static final long serialVersionUID = 1L;
    private String frameBegin;

    private String messageLength;

    private String messageType;

    private String crcCheck;

}
