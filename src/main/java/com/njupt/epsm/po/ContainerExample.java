package com.njupt.epsm.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContainerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ContainerExample() {
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

        public Criteria andContainerNameIsNull() {
            addCriterion("container_name is null");
            return (Criteria) this;
        }

        public Criteria andContainerNameIsNotNull() {
            addCriterion("container_name is not null");
            return (Criteria) this;
        }

        public Criteria andContainerNameEqualTo(String value) {
            addCriterion("container_name =", value, "containerName");
            return (Criteria) this;
        }

        public Criteria andContainerNameNotEqualTo(String value) {
            addCriterion("container_name <>", value, "containerName");
            return (Criteria) this;
        }

        public Criteria andContainerNameGreaterThan(String value) {
            addCriterion("container_name >", value, "containerName");
            return (Criteria) this;
        }

        public Criteria andContainerNameGreaterThanOrEqualTo(String value) {
            addCriterion("container_name >=", value, "containerName");
            return (Criteria) this;
        }

        public Criteria andContainerNameLessThan(String value) {
            addCriterion("container_name <", value, "containerName");
            return (Criteria) this;
        }

        public Criteria andContainerNameLessThanOrEqualTo(String value) {
            addCriterion("container_name <=", value, "containerName");
            return (Criteria) this;
        }

        public Criteria andContainerNameLike(String value) {
            addCriterion("container_name like", value, "containerName");
            return (Criteria) this;
        }

        public Criteria andContainerNameNotLike(String value) {
            addCriterion("container_name not like", value, "containerName");
            return (Criteria) this;
        }

        public Criteria andContainerNameIn(List<String> values) {
            addCriterion("container_name in", values, "containerName");
            return (Criteria) this;
        }

        public Criteria andContainerNameNotIn(List<String> values) {
            addCriterion("container_name not in", values, "containerName");
            return (Criteria) this;
        }

        public Criteria andContainerNameBetween(String value1, String value2) {
            addCriterion("container_name between", value1, value2, "containerName");
            return (Criteria) this;
        }

        public Criteria andContainerNameNotBetween(String value1, String value2) {
            addCriterion("container_name not between", value1, value2, "containerName");
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

        public Criteria andLastTimeIsNull() {
            addCriterion("last_time is null");
            return (Criteria) this;
        }

        public Criteria andLastTimeIsNotNull() {
            addCriterion("last_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastTimeEqualTo(Integer value) {
            addCriterion("last_time =", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeNotEqualTo(Integer value) {
            addCriterion("last_time <>", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeGreaterThan(Integer value) {
            addCriterion("last_time >", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("last_time >=", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeLessThan(Integer value) {
            addCriterion("last_time <", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeLessThanOrEqualTo(Integer value) {
            addCriterion("last_time <=", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeIn(List<Integer> values) {
            addCriterion("last_time in", values, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeNotIn(List<Integer> values) {
            addCriterion("last_time not in", values, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeBetween(Integer value1, Integer value2) {
            addCriterion("last_time between", value1, value2, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("last_time not between", value1, value2, "lastTime");
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

        public Criteria andImageIdIsNull() {
            addCriterion("image_id is null");
            return (Criteria) this;
        }

        public Criteria andImageIdIsNotNull() {
            addCriterion("image_id is not null");
            return (Criteria) this;
        }

        public Criteria andImageIdEqualTo(Long value) {
            addCriterion("image_id =", value, "imageId");
            return (Criteria) this;
        }

        public Criteria andImageIdNotEqualTo(Long value) {
            addCriterion("image_id <>", value, "imageId");
            return (Criteria) this;
        }

        public Criteria andImageIdGreaterThan(Long value) {
            addCriterion("image_id >", value, "imageId");
            return (Criteria) this;
        }

        public Criteria andImageIdGreaterThanOrEqualTo(Long value) {
            addCriterion("image_id >=", value, "imageId");
            return (Criteria) this;
        }

        public Criteria andImageIdLessThan(Long value) {
            addCriterion("image_id <", value, "imageId");
            return (Criteria) this;
        }

        public Criteria andImageIdLessThanOrEqualTo(Long value) {
            addCriterion("image_id <=", value, "imageId");
            return (Criteria) this;
        }

        public Criteria andImageIdIn(List<Long> values) {
            addCriterion("image_id in", values, "imageId");
            return (Criteria) this;
        }

        public Criteria andImageIdNotIn(List<Long> values) {
            addCriterion("image_id not in", values, "imageId");
            return (Criteria) this;
        }

        public Criteria andImageIdBetween(Long value1, Long value2) {
            addCriterion("image_id between", value1, value2, "imageId");
            return (Criteria) this;
        }

        public Criteria andImageIdNotBetween(Long value1, Long value2) {
            addCriterion("image_id not between", value1, value2, "imageId");
            return (Criteria) this;
        }

        public Criteria andContainerTypeIsNull() {
            addCriterion("container_type is null");
            return (Criteria) this;
        }

        public Criteria andContainerTypeIsNotNull() {
            addCriterion("container_type is not null");
            return (Criteria) this;
        }

        public Criteria andContainerTypeEqualTo(String value) {
            addCriterion("container_type =", value, "containerType");
            return (Criteria) this;
        }

        public Criteria andContainerTypeNotEqualTo(String value) {
            addCriterion("container_type <>", value, "containerType");
            return (Criteria) this;
        }

        public Criteria andContainerTypeGreaterThan(String value) {
            addCriterion("container_type >", value, "containerType");
            return (Criteria) this;
        }

        public Criteria andContainerTypeGreaterThanOrEqualTo(String value) {
            addCriterion("container_type >=", value, "containerType");
            return (Criteria) this;
        }

        public Criteria andContainerTypeLessThan(String value) {
            addCriterion("container_type <", value, "containerType");
            return (Criteria) this;
        }

        public Criteria andContainerTypeLessThanOrEqualTo(String value) {
            addCriterion("container_type <=", value, "containerType");
            return (Criteria) this;
        }

        public Criteria andContainerTypeLike(String value) {
            addCriterion("container_type like", value, "containerType");
            return (Criteria) this;
        }

        public Criteria andContainerTypeNotLike(String value) {
            addCriterion("container_type not like", value, "containerType");
            return (Criteria) this;
        }

        public Criteria andContainerTypeIn(List<String> values) {
            addCriterion("container_type in", values, "containerType");
            return (Criteria) this;
        }

        public Criteria andContainerTypeNotIn(List<String> values) {
            addCriterion("container_type not in", values, "containerType");
            return (Criteria) this;
        }

        public Criteria andContainerTypeBetween(String value1, String value2) {
            addCriterion("container_type between", value1, value2, "containerType");
            return (Criteria) this;
        }

        public Criteria andContainerTypeNotBetween(String value1, String value2) {
            addCriterion("container_type not between", value1, value2, "containerType");
            return (Criteria) this;
        }

        public Criteria andAttackTimeIsNull() {
            addCriterion("attack_time is null");
            return (Criteria) this;
        }

        public Criteria andAttackTimeIsNotNull() {
            addCriterion("attack_time is not null");
            return (Criteria) this;
        }

        public Criteria andAttackTimeEqualTo(Integer value) {
            addCriterion("attack_time =", value, "attackTime");
            return (Criteria) this;
        }

        public Criteria andAttackTimeNotEqualTo(Integer value) {
            addCriterion("attack_time <>", value, "attackTime");
            return (Criteria) this;
        }

        public Criteria andAttackTimeGreaterThan(Integer value) {
            addCriterion("attack_time >", value, "attackTime");
            return (Criteria) this;
        }

        public Criteria andAttackTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("attack_time >=", value, "attackTime");
            return (Criteria) this;
        }

        public Criteria andAttackTimeLessThan(Integer value) {
            addCriterion("attack_time <", value, "attackTime");
            return (Criteria) this;
        }

        public Criteria andAttackTimeLessThanOrEqualTo(Integer value) {
            addCriterion("attack_time <=", value, "attackTime");
            return (Criteria) this;
        }

        public Criteria andAttackTimeIn(List<Integer> values) {
            addCriterion("attack_time in", values, "attackTime");
            return (Criteria) this;
        }

        public Criteria andAttackTimeNotIn(List<Integer> values) {
            addCriterion("attack_time not in", values, "attackTime");
            return (Criteria) this;
        }

        public Criteria andAttackTimeBetween(Integer value1, Integer value2) {
            addCriterion("attack_time between", value1, value2, "attackTime");
            return (Criteria) this;
        }

        public Criteria andAttackTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("attack_time not between", value1, value2, "attackTime");
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

        public Criteria andContainerIdIsNull() {
            addCriterion("container_id is null");
            return (Criteria) this;
        }

        public Criteria andContainerIdIsNotNull() {
            addCriterion("container_id is not null");
            return (Criteria) this;
        }

        public Criteria andContainerIdEqualTo(String value) {
            addCriterion("container_id =", value, "containerId");
            return (Criteria) this;
        }

        public Criteria andContainerIdNotEqualTo(String value) {
            addCriterion("container_id <>", value, "containerId");
            return (Criteria) this;
        }

        public Criteria andContainerIdGreaterThan(String value) {
            addCriterion("container_id >", value, "containerId");
            return (Criteria) this;
        }

        public Criteria andContainerIdGreaterThanOrEqualTo(String value) {
            addCriterion("container_id >=", value, "containerId");
            return (Criteria) this;
        }

        public Criteria andContainerIdLessThan(String value) {
            addCriterion("container_id <", value, "containerId");
            return (Criteria) this;
        }

        public Criteria andContainerIdLessThanOrEqualTo(String value) {
            addCriterion("container_id <=", value, "containerId");
            return (Criteria) this;
        }

        public Criteria andContainerIdLike(String value) {
            addCriterion("container_id like", value, "containerId");
            return (Criteria) this;
        }

        public Criteria andContainerIdNotLike(String value) {
            addCriterion("container_id not like", value, "containerId");
            return (Criteria) this;
        }

        public Criteria andContainerIdIn(List<String> values) {
            addCriterion("container_id in", values, "containerId");
            return (Criteria) this;
        }

        public Criteria andContainerIdNotIn(List<String> values) {
            addCriterion("container_id not in", values, "containerId");
            return (Criteria) this;
        }

        public Criteria andContainerIdBetween(String value1, String value2) {
            addCriterion("container_id between", value1, value2, "containerId");
            return (Criteria) this;
        }

        public Criteria andContainerIdNotBetween(String value1, String value2) {
            addCriterion("container_id not between", value1, value2, "containerId");
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