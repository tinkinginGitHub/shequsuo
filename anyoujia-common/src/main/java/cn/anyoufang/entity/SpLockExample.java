package cn.anyoufang.entity;

import java.util.ArrayList;
import java.util.List;

public class SpLockExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SpLockExample() {
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

        public Criteria andSnIsNull() {
            addCriterion("sn is null");
            return (Criteria) this;
        }

        public Criteria andSnIsNotNull() {
            addCriterion("sn is not null");
            return (Criteria) this;
        }

        public Criteria andSnEqualTo(String value) {
            addCriterion("sn =", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotEqualTo(String value) {
            addCriterion("sn <>", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnGreaterThan(String value) {
            addCriterion("sn >", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnGreaterThanOrEqualTo(String value) {
            addCriterion("sn >=", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnLessThan(String value) {
            addCriterion("sn <", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnLessThanOrEqualTo(String value) {
            addCriterion("sn <=", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnLike(String value) {
            addCriterion("sn like", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotLike(String value) {
            addCriterion("sn not like", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnIn(List<String> values) {
            addCriterion("sn in", values, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotIn(List<String> values) {
            addCriterion("sn not in", values, "sn");
            return (Criteria) this;
        }

        public Criteria andSnBetween(String value1, String value2) {
            addCriterion("sn between", value1, value2, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotBetween(String value1, String value2) {
            addCriterion("sn not between", value1, value2, "sn");
            return (Criteria) this;
        }

        public Criteria andCode1IsNull() {
            addCriterion("code1 is null");
            return (Criteria) this;
        }

        public Criteria andCode1IsNotNull() {
            addCriterion("code1 is not null");
            return (Criteria) this;
        }

        public Criteria andCode1EqualTo(String value) {
            addCriterion("code1 =", value, "code1");
            return (Criteria) this;
        }

        public Criteria andCode1NotEqualTo(String value) {
            addCriterion("code1 <>", value, "code1");
            return (Criteria) this;
        }

        public Criteria andCode1GreaterThan(String value) {
            addCriterion("code1 >", value, "code1");
            return (Criteria) this;
        }

        public Criteria andCode1GreaterThanOrEqualTo(String value) {
            addCriterion("code1 >=", value, "code1");
            return (Criteria) this;
        }

        public Criteria andCode1LessThan(String value) {
            addCriterion("code1 <", value, "code1");
            return (Criteria) this;
        }

        public Criteria andCode1LessThanOrEqualTo(String value) {
            addCriterion("code1 <=", value, "code1");
            return (Criteria) this;
        }

        public Criteria andCode1Like(String value) {
            addCriterion("code1 like", value, "code1");
            return (Criteria) this;
        }

        public Criteria andCode1NotLike(String value) {
            addCriterion("code1 not like", value, "code1");
            return (Criteria) this;
        }

        public Criteria andCode1In(List<String> values) {
            addCriterion("code1 in", values, "code1");
            return (Criteria) this;
        }

        public Criteria andCode1NotIn(List<String> values) {
            addCriterion("code1 not in", values, "code1");
            return (Criteria) this;
        }

        public Criteria andCode1Between(String value1, String value2) {
            addCriterion("code1 between", value1, value2, "code1");
            return (Criteria) this;
        }

        public Criteria andCode1NotBetween(String value1, String value2) {
            addCriterion("code1 not between", value1, value2, "code1");
            return (Criteria) this;
        }

        public Criteria andCode2IsNull() {
            addCriterion("code2 is null");
            return (Criteria) this;
        }

        public Criteria andCode2IsNotNull() {
            addCriterion("code2 is not null");
            return (Criteria) this;
        }

        public Criteria andCode2EqualTo(String value) {
            addCriterion("code2 =", value, "code2");
            return (Criteria) this;
        }

        public Criteria andCode2NotEqualTo(String value) {
            addCriterion("code2 <>", value, "code2");
            return (Criteria) this;
        }

        public Criteria andCode2GreaterThan(String value) {
            addCriterion("code2 >", value, "code2");
            return (Criteria) this;
        }

        public Criteria andCode2GreaterThanOrEqualTo(String value) {
            addCriterion("code2 >=", value, "code2");
            return (Criteria) this;
        }

        public Criteria andCode2LessThan(String value) {
            addCriterion("code2 <", value, "code2");
            return (Criteria) this;
        }

        public Criteria andCode2LessThanOrEqualTo(String value) {
            addCriterion("code2 <=", value, "code2");
            return (Criteria) this;
        }

        public Criteria andCode2Like(String value) {
            addCriterion("code2 like", value, "code2");
            return (Criteria) this;
        }

        public Criteria andCode2NotLike(String value) {
            addCriterion("code2 not like", value, "code2");
            return (Criteria) this;
        }

        public Criteria andCode2In(List<String> values) {
            addCriterion("code2 in", values, "code2");
            return (Criteria) this;
        }

        public Criteria andCode2NotIn(List<String> values) {
            addCriterion("code2 not in", values, "code2");
            return (Criteria) this;
        }

        public Criteria andCode2Between(String value1, String value2) {
            addCriterion("code2 between", value1, value2, "code2");
            return (Criteria) this;
        }

        public Criteria andCode2NotBetween(String value1, String value2) {
            addCriterion("code2 not between", value1, value2, "code2");
            return (Criteria) this;
        }

        public Criteria andActiveIsNull() {
            addCriterion("active is null");
            return (Criteria) this;
        }

        public Criteria andActiveIsNotNull() {
            addCriterion("active is not null");
            return (Criteria) this;
        }

        public Criteria andActiveEqualTo(Boolean value) {
            addCriterion("active =", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveNotEqualTo(Boolean value) {
            addCriterion("active <>", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveGreaterThan(Boolean value) {
            addCriterion("active >", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveGreaterThanOrEqualTo(Boolean value) {
            addCriterion("active >=", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveLessThan(Boolean value) {
            addCriterion("active <", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveLessThanOrEqualTo(Boolean value) {
            addCriterion("active <=", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveIn(List<Boolean> values) {
            addCriterion("active in", values, "active");
            return (Criteria) this;
        }

        public Criteria andActiveNotIn(List<Boolean> values) {
            addCriterion("active not in", values, "active");
            return (Criteria) this;
        }

        public Criteria andActiveBetween(Boolean value1, Boolean value2) {
            addCriterion("active between", value1, value2, "active");
            return (Criteria) this;
        }

        public Criteria andActiveNotBetween(Boolean value1, Boolean value2) {
            addCriterion("active not between", value1, value2, "active");
            return (Criteria) this;
        }

        public Criteria andVerIsNull() {
            addCriterion("ver is null");
            return (Criteria) this;
        }

        public Criteria andVerIsNotNull() {
            addCriterion("ver is not null");
            return (Criteria) this;
        }

        public Criteria andVerEqualTo(String value) {
            addCriterion("ver =", value, "ver");
            return (Criteria) this;
        }

        public Criteria andVerNotEqualTo(String value) {
            addCriterion("ver <>", value, "ver");
            return (Criteria) this;
        }

        public Criteria andVerGreaterThan(String value) {
            addCriterion("ver >", value, "ver");
            return (Criteria) this;
        }

        public Criteria andVerGreaterThanOrEqualTo(String value) {
            addCriterion("ver >=", value, "ver");
            return (Criteria) this;
        }

        public Criteria andVerLessThan(String value) {
            addCriterion("ver <", value, "ver");
            return (Criteria) this;
        }

        public Criteria andVerLessThanOrEqualTo(String value) {
            addCriterion("ver <=", value, "ver");
            return (Criteria) this;
        }

        public Criteria andVerLike(String value) {
            addCriterion("ver like", value, "ver");
            return (Criteria) this;
        }

        public Criteria andVerNotLike(String value) {
            addCriterion("ver not like", value, "ver");
            return (Criteria) this;
        }

        public Criteria andVerIn(List<String> values) {
            addCriterion("ver in", values, "ver");
            return (Criteria) this;
        }

        public Criteria andVerNotIn(List<String> values) {
            addCriterion("ver not in", values, "ver");
            return (Criteria) this;
        }

        public Criteria andVerBetween(String value1, String value2) {
            addCriterion("ver between", value1, value2, "ver");
            return (Criteria) this;
        }

        public Criteria andVerNotBetween(String value1, String value2) {
            addCriterion("ver not between", value1, value2, "ver");
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

        public Criteria andActivetimeIsNull() {
            addCriterion("activetime is null");
            return (Criteria) this;
        }

        public Criteria andActivetimeIsNotNull() {
            addCriterion("activetime is not null");
            return (Criteria) this;
        }

        public Criteria andActivetimeEqualTo(Integer value) {
            addCriterion("activetime =", value, "activetime");
            return (Criteria) this;
        }

        public Criteria andActivetimeNotEqualTo(Integer value) {
            addCriterion("activetime <>", value, "activetime");
            return (Criteria) this;
        }

        public Criteria andActivetimeGreaterThan(Integer value) {
            addCriterion("activetime >", value, "activetime");
            return (Criteria) this;
        }

        public Criteria andActivetimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("activetime >=", value, "activetime");
            return (Criteria) this;
        }

        public Criteria andActivetimeLessThan(Integer value) {
            addCriterion("activetime <", value, "activetime");
            return (Criteria) this;
        }

        public Criteria andActivetimeLessThanOrEqualTo(Integer value) {
            addCriterion("activetime <=", value, "activetime");
            return (Criteria) this;
        }

        public Criteria andActivetimeIn(List<Integer> values) {
            addCriterion("activetime in", values, "activetime");
            return (Criteria) this;
        }

        public Criteria andActivetimeNotIn(List<Integer> values) {
            addCriterion("activetime not in", values, "activetime");
            return (Criteria) this;
        }

        public Criteria andActivetimeBetween(Integer value1, Integer value2) {
            addCriterion("activetime between", value1, value2, "activetime");
            return (Criteria) this;
        }

        public Criteria andActivetimeNotBetween(Integer value1, Integer value2) {
            addCriterion("activetime not between", value1, value2, "activetime");
            return (Criteria) this;
        }

        public Criteria andCardIsNull() {
            addCriterion("card is null");
            return (Criteria) this;
        }

        public Criteria andCardIsNotNull() {
            addCriterion("card is not null");
            return (Criteria) this;
        }

        public Criteria andCardEqualTo(String value) {
            addCriterion("card =", value, "card");
            return (Criteria) this;
        }

        public Criteria andCardNotEqualTo(String value) {
            addCriterion("card <>", value, "card");
            return (Criteria) this;
        }

        public Criteria andCardGreaterThan(String value) {
            addCriterion("card >", value, "card");
            return (Criteria) this;
        }

        public Criteria andCardGreaterThanOrEqualTo(String value) {
            addCriterion("card >=", value, "card");
            return (Criteria) this;
        }

        public Criteria andCardLessThan(String value) {
            addCriterion("card <", value, "card");
            return (Criteria) this;
        }

        public Criteria andCardLessThanOrEqualTo(String value) {
            addCriterion("card <=", value, "card");
            return (Criteria) this;
        }

        public Criteria andCardLike(String value) {
            addCriterion("card like", value, "card");
            return (Criteria) this;
        }

        public Criteria andCardNotLike(String value) {
            addCriterion("card not like", value, "card");
            return (Criteria) this;
        }

        public Criteria andCardIn(List<String> values) {
            addCriterion("card in", values, "card");
            return (Criteria) this;
        }

        public Criteria andCardNotIn(List<String> values) {
            addCriterion("card not in", values, "card");
            return (Criteria) this;
        }

        public Criteria andCardBetween(String value1, String value2) {
            addCriterion("card between", value1, value2, "card");
            return (Criteria) this;
        }

        public Criteria andCardNotBetween(String value1, String value2) {
            addCriterion("card not between", value1, value2, "card");
            return (Criteria) this;
        }

        public Criteria andLbsIsNull() {
            addCriterion("lbs is null");
            return (Criteria) this;
        }

        public Criteria andLbsIsNotNull() {
            addCriterion("lbs is not null");
            return (Criteria) this;
        }

        public Criteria andLbsEqualTo(String value) {
            addCriterion("lbs =", value, "lbs");
            return (Criteria) this;
        }

        public Criteria andLbsNotEqualTo(String value) {
            addCriterion("lbs <>", value, "lbs");
            return (Criteria) this;
        }

        public Criteria andLbsGreaterThan(String value) {
            addCriterion("lbs >", value, "lbs");
            return (Criteria) this;
        }

        public Criteria andLbsGreaterThanOrEqualTo(String value) {
            addCriterion("lbs >=", value, "lbs");
            return (Criteria) this;
        }

        public Criteria andLbsLessThan(String value) {
            addCriterion("lbs <", value, "lbs");
            return (Criteria) this;
        }

        public Criteria andLbsLessThanOrEqualTo(String value) {
            addCriterion("lbs <=", value, "lbs");
            return (Criteria) this;
        }

        public Criteria andLbsLike(String value) {
            addCriterion("lbs like", value, "lbs");
            return (Criteria) this;
        }

        public Criteria andLbsNotLike(String value) {
            addCriterion("lbs not like", value, "lbs");
            return (Criteria) this;
        }

        public Criteria andLbsIn(List<String> values) {
            addCriterion("lbs in", values, "lbs");
            return (Criteria) this;
        }

        public Criteria andLbsNotIn(List<String> values) {
            addCriterion("lbs not in", values, "lbs");
            return (Criteria) this;
        }

        public Criteria andLbsBetween(String value1, String value2) {
            addCriterion("lbs between", value1, value2, "lbs");
            return (Criteria) this;
        }

        public Criteria andLbsNotBetween(String value1, String value2) {
            addCriterion("lbs not between", value1, value2, "lbs");
            return (Criteria) this;
        }

        public Criteria andPingIsNull() {
            addCriterion("ping is null");
            return (Criteria) this;
        }

        public Criteria andPingIsNotNull() {
            addCriterion("ping is not null");
            return (Criteria) this;
        }

        public Criteria andPingEqualTo(Integer value) {
            addCriterion("ping =", value, "ping");
            return (Criteria) this;
        }

        public Criteria andPingNotEqualTo(Integer value) {
            addCriterion("ping <>", value, "ping");
            return (Criteria) this;
        }

        public Criteria andPingGreaterThan(Integer value) {
            addCriterion("ping >", value, "ping");
            return (Criteria) this;
        }

        public Criteria andPingGreaterThanOrEqualTo(Integer value) {
            addCriterion("ping >=", value, "ping");
            return (Criteria) this;
        }

        public Criteria andPingLessThan(Integer value) {
            addCriterion("ping <", value, "ping");
            return (Criteria) this;
        }

        public Criteria andPingLessThanOrEqualTo(Integer value) {
            addCriterion("ping <=", value, "ping");
            return (Criteria) this;
        }

        public Criteria andPingIn(List<Integer> values) {
            addCriterion("ping in", values, "ping");
            return (Criteria) this;
        }

        public Criteria andPingNotIn(List<Integer> values) {
            addCriterion("ping not in", values, "ping");
            return (Criteria) this;
        }

        public Criteria andPingBetween(Integer value1, Integer value2) {
            addCriterion("ping between", value1, value2, "ping");
            return (Criteria) this;
        }

        public Criteria andPingNotBetween(Integer value1, Integer value2) {
            addCriterion("ping not between", value1, value2, "ping");
            return (Criteria) this;
        }

        public Criteria andUpcycIsNull() {
            addCriterion("upcyc is null");
            return (Criteria) this;
        }

        public Criteria andUpcycIsNotNull() {
            addCriterion("upcyc is not null");
            return (Criteria) this;
        }

        public Criteria andUpcycEqualTo(Integer value) {
            addCriterion("upcyc =", value, "upcyc");
            return (Criteria) this;
        }

        public Criteria andUpcycNotEqualTo(Integer value) {
            addCriterion("upcyc <>", value, "upcyc");
            return (Criteria) this;
        }

        public Criteria andUpcycGreaterThan(Integer value) {
            addCriterion("upcyc >", value, "upcyc");
            return (Criteria) this;
        }

        public Criteria andUpcycGreaterThanOrEqualTo(Integer value) {
            addCriterion("upcyc >=", value, "upcyc");
            return (Criteria) this;
        }

        public Criteria andUpcycLessThan(Integer value) {
            addCriterion("upcyc <", value, "upcyc");
            return (Criteria) this;
        }

        public Criteria andUpcycLessThanOrEqualTo(Integer value) {
            addCriterion("upcyc <=", value, "upcyc");
            return (Criteria) this;
        }

        public Criteria andUpcycIn(List<Integer> values) {
            addCriterion("upcyc in", values, "upcyc");
            return (Criteria) this;
        }

        public Criteria andUpcycNotIn(List<Integer> values) {
            addCriterion("upcyc not in", values, "upcyc");
            return (Criteria) this;
        }

        public Criteria andUpcycBetween(Integer value1, Integer value2) {
            addCriterion("upcyc between", value1, value2, "upcyc");
            return (Criteria) this;
        }

        public Criteria andUpcycNotBetween(Integer value1, Integer value2) {
            addCriterion("upcyc not between", value1, value2, "upcyc");
            return (Criteria) this;
        }

        public Criteria andOvertimeIsNull() {
            addCriterion("overtime is null");
            return (Criteria) this;
        }

        public Criteria andOvertimeIsNotNull() {
            addCriterion("overtime is not null");
            return (Criteria) this;
        }

        public Criteria andOvertimeEqualTo(Integer value) {
            addCriterion("overtime =", value, "overtime");
            return (Criteria) this;
        }

        public Criteria andOvertimeNotEqualTo(Integer value) {
            addCriterion("overtime <>", value, "overtime");
            return (Criteria) this;
        }

        public Criteria andOvertimeGreaterThan(Integer value) {
            addCriterion("overtime >", value, "overtime");
            return (Criteria) this;
        }

        public Criteria andOvertimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("overtime >=", value, "overtime");
            return (Criteria) this;
        }

        public Criteria andOvertimeLessThan(Integer value) {
            addCriterion("overtime <", value, "overtime");
            return (Criteria) this;
        }

        public Criteria andOvertimeLessThanOrEqualTo(Integer value) {
            addCriterion("overtime <=", value, "overtime");
            return (Criteria) this;
        }

        public Criteria andOvertimeIn(List<Integer> values) {
            addCriterion("overtime in", values, "overtime");
            return (Criteria) this;
        }

        public Criteria andOvertimeNotIn(List<Integer> values) {
            addCriterion("overtime not in", values, "overtime");
            return (Criteria) this;
        }

        public Criteria andOvertimeBetween(Integer value1, Integer value2) {
            addCriterion("overtime between", value1, value2, "overtime");
            return (Criteria) this;
        }

        public Criteria andOvertimeNotBetween(Integer value1, Integer value2) {
            addCriterion("overtime not between", value1, value2, "overtime");
            return (Criteria) this;
        }

        public Criteria andTcpIsNull() {
            addCriterion("tcp is null");
            return (Criteria) this;
        }

        public Criteria andTcpIsNotNull() {
            addCriterion("tcp is not null");
            return (Criteria) this;
        }

        public Criteria andTcpEqualTo(Integer value) {
            addCriterion("tcp =", value, "tcp");
            return (Criteria) this;
        }

        public Criteria andTcpNotEqualTo(Integer value) {
            addCriterion("tcp <>", value, "tcp");
            return (Criteria) this;
        }

        public Criteria andTcpGreaterThan(Integer value) {
            addCriterion("tcp >", value, "tcp");
            return (Criteria) this;
        }

        public Criteria andTcpGreaterThanOrEqualTo(Integer value) {
            addCriterion("tcp >=", value, "tcp");
            return (Criteria) this;
        }

        public Criteria andTcpLessThan(Integer value) {
            addCriterion("tcp <", value, "tcp");
            return (Criteria) this;
        }

        public Criteria andTcpLessThanOrEqualTo(Integer value) {
            addCriterion("tcp <=", value, "tcp");
            return (Criteria) this;
        }

        public Criteria andTcpIn(List<Integer> values) {
            addCriterion("tcp in", values, "tcp");
            return (Criteria) this;
        }

        public Criteria andTcpNotIn(List<Integer> values) {
            addCriterion("tcp not in", values, "tcp");
            return (Criteria) this;
        }

        public Criteria andTcpBetween(Integer value1, Integer value2) {
            addCriterion("tcp between", value1, value2, "tcp");
            return (Criteria) this;
        }

        public Criteria andTcpNotBetween(Integer value1, Integer value2) {
            addCriterion("tcp not between", value1, value2, "tcp");
            return (Criteria) this;
        }

        public Criteria andRetryIsNull() {
            addCriterion("retry is null");
            return (Criteria) this;
        }

        public Criteria andRetryIsNotNull() {
            addCriterion("retry is not null");
            return (Criteria) this;
        }

        public Criteria andRetryEqualTo(Byte value) {
            addCriterion("retry =", value, "retry");
            return (Criteria) this;
        }

        public Criteria andRetryNotEqualTo(Byte value) {
            addCriterion("retry <>", value, "retry");
            return (Criteria) this;
        }

        public Criteria andRetryGreaterThan(Byte value) {
            addCriterion("retry >", value, "retry");
            return (Criteria) this;
        }

        public Criteria andRetryGreaterThanOrEqualTo(Byte value) {
            addCriterion("retry >=", value, "retry");
            return (Criteria) this;
        }

        public Criteria andRetryLessThan(Byte value) {
            addCriterion("retry <", value, "retry");
            return (Criteria) this;
        }

        public Criteria andRetryLessThanOrEqualTo(Byte value) {
            addCriterion("retry <=", value, "retry");
            return (Criteria) this;
        }

        public Criteria andRetryIn(List<Byte> values) {
            addCriterion("retry in", values, "retry");
            return (Criteria) this;
        }

        public Criteria andRetryNotIn(List<Byte> values) {
            addCriterion("retry not in", values, "retry");
            return (Criteria) this;
        }

        public Criteria andRetryBetween(Byte value1, Byte value2) {
            addCriterion("retry between", value1, value2, "retry");
            return (Criteria) this;
        }

        public Criteria andRetryNotBetween(Byte value1, Byte value2) {
            addCriterion("retry not between", value1, value2, "retry");
            return (Criteria) this;
        }

        public Criteria andIp1IsNull() {
            addCriterion("ip1 is null");
            return (Criteria) this;
        }

        public Criteria andIp1IsNotNull() {
            addCriterion("ip1 is not null");
            return (Criteria) this;
        }

        public Criteria andIp1EqualTo(String value) {
            addCriterion("ip1 =", value, "ip1");
            return (Criteria) this;
        }

        public Criteria andIp1NotEqualTo(String value) {
            addCriterion("ip1 <>", value, "ip1");
            return (Criteria) this;
        }

        public Criteria andIp1GreaterThan(String value) {
            addCriterion("ip1 >", value, "ip1");
            return (Criteria) this;
        }

        public Criteria andIp1GreaterThanOrEqualTo(String value) {
            addCriterion("ip1 >=", value, "ip1");
            return (Criteria) this;
        }

        public Criteria andIp1LessThan(String value) {
            addCriterion("ip1 <", value, "ip1");
            return (Criteria) this;
        }

        public Criteria andIp1LessThanOrEqualTo(String value) {
            addCriterion("ip1 <=", value, "ip1");
            return (Criteria) this;
        }

        public Criteria andIp1Like(String value) {
            addCriterion("ip1 like", value, "ip1");
            return (Criteria) this;
        }

        public Criteria andIp1NotLike(String value) {
            addCriterion("ip1 not like", value, "ip1");
            return (Criteria) this;
        }

        public Criteria andIp1In(List<String> values) {
            addCriterion("ip1 in", values, "ip1");
            return (Criteria) this;
        }

        public Criteria andIp1NotIn(List<String> values) {
            addCriterion("ip1 not in", values, "ip1");
            return (Criteria) this;
        }

        public Criteria andIp1Between(String value1, String value2) {
            addCriterion("ip1 between", value1, value2, "ip1");
            return (Criteria) this;
        }

        public Criteria andIp1NotBetween(String value1, String value2) {
            addCriterion("ip1 not between", value1, value2, "ip1");
            return (Criteria) this;
        }

        public Criteria andIp2IsNull() {
            addCriterion("ip2 is null");
            return (Criteria) this;
        }

        public Criteria andIp2IsNotNull() {
            addCriterion("ip2 is not null");
            return (Criteria) this;
        }

        public Criteria andIp2EqualTo(String value) {
            addCriterion("ip2 =", value, "ip2");
            return (Criteria) this;
        }

        public Criteria andIp2NotEqualTo(String value) {
            addCriterion("ip2 <>", value, "ip2");
            return (Criteria) this;
        }

        public Criteria andIp2GreaterThan(String value) {
            addCriterion("ip2 >", value, "ip2");
            return (Criteria) this;
        }

        public Criteria andIp2GreaterThanOrEqualTo(String value) {
            addCriterion("ip2 >=", value, "ip2");
            return (Criteria) this;
        }

        public Criteria andIp2LessThan(String value) {
            addCriterion("ip2 <", value, "ip2");
            return (Criteria) this;
        }

        public Criteria andIp2LessThanOrEqualTo(String value) {
            addCriterion("ip2 <=", value, "ip2");
            return (Criteria) this;
        }

        public Criteria andIp2Like(String value) {
            addCriterion("ip2 like", value, "ip2");
            return (Criteria) this;
        }

        public Criteria andIp2NotLike(String value) {
            addCriterion("ip2 not like", value, "ip2");
            return (Criteria) this;
        }

        public Criteria andIp2In(List<String> values) {
            addCriterion("ip2 in", values, "ip2");
            return (Criteria) this;
        }

        public Criteria andIp2NotIn(List<String> values) {
            addCriterion("ip2 not in", values, "ip2");
            return (Criteria) this;
        }

        public Criteria andIp2Between(String value1, String value2) {
            addCriterion("ip2 between", value1, value2, "ip2");
            return (Criteria) this;
        }

        public Criteria andIp2NotBetween(String value1, String value2) {
            addCriterion("ip2 not between", value1, value2, "ip2");
            return (Criteria) this;
        }

        public Criteria andPort1IsNull() {
            addCriterion("port1 is null");
            return (Criteria) this;
        }

        public Criteria andPort1IsNotNull() {
            addCriterion("port1 is not null");
            return (Criteria) this;
        }

        public Criteria andPort1EqualTo(Integer value) {
            addCriterion("port1 =", value, "port1");
            return (Criteria) this;
        }

        public Criteria andPort1NotEqualTo(Integer value) {
            addCriterion("port1 <>", value, "port1");
            return (Criteria) this;
        }

        public Criteria andPort1GreaterThan(Integer value) {
            addCriterion("port1 >", value, "port1");
            return (Criteria) this;
        }

        public Criteria andPort1GreaterThanOrEqualTo(Integer value) {
            addCriterion("port1 >=", value, "port1");
            return (Criteria) this;
        }

        public Criteria andPort1LessThan(Integer value) {
            addCriterion("port1 <", value, "port1");
            return (Criteria) this;
        }

        public Criteria andPort1LessThanOrEqualTo(Integer value) {
            addCriterion("port1 <=", value, "port1");
            return (Criteria) this;
        }

        public Criteria andPort1In(List<Integer> values) {
            addCriterion("port1 in", values, "port1");
            return (Criteria) this;
        }

        public Criteria andPort1NotIn(List<Integer> values) {
            addCriterion("port1 not in", values, "port1");
            return (Criteria) this;
        }

        public Criteria andPort1Between(Integer value1, Integer value2) {
            addCriterion("port1 between", value1, value2, "port1");
            return (Criteria) this;
        }

        public Criteria andPort1NotBetween(Integer value1, Integer value2) {
            addCriterion("port1 not between", value1, value2, "port1");
            return (Criteria) this;
        }

        public Criteria andPort2IsNull() {
            addCriterion("port2 is null");
            return (Criteria) this;
        }

        public Criteria andPort2IsNotNull() {
            addCriterion("port2 is not null");
            return (Criteria) this;
        }

        public Criteria andPort2EqualTo(Integer value) {
            addCriterion("port2 =", value, "port2");
            return (Criteria) this;
        }

        public Criteria andPort2NotEqualTo(Integer value) {
            addCriterion("port2 <>", value, "port2");
            return (Criteria) this;
        }

        public Criteria andPort2GreaterThan(Integer value) {
            addCriterion("port2 >", value, "port2");
            return (Criteria) this;
        }

        public Criteria andPort2GreaterThanOrEqualTo(Integer value) {
            addCriterion("port2 >=", value, "port2");
            return (Criteria) this;
        }

        public Criteria andPort2LessThan(Integer value) {
            addCriterion("port2 <", value, "port2");
            return (Criteria) this;
        }

        public Criteria andPort2LessThanOrEqualTo(Integer value) {
            addCriterion("port2 <=", value, "port2");
            return (Criteria) this;
        }

        public Criteria andPort2In(List<Integer> values) {
            addCriterion("port2 in", values, "port2");
            return (Criteria) this;
        }

        public Criteria andPort2NotIn(List<Integer> values) {
            addCriterion("port2 not in", values, "port2");
            return (Criteria) this;
        }

        public Criteria andPort2Between(Integer value1, Integer value2) {
            addCriterion("port2 between", value1, value2, "port2");
            return (Criteria) this;
        }

        public Criteria andPort2NotBetween(Integer value1, Integer value2) {
            addCriterion("port2 not between", value1, value2, "port2");
            return (Criteria) this;
        }

        public Criteria andApnIsNull() {
            addCriterion("apn is null");
            return (Criteria) this;
        }

        public Criteria andApnIsNotNull() {
            addCriterion("apn is not null");
            return (Criteria) this;
        }

        public Criteria andApnEqualTo(String value) {
            addCriterion("apn =", value, "apn");
            return (Criteria) this;
        }

        public Criteria andApnNotEqualTo(String value) {
            addCriterion("apn <>", value, "apn");
            return (Criteria) this;
        }

        public Criteria andApnGreaterThan(String value) {
            addCriterion("apn >", value, "apn");
            return (Criteria) this;
        }

        public Criteria andApnGreaterThanOrEqualTo(String value) {
            addCriterion("apn >=", value, "apn");
            return (Criteria) this;
        }

        public Criteria andApnLessThan(String value) {
            addCriterion("apn <", value, "apn");
            return (Criteria) this;
        }

        public Criteria andApnLessThanOrEqualTo(String value) {
            addCriterion("apn <=", value, "apn");
            return (Criteria) this;
        }

        public Criteria andApnLike(String value) {
            addCriterion("apn like", value, "apn");
            return (Criteria) this;
        }

        public Criteria andApnNotLike(String value) {
            addCriterion("apn not like", value, "apn");
            return (Criteria) this;
        }

        public Criteria andApnIn(List<String> values) {
            addCriterion("apn in", values, "apn");
            return (Criteria) this;
        }

        public Criteria andApnNotIn(List<String> values) {
            addCriterion("apn not in", values, "apn");
            return (Criteria) this;
        }

        public Criteria andApnBetween(String value1, String value2) {
            addCriterion("apn between", value1, value2, "apn");
            return (Criteria) this;
        }

        public Criteria andApnNotBetween(String value1, String value2) {
            addCriterion("apn not between", value1, value2, "apn");
            return (Criteria) this;
        }

        public Criteria andDialuserIsNull() {
            addCriterion("dialuser is null");
            return (Criteria) this;
        }

        public Criteria andDialuserIsNotNull() {
            addCriterion("dialuser is not null");
            return (Criteria) this;
        }

        public Criteria andDialuserEqualTo(String value) {
            addCriterion("dialuser =", value, "dialuser");
            return (Criteria) this;
        }

        public Criteria andDialuserNotEqualTo(String value) {
            addCriterion("dialuser <>", value, "dialuser");
            return (Criteria) this;
        }

        public Criteria andDialuserGreaterThan(String value) {
            addCriterion("dialuser >", value, "dialuser");
            return (Criteria) this;
        }

        public Criteria andDialuserGreaterThanOrEqualTo(String value) {
            addCriterion("dialuser >=", value, "dialuser");
            return (Criteria) this;
        }

        public Criteria andDialuserLessThan(String value) {
            addCriterion("dialuser <", value, "dialuser");
            return (Criteria) this;
        }

        public Criteria andDialuserLessThanOrEqualTo(String value) {
            addCriterion("dialuser <=", value, "dialuser");
            return (Criteria) this;
        }

        public Criteria andDialuserLike(String value) {
            addCriterion("dialuser like", value, "dialuser");
            return (Criteria) this;
        }

        public Criteria andDialuserNotLike(String value) {
            addCriterion("dialuser not like", value, "dialuser");
            return (Criteria) this;
        }

        public Criteria andDialuserIn(List<String> values) {
            addCriterion("dialuser in", values, "dialuser");
            return (Criteria) this;
        }

        public Criteria andDialuserNotIn(List<String> values) {
            addCriterion("dialuser not in", values, "dialuser");
            return (Criteria) this;
        }

        public Criteria andDialuserBetween(String value1, String value2) {
            addCriterion("dialuser between", value1, value2, "dialuser");
            return (Criteria) this;
        }

        public Criteria andDialuserNotBetween(String value1, String value2) {
            addCriterion("dialuser not between", value1, value2, "dialuser");
            return (Criteria) this;
        }

        public Criteria andDialpassIsNull() {
            addCriterion("dialpass is null");
            return (Criteria) this;
        }

        public Criteria andDialpassIsNotNull() {
            addCriterion("dialpass is not null");
            return (Criteria) this;
        }

        public Criteria andDialpassEqualTo(String value) {
            addCriterion("dialpass =", value, "dialpass");
            return (Criteria) this;
        }

        public Criteria andDialpassNotEqualTo(String value) {
            addCriterion("dialpass <>", value, "dialpass");
            return (Criteria) this;
        }

        public Criteria andDialpassGreaterThan(String value) {
            addCriterion("dialpass >", value, "dialpass");
            return (Criteria) this;
        }

        public Criteria andDialpassGreaterThanOrEqualTo(String value) {
            addCriterion("dialpass >=", value, "dialpass");
            return (Criteria) this;
        }

        public Criteria andDialpassLessThan(String value) {
            addCriterion("dialpass <", value, "dialpass");
            return (Criteria) this;
        }

        public Criteria andDialpassLessThanOrEqualTo(String value) {
            addCriterion("dialpass <=", value, "dialpass");
            return (Criteria) this;
        }

        public Criteria andDialpassLike(String value) {
            addCriterion("dialpass like", value, "dialpass");
            return (Criteria) this;
        }

        public Criteria andDialpassNotLike(String value) {
            addCriterion("dialpass not like", value, "dialpass");
            return (Criteria) this;
        }

        public Criteria andDialpassIn(List<String> values) {
            addCriterion("dialpass in", values, "dialpass");
            return (Criteria) this;
        }

        public Criteria andDialpassNotIn(List<String> values) {
            addCriterion("dialpass not in", values, "dialpass");
            return (Criteria) this;
        }

        public Criteria andDialpassBetween(String value1, String value2) {
            addCriterion("dialpass between", value1, value2, "dialpass");
            return (Criteria) this;
        }

        public Criteria andDialpassNotBetween(String value1, String value2) {
            addCriterion("dialpass not between", value1, value2, "dialpass");
            return (Criteria) this;
        }

        public Criteria andAdminidIsNull() {
            addCriterion("adminid is null");
            return (Criteria) this;
        }

        public Criteria andAdminidIsNotNull() {
            addCriterion("adminid is not null");
            return (Criteria) this;
        }

        public Criteria andAdminidEqualTo(Integer value) {
            addCriterion("adminid =", value, "adminid");
            return (Criteria) this;
        }

        public Criteria andAdminidNotEqualTo(Integer value) {
            addCriterion("adminid <>", value, "adminid");
            return (Criteria) this;
        }

        public Criteria andAdminidGreaterThan(Integer value) {
            addCriterion("adminid >", value, "adminid");
            return (Criteria) this;
        }

        public Criteria andAdminidGreaterThanOrEqualTo(Integer value) {
            addCriterion("adminid >=", value, "adminid");
            return (Criteria) this;
        }

        public Criteria andAdminidLessThan(Integer value) {
            addCriterion("adminid <", value, "adminid");
            return (Criteria) this;
        }

        public Criteria andAdminidLessThanOrEqualTo(Integer value) {
            addCriterion("adminid <=", value, "adminid");
            return (Criteria) this;
        }

        public Criteria andAdminidIn(List<Integer> values) {
            addCriterion("adminid in", values, "adminid");
            return (Criteria) this;
        }

        public Criteria andAdminidNotIn(List<Integer> values) {
            addCriterion("adminid not in", values, "adminid");
            return (Criteria) this;
        }

        public Criteria andAdminidBetween(Integer value1, Integer value2) {
            addCriterion("adminid between", value1, value2, "adminid");
            return (Criteria) this;
        }

        public Criteria andAdminidNotBetween(Integer value1, Integer value2) {
            addCriterion("adminid not between", value1, value2, "adminid");
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