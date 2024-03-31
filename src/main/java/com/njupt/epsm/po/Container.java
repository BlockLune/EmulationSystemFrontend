package com.njupt.epsm.po;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by Mybatis Generator on 2024/03/21
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Container implements Serializable {
    private Long id;

    private String containerName;

    private String rangeName;

    private Date startTime;

    private Date endTime;

    private Integer lastTime;

    private String status;

    private Long imageId;

    private String containerType;

    private Integer attackTime;

    private Integer defendTime;

    private Date createTime;

    private Date updateTime;

    private String containerId;

    private static final long serialVersionUID = 1L;
}