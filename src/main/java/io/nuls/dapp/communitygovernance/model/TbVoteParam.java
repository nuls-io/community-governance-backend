package io.nuls.dapp.communitygovernance.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbVoteParam {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbVoteParam() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andContractAddressIsNull() {
            addCriterion("contract_address is null");
            return (Criteria) this;
        }

        public Criteria andContractAddressIsNotNull() {
            addCriterion("contract_address is not null");
            return (Criteria) this;
        }

        public Criteria andContractAddressEqualTo(String value) {
            addCriterion("contract_address =", value, "contractAddress");
            return (Criteria) this;
        }

        public Criteria andContractAddressNotEqualTo(String value) {
            addCriterion("contract_address <>", value, "contractAddress");
            return (Criteria) this;
        }

        public Criteria andContractAddressGreaterThan(String value) {
            addCriterion("contract_address >", value, "contractAddress");
            return (Criteria) this;
        }

        public Criteria andContractAddressGreaterThanOrEqualTo(String value) {
            addCriterion("contract_address >=", value, "contractAddress");
            return (Criteria) this;
        }

        public Criteria andContractAddressLessThan(String value) {
            addCriterion("contract_address <", value, "contractAddress");
            return (Criteria) this;
        }

        public Criteria andContractAddressLessThanOrEqualTo(String value) {
            addCriterion("contract_address <=", value, "contractAddress");
            return (Criteria) this;
        }

        public Criteria andContractAddressLike(String value) {
            addCriterion("contract_address like", value, "contractAddress");
            return (Criteria) this;
        }

        public Criteria andContractAddressNotLike(String value) {
            addCriterion("contract_address not like", value, "contractAddress");
            return (Criteria) this;
        }

        public Criteria andContractAddressIn(List<String> values) {
            addCriterion("contract_address in", values, "contractAddress");
            return (Criteria) this;
        }

        public Criteria andContractAddressNotIn(List<String> values) {
            addCriterion("contract_address not in", values, "contractAddress");
            return (Criteria) this;
        }

        public Criteria andContractAddressBetween(String value1, String value2) {
            addCriterion("contract_address between", value1, value2, "contractAddress");
            return (Criteria) this;
        }

        public Criteria andContractAddressNotBetween(String value1, String value2) {
            addCriterion("contract_address not between", value1, value2, "contractAddress");
            return (Criteria) this;
        }

        public Criteria andContractVoteIdIsNull() {
            addCriterion("contract_vote_id is null");
            return (Criteria) this;
        }

        public Criteria andContractVoteIdIsNotNull() {
            addCriterion("contract_vote_id is not null");
            return (Criteria) this;
        }

        public Criteria andContractVoteIdEqualTo(Long value) {
            addCriterion("contract_vote_id =", value, "contractVoteId");
            return (Criteria) this;
        }

        public Criteria andContractVoteIdNotEqualTo(Long value) {
            addCriterion("contract_vote_id <>", value, "contractVoteId");
            return (Criteria) this;
        }

        public Criteria andContractVoteIdGreaterThan(Long value) {
            addCriterion("contract_vote_id >", value, "contractVoteId");
            return (Criteria) this;
        }

        public Criteria andContractVoteIdGreaterThanOrEqualTo(Long value) {
            addCriterion("contract_vote_id >=", value, "contractVoteId");
            return (Criteria) this;
        }

        public Criteria andContractVoteIdLessThan(Long value) {
            addCriterion("contract_vote_id <", value, "contractVoteId");
            return (Criteria) this;
        }

        public Criteria andContractVoteIdLessThanOrEqualTo(Long value) {
            addCriterion("contract_vote_id <=", value, "contractVoteId");
            return (Criteria) this;
        }

        public Criteria andContractVoteIdIn(List<Long> values) {
            addCriterion("contract_vote_id in", values, "contractVoteId");
            return (Criteria) this;
        }

        public Criteria andContractVoteIdNotIn(List<Long> values) {
            addCriterion("contract_vote_id not in", values, "contractVoteId");
            return (Criteria) this;
        }

        public Criteria andContractVoteIdBetween(Long value1, Long value2) {
            addCriterion("contract_vote_id between", value1, value2, "contractVoteId");
            return (Criteria) this;
        }

        public Criteria andContractVoteIdNotBetween(Long value1, Long value2) {
            addCriterion("contract_vote_id not between", value1, value2, "contractVoteId");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andHasMultipleSelectIsNull() {
            addCriterion("has_multiple_select is null");
            return (Criteria) this;
        }

        public Criteria andHasMultipleSelectIsNotNull() {
            addCriterion("has_multiple_select is not null");
            return (Criteria) this;
        }

        public Criteria andHasMultipleSelectEqualTo(Byte value) {
            addCriterion("has_multiple_select =", value, "hasMultipleSelect");
            return (Criteria) this;
        }

        public Criteria andHasMultipleSelectNotEqualTo(Byte value) {
            addCriterion("has_multiple_select <>", value, "hasMultipleSelect");
            return (Criteria) this;
        }

        public Criteria andHasMultipleSelectGreaterThan(Byte value) {
            addCriterion("has_multiple_select >", value, "hasMultipleSelect");
            return (Criteria) this;
        }

        public Criteria andHasMultipleSelectGreaterThanOrEqualTo(Byte value) {
            addCriterion("has_multiple_select >=", value, "hasMultipleSelect");
            return (Criteria) this;
        }

        public Criteria andHasMultipleSelectLessThan(Byte value) {
            addCriterion("has_multiple_select <", value, "hasMultipleSelect");
            return (Criteria) this;
        }

        public Criteria andHasMultipleSelectLessThanOrEqualTo(Byte value) {
            addCriterion("has_multiple_select <=", value, "hasMultipleSelect");
            return (Criteria) this;
        }

        public Criteria andHasMultipleSelectIn(List<Byte> values) {
            addCriterion("has_multiple_select in", values, "hasMultipleSelect");
            return (Criteria) this;
        }

        public Criteria andHasMultipleSelectNotIn(List<Byte> values) {
            addCriterion("has_multiple_select not in", values, "hasMultipleSelect");
            return (Criteria) this;
        }

        public Criteria andHasMultipleSelectBetween(Byte value1, Byte value2) {
            addCriterion("has_multiple_select between", value1, value2, "hasMultipleSelect");
            return (Criteria) this;
        }

        public Criteria andHasMultipleSelectNotBetween(Byte value1, Byte value2) {
            addCriterion("has_multiple_select not between", value1, value2, "hasMultipleSelect");
            return (Criteria) this;
        }

        public Criteria andMinSelectCountIsNull() {
            addCriterion("min_select_count is null");
            return (Criteria) this;
        }

        public Criteria andMinSelectCountIsNotNull() {
            addCriterion("min_select_count is not null");
            return (Criteria) this;
        }

        public Criteria andMinSelectCountEqualTo(Byte value) {
            addCriterion("min_select_count =", value, "minSelectCount");
            return (Criteria) this;
        }

        public Criteria andMinSelectCountNotEqualTo(Byte value) {
            addCriterion("min_select_count <>", value, "minSelectCount");
            return (Criteria) this;
        }

        public Criteria andMinSelectCountGreaterThan(Byte value) {
            addCriterion("min_select_count >", value, "minSelectCount");
            return (Criteria) this;
        }

        public Criteria andMinSelectCountGreaterThanOrEqualTo(Byte value) {
            addCriterion("min_select_count >=", value, "minSelectCount");
            return (Criteria) this;
        }

        public Criteria andMinSelectCountLessThan(Byte value) {
            addCriterion("min_select_count <", value, "minSelectCount");
            return (Criteria) this;
        }

        public Criteria andMinSelectCountLessThanOrEqualTo(Byte value) {
            addCriterion("min_select_count <=", value, "minSelectCount");
            return (Criteria) this;
        }

        public Criteria andMinSelectCountIn(List<Byte> values) {
            addCriterion("min_select_count in", values, "minSelectCount");
            return (Criteria) this;
        }

        public Criteria andMinSelectCountNotIn(List<Byte> values) {
            addCriterion("min_select_count not in", values, "minSelectCount");
            return (Criteria) this;
        }

        public Criteria andMinSelectCountBetween(Byte value1, Byte value2) {
            addCriterion("min_select_count between", value1, value2, "minSelectCount");
            return (Criteria) this;
        }

        public Criteria andMinSelectCountNotBetween(Byte value1, Byte value2) {
            addCriterion("min_select_count not between", value1, value2, "minSelectCount");
            return (Criteria) this;
        }

        public Criteria andMaxSelectCountIsNull() {
            addCriterion("max_select_count is null");
            return (Criteria) this;
        }

        public Criteria andMaxSelectCountIsNotNull() {
            addCriterion("max_select_count is not null");
            return (Criteria) this;
        }

        public Criteria andMaxSelectCountEqualTo(Byte value) {
            addCriterion("max_select_count =", value, "maxSelectCount");
            return (Criteria) this;
        }

        public Criteria andMaxSelectCountNotEqualTo(Byte value) {
            addCriterion("max_select_count <>", value, "maxSelectCount");
            return (Criteria) this;
        }

        public Criteria andMaxSelectCountGreaterThan(Byte value) {
            addCriterion("max_select_count >", value, "maxSelectCount");
            return (Criteria) this;
        }

        public Criteria andMaxSelectCountGreaterThanOrEqualTo(Byte value) {
            addCriterion("max_select_count >=", value, "maxSelectCount");
            return (Criteria) this;
        }

        public Criteria andMaxSelectCountLessThan(Byte value) {
            addCriterion("max_select_count <", value, "maxSelectCount");
            return (Criteria) this;
        }

        public Criteria andMaxSelectCountLessThanOrEqualTo(Byte value) {
            addCriterion("max_select_count <=", value, "maxSelectCount");
            return (Criteria) this;
        }

        public Criteria andMaxSelectCountIn(List<Byte> values) {
            addCriterion("max_select_count in", values, "maxSelectCount");
            return (Criteria) this;
        }

        public Criteria andMaxSelectCountNotIn(List<Byte> values) {
            addCriterion("max_select_count not in", values, "maxSelectCount");
            return (Criteria) this;
        }

        public Criteria andMaxSelectCountBetween(Byte value1, Byte value2) {
            addCriterion("max_select_count between", value1, value2, "maxSelectCount");
            return (Criteria) this;
        }

        public Criteria andMaxSelectCountNotBetween(Byte value1, Byte value2) {
            addCriterion("max_select_count not between", value1, value2, "maxSelectCount");
            return (Criteria) this;
        }

        public Criteria andVoteCanModifyIsNull() {
            addCriterion("vote_can_modify is null");
            return (Criteria) this;
        }

        public Criteria andVoteCanModifyIsNotNull() {
            addCriterion("vote_can_modify is not null");
            return (Criteria) this;
        }

        public Criteria andVoteCanModifyEqualTo(Byte value) {
            addCriterion("vote_can_modify =", value, "voteCanModify");
            return (Criteria) this;
        }

        public Criteria andVoteCanModifyNotEqualTo(Byte value) {
            addCriterion("vote_can_modify <>", value, "voteCanModify");
            return (Criteria) this;
        }

        public Criteria andVoteCanModifyGreaterThan(Byte value) {
            addCriterion("vote_can_modify >", value, "voteCanModify");
            return (Criteria) this;
        }

        public Criteria andVoteCanModifyGreaterThanOrEqualTo(Byte value) {
            addCriterion("vote_can_modify >=", value, "voteCanModify");
            return (Criteria) this;
        }

        public Criteria andVoteCanModifyLessThan(Byte value) {
            addCriterion("vote_can_modify <", value, "voteCanModify");
            return (Criteria) this;
        }

        public Criteria andVoteCanModifyLessThanOrEqualTo(Byte value) {
            addCriterion("vote_can_modify <=", value, "voteCanModify");
            return (Criteria) this;
        }

        public Criteria andVoteCanModifyIn(List<Byte> values) {
            addCriterion("vote_can_modify in", values, "voteCanModify");
            return (Criteria) this;
        }

        public Criteria andVoteCanModifyNotIn(List<Byte> values) {
            addCriterion("vote_can_modify not in", values, "voteCanModify");
            return (Criteria) this;
        }

        public Criteria andVoteCanModifyBetween(Byte value1, Byte value2) {
            addCriterion("vote_can_modify between", value1, value2, "voteCanModify");
            return (Criteria) this;
        }

        public Criteria andVoteCanModifyNotBetween(Byte value1, Byte value2) {
            addCriterion("vote_can_modify not between", value1, value2, "voteCanModify");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andDepositIsNull() {
            addCriterion("deposit is null");
            return (Criteria) this;
        }

        public Criteria andDepositIsNotNull() {
            addCriterion("deposit is not null");
            return (Criteria) this;
        }

        public Criteria andDepositEqualTo(BigInteger value) {
            addCriterion("deposit =", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotEqualTo(BigInteger value) {
            addCriterion("deposit <>", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositGreaterThan(BigInteger value) {
            addCriterion("deposit >", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositGreaterThanOrEqualTo(BigInteger value) {
            addCriterion("deposit >=", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositLessThan(BigInteger value) {
            addCriterion("deposit <", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositLessThanOrEqualTo(BigInteger value) {
            addCriterion("deposit <=", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositIn(List<BigInteger> values) {
            addCriterion("deposit in", values, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotIn(List<BigInteger> values) {
            addCriterion("deposit not in", values, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositBetween(BigInteger value1, BigInteger value2) {
            addCriterion("deposit between", value1, value2, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotBetween(BigInteger value1, BigInteger value2) {
            addCriterion("deposit not between", value1, value2, "deposit");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCountsIsNull() {
            addCriterion("counts is null");
            return (Criteria) this;
        }

        public Criteria andCountsIsNotNull() {
            addCriterion("counts is not null");
            return (Criteria) this;
        }

        public Criteria andCountsEqualTo(Integer value) {
            addCriterion("counts =", value, "counts");
            return (Criteria) this;
        }

        public Criteria andCountsNotEqualTo(Integer value) {
            addCriterion("counts <>", value, "counts");
            return (Criteria) this;
        }

        public Criteria andCountsGreaterThan(Integer value) {
            addCriterion("counts >", value, "counts");
            return (Criteria) this;
        }

        public Criteria andCountsGreaterThanOrEqualTo(Integer value) {
            addCriterion("counts >=", value, "counts");
            return (Criteria) this;
        }

        public Criteria andCountsLessThan(Integer value) {
            addCriterion("counts <", value, "counts");
            return (Criteria) this;
        }

        public Criteria andCountsLessThanOrEqualTo(Integer value) {
            addCriterion("counts <=", value, "counts");
            return (Criteria) this;
        }

        public Criteria andCountsIn(List<Integer> values) {
            addCriterion("counts in", values, "counts");
            return (Criteria) this;
        }

        public Criteria andCountsNotIn(List<Integer> values) {
            addCriterion("counts not in", values, "counts");
            return (Criteria) this;
        }

        public Criteria andCountsBetween(Integer value1, Integer value2) {
            addCriterion("counts between", value1, value2, "counts");
            return (Criteria) this;
        }

        public Criteria andCountsNotBetween(Integer value1, Integer value2) {
            addCriterion("counts not between", value1, value2, "counts");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(BigDecimal value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(BigDecimal value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(BigDecimal value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(BigDecimal value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<BigDecimal> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<BigDecimal> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andBlockHeightIsNull() {
            addCriterion("block_height is null");
            return (Criteria) this;
        }

        public Criteria andBlockHeightIsNotNull() {
            addCriterion("block_height is not null");
            return (Criteria) this;
        }

        public Criteria andBlockHeightEqualTo(Long value) {
            addCriterion("block_height =", value, "blockHeight");
            return (Criteria) this;
        }

        public Criteria andBlockHeightNotEqualTo(Long value) {
            addCriterion("block_height <>", value, "blockHeight");
            return (Criteria) this;
        }

        public Criteria andBlockHeightGreaterThan(Long value) {
            addCriterion("block_height >", value, "blockHeight");
            return (Criteria) this;
        }

        public Criteria andBlockHeightGreaterThanOrEqualTo(Long value) {
            addCriterion("block_height >=", value, "blockHeight");
            return (Criteria) this;
        }

        public Criteria andBlockHeightLessThan(Long value) {
            addCriterion("block_height <", value, "blockHeight");
            return (Criteria) this;
        }

        public Criteria andBlockHeightLessThanOrEqualTo(Long value) {
            addCriterion("block_height <=", value, "blockHeight");
            return (Criteria) this;
        }

        public Criteria andBlockHeightIn(List<Long> values) {
            addCriterion("block_height in", values, "blockHeight");
            return (Criteria) this;
        }

        public Criteria andBlockHeightNotIn(List<Long> values) {
            addCriterion("block_height not in", values, "blockHeight");
            return (Criteria) this;
        }

        public Criteria andBlockHeightBetween(Long value1, Long value2) {
            addCriterion("block_height between", value1, value2, "blockHeight");
            return (Criteria) this;
        }

        public Criteria andBlockHeightNotBetween(Long value1, Long value2) {
            addCriterion("block_height not between", value1, value2, "blockHeight");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("creator like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("creator not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Long value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Long value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Long value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Long value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Long value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Long> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Long> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Long value1, Long value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Long value1, Long value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Long value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Long value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Long value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Long value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Long value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Long> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Long> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Long value1, Long value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Long value1, Long value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}