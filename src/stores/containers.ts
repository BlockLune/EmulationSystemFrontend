import { ref } from "vue";
import { defineStore } from "pinia";
import axiosInstance from "../utils/axiosInstance";
import type { Image, Container, NewContainer, QueryContainer, PaginationInfo } from "../types";
import { ElMessage } from "element-plus";

const useContainersStore = defineStore('containers', () => {
    const containers = ref<Container[] | undefined>(undefined);
    const newContainerParams = ref<NewContainer>({ imageId: undefined });
    const queryContainerParams = ref<QueryContainer>({
        containerName: undefined, status: undefined, targetName: undefined
    });
    const paginationInfo = ref<PaginationInfo>({
        pageNum: 1, pageSize: 10, total: undefined
    });
    const allTargetImages = ref<Image[] | undefined>(undefined);
    const selectContainersByPage = async () => {
        try {
            const response = await axiosInstance
                .post("/container/selectByPage", {
                    containerName: queryContainerParams.value?.containerName,
                    pageNum: paginationInfo.value.pageNum,
                    pageSize: paginationInfo.value.pageSize,
                    status: queryContainerParams.value?.status,
                    targetName: queryContainerParams.value?.targetName,
                });
            if (response.data.code !== 200) {
                ElMessage.error(response.data.message);
            } else {
                containers.value = response.data.data.list;
                // paginationInfo.value.pageNum = response.data.data.pageNum;
                // paginationInfo.value.pageSize = response.data.data.pageSize;
                paginationInfo.value.total = response.data.data.total;
                ElMessage.success("容器查询成功");
            }
            return response;
        } catch (err) {
            ElMessage.error("" + err);
            console.error(err);
        }
    }
    const createContainer = async () => {
        try {
            const response = await axiosInstance
                .post("/container/create", newContainerParams.value)
            if (response.data.code === 200 || response.data.code === 201) {
                ElMessage.success("创建成功");
                selectContainersByPage();
            } else {
                ElMessage.error("创建失败");
            }
            return response;
        } catch (err) {
            ElMessage.error("" + err);
            console.error(err);
        } finally {
            newContainerParams.value = { imageId: undefined };
        }
    }
    const selectContainerStatus = async (containerId: string) => {
        try {
            const response = await axiosInstance
                .post("/container/selectContainerStatus", {
                    containerId: containerId
                });
            if (response.data.code !== 200) {
                ElMessage.error(response.data.message);
            } else {
                ElMessage.success(response.data.message);
            }
            return response;
        } catch (err) {
            ElMessage.error("" + err);
            console.error(err);
        }
    }
    const selectTargetImage = async () => {
        try {
            const response = await axiosInstance
                .post("/container/selectTargetImage");
            allTargetImages.value = response.data.data;
            return response;
        } catch (err) {
            ElMessage.error("" + err);
            console.error(err);
        }
    }
    return {
        containers, newContainerParams,
        queryContainerParams, paginationInfo, allTargetImages,
        selectContainersByPage, createContainer,
        selectContainerStatus, selectTargetImage
    }
});

export default useContainersStore;