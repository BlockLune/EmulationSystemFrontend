package com.njupt.epsm.req;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CreateTargetContainerReq implements Serializable {

    @ApiModelProperty("容器镜像Id")
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long imageId;
}
