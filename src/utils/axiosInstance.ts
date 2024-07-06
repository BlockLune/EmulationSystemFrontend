import axios from 'axios';

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

export default axiosInstance;
