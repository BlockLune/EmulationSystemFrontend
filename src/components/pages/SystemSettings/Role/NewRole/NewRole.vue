<template>
  <el-button type="primary" @click="showAddDialog">新增</el-button>
  <el-dialog v-model="addDialogVisible" title="新增角色" width="30%">
    <el-form ref="newRoleFormRef" label-position="left">
      <el-form-item label="角色名称">
        <el-input v-model="newRoleForm.name" placeholder="单行输入" />
      </el-form-item>
      <el-form-item label="权限">
        <All
          :permissions="newRoleForm.permissions"
          @update:permissions="
            (range, container, mirror, attackDefense, loopholes, system) => {
              newRoleForm.permissions.length = 0;
              if (range && container && mirror)
                newRoleForm.permissions.push('仿真靶场管理');
              if (range) newRoleForm.permissions.push('靶场管理');
              if (container) newRoleForm.permissions.push('容器管理');
              if (mirror) newRoleForm.permissions.push('镜像管理');
              if (attackDefense) newRoleForm.permissions.push('攻防演练');
              if (loopholes) newRoleForm.permissions.push('漏洞管理');
              if (system) newRoleForm.permissions.push('系统管理');
            }
          "
        />
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
</template>

<script lang="ts" setup>
import All from "./Permissions/All.vue";
import { reactive, ref } from "vue";
import { FormInstance } from "element-plus";

interface Role {
  name: string;
  permissions: string[];
  createdAt: string;
  updatedAt: string;
}

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
const newRoleFormRef = ref<FormInstance>();
const newRoleForm = reactive({
  name: "",
  permissions: [] as string[],
});

const getTime = (): string => {
  const datetime = new Date();
  return `${datetime.getFullYear()}-${datetime.getMonth() + 1}-${datetime.getDate()}`;
};

const formatPermissions = (permissions: string[]): string => {
  return permissions.join("、");
};

const addDialogVisible = ref(false);
const showAddDialog = () => {
  addDialogVisible.value = true;
};
const clearNewRoleForm = () => {
  newRoleForm.name = "";
  newRoleForm.permissions = [];
};
const clearAddDialog = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.resetFields();
  clearNewRoleForm();
};
const closeAddDialogNoSubmitForm = (formEl: FormInstance | undefined) => {
  addDialogVisible.value = false;
  //console.log("closeAddDialogNoSubmitForm() called.");
  if (!formEl) return;
  clearAddDialog(formEl);
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
  clearAddDialog(formEl);
};
</script>
