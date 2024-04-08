package com.njupt.epsm.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
* Created by Mybatis Generator on 2024/01/10
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysConfigDto implements Serializable {

    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    private String configName;

    private String configValue;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}