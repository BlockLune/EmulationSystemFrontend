package com.njupt.epsm.req;


import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserReq implements Serializable {


    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty("用户Id")
    private Long userId;


}
