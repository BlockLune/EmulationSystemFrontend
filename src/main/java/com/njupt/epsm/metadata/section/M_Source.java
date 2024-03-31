package com.njupt.epsm.metadata.section;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 攻击源信息
 */
@Data
@AllArgsConstructor
public class M_Source implements Serializable {

    //攻击镜像编号
    public Long image_number;

    //攻击类型
    public Long attack_category_id;

    //攻击源名称
    public String name;

    //镜像版本
    public String version;
}
