<template>
  <!-- 新增配置 -->
  <el-button type="primary" @click="addDialogVisible = true">新增</el-button>
  <el-dialog v-model="addDialogVisible" title="新增配置" width="30%">
    <!-- TODO: 当前的行为是点击空白处关闭新建框会保留之前的填写记录, 是否要清除? -->
    <el-form
      ref="newConfigFormRef"
      :model="newConfigForm"
      label="70px"
      label-position="left"
    >
      <el-form-item label="配置项" prop="configItem">
        <el-input v-model="newConfigForm.configItem" placeholder="单行输入" />
      </el-form-item>
      <el-form-item label="配置值" prop="configValue">
        <el-input v-model="newConfigForm.configValue" placeholder="单行输入" />
      </el-form-item>
      <el-form-item label="备注" prop="note">
        <el-input
          v-model="newConfigForm.remark"
          placeholder="多行输入"
          :rows="4"
          type="textarea"
        />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button
          type="primary"
          @click="closeAddDialogSubmitForm()"
        >
          确定
        </el-button>
        <el-button @click="addDialogVisible = false"
          >取消</el-button
        >
      </span>
    </template>
  </el-dialog>

  <!-- 表格主体 -->
  <div style="width: 100%">
    <el-table
        :data="configs.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
        stripe style="width: 100%">
      <el-table-column prop="id" label="配置ID"></el-table-column>
      <el-table-column prop="configName" label="配置项"></el-table-column>
      <el-table-column prop="configValue" label="配置值"></el-table-column>
      <el-table-column prop="remark" label="备注"></el-table-column>
      <el-table-column prop="createTime" label="创建时间"></el-table-column>
      <el-table-column prop="updateTime" label="更新时间"></el-table-column>
      <el-table-column label="操作">
        <template #default="{ row }">
          <el-button link type="primary" @click="showEditDialog(row)"
          >编辑</el-button
          >
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
        :total="configs.length"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    />
  </div>

  <!-- 编辑角色 -->
  <el-dialog v-model="editDialogVisible" title="编辑" width="30%">
    <el-form
      ref="editConfigFormRef"
      :model="editConfigForm"
      label="70px"
      label-position="left"
    >
      <el-form-item label="配置Id" prop="configId">
        <el-input v-model="editConfigForm.configId" disabled />
      </el-form-item>
      <el-form-item label="配置项" prop="configItem">
        <el-input v-model="editConfigForm.configItem"/>
      </el-form-item>
      <el-form-item label="配置值" prop="configValue">
        <el-input v-model="editConfigForm.configValue" />
      </el-form-item>
      <el-form-item label="备注" prop="remark">
        <el-input v-model="editConfigForm.remark" :rows="4" type="textarea" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button
          type="primary"
          @click="closeEditDialogSubmitForm"
        >
          确定
        </el-button>
        <el-button @click="editDialogVisible = false"
          >取消</el-button
        >
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import {ref, reactive, onMounted} from "vue";
import {ElMessage} from "element-plus";
import axios from "axios";

interface Config {
  configName: string;
  configValue: string;
  createTime: string;
  id: string;
  updateTime: string;
}

onMounted(() => {
  listConfigs()
})

const configs = ref<Config[]>([]);

const listConfigs = () => {
  configs.value = []
  axios({
    headers: {
      Authorization: localStorage.getItem('Authorization')
    },
    method: 'get',
    url: '/system/config/selectByPage/1/10000'
  }).then((response) => {
    for (const config of response.data.data.list) {
      configs.value.push(config);
    }
  });
};

const addConfig = (configItem: string, configValue: string, remark: string) => {
  axios({
    method: 'post',
    url: '/system/config/createConfig',
    headers: {
      'Authorization': localStorage.getItem('Authorization'),
    },
    data: {
      configItem: configItem,
      configValue: configValue,
      remark: remark
    }
  }).then((response) => {
    ElMessage(response.data.message)
  });
}

const deleteConfig = (configId: string) => {
  axios({
    method: 'post',
    url: '/system/config/deleteConfig',
    headers: {
      'Authorization': localStorage.getItem('Authorization'),
    },
    data: {
      configId: configId
    }
  }).then((response) => {
    ElMessage(response.data.message)
  });
}

const updateConfig = (configId: string, configItem: string, configValue: string, remark: string) => {
  axios({
    method: 'post',
    url: '/system/config/updateConfig',
    headers: {
      'Authorization': localStorage.getItem('Authorization'),
    },
    data: {
      configId: configId,
      configItem: configItem,
      configValue: configValue,
      remark: remark
    }
  }).then((response) => {
    ElMessage(response.data.message)
  });
};

// add new config
const newConfigForm = reactive({
  configItem: '',
  configValue: '',
  remark: '',
});

const addDialogVisible = ref(false);

const closeAddDialogSubmitForm = () => {
  addConfig(newConfigForm.configItem, newConfigForm.configValue, newConfigForm.remark)
  window.setTimeout(() => {
    listConfigs()
  }, 250)
  newConfigForm.configItem = ''
  newConfigForm.configValue = ''
  newConfigForm.remark = ''
  addDialogVisible.value = false
};

// edit user
const editConfigForm = reactive({
  configId: '',
  configItem: '',
  configValue: '',
  remark: '',
});
const editDialogVisible = ref(false);

const showEditDialog = (row) => {
  editDialogVisible.value = true
  editConfigForm.configId = row.id
  editConfigForm.configItem = row.configName
  editConfigForm.configValue = row.configValue
  editConfigForm.remark = row.remark
}

const closeEditDialogSubmitForm = () => {
  updateConfig(editConfigForm.configId, editConfigForm.configItem, editConfigForm.configValue, editConfigForm.remark)
  window.setTimeout(() => {
    listConfigs()
  }, 250)
  editDialogVisible.value = false
};

// delete user
const deleteRow = (row: Config) => {
  deleteConfig(row.id)
  window.setTimeout(() => {
    listConfigs()
  }, 250)
};

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
