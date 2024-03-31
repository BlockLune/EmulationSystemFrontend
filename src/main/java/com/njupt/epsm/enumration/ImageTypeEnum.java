package com.njupt.epsm.enumration;

public enum ImageTypeEnum {

    BUG_HOLE_DEFEND("1", "漏洞挖掘镜像"),
    BUG_HOLE("2", "包含漏洞数据镜像"),
    NETWORK_ATTACK("3", "网络攻击镜像"),
    NETWORK_DEFEND("4", "网络防御镜像"),
    TARGET("5", "靶机镜像");


    private String code;

    private String name;

    ImageTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
