package com.bjtu.model.pcap1;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bjtu.model.base.PcapEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author BJTU
 * @since 2023-04-17
 */
@Data
@ApiModel(description = "无线车次号校验")
@TableName("train_number_verify")
public class TrainNumberVerify  extends PcapEntity {

    private static final long serialVersionUID = 1L;
    private String frameStart;

    private String messageLength;

    private String sourcePortCode;

    private String sourceCommunicationAddressLength;

    private String destinationPortCode;

    private String destinationCommunicationAddressLength;

    private String businessType;

    private String command;

    private String localBoardAddress;

    private String featureCodeOne;

    private String flags;

    private Integer versionNumber;

    private String reservedOne;

    private Integer stationNumber;

    private String trainNumberType;

    private String driverNumber;

    private String deputyDriverNumber;

    private String reservedTwo;

    private String locomotiveTypeNumber;

    private String actualRouteNumber;

    private String reservedThree;

    private String replacementPassengerOrCargoOne;

    private String trainNumber;

    private String checkAndOne;

    private String addressOfThisBoard;

    private String featureCodeTwo;

    private String detectionUnitCode;

    private String dateTime;

    private Integer actualSpeed;

    private String locomotiveSignal;

    private String locomotiveWorkingCondition;

    private String signalMachineNumber;

    private String signalMachineType;

    private String mileage;

    private String totalWeight;

    private String lengthNote;

    private Integer numberOfVehicles;

    private String replacementPassengerOrCargoTwo;

    private String trainNumberFive;

    private String sectionNumber;

    private Integer stationNumberTwo;

    private String driverNumberTwo;

    private String deputyDriverNumberTwo;

    private String locomotiveNumber;

    private Integer locomotiveTypeNumberTwo;

    private String trainTubePressure;

    private String deviceStatus;

    private String reservedFour;

    private String checkAndTwo;

    private String lineNumber;

    private String transmissionTotalNumber;
    @TableField("transmission_total_number_to_LTE")
    private String transmissionTotalNumberToLte;

    private String currentCarNumberSendTotalNum;

    private String preReservedOne;

    @TableField("CTC_dedicated_domain")
    private String ctcDedicatedDomain;

    private String preReservedTwo;

    @TableField("follow_ID")
    private String followId;

    @TableField("community_ID")
    private String communityId;

    private String positionStatus;

    private String currentLongitude;

    private String currentPositionLatitude;

    private String pcapTime;
    @TableField("CRC_checksum")
    private String crcChecksum;

    private String endOfFrame;

}
