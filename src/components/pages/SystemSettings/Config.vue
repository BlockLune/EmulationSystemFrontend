<template>
  <!-- 新增配置 -->
  <el-button type="primary" @click="showAddDialog">新增</el-button>
  <el-dialog v-model="addDialogVisible" title="新增配置" width="30%">
    <!-- TODO: 当前的行为是点击空白处关闭新建框会保留之前的填写记录, 是否要清除? -->
    <el-form
      ref="newConfigFormRef"
      :model="newConfigForm"
      label="70px"
      label-position="left"
    >
      <el-form-item label="配置项" prop="key">
        <el-input v-model="newConfigForm.key" placeholder="单行输入" />
      </el-form-item>
      <el-form-item label="配置值" prop="value">
        <el-input v-model="newConfigForm.value" placeholder="单行输入" />
      </el-form-item>
      <el-form-item label="备注" prop="note">
        <el-input
          v-model="newConfigForm.note"
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
          @click="closeAddDialogSubmitForm(newConfigFormRef)"
        >
          确定
        </el-button>
        <el-button @click="closeAddDialogNoSubmitForm(newConfigFormRef)"
          >取消</el-button
        >
      </span>
    </template>
  </el-dialog>

  <!-- 表格主体 -->
  <el-table :data="configs" stripe style="width: 100%">
    <el-table-column prop="key" label="配置项"></el-table-column>
    <el-table-column prop="value" label="配置值"></el-table-column>
    <el-table-column prop="note" label="备注"></el-table-column>
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
      ref="editConfigFormRef"
      :model="editConfigForm"
      label="70px"
      label-position="left"
    >
      <el-form-item label="配置值" prop="key">
        <el-input v-model="editConfigForm.key" disabled />
      </el-form-item>
      <el-form-item label="配置项" prop="value">
        <el-input v-model="editConfigForm.value" />
      </el-form-item>
      <el-form-item label="备注" prop="note">
        <el-input v-model="editConfigForm.note" :rows="4" type="textarea" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button
          type="primary"
          @click="closeEditDialogSubmitForm(editConfigFormRef)"
        >
          确定
        </el-button>
        <el-button @click="closeEditDialogNoSubmitForm(editConfigFormRef)"
          >取消</el-button
        >
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import { ref, reactive } from "vue";
import { FormInstance } from "element-plus";
import { config } from "process";

interface Config {
  key: string;
  value: number;
  note: string;
  createdAt: string;
  updatedAt: string;
}

// for debugging
const configs = ref<Config[]>([
  {
    key: "example_key",
    value: 7,
    note: "",
    createdAt: "2023-12-31",
    updatedAt: "2024-01-01",
  },
]);

const getTime = (): string => {
  const datetime = new Date();
  return `${datetime.getFullYear()}-${datetime.getMonth() + 1}-${datetime.getDate()}`;
};

// add new config
const newConfigFormRef = ref<FormInstance>();
const newConfigForm = reactive({
  key: "",
  value: 0,
  note: "",
});

const addDialogVisible = ref(false);
const showAddDialog = () => {
  addDialogVisible.value = true;
};
const clearNewConfigForm = () => {
  newConfigForm.key = "";
  newConfigForm.value = 0;
  newConfigForm.note = "";
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
  const newConfig: Config = {
    key: newConfigForm.key,
    value: newConfigForm.value,
    note: newConfigForm.note,
    createdAt: getTime(),
    updatedAt: getTime(),
  };
  configs.value.push(newConfig);
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

let configIndex = -1;
const findConfigIndex = (row: Config): number => {
  const configForFinding: Config = {
    key: row.key,
    value: row.value,
    note: row.note,
    createdAt: row.createdAt,
    updatedAt: row.updatedAt,
  };
  for (let i = 0; i < configs.value.length; ++i) {
    const curConfig = configs.value.at(i);
    if (deepEqual<Config>(curConfig!, configForFinding)) {
      configIndex = i;
      break;
    }
  }
  console.log(configIndex);
  return configIndex;
};

// edit config
const editConfigFormRef = ref<FormInstance>();
const editConfigForm = reactive({
  key: "",
  value: 0,
  note: "",
});
const editDialogVisible = ref(false);
const showEditDialog = (row: Config) => {
  editConfigForm.key = row.key;
  editConfigForm.value = row.value;
  editConfigForm.note = row.note;
  configIndex = findConfigIndex(row); // update role index
  editDialogVisible.value = true;
};
const clearEditRoleForm = () => {
  editConfigForm.key = "";
  editConfigForm.value = 0;
  editConfigForm.note = "";
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
  configs.value.at(configIndex)!.value = editConfigForm.value;
  configs.value.at(configIndex)!.note = editConfigForm.note;
  configs.value.at(configIndex)!.updatedAt = getTime();
};

// delete role
const deleteRow = (row: Config) => {
  configIndex = findConfigIndex(row); // update role index
  configs.value = configs.value.filter(
    (element) => configs.value.indexOf(element) !== configIndex
  );
};
</script>
