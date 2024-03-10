<template>
  <!-- 新增用户 -->
  <el-button type="primary" @click="showAddDialog">新增</el-button>
  <el-dialog v-model="addDialogVisible" title="新增演练" width="30%">
    <!-- TODO: 当前的行为是点击空白处关闭新建框会保留之前的填写记录, 是否要清除? -->
    <el-form ref="newUserFormRef" :model="newUserForm" label-position="left">
      <el-form-item label="演练名称" prop="name">
        <el-input v-model="newUserForm.username" placeholder="单行输入" />
      </el-form-item>
      <el-form-item label="演练靶机" prop="role">
        <el-select-v2
          v-model="newUserForm.role"
          :options="roleOptions1"
          placeholder="请选择"
          style="width: 300px"
        />
      </el-form-item>
      <el-form-item label="演练启动时间" prop="exerciseStartTime">
        <el-input v-model="newUserForm.exerciseStartTime" placeholder="单行输入" />
      </el-form-item>
      <el-form-item label="演练停止时间" prop="exerciseStopTime">
        <el-input v-model="newUserForm.exerciseStopTime" placeholder="单行输入" />
      </el-form-item>
      <el-form-item label="演练状态" prop="status">
        <el-select-v2
          v-model="newUserForm.status"
          :options="roleOptions4"
          placeholder="请选择"
          style="width: 300px"
        />
      </el-form-item>
      <el-form-item label="攻击流程" prop="attackFlow">
      </el-form-item>
      <el-form-item label="攻击镜像源" prop="attackMirrorSource">
        <el-select-v2
          v-model="newUserForm.attackMirrorSource"
          :options="roleOptions2"
          placeholder="请选择"
          style="width: 300px"
        />
      </el-form-item>
      <el-form-item label="攻击启动时间" prop="attackStartTime">
        <el-input v-model="newUserForm.attackStartTime" placeholder="单行输入" />
      </el-form-item>
      <el-form-item label="攻击停止时间" prop="attackStopTime">
        <el-input v-model="newUserForm.attackStopTime" placeholder="单行输入" />
      </el-form-item>
      <el-form-item label="防御镜像源" prop="defenceMirrorSource">
        <el-select-v2
          v-model="newUserForm.defenceMirrorSource"
          :options="roleOptions3"
          placeholder="请选择"
          style="width: 300px"
        />
      </el-form-item>
      <el-form-item label="防御启动时间" prop="defenceStartTime">
        <el-input v-model="newUserForm.defenceStartTime" placeholder="单行输入" />
      </el-form-item>
      <el-form-item label="防御停止时间" prop="adefenceStopTime">
        <el-input v-model="newUserForm.defenceStopTime" placeholder="单行输入" />
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
    <el-table-column prop="username" label="演练名称"></el-table-column>
    <el-table-column prop="role" label="演练靶机"></el-table-column>
    <el-table-column prop="exerciseStartTime" label="演练启动时间"></el-table-column>
    <el-table-column prop="exerciseStopTime" label="靶场停止时间"></el-table-column>
    <el-table-column prop="status" label="演练状态"></el-table-column>
    <el-table-column prop="attackMirrorSource" label="攻击源"></el-table-column>
    <el-table-column prop="attackNumbers" label="攻击次数"></el-table-column>
    <el-table-column prop="defenceMirrorSource" label="防御源"></el-table-column>
    <el-table-column prop="defenceNumbers" label="防御次数"></el-table-column>
    <el-table-column label="操作">
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
</template>

<script lang="ts" setup>
import { ref, reactive } from "vue";
import { ElSelect, FormInstance } from "element-plus";

interface User {
  username: string;
  role: string;
  status: string;
  exerciseStartTime: string;
  exerciseStopTime: string;
  attackMirrorSource: string;
  attackStartTime: string;
  attackStopTime: string;
  attackNumbers: string;
  defenceMirrorSource: string;
  defenceStartTime: string;
  defenceStopTime: string;
  defenceNumbers: string;
}

const roleOptions1 = [
  { value: "靶机容器A", label: "靶机容器A" },
  { value: "靶机容器B", label: "靶机容器B" },
];
const roleOptions2 = [
  { value: "攻击镜像源", label: "攻击镜像源" },
];
const roleOptions3 = [
  { value: "防御镜像源", label: "防御镜像源" },
];
const roleOptions4 = [
  { value: "未开始", label: "未开始" },
  { value: "进行中", label: "进行中" },
  { value: "已停止", label: "已停止" },
];
// for debugging
const users = ref<User[]>([
  {
    status:"未开始"
  },
  {
    status:"进行中"
  },
  {
    status:"已停止"
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
  exerciseStartTime:"",
  exerciseStopTime: "",
  status: "",
  attackMirrorSource:"",
  attackNumbers: "",
  defenceMirrorSource:"",
  defenceNumbers: "",
});

const addDialogVisible = ref(false);
const showAddDialog = () => {
  addDialogVisible.value = true;
};
const clearNewConfigForm = () => {
  newUserForm.username = "";
  newUserForm.role = "";
  newUserForm.exerciseStartTime = "";
  newUserForm.exerciseStopTime = "";
  newUserForm.status = "";
  newUserForm.attackMirrorSource = "";
  newUserForm.attackNumbers = "";
  newUserForm.defenceMirrorSource = "";
  newUserForm.defenceNumbers = "";
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
    exerciseStartTime: newUserForm.exerciseStartTime,
    exerciseStopTime: newUserForm.exerciseStopTime,
    status: newUserForm.status,
    attackMirrorSource: newUserForm.attackMirrorSource,
    attackNumbers: newUserForm.attackNumbers,
    defenceMirrorSource: newUserForm.defenceMirrorSource,
    defenceNumbers: newUserForm.defenceNumbers,
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
// delete user
const deleteRow = (row: User) => {
  userIndex = findUserIndex(row); // update role index
  users.value = users.value.filter(
    (element) => users.value.indexOf(element) !== userIndex
  );
};
</script>

