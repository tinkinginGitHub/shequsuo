package cn.anyoufang.entity;

import java.util.ArrayList;
import java.util.List;

public class SpLockFingerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SpLockFingerExample() {
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

        public Criteria andLocksnIsNull() {
            addCriterion("locksn is null");
            return (Criteria) this;
        }

        public Criteria andLocksnIsNotNull() {
            addCriterion("locksn is not null");
            return (Criteria) this;
        }

        public Criteria andLocksnEqualTo(String value) {
            addCriterion("locksn =", value, "locksn");
            return (Criteria) this;
        }

        public Criteria andLocksnNotEqualTo(String value) {
            addCriterion("locksn <>", value, "locksn");
            return (Criteria) this;
        }

        public Criteria andLocksnGreaterThan(String value) {
            addCriterion("locksn >", value, "locksn");
            return (Criteria) this;
        }

        public Criteria andLocksnGreaterThanOrEqualTo(String value) {
            addCriterion("locksn >=", value, "locksn");
            return (Criteria) this;
        }

        public Criteria andLocksnLessThan(String value) {
            addCriterion("locksn <", value, "locksn");
            return (Criteria) this;
        }

        public Criteria andLocksnLessThanOrEqualTo(String value) {
            addCriterion("locksn <=", value, "locksn");
            return (Criteria) this;
        }

        public Criteria andLocksnLike(String value) {
            addCriterion("locksn like", value, "locksn");
            return (Criteria) this;
        }

        public Criteria andLocksnNotLike(String value) {
            addCriterion("locksn not like", value, "locksn");
            return (Criteria) this;
        }

        public Criteria andLocksnIn(List<String> values) {
            addCriterion("locksn in", values, "locksn");
            return (Criteria) this;
        }

        public Criteria andLocksnNotIn(List<String> values) {
            addCriterion("locksn not in", values, "locksn");
            return (Criteria) this;
        }

        public Criteria andLocksnBetween(String value1, String value2) {
            addCriterion("locksn between", value1, value2, "locksn");
            return (Criteria) this;
        }

        public Criteria andLocksnNotBetween(String value1, String value2) {
            addCriterion("locksn not between", value1, value2, "locksn");
            return (Criteria) this;
        }

        public Criteria andMemberidIsNull() {
            addCriterion("memberid is null");
            return (Criteria) this;
        }

        public Criteria andMemberidIsNotNull() {
            addCriterion("memberid is not null");
            return (Criteria) this;
        }

        public Criteria andMemberidEqualTo(Integer value) {
            addCriterion("memberid =", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidNotEqualTo(Integer value) {
            addCriterion("memberid <>", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidGreaterThan(Integer value) {
            addCriterion("memberid >", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidGreaterThanOrEqualTo(Integer value) {
            addCriterion("memberid >=", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidLessThan(Integer value) {
            addCriterion("memberid <", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidLessThanOrEqualTo(Integer value) {
            addCriterion("memberid <=", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidIn(List<Integer> values) {
            addCriterion("memberid in", values, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidNotIn(List<Integer> values) {
            addCriterion("memberid not in", values, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidBetween(Integer value1, Integer value2) {
            addCriterion("memberid between", value1, value2, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidNotBetween(Integer value1, Integer value2) {
            addCriterion("memberid not between", value1, value2, "memberid");
            return (Criteria) this;
        }

        public Criteria andFingeridIsNull() {
            addCriterion("fingerid is null");
            return (Criteria) this;
        }

        public Criteria andFingeridIsNotNull() {
            addCriterion("fingerid is not null");
            return (Criteria) this;
        }

        public Criteria andFingeridEqualTo(String value) {
            addCriterion("fingerid =", value, "fingerid");
            return (Criteria) this;
        }

        public Criteria andFingeridNotEqualTo(String value) {
            addCriterion("fingerid <>", value, "fingerid");
            return (Criteria) this;
        }

        public Criteria andFingeridGreaterThan(String value) {
            addCriterion("fingerid >", value, "fingerid");
            return (Criteria) this;
        }

        public Criteria andFingeridGreaterThanOrEqualTo(String value) {
            addCriterion("fingerid >=", value, "fingerid");
            return (Criteria) this;
        }

        public Criteria andFingeridLessThan(String value) {
            addCriterion("fingerid <", value, "fingerid");
            return (Criteria) this;
        }

        public Criteria andFingeridLessThanOrEqualTo(String value) {
            addCriterion("fingerid <=", value, "fingerid");
            return (Criteria) this;
        }

        public Criteria andFingeridLike(String value) {
            addCriterion("fingerid like", value, "fingerid");
            return (Criteria) this;
        }

        public Criteria andFingeridNotLike(String value) {
            addCriterion("fingerid not like", value, "fingerid");
            return (Criteria) this;
        }

        public Criteria andFingeridIn(List<String> values) {
            addCriterion("fingerid in", values, "fingerid");
            return (Criteria) this;
        }

        public Criteria andFingeridNotIn(List<String> values) {
            addCriterion("fingerid not in", values, "fingerid");
            return (Criteria) this;
        }

        public Criteria andFingeridBetween(String value1, String value2) {
            addCriterion("fingerid between", value1, value2, "fingerid");
            return (Criteria) this;
        }

        public Criteria andFingeridNotBetween(String value1, String value2) {
            addCriterion("fingerid not between", value1, value2, "fingerid");
            return (Criteria) this;
        }

        public Criteria andFingerdescIsNull() {
            addCriterion("fingerdesc is null");
            return (Criteria) this;
        }

        public Criteria andFingerdescIsNotNull() {
            addCriterion("fingerdesc is not null");
            return (Criteria) this;
        }

        public Criteria andFingerdescEqualTo(String value) {
            addCriterion("fingerdesc =", value, "fingerdesc");
            return (Criteria) this;
        }

        public Criteria andFingerdescNotEqualTo(String value) {
            addCriterion("fingerdesc <>", value, "fingerdesc");
            return (Criteria) this;
        }

        public Criteria andFingerdescGreaterThan(String value) {
            addCriterion("fingerdesc >", value, "fingerdesc");
            return (Criteria) this;
        }

        public Criteria andFingerdescGreaterThanOrEqualTo(String value) {
            addCriterion("fingerdesc >=", value, "fingerdesc");
            return (Criteria) this;
        }

        public Criteria andFingerdescLessThan(String value) {
            addCriterion("fingerdesc <", value, "fingerdesc");
            return (Criteria) this;
        }

        public Criteria andFingerdescLessThanOrEqualTo(String value) {
            addCriterion("fingerdesc <=", value, "fingerdesc");
            return (Criteria) this;
        }

        public Criteria andFingerdescLike(String value) {
            addCriterion("fingerdesc like", value, "fingerdesc");
            return (Criteria) this;
        }

        public Criteria andFingerdescNotLike(String value) {
            addCriterion("fingerdesc not like", value, "fingerdesc");
            return (Criteria) this;
        }

        public Criteria andFingerdescIn(List<String> values) {
            addCriterion("fingerdesc in", values, "fingerdesc");
            return (Criteria) this;
        }

        public Criteria andFingerdescNotIn(List<String> values) {
            addCriterion("fingerdesc not in", values, "fingerdesc");
            return (Criteria) this;
        }

        public Criteria andFingerdescBetween(String value1, String value2) {
            addCriterion("fingerdesc between", value1, value2, "fingerdesc");
            return (Criteria) this;
        }

        public Criteria andFingerdescNotBetween(String value1, String value2) {
            addCriterion("fingerdesc not between", value1, value2, "fingerdesc");
            return (Criteria) this;
        }

        public Criteria andPtypeIsNull() {
            addCriterion("ptype is null");
            return (Criteria) this;
        }

        public Criteria andPtypeIsNotNull() {
            addCriterion("ptype is not null");
            return (Criteria) this;
        }

        public Criteria andPtypeEqualTo(Integer value) {
            addCriterion("ptype =", value, "ptype");
            return (Criteria) this;
        }

        public Criteria andPtypeNotEqualTo(Integer value) {
            addCriterion("ptype <>", value, "ptype");
            return (Criteria) this;
        }

        public Criteria andPtypeGreaterThan(Integer value) {
            addCriterion("ptype >", value, "ptype");
            return (Criteria) this;
        }

        public Criteria andPtypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("ptype >=", value, "ptype");
            return (Criteria) this;
        }

        public Criteria andPtypeLessThan(Integer value) {
            addCriterion("ptype <", value, "ptype");
            return (Criteria) this;
        }

        public Criteria andPtypeLessThanOrEqualTo(Integer value) {
            addCriterion("ptype <=", value, "ptype");
            return (Criteria) this;
        }

        public Criteria andPtypeIn(List<Integer> values) {
            addCriterion("ptype in", values, "ptype");
            return (Criteria) this;
        }

        public Criteria andPtypeNotIn(List<Integer> values) {
            addCriterion("ptype not in", values, "ptype");
            return (Criteria) this;
        }

        public Criteria andPtypeBetween(Integer value1, Integer value2) {
            addCriterion("ptype between", value1, value2, "ptype");
            return (Criteria) this;
        }

        public Criteria andPtypeNotBetween(Integer value1, Integer value2) {
            addCriterion("ptype not between", value1, value2, "ptype");
            return (Criteria) this;
        }

        public Criteria andAddtimeIsNull() {
            addCriterion("addtime is null");
            return (Criteria) this;
        }

        public Criteria andAddtimeIsNotNull() {
            addCriterion("addtime is not null");
            return (Criteria) this;
        }

        public Criteria andAddtimeEqualTo(Integer value) {
            addCriterion("addtime =", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotEqualTo(Integer value) {
            addCriterion("addtime <>", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeGreaterThan(Integer value) {
            addCriterion("addtime >", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("addtime >=", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeLessThan(Integer value) {
            addCriterion("addtime <", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeLessThanOrEqualTo(Integer value) {
            addCriterion("addtime <=", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeIn(List<Integer> values) {
            addCriterion("addtime in", values, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotIn(List<Integer> values) {
            addCriterion("addtime not in", values, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeBetween(Integer value1, Integer value2) {
            addCriterion("addtime between", value1, value2, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotBetween(Integer value1, Integer value2) {
            addCriterion("addtime not between", value1, value2, "addtime");
            return (Criteria) this;
        }

        public Criteria andExpiredIsNull() {
            addCriterion("expired is null");
            return (Criteria) this;
        }

        public Criteria andExpiredIsNotNull() {
            addCriterion("expired is not null");
            return (Criteria) this;
        }

        public Criteria andExpiredEqualTo(Boolean value) {
            addCriterion("expired =", value, "expired");
            return (Criteria) this;
        }

        public Criteria andExpiredNotEqualTo(Boolean value) {
            addCriterion("expired <>", value, "expired");
            return (Criteria) this;
        }

        public Criteria andExpiredGreaterThan(Boolean value) {
            addCriterion("expired >", value, "expired");
            return (Criteria) this;
        }

        public Criteria andExpiredGreaterThanOrEqualTo(Boolean value) {
            addCriterion("expired >=", value, "expired");
            return (Criteria) this;
        }

        public Criteria andExpiredLessThan(Boolean value) {
            addCriterion("expired <", value, "expired");
            return (Criteria) this;
        }

        public Criteria andExpiredLessThanOrEqualTo(Boolean value) {
            addCriterion("expired <=", value, "expired");
            return (Criteria) this;
        }

        public Criteria andExpiredIn(List<Boolean> values) {
            addCriterion("expired in", values, "expired");
            return (Criteria) this;
        }

        public Criteria andExpiredNotIn(List<Boolean> values) {
            addCriterion("expired not in", values, "expired");
            return (Criteria) this;
        }

        public Criteria andExpiredBetween(Boolean value1, Boolean value2) {
            addCriterion("expired between", value1, value2, "expired");
            return (Criteria) this;
        }

        public Criteria andExpiredNotBetween(Boolean value1, Boolean value2) {
            addCriterion("expired not between", value1, value2, "expired");
            return (Criteria) this;
        }

        public Criteria andDeltimeIsNull() {
            addCriterion("deltime is null");
            return (Criteria) this;
        }

        public Criteria andDeltimeIsNotNull() {
            addCriterion("deltime is not null");
            return (Criteria) this;
        }

        public Criteria andDeltimeEqualTo(Integer value) {
            addCriterion("deltime =", value, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeNotEqualTo(Integer value) {
            addCriterion("deltime <>", value, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeGreaterThan(Integer value) {
            addCriterion("deltime >", value, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("deltime >=", value, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeLessThan(Integer value) {
            addCriterion("deltime <", value, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeLessThanOrEqualTo(Integer value) {
            addCriterion("deltime <=", value, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeIn(List<Integer> values) {
            addCriterion("deltime in", values, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeNotIn(List<Integer> values) {
            addCriterion("deltime not in", values, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeBetween(Integer value1, Integer value2) {
            addCriterion("deltime between", value1, value2, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeNotBetween(Integer value1, Integer value2) {
            addCriterion("deltime not between", value1, value2, "deltime");
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