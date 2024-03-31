package com.njupt.epsm.req;


import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CreateUserReq implements Serializable {

    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty("角色Id")
    private Long roleId;

    @ApiModelProperty("用户名称")
    private String userName;

    @ApiModelProperty("登录名称")
    private String loginName;


}
