package io.nuls.dapp.communitygovernance.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class TbVote {
    private Integer id;

    private String contractAddress;

    private Long contractVoteId;

    private String title;

    private String description;

    private Byte hasMultipleSelect;

    private Byte minSelectCount;

    private Byte maxSelectCount;

    private Byte voteCanModify;

    private Date startTime;

    private Date endTime;

    private BigInteger deposit;

    private Byte status;

    private Integer counts;

    private BigDecimal amount;

    private Long blockHeight;

    private String creator;

    private Long createTime;

    private Long updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress == null ? null : contractAddress.trim();
    }

    public Long getContractVoteId() {
        return contractVoteId;
    }

    public void setContractVoteId(Long contractVoteId) {
        this.contractVoteId = contractVoteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Byte getHasMultipleSelect() {
        return hasMultipleSelect;
    }

    public void setHasMultipleSelect(Byte hasMultipleSelect) {
        this.hasMultipleSelect = hasMultipleSelect;
    }

    public Byte getMinSelectCount() {
        return minSelectCount;
    }

    public void setMinSelectCount(Byte minSelectCount) {
        this.minSelectCount = minSelectCount;
    }

    public Byte getMaxSelectCount() {
        return maxSelectCount;
    }

    public void setMaxSelectCount(Byte maxSelectCount) {
        this.maxSelectCount = maxSelectCount;
    }

    public Byte getVoteCanModify() {
        return voteCanModify;
    }

    public void setVoteCanModify(Byte voteCanModify) {
        this.voteCanModify = voteCanModify;
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

    public BigInteger getDeposit() {
        return deposit;
    }

    public void setDeposit(BigInteger deposit) {
        this.deposit = deposit;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getBlockHeight() {
        return blockHeight;
    }

    public void setBlockHeight(Long blockHeight) {
        this.blockHeight = blockHeight;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
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