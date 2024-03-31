<template>
  <div style="display: flex; flex-direction: column; width: 100%">
    <div>
      <el-form ref="queryFormRef" :model="queryForm" label-position="left" style="display: flex; flex-direction: row">
        <el-form-item label="攻防演练名称" prop="exerciseName">
          <el-input style="margin-right: 50px" v-model="queryForm.exerciseName" placeholder="单行输入" />
        </el-form-item>
        <el-form-item label="靶场名称" prop="rangeName">
          <el-input style="margin-right: 50px" v-model="queryForm.rangeName" placeholder="单行输入" />
        </el-form-item>
        <el-form-item label="靶场状态" prop="imageType">
          <el-select-v2
              v-model="queryForm.rangeStatus"
              :options="options"
              placeholder="请选择"
              style="width: 300px"
          />
        </el-form-item>
      </el-form>

    </div>
    <div>
      <!-- 新增 -->
<!--      <el-button type="primary" @click="addDialogVisible = true">新增靶机容器</el-button>-->
      <el-button type="primary" @click="query" style="display: flex; float: right">查询</el-button>
<!--      <el-dialog v-model="addDialogVisible" title="上传镜像" width="30%">-->
<!--        &lt;!&ndash; TODO: 当前的行为是点击空白处关闭新建框会保留之前的填写记录, 是否要清除? &ndash;&gt;-->
<!--        <el-form ref="newFormRef" :model="newForm" label-position="left">-->
<!--          <el-form-item label="镜像ID" prop="imageId">-->
<!--            <el-input v-model="newForm.imageId" placeholder="单行输入" />-->
<!--          </el-form-item>-->
<!--        </el-form>-->
<!--        <template #footer>-->
<!--      <span class="dialog-footer">-->
<!--        <el-button-->
<!--            type="primary"-->
<!--            @click="closeAddDialogSubmitForm()"-->
<!--        >-->
<!--          上传-->
<!--        </el-button>-->
<!--        <el-button @click="addDialogVisible = false"-->
<!--        >取消</el-button-->
<!--        >-->
<!--      </span>-->
<!--        </template>-->
<!--      </el-dialog>-->

      <!-- 表格主体 -->
      <div style="width: 100%">
        <el-table :data="datas.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
                  stripe style="width: 100%">
          <el-table-column prop="id" label="ID"></el-table-column>
          <el-table-column prop="exerciseId" label="攻防演练ID"></el-table-column>
          <el-table-column prop="rangeName" label="靶场名称"></el-table-column>
          <el-table-column prop="digBugNums" label="挖掘漏洞个数"></el-table-column>
          <el-table-column prop="defendPercent" label="防御比例"></el-table-column>
          <el-table-column prop="status" label="靶场状态">
          </el-table-column>
          <el-table-column prop="createTime" label="创建时间"></el-table-column>
          <el-table-column prop="startTime" label="开始时间"></el-table-column>
          <el-table-column prop="endTime" label="终止时间"></el-table-column>
          <el-table-column prop="attackTimes" label="攻击次数"></el-table-column>
          <el-table-column prop="defendTime" label="防御时间"></el-table-column>
          <el-table-column prop="updateTime" label="更新时间"></el-table-column>
        </el-table>
        <el-pagination
            v-model:current-page="currentPage"
            v-model:page-size="pageSize"
            :page-sizes="[2, 5, 10, 20, 30, 40]"
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
import {ref, reactive, onMounted} from "vue";
import {ElMessage} from "element-plus";
import axios from "axios";

onMounted(() => {
  listData()
})

interface Data {
  attackTimes: string,
  createTime: string,
  defendPercent: string,
  defendTime: string,
  digBugNums: string,
  endTime: string,
  exerciseId: string,
  id: string,
  rangeName: null,
  startTime: string,
  status: string,
  updateTime: string
}

const options = [
  { value: "-1", label: "请选择" },
  { value: "0", label: "运行中" },
  { value: "1", label: "已停止" }
];

const datas = ref<Data[]>([]);

const listData = () => {
  datas.value = []
  axios({
    headers: {
      Authorization: localStorage.getItem('Authorization')
    },
    method: 'post',
    url: '/range/selectByPage',
    data: {
      "containerName": "",
      "status": "",
      "targetName": "",
      "pageNum": 1,
      "pageSize": 1000
    }
  }).then((response) => {
    for (const data of response.data.data.list) {
      if (data.status === '1')
        data.status = '已停止'
      else
        data.status = '运行中'
      datas.value.push(data);
    }
  });
};

const query = () => {
  if (queryForm.exerciseName === "" && queryForm.rangeName === "" && queryForm.rangeStatus === "-1") {
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
      url: '/range/selectByPage',
      data: {
        "exerciseName": queryForm.exerciseName,
        "rangeName": queryForm.rangeName,
        "rangeStatus": queryForm.rangeStatus,
        "pageNum": 1,
        "pageSize": 1000
      }
    }).then((response) => {
      for (const data of response.data.data.list) {
        if (data.status === '1')
          data.status = '已停止'
        else
          data.status = '运行中'
        datas.value.push(data);
      }
      ElMessage(response.data.message)
    });
  }
}

//
// const add = (imageId: string) => {
//   axios({
//     method: 'post',
//     url: '/container/create',
//     headers: {
//       'Authorization': localStorage.getItem('Authorization')
//     },
//     data: {imageId: imageId}
//   }).then((response) => {
//     ElMessage(response.data.message)
//   });
// }

// let addDialogVisible = ref(false)

const queryForm = reactive({
  exerciseName: '',
  rangeStatus: '',
  rangeName: '',
})

// const newForm = reactive({
//   imageId: ''
// })
//
// const closeAddDialogSubmitForm = () => {
//   add(newForm.imageId)
//   window.setTimeout(() => {
//     listData()
//   }, 250)
//   newForm.imageId = ''
//   addDialogVisible.value = false
// }

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
