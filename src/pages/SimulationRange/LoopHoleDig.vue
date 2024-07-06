<template>
  <div style="display: flex; flex-direction: column; width: 100%">
    <div>
      <el-select
        v-model="value"
        placeholder="请选择查询方式"
        clearable
        style="width: 160px; margin-bottom: 10px; margin-right: 10px"
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
        style="width: 180px; margin-bottom: 10px"
      />
    </div>
    <div>
      <!-- 新增 -->
      <el-button type="primary" @click="addDialogVisible = true"
        >上传插件</el-button
      >
      <el-button
        type="primary"
        @click="queryPluginByOption"
        style="display: flex; float: right"
        >查询</el-button
      >
      <!--      <el-button type="primary" @click="query" style="display: flex; float: right">查询</el-button>-->
      <el-dialog v-model="addDialogVisible" title="上传插件" width="30%">
        <!-- TODO: 当前的行为是点击空白处关闭新建框会保留之前的填写记录, 是否要清除? -->
        <el-form
          ref="newFormRef"
          :model="newForm"
          label-position="left"
          label-width="auto"
        >
          <el-form-item label="插件名称" prop="qemuPluginName">
            <el-input v-model="newForm.qemuPluginName" placeholder="单行输入" />
          </el-form-item>
          <el-form-item label="版本号" prop="version">
            <el-input v-model="newForm.version" placeholder="单行输入" />
          </el-form-item>
          <el-form-item label="资源文件" prop="resourceType">
            <el-upload
              ref="uploadrefs"
              :on-change="handleChange"
              class="upload-demo"
              action=""
              :auto-upload="false"
            >
              <template #trigger>
                <el-button type="primary">选择文件</el-button>
              </template>
            </el-upload>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button type="primary" @click="closeAddDialogSubmitForm()">
              上传
            </el-button>
            <el-button @click="addDialogVisible = false">取消</el-button>
          </span>
        </template>
      </el-dialog>

      <!-- 主体 -->
      <div
        style="
          margin-top: 10px;
          display: flex;
          flex-direction: row;
          flex-wrap: wrap;
        "
      >
        <el-card
          class="card"
          v-for="(item, index) in datas"
          :key="index"
          style="width: 365px; margin: 10px"
        >
          <template #header>
            {{ item.qemuPluginName }}
          </template>
          <div>
            <p>ID: {{ item.id }}</p>
            <p>插件名称: {{ item.qemuPluginName }}</p>
            <el-tooltip
              class="box-item"
              effect="dark"
              :content="item.imageId"
              placement="top-start"
            >
              <p>镜像ID: {{ item.imageId }}</p>
            </el-tooltip>
            <el-tooltip
              class="box-item"
              effect="dark"
              :content="item.repository"
              placement="top-start"
            >
              <p>资源文件: {{ item.repository }}</p>
            </el-tooltip>
            <p>创建时间: {{ item.createTime }}</p>
            <p>更新时间: {{ item.updateTime }}</p>
            <p>版本号: {{ item.version }}</p>
          </div>
          <template #footer>
            <el-button @click="startDig(item)" style="width: 155px"
              >开启挖掘</el-button
            >
            <el-button @click="deletePlugin(item)" style="width: 155px"
              >删除</el-button
            >
            <!--            <el-button @click="stopDig(item)" style="width: 90px">停止挖掘</el-button>-->
          </template>
        </el-card>
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
  createTime: string;
  id: number;
  imageId: string;
  qemuPluginName: string;
  repository: string;
  updateTime: string;
  version: string;
}

const value = ref("");

const options = [
  {
    value: "0",
    label: "根据ID查询插件",
  },
  {
    value: "1",
    label: "根据名称查询插件",
  },
];

const relateData = ref("");

const queryPluginByOption = () => {
  let str = "";
  if (!value.value) {
    ElMessage("请选择查询方式");
  } else {
    if (value.value === "0") {
      str = "/qemu/getbyid?id=" + relateData.value;
    }
    if (value.value === "1") {
      str = "/qemu/getbyname/1/1000?name=" + relateData.value;
    }
    query(str);
    relateData.value = "";
  }
};

const datas = ref<Data[]>([]);

const listData = () => {
  datas.value = [];
  axiosInstance({
    method: "get",
    url: "/qemu/getall/1/1000",
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
    axiosInstance({
      method: "get",
      url: str,
    }).then((response) => {
      if (
        !response.data.data ||
        (response.data.data.list && response.data.data.list.length === 0)
      ) {
        ElMessage("数据不存在！已显示所有数据！");
        window.setTimeout(() => {
          listData();
        }, 250);
      } else {
        if (value.value === "0") {
          datas.value.push(response.data.data);
        }
        if (value.value === "1") {
          for (const data of response.data.data.list) {
            datas.value.push(data);
          }
        }
        ElMessage(response.data.message);
      }
    });
  }
};

const deleteCard = (id: string) => {
  axiosInstance({
    method: "post",
    url: "/qemu/delete",
    data: {
      id: id,
    },
  }).then((response) => {
    ElMessage(response.data.message);
  });
};

const uploadedFile = ref(null);
const uploadrefs = ref();

const handleChange = (file) => {
  uploadedFile.value = file.raw;
};

const add = (formData) => {
  axiosInstance
    .post("/qemu/upload", formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    })
    .then((response) => {
      ElMessage(response.data.message);
    });
};

const addDialogVisible = ref(false);

const newForm = reactive({
  qemuPluginName: "",
  version: "",
});

const closeAddDialogSubmitForm = () => {
  const formData = new FormData();
  formData.append("file", uploadedFile.value);
  formData.append("qemuPluginName", newForm.qemuPluginName);
  formData.append("version", newForm.version);
  add(formData);
  window.setTimeout(() => {
    listData();
  }, 450);
  newForm.qemuPluginName = "";
  newForm.version = "";
  addDialogVisible.value = false;
  uploadrefs.value.clearFiles();
};

const startDig = (item) => {
  axiosInstance({
    method: "post",
    url: "/qemu/createqemutask",
    data: { pluginId: item.id },
  }).then((response) => {
    ElMessage(response.data.message);
  });
};
// const stopDig = (item) => {
//   instance({
//     method: 'post',
//     url: '/qemu/stoptask',
//     data: {containerId: item.imageId.slice(7)}
//   }).then((response) => {
//     ElMessage(response.data.message)
//   });
// }
const deletePlugin = (item) => {
  deleteCard(item.id);
  window.setTimeout(() => {
    listData();
  }, 250);
};
</script>
<style>
.card {
  word-wrap: break-word;
}
</style>
