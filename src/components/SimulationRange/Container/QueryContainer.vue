<template>
  <div class="flex flex-row gap-2">
    <el-form class="flex flex-row gap-2 w-full">
      <el-form-item label="容器名称" class="w-full">
        <el-input
          v-model="queryContainerParams.containerName"
          clearable
          class="w-full"
        />
      </el-form-item>
      <el-form-item label="目标名称" class="w-full">
        <el-input
          v-model="queryContainerParams.targetName"
          clearable
          class="w-full"
        />
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          @click="
            () => {
              paginationInfo.pageNum = 1;
              selectContainersByPage();
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
import useContainersStore from "~/stores/containers";

const containersStore = useContainersStore();
const { queryContainerParams, paginationInfo } = storeToRefs(containersStore);
const { selectContainersByPage } = containersStore;
</script>
