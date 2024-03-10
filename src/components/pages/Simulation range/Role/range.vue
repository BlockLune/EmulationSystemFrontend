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
      <el-table-column prop="range name" label="靶场名称"></el-table-column>
      <el-table-column prop="attack&defence name" label="攻防演练名称"></el-table-column>
      <el-table-column prop="range start time" label="靶场启动时间"></el-table-column>
      <el-table-column prop="range end time" label="靶场终止时间"></el-table-column>
      <el-table-column prop="range status" label="靶场状态"></el-table-column>
      <el-table-column prop="number of vulnerabilities discovered" label="挖掘漏洞数量"></el-table-column>
      <el-table-column prop="attack times" label="攻击次数"></el-table-column>
      <el-table-column prop="effective defense times" label="有效防御次数"></el-table-column>
      <el-table-column prop="defense ratio" label="防御比例"></el-table-column>
      
    </el-table>
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
  
  
  </script>
  