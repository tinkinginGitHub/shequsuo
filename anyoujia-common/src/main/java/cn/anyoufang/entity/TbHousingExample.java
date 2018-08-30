package cn.anyoufang.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbHousingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbHousingExample() {
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

        public Criteria andAreaIdIsNull() {
            addCriterion("area_id is null");
            return (Criteria) this;
        }

        public Criteria andAreaIdIsNotNull() {
            addCriterion("area_id is not null");
            return (Criteria) this;
        }

        public Criteria andAreaIdEqualTo(String value) {
            addCriterion("area_id =", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotEqualTo(String value) {
            addCriterion("area_id <>", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdGreaterThan(String value) {
            addCriterion("area_id >", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdGreaterThanOrEqualTo(String value) {
            addCriterion("area_id >=", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLessThan(String value) {
            addCriterion("area_id <", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLessThanOrEqualTo(String value) {
            addCriterion("area_id <=", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLike(String value) {
            addCriterion("area_id like", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotLike(String value) {
            addCriterion("area_id not like", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdIn(List<String> values) {
            addCriterion("area_id in", values, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotIn(List<String> values) {
            addCriterion("area_id not in", values, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdBetween(String value1, String value2) {
            addCriterion("area_id between", value1, value2, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotBetween(String value1, String value2) {
            addCriterion("area_id not between", value1, value2, "areaId");
            return (Criteria) this;
        }

        public Criteria andApartmentIdIsNull() {
            addCriterion("apartment_id is null");
            return (Criteria) this;
        }

        public Criteria andApartmentIdIsNotNull() {
            addCriterion("apartment_id is not null");
            return (Criteria) this;
        }

        public Criteria andApartmentIdEqualTo(String value) {
            addCriterion("apartment_id =", value, "apartmentId");
            return (Criteria) this;
        }

        public Criteria andApartmentIdNotEqualTo(String value) {
            addCriterion("apartment_id <>", value, "apartmentId");
            return (Criteria) this;
        }

        public Criteria andApartmentIdGreaterThan(String value) {
            addCriterion("apartment_id >", value, "apartmentId");
            return (Criteria) this;
        }

        public Criteria andApartmentIdGreaterThanOrEqualTo(String value) {
            addCriterion("apartment_id >=", value, "apartmentId");
            return (Criteria) this;
        }

        public Criteria andApartmentIdLessThan(String value) {
            addCriterion("apartment_id <", value, "apartmentId");
            return (Criteria) this;
        }

        public Criteria andApartmentIdLessThanOrEqualTo(String value) {
            addCriterion("apartment_id <=", value, "apartmentId");
            return (Criteria) this;
        }

        public Criteria andApartmentIdLike(String value) {
            addCriterion("apartment_id like", value, "apartmentId");
            return (Criteria) this;
        }

        public Criteria andApartmentIdNotLike(String value) {
            addCriterion("apartment_id not like", value, "apartmentId");
            return (Criteria) this;
        }

        public Criteria andApartmentIdIn(List<String> values) {
            addCriterion("apartment_id in", values, "apartmentId");
            return (Criteria) this;
        }

        public Criteria andApartmentIdNotIn(List<String> values) {
            addCriterion("apartment_id not in", values, "apartmentId");
            return (Criteria) this;
        }

        public Criteria andApartmentIdBetween(String value1, String value2) {
            addCriterion("apartment_id between", value1, value2, "apartmentId");
            return (Criteria) this;
        }

        public Criteria andApartmentIdNotBetween(String value1, String value2) {
            addCriterion("apartment_id not between", value1, value2, "apartmentId");
            return (Criteria) this;
        }

        public Criteria andVoucherIsNull() {
            addCriterion("voucher is null");
            return (Criteria) this;
        }

        public Criteria andVoucherIsNotNull() {
            addCriterion("voucher is not null");
            return (Criteria) this;
        }

        public Criteria andVoucherEqualTo(Byte value) {
            addCriterion("voucher =", value, "voucher");
            return (Criteria) this;
        }

        public Criteria andVoucherNotEqualTo(Byte value) {
            addCriterion("voucher <>", value, "voucher");
            return (Criteria) this;
        }

        public Criteria andVoucherGreaterThan(Byte value) {
            addCriterion("voucher >", value, "voucher");
            return (Criteria) this;
        }

        public Criteria andVoucherGreaterThanOrEqualTo(Byte value) {
            addCriterion("voucher >=", value, "voucher");
            return (Criteria) this;
        }

        public Criteria andVoucherLessThan(Byte value) {
            addCriterion("voucher <", value, "voucher");
            return (Criteria) this;
        }

        public Criteria andVoucherLessThanOrEqualTo(Byte value) {
            addCriterion("voucher <=", value, "voucher");
            return (Criteria) this;
        }

        public Criteria andVoucherIn(List<Byte> values) {
            addCriterion("voucher in", values, "voucher");
            return (Criteria) this;
        }

        public Criteria andVoucherNotIn(List<Byte> values) {
            addCriterion("voucher not in", values, "voucher");
            return (Criteria) this;
        }

        public Criteria andVoucherBetween(Byte value1, Byte value2) {
            addCriterion("voucher between", value1, value2, "voucher");
            return (Criteria) this;
        }

        public Criteria andVoucherNotBetween(Byte value1, Byte value2) {
            addCriterion("voucher not between", value1, value2, "voucher");
            return (Criteria) this;
        }

        public Criteria andSpecialIsNull() {
            addCriterion("special is null");
            return (Criteria) this;
        }

        public Criteria andSpecialIsNotNull() {
            addCriterion("special is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialEqualTo(String value) {
            addCriterion("special =", value, "special");
            return (Criteria) this;
        }

        public Criteria andSpecialNotEqualTo(String value) {
            addCriterion("special <>", value, "special");
            return (Criteria) this;
        }

        public Criteria andSpecialGreaterThan(String value) {
            addCriterion("special >", value, "special");
            return (Criteria) this;
        }

        public Criteria andSpecialGreaterThanOrEqualTo(String value) {
            addCriterion("special >=", value, "special");
            return (Criteria) this;
        }

        public Criteria andSpecialLessThan(String value) {
            addCriterion("special <", value, "special");
            return (Criteria) this;
        }

        public Criteria andSpecialLessThanOrEqualTo(String value) {
            addCriterion("special <=", value, "special");
            return (Criteria) this;
        }

        public Criteria andSpecialLike(String value) {
            addCriterion("special like", value, "special");
            return (Criteria) this;
        }

        public Criteria andSpecialNotLike(String value) {
            addCriterion("special not like", value, "special");
            return (Criteria) this;
        }

        public Criteria andSpecialIn(List<String> values) {
            addCriterion("special in", values, "special");
            return (Criteria) this;
        }

        public Criteria andSpecialNotIn(List<String> values) {
            addCriterion("special not in", values, "special");
            return (Criteria) this;
        }

        public Criteria andSpecialBetween(String value1, String value2) {
            addCriterion("special between", value1, value2, "special");
            return (Criteria) this;
        }

        public Criteria andSpecialNotBetween(String value1, String value2) {
            addCriterion("special not between", value1, value2, "special");
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

        public Criteria andPicInfoIsNull() {
            addCriterion("pic_info is null");
            return (Criteria) this;
        }

        public Criteria andPicInfoIsNotNull() {
            addCriterion("pic_info is not null");
            return (Criteria) this;
        }

        public Criteria andPicInfoEqualTo(String value) {
            addCriterion("pic_info =", value, "picInfo");
            return (Criteria) this;
        }

        public Criteria andPicInfoNotEqualTo(String value) {
            addCriterion("pic_info <>", value, "picInfo");
            return (Criteria) this;
        }

        public Criteria andPicInfoGreaterThan(String value) {
            addCriterion("pic_info >", value, "picInfo");
            return (Criteria) this;
        }

        public Criteria andPicInfoGreaterThanOrEqualTo(String value) {
            addCriterion("pic_info >=", value, "picInfo");
            return (Criteria) this;
        }

        public Criteria andPicInfoLessThan(String value) {
            addCriterion("pic_info <", value, "picInfo");
            return (Criteria) this;
        }

        public Criteria andPicInfoLessThanOrEqualTo(String value) {
            addCriterion("pic_info <=", value, "picInfo");
            return (Criteria) this;
        }

        public Criteria andPicInfoLike(String value) {
            addCriterion("pic_info like", value, "picInfo");
            return (Criteria) this;
        }

        public Criteria andPicInfoNotLike(String value) {
            addCriterion("pic_info not like", value, "picInfo");
            return (Criteria) this;
        }

        public Criteria andPicInfoIn(List<String> values) {
            addCriterion("pic_info in", values, "picInfo");
            return (Criteria) this;
        }

        public Criteria andPicInfoNotIn(List<String> values) {
            addCriterion("pic_info not in", values, "picInfo");
            return (Criteria) this;
        }

        public Criteria andPicInfoBetween(String value1, String value2) {
            addCriterion("pic_info between", value1, value2, "picInfo");
            return (Criteria) this;
        }

        public Criteria andPicInfoNotBetween(String value1, String value2) {
            addCriterion("pic_info not between", value1, value2, "picInfo");
            return (Criteria) this;
        }

        public Criteria andLocationIsNull() {
            addCriterion("location is null");
            return (Criteria) this;
        }

        public Criteria andLocationIsNotNull() {
            addCriterion("location is not null");
            return (Criteria) this;
        }

        public Criteria andLocationEqualTo(String value) {
            addCriterion("location =", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotEqualTo(String value) {
            addCriterion("location <>", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThan(String value) {
            addCriterion("location >", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThanOrEqualTo(String value) {
            addCriterion("location >=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThan(String value) {
            addCriterion("location <", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThanOrEqualTo(String value) {
            addCriterion("location <=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLike(String value) {
            addCriterion("location like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotLike(String value) {
            addCriterion("location not like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationIn(List<String> values) {
            addCriterion("location in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotIn(List<String> values) {
            addCriterion("location not in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationBetween(String value1, String value2) {
            addCriterion("location between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotBetween(String value1, String value2) {
            addCriterion("location not between", value1, value2, "location");
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

        public Criteria andFacilityIsNull() {
            addCriterion("facility is null");
            return (Criteria) this;
        }

        public Criteria andFacilityIsNotNull() {
            addCriterion("facility is not null");
            return (Criteria) this;
        }

        public Criteria andFacilityEqualTo(String value) {
            addCriterion("facility =", value, "facility");
            return (Criteria) this;
        }

        public Criteria andFacilityNotEqualTo(String value) {
            addCriterion("facility <>", value, "facility");
            return (Criteria) this;
        }

        public Criteria andFacilityGreaterThan(String value) {
            addCriterion("facility >", value, "facility");
            return (Criteria) this;
        }

        public Criteria andFacilityGreaterThanOrEqualTo(String value) {
            addCriterion("facility >=", value, "facility");
            return (Criteria) this;
        }

        public Criteria andFacilityLessThan(String value) {
            addCriterion("facility <", value, "facility");
            return (Criteria) this;
        }

        public Criteria andFacilityLessThanOrEqualTo(String value) {
            addCriterion("facility <=", value, "facility");
            return (Criteria) this;
        }

        public Criteria andFacilityLike(String value) {
            addCriterion("facility like", value, "facility");
            return (Criteria) this;
        }

        public Criteria andFacilityNotLike(String value) {
            addCriterion("facility not like", value, "facility");
            return (Criteria) this;
        }

        public Criteria andFacilityIn(List<String> values) {
            addCriterion("facility in", values, "facility");
            return (Criteria) this;
        }

        public Criteria andFacilityNotIn(List<String> values) {
            addCriterion("facility not in", values, "facility");
            return (Criteria) this;
        }

        public Criteria andFacilityBetween(String value1, String value2) {
            addCriterion("facility between", value1, value2, "facility");
            return (Criteria) this;
        }

        public Criteria andFacilityNotBetween(String value1, String value2) {
            addCriterion("facility not between", value1, value2, "facility");
            return (Criteria) this;
        }

        public Criteria andRentIsNull() {
            addCriterion("rent is null");
            return (Criteria) this;
        }

        public Criteria andRentIsNotNull() {
            addCriterion("rent is not null");
            return (Criteria) this;
        }

        public Criteria andRentEqualTo(String value) {
            addCriterion("rent =", value, "rent");
            return (Criteria) this;
        }

        public Criteria andRentNotEqualTo(String value) {
            addCriterion("rent <>", value, "rent");
            return (Criteria) this;
        }

        public Criteria andRentGreaterThan(String value) {
            addCriterion("rent >", value, "rent");
            return (Criteria) this;
        }

        public Criteria andRentGreaterThanOrEqualTo(String value) {
            addCriterion("rent >=", value, "rent");
            return (Criteria) this;
        }

        public Criteria andRentLessThan(String value) {
            addCriterion("rent <", value, "rent");
            return (Criteria) this;
        }

        public Criteria andRentLessThanOrEqualTo(String value) {
            addCriterion("rent <=", value, "rent");
            return (Criteria) this;
        }

        public Criteria andRentLike(String value) {
            addCriterion("rent like", value, "rent");
            return (Criteria) this;
        }

        public Criteria andRentNotLike(String value) {
            addCriterion("rent not like", value, "rent");
            return (Criteria) this;
        }

        public Criteria andRentIn(List<String> values) {
            addCriterion("rent in", values, "rent");
            return (Criteria) this;
        }

        public Criteria andRentNotIn(List<String> values) {
            addCriterion("rent not in", values, "rent");
            return (Criteria) this;
        }

        public Criteria andRentBetween(String value1, String value2) {
            addCriterion("rent between", value1, value2, "rent");
            return (Criteria) this;
        }

        public Criteria andRentNotBetween(String value1, String value2) {
            addCriterion("rent not between", value1, value2, "rent");
            return (Criteria) this;
        }

        public Criteria andBookIdIsNull() {
            addCriterion("book_id is null");
            return (Criteria) this;
        }

        public Criteria andBookIdIsNotNull() {
            addCriterion("book_id is not null");
            return (Criteria) this;
        }

        public Criteria andBookIdEqualTo(String value) {
            addCriterion("book_id =", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotEqualTo(String value) {
            addCriterion("book_id <>", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdGreaterThan(String value) {
            addCriterion("book_id >", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdGreaterThanOrEqualTo(String value) {
            addCriterion("book_id >=", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdLessThan(String value) {
            addCriterion("book_id <", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdLessThanOrEqualTo(String value) {
            addCriterion("book_id <=", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdLike(String value) {
            addCriterion("book_id like", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotLike(String value) {
            addCriterion("book_id not like", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdIn(List<String> values) {
            addCriterion("book_id in", values, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotIn(List<String> values) {
            addCriterion("book_id not in", values, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdBetween(String value1, String value2) {
            addCriterion("book_id between", value1, value2, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotBetween(String value1, String value2) {
            addCriterion("book_id not between", value1, value2, "bookId");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNull() {
            addCriterion("money is null");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNotNull() {
            addCriterion("money is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyEqualTo(Integer value) {
            addCriterion("money =", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotEqualTo(Integer value) {
            addCriterion("money <>", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThan(Integer value) {
            addCriterion("money >", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("money >=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThan(Integer value) {
            addCriterion("money <", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("money <=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyIn(List<Integer> values) {
            addCriterion("money in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotIn(List<Integer> values) {
            addCriterion("money not in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyBetween(Integer value1, Integer value2) {
            addCriterion("money between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("money not between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andExpectedTimeIsNull() {
            addCriterion("expected_time is null");
            return (Criteria) this;
        }

        public Criteria andExpectedTimeIsNotNull() {
            addCriterion("expected_time is not null");
            return (Criteria) this;
        }

        public Criteria andExpectedTimeEqualTo(Date value) {
            addCriterion("expected_time =", value, "expectedTime");
            return (Criteria) this;
        }

        public Criteria andExpectedTimeNotEqualTo(Date value) {
            addCriterion("expected_time <>", value, "expectedTime");
            return (Criteria) this;
        }

        public Criteria andExpectedTimeGreaterThan(Date value) {
            addCriterion("expected_time >", value, "expectedTime");
            return (Criteria) this;
        }

        public Criteria andExpectedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("expected_time >=", value, "expectedTime");
            return (Criteria) this;
        }

        public Criteria andExpectedTimeLessThan(Date value) {
            addCriterion("expected_time <", value, "expectedTime");
            return (Criteria) this;
        }

        public Criteria andExpectedTimeLessThanOrEqualTo(Date value) {
            addCriterion("expected_time <=", value, "expectedTime");
            return (Criteria) this;
        }

        public Criteria andExpectedTimeIn(List<Date> values) {
            addCriterion("expected_time in", values, "expectedTime");
            return (Criteria) this;
        }

        public Criteria andExpectedTimeNotIn(List<Date> values) {
            addCriterion("expected_time not in", values, "expectedTime");
            return (Criteria) this;
        }

        public Criteria andExpectedTimeBetween(Date value1, Date value2) {
            addCriterion("expected_time between", value1, value2, "expectedTime");
            return (Criteria) this;
        }

        public Criteria andExpectedTimeNotBetween(Date value1, Date value2) {
            addCriterion("expected_time not between", value1, value2, "expectedTime");
            return (Criteria) this;
        }

        public Criteria andSuperiorIsNull() {
            addCriterion("superior is null");
            return (Criteria) this;
        }

        public Criteria andSuperiorIsNotNull() {
            addCriterion("superior is not null");
            return (Criteria) this;
        }

        public Criteria andSuperiorEqualTo(Byte value) {
            addCriterion("superior =", value, "superior");
            return (Criteria) this;
        }

        public Criteria andSuperiorNotEqualTo(Byte value) {
            addCriterion("superior <>", value, "superior");
            return (Criteria) this;
        }

        public Criteria andSuperiorGreaterThan(Byte value) {
            addCriterion("superior >", value, "superior");
            return (Criteria) this;
        }

        public Criteria andSuperiorGreaterThanOrEqualTo(Byte value) {
            addCriterion("superior >=", value, "superior");
            return (Criteria) this;
        }

        public Criteria andSuperiorLessThan(Byte value) {
            addCriterion("superior <", value, "superior");
            return (Criteria) this;
        }

        public Criteria andSuperiorLessThanOrEqualTo(Byte value) {
            addCriterion("superior <=", value, "superior");
            return (Criteria) this;
        }

        public Criteria andSuperiorIn(List<Byte> values) {
            addCriterion("superior in", values, "superior");
            return (Criteria) this;
        }

        public Criteria andSuperiorNotIn(List<Byte> values) {
            addCriterion("superior not in", values, "superior");
            return (Criteria) this;
        }

        public Criteria andSuperiorBetween(Byte value1, Byte value2) {
            addCriterion("superior between", value1, value2, "superior");
            return (Criteria) this;
        }

        public Criteria andSuperiorNotBetween(Byte value1, Byte value2) {
            addCriterion("superior not between", value1, value2, "superior");
            return (Criteria) this;
        }

        public Criteria andHouseTypeIsNull() {
            addCriterion("house_type is null");
            return (Criteria) this;
        }

        public Criteria andHouseTypeIsNotNull() {
            addCriterion("house_type is not null");
            return (Criteria) this;
        }

        public Criteria andHouseTypeEqualTo(String value) {
            addCriterion("house_type =", value, "houseType");
            return (Criteria) this;
        }

        public Criteria andHouseTypeNotEqualTo(String value) {
            addCriterion("house_type <>", value, "houseType");
            return (Criteria) this;
        }

        public Criteria andHouseTypeGreaterThan(String value) {
            addCriterion("house_type >", value, "houseType");
            return (Criteria) this;
        }

        public Criteria andHouseTypeGreaterThanOrEqualTo(String value) {
            addCriterion("house_type >=", value, "houseType");
            return (Criteria) this;
        }

        public Criteria andHouseTypeLessThan(String value) {
            addCriterion("house_type <", value, "houseType");
            return (Criteria) this;
        }

        public Criteria andHouseTypeLessThanOrEqualTo(String value) {
            addCriterion("house_type <=", value, "houseType");
            return (Criteria) this;
        }

        public Criteria andHouseTypeLike(String value) {
            addCriterion("house_type like", value, "houseType");
            return (Criteria) this;
        }

        public Criteria andHouseTypeNotLike(String value) {
            addCriterion("house_type not like", value, "houseType");
            return (Criteria) this;
        }

        public Criteria andHouseTypeIn(List<String> values) {
            addCriterion("house_type in", values, "houseType");
            return (Criteria) this;
        }

        public Criteria andHouseTypeNotIn(List<String> values) {
            addCriterion("house_type not in", values, "houseType");
            return (Criteria) this;
        }

        public Criteria andHouseTypeBetween(String value1, String value2) {
            addCriterion("house_type between", value1, value2, "houseType");
            return (Criteria) this;
        }

        public Criteria andHouseTypeNotBetween(String value1, String value2) {
            addCriterion("house_type not between", value1, value2, "houseType");
            return (Criteria) this;
        }

        public Criteria andHallRoomIsNull() {
            addCriterion("hall_room is null");
            return (Criteria) this;
        }

        public Criteria andHallRoomIsNotNull() {
            addCriterion("hall_room is not null");
            return (Criteria) this;
        }

        public Criteria andHallRoomEqualTo(String value) {
            addCriterion("hall_room =", value, "hallRoom");
            return (Criteria) this;
        }

        public Criteria andHallRoomNotEqualTo(String value) {
            addCriterion("hall_room <>", value, "hallRoom");
            return (Criteria) this;
        }

        public Criteria andHallRoomGreaterThan(String value) {
            addCriterion("hall_room >", value, "hallRoom");
            return (Criteria) this;
        }

        public Criteria andHallRoomGreaterThanOrEqualTo(String value) {
            addCriterion("hall_room >=", value, "hallRoom");
            return (Criteria) this;
        }

        public Criteria andHallRoomLessThan(String value) {
            addCriterion("hall_room <", value, "hallRoom");
            return (Criteria) this;
        }

        public Criteria andHallRoomLessThanOrEqualTo(String value) {
            addCriterion("hall_room <=", value, "hallRoom");
            return (Criteria) this;
        }

        public Criteria andHallRoomLike(String value) {
            addCriterion("hall_room like", value, "hallRoom");
            return (Criteria) this;
        }

        public Criteria andHallRoomNotLike(String value) {
            addCriterion("hall_room not like", value, "hallRoom");
            return (Criteria) this;
        }

        public Criteria andHallRoomIn(List<String> values) {
            addCriterion("hall_room in", values, "hallRoom");
            return (Criteria) this;
        }

        public Criteria andHallRoomNotIn(List<String> values) {
            addCriterion("hall_room not in", values, "hallRoom");
            return (Criteria) this;
        }

        public Criteria andHallRoomBetween(String value1, String value2) {
            addCriterion("hall_room between", value1, value2, "hallRoom");
            return (Criteria) this;
        }

        public Criteria andHallRoomNotBetween(String value1, String value2) {
            addCriterion("hall_room not between", value1, value2, "hallRoom");
            return (Criteria) this;
        }

        public Criteria andRoomsIsNull() {
            addCriterion("rooms is null");
            return (Criteria) this;
        }

        public Criteria andRoomsIsNotNull() {
            addCriterion("rooms is not null");
            return (Criteria) this;
        }

        public Criteria andRoomsEqualTo(String value) {
            addCriterion("rooms =", value, "rooms");
            return (Criteria) this;
        }

        public Criteria andRoomsNotEqualTo(String value) {
            addCriterion("rooms <>", value, "rooms");
            return (Criteria) this;
        }

        public Criteria andRoomsGreaterThan(String value) {
            addCriterion("rooms >", value, "rooms");
            return (Criteria) this;
        }

        public Criteria andRoomsGreaterThanOrEqualTo(String value) {
            addCriterion("rooms >=", value, "rooms");
            return (Criteria) this;
        }

        public Criteria andRoomsLessThan(String value) {
            addCriterion("rooms <", value, "rooms");
            return (Criteria) this;
        }

        public Criteria andRoomsLessThanOrEqualTo(String value) {
            addCriterion("rooms <=", value, "rooms");
            return (Criteria) this;
        }

        public Criteria andRoomsLike(String value) {
            addCriterion("rooms like", value, "rooms");
            return (Criteria) this;
        }

        public Criteria andRoomsNotLike(String value) {
            addCriterion("rooms not like", value, "rooms");
            return (Criteria) this;
        }

        public Criteria andRoomsIn(List<String> values) {
            addCriterion("rooms in", values, "rooms");
            return (Criteria) this;
        }

        public Criteria andRoomsNotIn(List<String> values) {
            addCriterion("rooms not in", values, "rooms");
            return (Criteria) this;
        }

        public Criteria andRoomsBetween(String value1, String value2) {
            addCriterion("rooms between", value1, value2, "rooms");
            return (Criteria) this;
        }

        public Criteria andRoomsNotBetween(String value1, String value2) {
            addCriterion("rooms not between", value1, value2, "rooms");
            return (Criteria) this;
        }

        public Criteria andPaymentIdIsNull() {
            addCriterion("payment_id is null");
            return (Criteria) this;
        }

        public Criteria andPaymentIdIsNotNull() {
            addCriterion("payment_id is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentIdEqualTo(String value) {
            addCriterion("payment_id =", value, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdNotEqualTo(String value) {
            addCriterion("payment_id <>", value, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdGreaterThan(String value) {
            addCriterion("payment_id >", value, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdGreaterThanOrEqualTo(String value) {
            addCriterion("payment_id >=", value, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdLessThan(String value) {
            addCriterion("payment_id <", value, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdLessThanOrEqualTo(String value) {
            addCriterion("payment_id <=", value, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdLike(String value) {
            addCriterion("payment_id like", value, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdNotLike(String value) {
            addCriterion("payment_id not like", value, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdIn(List<String> values) {
            addCriterion("payment_id in", values, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdNotIn(List<String> values) {
            addCriterion("payment_id not in", values, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdBetween(String value1, String value2) {
            addCriterion("payment_id between", value1, value2, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdNotBetween(String value1, String value2) {
            addCriterion("payment_id not between", value1, value2, "paymentId");
            return (Criteria) this;
        }

        public Criteria andAcreageIsNull() {
            addCriterion("acreage is null");
            return (Criteria) this;
        }

        public Criteria andAcreageIsNotNull() {
            addCriterion("acreage is not null");
            return (Criteria) this;
        }

        public Criteria andAcreageEqualTo(Double value) {
            addCriterion("acreage =", value, "acreage");
            return (Criteria) this;
        }

        public Criteria andAcreageNotEqualTo(Double value) {
            addCriterion("acreage <>", value, "acreage");
            return (Criteria) this;
        }

        public Criteria andAcreageGreaterThan(Double value) {
            addCriterion("acreage >", value, "acreage");
            return (Criteria) this;
        }

        public Criteria andAcreageGreaterThanOrEqualTo(Double value) {
            addCriterion("acreage >=", value, "acreage");
            return (Criteria) this;
        }

        public Criteria andAcreageLessThan(Double value) {
            addCriterion("acreage <", value, "acreage");
            return (Criteria) this;
        }

        public Criteria andAcreageLessThanOrEqualTo(Double value) {
            addCriterion("acreage <=", value, "acreage");
            return (Criteria) this;
        }

        public Criteria andAcreageIn(List<Double> values) {
            addCriterion("acreage in", values, "acreage");
            return (Criteria) this;
        }

        public Criteria andAcreageNotIn(List<Double> values) {
            addCriterion("acreage not in", values, "acreage");
            return (Criteria) this;
        }

        public Criteria andAcreageBetween(Double value1, Double value2) {
            addCriterion("acreage between", value1, value2, "acreage");
            return (Criteria) this;
        }

        public Criteria andAcreageNotBetween(Double value1, Double value2) {
            addCriterion("acreage not between", value1, value2, "acreage");
            return (Criteria) this;
        }

        public Criteria andSubwayLineIsNull() {
            addCriterion("subway_line is null");
            return (Criteria) this;
        }

        public Criteria andSubwayLineIsNotNull() {
            addCriterion("subway_line is not null");
            return (Criteria) this;
        }

        public Criteria andSubwayLineEqualTo(String value) {
            addCriterion("subway_line =", value, "subwayLine");
            return (Criteria) this;
        }

        public Criteria andSubwayLineNotEqualTo(String value) {
            addCriterion("subway_line <>", value, "subwayLine");
            return (Criteria) this;
        }

        public Criteria andSubwayLineGreaterThan(String value) {
            addCriterion("subway_line >", value, "subwayLine");
            return (Criteria) this;
        }

        public Criteria andSubwayLineGreaterThanOrEqualTo(String value) {
            addCriterion("subway_line >=", value, "subwayLine");
            return (Criteria) this;
        }

        public Criteria andSubwayLineLessThan(String value) {
            addCriterion("subway_line <", value, "subwayLine");
            return (Criteria) this;
        }

        public Criteria andSubwayLineLessThanOrEqualTo(String value) {
            addCriterion("subway_line <=", value, "subwayLine");
            return (Criteria) this;
        }

        public Criteria andSubwayLineLike(String value) {
            addCriterion("subway_line like", value, "subwayLine");
            return (Criteria) this;
        }

        public Criteria andSubwayLineNotLike(String value) {
            addCriterion("subway_line not like", value, "subwayLine");
            return (Criteria) this;
        }

        public Criteria andSubwayLineIn(List<String> values) {
            addCriterion("subway_line in", values, "subwayLine");
            return (Criteria) this;
        }

        public Criteria andSubwayLineNotIn(List<String> values) {
            addCriterion("subway_line not in", values, "subwayLine");
            return (Criteria) this;
        }

        public Criteria andSubwayLineBetween(String value1, String value2) {
            addCriterion("subway_line between", value1, value2, "subwayLine");
            return (Criteria) this;
        }

        public Criteria andSubwayLineNotBetween(String value1, String value2) {
            addCriterion("subway_line not between", value1, value2, "subwayLine");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNull() {
            addCriterion("latitude is null");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNotNull() {
            addCriterion("latitude is not null");
            return (Criteria) this;
        }

        public Criteria andLatitudeEqualTo(Double value) {
            addCriterion("latitude =", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotEqualTo(Double value) {
            addCriterion("latitude <>", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThan(Double value) {
            addCriterion("latitude >", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("latitude >=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThan(Double value) {
            addCriterion("latitude <", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThanOrEqualTo(Double value) {
            addCriterion("latitude <=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIn(List<Double> values) {
            addCriterion("latitude in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotIn(List<Double> values) {
            addCriterion("latitude not in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeBetween(Double value1, Double value2) {
            addCriterion("latitude between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotBetween(Double value1, Double value2) {
            addCriterion("latitude not between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNull() {
            addCriterion("longitude is null");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNotNull() {
            addCriterion("longitude is not null");
            return (Criteria) this;
        }

        public Criteria andLongitudeEqualTo(Double value) {
            addCriterion("longitude =", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotEqualTo(Double value) {
            addCriterion("longitude <>", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThan(Double value) {
            addCriterion("longitude >", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("longitude >=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThan(Double value) {
            addCriterion("longitude <", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThanOrEqualTo(Double value) {
            addCriterion("longitude <=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIn(List<Double> values) {
            addCriterion("longitude in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotIn(List<Double> values) {
            addCriterion("longitude not in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeBetween(Double value1, Double value2) {
            addCriterion("longitude between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotBetween(Double value1, Double value2) {
            addCriterion("longitude not between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andStoreyIsNull() {
            addCriterion("storey is null");
            return (Criteria) this;
        }

        public Criteria andStoreyIsNotNull() {
            addCriterion("storey is not null");
            return (Criteria) this;
        }

        public Criteria andStoreyEqualTo(String value) {
            addCriterion("storey =", value, "storey");
            return (Criteria) this;
        }

        public Criteria andStoreyNotEqualTo(String value) {
            addCriterion("storey <>", value, "storey");
            return (Criteria) this;
        }

        public Criteria andStoreyGreaterThan(String value) {
            addCriterion("storey >", value, "storey");
            return (Criteria) this;
        }

        public Criteria andStoreyGreaterThanOrEqualTo(String value) {
            addCriterion("storey >=", value, "storey");
            return (Criteria) this;
        }

        public Criteria andStoreyLessThan(String value) {
            addCriterion("storey <", value, "storey");
            return (Criteria) this;
        }

        public Criteria andStoreyLessThanOrEqualTo(String value) {
            addCriterion("storey <=", value, "storey");
            return (Criteria) this;
        }

        public Criteria andStoreyLike(String value) {
            addCriterion("storey like", value, "storey");
            return (Criteria) this;
        }

        public Criteria andStoreyNotLike(String value) {
            addCriterion("storey not like", value, "storey");
            return (Criteria) this;
        }

        public Criteria andStoreyIn(List<String> values) {
            addCriterion("storey in", values, "storey");
            return (Criteria) this;
        }

        public Criteria andStoreyNotIn(List<String> values) {
            addCriterion("storey not in", values, "storey");
            return (Criteria) this;
        }

        public Criteria andStoreyBetween(String value1, String value2) {
            addCriterion("storey between", value1, value2, "storey");
            return (Criteria) this;
        }

        public Criteria andStoreyNotBetween(String value1, String value2) {
            addCriterion("storey not between", value1, value2, "storey");
            return (Criteria) this;
        }

        public Criteria andOrientationsIsNull() {
            addCriterion("orientations is null");
            return (Criteria) this;
        }

        public Criteria andOrientationsIsNotNull() {
            addCriterion("orientations is not null");
            return (Criteria) this;
        }

        public Criteria andOrientationsEqualTo(String value) {
            addCriterion("orientations =", value, "orientations");
            return (Criteria) this;
        }

        public Criteria andOrientationsNotEqualTo(String value) {
            addCriterion("orientations <>", value, "orientations");
            return (Criteria) this;
        }

        public Criteria andOrientationsGreaterThan(String value) {
            addCriterion("orientations >", value, "orientations");
            return (Criteria) this;
        }

        public Criteria andOrientationsGreaterThanOrEqualTo(String value) {
            addCriterion("orientations >=", value, "orientations");
            return (Criteria) this;
        }

        public Criteria andOrientationsLessThan(String value) {
            addCriterion("orientations <", value, "orientations");
            return (Criteria) this;
        }

        public Criteria andOrientationsLessThanOrEqualTo(String value) {
            addCriterion("orientations <=", value, "orientations");
            return (Criteria) this;
        }

        public Criteria andOrientationsLike(String value) {
            addCriterion("orientations like", value, "orientations");
            return (Criteria) this;
        }

        public Criteria andOrientationsNotLike(String value) {
            addCriterion("orientations not like", value, "orientations");
            return (Criteria) this;
        }

        public Criteria andOrientationsIn(List<String> values) {
            addCriterion("orientations in", values, "orientations");
            return (Criteria) this;
        }

        public Criteria andOrientationsNotIn(List<String> values) {
            addCriterion("orientations not in", values, "orientations");
            return (Criteria) this;
        }

        public Criteria andOrientationsBetween(String value1, String value2) {
            addCriterion("orientations between", value1, value2, "orientations");
            return (Criteria) this;
        }

        public Criteria andOrientationsNotBetween(String value1, String value2) {
            addCriterion("orientations not between", value1, value2, "orientations");
            return (Criteria) this;
        }

        public Criteria andHousekeeperidIsNull() {
            addCriterion("housekeeperid is null");
            return (Criteria) this;
        }

        public Criteria andHousekeeperidIsNotNull() {
            addCriterion("housekeeperid is not null");
            return (Criteria) this;
        }

        public Criteria andHousekeeperidEqualTo(String value) {
            addCriterion("housekeeperid =", value, "housekeeperid");
            return (Criteria) this;
        }

        public Criteria andHousekeeperidNotEqualTo(String value) {
            addCriterion("housekeeperid <>", value, "housekeeperid");
            return (Criteria) this;
        }

        public Criteria andHousekeeperidGreaterThan(String value) {
            addCriterion("housekeeperid >", value, "housekeeperid");
            return (Criteria) this;
        }

        public Criteria andHousekeeperidGreaterThanOrEqualTo(String value) {
            addCriterion("housekeeperid >=", value, "housekeeperid");
            return (Criteria) this;
        }

        public Criteria andHousekeeperidLessThan(String value) {
            addCriterion("housekeeperid <", value, "housekeeperid");
            return (Criteria) this;
        }

        public Criteria andHousekeeperidLessThanOrEqualTo(String value) {
            addCriterion("housekeeperid <=", value, "housekeeperid");
            return (Criteria) this;
        }

        public Criteria andHousekeeperidLike(String value) {
            addCriterion("housekeeperid like", value, "housekeeperid");
            return (Criteria) this;
        }

        public Criteria andHousekeeperidNotLike(String value) {
            addCriterion("housekeeperid not like", value, "housekeeperid");
            return (Criteria) this;
        }

        public Criteria andHousekeeperidIn(List<String> values) {
            addCriterion("housekeeperid in", values, "housekeeperid");
            return (Criteria) this;
        }

        public Criteria andHousekeeperidNotIn(List<String> values) {
            addCriterion("housekeeperid not in", values, "housekeeperid");
            return (Criteria) this;
        }

        public Criteria andHousekeeperidBetween(String value1, String value2) {
            addCriterion("housekeeperid between", value1, value2, "housekeeperid");
            return (Criteria) this;
        }

        public Criteria andHousekeeperidNotBetween(String value1, String value2) {
            addCriterion("housekeeperid not between", value1, value2, "housekeeperid");
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