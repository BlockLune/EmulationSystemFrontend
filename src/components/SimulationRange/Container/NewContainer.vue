<template>
  <el-button @click="showNewDialog" type="primary">新增靶机容器</el-button>
  <el-dialog v-model="newDialogVisible" title="新增靶机容器" width="30%">
    <el-form
      ref="newFormRef"
      :model="newForm"
      label-position="left"
      label-width="auto"
    >
      <el-form-item label="靶机镜像">
        <el-select-v2
          v-model="newForm.imageId"
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
        <el-button @click="hideAndClearNewDialog(newFormRef)">取消</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from "vue";
import type { NewContainer } from "~/types";
import type { FormInstance } from "element-plus";
import { storeToRefs } from "pinia";
import useContainersStore from "~/stores/containers";

const newDialogVisible = ref(false);
const newForm = reactive<NewContainer>({} as NewContainer);
const newFormRef = ref<FormInstance>();

const newContainerCreated = defineModel({ required: true });

const containersStore = useContainersStore();
const { allTargetImages } = storeToRefs(containersStore);
const { createContainer, selectTargetImage } = containersStore;

onMounted(async () => {
  await selectTargetImage();
});

const showNewDialog = () => {
  newDialogVisible.value = true;
};
const hideAndClearNewDialog = (formEl: FormInstance | undefined) => {
  newDialogVisible.value = false;
  if (!formEl) return;
  formEl.resetFields();
};

const submit = async () => {
  newDialogVisible.value = false;
  await createContainer(newForm);
  newContainerCreated.value = true;
};
</script>
