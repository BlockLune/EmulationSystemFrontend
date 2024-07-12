<template>
  <div class="flex flex-col items-center w-full gap-2">
    <el-table :data="images" class="w-full">
      <el-table-column
        prop="id"
        label="ID"
        show-overflow-tooltip
        fixed
        width="200"
      />
      <el-table-column
        prop="imageName"
        label="镜像名称"
        show-overflow-tooltip
        fixed
        width="100"
      />
      <el-table-column
        prop="imageType"
        label="镜像类型"
        show-overflow-tooltip
        width="100"
      />
      <el-table-column
        prop="path"
        label="文件路径"
        show-overflow-tooltip
        min-width="200"
      />
      <el-table-column
        prop="version"
        label="版本"
        show-overflow-tooltip
        width="100"
      />
      <el-table-column
        prop="createTime"
        label="创建时间"
        show-overflow-tooltip
        width="200"
      />
      <el-table-column
        prop="updateTime"
        label="更新时间"
        show-overflow-tooltip
        width="200"
      />
      <el-table-column label="操作" fixed="right" width="70">
        <template #default="{ row }">
          <el-popconfirm
            title="确认删除？"
            confirm-button-text="确认"
            cancel-button-text="取消"
            @confirm="deleteRow(row)"
          >
            <template #reference>
              <el-button link type="danger">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
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
          selectImagesByPage();
        }
      "
      @current-change="
        (val: number) => {
          paginationInfo.pageNum = val;
          selectImagesByPage();
        }
      "
    />
  </div>
</template>

<script lang="ts" setup>
import { ref, onMounted } from "vue";
import { storeToRefs } from "pinia";
import useImagesStore from "~/stores/images";
import { Image } from "~/types";

const imagesStore = useImagesStore();
const { images, paginationInfo, deleteImageParams } = storeToRefs(imagesStore);
const { selectImagesByPage, deleteImage } = imagesStore;

const pageSizes = ref([10, 20, 30, 40]);

onMounted(() => {
  selectImagesByPage();
});

const deleteRow = (row: Image) => {
  deleteImageParams.value.id = row.id;
  deleteImage();
};
</script>
