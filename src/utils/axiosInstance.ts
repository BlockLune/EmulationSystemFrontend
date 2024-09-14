import axios from 'axios';
import useAuthStore from '~/stores/auth';

const axiosInstance = axios.create({
  baseURL: import.meta.env.VITE_BASE_API_URL,
  timeout: import.meta.env.VITE_AXIOS_TIMEOUT || 0,
});

axiosInstance.interceptors.request.use(
  config => {
    const { user } = useAuthStore();
    if (user?.token) {
      config.headers.Authorization = `Bearer ${user.token}`;
    }
    return config;
  },
  error => {
    return Promise.reject(error);
  }
);

axiosInstance.interceptors.response.use(
  async response => {
    return response;
  },
  async error => {
    const { logout } = useAuthStore();
    if (error.response) {
      if ([401, 403].includes(error.response.status)) {
        logout();
      }
      const errorMessage = error.response.data?.message || error.response.statusText || 'Unknown error';
      return Promise.reject(errorMessage);
    }
    return Promise.reject(error);
  }
);

export default axiosInstance;
