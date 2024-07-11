export enum Auth {
    "靶场管理",
    "容器管理",
    "镜像管理",
    "攻防演练",
    "漏洞库管理",
    "系统管理",
};
export interface Role {
    id: string | number,
    name: string,
    auth: string | Auth,
    createdAt: string | Date,
    updatedAt: string | Date,
};

export interface User {
    id: string | number,
    userName: string,
    loginName: string,
    roleId: string | number,
    auth: string | Auth,
    password: string,
    status: string,
    createTime: string | Date,
    updateTime: string | Date,
}

export interface Config {
    id: string | number,
    configItem: string,
    configValue: string,
    remark: string,
    createTime: string | Date,
    updateTime: string | Date,
}

export interface Exercise {
    id: string,
    exerciseName: string,
    network: string,
    status: string,

    targetImageId: string,

    attackImageId: string,
    attackStartTime: string,
    attackEndTime: string,
    attackTimes: string,

    defendImageId: string,
    defendStartTime: string,
    defendEndTime: string,
    defendTimes: string,

    startTime: string,
    endTime: string,
    createTime: string,
    updateTime: string,
}

export interface NewExercise {
    exerciseName: string,
    startTime: string,
    endTime: string,

    targetImageId: string,

    attackImageId: string,
    attackStartTime: string,
    attackEndTime: string,

    defendImageId: string,
    defendStartTime: string,
    defendEndTime: string,
}

// imageType:
// 0: 漏洞挖掘镜像
// 1: 包含漏洞数据镜像
// 2: 网络攻击镜像
// 3: 网络防御镜像
// 4: 靶机镜像

// AttackImage: POST /exercise/selectAttackImage
// DefenseImage: POST /exercise/selectDefenseImage
export interface Image {
    id: string,
    imageName: string,
    imageType: string,
    path: string,
    version: string,
    createTime: string,
    updateTime: string,
}
export interface NewImage {
    file: string,
    imageName: string,
    imageType: string,
    version: string,
}
