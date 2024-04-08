package com.njupt.epsm.metadata;

import com.njupt.epsm.po.LoopHole;
import com.njupt.epsm.po.LoopHoleCategory;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MetadataAnalyzeResult {

    private LoopHoleCategory loopHoleCategory;

    private LoopHole loopHole;

    private String definedName;
}
