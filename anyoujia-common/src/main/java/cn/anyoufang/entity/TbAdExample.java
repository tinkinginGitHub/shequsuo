package cn.anyoufang.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbAdExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbAdExample() {
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

        public Criteria andAdSortOrderIsNull() {
            addCriterion("ad_sort_order is null");
            return (Criteria) this;
        }

        public Criteria andAdSortOrderIsNotNull() {
            addCriterion("ad_sort_order is not null");
            return (Criteria) this;
        }

        public Criteria andAdSortOrderEqualTo(Byte value) {
            addCriterion("ad_sort_order =", value, "adSortOrder");
            return (Criteria) this;
        }

        public Criteria andAdSortOrderNotEqualTo(Byte value) {
            addCriterion("ad_sort_order <>", value, "adSortOrder");
            return (Criteria) this;
        }

        public Criteria andAdSortOrderGreaterThan(Byte value) {
            addCriterion("ad_sort_order >", value, "adSortOrder");
            return (Criteria) this;
        }

        public Criteria andAdSortOrderGreaterThanOrEqualTo(Byte value) {
            addCriterion("ad_sort_order >=", value, "adSortOrder");
            return (Criteria) this;
        }

        public Criteria andAdSortOrderLessThan(Byte value) {
            addCriterion("ad_sort_order <", value, "adSortOrder");
            return (Criteria) this;
        }

        public Criteria andAdSortOrderLessThanOrEqualTo(Byte value) {
            addCriterion("ad_sort_order <=", value, "adSortOrder");
            return (Criteria) this;
        }

        public Criteria andAdSortOrderIn(List<Byte> values) {
            addCriterion("ad_sort_order in", values, "adSortOrder");
            return (Criteria) this;
        }

        public Criteria andAdSortOrderNotIn(List<Byte> values) {
            addCriterion("ad_sort_order not in", values, "adSortOrder");
            return (Criteria) this;
        }

        public Criteria andAdSortOrderBetween(Byte value1, Byte value2) {
            addCriterion("ad_sort_order between", value1, value2, "adSortOrder");
            return (Criteria) this;
        }

        public Criteria andAdSortOrderNotBetween(Byte value1, Byte value2) {
            addCriterion("ad_sort_order not between", value1, value2, "adSortOrder");
            return (Criteria) this;
        }

        public Criteria andAdStatusIsNull() {
            addCriterion("ad_status is null");
            return (Criteria) this;
        }

        public Criteria andAdStatusIsNotNull() {
            addCriterion("ad_status is not null");
            return (Criteria) this;
        }

        public Criteria andAdStatusEqualTo(String value) {
            addCriterion("ad_status =", value, "adStatus");
            return (Criteria) this;
        }

        public Criteria andAdStatusNotEqualTo(String value) {
            addCriterion("ad_status <>", value, "adStatus");
            return (Criteria) this;
        }

        public Criteria andAdStatusGreaterThan(String value) {
            addCriterion("ad_status >", value, "adStatus");
            return (Criteria) this;
        }

        public Criteria andAdStatusGreaterThanOrEqualTo(String value) {
            addCriterion("ad_status >=", value, "adStatus");
            return (Criteria) this;
        }

        public Criteria andAdStatusLessThan(String value) {
            addCriterion("ad_status <", value, "adStatus");
            return (Criteria) this;
        }

        public Criteria andAdStatusLessThanOrEqualTo(String value) {
            addCriterion("ad_status <=", value, "adStatus");
            return (Criteria) this;
        }

        public Criteria andAdStatusLike(String value) {
            addCriterion("ad_status like", value, "adStatus");
            return (Criteria) this;
        }

        public Criteria andAdStatusNotLike(String value) {
            addCriterion("ad_status not like", value, "adStatus");
            return (Criteria) this;
        }

        public Criteria andAdStatusIn(List<String> values) {
            addCriterion("ad_status in", values, "adStatus");
            return (Criteria) this;
        }

        public Criteria andAdStatusNotIn(List<String> values) {
            addCriterion("ad_status not in", values, "adStatus");
            return (Criteria) this;
        }

        public Criteria andAdStatusBetween(String value1, String value2) {
            addCriterion("ad_status between", value1, value2, "adStatus");
            return (Criteria) this;
        }

        public Criteria andAdStatusNotBetween(String value1, String value2) {
            addCriterion("ad_status not between", value1, value2, "adStatus");
            return (Criteria) this;
        }

        public Criteria andAdPicHrefIsNull() {
            addCriterion("ad_pic_href is null");
            return (Criteria) this;
        }

        public Criteria andAdPicHrefIsNotNull() {
            addCriterion("ad_pic_href is not null");
            return (Criteria) this;
        }

        public Criteria andAdPicHrefEqualTo(String value) {
            addCriterion("ad_pic_href =", value, "adPicHref");
            return (Criteria) this;
        }

        public Criteria andAdPicHrefNotEqualTo(String value) {
            addCriterion("ad_pic_href <>", value, "adPicHref");
            return (Criteria) this;
        }

        public Criteria andAdPicHrefGreaterThan(String value) {
            addCriterion("ad_pic_href >", value, "adPicHref");
            return (Criteria) this;
        }

        public Criteria andAdPicHrefGreaterThanOrEqualTo(String value) {
            addCriterion("ad_pic_href >=", value, "adPicHref");
            return (Criteria) this;
        }

        public Criteria andAdPicHrefLessThan(String value) {
            addCriterion("ad_pic_href <", value, "adPicHref");
            return (Criteria) this;
        }

        public Criteria andAdPicHrefLessThanOrEqualTo(String value) {
            addCriterion("ad_pic_href <=", value, "adPicHref");
            return (Criteria) this;
        }

        public Criteria andAdPicHrefLike(String value) {
            addCriterion("ad_pic_href like", value, "adPicHref");
            return (Criteria) this;
        }

        public Criteria andAdPicHrefNotLike(String value) {
            addCriterion("ad_pic_href not like", value, "adPicHref");
            return (Criteria) this;
        }

        public Criteria andAdPicHrefIn(List<String> values) {
            addCriterion("ad_pic_href in", values, "adPicHref");
            return (Criteria) this;
        }

        public Criteria andAdPicHrefNotIn(List<String> values) {
            addCriterion("ad_pic_href not in", values, "adPicHref");
            return (Criteria) this;
        }

        public Criteria andAdPicHrefBetween(String value1, String value2) {
            addCriterion("ad_pic_href between", value1, value2, "adPicHref");
            return (Criteria) this;
        }

        public Criteria andAdPicHrefNotBetween(String value1, String value2) {
            addCriterion("ad_pic_href not between", value1, value2, "adPicHref");
            return (Criteria) this;
        }

        public Criteria andAdPicLinkIsNull() {
            addCriterion("ad_pic_link is null");
            return (Criteria) this;
        }

        public Criteria andAdPicLinkIsNotNull() {
            addCriterion("ad_pic_link is not null");
            return (Criteria) this;
        }

        public Criteria andAdPicLinkEqualTo(String value) {
            addCriterion("ad_pic_link =", value, "adPicLink");
            return (Criteria) this;
        }

        public Criteria andAdPicLinkNotEqualTo(String value) {
            addCriterion("ad_pic_link <>", value, "adPicLink");
            return (Criteria) this;
        }

        public Criteria andAdPicLinkGreaterThan(String value) {
            addCriterion("ad_pic_link >", value, "adPicLink");
            return (Criteria) this;
        }

        public Criteria andAdPicLinkGreaterThanOrEqualTo(String value) {
            addCriterion("ad_pic_link >=", value, "adPicLink");
            return (Criteria) this;
        }

        public Criteria andAdPicLinkLessThan(String value) {
            addCriterion("ad_pic_link <", value, "adPicLink");
            return (Criteria) this;
        }

        public Criteria andAdPicLinkLessThanOrEqualTo(String value) {
            addCriterion("ad_pic_link <=", value, "adPicLink");
            return (Criteria) this;
        }

        public Criteria andAdPicLinkLike(String value) {
            addCriterion("ad_pic_link like", value, "adPicLink");
            return (Criteria) this;
        }

        public Criteria andAdPicLinkNotLike(String value) {
            addCriterion("ad_pic_link not like", value, "adPicLink");
            return (Criteria) this;
        }

        public Criteria andAdPicLinkIn(List<String> values) {
            addCriterion("ad_pic_link in", values, "adPicLink");
            return (Criteria) this;
        }

        public Criteria andAdPicLinkNotIn(List<String> values) {
            addCriterion("ad_pic_link not in", values, "adPicLink");
            return (Criteria) this;
        }

        public Criteria andAdPicLinkBetween(String value1, String value2) {
            addCriterion("ad_pic_link between", value1, value2, "adPicLink");
            return (Criteria) this;
        }

        public Criteria andAdPicLinkNotBetween(String value1, String value2) {
            addCriterion("ad_pic_link not between", value1, value2, "adPicLink");
            return (Criteria) this;
        }

        public Criteria andAdTypeIsNull() {
            addCriterion("ad_type is null");
            return (Criteria) this;
        }

        public Criteria andAdTypeIsNotNull() {
            addCriterion("ad_type is not null");
            return (Criteria) this;
        }

        public Criteria andAdTypeEqualTo(String value) {
            addCriterion("ad_type =", value, "adType");
            return (Criteria) this;
        }

        public Criteria andAdTypeNotEqualTo(String value) {
            addCriterion("ad_type <>", value, "adType");
            return (Criteria) this;
        }

        public Criteria andAdTypeGreaterThan(String value) {
            addCriterion("ad_type >", value, "adType");
            return (Criteria) this;
        }

        public Criteria andAdTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ad_type >=", value, "adType");
            return (Criteria) this;
        }

        public Criteria andAdTypeLessThan(String value) {
            addCriterion("ad_type <", value, "adType");
            return (Criteria) this;
        }

        public Criteria andAdTypeLessThanOrEqualTo(String value) {
            addCriterion("ad_type <=", value, "adType");
            return (Criteria) this;
        }

        public Criteria andAdTypeLike(String value) {
            addCriterion("ad_type like", value, "adType");
            return (Criteria) this;
        }

        public Criteria andAdTypeNotLike(String value) {
            addCriterion("ad_type not like", value, "adType");
            return (Criteria) this;
        }

        public Criteria andAdTypeIn(List<String> values) {
            addCriterion("ad_type in", values, "adType");
            return (Criteria) this;
        }

        public Criteria andAdTypeNotIn(List<String> values) {
            addCriterion("ad_type not in", values, "adType");
            return (Criteria) this;
        }

        public Criteria andAdTypeBetween(String value1, String value2) {
            addCriterion("ad_type between", value1, value2, "adType");
            return (Criteria) this;
        }

        public Criteria andAdTypeNotBetween(String value1, String value2) {
            addCriterion("ad_type not between", value1, value2, "adType");
            return (Criteria) this;
        }

        public Criteria andAdCreatedIsNull() {
            addCriterion("ad_created is null");
            return (Criteria) this;
        }

        public Criteria andAdCreatedIsNotNull() {
            addCriterion("ad_created is not null");
            return (Criteria) this;
        }

        public Criteria andAdCreatedEqualTo(Date value) {
            addCriterion("ad_created =", value, "adCreated");
            return (Criteria) this;
        }

        public Criteria andAdCreatedNotEqualTo(Date value) {
            addCriterion("ad_created <>", value, "adCreated");
            return (Criteria) this;
        }

        public Criteria andAdCreatedGreaterThan(Date value) {
            addCriterion("ad_created >", value, "adCreated");
            return (Criteria) this;
        }

        public Criteria andAdCreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("ad_created >=", value, "adCreated");
            return (Criteria) this;
        }

        public Criteria andAdCreatedLessThan(Date value) {
            addCriterion("ad_created <", value, "adCreated");
            return (Criteria) this;
        }

        public Criteria andAdCreatedLessThanOrEqualTo(Date value) {
            addCriterion("ad_created <=", value, "adCreated");
            return (Criteria) this;
        }

        public Criteria andAdCreatedIn(List<Date> values) {
            addCriterion("ad_created in", values, "adCreated");
            return (Criteria) this;
        }

        public Criteria andAdCreatedNotIn(List<Date> values) {
            addCriterion("ad_created not in", values, "adCreated");
            return (Criteria) this;
        }

        public Criteria andAdCreatedBetween(Date value1, Date value2) {
            addCriterion("ad_created between", value1, value2, "adCreated");
            return (Criteria) this;
        }

        public Criteria andAdCreatedNotBetween(Date value1, Date value2) {
            addCriterion("ad_created not between", value1, value2, "adCreated");
            return (Criteria) this;
        }

        public Criteria andAdUpdatedIsNull() {
            addCriterion("ad_updated is null");
            return (Criteria) this;
        }

        public Criteria andAdUpdatedIsNotNull() {
            addCriterion("ad_updated is not null");
            return (Criteria) this;
        }

        public Criteria andAdUpdatedEqualTo(Date value) {
            addCriterion("ad_updated =", value, "adUpdated");
            return (Criteria) this;
        }

        public Criteria andAdUpdatedNotEqualTo(Date value) {
            addCriterion("ad_updated <>", value, "adUpdated");
            return (Criteria) this;
        }

        public Criteria andAdUpdatedGreaterThan(Date value) {
            addCriterion("ad_updated >", value, "adUpdated");
            return (Criteria) this;
        }

        public Criteria andAdUpdatedGreaterThanOrEqualTo(Date value) {
            addCriterion("ad_updated >=", value, "adUpdated");
            return (Criteria) this;
        }

        public Criteria andAdUpdatedLessThan(Date value) {
            addCriterion("ad_updated <", value, "adUpdated");
            return (Criteria) this;
        }

        public Criteria andAdUpdatedLessThanOrEqualTo(Date value) {
            addCriterion("ad_updated <=", value, "adUpdated");
            return (Criteria) this;
        }

        public Criteria andAdUpdatedIn(List<Date> values) {
            addCriterion("ad_updated in", values, "adUpdated");
            return (Criteria) this;
        }

        public Criteria andAdUpdatedNotIn(List<Date> values) {
            addCriterion("ad_updated not in", values, "adUpdated");
            return (Criteria) this;
        }

        public Criteria andAdUpdatedBetween(Date value1, Date value2) {
            addCriterion("ad_updated between", value1, value2, "adUpdated");
            return (Criteria) this;
        }

        public Criteria andAdUpdatedNotBetween(Date value1, Date value2) {
            addCriterion("ad_updated not between", value1, value2, "adUpdated");
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