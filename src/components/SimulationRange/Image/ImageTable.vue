<template>
  <div class="flex flex-col w-full">
    <div class="flex flex-row justify-between">
      <new-image v-model="newImageCreated" />
    </div>
    <div class="flex flex-col items-center w-full gap-2">
      <el-table :data="images" class="w-full">
        <el-table-column prop="id" label="ID" show-overflow-tooltip />
        <el-table-column
          prop="imageName"
          label="镜像名称"
          show-overflow-tooltip
        />
        <el-table-column
          prop="imageType"
          label="镜像类型"
          show-overflow-tooltip
        />
        <el-table-column prop="path" label="文件路径" show-overflow-tooltip />
        <el-table-column prop="version" label="版本" show-overflow-tooltip />
        <el-table-column
          prop="createTime"
          label="创建时间"
          show-overflow-tooltip
        />
        <el-table-column
          prop="updateTime"
          label="更新时间"
          show-overflow-tooltip
        />
        <el-table-column label="操作">
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
        v-model:current-page="pageNum"
        v-model:page-size="pageSize"
        :page-sizes="pageSizes"
        :background="true"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, watch } from "vue";
import { storeToRefs } from "pinia";
import useImagesStore from "~/stores/images";
import type { Image } from "~/types";

const imagesStore = useImagesStore();
const { images } = storeToRefs(imagesStore);
const { selectImagesByPage, deleteImage } = imagesStore;

const pageSizes = ref([10, 20, 30, 40]);
const pageNum = ref(1);
const pageSize = ref(10);
const total = ref<number | undefined>(undefined);

const fetchImages = async () => {
  const response = await selectImagesByPage(
    "",
    "",
    String(pageNum.value),
    String(pageSize.value)
  );
  total.value = response?.data.data.total;
};

onMounted(() => {
  fetchImages();
});

const handleSizeChange = (val: number) => {
  pageSize.value = val;
};
const handleCurrentChange = (val: number) => {
  pageNum.value = val;
};

// created
const newImageCreated = ref(false);
watch(newImageCreated, () => {
  fetchImages();
  newImageCreated.value = false;
});

// deleted
const deleteRow = async (row: Image) => {
  await deleteImage(row.id);
  fetchImages();
};
</script>
