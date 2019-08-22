package io.nuls.dapp.communitygovernance.model.vote;

public class TbAgencyRelation {
    private String agent;

    private String mandator;

    private Byte status;

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
}