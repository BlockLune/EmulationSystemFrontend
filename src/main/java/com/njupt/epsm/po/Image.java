package com.njupt.epsm.po;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by Mybatis Generator on 2024/03/20
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image implements Serializable {
    private Long id;

    private String imageName;

    private String version;

    private String imageType;

    private Long attackCategoryId;

    private String path;

    private String imageId;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}