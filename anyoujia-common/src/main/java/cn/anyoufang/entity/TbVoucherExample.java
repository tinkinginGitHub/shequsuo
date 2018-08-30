package cn.anyoufang.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbVoucherExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbVoucherExample() {
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

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(Integer value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(Integer value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(Integer value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(Integer value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(Integer value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<Integer> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<Integer> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(Integer value1, Integer value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andDeductibleMoneyIsNull() {
            addCriterion("deductible_money is null");
            return (Criteria) this;
        }

        public Criteria andDeductibleMoneyIsNotNull() {
            addCriterion("deductible_money is not null");
            return (Criteria) this;
        }

        public Criteria andDeductibleMoneyEqualTo(Integer value) {
            addCriterion("deductible_money =", value, "deductibleMoney");
            return (Criteria) this;
        }

        public Criteria andDeductibleMoneyNotEqualTo(Integer value) {
            addCriterion("deductible_money <>", value, "deductibleMoney");
            return (Criteria) this;
        }

        public Criteria andDeductibleMoneyGreaterThan(Integer value) {
            addCriterion("deductible_money >", value, "deductibleMoney");
            return (Criteria) this;
        }

        public Criteria andDeductibleMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("deductible_money >=", value, "deductibleMoney");
            return (Criteria) this;
        }

        public Criteria andDeductibleMoneyLessThan(Integer value) {
            addCriterion("deductible_money <", value, "deductibleMoney");
            return (Criteria) this;
        }

        public Criteria andDeductibleMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("deductible_money <=", value, "deductibleMoney");
            return (Criteria) this;
        }

        public Criteria andDeductibleMoneyIn(List<Integer> values) {
            addCriterion("deductible_money in", values, "deductibleMoney");
            return (Criteria) this;
        }

        public Criteria andDeductibleMoneyNotIn(List<Integer> values) {
            addCriterion("deductible_money not in", values, "deductibleMoney");
            return (Criteria) this;
        }

        public Criteria andDeductibleMoneyBetween(Integer value1, Integer value2) {
            addCriterion("deductible_money between", value1, value2, "deductibleMoney");
            return (Criteria) this;
        }

        public Criteria andDeductibleMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("deductible_money not between", value1, value2, "deductibleMoney");
            return (Criteria) this;
        }

        public Criteria andDeductibleDaysIsNull() {
            addCriterion("deductible_days is null");
            return (Criteria) this;
        }

        public Criteria andDeductibleDaysIsNotNull() {
            addCriterion("deductible_days is not null");
            return (Criteria) this;
        }

        public Criteria andDeductibleDaysEqualTo(Integer value) {
            addCriterion("deductible_days =", value, "deductibleDays");
            return (Criteria) this;
        }

        public Criteria andDeductibleDaysNotEqualTo(Integer value) {
            addCriterion("deductible_days <>", value, "deductibleDays");
            return (Criteria) this;
        }

        public Criteria andDeductibleDaysGreaterThan(Integer value) {
            addCriterion("deductible_days >", value, "deductibleDays");
            return (Criteria) this;
        }

        public Criteria andDeductibleDaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("deductible_days >=", value, "deductibleDays");
            return (Criteria) this;
        }

        public Criteria andDeductibleDaysLessThan(Integer value) {
            addCriterion("deductible_days <", value, "deductibleDays");
            return (Criteria) this;
        }

        public Criteria andDeductibleDaysLessThanOrEqualTo(Integer value) {
            addCriterion("deductible_days <=", value, "deductibleDays");
            return (Criteria) this;
        }

        public Criteria andDeductibleDaysIn(List<Integer> values) {
            addCriterion("deductible_days in", values, "deductibleDays");
            return (Criteria) this;
        }

        public Criteria andDeductibleDaysNotIn(List<Integer> values) {
            addCriterion("deductible_days not in", values, "deductibleDays");
            return (Criteria) this;
        }

        public Criteria andDeductibleDaysBetween(Integer value1, Integer value2) {
            addCriterion("deductible_days between", value1, value2, "deductibleDays");
            return (Criteria) this;
        }

        public Criteria andDeductibleDaysNotBetween(Integer value1, Integer value2) {
            addCriterion("deductible_days not between", value1, value2, "deductibleDays");
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

        public Criteria andPayNumberIsNull() {
            addCriterion("pay_number is null");
            return (Criteria) this;
        }

        public Criteria andPayNumberIsNotNull() {
            addCriterion("pay_number is not null");
            return (Criteria) this;
        }

        public Criteria andPayNumberEqualTo(String value) {
            addCriterion("pay_number =", value, "payNumber");
            return (Criteria) this;
        }

        public Criteria andPayNumberNotEqualTo(String value) {
            addCriterion("pay_number <>", value, "payNumber");
            return (Criteria) this;
        }

        public Criteria andPayNumberGreaterThan(String value) {
            addCriterion("pay_number >", value, "payNumber");
            return (Criteria) this;
        }

        public Criteria andPayNumberGreaterThanOrEqualTo(String value) {
            addCriterion("pay_number >=", value, "payNumber");
            return (Criteria) this;
        }

        public Criteria andPayNumberLessThan(String value) {
            addCriterion("pay_number <", value, "payNumber");
            return (Criteria) this;
        }

        public Criteria andPayNumberLessThanOrEqualTo(String value) {
            addCriterion("pay_number <=", value, "payNumber");
            return (Criteria) this;
        }

        public Criteria andPayNumberLike(String value) {
            addCriterion("pay_number like", value, "payNumber");
            return (Criteria) this;
        }

        public Criteria andPayNumberNotLike(String value) {
            addCriterion("pay_number not like", value, "payNumber");
            return (Criteria) this;
        }

        public Criteria andPayNumberIn(List<String> values) {
            addCriterion("pay_number in", values, "payNumber");
            return (Criteria) this;
        }

        public Criteria andPayNumberNotIn(List<String> values) {
            addCriterion("pay_number not in", values, "payNumber");
            return (Criteria) this;
        }

        public Criteria andPayNumberBetween(String value1, String value2) {
            addCriterion("pay_number between", value1, value2, "payNumber");
            return (Criteria) this;
        }

        public Criteria andPayNumberNotBetween(String value1, String value2) {
            addCriterion("pay_number not between", value1, value2, "payNumber");
            return (Criteria) this;
        }

        public Criteria andVoucherstateIsNull() {
            addCriterion("voucherstate is null");
            return (Criteria) this;
        }

        public Criteria andVoucherstateIsNotNull() {
            addCriterion("voucherstate is not null");
            return (Criteria) this;
        }

        public Criteria andVoucherstateEqualTo(String value) {
            addCriterion("voucherstate =", value, "voucherstate");
            return (Criteria) this;
        }

        public Criteria andVoucherstateNotEqualTo(String value) {
            addCriterion("voucherstate <>", value, "voucherstate");
            return (Criteria) this;
        }

        public Criteria andVoucherstateGreaterThan(String value) {
            addCriterion("voucherstate >", value, "voucherstate");
            return (Criteria) this;
        }

        public Criteria andVoucherstateGreaterThanOrEqualTo(String value) {
            addCriterion("voucherstate >=", value, "voucherstate");
            return (Criteria) this;
        }

        public Criteria andVoucherstateLessThan(String value) {
            addCriterion("voucherstate <", value, "voucherstate");
            return (Criteria) this;
        }

        public Criteria andVoucherstateLessThanOrEqualTo(String value) {
            addCriterion("voucherstate <=", value, "voucherstate");
            return (Criteria) this;
        }

        public Criteria andVoucherstateLike(String value) {
            addCriterion("voucherstate like", value, "voucherstate");
            return (Criteria) this;
        }

        public Criteria andVoucherstateNotLike(String value) {
            addCriterion("voucherstate not like", value, "voucherstate");
            return (Criteria) this;
        }

        public Criteria andVoucherstateIn(List<String> values) {
            addCriterion("voucherstate in", values, "voucherstate");
            return (Criteria) this;
        }

        public Criteria andVoucherstateNotIn(List<String> values) {
            addCriterion("voucherstate not in", values, "voucherstate");
            return (Criteria) this;
        }

        public Criteria andVoucherstateBetween(String value1, String value2) {
            addCriterion("voucherstate between", value1, value2, "voucherstate");
            return (Criteria) this;
        }

        public Criteria andVoucherstateNotBetween(String value1, String value2) {
            addCriterion("voucherstate not between", value1, value2, "voucherstate");
            return (Criteria) this;
        }

        public Criteria andTimeAvailableIsNull() {
            addCriterion("time_available is null");
            return (Criteria) this;
        }

        public Criteria andTimeAvailableIsNotNull() {
            addCriterion("time_available is not null");
            return (Criteria) this;
        }

        public Criteria andTimeAvailableEqualTo(Integer value) {
            addCriterion("time_available =", value, "timeAvailable");
            return (Criteria) this;
        }

        public Criteria andTimeAvailableNotEqualTo(Integer value) {
            addCriterion("time_available <>", value, "timeAvailable");
            return (Criteria) this;
        }

        public Criteria andTimeAvailableGreaterThan(Integer value) {
            addCriterion("time_available >", value, "timeAvailable");
            return (Criteria) this;
        }

        public Criteria andTimeAvailableGreaterThanOrEqualTo(Integer value) {
            addCriterion("time_available >=", value, "timeAvailable");
            return (Criteria) this;
        }

        public Criteria andTimeAvailableLessThan(Integer value) {
            addCriterion("time_available <", value, "timeAvailable");
            return (Criteria) this;
        }

        public Criteria andTimeAvailableLessThanOrEqualTo(Integer value) {
            addCriterion("time_available <=", value, "timeAvailable");
            return (Criteria) this;
        }

        public Criteria andTimeAvailableIn(List<Integer> values) {
            addCriterion("time_available in", values, "timeAvailable");
            return (Criteria) this;
        }

        public Criteria andTimeAvailableNotIn(List<Integer> values) {
            addCriterion("time_available not in", values, "timeAvailable");
            return (Criteria) this;
        }

        public Criteria andTimeAvailableBetween(Integer value1, Integer value2) {
            addCriterion("time_available between", value1, value2, "timeAvailable");
            return (Criteria) this;
        }

        public Criteria andTimeAvailableNotBetween(Integer value1, Integer value2) {
            addCriterion("time_available not between", value1, value2, "timeAvailable");
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

        public Criteria andTextIsNull() {
            addCriterion("text is null");
            return (Criteria) this;
        }

        public Criteria andTextIsNotNull() {
            addCriterion("text is not null");
            return (Criteria) this;
        }

        public Criteria andTextEqualTo(String value) {
            addCriterion("text =", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextNotEqualTo(String value) {
            addCriterion("text <>", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextGreaterThan(String value) {
            addCriterion("text >", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextGreaterThanOrEqualTo(String value) {
            addCriterion("text >=", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextLessThan(String value) {
            addCriterion("text <", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextLessThanOrEqualTo(String value) {
            addCriterion("text <=", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextLike(String value) {
            addCriterion("text like", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextNotLike(String value) {
            addCriterion("text not like", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextIn(List<String> values) {
            addCriterion("text in", values, "text");
            return (Criteria) this;
        }

        public Criteria andTextNotIn(List<String> values) {
            addCriterion("text not in", values, "text");
            return (Criteria) this;
        }

        public Criteria andTextBetween(String value1, String value2) {
            addCriterion("text between", value1, value2, "text");
            return (Criteria) this;
        }

        public Criteria andTextNotBetween(String value1, String value2) {
            addCriterion("text not between", value1, value2, "text");
            return (Criteria) this;
        }

        public Criteria andSerialNumIsNull() {
            addCriterion("serial_num is null");
            return (Criteria) this;
        }

        public Criteria andSerialNumIsNotNull() {
            addCriterion("serial_num is not null");
            return (Criteria) this;
        }

        public Criteria andSerialNumEqualTo(String value) {
            addCriterion("serial_num =", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumNotEqualTo(String value) {
            addCriterion("serial_num <>", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumGreaterThan(String value) {
            addCriterion("serial_num >", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumGreaterThanOrEqualTo(String value) {
            addCriterion("serial_num >=", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumLessThan(String value) {
            addCriterion("serial_num <", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumLessThanOrEqualTo(String value) {
            addCriterion("serial_num <=", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumLike(String value) {
            addCriterion("serial_num like", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumNotLike(String value) {
            addCriterion("serial_num not like", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumIn(List<String> values) {
            addCriterion("serial_num in", values, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumNotIn(List<String> values) {
            addCriterion("serial_num not in", values, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumBetween(String value1, String value2) {
            addCriterion("serial_num between", value1, value2, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumNotBetween(String value1, String value2) {
            addCriterion("serial_num not between", value1, value2, "serialNum");
            return (Criteria) this;
        }

        public Criteria andBuyTimeIsNull() {
            addCriterion("buy_time is null");
            return (Criteria) this;
        }

        public Criteria andBuyTimeIsNotNull() {
            addCriterion("buy_time is not null");
            return (Criteria) this;
        }

        public Criteria andBuyTimeEqualTo(Date value) {
            addCriterion("buy_time =", value, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeNotEqualTo(Date value) {
            addCriterion("buy_time <>", value, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeGreaterThan(Date value) {
            addCriterion("buy_time >", value, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("buy_time >=", value, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeLessThan(Date value) {
            addCriterion("buy_time <", value, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeLessThanOrEqualTo(Date value) {
            addCriterion("buy_time <=", value, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeIn(List<Date> values) {
            addCriterion("buy_time in", values, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeNotIn(List<Date> values) {
            addCriterion("buy_time not in", values, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeBetween(Date value1, Date value2) {
            addCriterion("buy_time between", value1, value2, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeNotBetween(Date value1, Date value2) {
            addCriterion("buy_time not between", value1, value2, "buyTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeIsNull() {
            addCriterion("active_time is null");
            return (Criteria) this;
        }

        public Criteria andActiveTimeIsNotNull() {
            addCriterion("active_time is not null");
            return (Criteria) this;
        }

        public Criteria andActiveTimeEqualTo(Date value) {
            addCriterion("active_time =", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeNotEqualTo(Date value) {
            addCriterion("active_time <>", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeGreaterThan(Date value) {
            addCriterion("active_time >", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("active_time >=", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeLessThan(Date value) {
            addCriterion("active_time <", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeLessThanOrEqualTo(Date value) {
            addCriterion("active_time <=", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeIn(List<Date> values) {
            addCriterion("active_time in", values, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeNotIn(List<Date> values) {
            addCriterion("active_time not in", values, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeBetween(Date value1, Date value2) {
            addCriterion("active_time between", value1, value2, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeNotBetween(Date value1, Date value2) {
            addCriterion("active_time not between", value1, value2, "activeTime");
            return (Criteria) this;
        }

        public Criteria andUsedtimeIsNull() {
            addCriterion("usedtime is null");
            return (Criteria) this;
        }

        public Criteria andUsedtimeIsNotNull() {
            addCriterion("usedtime is not null");
            return (Criteria) this;
        }

        public Criteria andUsedtimeEqualTo(Date value) {
            addCriterion("usedtime =", value, "usedtime");
            return (Criteria) this;
        }

        public Criteria andUsedtimeNotEqualTo(Date value) {
            addCriterion("usedtime <>", value, "usedtime");
            return (Criteria) this;
        }

        public Criteria andUsedtimeGreaterThan(Date value) {
            addCriterion("usedtime >", value, "usedtime");
            return (Criteria) this;
        }

        public Criteria andUsedtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("usedtime >=", value, "usedtime");
            return (Criteria) this;
        }

        public Criteria andUsedtimeLessThan(Date value) {
            addCriterion("usedtime <", value, "usedtime");
            return (Criteria) this;
        }

        public Criteria andUsedtimeLessThanOrEqualTo(Date value) {
            addCriterion("usedtime <=", value, "usedtime");
            return (Criteria) this;
        }

        public Criteria andUsedtimeIn(List<Date> values) {
            addCriterion("usedtime in", values, "usedtime");
            return (Criteria) this;
        }

        public Criteria andUsedtimeNotIn(List<Date> values) {
            addCriterion("usedtime not in", values, "usedtime");
            return (Criteria) this;
        }

        public Criteria andUsedtimeBetween(Date value1, Date value2) {
            addCriterion("usedtime between", value1, value2, "usedtime");
            return (Criteria) this;
        }

        public Criteria andUsedtimeNotBetween(Date value1, Date value2) {
            addCriterion("usedtime not between", value1, value2, "usedtime");
            return (Criteria) this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("userid is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userid is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(String value) {
            addCriterion("userid =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(String value) {
            addCriterion("userid <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(String value) {
            addCriterion("userid >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(String value) {
            addCriterion("userid >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(String value) {
            addCriterion("userid <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(String value) {
            addCriterion("userid <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLike(String value) {
            addCriterion("userid like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotLike(String value) {
            addCriterion("userid not like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<String> values) {
            addCriterion("userid in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<String> values) {
            addCriterion("userid not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(String value1, String value2) {
            addCriterion("userid between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(String value1, String value2) {
            addCriterion("userid not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andHousingidIsNull() {
            addCriterion("housingid is null");
            return (Criteria) this;
        }

        public Criteria andHousingidIsNotNull() {
            addCriterion("housingid is not null");
            return (Criteria) this;
        }

        public Criteria andHousingidEqualTo(String value) {
            addCriterion("housingid =", value, "housingid");
            return (Criteria) this;
        }

        public Criteria andHousingidNotEqualTo(String value) {
            addCriterion("housingid <>", value, "housingid");
            return (Criteria) this;
        }

        public Criteria andHousingidGreaterThan(String value) {
            addCriterion("housingid >", value, "housingid");
            return (Criteria) this;
        }

        public Criteria andHousingidGreaterThanOrEqualTo(String value) {
            addCriterion("housingid >=", value, "housingid");
            return (Criteria) this;
        }

        public Criteria andHousingidLessThan(String value) {
            addCriterion("housingid <", value, "housingid");
            return (Criteria) this;
        }

        public Criteria andHousingidLessThanOrEqualTo(String value) {
            addCriterion("housingid <=", value, "housingid");
            return (Criteria) this;
        }

        public Criteria andHousingidLike(String value) {
            addCriterion("housingid like", value, "housingid");
            return (Criteria) this;
        }

        public Criteria andHousingidNotLike(String value) {
            addCriterion("housingid not like", value, "housingid");
            return (Criteria) this;
        }

        public Criteria andHousingidIn(List<String> values) {
            addCriterion("housingid in", values, "housingid");
            return (Criteria) this;
        }

        public Criteria andHousingidNotIn(List<String> values) {
            addCriterion("housingid not in", values, "housingid");
            return (Criteria) this;
        }

        public Criteria andHousingidBetween(String value1, String value2) {
            addCriterion("housingid between", value1, value2, "housingid");
            return (Criteria) this;
        }

        public Criteria andHousingidNotBetween(String value1, String value2) {
            addCriterion("housingid not between", value1, value2, "housingid");
            return (Criteria) this;
        }

        public Criteria andTotalmoneyIsNull() {
            addCriterion("totalmoney is null");
            return (Criteria) this;
        }

        public Criteria andTotalmoneyIsNotNull() {
            addCriterion("totalmoney is not null");
            return (Criteria) this;
        }

        public Criteria andTotalmoneyEqualTo(Integer value) {
            addCriterion("totalmoney =", value, "totalmoney");
            return (Criteria) this;
        }

        public Criteria andTotalmoneyNotEqualTo(Integer value) {
            addCriterion("totalmoney <>", value, "totalmoney");
            return (Criteria) this;
        }

        public Criteria andTotalmoneyGreaterThan(Integer value) {
            addCriterion("totalmoney >", value, "totalmoney");
            return (Criteria) this;
        }

        public Criteria andTotalmoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("totalmoney >=", value, "totalmoney");
            return (Criteria) this;
        }

        public Criteria andTotalmoneyLessThan(Integer value) {
            addCriterion("totalmoney <", value, "totalmoney");
            return (Criteria) this;
        }

        public Criteria andTotalmoneyLessThanOrEqualTo(Integer value) {
            addCriterion("totalmoney <=", value, "totalmoney");
            return (Criteria) this;
        }

        public Criteria andTotalmoneyIn(List<Integer> values) {
            addCriterion("totalmoney in", values, "totalmoney");
            return (Criteria) this;
        }

        public Criteria andTotalmoneyNotIn(List<Integer> values) {
            addCriterion("totalmoney not in", values, "totalmoney");
            return (Criteria) this;
        }

        public Criteria andTotalmoneyBetween(Integer value1, Integer value2) {
            addCriterion("totalmoney between", value1, value2, "totalmoney");
            return (Criteria) this;
        }

        public Criteria andTotalmoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("totalmoney not between", value1, value2, "totalmoney");
            return (Criteria) this;
        }

        public Criteria andVoucherNameIsNull() {
            addCriterion("voucher_name is null");
            return (Criteria) this;
        }

        public Criteria andVoucherNameIsNotNull() {
            addCriterion("voucher_name is not null");
            return (Criteria) this;
        }

        public Criteria andVoucherNameEqualTo(String value) {
            addCriterion("voucher_name =", value, "voucherName");
            return (Criteria) this;
        }

        public Criteria andVoucherNameNotEqualTo(String value) {
            addCriterion("voucher_name <>", value, "voucherName");
            return (Criteria) this;
        }

        public Criteria andVoucherNameGreaterThan(String value) {
            addCriterion("voucher_name >", value, "voucherName");
            return (Criteria) this;
        }

        public Criteria andVoucherNameGreaterThanOrEqualTo(String value) {
            addCriterion("voucher_name >=", value, "voucherName");
            return (Criteria) this;
        }

        public Criteria andVoucherNameLessThan(String value) {
            addCriterion("voucher_name <", value, "voucherName");
            return (Criteria) this;
        }

        public Criteria andVoucherNameLessThanOrEqualTo(String value) {
            addCriterion("voucher_name <=", value, "voucherName");
            return (Criteria) this;
        }

        public Criteria andVoucherNameLike(String value) {
            addCriterion("voucher_name like", value, "voucherName");
            return (Criteria) this;
        }

        public Criteria andVoucherNameNotLike(String value) {
            addCriterion("voucher_name not like", value, "voucherName");
            return (Criteria) this;
        }

        public Criteria andVoucherNameIn(List<String> values) {
            addCriterion("voucher_name in", values, "voucherName");
            return (Criteria) this;
        }

        public Criteria andVoucherNameNotIn(List<String> values) {
            addCriterion("voucher_name not in", values, "voucherName");
            return (Criteria) this;
        }

        public Criteria andVoucherNameBetween(String value1, String value2) {
            addCriterion("voucher_name between", value1, value2, "voucherName");
            return (Criteria) this;
        }

        public Criteria andVoucherNameNotBetween(String value1, String value2) {
            addCriterion("voucher_name not between", value1, value2, "voucherName");
            return (Criteria) this;
        }

        public Criteria andRefundIsNull() {
            addCriterion("refund is null");
            return (Criteria) this;
        }

        public Criteria andRefundIsNotNull() {
            addCriterion("refund is not null");
            return (Criteria) this;
        }

        public Criteria andRefundEqualTo(Date value) {
            addCriterion("refund =", value, "refund");
            return (Criteria) this;
        }

        public Criteria andRefundNotEqualTo(Date value) {
            addCriterion("refund <>", value, "refund");
            return (Criteria) this;
        }

        public Criteria andRefundGreaterThan(Date value) {
            addCriterion("refund >", value, "refund");
            return (Criteria) this;
        }

        public Criteria andRefundGreaterThanOrEqualTo(Date value) {
            addCriterion("refund >=", value, "refund");
            return (Criteria) this;
        }

        public Criteria andRefundLessThan(Date value) {
            addCriterion("refund <", value, "refund");
            return (Criteria) this;
        }

        public Criteria andRefundLessThanOrEqualTo(Date value) {
            addCriterion("refund <=", value, "refund");
            return (Criteria) this;
        }

        public Criteria andRefundIn(List<Date> values) {
            addCriterion("refund in", values, "refund");
            return (Criteria) this;
        }

        public Criteria andRefundNotIn(List<Date> values) {
            addCriterion("refund not in", values, "refund");
            return (Criteria) this;
        }

        public Criteria andRefundBetween(Date value1, Date value2) {
            addCriterion("refund between", value1, value2, "refund");
            return (Criteria) this;
        }

        public Criteria andRefundNotBetween(Date value1, Date value2) {
            addCriterion("refund not between", value1, value2, "refund");
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

        public Criteria andVoucherCodeIsNull() {
            addCriterion("voucher_code is null");
            return (Criteria) this;
        }

        public Criteria andVoucherCodeIsNotNull() {
            addCriterion("voucher_code is not null");
            return (Criteria) this;
        }

        public Criteria andVoucherCodeEqualTo(String value) {
            addCriterion("voucher_code =", value, "voucherCode");
            return (Criteria) this;
        }

        public Criteria andVoucherCodeNotEqualTo(String value) {
            addCriterion("voucher_code <>", value, "voucherCode");
            return (Criteria) this;
        }

        public Criteria andVoucherCodeGreaterThan(String value) {
            addCriterion("voucher_code >", value, "voucherCode");
            return (Criteria) this;
        }

        public Criteria andVoucherCodeGreaterThanOrEqualTo(String value) {
            addCriterion("voucher_code >=", value, "voucherCode");
            return (Criteria) this;
        }

        public Criteria andVoucherCodeLessThan(String value) {
            addCriterion("voucher_code <", value, "voucherCode");
            return (Criteria) this;
        }

        public Criteria andVoucherCodeLessThanOrEqualTo(String value) {
            addCriterion("voucher_code <=", value, "voucherCode");
            return (Criteria) this;
        }

        public Criteria andVoucherCodeLike(String value) {
            addCriterion("voucher_code like", value, "voucherCode");
            return (Criteria) this;
        }

        public Criteria andVoucherCodeNotLike(String value) {
            addCriterion("voucher_code not like", value, "voucherCode");
            return (Criteria) this;
        }

        public Criteria andVoucherCodeIn(List<String> values) {
            addCriterion("voucher_code in", values, "voucherCode");
            return (Criteria) this;
        }

        public Criteria andVoucherCodeNotIn(List<String> values) {
            addCriterion("voucher_code not in", values, "voucherCode");
            return (Criteria) this;
        }

        public Criteria andVoucherCodeBetween(String value1, String value2) {
            addCriterion("voucher_code between", value1, value2, "voucherCode");
            return (Criteria) this;
        }

        public Criteria andVoucherCodeNotBetween(String value1, String value2) {
            addCriterion("voucher_code not between", value1, value2, "voucherCode");
            return (Criteria) this;
        }

        public Criteria andActualAmountIsNull() {
            addCriterion("actual_amount is null");
            return (Criteria) this;
        }

        public Criteria andActualAmountIsNotNull() {
            addCriterion("actual_amount is not null");
            return (Criteria) this;
        }

        public Criteria andActualAmountEqualTo(Integer value) {
            addCriterion("actual_amount =", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountNotEqualTo(Integer value) {
            addCriterion("actual_amount <>", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountGreaterThan(Integer value) {
            addCriterion("actual_amount >", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("actual_amount >=", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountLessThan(Integer value) {
            addCriterion("actual_amount <", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountLessThanOrEqualTo(Integer value) {
            addCriterion("actual_amount <=", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountIn(List<Integer> values) {
            addCriterion("actual_amount in", values, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountNotIn(List<Integer> values) {
            addCriterion("actual_amount not in", values, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountBetween(Integer value1, Integer value2) {
            addCriterion("actual_amount between", value1, value2, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("actual_amount not between", value1, value2, "actualAmount");
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