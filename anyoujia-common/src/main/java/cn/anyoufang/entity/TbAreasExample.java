package cn.anyoufang.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbAreasExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbAreasExample() {
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andYearIsNull() {
            addCriterion("year is null");
            return (Criteria) this;
        }

        public Criteria andYearIsNotNull() {
            addCriterion("year is not null");
            return (Criteria) this;
        }

        public Criteria andYearEqualTo(String value) {
            addCriterion("year =", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotEqualTo(String value) {
            addCriterion("year <>", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThan(String value) {
            addCriterion("year >", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThanOrEqualTo(String value) {
            addCriterion("year >=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThan(String value) {
            addCriterion("year <", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThanOrEqualTo(String value) {
            addCriterion("year <=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLike(String value) {
            addCriterion("year like", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotLike(String value) {
            addCriterion("year not like", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearIn(List<String> values) {
            addCriterion("year in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotIn(List<String> values) {
            addCriterion("year not in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearBetween(String value1, String value2) {
            addCriterion("year between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotBetween(String value1, String value2) {
            addCriterion("year not between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andGreenRatioIsNull() {
            addCriterion("green_ratio is null");
            return (Criteria) this;
        }

        public Criteria andGreenRatioIsNotNull() {
            addCriterion("green_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andGreenRatioEqualTo(Double value) {
            addCriterion("green_ratio =", value, "greenRatio");
            return (Criteria) this;
        }

        public Criteria andGreenRatioNotEqualTo(Double value) {
            addCriterion("green_ratio <>", value, "greenRatio");
            return (Criteria) this;
        }

        public Criteria andGreenRatioGreaterThan(Double value) {
            addCriterion("green_ratio >", value, "greenRatio");
            return (Criteria) this;
        }

        public Criteria andGreenRatioGreaterThanOrEqualTo(Double value) {
            addCriterion("green_ratio >=", value, "greenRatio");
            return (Criteria) this;
        }

        public Criteria andGreenRatioLessThan(Double value) {
            addCriterion("green_ratio <", value, "greenRatio");
            return (Criteria) this;
        }

        public Criteria andGreenRatioLessThanOrEqualTo(Double value) {
            addCriterion("green_ratio <=", value, "greenRatio");
            return (Criteria) this;
        }

        public Criteria andGreenRatioIn(List<Double> values) {
            addCriterion("green_ratio in", values, "greenRatio");
            return (Criteria) this;
        }

        public Criteria andGreenRatioNotIn(List<Double> values) {
            addCriterion("green_ratio not in", values, "greenRatio");
            return (Criteria) this;
        }

        public Criteria andGreenRatioBetween(Double value1, Double value2) {
            addCriterion("green_ratio between", value1, value2, "greenRatio");
            return (Criteria) this;
        }

        public Criteria andGreenRatioNotBetween(Double value1, Double value2) {
            addCriterion("green_ratio not between", value1, value2, "greenRatio");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeIsNull() {
            addCriterion("property_fee is null");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeIsNotNull() {
            addCriterion("property_fee is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeEqualTo(Double value) {
            addCriterion("property_fee =", value, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeNotEqualTo(Double value) {
            addCriterion("property_fee <>", value, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeGreaterThan(Double value) {
            addCriterion("property_fee >", value, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeGreaterThanOrEqualTo(Double value) {
            addCriterion("property_fee >=", value, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeLessThan(Double value) {
            addCriterion("property_fee <", value, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeLessThanOrEqualTo(Double value) {
            addCriterion("property_fee <=", value, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeIn(List<Double> values) {
            addCriterion("property_fee in", values, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeNotIn(List<Double> values) {
            addCriterion("property_fee not in", values, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeBetween(Double value1, Double value2) {
            addCriterion("property_fee between", value1, value2, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeNotBetween(Double value1, Double value2) {
            addCriterion("property_fee not between", value1, value2, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andPlotRatioIsNull() {
            addCriterion("plot_ratio is null");
            return (Criteria) this;
        }

        public Criteria andPlotRatioIsNotNull() {
            addCriterion("plot_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andPlotRatioEqualTo(Double value) {
            addCriterion("plot_ratio =", value, "plotRatio");
            return (Criteria) this;
        }

        public Criteria andPlotRatioNotEqualTo(Double value) {
            addCriterion("plot_ratio <>", value, "plotRatio");
            return (Criteria) this;
        }

        public Criteria andPlotRatioGreaterThan(Double value) {
            addCriterion("plot_ratio >", value, "plotRatio");
            return (Criteria) this;
        }

        public Criteria andPlotRatioGreaterThanOrEqualTo(Double value) {
            addCriterion("plot_ratio >=", value, "plotRatio");
            return (Criteria) this;
        }

        public Criteria andPlotRatioLessThan(Double value) {
            addCriterion("plot_ratio <", value, "plotRatio");
            return (Criteria) this;
        }

        public Criteria andPlotRatioLessThanOrEqualTo(Double value) {
            addCriterion("plot_ratio <=", value, "plotRatio");
            return (Criteria) this;
        }

        public Criteria andPlotRatioIn(List<Double> values) {
            addCriterion("plot_ratio in", values, "plotRatio");
            return (Criteria) this;
        }

        public Criteria andPlotRatioNotIn(List<Double> values) {
            addCriterion("plot_ratio not in", values, "plotRatio");
            return (Criteria) this;
        }

        public Criteria andPlotRatioBetween(Double value1, Double value2) {
            addCriterion("plot_ratio between", value1, value2, "plotRatio");
            return (Criteria) this;
        }

        public Criteria andPlotRatioNotBetween(Double value1, Double value2) {
            addCriterion("plot_ratio not between", value1, value2, "plotRatio");
            return (Criteria) this;
        }

        public Criteria andAreaPropertyIsNull() {
            addCriterion("area_property is null");
            return (Criteria) this;
        }

        public Criteria andAreaPropertyIsNotNull() {
            addCriterion("area_property is not null");
            return (Criteria) this;
        }

        public Criteria andAreaPropertyEqualTo(String value) {
            addCriterion("area_property =", value, "areaProperty");
            return (Criteria) this;
        }

        public Criteria andAreaPropertyNotEqualTo(String value) {
            addCriterion("area_property <>", value, "areaProperty");
            return (Criteria) this;
        }

        public Criteria andAreaPropertyGreaterThan(String value) {
            addCriterion("area_property >", value, "areaProperty");
            return (Criteria) this;
        }

        public Criteria andAreaPropertyGreaterThanOrEqualTo(String value) {
            addCriterion("area_property >=", value, "areaProperty");
            return (Criteria) this;
        }

        public Criteria andAreaPropertyLessThan(String value) {
            addCriterion("area_property <", value, "areaProperty");
            return (Criteria) this;
        }

        public Criteria andAreaPropertyLessThanOrEqualTo(String value) {
            addCriterion("area_property <=", value, "areaProperty");
            return (Criteria) this;
        }

        public Criteria andAreaPropertyLike(String value) {
            addCriterion("area_property like", value, "areaProperty");
            return (Criteria) this;
        }

        public Criteria andAreaPropertyNotLike(String value) {
            addCriterion("area_property not like", value, "areaProperty");
            return (Criteria) this;
        }

        public Criteria andAreaPropertyIn(List<String> values) {
            addCriterion("area_property in", values, "areaProperty");
            return (Criteria) this;
        }

        public Criteria andAreaPropertyNotIn(List<String> values) {
            addCriterion("area_property not in", values, "areaProperty");
            return (Criteria) this;
        }

        public Criteria andAreaPropertyBetween(String value1, String value2) {
            addCriterion("area_property between", value1, value2, "areaProperty");
            return (Criteria) this;
        }

        public Criteria andAreaPropertyNotBetween(String value1, String value2) {
            addCriterion("area_property not between", value1, value2, "areaProperty");
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

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andPicIsNull() {
            addCriterion("pic is null");
            return (Criteria) this;
        }

        public Criteria andPicIsNotNull() {
            addCriterion("pic is not null");
            return (Criteria) this;
        }

        public Criteria andPicEqualTo(String value) {
            addCriterion("pic =", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotEqualTo(String value) {
            addCriterion("pic <>", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicGreaterThan(String value) {
            addCriterion("pic >", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicGreaterThanOrEqualTo(String value) {
            addCriterion("pic >=", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLessThan(String value) {
            addCriterion("pic <", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLessThanOrEqualTo(String value) {
            addCriterion("pic <=", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLike(String value) {
            addCriterion("pic like", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotLike(String value) {
            addCriterion("pic not like", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicIn(List<String> values) {
            addCriterion("pic in", values, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotIn(List<String> values) {
            addCriterion("pic not in", values, "pic");
            return (Criteria) this;
        }

        public Criteria andPicBetween(String value1, String value2) {
            addCriterion("pic between", value1, value2, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotBetween(String value1, String value2) {
            addCriterion("pic not between", value1, value2, "pic");
            return (Criteria) this;
        }

        public Criteria andGeoAddrIsNull() {
            addCriterion("geo_addr is null");
            return (Criteria) this;
        }

        public Criteria andGeoAddrIsNotNull() {
            addCriterion("geo_addr is not null");
            return (Criteria) this;
        }

        public Criteria andGeoAddrEqualTo(String value) {
            addCriterion("geo_addr =", value, "geoAddr");
            return (Criteria) this;
        }

        public Criteria andGeoAddrNotEqualTo(String value) {
            addCriterion("geo_addr <>", value, "geoAddr");
            return (Criteria) this;
        }

        public Criteria andGeoAddrGreaterThan(String value) {
            addCriterion("geo_addr >", value, "geoAddr");
            return (Criteria) this;
        }

        public Criteria andGeoAddrGreaterThanOrEqualTo(String value) {
            addCriterion("geo_addr >=", value, "geoAddr");
            return (Criteria) this;
        }

        public Criteria andGeoAddrLessThan(String value) {
            addCriterion("geo_addr <", value, "geoAddr");
            return (Criteria) this;
        }

        public Criteria andGeoAddrLessThanOrEqualTo(String value) {
            addCriterion("geo_addr <=", value, "geoAddr");
            return (Criteria) this;
        }

        public Criteria andGeoAddrLike(String value) {
            addCriterion("geo_addr like", value, "geoAddr");
            return (Criteria) this;
        }

        public Criteria andGeoAddrNotLike(String value) {
            addCriterion("geo_addr not like", value, "geoAddr");
            return (Criteria) this;
        }

        public Criteria andGeoAddrIn(List<String> values) {
            addCriterion("geo_addr in", values, "geoAddr");
            return (Criteria) this;
        }

        public Criteria andGeoAddrNotIn(List<String> values) {
            addCriterion("geo_addr not in", values, "geoAddr");
            return (Criteria) this;
        }

        public Criteria andGeoAddrBetween(String value1, String value2) {
            addCriterion("geo_addr between", value1, value2, "geoAddr");
            return (Criteria) this;
        }

        public Criteria andGeoAddrNotBetween(String value1, String value2) {
            addCriterion("geo_addr not between", value1, value2, "geoAddr");
            return (Criteria) this;
        }

        public Criteria andBisAddrIsNull() {
            addCriterion("bis_addr is null");
            return (Criteria) this;
        }

        public Criteria andBisAddrIsNotNull() {
            addCriterion("bis_addr is not null");
            return (Criteria) this;
        }

        public Criteria andBisAddrEqualTo(String value) {
            addCriterion("bis_addr =", value, "bisAddr");
            return (Criteria) this;
        }

        public Criteria andBisAddrNotEqualTo(String value) {
            addCriterion("bis_addr <>", value, "bisAddr");
            return (Criteria) this;
        }

        public Criteria andBisAddrGreaterThan(String value) {
            addCriterion("bis_addr >", value, "bisAddr");
            return (Criteria) this;
        }

        public Criteria andBisAddrGreaterThanOrEqualTo(String value) {
            addCriterion("bis_addr >=", value, "bisAddr");
            return (Criteria) this;
        }

        public Criteria andBisAddrLessThan(String value) {
            addCriterion("bis_addr <", value, "bisAddr");
            return (Criteria) this;
        }

        public Criteria andBisAddrLessThanOrEqualTo(String value) {
            addCriterion("bis_addr <=", value, "bisAddr");
            return (Criteria) this;
        }

        public Criteria andBisAddrLike(String value) {
            addCriterion("bis_addr like", value, "bisAddr");
            return (Criteria) this;
        }

        public Criteria andBisAddrNotLike(String value) {
            addCriterion("bis_addr not like", value, "bisAddr");
            return (Criteria) this;
        }

        public Criteria andBisAddrIn(List<String> values) {
            addCriterion("bis_addr in", values, "bisAddr");
            return (Criteria) this;
        }

        public Criteria andBisAddrNotIn(List<String> values) {
            addCriterion("bis_addr not in", values, "bisAddr");
            return (Criteria) this;
        }

        public Criteria andBisAddrBetween(String value1, String value2) {
            addCriterion("bis_addr between", value1, value2, "bisAddr");
            return (Criteria) this;
        }

        public Criteria andBisAddrNotBetween(String value1, String value2) {
            addCriterion("bis_addr not between", value1, value2, "bisAddr");
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

        public Criteria andHouseholdNumIsNull() {
            addCriterion("household_num is null");
            return (Criteria) this;
        }

        public Criteria andHouseholdNumIsNotNull() {
            addCriterion("household_num is not null");
            return (Criteria) this;
        }

        public Criteria andHouseholdNumEqualTo(String value) {
            addCriterion("household_num =", value, "householdNum");
            return (Criteria) this;
        }

        public Criteria andHouseholdNumNotEqualTo(String value) {
            addCriterion("household_num <>", value, "householdNum");
            return (Criteria) this;
        }

        public Criteria andHouseholdNumGreaterThan(String value) {
            addCriterion("household_num >", value, "householdNum");
            return (Criteria) this;
        }

        public Criteria andHouseholdNumGreaterThanOrEqualTo(String value) {
            addCriterion("household_num >=", value, "householdNum");
            return (Criteria) this;
        }

        public Criteria andHouseholdNumLessThan(String value) {
            addCriterion("household_num <", value, "householdNum");
            return (Criteria) this;
        }

        public Criteria andHouseholdNumLessThanOrEqualTo(String value) {
            addCriterion("household_num <=", value, "householdNum");
            return (Criteria) this;
        }

        public Criteria andHouseholdNumLike(String value) {
            addCriterion("household_num like", value, "householdNum");
            return (Criteria) this;
        }

        public Criteria andHouseholdNumNotLike(String value) {
            addCriterion("household_num not like", value, "householdNum");
            return (Criteria) this;
        }

        public Criteria andHouseholdNumIn(List<String> values) {
            addCriterion("household_num in", values, "householdNum");
            return (Criteria) this;
        }

        public Criteria andHouseholdNumNotIn(List<String> values) {
            addCriterion("household_num not in", values, "householdNum");
            return (Criteria) this;
        }

        public Criteria andHouseholdNumBetween(String value1, String value2) {
            addCriterion("household_num between", value1, value2, "householdNum");
            return (Criteria) this;
        }

        public Criteria andHouseholdNumNotBetween(String value1, String value2) {
            addCriterion("household_num not between", value1, value2, "householdNum");
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