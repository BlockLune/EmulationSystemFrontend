package com.njupt.epsm.metadata.section.score;

import java.util.HashMap;

//影响因素的选项与分值
public class ImpactScore {

    //无0.0
    public static final String NONE = "NONE";

    //部分0.275
    public static final String PARTIAL = "PARTIAL";

    //完整0.660
    public static final String COMPLETE = "COMPLETE";

    private static final HashMap<String,Double> scoreMap = new HashMap<String,Double>(){{
        put(NONE,0.0);
        put(PARTIAL,0.275);
        put(COMPLETE,0.660);
    }};

    public static double getScore(String key){
        return scoreMap.get(key);
    }
}
