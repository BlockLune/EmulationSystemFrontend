import { ref } from "vue";
import { defineStore } from "pinia";
import axiosInstance from "../utils/axiosInstance";
import type { Image, NewImage } from "../types";
import { ElMessage } from "element-plus";

const useImagesStore = defineStore('images', () => {
  const images = ref<Image[] | undefined>(undefined);
  const allAttackImages = ref<Image[] | undefined>(undefined);
  const allDefenseImages = ref<Image[] | undefined>(undefined);
  const allTargetImages = ref<Image[] | undefined>(undefined);
  const selectImagesByPage = async (
    imageName: string,
    imageType: string,
    pageNum: string,
    pageSize: string,
  ) => {
    try {
      const response = await axiosInstance.post("/image/selectByPage", {
        imageName: imageName,
        imageType: imageType,
        pageNum: pageNum,
        pageSize: pageSize,
      });
      images.value = response.data.data.list;
      return response;
    } catch (err) {
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
  const uploadImage = async (newImage: NewImage) => {
    try {
      const formData = new FormData();
      formData.append("file", newImage.file);

      const url = `/image/uploadImage` +
        `?imageName=${encodeURIComponent(newImage.imageName)}` +
        `&imageType=${encodeURIComponent(newImage.imageType)}` +
        `&version=${encodeURIComponent(newImage.version)}`
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
      }
      return response;
    } catch (err) {
      ElMessage.error("上传镜像失败: " + err);
      console.error(err);
    }
  }
  const deleteImage = async (imageId: string) => {
    try {
      const response = await axiosInstance.post("/image/deleteImage", {
        id: imageId,
      });
      return response;
    } catch (err) {
      ElMessage.error("删除镜像失败: " + err);
      console.error(err);
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
  return { images, allAttackImages, allDefenseImages, allTargetImages, selectImagesByPage, selectAttackImage, selectDefenseImage, selectTargetImage, uploadImage, deleteImage, getImageNameById };
});

export default useImagesStore;
