import { ref } from "vue";
import { defineStore } from "pinia";
import router from "~/router";
import axiosInstance from "~/utils/axiosInstance";

interface User {
  loginName: string;
  token: string;
}

const useAuthStore = defineStore("auth", () => {
  const user = ref<User | null>(JSON.parse(localStorage.getItem("user") ?? "null"));

  const login = async (loginName: string, password: string) => {
    try {
      const response = await axiosInstance.post('/system/user/login', {
        loginName,
        password,
      });
      if (response.data.code !== 200) {
        throw new Error(response.data.message);
      }
      const token = response.data.data;
      console.log(token);
      user.value = { loginName, token };
      localStorage.setItem('user', JSON.stringify(user.value));
      router.push('/dashboard');
    } catch (error) {
      throw error;
    }
  }

  const logout = () => {
    user.value = null;
    localStorage.removeItem('user');
    router.push('/login');
  }

  const isTokenValid = () => {
    const token = user.value?.token;
    if (!token) {
      return false
    }
    try {
      const payload = JSON.parse(atob(token.split('.')[1]))
      const exp = payload.exp
      const currentTime = Math.floor(Date.now() / 1000)
      return exp > currentTime
    } catch (error) {
      return false
    }
  }

  return { user, login, logout, isTokenValid };
});

export default useAuthStore;
