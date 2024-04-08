package com.njupt.epsm.metadata;

import com.njupt.epsm.common.CommonResult;
import com.njupt.epsm.mapper.LoopHoleCategoryMapper;
import com.njupt.epsm.mapper.LoopHoleMapper;
import com.njupt.epsm.po.LoopHole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetadataService {

    @Autowired
    private LoopHoleCategoryMapper loopHoleCategoryMapper;

    @Autowired
    private LoopHoleMapper loopHoleMapper;

    /**
     * 通过metadata插入漏洞
     */
    public CommonResult insertLoopHoleFromMetadata(Metadata metadata){
        MetadataAnalyzeResult metadataAnalyzeResult = getMetadataAnalyzeResultFromMetadata(metadata);
        LoopHole loopHole = metadataAnalyzeResult.getLoopHole();
        int count = loopHoleMapper.insert(loopHole);
        if(count > 0){
            return CommonResult.success(loopHole,"插入成功");
        }else {
            return CommonResult.failed("插入失败");
        }
    }

    /**
     * 通过metadataJson插入漏洞
     */
    public CommonResult insertLoopHoleFromMetadataJson(String metadataJson){
        MetadataAnalyzeResult metadataAnalyzeResult = getMetadataAnalyzeResultFromMetadataJson(metadataJson);
        LoopHole loopHole = metadataAnalyzeResult.getLoopHole();
        int count = loopHoleMapper.insert(loopHole);
        if(count > 0){
            return CommonResult.success(loopHole,"插入成功");
        }else {
            return CommonResult.failed("插入失败");
        }
    }

    /**
     * 通过Metadata获取漏洞分析结果
     */
    public MetadataAnalyzeResult getMetadataAnalyzeResultFromMetadata(Metadata metadata){
        MetadataAnalyzer metadataAnalyzer = new MetadataAnalyzer(metadata);
        return metadataAnalyzer.analyze();
    }

    /**
     * 通过MetadataJson获取漏洞分析结果
     */
    public MetadataAnalyzeResult getMetadataAnalyzeResultFromMetadataJson(String metadataJson){
        MetadataAnalyzer metadataAnalyzer = new MetadataAnalyzer(metadataJson);
        return metadataAnalyzer.analyze();
    }

    public CommonResult testInsert(){
        return insertLoopHoleFromMetadata(MetadataUtil.testMetadata);
    }
}
