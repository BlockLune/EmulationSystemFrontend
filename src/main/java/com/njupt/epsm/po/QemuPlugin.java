package com.njupt.epsm.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QemuPlugin implements Serializable {

    private Long id;

    private String qemuPluginName;

    private String version;

    private String repository;

    private String imageId;

    private Date createTime;

    private Date updateTime;
}
