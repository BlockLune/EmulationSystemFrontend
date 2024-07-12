import axiosInstance from "./axiosInstance";
import { ElMessage } from "element-plus";

const checkAlive = async () => {
    try {
        const response = await axiosInstance.post("/system/user/getUserInfo");
        if (response.data.code !== 200) {
            ElMessage.error("登录状态已过期，请重新登录");
            return false;
        }
        return true;
    } catch (err) {
        ElMessage.error("登录状态已过期，请重新登录");
        return false;
    }
}

export default checkAlive;
