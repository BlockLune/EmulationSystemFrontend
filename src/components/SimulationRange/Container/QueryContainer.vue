<template>
  <div class="flex flex-row gap-2">
    <el-form :model="queryForm" class="flex flex-row gap-2 w-full">
      <el-form-item label="容器名称" class="w-full">
        <el-input v-model="queryForm.containerName" clearable class="w-full" />
      </el-form-item>
      <el-form-item label="目标名称" class="w-full">
        <el-input v-model="queryForm.targetName" clearable class="w-full" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="query">查询</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup lang="ts">
import { ref, defineModel } from "vue";
import useContainersStore from "~/stores/containers";
import type { QueryContainer } from "~/types";

const pageNum = defineModel("pageNum", { required: true });
const pageSize = defineModel("pageSize", { required: true });
const { selectContainersByPage } = useContainersStore();

const query = async () => {
  const response = await selectContainersByPage(
    queryForm.value.containerName,
    String(pageNum.value),
    String(pageSize.value),
    "",
    queryForm.value.targetName
  );
  console.log(response);
};

const queryForm = ref<QueryContainer>({} as QueryContainer);
</script>
