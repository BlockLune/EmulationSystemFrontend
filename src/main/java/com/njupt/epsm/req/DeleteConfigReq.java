package com.njupt.epsm.req;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class DeleteConfigReq implements Serializable {

    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty("主键")
    private Long configId;
}
