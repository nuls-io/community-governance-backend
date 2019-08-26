package io.nuls.dapp.communitygovernance.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TbProposalVoteRecordParam {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbProposalVoteRecordParam() {
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

        public Criteria andProposalIdIsNull() {
            addCriterion("proposal_id is null");
            return (Criteria) this;
        }

        public Criteria andProposalIdIsNotNull() {
            addCriterion("proposal_id is not null");
            return (Criteria) this;
        }

        public Criteria andProposalIdEqualTo(Integer value) {
            addCriterion("proposal_id =", value, "proposalId");
            return (Criteria) this;
        }

        public Criteria andProposalIdNotEqualTo(Integer value) {
            addCriterion("proposal_id <>", value, "proposalId");
            return (Criteria) this;
        }

        public Criteria andProposalIdGreaterThan(Integer value) {
            addCriterion("proposal_id >", value, "proposalId");
            return (Criteria) this;
        }

        public Criteria andProposalIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("proposal_id >=", value, "proposalId");
            return (Criteria) this;
        }

        public Criteria andProposalIdLessThan(Integer value) {
            addCriterion("proposal_id <", value, "proposalId");
            return (Criteria) this;
        }

        public Criteria andProposalIdLessThanOrEqualTo(Integer value) {
            addCriterion("proposal_id <=", value, "proposalId");
            return (Criteria) this;
        }

        public Criteria andProposalIdIn(List<Integer> values) {
            addCriterion("proposal_id in", values, "proposalId");
            return (Criteria) this;
        }

        public Criteria andProposalIdNotIn(List<Integer> values) {
            addCriterion("proposal_id not in", values, "proposalId");
            return (Criteria) this;
        }

        public Criteria andProposalIdBetween(Integer value1, Integer value2) {
            addCriterion("proposal_id between", value1, value2, "proposalId");
            return (Criteria) this;
        }

        public Criteria andProposalIdNotBetween(Integer value1, Integer value2) {
            addCriterion("proposal_id not between", value1, value2, "proposalId");
            return (Criteria) this;
        }

        public Criteria andVoterIsNull() {
            addCriterion("voter is null");
            return (Criteria) this;
        }

        public Criteria andVoterIsNotNull() {
            addCriterion("voter is not null");
            return (Criteria) this;
        }

        public Criteria andVoterEqualTo(String value) {
            addCriterion("voter =", value, "voter");
            return (Criteria) this;
        }

        public Criteria andVoterNotEqualTo(String value) {
            addCriterion("voter <>", value, "voter");
            return (Criteria) this;
        }

        public Criteria andVoterGreaterThan(String value) {
            addCriterion("voter >", value, "voter");
            return (Criteria) this;
        }

        public Criteria andVoterGreaterThanOrEqualTo(String value) {
            addCriterion("voter >=", value, "voter");
            return (Criteria) this;
        }

        public Criteria andVoterLessThan(String value) {
            addCriterion("voter <", value, "voter");
            return (Criteria) this;
        }

        public Criteria andVoterLessThanOrEqualTo(String value) {
            addCriterion("voter <=", value, "voter");
            return (Criteria) this;
        }

        public Criteria andVoterLike(String value) {
            addCriterion("voter like", value, "voter");
            return (Criteria) this;
        }

        public Criteria andVoterNotLike(String value) {
            addCriterion("voter not like", value, "voter");
            return (Criteria) this;
        }

        public Criteria andVoterIn(List<String> values) {
            addCriterion("voter in", values, "voter");
            return (Criteria) this;
        }

        public Criteria andVoterNotIn(List<String> values) {
            addCriterion("voter not in", values, "voter");
            return (Criteria) this;
        }

        public Criteria andVoterBetween(String value1, String value2) {
            addCriterion("voter between", value1, value2, "voter");
            return (Criteria) this;
        }

        public Criteria andVoterNotBetween(String value1, String value2) {
            addCriterion("voter not between", value1, value2, "voter");
            return (Criteria) this;
        }

        public Criteria andResultIsNull() {
            addCriterion("result is null");
            return (Criteria) this;
        }

        public Criteria andResultIsNotNull() {
            addCriterion("result is not null");
            return (Criteria) this;
        }

        public Criteria andResultEqualTo(Byte value) {
            addCriterion("result =", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotEqualTo(Byte value) {
            addCriterion("result <>", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThan(Byte value) {
            addCriterion("result >", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThanOrEqualTo(Byte value) {
            addCriterion("result >=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThan(Byte value) {
            addCriterion("result <", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThanOrEqualTo(Byte value) {
            addCriterion("result <=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultIn(List<Byte> values) {
            addCriterion("result in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotIn(List<Byte> values) {
            addCriterion("result not in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultBetween(Byte value1, Byte value2) {
            addCriterion("result between", value1, value2, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotBetween(Byte value1, Byte value2) {
            addCriterion("result not between", value1, value2, "result");
            return (Criteria) this;
        }

        public Criteria andVotesIsNull() {
            addCriterion("votes is null");
            return (Criteria) this;
        }

        public Criteria andVotesIsNotNull() {
            addCriterion("votes is not null");
            return (Criteria) this;
        }

        public Criteria andVotesEqualTo(BigDecimal value) {
            addCriterion("votes =", value, "votes");
            return (Criteria) this;
        }

        public Criteria andVotesNotEqualTo(BigDecimal value) {
            addCriterion("votes <>", value, "votes");
            return (Criteria) this;
        }

        public Criteria andVotesGreaterThan(BigDecimal value) {
            addCriterion("votes >", value, "votes");
            return (Criteria) this;
        }

        public Criteria andVotesGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("votes >=", value, "votes");
            return (Criteria) this;
        }

        public Criteria andVotesLessThan(BigDecimal value) {
            addCriterion("votes <", value, "votes");
            return (Criteria) this;
        }

        public Criteria andVotesLessThanOrEqualTo(BigDecimal value) {
            addCriterion("votes <=", value, "votes");
            return (Criteria) this;
        }

        public Criteria andVotesIn(List<BigDecimal> values) {
            addCriterion("votes in", values, "votes");
            return (Criteria) this;
        }

        public Criteria andVotesNotIn(List<BigDecimal> values) {
            addCriterion("votes not in", values, "votes");
            return (Criteria) this;
        }

        public Criteria andVotesBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("votes between", value1, value2, "votes");
            return (Criteria) this;
        }

        public Criteria andVotesNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("votes not between", value1, value2, "votes");
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