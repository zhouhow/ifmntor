package com.bjtu.vo.pcap;

import java.io.Serializable;

/**
 * <p>
 * 无线车次号查询实体
 * </p>
 */
public class PcapQueryVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String trainID;
    private String id;

    public String getTrainID() {
        return trainID;
    }

    public String getID() {
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
    public void setTrainID(String trainID) {
        this.trainID = trainID;
    }
}