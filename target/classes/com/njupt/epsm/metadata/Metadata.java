package com.njupt.epsm.metadata;

import com.alibaba.fastjson2.annotation.JSONField;
import com.njupt.epsm.metadata.section.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 演练漏洞元数据
 */
@Data
@AllArgsConstructor
public class Metadata implements Serializable {

    @JSONField( name = "exercise_id")
    public Long exercise_id;

    @JSONField( name = "loophole")
    public M_LoopHole m_loophole;

}