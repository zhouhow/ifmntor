package com.bjtu.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "pcap")
public class PropConfig {
    public PropConfig() {
    }
    private String nifName;
    private String filter;
    private String snapshotLength;
    private String readTimeout;

    public String getNifName() {
        return nifName;
    }

    public String getFilter() {
        return filter;
    }

    public void setNifName(String nifName) {
        this.nifName = nifName;
    }
    public void setFilter(String filter) {
        this.filter = filter;
    }
}
