import {
    baseApiUrl,
    token,
} from "./keys";
import axios from 'axios';
import {ElMessage} from "element-plus";

axios.defaults.baseURL = baseApiUrl;
axios.defaults.timeout = 5000;

export const apikey = {
    key: 'Authorization',
    value: '',
};

interface Role {
    auth: string,
    createdAt: string,
    id: string,
    name: string,
    updatedAt: string,
};

//export let roless = [] as Role[];

export const loginReq = (loginName: string, password: string) => {
    const postData = {
        loginName: loginName,
        password: password,
    }
    axios.post('/system/user/login', postData)
        .then((response) => {
            apikey.value = token + response.data.data;
            localStorage.setItem('Authorization', apikey.value)
            ElMessage(response.data.message)
        });
};

// export const listRoles = () => {
//     axios.create({headers: {Authorization: localStorage.getItem('Authorization')}})
//         .get('/system/role/listRoles')
//         .then((response) => {
//             roless = []
//             for (const role of response.data.data) {
//                 roless.push(role);
//             }
//         });
// };

// export const updateRole = (auth: string, roleId: number, roleName: string) => {
//     axios({
//         method: 'post',
//         url: '/system/role/updateRole',
//         headers: {
//             'Authorization': localStorage.getItem('Authorization'),
//         },
//         data: {
//             auth: auth,
//             roleId: roleId,
//             roleName: roleName
//         }
//     }).then(
//     );
// };

// export const deleteRole = (roleId: number) => {
//     axios({
//         method: 'post',
//         url: '/system/role/deleteRole',
//         headers: {
//             'Authorization': localStorage.getItem('Authorization'),
//         },
//         data: {
//             roleId: roleId
//         }
//     }).then(
//     );
//
// }

// export const addRole = (roleName: string, auth: string) => {
//     axios({
//         method: 'post',
//         url: '/system/role/createRole',
//         headers: {
//             'Authorization': localStorage.getItem('Authorization'),
//         },
//         data: {
//             roleName: roleName,
//             auth: auth
//         }
//     }).then(
//     );
// }

// export const changeUserStatus = (status: string, userId: number) => {
//     axios({
//         method: 'post',
//         url: '/system/user/changeUserStatus',
//         headers: {
//             'Authorization': localStorage.getItem('Authorization'),
//         },
//         data: {
//             status: status,
//             userId: userId
//         }
//     });
// }
