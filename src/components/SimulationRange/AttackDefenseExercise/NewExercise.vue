<template>
  <el-button @click="showNewExerciseDialog">新增演练</el-button>
  <el-dialog v-model="newExerciseDialogVisible" title="新增演练" width="30%">
    <el-form
      ref="newExerciseFormRef"
      :model="newExerciseForm"
      label-position="left"
      label-width="auto"
    >
      <el-form-item label="演练名称" prop="exerciseName">
        <el-input
          v-model="newExerciseForm.exerciseName"
          placeholder="单行输入"
        />
      </el-form-item>
      <el-form-item label="演练靶机" prop="targetImageId">
        <el-select-v2
          v-model="newExerciseForm.targetImageId"
          :options="
            allTargetImages?.map((image) => ({
              label: image.imageName,
              value: image.id,
            }))
          "
          placeholder="请选择"
          clearable
        />
      </el-form-item>
      <el-form-item label="开始时间" prop="startTime">
        <el-date-picker
          v-model="newExerciseForm.startTime"
          type="datetime"
          value-format="YYYY-MM-DD HH:mm:ss"
          placeholder="选择日期和时间"
        />
      </el-form-item>
      <el-form-item label="停止时间" prop="endTime">
        <el-date-picker
          v-model="newExerciseForm.endTime"
          type="datetime"
          placeholder="选择日期和时间"
          value-format="YYYY-MM-DD HH:mm:ss"
        />
      </el-form-item>
      <el-form-item label="攻击镜像" prop="attackImageId">
        <el-select-v2
          v-model="newExerciseForm.attackImageId"
          :options="
            allAttackImages?.map((image) => ({
              label: `${image.imageName} (${image.id})`,
              value: image.id,
            }))
          "
          placeholder="请选择"
          clearable
        />
      </el-form-item>
      <el-form-item label="攻击开始时间" prop="attackStartTime">
        <el-date-picker
          v-model="newExerciseForm.attackStartTime"
          type="datetime"
          placeholder="选择日期和时间"
          value-format="YYYY-MM-DD HH:mm:ss"
        />
      </el-form-item>
      <el-form-item label="攻击终止时间" prop="attackEndTime">
        <el-date-picker
          v-model="newExerciseForm.attackEndTime"
          type="datetime"
          placeholder="选择日期和时间"
          value-format="YYYY-MM-DD HH:mm:ss"
        />
      </el-form-item>
      <el-form-item label="防御镜像" prop="defenseImageId">
        <el-select-v2
          v-model="newExerciseForm.defendImageId"
          :options="
            allDefenseImages?.map((image) => ({
              label: `${image.imageName} (${image.id})`,
              value: image.id,
            }))
          "
          placeholder="请选择"
          clearable
        />
      </el-form-item>
      <el-form-item label="防御启动时间" prop="defendStartTime">
        <el-date-picker
          v-model="newExerciseForm.defendStartTime"
          type="datetime"
          placeholder="选择日期和时间"
          value-format="YYYY-MM-DD HH:mm:ss"
        />
      </el-form-item>
      <el-form-item label="防御开始时间" prop="defendEndTime">
        <el-date-picker
          v-model="newExerciseForm.defendEndTime"
          type="datetime"
          placeholder="选择日期和时间"
          value-format="YYYY-MM-DD HH:mm:ss"
        />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="submit"> 确定 </el-button>
        <el-button @click="hideAndClearNewExerciseDialog(newExerciseFormRef)"
          >取消</el-button
        >
      </span>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from "vue";
import type { NewExercise } from "~/types";
import type { FormInstance } from "element-plus";
import { storeToRefs } from "pinia";
import useExercisesStore from "~/stores/exercises";
import useImagesStore from "~/stores/images";

const exercisesStore = useExercisesStore();
const { exercises } = storeToRefs(exercisesStore);
const { createExercise } = exercisesStore;

const imagesStore = useImagesStore();
const { images, allAttackImages, allDefenseImages, allTargetImages } =
  storeToRefs(imagesStore);
const { selectAttackImage, selectDefenseImage, selectTargetImage } =
  imagesStore;

onMounted(async () => {
  await selectAttackImage();
  await selectDefenseImage();
  await selectTargetImage();
});

const newExerciseDialogVisible = ref(false);
const newExerciseForm = reactive<NewExercise>({} as NewExercise);
const newExerciseFormRef = ref<FormInstance>();

const showNewExerciseDialog = () => {
  newExerciseDialogVisible.value = true;
};
const hideAndClearNewExerciseDialog = (formEl: FormInstance | undefined) => {
  newExerciseDialogVisible.value = false;
  if (!formEl) return;
  formEl.resetFields();
};

const submit = async () => {
  await createExercise(newExerciseForm);
};
</script>
