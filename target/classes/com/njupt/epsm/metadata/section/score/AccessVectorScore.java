package com.njupt.epsm.metadata.section.score;

import java.util.HashMap;

//攻击向量的选项及分值
public class AccessVectorScore {

    //本地0.395
    public static final String LOCAL = "LOCAL";

    //相邻网络0.646
    public static final String ADJACENT_NETWORK = "ADJACENT_NETWORK";

    //网络1
    public static final String NETWORK = "NETWORK";

    private static final HashMap<String,Double> scoreMap = new HashMap<String,Double>(){{
        put(LOCAL,0.395);
        put(ADJACENT_NETWORK,0.646);
        put(NETWORK,1.0);
    }};

    public static double getScore(String key) {
        return scoreMap.get(key);
    }
}
