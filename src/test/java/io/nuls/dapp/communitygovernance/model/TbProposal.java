package io.nuls.dapp.communitygovernance.model;

import java.math.BigDecimal;
import java.util.Date;

public class TbProposal {
    private Integer proposalId;

    private String name;

    private Byte type;

    private String email;

    private String owner;

    private Byte status;

    private Date startTime;

    private Date endTime;

    private Integer counts;

    private BigDecimal favour;

    private BigDecimal against;

    private BigDecimal abstention;

    private Byte refund;

    private Long createTime;

    private Long updateTime;

    private String desc;

    public Integer getProposalId() {
        return proposalId;
    }

    public void setProposalId(Integer proposalId) {
        this.proposalId = proposalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public BigDecimal getFavour() {
        return favour;
    }

    public void setFavour(BigDecimal favour) {
        this.favour = favour;
    }

    public BigDecimal getAgainst() {
        return against;
    }

    public void setAgainst(BigDecimal against) {
        this.against = against;
    }

    public BigDecimal getAbstention() {
        return abstention;
    }

    public void setAbstention(BigDecimal abstention) {
        this.abstention = abstention;
    }

    public Byte getRefund() {
        return refund;
    }

    public void setRefund(Byte refund) {
        this.refund = refund;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }
}