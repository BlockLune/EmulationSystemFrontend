<template>
  <div style="display:flex; flex-direction: column; width: 100%">
    <div>
      <el-form
          style="display:flex; flex-direction: row"
          :model="detailedLoopHoleForm" label="70px" label-position="left">
<!--        <el-page-header @back="goBack"></el-page-header>-->
        <el-form-item label="漏洞名称" prop="name" style="margin-right: 20px">
          <el-input v-model="detailedLoopHoleForm.name" placeholder="单行输入" />
        </el-form-item>
        <el-form-item label="漏洞发现时间" prop="time">
          <el-time-select
              style="width: 300px"
              v-model="detailedLoopHoleForm.time"
              start="08:30" step="00:15" end="18:30"
              placeholder="时间选择" />
        </el-form-item>
      </el-form>
      <el-button style="display: flex; float: right" @click="">查询漏洞</el-button>
      <el-button style="display: flex; float: right" @click="">查询目录</el-button>
    </div>
    <div>

    </div>
    <div>
      <el-table :data="datas">
        <el-table-column prop="categoryId" label="目录ID" />
        <el-table-column prop="id" label="ID" />
        <el-table-column prop="holeName" label="漏洞名称" />
        <el-table-column prop="level" label="漏洞等级" />
        <el-table-column prop="metadata" label="元数据" width="120"/>
        <el-table-column prop="relateContainerId" label="相关容器ID" />
        <el-table-column prop="relateExerciseId" label="相关演练ID" />
        <el-table-column prop="relateImageId" label="相关镜像ID" />
        <el-table-column prop="createTime" label="创建时间" />
        <el-table-column prop="updateTime" label="更新时间" />
        <el-table-column prop="source" label="漏洞源" />
      </el-table>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {onMounted, reactive, ref} from 'vue';
import axios from "axios";
import {ElMessage} from "element-plus";

onMounted(() => {
  listData()
})

const dialogTableVisible = ref(false)
const dialogTable2Visible = ref(false)
const detailedLoopHoleForm = reactive({
  name: '',
  time: '',
});


interface Data {
  categoryId: string,
  createTime: string,
  holeName: string,
  id: string,
  level: string,
  metadata: object,
  relateContainerId: string,
  relateExerciseId: string,
  relateImageId: null,
  updateTime: string
}

const datas = ref<Data[]>([]);
const metadatas = ref([]);

const listData = () => {
  datas.value = []
  axios({
    headers: {
      Authorization: localStorage.getItem('Authorization')
    },
    method: 'get',
    url: '/loophole/get/all/1/1000',
  }).then((response) => {
    console.log(response.data.data.list)
    for (const data of response.data.data.list) {
      datas.value.push(data);
      for (const meta of data.metadata) {
        metadatas.value.push(meta)
      }
    }
  });
};

</script>
