import { ref } from "vue";
import { defineStore } from "pinia";
import axiosInstance from "../utils/axiosInstance";
import type { Image, NewImage, QueryImage, DeleteImage, PaginationInfo } from "../types";
import { ElMessage } from "element-plus";

const useImagesStore = defineStore('images', () => {
  const images = ref<Image[] | undefined>(undefined);
  const newImageParams = ref<NewImage>({
    file: undefined, imageName: undefined,
    imageType: undefined, version: undefined
  });
  const queryImageParams = ref<QueryImage>({
    imageName: undefined, imageType: undefined
  });
  const deleteImageParams = ref<DeleteImage>({ id: undefined });
  const paginationInfo = ref<PaginationInfo>({
    pageNum: 1, pageSize: 10, total: undefined
  });
  const allAttackImages = ref<Image[] | undefined>(undefined);
  const allDefenseImages = ref<Image[] | undefined>(undefined);
  const allTargetImages = ref<Image[] | undefined>(undefined);
  const selectImagesByPage = async () => {
    try {
      const response = await axiosInstance.post("/image/selectByPage", {
        imageName: queryImageParams.value?.imageName,
        imageType: queryImageParams.value?.imageType,
        pageNum: paginationInfo.value.pageNum,
        pageSize: paginationInfo.value.pageSize,
      });
      if (response.data.code !== 200) {
        ElMessage.error(response.data.message);
      } else {
        images.value = response.data.data.list;
        paginationInfo.value.total = response.data.data.total;
        ElMessage.success("镜像查询成功");
      }
      return response;
    } catch (err) {
      ElMessage.error("" + err);
      console.error(err);
    }
  }
  const selectAttackImage = async () => {
    try {
      const response = await axiosInstance.post("/exercise/selectAttackImage");
      allAttackImages.value = response.data.data;
      return response;
    } catch (err) {
      ElMessage.error("获取攻击镜像失败: " + err);
      console.error(err);
    }
  }
  const selectDefenseImage = async () => {
    try {
      const response = await axiosInstance.post("/exercise/selectDefenseImage");
      allDefenseImages.value = response.data.data;
      return response;
    } catch (err) {
      ElMessage.error("获取防御镜像失败: " + err);
      console.error(err);
    }
  }
  const selectTargetImage = async () => {
    try {
      const response = await axiosInstance.post("/exercise/selectTarget");
      allTargetImages.value = response.data.data;
      return response;
    } catch (err) {
      ElMessage.error("获取靶机镜像失败: " + err);
      console.error(err);
    }
  }
  const clearNewImageParams = () => {
    newImageParams.value.file = undefined;
    newImageParams.value.imageName = undefined;
    newImageParams.value.imageType = undefined;
    newImageParams.value.version = undefined;
  }
  const uploadImage = async () => {
    if (!newImageParams.value.file ||
      !newImageParams.value.imageName ||
      !newImageParams.value.imageType ||
      !newImageParams.value.version) {
      return;
    }
    try {
      const formData = new FormData();
      formData.append("file", newImageParams.value.file);

      const url = `/image/uploadImage` +
        `?imageName=${encodeURIComponent(newImageParams.value.imageName)}` +
        `&imageType=${encodeURIComponent(newImageParams.value.imageType)}` +
        `&version=${encodeURIComponent(newImageParams.value.version)}`
      const response = await axiosInstance.post(url, formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      });
      // i'm not sure what the code should be
      // i suppose here to be 201 since it represents created
      // however, the backend returns 200 when i was testing
      // so i just put 200 and 201 here
      if (response.data.code !== 200 && response.data.code !== 201) {
        ElMessage.error("上传镜像失败");
        console.error(response.data);
      } else {
        ElMessage.success("上传镜像成功");
        selectImagesByPage();
      }
      return response;
    } catch (err) {
      ElMessage.error("上传镜像失败: " + err);
      console.error(err);
    } finally {
      clearNewImageParams();
    }
  }
  const deleteImage = async () => {
    try {
      const response = await axiosInstance
        .post("/image/deleteImage", deleteImageParams.value);
      if (response.data.code !== 200) {
        ElMessage.error("删除镜像失败");
        console.error(response.data);
      } else {
        ElMessage.success("删除镜像成功");
        selectImagesByPage();
      }
      return response;
    } catch (err) {
      ElMessage.error("删除镜像失败: " + err);
      console.error(err);
    } finally {
      deleteImageParams.value = { id: undefined };
    }
  }
  const getImageNameById = (imageId: string, imageType: string | undefined) => {
    if (imageType === "2") {
      // attack image
      return allAttackImages.value?.find(image => image.id === imageId)?.imageName;
    } else if (imageType === "3") {
      // defense image
      return allDefenseImages.value?.find(image => image.id === imageId)?.imageName;
    } else if (imageType === "4") {
      // target image
      return allTargetImages.value?.find(image => image.id === imageId)?.imageName;
    } else {
      return images.value?.find(image => image.id === imageId)?.imageName;
    }
  }
  return {
    images, newImageParams, queryImageParams, paginationInfo, deleteImageParams,
    allAttackImages, allDefenseImages, allTargetImages,
    selectImagesByPage, selectAttackImage, selectDefenseImage,
    selectTargetImage, uploadImage, deleteImage, getImageNameById,
    clearNewImageParams
  };
});

export default useImagesStore;
