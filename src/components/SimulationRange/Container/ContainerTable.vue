<template>
  <div class="flex flex-col gap-2 items-center w-full">
    <el-table :data="containers" stripe>
      <el-table-column
        show-overflow-tooltip
        prop="id"
        label="ID"
        fixed
        width="100"
      >
      </el-table-column>
      <el-table-column
        show-overflow-tooltip
        prop="containerId"
        label="容器 ID (SHA256)"
        fixed
        width="200"
      ></el-table-column>
      <el-table-column
        show-overflow-tooltip
        prop="containerName"
        label="容器名称"
        fixed
        width="200"
      ></el-table-column>
      <el-table-column show-overflow-tooltip label="容器类型" width="150">
        <template #default="scope">
          <span>{{ ContainerType[scope.row.containerType] }}</span>
        </template>
      </el-table-column>
      <el-table-column show-overflow-tooltip label="状态" width="200">
        <template #default="scope">
          <span>{{
            scope.row.status in ContainerStatus
              ? ContainerStatus[scope.row.status]
              : scope.row.status
          }}</span>
        </template>
      </el-table-column>
      <el-table-column
        show-overflow-tooltip
        prop="imageId"
        label="镜像ID"
        width="200"
      ></el-table-column>
      <el-table-column
        show-overflow-tooltip
        prop="imageName"
        label="镜像名称"
        width="200"
      ></el-table-column>
      <el-table-column
        show-overflow-tooltip
        prop="rangeName"
        label="靶场名称"
        width="200"
      ></el-table-column>
      <el-table-column
        show-overflow-tooltip
        prop="attackTime"
        label="攻击时间"
        width="200"
      ></el-table-column>
      <el-table-column
        show-overflow-tooltip
        prop="defendTime"
        label="防御时间"
        width="200"
      ></el-table-column>
      <el-table-column
        show-overflow-tooltip
        prop="startTime"
        label="启动时间"
        width="200"
      ></el-table-column>
      <el-table-column
        show-overflow-tooltip
        prop="endTime"
        label="终止时间"
        width="200"
      ></el-table-column>
      <el-table-column
        show-overflow-tooltip
        prop="lastTime"
        label="持续时间"
        width="200"
      ></el-table-column>
      <el-table-column
        show-overflow-tooltip
        prop="createTime"
        label="创建时间"
        width="200"
      ></el-table-column>
      <el-table-column
        show-overflow-tooltip
        prop="updateTime"
        label="更新时间"
        width="200"
      ></el-table-column>
    </el-table>
    <el-pagination
      v-model:current-page="paginationInfo.pageNum"
      v-model:page-size="paginationInfo.pageSize"
      :page-sizes="pageSizes"
      :background="true"
      layout="total, sizes, prev, pager, next, jumper"
      :total="paginationInfo.total"
      @size-change="
        (val: number) => {
          paginationInfo.pageSize = val;
          selectContainersByPage();
        }
      "
      @current-change="
        (val: number) => {
          paginationInfo.pageNum = val;
          selectContainersByPage();
        }
      "
    />
  </div>
</template>

<script lang="ts" setup>
import { ref, onMounted } from "vue";
import { storeToRefs } from "pinia";
import useContainersStore from "~/stores/containers";
import { ContainerType, ContainerStatus } from "~/types";

const containersStore = useContainersStore();
const { containers, paginationInfo } = storeToRefs(containersStore);
const { selectContainersByPage } = containersStore;

const pageSizes = ref([10, 20, 30, 40]);

onMounted(() => {
  selectContainersByPage();
});
</script>
