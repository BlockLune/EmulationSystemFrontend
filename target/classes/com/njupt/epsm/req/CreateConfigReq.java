package com.njupt.epsm.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CreateConfigReq implements Serializable {

    @ApiModelProperty("配置项")
    private String configItem;

    @ApiModelProperty("配置值")
    private String configValue;

    @ApiModelProperty("备注")
    private String remark;
}
