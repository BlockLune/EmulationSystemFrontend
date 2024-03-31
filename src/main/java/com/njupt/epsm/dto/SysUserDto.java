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
public class SysUserDto implements Serializable {

    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    private String userName;

    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long roleId;

    private String loginName;

    private String password;

    private String status;

    private Date createTime;

    private Date updateTime;

    private String auth;

    private static final long serialVersionUID = 1L;
}