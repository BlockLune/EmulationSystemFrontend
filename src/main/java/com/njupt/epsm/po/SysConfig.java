package com.njupt.epsm.po;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by Mybatis Generator on 2024/01/10
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysConfig implements Serializable {
    private Long id;

    private String configName;

    private String configValue;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}