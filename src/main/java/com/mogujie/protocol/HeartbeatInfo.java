package com.mogujie.protocol;

/**
 * @author pingchun@meili-inc.com
 * @since 2019/1/31
 */
public class HeartbeatInfo {

    private String clientId;
    private Integer activeTime;
    private Integer lastReportTime;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Integer getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Integer activeTime) {
        this.activeTime = activeTime;
    }

    public Integer getLastReportTime() {
        return lastReportTime;
    }

    public void setLastReportTime(Integer lastReportTime) {
        this.lastReportTime = lastReportTime;
    }
}
