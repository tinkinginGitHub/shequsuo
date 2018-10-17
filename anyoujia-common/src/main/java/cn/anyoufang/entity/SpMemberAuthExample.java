package cn.anyoufang.entity;

import java.util.ArrayList;
import java.util.List;

public class SpMemberAuthExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SpMemberAuthExample() {
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

        public Criteria andRelationidIsNull() {
            addCriterion("relationid is null");
            return (Criteria) this;
        }

        public Criteria andRelationidIsNotNull() {
            addCriterion("relationid is not null");
            return (Criteria) this;
        }

        public Criteria andRelationidEqualTo(Integer value) {
            addCriterion("relationid =", value, "relationid");
            return (Criteria) this;
        }

        public Criteria andRelationidNotEqualTo(Integer value) {
            addCriterion("relationid <>", value, "relationid");
            return (Criteria) this;
        }

        public Criteria andRelationidGreaterThan(Integer value) {
            addCriterion("relationid >", value, "relationid");
            return (Criteria) this;
        }

        public Criteria andRelationidGreaterThanOrEqualTo(Integer value) {
            addCriterion("relationid >=", value, "relationid");
            return (Criteria) this;
        }

        public Criteria andRelationidLessThan(Integer value) {
            addCriterion("relationid <", value, "relationid");
            return (Criteria) this;
        }

        public Criteria andRelationidLessThanOrEqualTo(Integer value) {
            addCriterion("relationid <=", value, "relationid");
            return (Criteria) this;
        }

        public Criteria andRelationidIn(List<Integer> values) {
            addCriterion("relationid in", values, "relationid");
            return (Criteria) this;
        }

        public Criteria andRelationidNotIn(List<Integer> values) {
            addCriterion("relationid not in", values, "relationid");
            return (Criteria) this;
        }

        public Criteria andRelationidBetween(Integer value1, Integer value2) {
            addCriterion("relationid between", value1, value2, "relationid");
            return (Criteria) this;
        }

        public Criteria andRelationidNotBetween(Integer value1, Integer value2) {
            addCriterion("relationid not between", value1, value2, "relationid");
            return (Criteria) this;
        }

        public Criteria andLocksnIsNull() {
            addCriterion("locksn is null");
            return (Criteria) this;
        }

        public Criteria andLocksnIsNotNull() {
            addCriterion("locksn is not null");
            return (Criteria) this;
        }

        public Criteria andLocksnEqualTo(Integer value) {
            addCriterion("locksn =", value, "locksn");
            return (Criteria) this;
        }

        public Criteria andLocksnNotEqualTo(Integer value) {
            addCriterion("locksn <>", value, "locksn");
            return (Criteria) this;
        }

        public Criteria andLocksnGreaterThan(Integer value) {
            addCriterion("locksn >", value, "locksn");
            return (Criteria) this;
        }

        public Criteria andLocksnGreaterThanOrEqualTo(Integer value) {
            addCriterion("locksn >=", value, "locksn");
            return (Criteria) this;
        }

        public Criteria andLocksnLessThan(Integer value) {
            addCriterion("locksn <", value, "locksn");
            return (Criteria) this;
        }

        public Criteria andLocksnLessThanOrEqualTo(Integer value) {
            addCriterion("locksn <=", value, "locksn");
            return (Criteria) this;
        }

        public Criteria andLocksnIn(List<Integer> values) {
            addCriterion("locksn in", values, "locksn");
            return (Criteria) this;
        }

        public Criteria andLocksnNotIn(List<Integer> values) {
            addCriterion("locksn not in", values, "locksn");
            return (Criteria) this;
        }

        public Criteria andLocksnBetween(Integer value1, Integer value2) {
            addCriterion("locksn between", value1, value2, "locksn");
            return (Criteria) this;
        }

        public Criteria andLocksnNotBetween(Integer value1, Integer value2) {
            addCriterion("locksn not between", value1, value2, "locksn");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andLockpwdauthIsNull() {
            addCriterion("lockpwdauth is null");
            return (Criteria) this;
        }

        public Criteria andLockpwdauthIsNotNull() {
            addCriterion("lockpwdauth is not null");
            return (Criteria) this;
        }

        public Criteria andLockpwdauthEqualTo(Boolean value) {
            addCriterion("lockpwdauth =", value, "lockpwdauth");
            return (Criteria) this;
        }

        public Criteria andLockpwdauthNotEqualTo(Boolean value) {
            addCriterion("lockpwdauth <>", value, "lockpwdauth");
            return (Criteria) this;
        }

        public Criteria andLockpwdauthGreaterThan(Boolean value) {
            addCriterion("lockpwdauth >", value, "lockpwdauth");
            return (Criteria) this;
        }

        public Criteria andLockpwdauthGreaterThanOrEqualTo(Boolean value) {
            addCriterion("lockpwdauth >=", value, "lockpwdauth");
            return (Criteria) this;
        }

        public Criteria andLockpwdauthLessThan(Boolean value) {
            addCriterion("lockpwdauth <", value, "lockpwdauth");
            return (Criteria) this;
        }

        public Criteria andLockpwdauthLessThanOrEqualTo(Boolean value) {
            addCriterion("lockpwdauth <=", value, "lockpwdauth");
            return (Criteria) this;
        }

        public Criteria andLockpwdauthIn(List<Boolean> values) {
            addCriterion("lockpwdauth in", values, "lockpwdauth");
            return (Criteria) this;
        }

        public Criteria andLockpwdauthNotIn(List<Boolean> values) {
            addCriterion("lockpwdauth not in", values, "lockpwdauth");
            return (Criteria) this;
        }

        public Criteria andLockpwdauthBetween(Boolean value1, Boolean value2) {
            addCriterion("lockpwdauth between", value1, value2, "lockpwdauth");
            return (Criteria) this;
        }

        public Criteria andLockpwdauthNotBetween(Boolean value1, Boolean value2) {
            addCriterion("lockpwdauth not between", value1, value2, "lockpwdauth");
            return (Criteria) this;
        }

        public Criteria andFingerpwdauthIsNull() {
            addCriterion("fingerpwdauth is null");
            return (Criteria) this;
        }

        public Criteria andFingerpwdauthIsNotNull() {
            addCriterion("fingerpwdauth is not null");
            return (Criteria) this;
        }

        public Criteria andFingerpwdauthEqualTo(Boolean value) {
            addCriterion("fingerpwdauth =", value, "fingerpwdauth");
            return (Criteria) this;
        }

        public Criteria andFingerpwdauthNotEqualTo(Boolean value) {
            addCriterion("fingerpwdauth <>", value, "fingerpwdauth");
            return (Criteria) this;
        }

        public Criteria andFingerpwdauthGreaterThan(Boolean value) {
            addCriterion("fingerpwdauth >", value, "fingerpwdauth");
            return (Criteria) this;
        }

        public Criteria andFingerpwdauthGreaterThanOrEqualTo(Boolean value) {
            addCriterion("fingerpwdauth >=", value, "fingerpwdauth");
            return (Criteria) this;
        }

        public Criteria andFingerpwdauthLessThan(Boolean value) {
            addCriterion("fingerpwdauth <", value, "fingerpwdauth");
            return (Criteria) this;
        }

        public Criteria andFingerpwdauthLessThanOrEqualTo(Boolean value) {
            addCriterion("fingerpwdauth <=", value, "fingerpwdauth");
            return (Criteria) this;
        }

        public Criteria andFingerpwdauthIn(List<Boolean> values) {
            addCriterion("fingerpwdauth in", values, "fingerpwdauth");
            return (Criteria) this;
        }

        public Criteria andFingerpwdauthNotIn(List<Boolean> values) {
            addCriterion("fingerpwdauth not in", values, "fingerpwdauth");
            return (Criteria) this;
        }

        public Criteria andFingerpwdauthBetween(Boolean value1, Boolean value2) {
            addCriterion("fingerpwdauth between", value1, value2, "fingerpwdauth");
            return (Criteria) this;
        }

        public Criteria andFingerpwdauthNotBetween(Boolean value1, Boolean value2) {
            addCriterion("fingerpwdauth not between", value1, value2, "fingerpwdauth");
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