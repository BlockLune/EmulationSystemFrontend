import { ref } from "vue";
import { defineStore } from "pinia";
import axios from 'axios';
import pinia from "~/stores";

export const useTokenStore = defineStore('token', () => {
  const tokenVal = ref<string>("");
  const getOrUpdate = async (loginName: string, password: string) => {
    try {
      tokenVal.value = (await axios.create({
        baseURL: import.meta.env.BASE_URL
      }).post('/system/user/login', {
        loginName: loginName,
        password: password,
      })).data.data;
      // console.log('获取 Token 成功：', tokenVal.value);
    } catch (error) {
      // console.error('获取 Token 失败：', error);
    }
  }
  const clear = () => {
    tokenVal.value = "";
  }
  const available = (): boolean => {
    return tokenVal.value !== "";
  }

  return { tokenVal, getOrUpdate, clear, available };
});

// use outside setup
export const useTokenStoreHook = () => useTokenStore(pinia);
