package com.njupt.epsm.metadata.section;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class M_LoopHole implements Serializable {

    public Long number;

    @JSONField(name = "target")
    public M_Target m_target;

    @JSONField(name = "source")
    public M_Source m_source;

    @JSONField(name = "metrics")
    public M_Metrics m_metrics;

}
