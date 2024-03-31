package com.njupt.epsm.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
* Created by Mybatis Generator on 2024/03/07
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TRangeDto implements Serializable {

    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    private String rangeName;

    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long exerciseId;

    private Date startTime;

    private Date endTime;

    private String status;

    private Integer digBugNums;

    private Integer attackTimes;

    private Integer defendTime;

    private BigDecimal defendPercent;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}