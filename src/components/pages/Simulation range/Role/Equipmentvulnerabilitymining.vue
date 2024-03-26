<template>
    <!-- 新增用户 -->
    <el-button type="primary" @click="showAddDialog">上传镜像</el-button>
    <el-dialog v-model="addDialogVisible" title="上传镜像" width="30%">
      <!-- TODO: 当前的行为是点击空白处关闭新建框会保留之前的填写记录, 是否要清除? -->
      <el-form ref="newUserFormRef" :model="newUserForm" label-position="left">
        <el-form-item label="镜像名称" prop="name">
          <el-input v-model="newUserForm.username" placeholder="单行输入" />
        </el-form-item>
        <el-form-item label="版本号" prop="role">
          <el-input v-model="newUserForm.role" placeholder="单行输入" />
        </el-form-item>
        <el-form-item label="资源类型" prop="loginName">
          <el-select-v2
            v-model="newUserForm.loginName"
            :options="roleOptions"
            placeholder="请选择"
            style="width: 300px"
          />
        </el-form-item>
        <el-form-item label="资源类型(.tar文件)" prop="resourceType">
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button
            type="primary"
            @click="closeAddDialogSubmitForm(newUserFormRef)"
          >
            上传
          </el-button>
          <el-button @click="closeAddDialogNoSubmitForm(newUserFormRef)"
            >取消</el-button
          >
        </span>
      </template>
    </el-dialog>
  
    <!-- 表格主体 -->
    <el-table :data="users" stripe style="width: 100%">
      <el-table-column prop="username" label="镜像名称"></el-table-column>
      <el-table-column prop="role" label="版本名称"></el-table-column>
      <el-table-column prop="loginName" label="镜像类型"></el-table-column>
      <el-table-column prop="lastLoginAt" label="上传时间"></el-table-column>
      <el-table-column prop="lastModifyAt" label="修改时间"></el-table-column>
      <el-table-column label="操作">
        <template #default="{ row }">
          <el-button link type="primary" @click="showEditDialog(row)"
            >更新</el-button
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
  
    <!-- 更新镜像 -->
    <el-dialog v-model="editDialogVisible" title="更新镜像" width="30%">
      <el-form
        ref="editUserFormRef"
        :model="editUserForm"
        label="70px"
        label-position="left"
      >
        <el-form-item label="镜像名称" prop="username">
          <el-input v-model="editUserForm.username" />
        </el-form-item>
        <el-form-item label="版本号" prop="role">
          <el-input v-model="editUserForm.role" />
        </el-form-item>
        <el-form-item label="资源类型" prop="loginName">
          <el-input v-model="editUserForm.loginName" />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button
            type="primary"
            @click="closeEditDialogSubmitForm(editUserFormRef)"
          >
            更新
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
    lastLoginAt: string;
    lastModifyAt: string;
  }
  
  const roleOptions = [
    { value: "漏洞挖掘", label: "漏洞挖掘" },
    { value: "包含漏洞数据镜像", label: "包含漏洞数据镜像" },
    { value: "网络攻击镜像", label: "网络攻击镜像" },
    { value: "网络防御镜像", label: "网络防御镜像" },
    { value: "靶机镜像", label: "靶机镜像" },
  ];
  
  // for debugging
  const users = ref<User[]>([
    {
      username: "test",
      role: "v1",
      loginName: "漏洞挖掘",
      lastLoginAt: "2023-12-02 00:00:00",
      lastModifyAt:"2023-12-02 00:00:00",
    },
    {
      username: "test",
      role: "v2",
      loginName: "靶机镜像",
      lastLoginAt: "2023-12-02 00:00:00",
      lastModifyAt: "2023-12-02 00:00:00",
    },
    {
      username: "",
      role: "",
      loginName: "网络攻击镜像",
      lastLoginAt: "2023-12-02 00:00:00",
      lastModifyAt: "2023-12-02 00:00:00",
    },
    {
      username: "",
      role: "",
      loginName: "网络防御镜像",
      lastLoginAt: "2023-12-02 00:00:00",
      lastModifyAt: "2023-12-02 00:00:00",
    },
    {
      username: "",
      role: "",
      loginName: "包含漏洞数据镜像",
      lastLoginAt: "2023-12-02 00:00:00",
      lastModifyAt: "2023-12-02 00:00:00",
    },
  ]);
  
  const getTime = (): string => {
    const datetime = new Date();
    return `${datetime.getFullYear()}-${datetime.getMonth() + 1}-${datetime.getDate()}`;
  };
  
  // add new user
  const newUserFormRef = ref<FormInstance>();
  const newUserForm = reactive({
    username: "",
    role: "",
    loginName: "",
    lastLoginAt: "",
    lastModifyAt:"",
  });
  
  const addDialogVisible = ref(false);
  const showAddDialog = () => {
    addDialogVisible.value = true;
  };
  const clearNewConfigForm = () => {
    newUserForm.username = "";
    newUserForm.role = "";
    newUserForm.loginName = "";
    newUserForm.lastLoginAt = "";
    newUserForm.lastModifyAt = "";
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
      lastLoginAt: newUserForm.lastLoginAt,
      lastModifyAt: newUserForm.lastModifyAt,
     
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
  };
  
  // delete user
  const deleteRow = (row: User) => {
    userIndex = findUserIndex(row); // update role index
    users.value = users.value.filter(
      (element) => users.value.indexOf(element) !== userIndex
    );
  };
  </script>
  