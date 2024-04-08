package com.njupt.epsm.metadata;

import com.njupt.epsm.common.CommonResult;
import com.njupt.epsm.metadata.section.*;
import com.njupt.epsm.metadata.section.score.AccessComplexityScore;
import com.njupt.epsm.metadata.section.score.AccessVectorScore;
import com.njupt.epsm.metadata.section.score.AuthenticationScore;
import com.njupt.epsm.metadata.section.score.ImpactScore;
import com.njupt.epsm.po.LoopHole;
import com.njupt.epsm.po.LoopHoleCategory;
import com.njupt.epsm.service.LoopHoleService;
import com.njupt.epsm.util.ApplicationContextProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;

/**
 * 元数据分析器
 */
public class MetadataAnalyzer{

    private static final Logger log = LoggerFactory.getLogger(MetadataAnalyzer.class);

    private Metadata metadata;

    private M_LoopHole loopHole;

    private M_Metrics metrics;

    private M_Source source;

    private M_Target target;

    private String metadataJSONString;

    private LoopHoleService loopHoleService;

    public Metadata getMetadata() {
        return metadata;
    }

    public M_Metrics getMetrics() {
        return metrics;
    }

    public M_Source getSource() {
        return source;
    }

    public M_Target getTarget() {
        return target;
    }

    public String getMetadataJSONString() {
        return metadataJSONString;
    }

    public MetadataAnalyzer(Metadata metadata){
        this.metadata = metadata;
        this.loopHole = metadata.m_loophole;
        this.metrics = metadata.m_loophole.m_metrics;
        this.source = metadata.m_loophole.m_source;
        this.target = metadata.m_loophole.m_target;
        this.metadataJSONString = MetadataUtil.toJSON(metadata);
        getService();
    }

    public MetadataAnalyzer(String metadataJSONString){
        Metadata metadata1 = MetadataUtil.JSONParse(metadataJSONString);
        this.metadataJSONString = metadataJSONString;
        this.metadata = metadata1;
        this.loopHole = metadata1.m_loophole;
        this.metrics = metadata1.m_loophole.m_metrics;
        this.source = metadata1.m_loophole.m_source;
        this.target = metadata1.m_loophole.m_target;
        getService();
    }

    private void getService(){
        this.loopHoleService = ApplicationContextProvider.getBean(LoopHoleService.class);
    }

    //计算CVSS得分
    private double computeCvssScore(){
        M_Metrics metrics = this.metadata.m_loophole.m_metrics;
        M_Exploitability exploitability = metrics.m_exploitability;
        double exploitabilityScore = 20
                * AccessVectorScore.getScore(exploitability.accessVector)
                * AccessComplexityScore.getScore(exploitability.accessComplexity)
                * AuthenticationScore.getScore(exploitability.authentication);
        M_Impact impact = metrics.m_impact;
        double impactScore = 10.41
                * ( 1 - ( 1 - ImpactScore.getScore(impact.confidentiality) )
                * ( 1 - ImpactScore.getScore(impact.integrity) )
                * ( 1 - ImpactScore.getScore(impact.availability) ) );
        double f_impact = impactScore == 0 ? 0 : 1.176;
        return Math.round(((0.6 * impactScore) + (0.4 * exploitabilityScore) - 1.5) * f_impact * 10) / 10.0;
    }

    //获取Cvss等级
    private String getCvssSeverity(double score){
        String severity = "";
        if(score >= 0 && score < 4){
            severity = "低危";
        }else if(score >= 4 && score < 7) {
            severity = "中危";
        }else if(score >= 7 && score < 9){
            severity = "高危";
        }else if(score >= 9 && score <= 10){
            severity = "严重";
        }
        return severity;
    }

    //定义漏洞名称
    private String defineLoopHoleName(){
        String loc = target.getLocation();
        String targetName = target.getName();
        String targetVersion = target.getVersion();
        String attackName = this.getLoopHoleCategory().getCategoryName();
        double cvssScore = this.computeCvssScore();
        String cvssSeverity = getCvssSeverity(cvssScore);
        return loc + " / "+ targetName + " - " + targetVersion + " / " + attackName + " | " + cvssScore + " " + cvssSeverity + "漏洞";
    }

    //获取漏洞类型
    private LoopHoleCategory getLoopHoleCategory() {
        Long category_id = this.source.attack_category_id;
        CommonResult<LoopHoleCategory> result = loopHoleService.getLoopHoleCategoryById(category_id);
        LoopHoleCategory loopHoleCategory = result.getData();
        return loopHoleCategory;
    }

    //获取漏洞实例
    private LoopHole getLoopHole(){
        LoopHole loopHole1 = new LoopHole();
        loopHole1.setMetadata(this.metadataJSONString);
        loopHole1.setId(this.loopHole.number);
        loopHole1.setHoleName(this.defineLoopHoleName());
        loopHole1.setLevel(this.computeCvssScore());
        loopHole1.setCategoryId(this.source.attack_category_id);
        loopHole1.setRelateImageId(this.source.getImage_number());
        loopHole1.setRelateContainerId(this.target.getContainer_id());
        loopHole1.setRelateExerciseId(this.metadata.getExercise_id());
        loopHole1.setCreateTime(new Date());
        loopHole1.setUpdateTime(new Date());
        return loopHole1;
    }

    //分析
    public MetadataAnalyzeResult analyze(){
        System.out.println(getLoopHoleCategory());
        System.out.println(getLoopHole());
        System.out.println(defineLoopHoleName());
        return new MetadataAnalyzeResult(getLoopHoleCategory(),getLoopHole(),defineLoopHoleName());
    }

    public static ArrayList<MetadataAnalyzeResult> test(){
        Metadata metadata = MetadataUtil.testMetadata;
        MetadataAnalyzer metadataAnalyzer1 = new MetadataAnalyzer(metadata);
        MetadataAnalyzer metadataAnalyzer2 = new MetadataAnalyzer(metadataAnalyzer1.metadataJSONString);

        MetadataAnalyzeResult result1 = metadataAnalyzer1.analyze();
        System.out.println(result1);

        MetadataAnalyzeResult result2 = metadataAnalyzer2.analyze();
        System.out.println(result2);
        ArrayList<MetadataAnalyzeResult> res = new ArrayList<>();
        res.add(result1);
        res.add(result2);
        return res;
    }
}