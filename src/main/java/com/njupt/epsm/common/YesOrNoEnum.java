package com.njupt.epsm.common;

import org.apache.commons.lang3.StringUtils;

/**
 * @author liangw
 * @create 2021-04-14 14:06
 * @description:系统通用枚举类型 凡是 生效，有效，开启，是等状态状态均为1
 * 凡是 无效，关闭，禁用，否等状态均为0
 */


public enum YesOrNoEnum {

    NO("0", "否"),
    YES("1", "是");

    private String code;

    private String name;


    YesOrNoEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public static String getNameByCode(String code) {
        for (YesOrNoEnum yesOrNoEnum : YesOrNoEnum.values()) {
            if (StringUtils.equals(yesOrNoEnum.getCode(), code)) {
                return yesOrNoEnum.getName();
            }
        }
        return null;
    }
}
