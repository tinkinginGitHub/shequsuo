package cn.anyoufang.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbCollectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbCollectExample() {
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

        public Criteria andCollectIdIsNull() {
            addCriterion("collect_id is null");
            return (Criteria) this;
        }

        public Criteria andCollectIdIsNotNull() {
            addCriterion("collect_id is not null");
            return (Criteria) this;
        }

        public Criteria andCollectIdEqualTo(String value) {
            addCriterion("collect_id =", value, "collectId");
            return (Criteria) this;
        }

        public Criteria andCollectIdNotEqualTo(String value) {
            addCriterion("collect_id <>", value, "collectId");
            return (Criteria) this;
        }

        public Criteria andCollectIdGreaterThan(String value) {
            addCriterion("collect_id >", value, "collectId");
            return (Criteria) this;
        }

        public Criteria andCollectIdGreaterThanOrEqualTo(String value) {
            addCriterion("collect_id >=", value, "collectId");
            return (Criteria) this;
        }

        public Criteria andCollectIdLessThan(String value) {
            addCriterion("collect_id <", value, "collectId");
            return (Criteria) this;
        }

        public Criteria andCollectIdLessThanOrEqualTo(String value) {
            addCriterion("collect_id <=", value, "collectId");
            return (Criteria) this;
        }

        public Criteria andCollectIdLike(String value) {
            addCriterion("collect_id like", value, "collectId");
            return (Criteria) this;
        }

        public Criteria andCollectIdNotLike(String value) {
            addCriterion("collect_id not like", value, "collectId");
            return (Criteria) this;
        }

        public Criteria andCollectIdIn(List<String> values) {
            addCriterion("collect_id in", values, "collectId");
            return (Criteria) this;
        }

        public Criteria andCollectIdNotIn(List<String> values) {
            addCriterion("collect_id not in", values, "collectId");
            return (Criteria) this;
        }

        public Criteria andCollectIdBetween(String value1, String value2) {
            addCriterion("collect_id between", value1, value2, "collectId");
            return (Criteria) this;
        }

        public Criteria andCollectIdNotBetween(String value1, String value2) {
            addCriterion("collect_id not between", value1, value2, "collectId");
            return (Criteria) this;
        }

        public Criteria andHousingIdIsNull() {
            addCriterion("housing_id is null");
            return (Criteria) this;
        }

        public Criteria andHousingIdIsNotNull() {
            addCriterion("housing_id is not null");
            return (Criteria) this;
        }

        public Criteria andHousingIdEqualTo(String value) {
            addCriterion("housing_id =", value, "housingId");
            return (Criteria) this;
        }

        public Criteria andHousingIdNotEqualTo(String value) {
            addCriterion("housing_id <>", value, "housingId");
            return (Criteria) this;
        }

        public Criteria andHousingIdGreaterThan(String value) {
            addCriterion("housing_id >", value, "housingId");
            return (Criteria) this;
        }

        public Criteria andHousingIdGreaterThanOrEqualTo(String value) {
            addCriterion("housing_id >=", value, "housingId");
            return (Criteria) this;
        }

        public Criteria andHousingIdLessThan(String value) {
            addCriterion("housing_id <", value, "housingId");
            return (Criteria) this;
        }

        public Criteria andHousingIdLessThanOrEqualTo(String value) {
            addCriterion("housing_id <=", value, "housingId");
            return (Criteria) this;
        }

        public Criteria andHousingIdLike(String value) {
            addCriterion("housing_id like", value, "housingId");
            return (Criteria) this;
        }

        public Criteria andHousingIdNotLike(String value) {
            addCriterion("housing_id not like", value, "housingId");
            return (Criteria) this;
        }

        public Criteria andHousingIdIn(List<String> values) {
            addCriterion("housing_id in", values, "housingId");
            return (Criteria) this;
        }

        public Criteria andHousingIdNotIn(List<String> values) {
            addCriterion("housing_id not in", values, "housingId");
            return (Criteria) this;
        }

        public Criteria andHousingIdBetween(String value1, String value2) {
            addCriterion("housing_id between", value1, value2, "housingId");
            return (Criteria) this;
        }

        public Criteria andHousingIdNotBetween(String value1, String value2) {
            addCriterion("housing_id not between", value1, value2, "housingId");
            return (Criteria) this;
        }

        public Criteria andRenterIdIsNull() {
            addCriterion("renter_id is null");
            return (Criteria) this;
        }

        public Criteria andRenterIdIsNotNull() {
            addCriterion("renter_id is not null");
            return (Criteria) this;
        }

        public Criteria andRenterIdEqualTo(String value) {
            addCriterion("renter_id =", value, "renterId");
            return (Criteria) this;
        }

        public Criteria andRenterIdNotEqualTo(String value) {
            addCriterion("renter_id <>", value, "renterId");
            return (Criteria) this;
        }

        public Criteria andRenterIdGreaterThan(String value) {
            addCriterion("renter_id >", value, "renterId");
            return (Criteria) this;
        }

        public Criteria andRenterIdGreaterThanOrEqualTo(String value) {
            addCriterion("renter_id >=", value, "renterId");
            return (Criteria) this;
        }

        public Criteria andRenterIdLessThan(String value) {
            addCriterion("renter_id <", value, "renterId");
            return (Criteria) this;
        }

        public Criteria andRenterIdLessThanOrEqualTo(String value) {
            addCriterion("renter_id <=", value, "renterId");
            return (Criteria) this;
        }

        public Criteria andRenterIdLike(String value) {
            addCriterion("renter_id like", value, "renterId");
            return (Criteria) this;
        }

        public Criteria andRenterIdNotLike(String value) {
            addCriterion("renter_id not like", value, "renterId");
            return (Criteria) this;
        }

        public Criteria andRenterIdIn(List<String> values) {
            addCriterion("renter_id in", values, "renterId");
            return (Criteria) this;
        }

        public Criteria andRenterIdNotIn(List<String> values) {
            addCriterion("renter_id not in", values, "renterId");
            return (Criteria) this;
        }

        public Criteria andRenterIdBetween(String value1, String value2) {
            addCriterion("renter_id between", value1, value2, "renterId");
            return (Criteria) this;
        }

        public Criteria andRenterIdNotBetween(String value1, String value2) {
            addCriterion("renter_id not between", value1, value2, "renterId");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNull() {
            addCriterion("created is null");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNotNull() {
            addCriterion("created is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedEqualTo(Date value) {
            addCriterion("created =", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotEqualTo(Date value) {
            addCriterion("created <>", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThan(Date value) {
            addCriterion("created >", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("created >=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThan(Date value) {
            addCriterion("created <", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThanOrEqualTo(Date value) {
            addCriterion("created <=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedIn(List<Date> values) {
            addCriterion("created in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotIn(List<Date> values) {
            addCriterion("created not in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedBetween(Date value1, Date value2) {
            addCriterion("created between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotBetween(Date value1, Date value2) {
            addCriterion("created not between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andFootmarkIsNull() {
            addCriterion("footmark is null");
            return (Criteria) this;
        }

        public Criteria andFootmarkIsNotNull() {
            addCriterion("footmark is not null");
            return (Criteria) this;
        }

        public Criteria andFootmarkEqualTo(Byte value) {
            addCriterion("footmark =", value, "footmark");
            return (Criteria) this;
        }

        public Criteria andFootmarkNotEqualTo(Byte value) {
            addCriterion("footmark <>", value, "footmark");
            return (Criteria) this;
        }

        public Criteria andFootmarkGreaterThan(Byte value) {
            addCriterion("footmark >", value, "footmark");
            return (Criteria) this;
        }

        public Criteria andFootmarkGreaterThanOrEqualTo(Byte value) {
            addCriterion("footmark >=", value, "footmark");
            return (Criteria) this;
        }

        public Criteria andFootmarkLessThan(Byte value) {
            addCriterion("footmark <", value, "footmark");
            return (Criteria) this;
        }

        public Criteria andFootmarkLessThanOrEqualTo(Byte value) {
            addCriterion("footmark <=", value, "footmark");
            return (Criteria) this;
        }

        public Criteria andFootmarkIn(List<Byte> values) {
            addCriterion("footmark in", values, "footmark");
            return (Criteria) this;
        }

        public Criteria andFootmarkNotIn(List<Byte> values) {
            addCriterion("footmark not in", values, "footmark");
            return (Criteria) this;
        }

        public Criteria andFootmarkBetween(Byte value1, Byte value2) {
            addCriterion("footmark between", value1, value2, "footmark");
            return (Criteria) this;
        }

        public Criteria andFootmarkNotBetween(Byte value1, Byte value2) {
            addCriterion("footmark not between", value1, value2, "footmark");
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