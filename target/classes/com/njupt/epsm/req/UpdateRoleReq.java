package com.njupt.epsm.req;


import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateRoleReq implements Serializable {

    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty("角色Id")
    private Long roleId;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("权限")
    private String auth;


}
