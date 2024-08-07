import axiosInstance from "./axiosInstance";

const getTokenAndStore = async (loginName: string, password: string) => {
  try {
    const response = await axiosInstance.post('/system/user/login', {
      loginName: loginName,
      password: password,
    });
    if (response.data.code !== 200) {
      throw new Error(response.data.message);
    }
    const token = response.data.data;
    console.log('获取 Token 成功：', token);
    localStorage.setItem('token', token);
    return token;
  } catch (error) {
    console.error('获取 Token 失败：', error);
    throw error;
  }
}

const deleteStoredToken = () => {
  localStorage.removeItem('token');
}

const isTokenAvailable = () => {
  return !!localStorage.getItem('token');
}

export { getTokenAndStore, deleteStoredToken, isTokenAvailable };
