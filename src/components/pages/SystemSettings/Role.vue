<template>
  <!-- 新增角色 -->
  <el-button type="primary" @click="addDialogVisible = true">新增</el-button>
  <el-dialog v-model="addDialogVisible" title="新增角色" width="30%">
    <!-- TODO: 当前的行为是点击空白处关闭新建框会保留之前的填写记录, 是否要清除? -->
    <el-form ref="newRoleFormRef" :model="newRoleForm" label="70px" label-position="left">
      <el-form-item label="角色名称" prop="name">
        <el-input v-model="newRoleForm.name" placeholder="单行输入" />
      </el-form-item>
      <el-form-item label="权限">
        <el-radio-group v-model="newRoleForm.auth">
          <el-radio value="仿真靶场管理">仿真靶场管理</el-radio>
          <el-radio value="靶场管理">靶场管理</el-radio>
          <el-radio value="容器管理">容器管理</el-radio>
          <el-radio value="镜像管理">镜像管理</el-radio>
          <el-radio value="攻防演练">攻防演练</el-radio>
          <el-radio value="漏洞库管理">漏洞库管理</el-radio>
          <el-radio value="系统管理">系统管理</el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="closeAddDialogSubmitForm">
          确定
        </el-button>
        <el-button @click="addDialogVisible = false">取消</el-button>
      </span>
    </template>
  </el-dialog>

  <!-- 表格主体 -->
  <el-table :data="roles" stripe style="width: 100%">
    <el-table-column prop="id" label="角色ID"></el-table-column>
    <el-table-column prop="name" label="角色名称"></el-table-column>
    <el-table-column prop="auth" label="权限">
<!--      <template #default="{ row }">-->
<!--        <span>{{ formatPermissions(row.auth) }}</span>-->
<!--      </template>-->
    </el-table-column>
    <el-table-column prop="createTime" label="创建时间"></el-table-column>
    <el-table-column prop="updateTime" label="更新时间"></el-table-column>
    <el-table-column label="操作">
      <template #default="{ row }">
        <el-button link type="primary" @click="showEditDialog(row)">编辑</el-button>
        <el-popconfirm title="确认删除？" confirm-button-text="确认" cancel-button-text="取消" @confirm="deleteRow(row)">
          <template #reference>
            <el-button link type="danger">删除</el-button>
          </template>
        </el-popconfirm>
      </template>
    </el-table-column>
  </el-table>

  <!-- 编辑角色 -->
  <el-dialog v-model="editDialogVisible" title="编辑角色" width="30%">
    <el-form ref="editRoleFormRef" :model="editRoleForm" label="70px" label-position="left">
      <el-form-item label="角色名称" prop="name">
        <el-input v-model="editRoleForm.name"/>
      </el-form-item>
      <el-form-item label="权限">
        <el-radio-group v-model="editRoleForm.auth">
          <el-radio label="仿真靶场管理" />
          <el-radio label="靶场管理" />
          <el-radio label="容器管理" />
          <el-radio label="镜像管理" />
<!--          <emulation-range-checkbox-group />-->
          <el-radio label="攻防演练" />
          <el-radio label="漏洞库管理" />
          <el-radio label="系统管理" />
        </el-radio-group>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="closeEditDialogSubmitForm">
          确定
        </el-button>
        <el-button @click="editDialogVisible = false">取消</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import {ref, reactive, onMounted} from "vue";
import { FormInstance } from "element-plus";
import EmulationRangeCheckboxGroup from "./Role/EmulationRangeCheckboxGroup.vue";
import { deleteRole, listRoles, updateRole, roless, addRole } from "~/services/api";

onMounted(() => {
  listRoles()
})

let roles = reactive(roless)

let addDialogVisible = ref(false)

let editDialogVisible = ref(false)

const newRoleForm = reactive({
  name: '',
  auth: ''
})

const editRoleForm = reactive({
  name: '',
  auth: ''
})
let rowData
const showEditDialog = (row) => {
  editDialogVisible.value = true
  rowData = row
}

const closeEditDialogSubmitForm = () => {
  editDialogVisible.value = true
  editRoleForm.name = rowData.name
  editRoleForm.auth = rowData.auth
  updateRole(editRoleForm.auth, rowData.id, editRoleForm.name)
}

const closeAddDialogSubmitForm = () => {
  addRole(newRoleForm.name, newRoleForm.auth)
  addDialogVisible.value = false
}

const deleteRow = (row) => {
  deleteRole(row.id)
}

</script>
