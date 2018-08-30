package cn.anyoufang.entity;

import java.util.ArrayList;
import java.util.List;

public class TbPaymentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbPaymentExample() {
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

        public Criteria andMonthlyPaymentIsNull() {
            addCriterion("monthly_payment is null");
            return (Criteria) this;
        }

        public Criteria andMonthlyPaymentIsNotNull() {
            addCriterion("monthly_payment is not null");
            return (Criteria) this;
        }

        public Criteria andMonthlyPaymentEqualTo(Byte value) {
            addCriterion("monthly_payment =", value, "monthlyPayment");
            return (Criteria) this;
        }

        public Criteria andMonthlyPaymentNotEqualTo(Byte value) {
            addCriterion("monthly_payment <>", value, "monthlyPayment");
            return (Criteria) this;
        }

        public Criteria andMonthlyPaymentGreaterThan(Byte value) {
            addCriterion("monthly_payment >", value, "monthlyPayment");
            return (Criteria) this;
        }

        public Criteria andMonthlyPaymentGreaterThanOrEqualTo(Byte value) {
            addCriterion("monthly_payment >=", value, "monthlyPayment");
            return (Criteria) this;
        }

        public Criteria andMonthlyPaymentLessThan(Byte value) {
            addCriterion("monthly_payment <", value, "monthlyPayment");
            return (Criteria) this;
        }

        public Criteria andMonthlyPaymentLessThanOrEqualTo(Byte value) {
            addCriterion("monthly_payment <=", value, "monthlyPayment");
            return (Criteria) this;
        }

        public Criteria andMonthlyPaymentIn(List<Byte> values) {
            addCriterion("monthly_payment in", values, "monthlyPayment");
            return (Criteria) this;
        }

        public Criteria andMonthlyPaymentNotIn(List<Byte> values) {
            addCriterion("monthly_payment not in", values, "monthlyPayment");
            return (Criteria) this;
        }

        public Criteria andMonthlyPaymentBetween(Byte value1, Byte value2) {
            addCriterion("monthly_payment between", value1, value2, "monthlyPayment");
            return (Criteria) this;
        }

        public Criteria andMonthlyPaymentNotBetween(Byte value1, Byte value2) {
            addCriterion("monthly_payment not between", value1, value2, "monthlyPayment");
            return (Criteria) this;
        }

        public Criteria andQuarterlyPaymentIsNull() {
            addCriterion("quarterly_payment is null");
            return (Criteria) this;
        }

        public Criteria andQuarterlyPaymentIsNotNull() {
            addCriterion("quarterly_payment is not null");
            return (Criteria) this;
        }

        public Criteria andQuarterlyPaymentEqualTo(Byte value) {
            addCriterion("quarterly_payment =", value, "quarterlyPayment");
            return (Criteria) this;
        }

        public Criteria andQuarterlyPaymentNotEqualTo(Byte value) {
            addCriterion("quarterly_payment <>", value, "quarterlyPayment");
            return (Criteria) this;
        }

        public Criteria andQuarterlyPaymentGreaterThan(Byte value) {
            addCriterion("quarterly_payment >", value, "quarterlyPayment");
            return (Criteria) this;
        }

        public Criteria andQuarterlyPaymentGreaterThanOrEqualTo(Byte value) {
            addCriterion("quarterly_payment >=", value, "quarterlyPayment");
            return (Criteria) this;
        }

        public Criteria andQuarterlyPaymentLessThan(Byte value) {
            addCriterion("quarterly_payment <", value, "quarterlyPayment");
            return (Criteria) this;
        }

        public Criteria andQuarterlyPaymentLessThanOrEqualTo(Byte value) {
            addCriterion("quarterly_payment <=", value, "quarterlyPayment");
            return (Criteria) this;
        }

        public Criteria andQuarterlyPaymentIn(List<Byte> values) {
            addCriterion("quarterly_payment in", values, "quarterlyPayment");
            return (Criteria) this;
        }

        public Criteria andQuarterlyPaymentNotIn(List<Byte> values) {
            addCriterion("quarterly_payment not in", values, "quarterlyPayment");
            return (Criteria) this;
        }

        public Criteria andQuarterlyPaymentBetween(Byte value1, Byte value2) {
            addCriterion("quarterly_payment between", value1, value2, "quarterlyPayment");
            return (Criteria) this;
        }

        public Criteria andQuarterlyPaymentNotBetween(Byte value1, Byte value2) {
            addCriterion("quarterly_payment not between", value1, value2, "quarterlyPayment");
            return (Criteria) this;
        }

        public Criteria andHalfYearPaymentIsNull() {
            addCriterion("half_year_payment is null");
            return (Criteria) this;
        }

        public Criteria andHalfYearPaymentIsNotNull() {
            addCriterion("half_year_payment is not null");
            return (Criteria) this;
        }

        public Criteria andHalfYearPaymentEqualTo(Byte value) {
            addCriterion("half_year_payment =", value, "halfYearPayment");
            return (Criteria) this;
        }

        public Criteria andHalfYearPaymentNotEqualTo(Byte value) {
            addCriterion("half_year_payment <>", value, "halfYearPayment");
            return (Criteria) this;
        }

        public Criteria andHalfYearPaymentGreaterThan(Byte value) {
            addCriterion("half_year_payment >", value, "halfYearPayment");
            return (Criteria) this;
        }

        public Criteria andHalfYearPaymentGreaterThanOrEqualTo(Byte value) {
            addCriterion("half_year_payment >=", value, "halfYearPayment");
            return (Criteria) this;
        }

        public Criteria andHalfYearPaymentLessThan(Byte value) {
            addCriterion("half_year_payment <", value, "halfYearPayment");
            return (Criteria) this;
        }

        public Criteria andHalfYearPaymentLessThanOrEqualTo(Byte value) {
            addCriterion("half_year_payment <=", value, "halfYearPayment");
            return (Criteria) this;
        }

        public Criteria andHalfYearPaymentIn(List<Byte> values) {
            addCriterion("half_year_payment in", values, "halfYearPayment");
            return (Criteria) this;
        }

        public Criteria andHalfYearPaymentNotIn(List<Byte> values) {
            addCriterion("half_year_payment not in", values, "halfYearPayment");
            return (Criteria) this;
        }

        public Criteria andHalfYearPaymentBetween(Byte value1, Byte value2) {
            addCriterion("half_year_payment between", value1, value2, "halfYearPayment");
            return (Criteria) this;
        }

        public Criteria andHalfYearPaymentNotBetween(Byte value1, Byte value2) {
            addCriterion("half_year_payment not between", value1, value2, "halfYearPayment");
            return (Criteria) this;
        }

        public Criteria andAnnualPaymentIsNull() {
            addCriterion("annual_payment is null");
            return (Criteria) this;
        }

        public Criteria andAnnualPaymentIsNotNull() {
            addCriterion("annual_payment is not null");
            return (Criteria) this;
        }

        public Criteria andAnnualPaymentEqualTo(Byte value) {
            addCriterion("annual_payment =", value, "annualPayment");
            return (Criteria) this;
        }

        public Criteria andAnnualPaymentNotEqualTo(Byte value) {
            addCriterion("annual_payment <>", value, "annualPayment");
            return (Criteria) this;
        }

        public Criteria andAnnualPaymentGreaterThan(Byte value) {
            addCriterion("annual_payment >", value, "annualPayment");
            return (Criteria) this;
        }

        public Criteria andAnnualPaymentGreaterThanOrEqualTo(Byte value) {
            addCriterion("annual_payment >=", value, "annualPayment");
            return (Criteria) this;
        }

        public Criteria andAnnualPaymentLessThan(Byte value) {
            addCriterion("annual_payment <", value, "annualPayment");
            return (Criteria) this;
        }

        public Criteria andAnnualPaymentLessThanOrEqualTo(Byte value) {
            addCriterion("annual_payment <=", value, "annualPayment");
            return (Criteria) this;
        }

        public Criteria andAnnualPaymentIn(List<Byte> values) {
            addCriterion("annual_payment in", values, "annualPayment");
            return (Criteria) this;
        }

        public Criteria andAnnualPaymentNotIn(List<Byte> values) {
            addCriterion("annual_payment not in", values, "annualPayment");
            return (Criteria) this;
        }

        public Criteria andAnnualPaymentBetween(Byte value1, Byte value2) {
            addCriterion("annual_payment between", value1, value2, "annualPayment");
            return (Criteria) this;
        }

        public Criteria andAnnualPaymentNotBetween(Byte value1, Byte value2) {
            addCriterion("annual_payment not between", value1, value2, "annualPayment");
            return (Criteria) this;
        }

        public Criteria andMonthlyMoneyIsNull() {
            addCriterion("monthly_money is null");
            return (Criteria) this;
        }

        public Criteria andMonthlyMoneyIsNotNull() {
            addCriterion("monthly_money is not null");
            return (Criteria) this;
        }

        public Criteria andMonthlyMoneyEqualTo(Integer value) {
            addCriterion("monthly_money =", value, "monthlyMoney");
            return (Criteria) this;
        }

        public Criteria andMonthlyMoneyNotEqualTo(Integer value) {
            addCriterion("monthly_money <>", value, "monthlyMoney");
            return (Criteria) this;
        }

        public Criteria andMonthlyMoneyGreaterThan(Integer value) {
            addCriterion("monthly_money >", value, "monthlyMoney");
            return (Criteria) this;
        }

        public Criteria andMonthlyMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("monthly_money >=", value, "monthlyMoney");
            return (Criteria) this;
        }

        public Criteria andMonthlyMoneyLessThan(Integer value) {
            addCriterion("monthly_money <", value, "monthlyMoney");
            return (Criteria) this;
        }

        public Criteria andMonthlyMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("monthly_money <=", value, "monthlyMoney");
            return (Criteria) this;
        }

        public Criteria andMonthlyMoneyIn(List<Integer> values) {
            addCriterion("monthly_money in", values, "monthlyMoney");
            return (Criteria) this;
        }

        public Criteria andMonthlyMoneyNotIn(List<Integer> values) {
            addCriterion("monthly_money not in", values, "monthlyMoney");
            return (Criteria) this;
        }

        public Criteria andMonthlyMoneyBetween(Integer value1, Integer value2) {
            addCriterion("monthly_money between", value1, value2, "monthlyMoney");
            return (Criteria) this;
        }

        public Criteria andMonthlyMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("monthly_money not between", value1, value2, "monthlyMoney");
            return (Criteria) this;
        }

        public Criteria andMonthlyPledgeIsNull() {
            addCriterion("monthly_pledge is null");
            return (Criteria) this;
        }

        public Criteria andMonthlyPledgeIsNotNull() {
            addCriterion("monthly_pledge is not null");
            return (Criteria) this;
        }

        public Criteria andMonthlyPledgeEqualTo(Integer value) {
            addCriterion("monthly_pledge =", value, "monthlyPledge");
            return (Criteria) this;
        }

        public Criteria andMonthlyPledgeNotEqualTo(Integer value) {
            addCriterion("monthly_pledge <>", value, "monthlyPledge");
            return (Criteria) this;
        }

        public Criteria andMonthlyPledgeGreaterThan(Integer value) {
            addCriterion("monthly_pledge >", value, "monthlyPledge");
            return (Criteria) this;
        }

        public Criteria andMonthlyPledgeGreaterThanOrEqualTo(Integer value) {
            addCriterion("monthly_pledge >=", value, "monthlyPledge");
            return (Criteria) this;
        }

        public Criteria andMonthlyPledgeLessThan(Integer value) {
            addCriterion("monthly_pledge <", value, "monthlyPledge");
            return (Criteria) this;
        }

        public Criteria andMonthlyPledgeLessThanOrEqualTo(Integer value) {
            addCriterion("monthly_pledge <=", value, "monthlyPledge");
            return (Criteria) this;
        }

        public Criteria andMonthlyPledgeIn(List<Integer> values) {
            addCriterion("monthly_pledge in", values, "monthlyPledge");
            return (Criteria) this;
        }

        public Criteria andMonthlyPledgeNotIn(List<Integer> values) {
            addCriterion("monthly_pledge not in", values, "monthlyPledge");
            return (Criteria) this;
        }

        public Criteria andMonthlyPledgeBetween(Integer value1, Integer value2) {
            addCriterion("monthly_pledge between", value1, value2, "monthlyPledge");
            return (Criteria) this;
        }

        public Criteria andMonthlyPledgeNotBetween(Integer value1, Integer value2) {
            addCriterion("monthly_pledge not between", value1, value2, "monthlyPledge");
            return (Criteria) this;
        }

        public Criteria andQuarterlyMoneyIsNull() {
            addCriterion("quarterly_money is null");
            return (Criteria) this;
        }

        public Criteria andQuarterlyMoneyIsNotNull() {
            addCriterion("quarterly_money is not null");
            return (Criteria) this;
        }

        public Criteria andQuarterlyMoneyEqualTo(Integer value) {
            addCriterion("quarterly_money =", value, "quarterlyMoney");
            return (Criteria) this;
        }

        public Criteria andQuarterlyMoneyNotEqualTo(Integer value) {
            addCriterion("quarterly_money <>", value, "quarterlyMoney");
            return (Criteria) this;
        }

        public Criteria andQuarterlyMoneyGreaterThan(Integer value) {
            addCriterion("quarterly_money >", value, "quarterlyMoney");
            return (Criteria) this;
        }

        public Criteria andQuarterlyMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("quarterly_money >=", value, "quarterlyMoney");
            return (Criteria) this;
        }

        public Criteria andQuarterlyMoneyLessThan(Integer value) {
            addCriterion("quarterly_money <", value, "quarterlyMoney");
            return (Criteria) this;
        }

        public Criteria andQuarterlyMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("quarterly_money <=", value, "quarterlyMoney");
            return (Criteria) this;
        }

        public Criteria andQuarterlyMoneyIn(List<Integer> values) {
            addCriterion("quarterly_money in", values, "quarterlyMoney");
            return (Criteria) this;
        }

        public Criteria andQuarterlyMoneyNotIn(List<Integer> values) {
            addCriterion("quarterly_money not in", values, "quarterlyMoney");
            return (Criteria) this;
        }

        public Criteria andQuarterlyMoneyBetween(Integer value1, Integer value2) {
            addCriterion("quarterly_money between", value1, value2, "quarterlyMoney");
            return (Criteria) this;
        }

        public Criteria andQuarterlyMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("quarterly_money not between", value1, value2, "quarterlyMoney");
            return (Criteria) this;
        }

        public Criteria andQuarterlyPledgeIsNull() {
            addCriterion("quarterly_pledge is null");
            return (Criteria) this;
        }

        public Criteria andQuarterlyPledgeIsNotNull() {
            addCriterion("quarterly_pledge is not null");
            return (Criteria) this;
        }

        public Criteria andQuarterlyPledgeEqualTo(Integer value) {
            addCriterion("quarterly_pledge =", value, "quarterlyPledge");
            return (Criteria) this;
        }

        public Criteria andQuarterlyPledgeNotEqualTo(Integer value) {
            addCriterion("quarterly_pledge <>", value, "quarterlyPledge");
            return (Criteria) this;
        }

        public Criteria andQuarterlyPledgeGreaterThan(Integer value) {
            addCriterion("quarterly_pledge >", value, "quarterlyPledge");
            return (Criteria) this;
        }

        public Criteria andQuarterlyPledgeGreaterThanOrEqualTo(Integer value) {
            addCriterion("quarterly_pledge >=", value, "quarterlyPledge");
            return (Criteria) this;
        }

        public Criteria andQuarterlyPledgeLessThan(Integer value) {
            addCriterion("quarterly_pledge <", value, "quarterlyPledge");
            return (Criteria) this;
        }

        public Criteria andQuarterlyPledgeLessThanOrEqualTo(Integer value) {
            addCriterion("quarterly_pledge <=", value, "quarterlyPledge");
            return (Criteria) this;
        }

        public Criteria andQuarterlyPledgeIn(List<Integer> values) {
            addCriterion("quarterly_pledge in", values, "quarterlyPledge");
            return (Criteria) this;
        }

        public Criteria andQuarterlyPledgeNotIn(List<Integer> values) {
            addCriterion("quarterly_pledge not in", values, "quarterlyPledge");
            return (Criteria) this;
        }

        public Criteria andQuarterlyPledgeBetween(Integer value1, Integer value2) {
            addCriterion("quarterly_pledge between", value1, value2, "quarterlyPledge");
            return (Criteria) this;
        }

        public Criteria andQuarterlyPledgeNotBetween(Integer value1, Integer value2) {
            addCriterion("quarterly_pledge not between", value1, value2, "quarterlyPledge");
            return (Criteria) this;
        }

        public Criteria andHalfYearMoneyIsNull() {
            addCriterion("half_year_money is null");
            return (Criteria) this;
        }

        public Criteria andHalfYearMoneyIsNotNull() {
            addCriterion("half_year_money is not null");
            return (Criteria) this;
        }

        public Criteria andHalfYearMoneyEqualTo(Integer value) {
            addCriterion("half_year_money =", value, "halfYearMoney");
            return (Criteria) this;
        }

        public Criteria andHalfYearMoneyNotEqualTo(Integer value) {
            addCriterion("half_year_money <>", value, "halfYearMoney");
            return (Criteria) this;
        }

        public Criteria andHalfYearMoneyGreaterThan(Integer value) {
            addCriterion("half_year_money >", value, "halfYearMoney");
            return (Criteria) this;
        }

        public Criteria andHalfYearMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("half_year_money >=", value, "halfYearMoney");
            return (Criteria) this;
        }

        public Criteria andHalfYearMoneyLessThan(Integer value) {
            addCriterion("half_year_money <", value, "halfYearMoney");
            return (Criteria) this;
        }

        public Criteria andHalfYearMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("half_year_money <=", value, "halfYearMoney");
            return (Criteria) this;
        }

        public Criteria andHalfYearMoneyIn(List<Integer> values) {
            addCriterion("half_year_money in", values, "halfYearMoney");
            return (Criteria) this;
        }

        public Criteria andHalfYearMoneyNotIn(List<Integer> values) {
            addCriterion("half_year_money not in", values, "halfYearMoney");
            return (Criteria) this;
        }

        public Criteria andHalfYearMoneyBetween(Integer value1, Integer value2) {
            addCriterion("half_year_money between", value1, value2, "halfYearMoney");
            return (Criteria) this;
        }

        public Criteria andHalfYearMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("half_year_money not between", value1, value2, "halfYearMoney");
            return (Criteria) this;
        }

        public Criteria andHalfYearPledgeIsNull() {
            addCriterion("half_year_pledge is null");
            return (Criteria) this;
        }

        public Criteria andHalfYearPledgeIsNotNull() {
            addCriterion("half_year_pledge is not null");
            return (Criteria) this;
        }

        public Criteria andHalfYearPledgeEqualTo(Integer value) {
            addCriterion("half_year_pledge =", value, "halfYearPledge");
            return (Criteria) this;
        }

        public Criteria andHalfYearPledgeNotEqualTo(Integer value) {
            addCriterion("half_year_pledge <>", value, "halfYearPledge");
            return (Criteria) this;
        }

        public Criteria andHalfYearPledgeGreaterThan(Integer value) {
            addCriterion("half_year_pledge >", value, "halfYearPledge");
            return (Criteria) this;
        }

        public Criteria andHalfYearPledgeGreaterThanOrEqualTo(Integer value) {
            addCriterion("half_year_pledge >=", value, "halfYearPledge");
            return (Criteria) this;
        }

        public Criteria andHalfYearPledgeLessThan(Integer value) {
            addCriterion("half_year_pledge <", value, "halfYearPledge");
            return (Criteria) this;
        }

        public Criteria andHalfYearPledgeLessThanOrEqualTo(Integer value) {
            addCriterion("half_year_pledge <=", value, "halfYearPledge");
            return (Criteria) this;
        }

        public Criteria andHalfYearPledgeIn(List<Integer> values) {
            addCriterion("half_year_pledge in", values, "halfYearPledge");
            return (Criteria) this;
        }

        public Criteria andHalfYearPledgeNotIn(List<Integer> values) {
            addCriterion("half_year_pledge not in", values, "halfYearPledge");
            return (Criteria) this;
        }

        public Criteria andHalfYearPledgeBetween(Integer value1, Integer value2) {
            addCriterion("half_year_pledge between", value1, value2, "halfYearPledge");
            return (Criteria) this;
        }

        public Criteria andHalfYearPledgeNotBetween(Integer value1, Integer value2) {
            addCriterion("half_year_pledge not between", value1, value2, "halfYearPledge");
            return (Criteria) this;
        }

        public Criteria andAnnualMoneyIsNull() {
            addCriterion("annual_money is null");
            return (Criteria) this;
        }

        public Criteria andAnnualMoneyIsNotNull() {
            addCriterion("annual_money is not null");
            return (Criteria) this;
        }

        public Criteria andAnnualMoneyEqualTo(Integer value) {
            addCriterion("annual_money =", value, "annualMoney");
            return (Criteria) this;
        }

        public Criteria andAnnualMoneyNotEqualTo(Integer value) {
            addCriterion("annual_money <>", value, "annualMoney");
            return (Criteria) this;
        }

        public Criteria andAnnualMoneyGreaterThan(Integer value) {
            addCriterion("annual_money >", value, "annualMoney");
            return (Criteria) this;
        }

        public Criteria andAnnualMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("annual_money >=", value, "annualMoney");
            return (Criteria) this;
        }

        public Criteria andAnnualMoneyLessThan(Integer value) {
            addCriterion("annual_money <", value, "annualMoney");
            return (Criteria) this;
        }

        public Criteria andAnnualMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("annual_money <=", value, "annualMoney");
            return (Criteria) this;
        }

        public Criteria andAnnualMoneyIn(List<Integer> values) {
            addCriterion("annual_money in", values, "annualMoney");
            return (Criteria) this;
        }

        public Criteria andAnnualMoneyNotIn(List<Integer> values) {
            addCriterion("annual_money not in", values, "annualMoney");
            return (Criteria) this;
        }

        public Criteria andAnnualMoneyBetween(Integer value1, Integer value2) {
            addCriterion("annual_money between", value1, value2, "annualMoney");
            return (Criteria) this;
        }

        public Criteria andAnnualMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("annual_money not between", value1, value2, "annualMoney");
            return (Criteria) this;
        }

        public Criteria andAnnualPledgeIsNull() {
            addCriterion("annual_pledge is null");
            return (Criteria) this;
        }

        public Criteria andAnnualPledgeIsNotNull() {
            addCriterion("annual_pledge is not null");
            return (Criteria) this;
        }

        public Criteria andAnnualPledgeEqualTo(Integer value) {
            addCriterion("annual_pledge =", value, "annualPledge");
            return (Criteria) this;
        }

        public Criteria andAnnualPledgeNotEqualTo(Integer value) {
            addCriterion("annual_pledge <>", value, "annualPledge");
            return (Criteria) this;
        }

        public Criteria andAnnualPledgeGreaterThan(Integer value) {
            addCriterion("annual_pledge >", value, "annualPledge");
            return (Criteria) this;
        }

        public Criteria andAnnualPledgeGreaterThanOrEqualTo(Integer value) {
            addCriterion("annual_pledge >=", value, "annualPledge");
            return (Criteria) this;
        }

        public Criteria andAnnualPledgeLessThan(Integer value) {
            addCriterion("annual_pledge <", value, "annualPledge");
            return (Criteria) this;
        }

        public Criteria andAnnualPledgeLessThanOrEqualTo(Integer value) {
            addCriterion("annual_pledge <=", value, "annualPledge");
            return (Criteria) this;
        }

        public Criteria andAnnualPledgeIn(List<Integer> values) {
            addCriterion("annual_pledge in", values, "annualPledge");
            return (Criteria) this;
        }

        public Criteria andAnnualPledgeNotIn(List<Integer> values) {
            addCriterion("annual_pledge not in", values, "annualPledge");
            return (Criteria) this;
        }

        public Criteria andAnnualPledgeBetween(Integer value1, Integer value2) {
            addCriterion("annual_pledge between", value1, value2, "annualPledge");
            return (Criteria) this;
        }

        public Criteria andAnnualPledgeNotBetween(Integer value1, Integer value2) {
            addCriterion("annual_pledge not between", value1, value2, "annualPledge");
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