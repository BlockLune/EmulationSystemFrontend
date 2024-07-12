<template>
  <el-button @click="newDialogVisible = true" type="primary"
    >上传镜像</el-button
  >
  <el-dialog v-model="newDialogVisible" title="上传镜像" width="30%">
    <el-form label-position="left" label-width="auto">
      <el-form-item label="资源压缩包">
        <div class="flex flex-row gap-2 justify-between w-full">
          <el-button
            type="primary"
            size="default"
            @click="open"
            :class="newImageParams.file ? '' : 'w-full'"
            >选择文件</el-button
          >
          <span v-if="newImageParams.file">{{ newImageParams.file.name }}</span>
        </div>
      </el-form-item>
      <el-form-item label="镜像名称">
        <el-input v-model="newImageParams.imageName" clearable />
      </el-form-item>
      <el-form-item label="镜像版本">
        <el-input v-model="newImageParams.version" clearable />
      </el-form-item>
      <el-form-item label="镜像类型">
        <el-select-v2
          v-model="newImageParams.imageType"
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
        <el-button @click="hideAndClearNewDialog">取消</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { ref } from "vue";
import { storeToRefs } from "pinia";
import { ImageType } from "~/types";
import useImagesStore from "~/stores/images";

const imagesStore = useImagesStore();
const { newImageParams } = storeToRefs(imagesStore);
const { uploadImage, clearNewImageParams } = imagesStore;

const newDialogVisible = ref(false);

const submit = async () => {
  newDialogVisible.value = false;
  await uploadImage();
};
const hideAndClearNewDialog = () => {
  newDialogVisible.value = false;
  clearNewImageParams();
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
    newImageParams.value.file = files[0];
  }
});
</script>
