<template>
  <div style="display: flex; flex-direction: column; width: 100%">
    <div style="width: 100%">
      <el-form
        ref="queryFormRef"
        :model="queryForm"
        label-position="left"
        style="display: flex; flex-direction: row"
      >
        <el-form-item label="演练名称" prop="imageName">
          <el-input
            style="margin-right: 50px"
            v-model="queryForm.exerciseName"
            placeholder="单行输入"
          />
        </el-form-item>
        <el-form-item label="演练状态" prop="imageType">
          <el-select-v2
            clearable
            v-model="queryForm.status"
            :options="options"
            placeholder="请选择"
            style="width: 130px"
          />
        </el-form-item>
      </el-form>
      <el-button style="float: right" type="primary" @click="query"
        >查询</el-button
      >
      <!--      @click="queryPluginByOption"-->

      <el-button size="default" type="primary" @click="addDialogVisible = true"
        >新增</el-button
      >
    </div>
    <!-- 新增 -->
    <el-dialog v-model="addDialogVisible" title="新增演练" width="30%">
      <!-- TODO: 当前的行为是点击空白处关闭新建框会保留之前的填写记录, 是否要清除? -->
      <el-form
        ref="newFormRef"
        :model="newForm"
        label-position="left"
        label-width="auto"
      >
        <el-form-item label="演练名称" prop="exerciseName">
          <el-input v-model="newForm.exerciseName" placeholder="单行输入" />
        </el-form-item>
        <el-form-item label="演练靶机" prop="imageName">
          <el-select-v2
            v-model="newForm.imageName1"
            :options="imageOptions1"
            @change="getImageId1"
            placeholder="请选择"
            clearable
            style="width: 300px"
          />
        </el-form-item>
        <!--        <el-form-item label="靶机镜像ID" prop="targetImageId">-->
        <!--          <el-input disabled v-model="newForm.targetImageId" placeholder="单行输入" />-->
        <!--        </el-form-item>-->
        <el-form-item label="开始时间" prop="startTime">
          <el-date-picker
            v-model="newForm.startTime"
            type="datetime"
            value-format="YYYY-MM-DD HH:mm:ss"
            placeholder="选择日期和时间"
            style="width: 100%"
          />
          <!--        <el-input v-model="newForm.startTime" placeholder="单行输入" />-->
        </el-form-item>
        <el-form-item label="停止时间" prop="endTime">
          <el-date-picker
            v-model="newForm.endTime"
            type="datetime"
            placeholder="选择日期和时间"
            value-format="YYYY-MM-DD HH:mm:ss"
            style="width: 100%"
          />
          <!--        <el-input v-model="newForm.endTime" placeholder="单行输入" />-->
        </el-form-item>
        <el-form-item label="攻击镜像" prop="imageName">
          <el-select-v2
            v-model="newForm.imageName2"
            :options="imageOptions2"
            @change="getImageId2"
            placeholder="请选择"
            clearable
            style="width: 300px"
          />
        </el-form-item>
        <!--        <el-form-item label="攻击镜像ID" prop="attackImageId">-->
        <!--          <el-input v-model="newForm.attackImageId" placeholder="单行输入" disabled/>-->
        <!--        </el-form-item>-->
        <el-form-item label="攻击开始时间" prop="attackStartTime">
          <el-date-picker
            v-model="newForm.attackStartTime"
            type="datetime"
            placeholder="选择日期和时间"
            value-format="YYYY-MM-DD HH:mm:ss"
            style="width: 100%"
          />
          <!--        <el-input v-model="newForm.attackStartTime" placeholder="单行输入" />-->
        </el-form-item>
        <el-form-item label="攻击终止时间" prop="attackEndTime">
          <el-date-picker
            v-model="newForm.attackEndTime"
            type="datetime"
            placeholder="选择日期和时间"
            value-format="YYYY-MM-DD HH:mm:ss"
            style="width: 100%"
          />
          <!--        <el-input v-model="newForm.attackEndTime" placeholder="单行输入" />-->
        </el-form-item>
        <el-form-item label="防御镜像" prop="imageName">
          <el-select-v2
            v-model="newForm.imageName3"
            :options="imageOptions3"
            @change="getImageId3"
            placeholder="请选择"
            clearable
            style="width: 300px"
          />
        </el-form-item>
        <!--        <el-form-item label="防御镜像ID" prop="defendImageId">-->
        <!--          <el-input v-model="newForm.defendImageId" placeholder="单行输入" disabled/>-->
        <!--        </el-form-item>-->
        <el-form-item label="防御启动时间" prop="defendStartTime">
          <el-date-picker
            v-model="newForm.defendStartTime"
            type="datetime"
            placeholder="选择日期和时间"
            value-format="YYYY-MM-DD HH:mm:ss"
            style="width: 100%"
          />
          <!--        <el-input v-model="newForm.defendStartTime" placeholder="单行输入" />-->
        </el-form-item>
        <el-form-item label="防御终止时间" prop="defendEndTime">
          <el-date-picker
            v-model="newForm.defendEndTime"
            type="datetime"
            placeholder="选择日期和时间"
            value-format="YYYY-MM-DD HH:mm:ss"
            style="width: 100%"
          />
          <!--        <el-input v-model="newForm.defendEndTime" placeholder="单行输入" />-->
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" @click="closeAddDialogSubmitForm()">
            确定
          </el-button>
          <el-button @click="addDialogVisible = false">取消</el-button>
        </span>
      </template>
    </el-dialog>
    <el-dialog
      v-model="detailsDialogVisible"
      width="70%"
      @close="closeDetailsDialog"
    >
      <el-descriptions title="演练信息" :column="2" border>
        <el-descriptions-item label="演练名称">{{
          selectedRow.exerciseName
        }}</el-descriptions-item>
        <el-descriptions-item label="靶机镜像">{{
          selectedRow.targetImageName
        }}</el-descriptions-item>
        <el-descriptions-item label="演练状态">{{
          selectedRow.status
        }}</el-descriptions-item>
        <el-descriptions-item label="攻击镜像">{{
          selectedRow.attackImageName
        }}</el-descriptions-item>
        <el-descriptions-item label="攻击次数">{{
          selectedRow.attackTimes
        }}</el-descriptions-item>
        <el-descriptions-item label="防御镜像">{{
          selectedRow.defendImageName
        }}</el-descriptions-item>
        <el-descriptions-item label="防御次数">{{
          selectedRow.defendTimes
        }}</el-descriptions-item>
        <el-descriptions-item label="开始时间">{{
          selectedRow.startTime
        }}</el-descriptions-item>
        <el-descriptions-item label="结束时间">{{
          selectedRow.endTime
        }}</el-descriptions-item>
        <el-descriptions-item label="攻击开始时间">{{
          selectedRow.attackStartTime
        }}</el-descriptions-item>
        <el-descriptions-item label="攻击结束时间">{{
          selectedRow.attackEndTime
        }}</el-descriptions-item>
        <el-descriptions-item label="防御开始时间">{{
          selectedRow.defendStartTime
        }}</el-descriptions-item>
        <el-descriptions-item label="防御结束时间">{{
          selectedRow.defendEndTime
        }}</el-descriptions-item>
        <!-- 根据需要添加更多的描述项 -->
      </el-descriptions>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="detailsDialogVisible = false">关闭</el-button>
        </span>
      </template>
    </el-dialog>
    <!-- 表格主体 -->
    <div style="width: 100%">
      <el-table
        :data="
          datas.slice((currentPage - 1) * pageSize, currentPage * pageSize)
        "
        stripe
        style="width: 100%"
      >
        <!--        <el-table-column show-overflow-tooltip prop="id" label="ID"></el-table-column>-->
        <el-table-column
          show-overflow-tooltip
          prop="exerciseName"
          label="演练名称"
        ></el-table-column>
        <!--        <el-table-column show-overflow-tooltip prop="targetImageId" label="靶机镜像"></el-table-column>-->
        <el-table-column
          show-overflow-tooltip
          prop="targetImageName"
          label="靶机镜像"
        ></el-table-column>
        <!--        <el-table-column show-overflow-tooltip prop="createTime" label="创建时间"></el-table-column>-->
        <!--        <el-table-column show-overflow-tooltip prop="updateTime" label="更新时间"></el-table-column>-->
        <el-table-column
          show-overflow-tooltip
          prop="status"
          label="演练状态"
        ></el-table-column>
        <el-table-column
          show-overflow-tooltip
          prop="attackImageName"
          label="攻击镜像"
        ></el-table-column>
        <!--        <el-table-column show-overflow-tooltip prop="attackImageId" label="攻击镜像"></el-table-column>-->
        <el-table-column
          show-overflow-tooltip
          prop="attackTimes"
          label="攻击次数"
        ></el-table-column>
        <el-table-column
          show-overflow-tooltip
          prop="defendImageName"
          label="防御镜像"
        ></el-table-column>
        <!--        <el-table-column show-overflow-tooltip prop="defendImageId" label="防御镜像"></el-table-column>-->
        <el-table-column
          show-overflow-tooltip
          prop="defendTimes"
          label="防御次数"
        ></el-table-column>

        <el-table-column
          show-overflow-tooltip
          prop="startTime"
          label="开始时间"
        ></el-table-column>
        <el-table-column
          show-overflow-tooltip
          prop="endTime"
          label="结束时间"
        ></el-table-column>

        <el-table-column
          show-overflow-tooltip
          prop="attackStartTime"
          label="攻击开始时间"
        ></el-table-column>
        <el-table-column
          show-overflow-tooltip
          prop="attackEndTime"
          label="攻击结束时间"
        ></el-table-column>
        <el-table-column
          show-overflow-tooltip
          prop="defendStartTime"
          label="防御开始时间"
        ></el-table-column>
        <el-table-column
          show-overflow-tooltip
          prop="defendEndTime"
          label="防御结束时间"
        ></el-table-column>

        <el-table-column label="操作" width="100">
          <template #default="{ row }">
            <el-button size="small" type="primary" @click="viewDetails(row)"
              >查看详情</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        v-model:current-page="currentPage"
        v-model:page-size="pageSize"
        :page-sizes="[10, 20, 30, 40]"
        :small="small"
        :disabled="disabled"
        :background="background"
        layout="total, sizes, prev, pager, next, jumper"
        :total="datas.length"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref, reactive } from "vue";
import { ElMessage, ElSelect, FormInstance } from "element-plus";
import { onMounted } from "vue";
import axios from "axios";
import axiosInstance from "~/utils/axiosInstance";

onMounted(() => {
  listData();
  getImage();
});

interface Data {
  attackEndTime: string;
  attackImageId: string;
  imageName: string;
  attackStartTime: string;
  attackTimes: string;
  createTime: string;
  defendEndTime: string;
  defendImageId: string;
  defendStartTime: string;
  defendTimes: string;
  endTime: string;
  exerciseName: string;
  id: string;
  startTime: string;
  status: string;
  targetImageId: string;
  updateTime: string;
}
interface Data2 {
  createTime: string;
  imageName: string;
  imageType: string;
  id: string;
  updateTime: string;
  path: string;
  version: string;
}

const options = [
  { value: "0", label: "进行中" },
  { value: "1", label: "已停止" },
  { value: "2", label: "状态2" },
  { value: "3", label: "状态3" },
];
const imageOptions1 = [];
const imageOptions2 = [];
const imageOptions3 = [];

const datas = ref<Data[]>([]);
const datas2 = ref<Data2[]>([]);
const attackAndDefendData = ref<Data2[]>([]);

const getImage = () => {
  datas2.value = [];
  imageOptions1.splice(0, imageOptions1.length);
  imageOptions2.splice(0, imageOptions2.length);
  imageOptions3.splice(0, imageOptions3.length);
  axiosInstance({
    method: "post",
    url: "/exercise/selectTarget",
  }).then((response) => {
    for (const data of response.data.data) {
      datas2.value.push(data);
      let obj = {
        value: data.imageName,
        label: data.imageName,
        id: data.id,
      };
      imageOptions1.push(obj);
    }
  });
  axiosInstance({
    method: "post",
    url: "/exercise/selectDefenseImage",
  }).then((response) => {
    for (const data of response.data.data) {
      datas2.value.push(data);
      let obj = {
        value: data.imageName,
        label: data.imageName,
        id: data.id,
      };
      imageOptions3.push(obj);
    }
  });
  axiosInstance({
    method: "post",
    url: "/exercise/selectAttackImage",
  }).then((response) => {
    for (const data of response.data.data) {
      datas2.value.push(data);
      let obj = {
        value: data.imageName,
        label: data.imageName,
        id: data.id,
      };
      imageOptions2.push(obj);
    }
  });
};
const getImageId1 = () => {
  for (const data of imageOptions1) {
    if (data.value === newForm.imageName1) {
      newForm.targetImageId = data.id;
    }
  }
};
const getImageId2 = () => {
  for (const data of imageOptions2) {
    if (data.value === newForm.imageName2) {
      newForm.attackImageId = data.id;
    }
  }
};
const getImageId3 = () => {
  for (const data of imageOptions3) {
    if (data.value === newForm.imageName3) {
      newForm.defendImageId = data.id;
    }
  }
};
const listData = () => {
  datas.value = [];
  attackAndDefendData.value = [];
  axiosInstance({
    method: "post",
    url: "/exercise/selectTarget",
  }).then((response) => {
    for (const data of response.data.data) {
      attackAndDefendData.value.push(data);
    }
  });
  axiosInstance({
    method: "post",
    url: "/exercise/selectDefenseImage",
  }).then((response) => {
    for (const data of response.data.data) {
      attackAndDefendData.value.push(data);
    }
  });
  axiosInstance({
    method: "post",
    url: "/exercise/selectAttackImage",
  }).then((response) => {
    for (const data of response.data.data) {
      attackAndDefendData.value.push(data);
    }
  });
  axiosInstance({
    method: "post",
    url: "/exercise/selectByPage",
    data: {
      exerciseName: "",
      status: "",
      pageNum: 1,
      pageSize: 1000,
    },
  }).then((response) => {
    for (const data of response.data.data.list) {
      for (let i = 0; i < options.length; i++) {
        if (data.status === options[i].value) data.status = options[i].label;
      }

      for (const item of attackAndDefendData.value) {
        if (data.attackImageId === item.id) {
          data["attackImageName"] = item.imageName;
        }
        if (data.defendImageId === item.id) {
          data["defendImageName"] = item.imageName;
        }
        if (data.targetImageId === item.id) {
          data["targetImageName"] = item.imageName;
        }
      }
      datas.value.push(data);
    }
  });
};
const detailsDialogVisible = ref(false);
const selectedRow = ref({} as Data);
const closeDetailsDialog = () => {
  detailsDialogVisible.value = false;
  selectedRow.value = {} as Data;
};
const viewDetails = (row: Data) => {
  selectedRow.value = row; // 设置所选行数据
  detailsDialogVisible.value = true; // 显示对话框
};

const query = () => {
  if (queryForm.exerciseName === "" && !queryForm.status) {
    window.setTimeout(() => {
      listData();
    }, 250);
  } else {
    datas.value = [];
    axios({
      method: "post",
      url: "/exercise/selectByPage",
      data: {
        exerciseName: queryForm.exerciseName,
        status: queryForm.status,
        pageNum: 1,
        pageSize: 1000,
      },
    }).then((response) => {
      for (const data of response.data.data.list) {
        datas.value.push(data);
      }
      ElMessage(response.data.message);
    });
  }
};

const add = (form) => {
  axiosInstance({
    method: "post",
    url: "/exercise/createExercise",
    data: {
      exerciseName: form.exerciseName,
      targetImageId: form.targetImageId,
      startTime: form.startTime,
      endTime: form.endTime,
      attackImageId: form.attackImageId,
      attackStartTime: form.attackStartTime,
      attackEndTime: form.attackEndTime,
      defendImageId: form.defendImageId,
      defendStartTime: form.defendStartTime,
      defendEndTime: form.defendEndTime,
    },
  }).then((response) => {
    ElMessage(response.data.message);
  });
};

let addDialogVisible = ref(false);

const queryForm = reactive({
  exerciseName: "",
  status: "",
});

const newForm = reactive({
  exerciseName: "",
  targetImageId: "",
  startTime: null,
  endTime: null,
  attackImageId: "",
  attackStartTime: null,
  attackEndTime: null,
  defendImageId: "",
  defendStartTime: null,
  defendEndTime: null,
  imageName1: "",
  imageName2: "",
  imageName3: "",
});

const closeAddDialogSubmitForm = () => {
  add(newForm);
  window.setTimeout(() => {
    listData();
  }, 450);
  newForm.imageName1 = "";
  newForm.imageName2 = "";
  newForm.imageName3 = "";
  newForm.exerciseName = "";
  newForm.targetImageId = "";
  newForm.startTime = null;
  newForm.endTime = null;
  newForm.attackImageId = "";
  newForm.attackStartTime = null;
  newForm.attackEndTime = null;
  newForm.defendImageId = "";
  newForm.defendStartTime = null;
  newForm.defendEndTime = null;
  addDialogVisible.value = false;
};

const deleteRow = (row) => {};

const small = ref(false);
const background = ref(true);
const disabled = ref(false);
const pageSize = ref(10);
const currentPage = ref(1);

const handleSizeChange = (val: number) => {
  pageSize.value = val;
};

const handleCurrentChange = (val: number) => {
  currentPage.value = val;
};
</script>
