package com.njupt.epsm.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class RangeSelectReq implements Serializable {

    @ApiModelProperty("攻防演练名称")
    private String exerciseName;

    @ApiModelProperty("靶场名称")
    private String rangeName;

    @ApiModelProperty("靶场状态 0:已停止 1:运行中")
    private String rangeStatus;

    private Integer pageNum;

    private Integer pageSize;

}
