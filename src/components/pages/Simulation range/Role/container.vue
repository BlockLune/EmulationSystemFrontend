<template>
    <!-- 新增用户 -->
    <el-button type="primary" @click="showAddDialog">新增靶机容器</el-button>
    <el-dialog v-model="addDialogVisible" title="新增靶机容器" width="30%">
      <!-- TODO: 当前的行为是点击空白处关闭新建框会保留之前的填写记录, 是否要清除? -->
      <el-form ref="newUserFormRef" :model="newUserForm" label-position="left">
        <el-form-item label="容器名称" prop="name">
          <el-input v-model="newUserForm.ContainerName" placeholder="单行输入" />
        </el-form-item>
        <el-form-item label="靶场名称" prop="role">
          <el-input v-model="newUserForm.TargetName" placeholder="单行输入" />
        </el-form-item>
        <el-form-item label="资源类型" prop="StartTime">      //本段所有StartTime待修改
          <el-select-v2
            v-model="newUserForm.StartTime"
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
      <el-table-column prop="ContainerName" label="容器名称"></el-table-column>
      <el-table-column prop="TargetName" label="靶场名称"></el-table-column>
      <el-table-column prop="StartTime" label="容器启动时间"></el-table-column>
      <el-table-column prop="EndTime" label="容器终止时间"></el-table-column>
      <el-table-column prop="LastTime" label="容器持续时间（秒）"></el-table-column>
      <el-table-column prop="status" label="容器状态"></el-table-column>
      <el-table-column prop="mirror" label="关联镜像"></el-table-column>
      <el-table-column prop="type" label="容器类型"></el-table-column>
      <el-table-column prop="attack" label="攻击次数"></el-table-column>
      <el-table-column prop="defence" label="防御次数"></el-table-column>
    </el-table>
  
    <!-- 更新镜像 -->
    <el-dialog v-model="editDialogVisible" title="更新镜像" width="30%">
      <el-form
        ref="editUserFormRef"
        :model="editUserForm"
        label="70px"
        label-position="left"
      >
        <el-form-item label="镜像名称" prop="ContainerName">
          <el-input v-model="editUserForm.ContainerName" />
        </el-form-item>
        <el-form-item label="版本号" prop="TargetName">
          <el-input v-model="editUserForm.TargetName" />
        </el-form-item>
        <el-form-item label="资源类型" prop="loginName">           //StartTime待修改
          <el-input v-model="editUserForm.StartTime" />
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
    ContainerName: string;
    TargetName: string;
    StartTime: string;
    EndTime: string;
    LastTime: string;
    status: string;
    mirror: string;
    type: string;
    attack: string;
    defence: string;
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
      ContainerName: "容器A",
      TargetName: "靶场A",
      StartTime: "2023-12-24 12:00:00",
      EndTime: "2023-12-24 12:05:00",
      LastTime:"300",
      status: "运行中",
      mirror: "",
      type: "漏洞挖掘容器",
      attack: "/",
      defence: "1000",
    },
    {
      ContainerName: "容器B",
      TargetName: "靶场A",
      StartTime: "",
      EndTime: "",
      LastTime: "",
      status: "已停止",
      mirror: "",
      type: "漏洞元数据容器",
      attack: "1500",
      defence: "/",
    },
    {
      ContainerName: "容器C",
      TargetName: "靶场A",
      StartTime: "",
      EndTime: "",
      LastTime: "",
      status: "",
      mirror: "",
      type: "网络攻击容器",
      attack: "2000",
      defence: "/",
    },
    {
      ContainerName: "容器D",
      TargetName: "靶场A",
      StartTime: "",
      EndTime: "",
      LastTime: "",
      status: "",
      mirror: "",
      type: "网络防御容器",
      attack: "/",
      defence: "1500",
    },
    {
      ContainerName: "",
      TargetName: "",
      StartTime: "",
      EndTime: "",
      LastTime: "",
      status: "",
      mirror: "",
      type: "靶机容器",
      attack: "",
      defence: "",
    },
  ]);
  
  const getTime = (): string => {
    const datetime = new Date();
    return `${datetime.getFullYear()}-${datetime.getMonth() + 1}-${datetime.getDate()}`;
  };
  
  // add new user
  const newUserFormRef = ref<FormInstance>();
  const newUserForm = reactive({
    ContainerName: "",
    TargetName: "",
    StartTime: "",
    EndTime: "",
    LastTime: "",
    status: "",
    mirror: "",
    type: "",
    attack: "",
    defence: "",
  });
  
  const addDialogVisible = ref(false);
  const showAddDialog = () => {
    addDialogVisible.value = true;
  };
  const clearNewConfigForm = () => {
    newUserForm.ContainerName = "";
    newUserForm.TargetName = "";
    newUserForm.StartTime = "";
    newUserForm.EndTime = "";
    newUserForm.LastTime = "";
    newUserForm.status = "";
    newUserForm.mirror = "";
    newUserForm.type = "";
    newUserForm.attack = "";
    newUserForm.defence = "";
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
      ContainerName: newUserForm.ContainerName,
      TargetName: newUserForm.TargetName,
      StartTime: newUserForm.StartTime,
      EndTime: newUserForm.EndTime,
      LastTime: newUserForm.LastTime,
      status: newUserForm.status,
      mirror: newUserForm.mirror,
      type: newUserForm.type,
      attack: newUserForm.attack,
      defence: newUserForm.defence,
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
  
  // edit user                                          //以下所有StartTime均待修改！！！！！！！！！！！！！！！！！！！！！！1
  const editUserFormRef = ref<FormInstance>();
  const editUserForm = reactive({
    ContainerName: "",
    TargetName: "",
    StartTime: "",
  });
  const editDialogVisible = ref(false);
  const showEditDialog = (row: User) => {
    editUserForm.ContainerName = row.ContainerName;
    editUserForm.TargetName = row.TargetName;
    editUserForm.StartTime = row.StartTime;
    userIndex = findUserIndex(row); // update role index
    editDialogVisible.value = true;
  };
  const clearEditRoleForm = () => {
    editUserForm.ContainerName = "";
    editUserForm.TargetName = "";
    editUserForm.StartTime = "";
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
    users.value.at(userIndex)!.ContainerName = editUserForm.ContainerName;
    users.value.at(userIndex)!.TargetName = editUserForm.TargetName;
    users.value.at(userIndex)!.StartTime = editUserForm.StartTime;
  };
  
  // delete user
  const deleteRow = (row: User) => {
    userIndex = findUserIndex(row); // update role index
    users.value = users.value.filter(
      (element) => users.value.indexOf(element) !== userIndex
    );
  };
  </script>
  