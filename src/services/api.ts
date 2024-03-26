import {
    baseApiUrl,
    token,
} from "./keys";
import axios from 'axios';

axios.defaults.baseURL = baseApiUrl;
axios.defaults.timeout = 5000;

export const apikey = {
    key: 'Authorization',
    value: '',
};

interface Role {
    auth: string,
    createTime: string,
    id: number,
    name: string,
    updateTime: string,
};

export const roles = [] as Role[];

export const loginReq = (loginName: string, password: string) => {

    const postData = {
            loginName: loginName,
            password: password,
    }
    axios.post('/system/user/login',postData)
        .then((response) => {
            apikey.value = token + ' ' + response.data;
            console.log(apikey.value)
        });
};

export const listRoles = () => {
    axios.create({headers: {Authorization: apikey.value}})
        .get('/system/role/listRoles')
        .then((response) => {
            for (const role of response.data) {
                roles.push(role);
            }
        });
};

export const updateRole = (auth: string, roleId: number, roleName: string) => {
    axios.create({
        headers: {Authorization: apikey.value},
        data: {
            auth: auth,
            roleId: roleId,
            roleName: roleName
        }
    })
    .post('/system/role/updateRole')
    .then((response) => {
        // ...
    });
};

export const deleteRole = (roleId: number) => {
    axios.create({
        headers: {Authorization: apikey.value},
        data: {roleId: roleId},
    })
    .post('/system/role/deleteRole')
    .then((response) => {
        // ...
    });
}
