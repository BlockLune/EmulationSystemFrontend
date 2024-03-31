<template>
  <div style="display: flex; flex-direction: column; width: 100%">
    <div>
      <el-form ref="queryFormRef" :model="queryForm" label-position="left" style="display: flex; flex-direction: row">
        <el-form-item label="镜像名称" prop="imageName">
          <el-input style="margin-right: 50px" v-model="queryForm.imageName" placeholder="单行输入" />
        </el-form-item>
        <el-form-item label="资源类型" prop="imageType">
          <el-select-v2
              v-model="queryForm.imageType"
              :options="mirrorOptions"
              placeholder="请选择"
              style="width: 300px"
          />
        </el-form-item>
      </el-form>

    </div>
    <div>
      <!-- 新增 -->
      <el-button type="primary" @click="addDialogVisible = true">上传镜像</el-button>
      <el-button type="primary" @click="query" style="display: flex; float: right">查询</el-button>
      <el-dialog v-model="addDialogVisible" title="上传镜像" width="30%">
        <!-- TODO: 当前的行为是点击空白处关闭新建框会保留之前的填写记录, 是否要清除? -->
        <el-form ref="newMirrorFormRef" :model="newMirrorForm" label-position="left">
          <el-form-item label="镜像名称" prop="imageName">
            <el-input v-model="newMirrorForm.imageName" placeholder="单行输入" />
          </el-form-item>
          <el-form-item label="版本号" prop="version">
            <el-input v-model="newMirrorForm.version" placeholder="单行输入" />
          </el-form-item>
          <el-form-item label="资源类型" prop="imageType">
            <el-select-v2
                v-model="newMirrorForm.imageType"
                :options="mirrorOptions"
                placeholder="请选择"
                style="width: 300px"
            />
          </el-form-item>
          <el-form-item label="资源类型(.tar文件)" prop="resourceType">
            <el-upload
                ref="uploadRef"
                :limit="1"
                :auto-upload="false"
                action=""
                accept=".tar"
                :show-file-list="true"
                v-model:file-list="form.files"
            >
              <el-button type="primary">选择文件</el-button>
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

      <!-- 表格主体 -->
      <div style="width: 100%">
        <el-table :data="mirrors.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
                  stripe style="width: 100%">
          <el-table-column prop="id" label="镜像ID"></el-table-column>
          <el-table-column prop="imageName" label="镜像名称"></el-table-column>
          <el-table-column prop="version" label="版本名称"></el-table-column>
          <el-table-column prop="imageType" label="镜像类型"></el-table-column>
          <el-table-column prop="createTime" label="上传时间"></el-table-column>
          <el-table-column prop="updateTime" label="修改时间"></el-table-column>
          <el-table-column label="操作">
            <!--        <template #header>-->
            <!--          <el-input v-model="search" size="small" placeholder="搜索" />-->
            <!--        </template>-->
            <template #default="{ row }">
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
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
            v-model:current-page="currentPage"
            v-model:page-size="pageSize"
            :page-sizes="[5, 10, 20, 30, 40]"
            :small="small"
            :disabled="disabled"
            :background="background"
            layout="total, sizes, prev, pager, next, jumper"
            :total="mirrors.length"
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
  listMirrors()
})

interface Mirror {
  id: string;
  imageName: string;
  imageType: string;
  version: string;
  path: string;
  createTime: string;
  updateTime: string;
}

const mirrorOptions = [
  { value: "-1", label: "请选择" },
  { value: "0", label: "漏洞挖掘" },
  { value: "1", label: "包含漏洞数据镜像" },
  { value: "2", label: "网络攻击镜像" },
  { value: "3", label: "网络防御镜像" },
  { value: "4", label: "靶机镜像" },
];

const formData = new FormData();

const mirrors = ref<Mirror[]>([]);

const form = reactive<any>({
  fileList: [],
})

const listMirrors = () => {
  mirrors.value = []
  axios({
    headers: {
      Authorization: localStorage.getItem('Authorization')
    },
    method: 'post',
    url: '/image/selectByPage',
    data: {
      "imageName": "",
      "imageType": "",
      "pageNum": 1,
      "pageSize": 1000
    }
  }).then((response) => {
    for (const mirror of response.data.data.list) {
      for (let i = 0; i < mirrorOptions.length; i++) {
        if (mirror.imageType === mirrorOptions[i].value)
          mirror.imageType = mirrorOptions[i].label
      }
      mirrors.value.push(mirror);
    }
  });
};

const query = () => {
  if (queryForm.imageType === "" && queryForm.imageName === "") {
    window.setTimeout(() => {
      listMirrors()
    }, 250)
  } else {
    mirrors.value = []
    axios({
      headers: {
        Authorization: localStorage.getItem('Authorization')
      },
      method: 'post',
      url: '/image/selectByPage',
      data: {
        "imageName": queryForm.imageName,
        "imageType": queryForm.imageType,
        "pageNum": 1,
        "pageSize": 1000
      }
    }).then((response) => {
      for (const mirror of response.data.data.list) {
        for (let i = 0; i < mirrorOptions.length; i++) {
          if (mirror.imageType === mirrorOptions[i].value)
            mirror.imageType = mirrorOptions[i].label
        }
        mirrors.value.push(mirror);
      }
      ElMessage(response.data.message)
    });
  }

}

const deleteMirror = (id: string) => {
  axios({
    method: 'post',
    url: '/image/deleteImage',
    headers: {
      'Authorization': localStorage.getItem('Authorization'),
    },
    data: {
      id: id
    }
  }).then((response) => {
    ElMessage(response.data.message)
  });
}

const addMirror = (formData) => {
  axios({
    method: 'post',
    url: '/image/uploadImage',
    headers: {
      'Authorization': localStorage.getItem('Authorization'),
      'Content-Type': 'multipart/form-data'
    },
    data: formData
  }).then((response) => {
    ElMessage(response.data.message)
  });
}

let addDialogVisible = ref(false)

const queryForm = reactive({
  imageName: '',
  imageType: ''
})

const newMirrorForm = reactive({
  imageName: '',
  imageType: '',
  version: ''
})

const uploadRef = ref<UploadInstance>()

const closeAddDialogSubmitForm = () => {
  formData.append('file', form.fileList[0])
  formData.append('imageName', newMirrorForm.imageName)
  formData.append('imageType', newMirrorForm.imageType)
  formData.append('version', newMirrorForm.version)
  addMirror(formData)
  window.setTimeout(() => {
    listMirrors()
  }, 250)
  newMirrorForm.imageName = ''
  newMirrorForm.imageType = ''
  newMirrorForm.version = ''
  uploadRef.value?.clearFiles()
  addDialogVisible.value = false
}

const deleteRow = (row) => {
  deleteMirror(row.id)
  window.setTimeout(() => {
    listMirrors()
  }, 250)

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
