package com.njupt.epsm.req;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CreateRoleReq implements Serializable {

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("权限")
    private String auth;


}
