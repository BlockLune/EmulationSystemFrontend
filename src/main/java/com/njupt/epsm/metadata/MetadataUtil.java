package com.njupt.epsm.metadata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.njupt.epsm.metadata.section.*;
import com.njupt.epsm.metadata.section.score.AccessComplexityScore;
import com.njupt.epsm.metadata.section.score.AccessVectorScore;
import com.njupt.epsm.metadata.section.score.AuthenticationScore;
import com.njupt.epsm.metadata.section.score.ImpactScore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class MetadataUtil {

    public static Metadata testMetadata;

    static {
        M_Target m_target = new M_Target(generateRandomId(),generateRandomId(),"靶机1","v0.01","端");
        M_Source m_source = new M_Source(generateRandomId(),1216330704892219392L,"持续DOS攻击","v1");
        M_Impact m_impact = new M_Impact(ImpactScore.COMPLETE,ImpactScore.COMPLETE,ImpactScore.COMPLETE);
        M_Exploitability m_exploitability = new M_Exploitability(AccessVectorScore.NETWORK, AccessComplexityScore.MEDIUM, AuthenticationScore.SINGLE);
        M_Metrics m_metrics = new M_Metrics(m_impact,m_exploitability);
        M_LoopHole mLoopHole = new M_LoopHole(generateRandomId(),m_target,m_source,m_metrics);
        testMetadata = new Metadata(generateRandomId(),mLoopHole);
    }

    private static final Logger log = LoggerFactory.getLogger(MetadataUtil.class);

    public static String toJSON(Metadata metadata){
        String jsonResult = "";
        try {
            jsonResult = JSON.toJSONString(metadata);
        }catch(Exception e){
            log.error("Metadata to JSONString fail",e);
        }
        return jsonResult;
    }

    public static Metadata JSONParse(String metadataJSON){
        Metadata parsedObject = null;
        try{
            parsedObject = JSONObject.parseObject(metadataJSON,Metadata.class);
        }catch(Exception e){
            log.error("JSONParse metadata fail",e);
        }
        return parsedObject;
    }

    public static Long generateRandomId() {
        Random random = new Random();
        long randomId = random.nextLong();
        randomId = Math.abs(randomId); // 取绝对值确保生成的ID大于0
        if (randomId == 0) {
            randomId = 1; // 如果生成的ID为0，则设置为1
        }
        return randomId;
    }
}
