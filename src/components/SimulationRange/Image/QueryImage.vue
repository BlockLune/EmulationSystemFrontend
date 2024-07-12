<template>
  <div class="flex flex-row gap-2">
    <el-form :model="queryForm" class="flex flex-row gap-2 w-full">
      <el-form-item label="镜像名称" class="w-full">
        <el-input v-model="queryForm.imageName" clearable class="w-full" />
      </el-form-item>
      <el-form-item label="镜像类型">
        <el-select-v2
          class="w-64"
          v-model="queryForm.imageType"
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
        <el-button type="primary" @click="query">查询</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup lang="ts">
import { ref, defineModel } from "vue";
import useImagesStore from "~/stores/images";
import type { QueryImage } from "~/types";
import { ImageType } from "~/types";

const pageNum = defineModel("pageNum", { required: true });
const pageSize = defineModel("pageSize", { required: true });
const { selectImagesByPage } = useImagesStore();

const query = async () => {
  const response = await selectImagesByPage(
    String(pageNum.value),
    String(pageSize.value),
    String(pageNum.value),
    String(pageSize.value)
  );
  console.log(response);
};

const queryForm = ref<QueryImage>({} as QueryImage);
</script>
