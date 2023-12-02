package com.bjtu.model.pcap1;

import com.bjtu.model.base.PcapEntity;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author BJTU
 * @since 2023-05-08
 */
@Data
public class Alarm extends PcapEntity {

    private static final long serialVersionUID = 1L;

    private String type;

    private String alarmTime;


}
