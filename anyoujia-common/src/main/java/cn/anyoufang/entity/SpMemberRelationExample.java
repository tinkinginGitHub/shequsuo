package cn.anyoufang.entity;

import java.util.ArrayList;
import java.util.List;

public class SpMemberRelationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SpMemberRelationExample() {
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

        public Criteria andParentidIsNull() {
            addCriterion("parentid is null");
            return (Criteria) this;
        }

        public Criteria andParentidIsNotNull() {
            addCriterion("parentid is not null");
            return (Criteria) this;
        }

        public Criteria andParentidEqualTo(Integer value) {
            addCriterion("parentid =", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotEqualTo(Integer value) {
            addCriterion("parentid <>", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidGreaterThan(Integer value) {
            addCriterion("parentid >", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidGreaterThanOrEqualTo(Integer value) {
            addCriterion("parentid >=", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLessThan(Integer value) {
            addCriterion("parentid <", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLessThanOrEqualTo(Integer value) {
            addCriterion("parentid <=", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidIn(List<Integer> values) {
            addCriterion("parentid in", values, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotIn(List<Integer> values) {
            addCriterion("parentid not in", values, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidBetween(Integer value1, Integer value2) {
            addCriterion("parentid between", value1, value2, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotBetween(Integer value1, Integer value2) {
            addCriterion("parentid not between", value1, value2, "parentid");
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

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsertypeIsNull() {
            addCriterion("usertype is null");
            return (Criteria) this;
        }

        public Criteria andUsertypeIsNotNull() {
            addCriterion("usertype is not null");
            return (Criteria) this;
        }

        public Criteria andUsertypeEqualTo(String value) {
            addCriterion("usertype =", value, "usertype");
            return (Criteria) this;
        }

        public Criteria andUsertypeNotEqualTo(String value) {
            addCriterion("usertype <>", value, "usertype");
            return (Criteria) this;
        }

        public Criteria andUsertypeGreaterThan(String value) {
            addCriterion("usertype >", value, "usertype");
            return (Criteria) this;
        }

        public Criteria andUsertypeGreaterThanOrEqualTo(String value) {
            addCriterion("usertype >=", value, "usertype");
            return (Criteria) this;
        }

        public Criteria andUsertypeLessThan(String value) {
            addCriterion("usertype <", value, "usertype");
            return (Criteria) this;
        }

        public Criteria andUsertypeLessThanOrEqualTo(String value) {
            addCriterion("usertype <=", value, "usertype");
            return (Criteria) this;
        }

        public Criteria andUsertypeLike(String value) {
            addCriterion("usertype like", value, "usertype");
            return (Criteria) this;
        }

        public Criteria andUsertypeNotLike(String value) {
            addCriterion("usertype not like", value, "usertype");
            return (Criteria) this;
        }

        public Criteria andUsertypeIn(List<String> values) {
            addCriterion("usertype in", values, "usertype");
            return (Criteria) this;
        }

        public Criteria andUsertypeNotIn(List<String> values) {
            addCriterion("usertype not in", values, "usertype");
            return (Criteria) this;
        }

        public Criteria andUsertypeBetween(String value1, String value2) {
            addCriterion("usertype between", value1, value2, "usertype");
            return (Criteria) this;
        }

        public Criteria andUsertypeNotBetween(String value1, String value2) {
            addCriterion("usertype not between", value1, value2, "usertype");
            return (Criteria) this;
        }

        public Criteria andUserrelationIsNull() {
            addCriterion("userrelation is null");
            return (Criteria) this;
        }

        public Criteria andUserrelationIsNotNull() {
            addCriterion("userrelation is not null");
            return (Criteria) this;
        }

        public Criteria andUserrelationEqualTo(String value) {
            addCriterion("userrelation =", value, "userrelation");
            return (Criteria) this;
        }

        public Criteria andUserrelationNotEqualTo(String value) {
            addCriterion("userrelation <>", value, "userrelation");
            return (Criteria) this;
        }

        public Criteria andUserrelationGreaterThan(String value) {
            addCriterion("userrelation >", value, "userrelation");
            return (Criteria) this;
        }

        public Criteria andUserrelationGreaterThanOrEqualTo(String value) {
            addCriterion("userrelation >=", value, "userrelation");
            return (Criteria) this;
        }

        public Criteria andUserrelationLessThan(String value) {
            addCriterion("userrelation <", value, "userrelation");
            return (Criteria) this;
        }

        public Criteria andUserrelationLessThanOrEqualTo(String value) {
            addCriterion("userrelation <=", value, "userrelation");
            return (Criteria) this;
        }

        public Criteria andUserrelationLike(String value) {
            addCriterion("userrelation like", value, "userrelation");
            return (Criteria) this;
        }

        public Criteria andUserrelationNotLike(String value) {
            addCriterion("userrelation not like", value, "userrelation");
            return (Criteria) this;
        }

        public Criteria andUserrelationIn(List<String> values) {
            addCriterion("userrelation in", values, "userrelation");
            return (Criteria) this;
        }

        public Criteria andUserrelationNotIn(List<String> values) {
            addCriterion("userrelation not in", values, "userrelation");
            return (Criteria) this;
        }

        public Criteria andUserrelationBetween(String value1, String value2) {
            addCriterion("userrelation between", value1, value2, "userrelation");
            return (Criteria) this;
        }

        public Criteria andUserrelationNotBetween(String value1, String value2) {
            addCriterion("userrelation not between", value1, value2, "userrelation");
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

        public Criteria andStarttimeIsNull() {
            addCriterion("starttime is null");
            return (Criteria) this;
        }

        public Criteria andStarttimeIsNotNull() {
            addCriterion("starttime is not null");
            return (Criteria) this;
        }

        public Criteria andStarttimeEqualTo(Integer value) {
            addCriterion("starttime =", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotEqualTo(Integer value) {
            addCriterion("starttime <>", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeGreaterThan(Integer value) {
            addCriterion("starttime >", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("starttime >=", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLessThan(Integer value) {
            addCriterion("starttime <", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLessThanOrEqualTo(Integer value) {
            addCriterion("starttime <=", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeIn(List<Integer> values) {
            addCriterion("starttime in", values, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotIn(List<Integer> values) {
            addCriterion("starttime not in", values, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeBetween(Integer value1, Integer value2) {
            addCriterion("starttime between", value1, value2, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotBetween(Integer value1, Integer value2) {
            addCriterion("starttime not between", value1, value2, "starttime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNull() {
            addCriterion("endtime is null");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNotNull() {
            addCriterion("endtime is not null");
            return (Criteria) this;
        }

        public Criteria andEndtimeEqualTo(Integer value) {
            addCriterion("endtime =", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotEqualTo(Integer value) {
            addCriterion("endtime <>", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThan(Integer value) {
            addCriterion("endtime >", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("endtime >=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThan(Integer value) {
            addCriterion("endtime <", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThanOrEqualTo(Integer value) {
            addCriterion("endtime <=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIn(List<Integer> values) {
            addCriterion("endtime in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotIn(List<Integer> values) {
            addCriterion("endtime not in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeBetween(Integer value1, Integer value2) {
            addCriterion("endtime between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotBetween(Integer value1, Integer value2) {
            addCriterion("endtime not between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andSetedlockpwdIsNull() {
            addCriterion("setedlockpwd is null");
            return (Criteria) this;
        }

        public Criteria andSetedlockpwdIsNotNull() {
            addCriterion("setedlockpwd is not null");
            return (Criteria) this;
        }

        public Criteria andSetedlockpwdEqualTo(Boolean value) {
            addCriterion("setedlockpwd =", value, "setedlockpwd");
            return (Criteria) this;
        }

        public Criteria andSetedlockpwdNotEqualTo(Boolean value) {
            addCriterion("setedlockpwd <>", value, "setedlockpwd");
            return (Criteria) this;
        }

        public Criteria andSetedlockpwdGreaterThan(Boolean value) {
            addCriterion("setedlockpwd >", value, "setedlockpwd");
            return (Criteria) this;
        }

        public Criteria andSetedlockpwdGreaterThanOrEqualTo(Boolean value) {
            addCriterion("setedlockpwd >=", value, "setedlockpwd");
            return (Criteria) this;
        }

        public Criteria andSetedlockpwdLessThan(Boolean value) {
            addCriterion("setedlockpwd <", value, "setedlockpwd");
            return (Criteria) this;
        }

        public Criteria andSetedlockpwdLessThanOrEqualTo(Boolean value) {
            addCriterion("setedlockpwd <=", value, "setedlockpwd");
            return (Criteria) this;
        }

        public Criteria andSetedlockpwdIn(List<Boolean> values) {
            addCriterion("setedlockpwd in", values, "setedlockpwd");
            return (Criteria) this;
        }

        public Criteria andSetedlockpwdNotIn(List<Boolean> values) {
            addCriterion("setedlockpwd not in", values, "setedlockpwd");
            return (Criteria) this;
        }

        public Criteria andSetedlockpwdBetween(Boolean value1, Boolean value2) {
            addCriterion("setedlockpwd between", value1, value2, "setedlockpwd");
            return (Criteria) this;
        }

        public Criteria andSetedlockpwdNotBetween(Boolean value1, Boolean value2) {
            addCriterion("setedlockpwd not between", value1, value2, "setedlockpwd");
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

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updatetime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updatetime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Integer value) {
            addCriterion("updatetime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Integer value) {
            addCriterion("updatetime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Integer value) {
            addCriterion("updatetime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("updatetime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Integer value) {
            addCriterion("updatetime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Integer value) {
            addCriterion("updatetime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Integer> values) {
            addCriterion("updatetime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Integer> values) {
            addCriterion("updatetime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Integer value1, Integer value2) {
            addCriterion("updatetime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Integer value1, Integer value2) {
            addCriterion("updatetime not between", value1, value2, "updatetime");
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