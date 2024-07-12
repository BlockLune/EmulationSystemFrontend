import { ref } from "vue";
import { defineStore } from "pinia";
import axiosInstance from "../utils/axiosInstance";
import type { Image, Container, NewContainer } from "../types";
import { ElMessage } from "element-plus";

const useContainersStore = defineStore('containers', () => {
    const containers = ref<Container[] | undefined>(undefined);
    const allTargetImages = ref<Image[] | undefined>(undefined);
    const selectContainersByPage = async (
        containerName: string,
        pageNum: string,
        pageSize: string,
        status: string,
        targetName: string
    ) => {
        try {
            const response = await axiosInstance.post("/container/selectByPage", {
                containerName: containerName,
                pageNum: pageNum,
                pageSize: pageSize,
                status: status,
                targetName: targetName
            });
            containers.value = response.data.data.list;
            return response;
        } catch (err) {
            console.error(err);
        }
    }
    const createContainer = async (newContainer: NewContainer) => {
        try {
            const response = await axiosInstance.post("/container/create", newContainer)
            if (response.data.code === 200 || response.data.code === 201) {
                ElMessage.success("创建成功");
            } else {
                ElMessage.error("创建失败");
            }
            return response;
        } catch (err) {
            console.error(err);
        }
    }
    const selectContainerStatus = async (containerId: string) => {
        try {
            const response = await axiosInstance.post("/container/selectContainerStatus", {
                containerId: containerId
            });
            return response;
        } catch (err) {
            console.error(err);
        }
    }
    const selectTargetImage = async () => {
        try {
            const response = await axiosInstance.post("/container/selectTargetImage");
            allTargetImages.value = response.data.data;
            return response;
        } catch (err) {
            console.error(err);
        }
    }
    return { containers, allTargetImages, selectContainersByPage, createContainer, selectContainerStatus, selectTargetImage }
});

export default useContainersStore;