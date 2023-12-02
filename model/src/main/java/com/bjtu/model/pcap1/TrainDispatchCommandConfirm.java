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
public class TrainDispatchCommandConfirm extends PcapEntity {

    private static final long serialVersionUID = 1L;
    private String frameBegin;

    private String messageLength;

    private String sourcePortCode;

    private String sourceAddressLength;

    private String destinationPortCode;

    private String destinationAddressLength;

    private String serviceType;

    private String command;

    private String commandInfoName;

    private String functionCode;

    private String pcapTime;

    private String trainNumber;

    private String locomotiveNumber;

    private String orderNumberLow;

    private String commandInfoNumber;

    private String mileage;

    private String positionLongitude;

    private String positionLatitude;

    private String orderNumberHigh;

    private String preReserve;

    private String packageNumber;

    private String crcCheckSum;

    private String endOfFrame;


}
