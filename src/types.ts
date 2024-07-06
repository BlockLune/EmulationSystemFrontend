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
