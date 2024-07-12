<template>
  <div class="flex flex-row gap-2">
    <el-form class="flex flex-row gap-2 w-full">
      <el-form-item label="镜像名称" class="w-full">
        <el-input
          v-model="queryImageParams.imageName"
          clearable
          class="w-full"
        />
      </el-form-item>
      <el-form-item label="镜像类型">
        <el-select-v2
          class="w-64"
          v-model="queryImageParams.imageType"
          placeholder="请选择"
          clearable
          :options="
            Object.entries(ImageType)
              .filter(([key, value]) => typeof value === 'number')
              .map(([label, value]) => ({ label, value }))
          "
        />
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          @click="
            () => {
              paginationInfo.pageNum = 1;
              selectImagesByPage();
            }
          "
          >查询</el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup lang="ts">
import { storeToRefs } from "pinia";
import useImagesStore from "~/stores/images";
import { ImageType } from "~/types";

const imagesStore = useImagesStore();
const { queryImageParams, paginationInfo } = storeToRefs(imagesStore);
const { selectImagesByPage } = imagesStore;
</script>
