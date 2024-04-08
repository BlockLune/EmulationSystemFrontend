package com.njupt.epsm.req;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ContainerSelectReq implements Serializable {


    @ApiModelProperty("容器名称")
    private String containerName;

    @ApiModelProperty("靶场名称")
    private String targetName;

    @ApiModelProperty("容器状态")
    private String status;

    private Integer pageNum;

    private Integer pageSize;
}
