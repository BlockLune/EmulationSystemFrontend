<template>
  <div class="flex flex-col w-full">
    <div class="flex flex-row justify-between">
      <new-exercise v-model="newExerciseCreated" />
      <el-form :model="queryExerciseForm" class="flex flex-row gap-2">
        <el-form-item label="演练名称">
          <el-input v-model="queryExerciseForm.exerciseName" clearable />
        </el-form-item>
        <el-form-item label="状态">
          <el-select-v2
            class="w-48"
            v-model="queryExerciseForm.status"
            placeholder="请选择"
            clearable
            :options="
              Object.entries(ExerciseStatus)
                .filter(([key, value]) => typeof value === 'number')
                .map(([label, value]) => ({ label, value }))
            "
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="query">查询</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="flex flex-col items-center w-full gap-2">
      <el-table :data="exercises" class="w-full">
        <el-table-column
          fixed="left"
          width="100"
          show-overflow-tooltip
          prop="id"
          label="ID"
        ></el-table-column>
        <el-table-column
          fixed="left"
          width="150"
          show-overflow-tooltip
          prop="exerciseName"
          label="演练名称"
        ></el-table-column>
        <el-table-column
          width="200"
          show-overflow-tooltip
          prop="network"
          label="网络"
        ></el-table-column>
        <el-table-column show-overflow-tooltip label="状态" width="100">
          <template #default="scope">
            <span>{{ ExerciseStatus[scope.row.status] }}</span>
          </template>
        </el-table-column>

        <el-table-column
          width="200"
          show-overflow-tooltip
          prop="targetImageId"
          label="靶机镜像 ID"
        ></el-table-column>

        <el-table-column label="攻击">
          <el-table-column
            width="200"
            show-overflow-tooltip
            prop="attackImageId"
            label="攻击镜像 ID"
          ></el-table-column>
          <el-table-column
            width="100"
            show-overflow-tooltip
            prop="attackTimes"
            label="攻击次数"
          ></el-table-column>
          <el-table-column
            width="200"
            show-overflow-tooltip
            prop="attackStartTime"
            label="攻击开始时间"
          ></el-table-column>
          <el-table-column
            width="200"
            show-overflow-tooltip
            prop="attackEndTime"
            label="攻击结束时间"
          ></el-table-column>
        </el-table-column>

        <el-table-column label="防御">
          <el-table-column
            width="200"
            show-overflow-tooltip
            prop="defendImageId"
            label="防御镜像 ID"
          ></el-table-column>
          <el-table-column
            width="100"
            show-overflow-tooltip
            prop="defendTimes"
            label="防御次数"
          ></el-table-column>
          <el-table-column
            width="200"
            show-overflow-tooltip
            prop="defendStartTime"
            label="防御开始时间"
          ></el-table-column>
          <el-table-column
            width="200"
            show-overflow-tooltip
            prop="defendEndTime"
            label="防御结束时间"
          ></el-table-column>
        </el-table-column>

        <el-table-column
          width="200"
          show-overflow-tooltip
          prop="startTime"
          label="开始时间"
        ></el-table-column>
        <el-table-column
          width="200"
          show-overflow-tooltip
          prop="endTime"
          label="结束时间"
        ></el-table-column>
        <el-table-column
          width="200"
          show-overflow-tooltip
          prop="createTime"
          label="创建时间"
        ></el-table-column>
        <el-table-column
          width="200"
          show-overflow-tooltip
          prop="updateTime"
          label="更新时间"
        ></el-table-column>
        <el-table-column label="操作" fixed="right" width="100">
          <template #default="{ row }">
            <el-button size="small" type="primary" @click="viewDetails(row)"
              >查看详情</el-button
            >
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

  <Teleport to="body">
    <el-dialog
      v-model="detailsDialogVisible"
      width="90%"
      @close="closeDetailsDialog"
    >
      <el-descriptions title="演练信息" border>
        <el-descriptions-item label="演练 ID">{{
          selectedRow.id
        }}</el-descriptions-item>
        <el-descriptions-item label="演练名称">{{
          selectedRow.exerciseName
        }}</el-descriptions-item>
        <el-descriptions-item label="网络">{{
          selectedRow.network
        }}</el-descriptions-item>
        <el-descriptions-item label="状态">{{
          ExerciseStatus[Number.parseInt(selectedRow.status)]
        }}</el-descriptions-item>

        <el-descriptions-item label="靶机镜像 ID">{{
          selectedRow.targetImageId
        }}</el-descriptions-item>

        <el-descriptions-item label="攻击镜像 ID">{{
          selectedRow.attackImageId
        }}</el-descriptions-item>
        <el-descriptions-item label="攻击次数">{{
          selectedRow.attackTimes
        }}</el-descriptions-item>
        <el-descriptions-item label="攻击开始时间">{{
          selectedRow.attackStartTime
        }}</el-descriptions-item>
        <el-descriptions-item label="攻击结束时间">{{
          selectedRow.attackEndTime
        }}</el-descriptions-item>

        <el-descriptions-item label="防御镜像">{{
          selectedRow.defendImageId
        }}</el-descriptions-item>
        <el-descriptions-item label="防御次数">{{
          selectedRow.defendTimes
        }}</el-descriptions-item>
        <el-descriptions-item label="防御开始时间">{{
          selectedRow.defendStartTime
        }}</el-descriptions-item>
        <el-descriptions-item label="防御结束时间">{{
          selectedRow.defendEndTime
        }}</el-descriptions-item>

        <el-descriptions-item label="开始时间">{{
          selectedRow.startTime
        }}</el-descriptions-item>
        <el-descriptions-item label="结束时间">{{
          selectedRow.endTime
        }}</el-descriptions-item>
        <el-descriptions-item label="创建时间">{{
          selectedRow.createTime
        }}</el-descriptions-item>
        <el-descriptions-item label="更新时间">{{
          selectedRow.updateTime
        }}</el-descriptions-item>
      </el-descriptions>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="detailsDialogVisible = false">关闭</el-button>
        </span>
      </template>
    </el-dialog>
  </Teleport>
</template>

<script lang="ts" setup>
import { ref, onMounted, watch } from "vue";
import { storeToRefs } from "pinia";
import useExercisesStore from "~/stores/exercises";
import type { Exercise, QueryExercise } from "~/types";
import { ExerciseStatus } from "~/types";

const exercisesStore = useExercisesStore();
const { exercises } = storeToRefs(exercisesStore);
const { selectExercisesByPage } = exercisesStore;

const pageSizes = ref([10, 20, 30, 40]);
const pageNum = ref(1);
const pageSize = ref(10);
const total = ref<number | undefined>(undefined);

const queryExerciseForm = ref<QueryExercise>({} as QueryExercise);

const fetchExercises = async () => {
  const response = await selectExercisesByPage(
    queryExerciseForm.value.exerciseName,
    String(pageNum.value),
    String(pageSize.value),
    queryExerciseForm.value.status
  );
  total.value = response?.data.data.total;
};

onMounted(() => {
  fetchExercises();
});

watch([pageNum, pageSize], () => {
  fetchExercises();
});

const query = () => {
  console.log(queryExerciseForm.value);
  fetchExercises();
};

const handleSizeChange = (val: number) => {
  pageSize.value = val;
};

const handleCurrentChange = (val: number) => {
  pageNum.value = val;
};

const detailsDialogVisible = ref(false);
const selectedRow = ref<Exercise>({} as Exercise);
const closeDetailsDialog = () => {
  detailsDialogVisible.value = false;
  selectedRow.value = {} as Exercise;
};
const viewDetails = (row: Exercise) => {
  selectedRow.value = row;
  detailsDialogVisible.value = true;
};

const newExerciseCreated = ref(false);
watch(newExerciseCreated, () => {
  fetchExercises();
  newExerciseCreated.value = false;
});
</script>
