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
    createTime: string | Date,
    updateTime: string | Date,
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

export interface QueryExercise {
    exerciseName: string,
    status: string
}

export enum ExerciseStatus {
    "未开始" = 1,
    "进行中" = 2,
    "已停止" = 3,
}


// Code snippet to generate { label: string, value: string }[] from enum
/*
Object.entries(ImageType)
    .filter(([key, value]) => typeof value === 'number')
    .map(([label, value]) => ({ label, value }))
*/
// varchar(1)
export enum ImageType {
    "漏洞挖掘镜像" = 0,
    "包含漏洞数据镜像" = 1,
    "网络攻击镜像" = 2,
    "网络防御镜像" = 3,
    "靶机镜像" = 4,
}


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
    file: File,
    imageName: string,
    imageType: string,
    version: string,
}

export interface QueryImage {
    imageName: string,
    imageType: string,
}

// char(1)
export enum ContainerType {
    "漏洞挖掘容器" = 1,
    "漏洞元数据容器" = 2,
    "网络攻击容器" = 3,
    "网络防御容器" = 4,
    "靶机容器" = 5,
}

// varchar(500)
export enum ContainerStatus {
    "created",
    "restarting",
    "running",
    "paused",
    "exited",
}

export interface Container {
    id: string,
    containerId: string, // sha256
    containerName: string,
    containerType: string,
    status: string,
    imageId: string,
    imageName: string,
    rangeName: string,
    attackTime: string,
    defendTime: string,
    startTime: string,
    endTime: string,
    lastTime: string,
    createTime: string,
    updateTime: string,
}

export interface NewContainer {
    imageId: string,
}

export interface QueryContainer {
    containerName: string,
    // status: string,
    targetName: string,
}

// char(1)
export enum RangeStatus {
    "待启动" = 0,
    "进行中" = 1,
    "已停止" = 2,
}