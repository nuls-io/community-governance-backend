package io.nuls.dapp.communitygovernance.model;

public class TbAgencyRelation {
    private String agent;

    private String mandator;

    private Byte status;

    private Long createTime;

    private Long updateTime;

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent == null ? null : agent.trim();
    }

    public String getMandator() {
        return mandator;
    }

    public void setMandator(String mandator) {
        this.mandator = mandator == null ? null : mandator.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}