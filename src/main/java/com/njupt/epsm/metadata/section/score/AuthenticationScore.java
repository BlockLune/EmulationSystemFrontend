package com.njupt.epsm.metadata.section.score;

import java.util.HashMap;

//是否认证的选项与分值
public class AuthenticationScore {

    //多次0.45
    public static final String MULTIPLE = "MULTIPLE";

    //一次0.56
    public static final String SINGLE = "SINGLE";

    //没有0.704
    public static final String NONE = "NONE";

    public static final HashMap<String,Double> scoreMap = new HashMap<String,Double>(){{
        put(MULTIPLE,0.45);
        put(SINGLE,0.56);
        put(NONE,0.704);
    }};

    public static double getScore(String key) {
        return scoreMap.get(key);
    }
}
