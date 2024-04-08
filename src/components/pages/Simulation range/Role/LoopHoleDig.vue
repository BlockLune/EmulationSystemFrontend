<template>
  <div style="display: flex; flex-direction: column; width: 100%">
    <div>
      <el-form ref="queryFormRef" :model="queryForm" label-position="left" style="display: flex; flex-direction: row">
        <el-form-item label="插件名称" prop="qemuPluginName">
          <el-input style="margin-right: 50px" v-model="queryForm.qemuPluginName" placeholder="单行输入" />
        </el-form-item>
      </el-form>
    </div>
    <div>
      <!-- 新增 -->
      <el-button type="primary" @click="addDialogVisible = true">上传插件</el-button>
      <el-button type="primary" @click="query" style="display: flex; float: right">查询</el-button>
      <el-dialog v-model="addDialogVisible" title="上传插件" width="30%">
        <!-- TODO: 当前的行为是点击空白处关闭新建框会保留之前的填写记录, 是否要清除? -->
        <el-form ref="newFormRef" :model="newForm" label-position="left">
          <el-form-item label="插件名称" prop="qemuPluginName">
            <el-input v-model="newForm.qemuPluginName" placeholder="单行输入" />
          </el-form-item>
          <el-form-item label="版本号" prop="version">
            <el-input v-model="newForm.version" placeholder="单行输入" />
          </el-form-item>
          <el-form-item label="资源文件" prop="resourceType">
            <el-upload
                :on-change="handleChange"
                class="upload-demo"
                action=""
                :auto-upload="false">
              <template #trigger>
                <el-button type="primary">选择文件</el-button>
              </template>
            </el-upload>
          </el-form-item>
        </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button
            type="primary"
            @click="closeAddDialogSubmitForm()"
        >
          上传
        </el-button>
        <el-button @click="addDialogVisible = false"
        >取消</el-button
        >
      </span>
        </template>
      </el-dialog>

      <!-- 主体 -->
      <div style="margin-top: 20px">
        <el-card v-for="(item, index) in datas" :key="index" style="width: 300px">
          <template #header>
            {{ item.qemuPluginName }}
          </template>
          <div>
            <p>ID: {{ item.id }}</p>
            <p>插件名称: {{ item.qemuPluginName }}</p>
            <p>镜像ID: {{ item.imageId }}</p>
            <p>资源文件: {{ item.repository }}</p>
            <p>创建时间: {{ item.createTime }}</p>
            <p>更新时间: {{ item.updateTime }}</p>
            <p>版本号: {{ item.version }}</p>
          </div>
          <template #footer>
            <el-button @click="startDig">开启挖掘</el-button>
            <el-button @click="deletePlugin">删除</el-button>
          </template>
        </el-card>
      </div>
    </div>
  </div>


</template>

<script lang="ts" setup>
import {ref, reactive, onMounted, computed} from "vue";
import {ElMessage, ElMessageBox, UploadInstance} from "element-plus";
import axios from "axios";
import type { UploadProps, UploadUserFile } from 'element-plus'

onMounted(() => {
  listData()
})

interface Data {
  createTime: string;
  id: string;
  imageId: string;
  qemuPluginName: string;
  repository: string;
  updateTime: string;
  version: string;
}

const datas = ref<Data[]>([]);

const listData = () => {
  datas.value = []
  axios({
    headers: {
      Authorization: localStorage.getItem('Authorization')
    },
    method: 'get',
    url: '/qemu/getall/1/1000',
  }).then((response) => {
    for (const data of response.data.data.list) {
      datas.value.push(data);
    }
  });
};

const query = () => {
  if (queryForm.qemuPluginName === "") {
    window.setTimeout(() => {
      listData()
    }, 250)
  } else {
    datas.value = []
    axios({
      headers: {
        Authorization: localStorage.getItem('Authorization')
      },
      method: 'get',
      url: '/container/selectByPage',
    }).then((response) => {
      for (const data of response.data.data.list) {
        datas.value.push(data);
      }
      ElMessage(response.data.message)
    });
  }
}

const formData = new FormData();

const uploadedFile = ref(null)

const handleChange = (file) => {
  uploadedFile.value = file.raw
}

const add = (formData) => {
  axios.post('/image/uploadImage', formData, {
    headers: {
      'Content-Type': 'multipart/form-data',
      'Authorization': localStorage.getItem('Authorization'),
    }
  }).then((response) => {
    ElMessage(response.data.message)
  });
}

let addDialogVisible = ref(false)

const queryForm = reactive({
  qemuPluginName: '',
})

const newForm = reactive({
  qemuPluginName: '',
  version: ''
})

const closeAddDialogSubmitForm = () => {
  formData.append('file', uploadedFile.value)
  formData.append('qemuPluginName', newForm.qemuPluginName)
  formData.append('version', newForm.version)
  add(formData)
  window.setTimeout(() => {
    listData()
  }, 250)
  newForm.qemuPluginName = ''
  newForm.version = ''
  addDialogVisible.value = false
}

const startDig = () => {

}
const deletePlugin = () => {

}
</script>
