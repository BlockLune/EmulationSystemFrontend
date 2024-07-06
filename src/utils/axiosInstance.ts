import axios from 'axios';
import { useTokenStore } from '~/stores/modules/token';
import { useRouter } from 'vue-router';

const token = useTokenStore();
const router = useRouter();

const axiosInstance = axios.create({
  baseURL: import.meta.env.VITE_BASE_API_URL,
});

axiosInstance.interceptors.request.use(
  config => {
    if (token.available()) {
      config.headers['Authorization'] = `Bearer ${token.tokenVal}`;
    }
    // console.log(config);
    return config;
  },
  error => {
    // console.log(error);
    return Promise.reject(error);
  }
);

axiosInstance.interceptors.response.use(
  async response => {
    // console.log(response);
    if (response.data.code === 401) {
      token.clear();
      router.push("/login");
    }
    return response;
  },
  async error => {
    // console.error(error);
    if (error.response && error.response.status === 401) {
      token.clear();
      router.push("/login");
    }
    return Promise.reject(error);
  }
);

export default axiosInstance;
