package com.njupt.epsm.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Mybatis Generator on 2024/03/21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContainerDto implements Serializable {

    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    private String containerName;

    private String rangeName;

    private Date startTime;

    private Date endTime;

    private Integer lastTime;

    private String status;

    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long imageId;

    private String imageName;

    private String containerType;

    private Integer attackTime;

    private Integer defendTime;

    private Date createTime;

    private Date updateTime;

    private String containerId;

    private static final long serialVersionUID = 1L;
}