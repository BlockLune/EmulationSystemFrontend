package com.njupt.epsm.metadata.section.score;

import java.util.HashMap;

//攻击复杂度的选项及分值
public class AccessComplexityScore {

    //高0.35
    public static final String HIGH = "HIGH";

    //中0.61
    public static final String MEDIUM = "MEDIUM";

    //低0.71
    public static final String LOW = "LOW";

    public static final HashMap<String,Double> scoreMap = new HashMap<String,Double>(){{
        put(HIGH,0.35);
        put(MEDIUM,0.61);
        put(LOW,0.71);
    }};

    public static double getScore(String key) {
        return scoreMap.get(key);
    }
}
