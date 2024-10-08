<template>
  <div class="flex flex-col w-full">
    <!-- banner -->
    <div class="flex flex-row justify-between">
      <!-- 新增用户 -->
      <div>
        <el-button type="primary" @click="addDialogVisible = true"
          >新增</el-button
        >
        <el-dialog v-model="addDialogVisible" title="新增用户" width="30%">
          <el-form
            ref="newUserFormRef"
            :model="newUserForm"
            label-position="left"
            label-width="auto"
          >
            <el-form-item label="用户名" prop="userName">
              <el-input v-model="newUserForm.userName" placeholder="单行输入" />
            </el-form-item>
            <el-form-item label="登录名" prop="loginName">
              <el-input
                v-model="newUserForm.loginName"
                placeholder="单行输入"
              />
            </el-form-item>
            <el-form-item label="角色名称" prop="roleName">
              <el-select-v2
                v-model="newUserForm.roleName"
                :options="options"
                @change="getId"
                placeholder="请选择"
                clearable
              />
            </el-form-item>
            <el-form-item label="权限" prop="auth">
              <el-radio-group v-model="newUserForm.auth">
                <el-radio value="靶场管理">靶场管理</el-radio>
                <el-radio value="容器管理">容器管理</el-radio>
                <el-radio value="镜像管理">镜像管理</el-radio>
                <el-radio value="攻防演练">攻防演练</el-radio>
                <el-radio value="漏洞库管理">漏洞库管理</el-radio>
                <el-radio value="系统管理">系统管理</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input
                v-model="newUserForm.password"
                placeholder="单行输入"
                show-password
              />
            </el-form-item>
          </el-form>
          <template #footer>
            <span class="dialog-footer">
              <el-button type="primary" @click="closeAddDialogSubmitForm()"
                >确定
              </el-button>
              <el-button @click="addDialogVisible = false">取消 </el-button>
            </span>
          </template>
        </el-dialog>
      </div>
      <div class="flex flex-row gap-2">
        <el-form
          ref="queryFormRef"
          :model="queryForm"
          label-position="left"
          class="flex flex-row gap-2"
        >
          <el-form-item label="用户名" prop="imageName">
            <el-input v-model="queryForm.userName" placeholder="单行输入" />
          </el-form-item>
          <el-button
            :disabled="queryForm.userName === ''"
            type="primary"
            @click="query"
            >查询</el-button
          >
        </el-form>
        <el-button @click="listUsers">清空查询结果</el-button>
      </div>
    </div>
    <!-- table -->
    <div class="flex flex-col items-center space-y-2">
      <el-table
        :data="
          users.slice((currentPage - 1) * pageSize, currentPage * pageSize)
        "
        stripe
        style="width: 100%"
      >
        <el-table-column prop="id" label="用户ID"></el-table-column>
        <el-table-column prop="userName" label="用户名"></el-table-column>
        <el-table-column prop="loginName" label="登录名"></el-table-column>
        <el-table-column prop="roleId" label="角色ID"></el-table-column>
        <el-table-column prop="auth" label="权限"></el-table-column>
        <el-table-column prop="password" label="密码"></el-table-column>
        <el-table-column prop="status" label="用户状态" width="200">
          <template #default="{ row }">
            <el-switch
              active-value="1"
              inactive-value="0"
              v-model="row.status"
              active-text="启用"
              inactive-text="禁用"
              @change="changeStatus(row)"
            />
          </template>
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
      <!-- 编辑用户 -->
      <Teleport to="body">
        <el-dialog v-model="editDialogVisible" title="编辑用户" width="30%">
          <el-form
            ref="editUserFormRef"
            :model="editUserForm"
            label="70px"
            label-position="left"
          >
            <el-form-item label="用户ID" prop="id">
              <el-input v-model="editUserForm.id" disabled />
            </el-form-item>
            <el-form-item label="登录名" prop="loginName">
              <el-input v-model="editUserForm.loginName" />
            </el-form-item>
            <el-form-item label="角色ID" prop="roleId">
              <el-input v-model="editUserForm.roleId" />
            </el-form-item>
            <el-form-item label="用户名" prop="userName">
              <el-input v-model="editUserForm.userName" />
            </el-form-item>
          </el-form>
          <template #footer>
            <span class="dialog-footer">
              <el-button type="primary" @click="closeEditDialogSubmitForm()">
                确定
              </el-button>
              <el-button @click="editDialogVisible = false">取消</el-button>
            </span>
          </template>
        </el-dialog>
      </Teleport>
      <el-pagination
        v-model:current-page="currentPage"
        v-model:page-size="pageSize"
        :page-sizes="[10, 20, 30, 40]"
        :small="small"
        :disabled="disabled"
        :background="background"
        layout="total, sizes, prev, pager, next, jumper"
        :total="users.length"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref, reactive, onMounted } from "vue";
import { ElMessage } from "element-plus";
import axiosInstance from "~/utils/axiosInstance";
import type { User } from "~/types";

onMounted(() => {
  listUsers();
  getRoleName();
});

const users = ref<User[]>([]);

const options: { value: string; label: string; id: string }[] = [
  // { value: "0", label: "角色1" },
  // { value: "1", label: "角色2" },
];

const getRoleName = () => {
  axiosInstance({
    method: "get",
    url: "/system/role/listRoles",
  }).then((response) => {
    for (const role of response.data.data) {
      options.push({
        value: role.name,
        label: role.name,
        id: role.id,
      });
    }
    console.log(options);
  });
};

const getId = () => {
  for (const data of options) {
    if (data.value === newUserForm.roleName) {
      newUserForm.roleId = data.id;
    }
  }
  console.log(newUserForm.roleId);
};

const listUsers = () => {
  users.value = [];
  axiosInstance({
    method: "get",
    url: "/system/user/selectByPage/1/10000",
  }).then((response) => {
    for (const user of response.data.data.list) {
      users.value.push(user);
    }
  });
};

const addUser = (loginName: string, roleId: string, userName: string) => {
  axiosInstance({
    method: "post",
    url: "/system/user/createUser",
    data: {
      loginName: loginName,
      roleId: roleId,
      userName: userName,
    },
  }).then((response) => {
    ElMessage(response.data.message);
  });
};

const deleteUser = (userId: string) => {
  axiosInstance({
    method: "post",
    url: "/system/user/deleteUser",
    data: {
      userId: userId,
    },
  }).then((response) => {
    ElMessage(response.data.message);
  });
};

const updateUser = (
  loginName: string,
  roleId: string,
  userId: string,
  userName: string
) => {
  axiosInstance({
    method: "post",
    url: "/system/user/updateUser",
    data: {
      loginName: loginName,
      roleId: roleId,
      userId: userId,
      userName: userName,
    },
  }).then((response) => {
    ElMessage(response.data.message);
  });
};

const changeUserStatus = (status: string, userId: string) => {
  axiosInstance({
    method: "post",
    url: "/system/user/changeUserStatus",
    data: {
      status: status,
      userId: userId,
    },
  }).then((response) => {
    ElMessage(response.data.message);
  });
};

const changeStatus = (row: User) => {
  changeUserStatus(row.status, String(row.id));
};

// add new user
const newUserForm = reactive({
  auth: "",
  createAt: "",
  id: "",
  loginName: "",
  password: "",
  roleId: "",
  roleName: "",
  status: "",
  updateAt: "",
  userName: "",
});

const queryForm = reactive({
  userName: "",
});

const query = () => {
  users.value = users.value.filter((user) =>
    user.userName.includes(queryForm.userName)
  );
};

const addDialogVisible = ref(false);

const closeAddDialogSubmitForm = () => {
  addUser(newUserForm.loginName, newUserForm.roleId, newUserForm.userName);
  window.setTimeout(() => {
    listUsers();
  }, 250);
  newUserForm.loginName = "";
  newUserForm.roleId = "";
  newUserForm.userName = "";
  addDialogVisible.value = false;
};

// edit user
const editUserForm = reactive({
  id: "",
  loginName: "",
  roleId: "",
  userName: "",
});
const editDialogVisible = ref(false);

const showEditDialog = (row: User) => {
  editDialogVisible.value = true;
  editUserForm.loginName = row.loginName;
  editUserForm.roleId = String(row.roleId);
  editUserForm.id = String(row.id);
  editUserForm.userName = row.userName;
};

const closeEditDialogSubmitForm = () => {
  updateUser(
    editUserForm.loginName,
    editUserForm.roleId,
    editUserForm.id,
    editUserForm.userName
  );
  window.setTimeout(() => {
    listUsers();
  }, 250);
  editDialogVisible.value = false;
};

// delete user
const deleteRow = (row: User) => {
  deleteUser(String(row.id));
  window.setTimeout(() => {
    listUsers();
  }, 250);
};

const small = ref(false);
const background = ref(true);
const disabled = ref(false);
const pageSize = ref(10);
const currentPage = ref(1);

const handleSizeChange = (val: number) => {
  pageSize.value = val;
};

const handleCurrentChange = (val: number) => {
  currentPage.value = val;
};
</script>
