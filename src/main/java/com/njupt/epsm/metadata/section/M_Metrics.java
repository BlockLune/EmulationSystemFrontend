package com.njupt.epsm.metadata.section;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class M_Metrics implements Serializable {

    @JSONField(name = "impact")
    public M_Impact m_impact;

    @JSONField(name = "exploitability")
    public M_Exploitability m_exploitability;

}
