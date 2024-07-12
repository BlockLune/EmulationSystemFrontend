<template>
  <el-button @click="showNewDialog" type="primary">上传镜像</el-button>
  <el-dialog v-model="newDialogVisible" title="上传镜像" width="30%">
    <el-form
      ref="newFormRef"
      :model="newForm"
      label-position="left"
      label-width="auto"
    >
      <el-form-item label="资源压缩包">
        <div class="flex flex-row gap-2 justify-between w-full">
          <el-button
            type="primary"
            size="default"
            @click="open"
            :class="files && files.length > 0 ? '' : 'w-full'"
            >选择文件</el-button
          >
          <span v-if="files && files.length > 0">{{ files[0].name }}</span>
        </div>
      </el-form-item>
      <el-form-item label="镜像名称">
        <el-input v-model="newForm.imageName" clearable />
      </el-form-item>
      <el-form-item label="镜像版本">
        <el-input v-model="newForm.version" clearable />
      </el-form-item>
      <el-form-item label="镜像类型">
        <el-select-v2
          v-model="newForm.imageType"
          clearable
          placeholder="请选择"
          :options="
            Object.entries(ImageType)
              .filter(([key, value]) => typeof value === 'number')
              .map(([label, value]) => ({ label, value }))
          "
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
import { ref, reactive } from "vue";
import type { NewImage } from "~/types";
import { ImageType } from "~/types";
import type { FormInstance } from "element-plus";
import useImagesStore from "~/stores/images";

const { uploadImage } = useImagesStore();

const newDialogVisible = ref(false);
const newForm = reactive<NewImage>({} as NewImage);
const newFormRef = ref<FormInstance>();

const newImageCreated = defineModel();

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
  await uploadImage(newForm);
  newImageCreated.value = true;
};

// file handler
import { useFileDialog } from "@vueuse/core";
const { files, open, onChange } = useFileDialog({
  multiple: false,
  accept: `application/x-freearc,
    application/x-bzip,
    application/x-bzip2,
    application/gzip,
    application/java-archive,
    application/vnd.rar,
    application/x-tar,
    application/zip`,
});

onChange((files) => {
  if (files) {
    // assert(files.length === 1);
    newForm.file = files[0];
  }
});
</script>
