package com.njupt.epsm.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class LoginReq implements Serializable {

    @ApiModelProperty("登录名")
    private String loginName;

    @ApiModelProperty("密码")
    private String password;
}
