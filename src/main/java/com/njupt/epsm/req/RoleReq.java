package com.njupt.epsm.req;


import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class RoleReq implements Serializable {


    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty("角色Id")
    private Long roleId;


}
