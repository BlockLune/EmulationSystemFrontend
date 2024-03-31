package com.njupt.epsm.req;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateConfigReq implements Serializable {

    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty("主键")
    private Long configId;

    @ApiModelProperty("配置项")
    private String configItem;

    @ApiModelProperty("配置值")
    private String configValue;

    @ApiModelProperty("备注")
    private String remark;
}
