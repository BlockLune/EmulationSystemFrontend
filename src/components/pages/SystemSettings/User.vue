<template>
  <div class="flex flex-col w-full gap-2">
    <!-- 新增用户 -->
    <div class="flex flex-row">
      <el-button type="primary" @click="addDialogVisible = true"
        >新增</el-button
      >
      <el-dialog v-model="addDialogVisible" title="新增用户" width="30%">
        <!-- TODO: 当前的行为是点击空白处关闭新建框会保留之前的填写记录, 是否要清除? -->
        <el-form
          ref="newUserFormRef"
          :model="newUserForm"
          label-position="left"
        >
          <el-form-item label="登录名" prop="loginName">
            <el-input v-model="newUserForm.loginName" placeholder="单行输入" />
          </el-form-item>
          <el-form-item label="角色ID" prop="roleId">
            <el-input v-model="newUserForm.roleId" placeholder="单行输入" />
          </el-form-item>
          <el-form-item label="用户名" prop="userName">
            <el-input v-model="newUserForm.userName" placeholder="单行输入" />
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

    <!-- 表格主体 -->
    <div class="flex flex-col w-full gap-2">
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

      <!--分页-->
      <div
        class="flex flex-row justify-center items-center"
        v-if="users.length > pageSize"
      >
        <el-pagination
          v-model:current-page="currentPage"
          v-model:page-size="pageSize"
          :page-sizes="[5, 10, 20, 30, 40]"
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
  </div>
</template>

<script lang="ts" setup>
import { ref, reactive, onMounted } from "vue";
import { ElMessage } from "element-plus";
import axios from "axios";

onMounted(() => {
  listUsers();
});

interface User {
  auth: string;
  createTime: string;
  id: string;
  loginName: string;
  password: string;
  roleId: string;
  status: string;
  updateTime: string;
  userName: string;
}

const users = ref<User[]>([]);

const listUsers = () => {
  users.value = [];
  axios({
    headers: {
      Authorization: localStorage.getItem("Authorization"),
    },
    method: "get",
    url: "/system/user/selectByPage/1/10000",
  }).then((response) => {
    for (const user of response.data.data.list) {
      users.value.push(user);
    }
  });
};

const addUser = (loginName: string, roleId: string, userName: string) => {
  axios({
    method: "post",
    url: "/system/user/createUser",
    headers: {
      Authorization: localStorage.getItem("Authorization"),
    },
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
  axios({
    method: "post",
    url: "/system/user/deleteUser",
    headers: {
      Authorization: localStorage.getItem("Authorization"),
    },
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
  axios({
    method: "post",
    url: "/system/user/updateUser",
    headers: {
      Authorization: localStorage.getItem("Authorization"),
    },
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
  axios({
    method: "post",
    url: "/system/user/changeUserStatus",
    headers: {
      Authorization: localStorage.getItem("Authorization"),
    },
    data: {
      status: status,
      userId: userId,
    },
  }).then((response) => {
    ElMessage(response.data.message);
  });
};

const changeStatus = (row: User) => {
  changeUserStatus(row.status, row.id);
};

// add new user
const newUserForm = reactive({
  auth: "",
  createTime: "",
  id: "",
  loginName: "",
  password: "",
  roleId: "",
  status: "",
  updateTime: "",
  userName: "",
});

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
  editUserForm.roleId = row.roleId;
  editUserForm.id = row.id;
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
  deleteUser(row.id);
  window.setTimeout(() => {
    listUsers();
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
