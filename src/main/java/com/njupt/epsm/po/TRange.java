package com.njupt.epsm.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by Mybatis Generator on 2024/03/07
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TRange implements Serializable {
    private Long id;

    private String rangeName;

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