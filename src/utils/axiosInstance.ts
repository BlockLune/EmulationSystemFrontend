import axios from 'axios';
import { deleteStoredToken } from './handleToken';

const axiosInstance = axios.create({
  baseURL: import.meta.env.VITE_BASE_API_URL,
});

axiosInstance.interceptors.request.use(
  config => {
    const token = localStorage.getItem('token');
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`;
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
      deleteStoredToken();
    }
    return response;
  },
  async error => {
    // console.error(error);
    if (error.response && error.response.status === 401) {
      deleteStoredToken();
    }
    return Promise.reject(error);
  }
);

export default axiosInstance;
