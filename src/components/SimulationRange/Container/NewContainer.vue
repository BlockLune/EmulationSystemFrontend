<template>
  <el-button @click="newDialogVisible = true" type="primary"
    >新增靶机容器</el-button
  >
  <el-dialog v-model="newDialogVisible" title="新增靶机容器" width="30%">
    <el-form label-position="left" label-width="auto">
      <el-form-item label="靶机镜像">
        <el-select-v2
          v-model="newContainerParams.imageId"
          :options="
            allTargetImages?.map((image) => ({
              label: `${image.imageName} (${image.id})`,
              value: image.id,
            }))
          "
          clearable
          placeholder="请选择靶机镜像"
        />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="submit"> 确定 </el-button>
        <el-button @click="hideAndClearNewDialog">取消</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import { storeToRefs } from "pinia";
import useContainersStore from "~/stores/containers";

const containersStore = useContainersStore();
const { newContainerParams, allTargetImages } = storeToRefs(containersStore);
const { createContainer, selectTargetImage } = containersStore;

onMounted(async () => {
  await selectTargetImage();
});

const newDialogVisible = ref(false);

const submit = async () => {
  newDialogVisible.value = false;
  await createContainer();
};
const hideAndClearNewDialog = () => {
  newDialogVisible.value = false;
  // clear will be done by the pinia store
};
</script>
