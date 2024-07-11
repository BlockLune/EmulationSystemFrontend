import { ref } from "vue";
import { defineStore } from "pinia";
import axiosInstance from "../utils/axiosInstance";
import type { Image, NewImage } from "../types";

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
      console.error(err);
    }
  }
  const selectDefenseImage = async () => {
    try {
      const response = await axiosInstance.post("/exercise/selectDefenseImage");
      allDefenseImages.value = response.data.data;
      return response;
    } catch (err) {
      console.error(err);
    }
  }
  const selectTargetImage = async () => {
    try {
      const response = await axiosInstance.post("/exercise/selectTarget");
      allTargetImages.value = response.data.data;
      return response;
    } catch (err) {
      console.error(err);
    }
  }
  const uploadImage = async (newImage: NewImage) => {
    try {
      const response = await axiosInstance.post("/image/uploadImage", newImage);
      return response;
    } catch (err) {
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