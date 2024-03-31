package com.njupt.epsm.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TRangeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TRangeExample() {
        oredCriteria = new ArrayList<>();
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
            criteria = new ArrayList<>();
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andRangeNameIsNull() {
            addCriterion("range_name is null");
            return (Criteria) this;
        }

        public Criteria andRangeNameIsNotNull() {
            addCriterion("range_name is not null");
            return (Criteria) this;
        }

        public Criteria andRangeNameEqualTo(String value) {
            addCriterion("range_name =", value, "rangeName");
            return (Criteria) this;
        }

        public Criteria andRangeNameNotEqualTo(String value) {
            addCriterion("range_name <>", value, "rangeName");
            return (Criteria) this;
        }

        public Criteria andRangeNameGreaterThan(String value) {
            addCriterion("range_name >", value, "rangeName");
            return (Criteria) this;
        }

        public Criteria andRangeNameGreaterThanOrEqualTo(String value) {
            addCriterion("range_name >=", value, "rangeName");
            return (Criteria) this;
        }

        public Criteria andRangeNameLessThan(String value) {
            addCriterion("range_name <", value, "rangeName");
            return (Criteria) this;
        }

        public Criteria andRangeNameLessThanOrEqualTo(String value) {
            addCriterion("range_name <=", value, "rangeName");
            return (Criteria) this;
        }

        public Criteria andRangeNameLike(String value) {
            addCriterion("range_name like", value, "rangeName");
            return (Criteria) this;
        }

        public Criteria andRangeNameNotLike(String value) {
            addCriterion("range_name not like", value, "rangeName");
            return (Criteria) this;
        }

        public Criteria andRangeNameIn(List<String> values) {
            addCriterion("range_name in", values, "rangeName");
            return (Criteria) this;
        }

        public Criteria andRangeNameNotIn(List<String> values) {
            addCriterion("range_name not in", values, "rangeName");
            return (Criteria) this;
        }

        public Criteria andRangeNameBetween(String value1, String value2) {
            addCriterion("range_name between", value1, value2, "rangeName");
            return (Criteria) this;
        }

        public Criteria andRangeNameNotBetween(String value1, String value2) {
            addCriterion("range_name not between", value1, value2, "rangeName");
            return (Criteria) this;
        }

        public Criteria andExerciseIdIsNull() {
            addCriterion("exercise_id is null");
            return (Criteria) this;
        }

        public Criteria andExerciseIdIsNotNull() {
            addCriterion("exercise_id is not null");
            return (Criteria) this;
        }

        public Criteria andExerciseIdEqualTo(Long value) {
            addCriterion("exercise_id =", value, "exerciseId");
            return (Criteria) this;
        }

        public Criteria andExerciseIdNotEqualTo(Long value) {
            addCriterion("exercise_id <>", value, "exerciseId");
            return (Criteria) this;
        }

        public Criteria andExerciseIdGreaterThan(Long value) {
            addCriterion("exercise_id >", value, "exerciseId");
            return (Criteria) this;
        }

        public Criteria andExerciseIdGreaterThanOrEqualTo(Long value) {
            addCriterion("exercise_id >=", value, "exerciseId");
            return (Criteria) this;
        }

        public Criteria andExerciseIdLessThan(Long value) {
            addCriterion("exercise_id <", value, "exerciseId");
            return (Criteria) this;
        }

        public Criteria andExerciseIdLessThanOrEqualTo(Long value) {
            addCriterion("exercise_id <=", value, "exerciseId");
            return (Criteria) this;
        }

        public Criteria andExerciseIdIn(List<Long> values) {
            addCriterion("exercise_id in", values, "exerciseId");
            return (Criteria) this;
        }

        public Criteria andExerciseIdNotIn(List<Long> values) {
            addCriterion("exercise_id not in", values, "exerciseId");
            return (Criteria) this;
        }

        public Criteria andExerciseIdBetween(Long value1, Long value2) {
            addCriterion("exercise_id between", value1, value2, "exerciseId");
            return (Criteria) this;
        }

        public Criteria andExerciseIdNotBetween(Long value1, Long value2) {
            addCriterion("exercise_id not between", value1, value2, "exerciseId");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
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

        public Criteria andDigBugNumsIsNull() {
            addCriterion("dig_bug_nums is null");
            return (Criteria) this;
        }

        public Criteria andDigBugNumsIsNotNull() {
            addCriterion("dig_bug_nums is not null");
            return (Criteria) this;
        }

        public Criteria andDigBugNumsEqualTo(Integer value) {
            addCriterion("dig_bug_nums =", value, "digBugNums");
            return (Criteria) this;
        }

        public Criteria andDigBugNumsNotEqualTo(Integer value) {
            addCriterion("dig_bug_nums <>", value, "digBugNums");
            return (Criteria) this;
        }

        public Criteria andDigBugNumsGreaterThan(Integer value) {
            addCriterion("dig_bug_nums >", value, "digBugNums");
            return (Criteria) this;
        }

        public Criteria andDigBugNumsGreaterThanOrEqualTo(Integer value) {
            addCriterion("dig_bug_nums >=", value, "digBugNums");
            return (Criteria) this;
        }

        public Criteria andDigBugNumsLessThan(Integer value) {
            addCriterion("dig_bug_nums <", value, "digBugNums");
            return (Criteria) this;
        }

        public Criteria andDigBugNumsLessThanOrEqualTo(Integer value) {
            addCriterion("dig_bug_nums <=", value, "digBugNums");
            return (Criteria) this;
        }

        public Criteria andDigBugNumsIn(List<Integer> values) {
            addCriterion("dig_bug_nums in", values, "digBugNums");
            return (Criteria) this;
        }

        public Criteria andDigBugNumsNotIn(List<Integer> values) {
            addCriterion("dig_bug_nums not in", values, "digBugNums");
            return (Criteria) this;
        }

        public Criteria andDigBugNumsBetween(Integer value1, Integer value2) {
            addCriterion("dig_bug_nums between", value1, value2, "digBugNums");
            return (Criteria) this;
        }

        public Criteria andDigBugNumsNotBetween(Integer value1, Integer value2) {
            addCriterion("dig_bug_nums not between", value1, value2, "digBugNums");
            return (Criteria) this;
        }

        public Criteria andAttackTimesIsNull() {
            addCriterion("attack_times is null");
            return (Criteria) this;
        }

        public Criteria andAttackTimesIsNotNull() {
            addCriterion("attack_times is not null");
            return (Criteria) this;
        }

        public Criteria andAttackTimesEqualTo(Integer value) {
            addCriterion("attack_times =", value, "attackTimes");
            return (Criteria) this;
        }

        public Criteria andAttackTimesNotEqualTo(Integer value) {
            addCriterion("attack_times <>", value, "attackTimes");
            return (Criteria) this;
        }

        public Criteria andAttackTimesGreaterThan(Integer value) {
            addCriterion("attack_times >", value, "attackTimes");
            return (Criteria) this;
        }

        public Criteria andAttackTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("attack_times >=", value, "attackTimes");
            return (Criteria) this;
        }

        public Criteria andAttackTimesLessThan(Integer value) {
            addCriterion("attack_times <", value, "attackTimes");
            return (Criteria) this;
        }

        public Criteria andAttackTimesLessThanOrEqualTo(Integer value) {
            addCriterion("attack_times <=", value, "attackTimes");
            return (Criteria) this;
        }

        public Criteria andAttackTimesIn(List<Integer> values) {
            addCriterion("attack_times in", values, "attackTimes");
            return (Criteria) this;
        }

        public Criteria andAttackTimesNotIn(List<Integer> values) {
            addCriterion("attack_times not in", values, "attackTimes");
            return (Criteria) this;
        }

        public Criteria andAttackTimesBetween(Integer value1, Integer value2) {
            addCriterion("attack_times between", value1, value2, "attackTimes");
            return (Criteria) this;
        }

        public Criteria andAttackTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("attack_times not between", value1, value2, "attackTimes");
            return (Criteria) this;
        }

        public Criteria andDefendTimeIsNull() {
            addCriterion("defend_time is null");
            return (Criteria) this;
        }

        public Criteria andDefendTimeIsNotNull() {
            addCriterion("defend_time is not null");
            return (Criteria) this;
        }

        public Criteria andDefendTimeEqualTo(Integer value) {
            addCriterion("defend_time =", value, "defendTime");
            return (Criteria) this;
        }

        public Criteria andDefendTimeNotEqualTo(Integer value) {
            addCriterion("defend_time <>", value, "defendTime");
            return (Criteria) this;
        }

        public Criteria andDefendTimeGreaterThan(Integer value) {
            addCriterion("defend_time >", value, "defendTime");
            return (Criteria) this;
        }

        public Criteria andDefendTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("defend_time >=", value, "defendTime");
            return (Criteria) this;
        }

        public Criteria andDefendTimeLessThan(Integer value) {
            addCriterion("defend_time <", value, "defendTime");
            return (Criteria) this;
        }

        public Criteria andDefendTimeLessThanOrEqualTo(Integer value) {
            addCriterion("defend_time <=", value, "defendTime");
            return (Criteria) this;
        }

        public Criteria andDefendTimeIn(List<Integer> values) {
            addCriterion("defend_time in", values, "defendTime");
            return (Criteria) this;
        }

        public Criteria andDefendTimeNotIn(List<Integer> values) {
            addCriterion("defend_time not in", values, "defendTime");
            return (Criteria) this;
        }

        public Criteria andDefendTimeBetween(Integer value1, Integer value2) {
            addCriterion("defend_time between", value1, value2, "defendTime");
            return (Criteria) this;
        }

        public Criteria andDefendTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("defend_time not between", value1, value2, "defendTime");
            return (Criteria) this;
        }

        public Criteria andDefendPercentIsNull() {
            addCriterion("defend_percent is null");
            return (Criteria) this;
        }

        public Criteria andDefendPercentIsNotNull() {
            addCriterion("defend_percent is not null");
            return (Criteria) this;
        }

        public Criteria andDefendPercentEqualTo(BigDecimal value) {
            addCriterion("defend_percent =", value, "defendPercent");
            return (Criteria) this;
        }

        public Criteria andDefendPercentNotEqualTo(BigDecimal value) {
            addCriterion("defend_percent <>", value, "defendPercent");
            return (Criteria) this;
        }

        public Criteria andDefendPercentGreaterThan(BigDecimal value) {
            addCriterion("defend_percent >", value, "defendPercent");
            return (Criteria) this;
        }

        public Criteria andDefendPercentGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("defend_percent >=", value, "defendPercent");
            return (Criteria) this;
        }

        public Criteria andDefendPercentLessThan(BigDecimal value) {
            addCriterion("defend_percent <", value, "defendPercent");
            return (Criteria) this;
        }

        public Criteria andDefendPercentLessThanOrEqualTo(BigDecimal value) {
            addCriterion("defend_percent <=", value, "defendPercent");
            return (Criteria) this;
        }

        public Criteria andDefendPercentIn(List<BigDecimal> values) {
            addCriterion("defend_percent in", values, "defendPercent");
            return (Criteria) this;
        }

        public Criteria andDefendPercentNotIn(List<BigDecimal> values) {
            addCriterion("defend_percent not in", values, "defendPercent");
            return (Criteria) this;
        }

        public Criteria andDefendPercentBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("defend_percent between", value1, value2, "defendPercent");
            return (Criteria) this;
        }

        public Criteria andDefendPercentNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("defend_percent not between", value1, value2, "defendPercent");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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