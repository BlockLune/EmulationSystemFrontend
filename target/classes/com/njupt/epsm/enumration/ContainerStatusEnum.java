package com.njupt.epsm.enumration;

public enum ContainerStatusEnum {

    INITIAL("0", "初始化"),
    RUNNING("1", "运行中"),
    STOP("2", "已停止");


    private String code;

    private String name;

    ContainerStatusEnum(String code, String name) {
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
