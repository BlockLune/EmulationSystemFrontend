import axios from 'axios';
import { ElMessage } from "element-plus";
import { ref } from "vue";

const baseApiUrl = import.meta.env.VITE_BASE_API_URL;
const token = import.meta.env.VITE_TOKEN;

axios.defaults.baseURL = baseApiUrl;
axios.defaults.timeout = 5000;

export const apiKey = {
    key: 'Authorization',
    value: '',
};

export const showHome = ref(localStorage.getItem('Authorization') === null);

const axiosInstance = axios.create({
    baseURL: baseApiUrl,
    timeout: 0,
});

axiosInstance.interceptors.request.use((config) => {
    console.log('Requsted: ', config);
    return config;
}, error => {
    return Promise.reject(error);
});

axiosInstance.interceptors.response.use(
    (response) => {
        console.log('Response: ', response);
        if (response.data.code === 401) {
            localStorage.removeItem('Authorization');
            showHome.value = true;
        }
        return response;
    },
    async (error) => {
        console.log(error)
        if (error.response && error.response.status === 401) {
            localStorage.removeItem('Authorization');
            showHome.value = true;
        }
        return Promise.reject(error);
    }
);
export default axiosInstance;

interface Role {
    auth: string,
    createdAt: string,
    id: string,
    name: string,
    updatedAt: string,
};

export const loginReq = (loginName: string, password: string) => {
    const postData = {
        loginName: loginName,
        password: password,
    }
    axiosInstance.post('/system/user/login', postData)
        .then((response) => {
            apiKey.value = token + response.data.data;
            localStorage.setItem('Authorization', apiKey.value);
            ElMessage(response.data.message);
            showHome.value = false;
        });
};
