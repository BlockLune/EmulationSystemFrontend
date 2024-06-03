<template>
  <div class="flex flex-col w-full gap-2">
    <!-- 新增角色 -->
    <div class="flex flex-row">
      <el-button type="primary" @click="addDialogVisible = true"
        >新增</el-button
      >
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
    </div>

    <!-- 表格 -->
    <div class="flex flex-col w-full gap-2">
      <el-table
        :data="
          roles.slice((currentPage - 1) * pageSize, currentPage * pageSize)
        "
        stripe
      >
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

      <!--分页-->
      <div
        class="flex flex-row justify-center items-center"
        v-if="roles.length > pageSize"
      >
        <el-pagination
          v-model:current-page="currentPage"
          v-model:page-size="pageSize"
          :page-sizes="[5, 10, 20, 30, 40]"
          :small="small"
          :disabled="disabled"
          :background="background"
          layout="total, sizes, prev, pager, next, jumper"
          :total="roles.length"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        />
      </div>
    </div>
  </div>

  <!-- 编辑角色 -->
  <el-dialog v-model="editDialogVisible" title="编辑角色" width="30%">
    <el-form
      ref="editRoleFormRef"
      :model="editRoleForm"
      label="70px"
      label-position="left"
    >
      <el-form-item label="角色名称" prop="name">
        <el-input v-model="editRoleForm.name" />
      </el-form-item>
      <el-form-item label="权限">
        <el-radio-group v-model="editRoleForm.auth">
          <el-radio value="仿真靶场管理">仿真靶场管理</el-radio>
          <el-radio value="靶场管理">靶场管理</el-radio>
          <el-radio value="容器管理">容器管理</el-radio>
          <el-radio value="镜像管理">镜像管理</el-radio>
          <!--          <emulation-range-checkbox-group />-->
          <el-radio value="攻防演练">攻防演练</el-radio>
          <el-radio value="漏洞库管理">漏洞库管理</el-radio>
          <el-radio value="系统管理">系统管理</el-radio>
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
import axios from "axios";
import { onMounted, ref, reactive } from "vue";
import { ElMessage } from "element-plus";
import JSONBIG from "json-bigint";

onMounted(() => {
  listRoles();
});

interface Role {
  auth: string;
  createTime: string;
  id: string;
  name: string;
  updateTime: string;
}

axios.defaults.transformResponse = [
  function (data) {
    const json = JSONBIG({
      storeAsString: true,
    });
    const res = json.parse(data);
    return res;
  },
];

const roles = ref<Role[]>([]);

const listRoles = () => {
  roles.value = [];
  axios({
    headers: {
      Authorization: localStorage.getItem("Authorization"),
    },
    method: "get",
    url: "/system/role/listRoles",
  }).then((response) => {
    for (const role of response.data.data) {
      roles.value.push(role);
    }
  });
};

const deleteRole = (roleId: string) => {
  axios({
    method: "post",
    url: "/system/role/deleteRole",
    headers: {
      Authorization: localStorage.getItem("Authorization"),
    },
    data: {
      roleId: roleId,
    },
  }).then((response) => {
    ElMessage(response.data.message);
  });
};

const addRole = (roleName: string, auth: string) => {
  axios({
    method: "post",
    url: "/system/role/createRole",
    headers: {
      Authorization: localStorage.getItem("Authorization"),
    },
    data: {
      roleName: roleName,
      auth: auth,
    },
  }).then((response) => {
    ElMessage(response.data.message);
  });
};

const updateRole = (auth: string, roleId: string, roleName: string) => {
  axios({
    method: "post",
    url: "/system/role/updateRole",
    headers: {
      Authorization: localStorage.getItem("Authorization"),
    },
    data: {
      auth: auth,
      roleId: Number(roleId),
      roleName: roleName,
    },
  }).then((response) => {
    ElMessage(response.data.message);
  });
};

const addDialogVisible = ref(false);

const editDialogVisible = ref(false);

const newRoleForm = reactive({
  name: "",
  auth: "",
});

const editRoleForm = reactive({
  name: "",
  auth: "",
});

let rowData: Role;
const showEditDialog = (row: Role) => {
  editDialogVisible.value = true;
  editRoleForm.name = row.name;
  editRoleForm.auth = row.auth;
  rowData = row;
};

const closeEditDialogSubmitForm = () => {
  updateRole(editRoleForm.auth, rowData.id, editRoleForm.name);
  window.setTimeout(() => {
    listRoles();
  }, 250);
  editDialogVisible.value = false;
};

const closeAddDialogSubmitForm = () => {
  addRole(newRoleForm.name, newRoleForm.auth);
  window.setTimeout(() => {
    listRoles();
  }, 250);
  newRoleForm.name = "";
  newRoleForm.auth = "";
  addDialogVisible.value = false;
};

const deleteRow = (row: Role) => {
  deleteRole(row.id);
  window.setTimeout(() => {
    listRoles();
  }, 250);
};

const small = ref(false);
const background = ref(true);
const disabled = ref(false);
const pageSize = ref(5);
const currentPage = ref(1);

const handleSizeChange = (val: number) => {
  pageSize.value = val;
};

const handleCurrentChange = (val: number) => {
  currentPage.value = val;
};
</script>
