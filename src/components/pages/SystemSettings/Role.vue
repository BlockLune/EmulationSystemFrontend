<template>
  <!-- 新增角色 -->
  <el-button type="primary" @click="showAddDialog">新增</el-button>
  <el-dialog v-model="addDialogVisible" title="新增角色" width="30%">
    <!-- TODO: 当前的行为是点击空白处关闭新建框会保留之前的填写记录, 是否要清除? -->
    <el-form
      ref="newRoleFormRef"
      :model="newRoleForm"
      label="70px"
      label-position="left"
    >
      <el-form-item label="角色名称" prop="name">
        <el-input v-model="newRoleForm.name" placeholder="单行输入" />
      </el-form-item>
      <el-form-item label="权限">
        <el-checkbox-group v-model="newRoleForm.permissions">
          <el-checkbox label="仿真靶场管理" />
          <el-checkbox label="靶场管理" />
          <el-checkbox label="容器管理" />
          <el-checkbox label="镜像管理" />
          <el-checkbox label="攻防演练" />
          <el-checkbox label="漏洞库管理" />
          <el-checkbox label="系统管理" />
        </el-checkbox-group>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button
          type="primary"
          @click="closeAddDialogSubmitForm(newRoleFormRef)"
        >
          确定
        </el-button>
        <el-button @click="closeAddDialogNoSubmitForm(newRoleFormRef)"
          >取消</el-button
        >
      </span>
    </template>
  </el-dialog>

  <!-- 表格主体 -->
  <el-table :data="roles" stripe style="width: 100%">
    <el-table-column prop="name" label="角色名称"></el-table-column>
    <el-table-column prop="permissions" label="权限">
      <template #default="{ row }">
        <span>{{ formatPermissions(row.permissions) }}</span>
      </template>
    </el-table-column>
    <el-table-column prop="createdAt" label="创建时间"></el-table-column>
    <el-table-column prop="updatedAt" label="更新时间"></el-table-column>
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

  <!-- 编辑角色 -->
  <el-dialog v-model="editDialogVisible" title="编辑角色" width="30%">
    <el-form
      ref="editRoleFormRef"
      :model="editRoleForm"
      label="70px"
      label-position="left"
    >
      <el-form-item label="角色名称" prop="name">
        <el-input v-model="editRoleForm.name" disabled />
      </el-form-item>
      <el-form-item label="权限">
        <el-checkbox-group v-model="editRoleForm.permissions">
          <el-checkbox label="仿真靶场管理" />
          <el-checkbox label="靶场管理" />
          <el-checkbox label="容器管理" />
          <el-checkbox label="镜像管理" />
          <el-checkbox label="攻防演练" />
          <el-checkbox label="漏洞库管理" />
          <el-checkbox label="系统管理" />
        </el-checkbox-group>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button
          type="primary"
          @click="closeEditDialogSubmitForm(editRoleFormRef)"
        >
          确定
        </el-button>
        <el-button @click="closeEditDialogNoSubmitForm(editRoleFormRef)"
          >取消</el-button
        >
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import { ref, reactive } from "vue";
import { FormInstance } from "element-plus";

interface Role {
  name: string;
  permissions: string[];
  createdAt: string;
  updatedAt: string;
}

// for debugging
const roles = ref<Role[]>([
  {
    name: "角色A",
    permissions: ["仿真靶场管理"],
    createdAt: "2023-12-31",
    updatedAt: "2024-01-01",
  },
  {
    name: "角色B",
    permissions: ["攻防演练", "系统管理"],
    createdAt: "2023-12-30",
    updatedAt: "2024-01-02",
  },
]);

const getTime = (): string => {
  const datetime = new Date();
  return `${datetime.getFullYear()}-${datetime.getMonth() + 1}-${datetime.getDate()}`;
};

const formatPermissions = (permissions: string[]): string => {
  return permissions.join("、");
};

// add new role
const newRoleFormRef = ref<FormInstance>();
const newRoleForm = reactive({
  name: "",
  permissions: [] as string[],
});

const addDialogVisible = ref(false);
const showAddDialog = () => {
  addDialogVisible.value = true;
};
const clearAddDialogForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.resetFields();
  newRoleForm.name = "";
  newRoleForm.permissions = [];
};
const closeAddDialogNoSubmitForm = (formEl: FormInstance | undefined) => {
  addDialogVisible.value = false;
  //console.log("closeAddDialogNoSubmitForm() called.");
  if (!formEl) return;
  clearAddDialogForm(formEl);
};
const closeAddDialogSubmitForm = (formEl: FormInstance | undefined) => {
  addDialogVisible.value = false;
  if (!formEl) return;
  //console.log("closeAddDialogSubmitForm() called.");
  //console.log(newRoleForm);
  const newRole: Role = {
    name: newRoleForm.name,
    permissions: newRoleForm.permissions,
    createdAt: getTime(),
    updatedAt: getTime(),
  };
  roles.value.push(newRole);
};

// edit role
const editRoleFormRef = ref<FormInstance>();
const editRoleForm = reactive({
  name: "",
  permissions: [] as string[],
});
const editDialogVisible = ref(false);
let roleIndex = -1;
const isPermissionsEqual = (
  arr1: Array<String>,
  arr2: Array<String>
): boolean => {
  const set1 = new Set(arr1);
  const set2 = new Set(arr2);
  if (set1.size !== set2.size) return false;
  for (const item of set1) {
    if (!set2.has(item)) {
      return false;
    }
  }
  return true;
};
const showEditDialog = (row: Role) => {
  editRoleForm.name = row.name;
  for (const permission of row.permissions) {
    editRoleForm.permissions.push(permission);
  }

  for (let i = 0; i < roles.value.length; ++i) {
    const curRole = roles.value.at(i);
    if (curRole!.name === row.name) {
      if (isPermissionsEqual(curRole!.permissions, row.permissions)) {
        if (curRole!.createdAt === row.createdAt) {
          if (curRole!.updatedAt === row.updatedAt) {
            roleIndex = i;
          }
        }
      }
    }
  }
  if (roleIndex === -1) {
    console.log("ERROR: role not found");
    return;
  }
  editDialogVisible.value = true;
};
const clearEditDialogForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.resetFields();
  editRoleForm.name = "";
  editRoleForm.permissions = [];
};
const closeEditDialogNoSubmitForm = (formEl: FormInstance | undefined) => {
  editDialogVisible.value = false;
  if (!formEl) return;
  clearEditDialogForm(formEl);
};
const closeEditDialogSubmitForm = (formEl: FormInstance | undefined) => {
  editDialogVisible.value = false;
  if (!formEl) return;
  roles.value.at(roleIndex)!.permissions = editRoleForm.permissions;
  roles.value.at(roleIndex)!.updatedAt = getTime();
};
const deleteRow = (role: Role) => {
  console.log("删除: ", role);
};
</script>
