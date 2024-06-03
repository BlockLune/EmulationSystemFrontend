import {
    baseApiUrl,
    token,
} from "./keys";
import axios from 'axios';
import {ElMessage} from "element-plus";
import {ref} from "vue";


axios.defaults.baseURL = baseApiUrl;
axios.defaults.timeout = 5000;

export const apikey = {
    key: 'Authorization',
    value: '',
};

export const showHome = ref(localStorage.getItem('Authorization') === null);
// export const showHome = ref(sessionStorage.getItem('Authorization') === null);

// 创建一个axios实例
const instance = axios.create({
    baseURL: baseApiUrl,
    timeout: 0,
});

// // 添加请求拦截器
// instance.interceptors.request.use(
//     (config) => {
//         // 在发送请求之前做些什么
//         const token = localStorage.getItem('Authorization');
//         if (token) {
//             config.headers.Authorization = `Bearer ${token}`;
//         }
//         return config;
//     },
//     (error) => {
//         // 对请求错误做些什么
//         return Promise.reject(error);
//     }
// );

// 添加响应拦截器
instance.interceptors.response.use(
    (response) => {
        // 对响应数据做些什么
        // console.log(response.data.code)
        if (response.data.code === 401) {
            // 如果返回401，则移除auth并重新登录
            localStorage.removeItem('Authorization');
            // await router.push('/login');
            showHome.value = true;
        }
        return response;
    },
    async (error) => {
        // 对响应错误做些什么
        console.log(error)
        if (error.response && error.response.status === 401) {
            // 如果返回401，则移除auth并重新登录
            localStorage.removeItem('Authorization');
            // await router.push('/login');
            showHome.value = true;
        }
        return Promise.reject(error);
    }
);
export default instance;

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
    instance.post('/system/user/login', postData)
        .then((response) => {
            apikey.value = token + response.data.data;
            // sessionStorage.setItem('Authorization', apikey.value);
            localStorage.setItem('Authorization', apikey.value);
            ElMessage(response.data.message);
            showHome.value = false;
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
