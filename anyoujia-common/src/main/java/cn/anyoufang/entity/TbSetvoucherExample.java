package cn.anyoufang.entity;

import java.util.ArrayList;
import java.util.List;

public class TbSetvoucherExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbSetvoucherExample() {
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

        public Criteria andIntroduceIsNull() {
            addCriterion("introduce is null");
            return (Criteria) this;
        }

        public Criteria andIntroduceIsNotNull() {
            addCriterion("introduce is not null");
            return (Criteria) this;
        }

        public Criteria andIntroduceEqualTo(String value) {
            addCriterion("introduce =", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotEqualTo(String value) {
            addCriterion("introduce <>", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceGreaterThan(String value) {
            addCriterion("introduce >", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceGreaterThanOrEqualTo(String value) {
            addCriterion("introduce >=", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLessThan(String value) {
            addCriterion("introduce <", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLessThanOrEqualTo(String value) {
            addCriterion("introduce <=", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLike(String value) {
            addCriterion("introduce like", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotLike(String value) {
            addCriterion("introduce not like", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceIn(List<String> values) {
            addCriterion("introduce in", values, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotIn(List<String> values) {
            addCriterion("introduce not in", values, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceBetween(String value1, String value2) {
            addCriterion("introduce between", value1, value2, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotBetween(String value1, String value2) {
            addCriterion("introduce not between", value1, value2, "introduce");
            return (Criteria) this;
        }

        public Criteria andValidIsNull() {
            addCriterion("valid is null");
            return (Criteria) this;
        }

        public Criteria andValidIsNotNull() {
            addCriterion("valid is not null");
            return (Criteria) this;
        }

        public Criteria andValidEqualTo(Integer value) {
            addCriterion("valid =", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotEqualTo(Integer value) {
            addCriterion("valid <>", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidGreaterThan(Integer value) {
            addCriterion("valid >", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidGreaterThanOrEqualTo(Integer value) {
            addCriterion("valid >=", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidLessThan(Integer value) {
            addCriterion("valid <", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidLessThanOrEqualTo(Integer value) {
            addCriterion("valid <=", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidIn(List<Integer> values) {
            addCriterion("valid in", values, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotIn(List<Integer> values) {
            addCriterion("valid not in", values, "valid");
            return (Criteria) this;
        }

        public Criteria andValidBetween(Integer value1, Integer value2) {
            addCriterion("valid between", value1, value2, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotBetween(Integer value1, Integer value2) {
            addCriterion("valid not between", value1, value2, "valid");
            return (Criteria) this;
        }

        public Criteria andDeductibleMoneyIsNull() {
            addCriterion("deductible_money is null");
            return (Criteria) this;
        }

        public Criteria andDeductibleMoneyIsNotNull() {
            addCriterion("deductible_money is not null");
            return (Criteria) this;
        }

        public Criteria andDeductibleMoneyEqualTo(Integer value) {
            addCriterion("deductible_money =", value, "deductibleMoney");
            return (Criteria) this;
        }

        public Criteria andDeductibleMoneyNotEqualTo(Integer value) {
            addCriterion("deductible_money <>", value, "deductibleMoney");
            return (Criteria) this;
        }

        public Criteria andDeductibleMoneyGreaterThan(Integer value) {
            addCriterion("deductible_money >", value, "deductibleMoney");
            return (Criteria) this;
        }

        public Criteria andDeductibleMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("deductible_money >=", value, "deductibleMoney");
            return (Criteria) this;
        }

        public Criteria andDeductibleMoneyLessThan(Integer value) {
            addCriterion("deductible_money <", value, "deductibleMoney");
            return (Criteria) this;
        }

        public Criteria andDeductibleMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("deductible_money <=", value, "deductibleMoney");
            return (Criteria) this;
        }

        public Criteria andDeductibleMoneyIn(List<Integer> values) {
            addCriterion("deductible_money in", values, "deductibleMoney");
            return (Criteria) this;
        }

        public Criteria andDeductibleMoneyNotIn(List<Integer> values) {
            addCriterion("deductible_money not in", values, "deductibleMoney");
            return (Criteria) this;
        }

        public Criteria andDeductibleMoneyBetween(Integer value1, Integer value2) {
            addCriterion("deductible_money between", value1, value2, "deductibleMoney");
            return (Criteria) this;
        }

        public Criteria andDeductibleMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("deductible_money not between", value1, value2, "deductibleMoney");
            return (Criteria) this;
        }

        public Criteria andDeductibleDaysIsNull() {
            addCriterion("deductible_days is null");
            return (Criteria) this;
        }

        public Criteria andDeductibleDaysIsNotNull() {
            addCriterion("deductible_days is not null");
            return (Criteria) this;
        }

        public Criteria andDeductibleDaysEqualTo(Integer value) {
            addCriterion("deductible_days =", value, "deductibleDays");
            return (Criteria) this;
        }

        public Criteria andDeductibleDaysNotEqualTo(Integer value) {
            addCriterion("deductible_days <>", value, "deductibleDays");
            return (Criteria) this;
        }

        public Criteria andDeductibleDaysGreaterThan(Integer value) {
            addCriterion("deductible_days >", value, "deductibleDays");
            return (Criteria) this;
        }

        public Criteria andDeductibleDaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("deductible_days >=", value, "deductibleDays");
            return (Criteria) this;
        }

        public Criteria andDeductibleDaysLessThan(Integer value) {
            addCriterion("deductible_days <", value, "deductibleDays");
            return (Criteria) this;
        }

        public Criteria andDeductibleDaysLessThanOrEqualTo(Integer value) {
            addCriterion("deductible_days <=", value, "deductibleDays");
            return (Criteria) this;
        }

        public Criteria andDeductibleDaysIn(List<Integer> values) {
            addCriterion("deductible_days in", values, "deductibleDays");
            return (Criteria) this;
        }

        public Criteria andDeductibleDaysNotIn(List<Integer> values) {
            addCriterion("deductible_days not in", values, "deductibleDays");
            return (Criteria) this;
        }

        public Criteria andDeductibleDaysBetween(Integer value1, Integer value2) {
            addCriterion("deductible_days between", value1, value2, "deductibleDays");
            return (Criteria) this;
        }

        public Criteria andDeductibleDaysNotBetween(Integer value1, Integer value2) {
            addCriterion("deductible_days not between", value1, value2, "deductibleDays");
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

        public Criteria andAmountEqualTo(Integer value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(Integer value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(Integer value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(Integer value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(Integer value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<Integer> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<Integer> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(Integer value1, Integer value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andVoucherNameIsNull() {
            addCriterion("voucher_name is null");
            return (Criteria) this;
        }

        public Criteria andVoucherNameIsNotNull() {
            addCriterion("voucher_name is not null");
            return (Criteria) this;
        }

        public Criteria andVoucherNameEqualTo(String value) {
            addCriterion("voucher_name =", value, "voucherName");
            return (Criteria) this;
        }

        public Criteria andVoucherNameNotEqualTo(String value) {
            addCriterion("voucher_name <>", value, "voucherName");
            return (Criteria) this;
        }

        public Criteria andVoucherNameGreaterThan(String value) {
            addCriterion("voucher_name >", value, "voucherName");
            return (Criteria) this;
        }

        public Criteria andVoucherNameGreaterThanOrEqualTo(String value) {
            addCriterion("voucher_name >=", value, "voucherName");
            return (Criteria) this;
        }

        public Criteria andVoucherNameLessThan(String value) {
            addCriterion("voucher_name <", value, "voucherName");
            return (Criteria) this;
        }

        public Criteria andVoucherNameLessThanOrEqualTo(String value) {
            addCriterion("voucher_name <=", value, "voucherName");
            return (Criteria) this;
        }

        public Criteria andVoucherNameLike(String value) {
            addCriterion("voucher_name like", value, "voucherName");
            return (Criteria) this;
        }

        public Criteria andVoucherNameNotLike(String value) {
            addCriterion("voucher_name not like", value, "voucherName");
            return (Criteria) this;
        }

        public Criteria andVoucherNameIn(List<String> values) {
            addCriterion("voucher_name in", values, "voucherName");
            return (Criteria) this;
        }

        public Criteria andVoucherNameNotIn(List<String> values) {
            addCriterion("voucher_name not in", values, "voucherName");
            return (Criteria) this;
        }

        public Criteria andVoucherNameBetween(String value1, String value2) {
            addCriterion("voucher_name between", value1, value2, "voucherName");
            return (Criteria) this;
        }

        public Criteria andVoucherNameNotBetween(String value1, String value2) {
            addCriterion("voucher_name not between", value1, value2, "voucherName");
            return (Criteria) this;
        }

        public Criteria andPicUrlIsNull() {
            addCriterion("pic_url is null");
            return (Criteria) this;
        }

        public Criteria andPicUrlIsNotNull() {
            addCriterion("pic_url is not null");
            return (Criteria) this;
        }

        public Criteria andPicUrlEqualTo(String value) {
            addCriterion("pic_url =", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlNotEqualTo(String value) {
            addCriterion("pic_url <>", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlGreaterThan(String value) {
            addCriterion("pic_url >", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlGreaterThanOrEqualTo(String value) {
            addCriterion("pic_url >=", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlLessThan(String value) {
            addCriterion("pic_url <", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlLessThanOrEqualTo(String value) {
            addCriterion("pic_url <=", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlLike(String value) {
            addCriterion("pic_url like", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlNotLike(String value) {
            addCriterion("pic_url not like", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlIn(List<String> values) {
            addCriterion("pic_url in", values, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlNotIn(List<String> values) {
            addCriterion("pic_url not in", values, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlBetween(String value1, String value2) {
            addCriterion("pic_url between", value1, value2, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlNotBetween(String value1, String value2) {
            addCriterion("pic_url not between", value1, value2, "picUrl");
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