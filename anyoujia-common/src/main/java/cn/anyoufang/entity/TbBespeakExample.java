package cn.anyoufang.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbBespeakExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbBespeakExample() {
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

        public Criteria andAccRenterIdIsNull() {
            addCriterion("acc_renter_id is null");
            return (Criteria) this;
        }

        public Criteria andAccRenterIdIsNotNull() {
            addCriterion("acc_renter_id is not null");
            return (Criteria) this;
        }

        public Criteria andAccRenterIdEqualTo(String value) {
            addCriterion("acc_renter_id =", value, "accRenterId");
            return (Criteria) this;
        }

        public Criteria andAccRenterIdNotEqualTo(String value) {
            addCriterion("acc_renter_id <>", value, "accRenterId");
            return (Criteria) this;
        }

        public Criteria andAccRenterIdGreaterThan(String value) {
            addCriterion("acc_renter_id >", value, "accRenterId");
            return (Criteria) this;
        }

        public Criteria andAccRenterIdGreaterThanOrEqualTo(String value) {
            addCriterion("acc_renter_id >=", value, "accRenterId");
            return (Criteria) this;
        }

        public Criteria andAccRenterIdLessThan(String value) {
            addCriterion("acc_renter_id <", value, "accRenterId");
            return (Criteria) this;
        }

        public Criteria andAccRenterIdLessThanOrEqualTo(String value) {
            addCriterion("acc_renter_id <=", value, "accRenterId");
            return (Criteria) this;
        }

        public Criteria andAccRenterIdLike(String value) {
            addCriterion("acc_renter_id like", value, "accRenterId");
            return (Criteria) this;
        }

        public Criteria andAccRenterIdNotLike(String value) {
            addCriterion("acc_renter_id not like", value, "accRenterId");
            return (Criteria) this;
        }

        public Criteria andAccRenterIdIn(List<String> values) {
            addCriterion("acc_renter_id in", values, "accRenterId");
            return (Criteria) this;
        }

        public Criteria andAccRenterIdNotIn(List<String> values) {
            addCriterion("acc_renter_id not in", values, "accRenterId");
            return (Criteria) this;
        }

        public Criteria andAccRenterIdBetween(String value1, String value2) {
            addCriterion("acc_renter_id between", value1, value2, "accRenterId");
            return (Criteria) this;
        }

        public Criteria andAccRenterIdNotBetween(String value1, String value2) {
            addCriterion("acc_renter_id not between", value1, value2, "accRenterId");
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

        public Criteria andUpdatedIsNull() {
            addCriterion("updated is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedIsNotNull() {
            addCriterion("updated is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedEqualTo(Date value) {
            addCriterion("updated =", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotEqualTo(Date value) {
            addCriterion("updated <>", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedGreaterThan(Date value) {
            addCriterion("updated >", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedGreaterThanOrEqualTo(Date value) {
            addCriterion("updated >=", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedLessThan(Date value) {
            addCriterion("updated <", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedLessThanOrEqualTo(Date value) {
            addCriterion("updated <=", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedIn(List<Date> values) {
            addCriterion("updated in", values, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotIn(List<Date> values) {
            addCriterion("updated not in", values, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedBetween(Date value1, Date value2) {
            addCriterion("updated between", value1, value2, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotBetween(Date value1, Date value2) {
            addCriterion("updated not between", value1, value2, "updated");
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

        public Criteria andBasicInfoIsNull() {
            addCriterion("basic_info is null");
            return (Criteria) this;
        }

        public Criteria andBasicInfoIsNotNull() {
            addCriterion("basic_info is not null");
            return (Criteria) this;
        }

        public Criteria andBasicInfoEqualTo(String value) {
            addCriterion("basic_info =", value, "basicInfo");
            return (Criteria) this;
        }

        public Criteria andBasicInfoNotEqualTo(String value) {
            addCriterion("basic_info <>", value, "basicInfo");
            return (Criteria) this;
        }

        public Criteria andBasicInfoGreaterThan(String value) {
            addCriterion("basic_info >", value, "basicInfo");
            return (Criteria) this;
        }

        public Criteria andBasicInfoGreaterThanOrEqualTo(String value) {
            addCriterion("basic_info >=", value, "basicInfo");
            return (Criteria) this;
        }

        public Criteria andBasicInfoLessThan(String value) {
            addCriterion("basic_info <", value, "basicInfo");
            return (Criteria) this;
        }

        public Criteria andBasicInfoLessThanOrEqualTo(String value) {
            addCriterion("basic_info <=", value, "basicInfo");
            return (Criteria) this;
        }

        public Criteria andBasicInfoLike(String value) {
            addCriterion("basic_info like", value, "basicInfo");
            return (Criteria) this;
        }

        public Criteria andBasicInfoNotLike(String value) {
            addCriterion("basic_info not like", value, "basicInfo");
            return (Criteria) this;
        }

        public Criteria andBasicInfoIn(List<String> values) {
            addCriterion("basic_info in", values, "basicInfo");
            return (Criteria) this;
        }

        public Criteria andBasicInfoNotIn(List<String> values) {
            addCriterion("basic_info not in", values, "basicInfo");
            return (Criteria) this;
        }

        public Criteria andBasicInfoBetween(String value1, String value2) {
            addCriterion("basic_info between", value1, value2, "basicInfo");
            return (Criteria) this;
        }

        public Criteria andBasicInfoNotBetween(String value1, String value2) {
            addCriterion("basic_info not between", value1, value2, "basicInfo");
            return (Criteria) this;
        }

        public Criteria andViewingTimeIsNull() {
            addCriterion("viewing_time is null");
            return (Criteria) this;
        }

        public Criteria andViewingTimeIsNotNull() {
            addCriterion("viewing_time is not null");
            return (Criteria) this;
        }

        public Criteria andViewingTimeEqualTo(Date value) {
            addCriterion("viewing_time =", value, "viewingTime");
            return (Criteria) this;
        }

        public Criteria andViewingTimeNotEqualTo(Date value) {
            addCriterion("viewing_time <>", value, "viewingTime");
            return (Criteria) this;
        }

        public Criteria andViewingTimeGreaterThan(Date value) {
            addCriterion("viewing_time >", value, "viewingTime");
            return (Criteria) this;
        }

        public Criteria andViewingTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("viewing_time >=", value, "viewingTime");
            return (Criteria) this;
        }

        public Criteria andViewingTimeLessThan(Date value) {
            addCriterion("viewing_time <", value, "viewingTime");
            return (Criteria) this;
        }

        public Criteria andViewingTimeLessThanOrEqualTo(Date value) {
            addCriterion("viewing_time <=", value, "viewingTime");
            return (Criteria) this;
        }

        public Criteria andViewingTimeIn(List<Date> values) {
            addCriterion("viewing_time in", values, "viewingTime");
            return (Criteria) this;
        }

        public Criteria andViewingTimeNotIn(List<Date> values) {
            addCriterion("viewing_time not in", values, "viewingTime");
            return (Criteria) this;
        }

        public Criteria andViewingTimeBetween(Date value1, Date value2) {
            addCriterion("viewing_time between", value1, value2, "viewingTime");
            return (Criteria) this;
        }

        public Criteria andViewingTimeNotBetween(Date value1, Date value2) {
            addCriterion("viewing_time not between", value1, value2, "viewingTime");
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

        public Criteria andGenderIsNull() {
            addCriterion("gender is null");
            return (Criteria) this;
        }

        public Criteria andGenderIsNotNull() {
            addCriterion("gender is not null");
            return (Criteria) this;
        }

        public Criteria andGenderEqualTo(String value) {
            addCriterion("gender =", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotEqualTo(String value) {
            addCriterion("gender <>", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThan(String value) {
            addCriterion("gender >", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThanOrEqualTo(String value) {
            addCriterion("gender >=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThan(String value) {
            addCriterion("gender <", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThanOrEqualTo(String value) {
            addCriterion("gender <=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLike(String value) {
            addCriterion("gender like", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotLike(String value) {
            addCriterion("gender not like", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderIn(List<String> values) {
            addCriterion("gender in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotIn(List<String> values) {
            addCriterion("gender not in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderBetween(String value1, String value2) {
            addCriterion("gender between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotBetween(String value1, String value2) {
            addCriterion("gender not between", value1, value2, "gender");
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