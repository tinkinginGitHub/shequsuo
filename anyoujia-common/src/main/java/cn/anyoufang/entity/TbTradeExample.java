package cn.anyoufang.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbTradeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbTradeExample() {
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

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andVoucheridIsNull() {
            addCriterion("voucherid is null");
            return (Criteria) this;
        }

        public Criteria andVoucheridIsNotNull() {
            addCriterion("voucherid is not null");
            return (Criteria) this;
        }

        public Criteria andVoucheridEqualTo(String value) {
            addCriterion("voucherid =", value, "voucherid");
            return (Criteria) this;
        }

        public Criteria andVoucheridNotEqualTo(String value) {
            addCriterion("voucherid <>", value, "voucherid");
            return (Criteria) this;
        }

        public Criteria andVoucheridGreaterThan(String value) {
            addCriterion("voucherid >", value, "voucherid");
            return (Criteria) this;
        }

        public Criteria andVoucheridGreaterThanOrEqualTo(String value) {
            addCriterion("voucherid >=", value, "voucherid");
            return (Criteria) this;
        }

        public Criteria andVoucheridLessThan(String value) {
            addCriterion("voucherid <", value, "voucherid");
            return (Criteria) this;
        }

        public Criteria andVoucheridLessThanOrEqualTo(String value) {
            addCriterion("voucherid <=", value, "voucherid");
            return (Criteria) this;
        }

        public Criteria andVoucheridLike(String value) {
            addCriterion("voucherid like", value, "voucherid");
            return (Criteria) this;
        }

        public Criteria andVoucheridNotLike(String value) {
            addCriterion("voucherid not like", value, "voucherid");
            return (Criteria) this;
        }

        public Criteria andVoucheridIn(List<String> values) {
            addCriterion("voucherid in", values, "voucherid");
            return (Criteria) this;
        }

        public Criteria andVoucheridNotIn(List<String> values) {
            addCriterion("voucherid not in", values, "voucherid");
            return (Criteria) this;
        }

        public Criteria andVoucheridBetween(String value1, String value2) {
            addCriterion("voucherid between", value1, value2, "voucherid");
            return (Criteria) this;
        }

        public Criteria andVoucheridNotBetween(String value1, String value2) {
            addCriterion("voucherid not between", value1, value2, "voucherid");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updatetime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updatetime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updatetime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updatetime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updatetime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updatetime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updatetime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updatetime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updatetime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updatetime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updatetime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updatetime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andCompletetimeIsNull() {
            addCriterion("completetime is null");
            return (Criteria) this;
        }

        public Criteria andCompletetimeIsNotNull() {
            addCriterion("completetime is not null");
            return (Criteria) this;
        }

        public Criteria andCompletetimeEqualTo(Date value) {
            addCriterion("completetime =", value, "completetime");
            return (Criteria) this;
        }

        public Criteria andCompletetimeNotEqualTo(Date value) {
            addCriterion("completetime <>", value, "completetime");
            return (Criteria) this;
        }

        public Criteria andCompletetimeGreaterThan(Date value) {
            addCriterion("completetime >", value, "completetime");
            return (Criteria) this;
        }

        public Criteria andCompletetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("completetime >=", value, "completetime");
            return (Criteria) this;
        }

        public Criteria andCompletetimeLessThan(Date value) {
            addCriterion("completetime <", value, "completetime");
            return (Criteria) this;
        }

        public Criteria andCompletetimeLessThanOrEqualTo(Date value) {
            addCriterion("completetime <=", value, "completetime");
            return (Criteria) this;
        }

        public Criteria andCompletetimeIn(List<Date> values) {
            addCriterion("completetime in", values, "completetime");
            return (Criteria) this;
        }

        public Criteria andCompletetimeNotIn(List<Date> values) {
            addCriterion("completetime not in", values, "completetime");
            return (Criteria) this;
        }

        public Criteria andCompletetimeBetween(Date value1, Date value2) {
            addCriterion("completetime between", value1, value2, "completetime");
            return (Criteria) this;
        }

        public Criteria andCompletetimeNotBetween(Date value1, Date value2) {
            addCriterion("completetime not between", value1, value2, "completetime");
            return (Criteria) this;
        }

        public Criteria andPayamountIsNull() {
            addCriterion("payamount is null");
            return (Criteria) this;
        }

        public Criteria andPayamountIsNotNull() {
            addCriterion("payamount is not null");
            return (Criteria) this;
        }

        public Criteria andPayamountEqualTo(String value) {
            addCriterion("payamount =", value, "payamount");
            return (Criteria) this;
        }

        public Criteria andPayamountNotEqualTo(String value) {
            addCriterion("payamount <>", value, "payamount");
            return (Criteria) this;
        }

        public Criteria andPayamountGreaterThan(String value) {
            addCriterion("payamount >", value, "payamount");
            return (Criteria) this;
        }

        public Criteria andPayamountGreaterThanOrEqualTo(String value) {
            addCriterion("payamount >=", value, "payamount");
            return (Criteria) this;
        }

        public Criteria andPayamountLessThan(String value) {
            addCriterion("payamount <", value, "payamount");
            return (Criteria) this;
        }

        public Criteria andPayamountLessThanOrEqualTo(String value) {
            addCriterion("payamount <=", value, "payamount");
            return (Criteria) this;
        }

        public Criteria andPayamountLike(String value) {
            addCriterion("payamount like", value, "payamount");
            return (Criteria) this;
        }

        public Criteria andPayamountNotLike(String value) {
            addCriterion("payamount not like", value, "payamount");
            return (Criteria) this;
        }

        public Criteria andPayamountIn(List<String> values) {
            addCriterion("payamount in", values, "payamount");
            return (Criteria) this;
        }

        public Criteria andPayamountNotIn(List<String> values) {
            addCriterion("payamount not in", values, "payamount");
            return (Criteria) this;
        }

        public Criteria andPayamountBetween(String value1, String value2) {
            addCriterion("payamount between", value1, value2, "payamount");
            return (Criteria) this;
        }

        public Criteria andPayamountNotBetween(String value1, String value2) {
            addCriterion("payamount not between", value1, value2, "payamount");
            return (Criteria) this;
        }

        public Criteria andTradestatusIsNull() {
            addCriterion("tradestatus is null");
            return (Criteria) this;
        }

        public Criteria andTradestatusIsNotNull() {
            addCriterion("tradestatus is not null");
            return (Criteria) this;
        }

        public Criteria andTradestatusEqualTo(String value) {
            addCriterion("tradestatus =", value, "tradestatus");
            return (Criteria) this;
        }

        public Criteria andTradestatusNotEqualTo(String value) {
            addCriterion("tradestatus <>", value, "tradestatus");
            return (Criteria) this;
        }

        public Criteria andTradestatusGreaterThan(String value) {
            addCriterion("tradestatus >", value, "tradestatus");
            return (Criteria) this;
        }

        public Criteria andTradestatusGreaterThanOrEqualTo(String value) {
            addCriterion("tradestatus >=", value, "tradestatus");
            return (Criteria) this;
        }

        public Criteria andTradestatusLessThan(String value) {
            addCriterion("tradestatus <", value, "tradestatus");
            return (Criteria) this;
        }

        public Criteria andTradestatusLessThanOrEqualTo(String value) {
            addCriterion("tradestatus <=", value, "tradestatus");
            return (Criteria) this;
        }

        public Criteria andTradestatusLike(String value) {
            addCriterion("tradestatus like", value, "tradestatus");
            return (Criteria) this;
        }

        public Criteria andTradestatusNotLike(String value) {
            addCriterion("tradestatus not like", value, "tradestatus");
            return (Criteria) this;
        }

        public Criteria andTradestatusIn(List<String> values) {
            addCriterion("tradestatus in", values, "tradestatus");
            return (Criteria) this;
        }

        public Criteria andTradestatusNotIn(List<String> values) {
            addCriterion("tradestatus not in", values, "tradestatus");
            return (Criteria) this;
        }

        public Criteria andTradestatusBetween(String value1, String value2) {
            addCriterion("tradestatus between", value1, value2, "tradestatus");
            return (Criteria) this;
        }

        public Criteria andTradestatusNotBetween(String value1, String value2) {
            addCriterion("tradestatus not between", value1, value2, "tradestatus");
            return (Criteria) this;
        }

        public Criteria andPaytypeIsNull() {
            addCriterion("paytype is null");
            return (Criteria) this;
        }

        public Criteria andPaytypeIsNotNull() {
            addCriterion("paytype is not null");
            return (Criteria) this;
        }

        public Criteria andPaytypeEqualTo(String value) {
            addCriterion("paytype =", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeNotEqualTo(String value) {
            addCriterion("paytype <>", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeGreaterThan(String value) {
            addCriterion("paytype >", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeGreaterThanOrEqualTo(String value) {
            addCriterion("paytype >=", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeLessThan(String value) {
            addCriterion("paytype <", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeLessThanOrEqualTo(String value) {
            addCriterion("paytype <=", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeLike(String value) {
            addCriterion("paytype like", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeNotLike(String value) {
            addCriterion("paytype not like", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeIn(List<String> values) {
            addCriterion("paytype in", values, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeNotIn(List<String> values) {
            addCriterion("paytype not in", values, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeBetween(String value1, String value2) {
            addCriterion("paytype between", value1, value2, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeNotBetween(String value1, String value2) {
            addCriterion("paytype not between", value1, value2, "paytype");
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