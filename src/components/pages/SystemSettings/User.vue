<template>
  <!-- 新增用户 -->
  <el-button type="primary" @click="showAddDialog">新增</el-button>
  <el-dialog v-model="addDialogVisible" title="新增用户" width="30%">
    <!-- TODO: 当前的行为是点击空白处关闭新建框会保留之前的填写记录, 是否要清除? -->
    <el-form ref="newUserFormRef" :model="newUserForm" label-position="left">
      <el-form-item label="用户名称" prop="name">
        <el-input v-model="newUserForm.username" placeholder="单行输入" />
      </el-form-item>
      <el-form-item label="角色名称" prop="role">
        <el-select-v2
          v-model="newUserForm.role"
          :options="roleOptions"
          placeholder="请选择"
          style="width: 300px"
        />
      </el-form-item>
      <el-form-item label="登录名" prop="loginName">
        <el-input v-model="newUserForm.loginName" placeholder="单行输入" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button
          type="primary"
          @click="closeAddDialogSubmitForm(newUserFormRef)"
        >
          确定
        </el-button>
        <el-button @click="closeAddDialogNoSubmitForm(newUserFormRef)"
          >取消</el-button
        >
      </span>
    </template>
  </el-dialog>

  <!-- 表格主体 -->
  <el-table :data="users" stripe style="width: 100%">
    <el-table-column prop="username" label="用户名称"></el-table-column>
    <el-table-column prop="role" label="角色名称"></el-table-column>
    <el-table-column prop="loginName" label="登录名"></el-table-column>
    <el-table-column prop="status" label="用户状态">
      <template #default="{ row }">
        <el-switch
          v-model="row.status"
          active-text="启用"
          inactive-text="禁用"
        />
      </template>
    </el-table-column>
    <el-table-column prop="lastLoginAt" label="最近登录时间"></el-table-column>
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

  <!-- 编辑用户 -->
  <el-dialog v-model="editDialogVisible" title="编辑用户" width="30%">
    <el-form
      ref="editUserFormRef"
      :model="editUserForm"
      label="70px"
      label-position="left"
    >
      <el-form-item label="用户名称" prop="username">
        <el-input v-model="editUserForm.username" />
      </el-form-item>
      <el-form-item label="角色名" prop="role">
        <el-input v-model="editUserForm.role" />
      </el-form-item>
      <el-form-item label="登录名称" prop="loginName">
        <el-input v-model="editUserForm.loginName" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button
          type="primary"
          @click="closeEditDialogSubmitForm(editUserFormRef)"
        >
          确定
        </el-button>
        <el-button @click="closeEditDialogNoSubmitForm(editUserFormRef)"
          >取消</el-button
        >
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import { ref, reactive } from "vue";
import { ElSelect, FormInstance } from "element-plus";

interface User {
  username: string;
  role: string;
  loginName: string;
  status: boolean;
  lastLoginAt: string;
}

const roleOptions = [
  { value: "管理员", label: "管理员" },
  { value: "测试员", label: "测试员" },
];

// for debugging
const users = ref<User[]>([
  {
    username: "张三",
    role: "管理员",
    loginName: "zhang san",
    status: true,
    lastLoginAt: "2023-12-02 00:00:00",
  },
  {
    username: "王武",
    role: "测试员",
    loginName: "wang wu",
    status: false,
    lastLoginAt: "2023-12-02 00:00:00",
  },
]);

const getTime = (): string => {
  const datetime = new Date();
  return `${datetime.getFullYear()}-${datetime.getMonth() + 1}-${datetime.getDate()}`;
};

const getUserStatus = (row: User) => {
  return row.status ? "启用" : "禁用";
};

// add new user
const newUserFormRef = ref<FormInstance>();
const newUserForm = reactive({
  username: "",
  role: "",
  loginName: "",
  status: true, // 新增用户默认启用
  lastLoginAt: "",
});

const addDialogVisible = ref(false);
const showAddDialog = () => {
  addDialogVisible.value = true;
};
const clearNewConfigForm = () => {
  newUserForm.username = "";
  newUserForm.role = "";
  newUserForm.loginName = "";
  newUserForm.status = false;
  newUserForm.lastLoginAt = "";
};
const clearAddDialog = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.resetFields();
  clearNewConfigForm();
};
const closeAddDialogNoSubmitForm = (formEl: FormInstance | undefined) => {
  addDialogVisible.value = false;
  if (!formEl) return;
  clearAddDialog(formEl);
};
const closeAddDialogSubmitForm = (formEl: FormInstance | undefined) => {
  addDialogVisible.value = false;
  if (!formEl) return;
  const newUser: User = {
    username: newUserForm.username,
    role: newUserForm.role,
    loginName: newUserForm.loginName,
    status: newUserForm.status,
    lastLoginAt: newUserForm.lastLoginAt,
  };
  users.value.push(newUser);
  clearAddDialog(formEl);
};

// edit and delete operation helper
function deepEqual<T>(obj1: T, obj2: T): boolean {
  if (obj1 === obj2) {
    return true;
  }
  if (
    typeof obj1 !== "object" ||
    obj1 === null ||
    typeof obj2 !== "object" ||
    obj2 === null
  ) {
    return false;
  }

  const keys1 = Object.keys(obj1) as Array<keyof T>;
  const keys2 = Object.keys(obj2) as Array<keyof T>;

  if (keys1.length !== keys2.length) {
    return false;
  }

  for (const key of keys1) {
    if (!keys2.includes(key)) {
      return false;
    }

    if (!deepEqual(obj1[key], obj2[key])) {
      return false;
    }
  }

  return true;
}
let userIndex = -1;
const findUserIndex = (row: User): number => {
  for (let i = 0; i < users.value.length; ++i) {
    const curUser = users.value.at(i);
    if (deepEqual<User>(curUser!, row)) {
      userIndex = i;
      break;
    }
  }
  console.log(userIndex);
  return userIndex;
};

// edit user
const editUserFormRef = ref<FormInstance>();
const editUserForm = reactive({
  username: "",
  role: "",
  loginName: "",
  status: false,
});
const editDialogVisible = ref(false);
const showEditDialog = (row: User) => {
  editUserForm.username = row.username;
  editUserForm.role = row.role;
  editUserForm.loginName = row.loginName;
  userIndex = findUserIndex(row); // update role index
  editDialogVisible.value = true;
};
const clearEditRoleForm = () => {
  editUserForm.username = "";
  editUserForm.role = "";
  editUserForm.loginName = "";
  editUserForm.status = false;
};
const clearEditDialog = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.resetFields();
  clearEditRoleForm();
};
const closeEditDialogNoSubmitForm = (formEl: FormInstance | undefined) => {
  editDialogVisible.value = false;
  if (!formEl) return;
  clearEditDialog(formEl);
};
const closeEditDialogSubmitForm = (formEl: FormInstance | undefined) => {
  editDialogVisible.value = false;
  if (!formEl) return;
  users.value.at(userIndex)!.username = editUserForm.username;
  users.value.at(userIndex)!.role = editUserForm.role;
  users.value.at(userIndex)!.loginName = editUserForm.loginName;
  users.value.at(userIndex)!.status = editUserForm.status;
};

// delete user
const deleteRow = (row: User) => {
  userIndex = findUserIndex(row); // update role index
  users.value = users.value.filter(
    (element) => users.value.indexOf(element) !== userIndex
  );
};
</script>
