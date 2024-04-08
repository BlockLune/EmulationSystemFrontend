package com.njupt.epsm.metadata.section;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;


/**
 * 攻击目标
 */
@Data
@AllArgsConstructor
public class M_Target implements Serializable {

    //镜像编号
    public Long image_id;

    //容器id
    public Long container_id;

    //攻击目标名称（检测目标名）
    public String name;

    //检测目标版本
    public String version;

    //目标位置
    public String location;
}