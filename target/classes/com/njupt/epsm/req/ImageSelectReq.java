package com.njupt.epsm.req;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ImageSelectReq implements Serializable {

    @ApiModelProperty("镜像名称")
    private String imageName;

    @ApiModelProperty("镜像类型 0:漏洞挖掘镜像 1:包含漏洞数据镜像 2:网络攻击镜像  3:网络防御镜像  4:靶机镜像")
    private String imageType;

    private Integer pageNum;

    private Integer pageSize;
}
