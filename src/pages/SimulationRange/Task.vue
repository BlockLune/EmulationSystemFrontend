<template>
  <div style="display: flex; flex-direction: column; width: 100%">
    <div>
      <el-select
        v-model="value"
        placeholder="请选择查询方式"
        clearable
        style="width: 160px; margin-bottom: 20px"
      >
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        />
      </el-select>
      <el-input
        v-model="relateData"
        placeholder="请输入内容"
        style="width: 180px; margin-bottom: 20px"
      />
      <el-button
        @click="queryPluginByOption"
        style="margin-bottom: 20px; margin-right: 40px"
        >查询</el-button
      >
    </div>
    <div>
      <!-- 新增 -->
      <el-button type="primary" @click="addDialogVisible = true"
        >新增任务</el-button
      >
      <!--      <el-button type="primary" @click="query" style="display: flex; float: right">查询</el-button>-->
      <Teleport to="body">
        <el-dialog v-model="addDialogVisible" title="新增任务" width="30%">
          <!-- TODO: 当前的行为是点击空白处关闭新建框会保留之前的填写记录, 是否要清除? -->
          <el-form
            ref="newFormRef"
            :model="newForm"
            label-position="left"
            label-width="auto"
          >
            <el-form-item label="插件名称" prop="qemuPluginName">
              <el-select-v2
                v-model="newForm.qemuPluginName"
                :options="pluginName"
                placeholder="请选择"
                clearable
                style="width: 100%"
                @change="getIdByName"
              />
            </el-form-item>
            <!--          <el-form-item label="插件ID" prop="pluginId">-->
            <!--            <el-input v-model="newForm.pluginId" placeholder="单行输入" />-->
            <!--          </el-form-item>-->
          </el-form>
          <template #footer>
            <span class="dialog-footer">
              <el-button type="primary" @click="closeAddDialogSubmitForm()">
                添加
              </el-button>
              <el-button @click="addDialogVisible = false">取消</el-button>
            </span>
          </template>
        </el-dialog>
      </Teleport>

      <!-- 表格主体 -->
      <div style="width: 100%">
        <el-table
          :data="
            datas.slice((currentPage - 1) * pageSize, currentPage * pageSize)
          "
          stripe
          style="width: 100%"
        >
          <el-table-column
            show-overflow-tooltip
            prop="containerId"
            label="容器ID"
            width="100"
          ></el-table-column>
          <!--          <el-table-column prop="id" label="ID"></el-table-column>-->
          <el-table-column prop="id" label="ID"></el-table-column>
          <!--          <el-table-column prop="pluginId" label="插件ID"></el-table-column>-->
          <el-table-column prop="pluginId" label="插件ID"></el-table-column>
          <el-table-column prop="taskStatus" label="容器状态"></el-table-column>
          <el-table-column prop="createTime" label="创建时间"></el-table-column>
          <el-table-column prop="updateTime" label="更新时间"></el-table-column>
          <el-table-column label="操作" width="250">
            <template #default="{ row }">
              <el-popconfirm
                title="确认启动？"
                confirm-button-text="确认"
                cancel-button-text="取消"
                @confirm="startRow(row)"
              >
                <template #reference>
                  <el-button link type="success">启动</el-button>
                </template>
              </el-popconfirm>
              <el-popconfirm
                title="确认停止？"
                confirm-button-text="确认"
                cancel-button-text="取消"
                @confirm="stopRow(row)"
              >
                <template #reference>
                  <el-button link type="danger">停止</el-button>
                </template>
              </el-popconfirm>
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
              <el-popconfirm
                title="确认销毁？"
                confirm-button-text="确认"
                cancel-button-text="取消"
                @confirm="destroyRow(row)"
              >
                <template #reference>
                  <el-button link type="danger">销毁</el-button>
                </template>
              </el-popconfirm>
              <el-popconfirm
                title="确认刷新？"
                confirm-button-text="确认"
                cancel-button-text="取消"
                @confirm="flushRow(row)"
              >
                <template #reference>
                  <el-button link type="success">刷新</el-button>
                </template>
              </el-popconfirm>
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
  </div>
</template>

<script lang="ts" setup>
import { ref, reactive, onMounted, computed } from "vue";
import { ElMessage, ElMessageBox, UploadInstance } from "element-plus";
import axios from "axios";
import type { UploadProps, UploadUserFile } from "element-plus";
import JSONBIG from "json-bigint";
import axiosInstance from "~/utils/axiosInstance";

onMounted(() => {
  listData();
  getPluginName();
});

axiosInstance.defaults.transformResponse = [
  function (data) {
    const json = JSONBIG({
      storeAsString: true,
    });
    const res = json.parse(data);
    return res;
  },
];

interface Data {
  attackTime: string;
  containerId: string;
  containerName: string;
  containerType: string;
  createTime: string;
  defendTime: string;
  endTime: string;
  id: string;
  imageId: string;
  imageName: string;
  lastTime: string;
  rangeName: null;
  startTime: string;
  status: string;
  updateTime: string;
}

const value = ref("");

const options = [
  {
    value: "0",
    label: "根据插件ID查询任务",
  },
  // {
  //   value: '1',
  //   label: '根据容器ID查询任务状态',
  // }
];

const relateData = ref("");

const queryPluginByOption = () => {
  let str = "";
  if (!value.value) {
    ElMessage("请选择查询方式");
  } else {
    if (value.value === "0") {
      str = "/qemu/gettasksbypluginid/1/100?pluginId=" + relateData.value;
    }
    if (value.value === "1") {
      str = "/qemu/gettaskstatus";
    }
    query(str);
    relateData.value = "";
  }
};

const datas = ref<Data[]>([]);
const pluginName = [];
const getIdByName = () => {
  for (const data of pluginName) {
    if (newForm.qemuPluginName === data.value) newForm.pluginId = data.id;
  }
};
const getPluginName = () => {
  pluginName.splice(0, pluginName.length);
  axiosInstance({
    method: "get",
    url: "/qemu/getall/1/1000",
  }).then((response) => {
    for (const data of response.data.data.list) {
      let obj = {
        label: data.qemuPluginName,
        value: data.qemuPluginName,
        qemuPluginName: data.qemuPluginName,
        id: data.id,
      };
      pluginName.push(obj);
    }
  });
};
const listData = () => {
  datas.value = [];
  axiosInstance({
    method: "get",
    url: "/qemu/getalltasks/1/1000",
  }).then((response) => {
    for (const data of response.data.data.list) {
      datas.value.push(data);
    }
  });
};

const query = (str: string) => {
  if (!relateData.value) {
    window.setTimeout(() => {
      listData();
    }, 250);
    ElMessage("输入为空！");
  } else {
    datas.value = [];
    if (value.value === "0") {
      axiosInstance({
        method: "get",
        url: str,
      }).then((response) => {
        for (const data of response.data.data.list) {
          datas.value.push(data);
        }
        ElMessage(response.data.message);
      });
    }
    if (value.value === "1") {
      axiosInstance({
        method: "post",
        url: str,
        data: { containerId: relateData.value },
      }).then((response) => {
        for (const data of response.data.data.list) {
          datas.value.push(data);
        }
        ElMessage(response.data.message);
      });
    }
  }
};

const add = (pluginId: string) => {
  axiosInstance({
    method: "post",
    url: "/qemu/createqemutask",
    data: { pluginId: pluginId },
  }).then((response) => {
    ElMessage(response.data.message);
  });
};

const startData = (containerId: string) => {
  axiosInstance({
    method: "post",
    url: "/qemu/runtask",
    data: { containerId: containerId },
  }).then((response) => {
    ElMessage(response.data.message);
  });
};
const stopData = (containerId: string) => {
  axiosInstance({
    method: "post",
    url: "/qemu/stoptask",
    data: { containerId: containerId },
  }).then((response) => {
    ElMessage(response.data.message);
  });
};
const flushData = (containerId: string) => {
  axiosInstance({
    method: "post",
    url: "/qemu/gettaskstatus",
    data: { containerId: containerId },
  }).then((response) => {
    ElMessage(response.data.message);
  });
};
const deleteData = (taskId: string) => {
  axiosInstance({
    method: "post",
    url: "/qemu/deletetask",
    data: { taskId: taskId },
  }).then((response) => {
    ElMessage(response.data.message);
  });
};
const destroyData = (containerId: string) => {
  axiosInstance({
    method: "post",
    url: "/qemu/destroytask",
    data: { containerId: containerId },
  }).then((response) => {
    ElMessage(response.data.message);
  });
};

let addDialogVisible = ref(false);

const newForm = reactive({
  pluginId: "",
  qemuPluginName: "",
});

const closeAddDialogSubmitForm = () => {
  add(newForm.pluginId);
  window.setTimeout(() => {
    listData();
  }, 850);
  newForm.pluginId = "";
  addDialogVisible.value = false;
};

const startRow = (row) => {
  startData(row.containerId);
  window.setTimeout(() => {
    listData();
  }, 250);
};
const stopRow = (row) => {
  stopData(row.containerId);
  window.setTimeout(() => {
    listData();
  }, 250);
};
const flushRow = (row) => {
  flushData(row.containerId);
  window.setTimeout(() => {
    listData();
  }, 250);
};
const deleteRow = (row) => {
  deleteData(row.id);
  window.setTimeout(() => {
    listData();
  }, 250);
};
const destroyRow = (row) => {
  destroyData(row.containerId);
  window.setTimeout(() => {
    listData();
  }, 250);
};

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
