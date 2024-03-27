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
    createdAt: string,
    id: number,
    name: string,
    updatedAt: string,
};

export let roless = [] as Role[];


export const loginReq = (loginName: string, password: string) => {
    const postData = {
        loginName: loginName,
        password: password,
    }
    axios.post('/system/user/login', postData)
        .then((response) => {
            apikey.value = token + response.data.data;
            localStorage.setItem('Authorization', apikey.value)
            //console.log(apikey.value)
        });
};

export const listRoles = () => {
    roless = []
    axios.create({headers: {Authorization: localStorage.getItem('Authorization')}})
        .get('/system/role/listRoles')
        .then((response) => {
            for (const role of response.data.data) {
                roless.push(role);
            }
        });
};

export const updateRole = (auth: string, roleId: number, roleName: string) => {
    axios({
        method: 'post',
        url: '/system/role/updateRole',
        headers: {
            'Authorization': localStorage.getItem('Authorization'),
        },
        data: {
            auth: auth,
            roleId: roleId,
            roleName: roleName
        }
    }).then(
        listRoles()
    );

};

export const deleteRole = (roleId: number) => {
    axios({
        method: 'post',
        url: '/system/role/deleteRole',
        headers: {
            'Authorization': localStorage.getItem('Authorization'),
        },
        data: {
            roleId: roleId
        }
    }).then(
        listRoles()
    );

}

export const addRole = (roleName: string, auth: string) => {
    axios({
        method: 'post',
        url: '/system/role/createRole',
        headers: {
            'Authorization': localStorage.getItem('Authorization'),
        },
        data: {
            roleName: roleName,
            auth: auth
        }
    }).then(
        listRoles()
    )
    ;

}

export const changeUserStatus = (status: string, userId: number) => {
    axios({
        method: 'post',
        url: '/system/user/changeUserStatus',
        headers: {
            'Authorization': localStorage.getItem('Authorization'),
        },
        data: {
            status: status,
            userId: userId
        }
    });
}
