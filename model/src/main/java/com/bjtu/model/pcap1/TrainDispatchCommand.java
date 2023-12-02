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
public class TrainDispatchCommand extends PcapEntity {

    private static final long serialVersionUID = 1L;

    private String frameBegin;

    private String messageLength;

    private String sourcePortCode;

    private String sourceAddressLength;

    private String destinationPortCode;

    private String destinationAddressLength;

    private String serviceType;

    private String command;

    private String functionCode;

    private String publishTime;

    private String sendTime;

    private String trainNumber;

    private String locotiveNumber;

    private String orderNumberLow;

    private String commandInfoNumber;

    private String issuersName;

    private String commandStatus;

    private String orderNumberHigh;

    private String preReserved;

    private String totalPackage;

    private String thisPackageNumber;

    private String commandText;

    private String crcCheckSum;

    private String endOfFrame;
}
