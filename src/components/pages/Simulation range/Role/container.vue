<template>
  <div style="display: flex; flex-direction: column; width: 100%">
    <div>
      <el-form ref="queryFormRef" :model="queryForm" label-position="left" style="display: flex; flex-direction: row">
        <el-form-item label="容器名称" prop="containerName">
          <el-input style="margin-right: 50px" v-model="queryForm.containerName" placeholder="单行输入" />
        </el-form-item>
        <el-form-item label="靶场名称" prop="rangeName">
          <el-input style="margin-right: 50px" v-model="queryForm.targetName" placeholder="单行输入" />
        </el-form-item>
        <el-form-item label="容器状态" prop="imageType">
          <el-select-v2
              v-model="queryForm.status"
              :options="options"
              placeholder="请选择"
              style="width: 300px"
          />
        </el-form-item>
      </el-form>

    </div>
    <div>
      <!-- 新增 -->
      <el-button type="primary" @click="addDialogVisible = true">新增靶机容器</el-button>
      <el-button type="primary" @click="query" style="display: flex; float: right">查询</el-button>
      <el-dialog v-model="addDialogVisible" title="新增靶机容器" width="30%">
        <!-- TODO: 当前的行为是点击空白处关闭新建框会保留之前的填写记录, 是否要清除? -->
        <el-form ref="newFormRef" :model="newForm" label-position="left">
          <el-form-item label="镜像ID" prop="imageId">
            <el-input v-model="newForm.imageId" placeholder="单行输入" />
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

      <!-- 表格主体 -->
      <div style="width: 100%">
        <el-table :data="datas.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
                  stripe style="width: 100%">
          <el-table-column prop="containerId" label="容器ID"></el-table-column>
          <el-table-column prop="containerName" label="容器名称"></el-table-column>
          <el-table-column prop="containerType" label="容器类型"></el-table-column>
          <el-table-column prop="id" label="ID"></el-table-column>
          <el-table-column prop="imageId" label="镜像ID"></el-table-column>
          <el-table-column prop="imageName" label="镜像名称"></el-table-column>
          <el-table-column prop="rangeName" label="靶机名称"></el-table-column>
          <el-table-column prop="status" label="容器状态"></el-table-column>
          <el-table-column prop="startTime" label="容器启动时间"></el-table-column>
          <el-table-column prop="endTime" label="容器终止时间"></el-table-column>
          <el-table-column prop="createTime" label="创建时间"></el-table-column>
          <el-table-column prop="lastTime" label="持续时间"></el-table-column>
          <el-table-column prop="attackTime" label="攻击时间"></el-table-column>
          <el-table-column prop="defendTime" label="防御时间"></el-table-column>
          <el-table-column prop="updateTime" label="更新时间"></el-table-column>
        </el-table>
        <el-pagination
            v-model:current-page="currentPage"
            v-model:page-size="pageSize"
            :page-sizes="[5, 10, 20, 30, 40]"
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
import {ref, reactive, onMounted, computed} from "vue";
import {ElMessage, ElMessageBox, UploadInstance} from "element-plus";
import axios from "axios";
import type { UploadProps, UploadUserFile } from 'element-plus'

onMounted(() => {
  listData()
})

interface Data {
  attackTime: string,
  containerId: string,
  containerName: string,
  containerType: string,
  createTime: string,
  defendTime: string,
  endTime: string,
  id: string,
  imageId: string,
  imageName: string,
  lastTime: string,
  rangeName: null,
  startTime: string,
  status: string,
  updateTime: string
}

const options = [
  { value: "-1", label: "请选择" },
  { value: "0", label: "created" },
  { value: "1", label: "restarting" },
  { value: "2", label: "running" },
  { value: "3", label: "paused" },
  { value: "4", label: "exited" },
];

const datas = ref<Data[]>([]);

const form = reactive<any>({
  fileList: [],
})

const listData = () => {
  datas.value = []
  axios({
    headers: {
      Authorization: localStorage.getItem('Authorization')
    },
    method: 'post',
    url: '/container/selectByPage',
    data: {
      "containerName": "",
      "status": "",
      "targetName": "",
      "pageNum": 1,
      "pageSize": 1000
    }
  }).then((response) => {
    for (const data of response.data.data.list) {
      for (let i = 0; i < options.length; i++) {
        if (data.status === options[i].value)
          data.status = options[i].label
      }
      datas.value.push(data);
    }
  });
};

const query = () => {
  if (queryForm.containerName === "" && queryForm.status === "-1" && queryForm.targetName === "") {
    window.setTimeout(() => {
      listData()
    }, 250)
  } else {
    datas.value = []
    axios({
      headers: {
        Authorization: localStorage.getItem('Authorization')
      },
      method: 'post',
      url: '/container/selectByPage',
      data: {
        "containerName": queryForm.containerName,
        "status": queryForm.status,
        "targetName": queryForm.targetName,
        "pageNum": 1,
        "pageSize": 1000
      }
    }).then((response) => {
      for (const data of response.data.data.list) {
        for (let i = 0; i < options.length; i++) {
          if (data.status === options[i].value)
            data.status = options[i].label
        }

        datas.value.push(data);
      }
      ElMessage(response.data.message)
    });
  }

}

const add = (imageId: string) => {
  axios({
    method: 'post',
    url: '/container/create',
    headers: {
      'Authorization': localStorage.getItem('Authorization')
    },
    data: {imageId: imageId}
  }).then((response) => {
    ElMessage(response.data.message)
  });
}

let addDialogVisible = ref(false)

const queryForm = reactive({
  containerName: '',
  status: '',
  targetName: '',
})

const newForm = reactive({
  imageId: ''
})

const closeAddDialogSubmitForm = () => {
  add(newForm.imageId)
  window.setTimeout(() => {
    listData()
  }, 250)
  newForm.imageId = ''
  addDialogVisible.value = false
}

const small = ref(false)
const background = ref(true)
const disabled = ref(false)
const pageSize = ref(5)
const currentPage = ref(1)

const handleSizeChange = (val: number) => {
  pageSize.value = val
}

const handleCurrentChange = (val: number) => {
  currentPage.value = val
}
</script>
