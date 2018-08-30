package cn.anyoufang.entity;

import java.util.ArrayList;
import java.util.List;

public class TbPictureExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbPictureExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andUrl1IsNull() {
            addCriterion("url1 is null");
            return (Criteria) this;
        }

        public Criteria andUrl1IsNotNull() {
            addCriterion("url1 is not null");
            return (Criteria) this;
        }

        public Criteria andUrl1EqualTo(String value) {
            addCriterion("url1 =", value, "url1");
            return (Criteria) this;
        }

        public Criteria andUrl1NotEqualTo(String value) {
            addCriterion("url1 <>", value, "url1");
            return (Criteria) this;
        }

        public Criteria andUrl1GreaterThan(String value) {
            addCriterion("url1 >", value, "url1");
            return (Criteria) this;
        }

        public Criteria andUrl1GreaterThanOrEqualTo(String value) {
            addCriterion("url1 >=", value, "url1");
            return (Criteria) this;
        }

        public Criteria andUrl1LessThan(String value) {
            addCriterion("url1 <", value, "url1");
            return (Criteria) this;
        }

        public Criteria andUrl1LessThanOrEqualTo(String value) {
            addCriterion("url1 <=", value, "url1");
            return (Criteria) this;
        }

        public Criteria andUrl1Like(String value) {
            addCriterion("url1 like", value, "url1");
            return (Criteria) this;
        }

        public Criteria andUrl1NotLike(String value) {
            addCriterion("url1 not like", value, "url1");
            return (Criteria) this;
        }

        public Criteria andUrl1In(List<String> values) {
            addCriterion("url1 in", values, "url1");
            return (Criteria) this;
        }

        public Criteria andUrl1NotIn(List<String> values) {
            addCriterion("url1 not in", values, "url1");
            return (Criteria) this;
        }

        public Criteria andUrl1Between(String value1, String value2) {
            addCriterion("url1 between", value1, value2, "url1");
            return (Criteria) this;
        }

        public Criteria andUrl1NotBetween(String value1, String value2) {
            addCriterion("url1 not between", value1, value2, "url1");
            return (Criteria) this;
        }

        public Criteria andUrl2IsNull() {
            addCriterion("url2 is null");
            return (Criteria) this;
        }

        public Criteria andUrl2IsNotNull() {
            addCriterion("url2 is not null");
            return (Criteria) this;
        }

        public Criteria andUrl2EqualTo(String value) {
            addCriterion("url2 =", value, "url2");
            return (Criteria) this;
        }

        public Criteria andUrl2NotEqualTo(String value) {
            addCriterion("url2 <>", value, "url2");
            return (Criteria) this;
        }

        public Criteria andUrl2GreaterThan(String value) {
            addCriterion("url2 >", value, "url2");
            return (Criteria) this;
        }

        public Criteria andUrl2GreaterThanOrEqualTo(String value) {
            addCriterion("url2 >=", value, "url2");
            return (Criteria) this;
        }

        public Criteria andUrl2LessThan(String value) {
            addCriterion("url2 <", value, "url2");
            return (Criteria) this;
        }

        public Criteria andUrl2LessThanOrEqualTo(String value) {
            addCriterion("url2 <=", value, "url2");
            return (Criteria) this;
        }

        public Criteria andUrl2Like(String value) {
            addCriterion("url2 like", value, "url2");
            return (Criteria) this;
        }

        public Criteria andUrl2NotLike(String value) {
            addCriterion("url2 not like", value, "url2");
            return (Criteria) this;
        }

        public Criteria andUrl2In(List<String> values) {
            addCriterion("url2 in", values, "url2");
            return (Criteria) this;
        }

        public Criteria andUrl2NotIn(List<String> values) {
            addCriterion("url2 not in", values, "url2");
            return (Criteria) this;
        }

        public Criteria andUrl2Between(String value1, String value2) {
            addCriterion("url2 between", value1, value2, "url2");
            return (Criteria) this;
        }

        public Criteria andUrl2NotBetween(String value1, String value2) {
            addCriterion("url2 not between", value1, value2, "url2");
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