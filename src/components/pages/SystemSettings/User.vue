<template>
  <div>
    <el-button type="primary" @click="showAddDialog">新增</el-button>
    <el-dialog v-model="addDialogVisible" title="新增用户" width="30%">
      <el-form ref="newUserFormRef" :model="newUserForm" label="70px" label-position="left">
        <el-form-item label="用户名称" prop="userName">
          <el-input v-model="newUserForm.userName" placeholder="单行输入" />
        </el-form-item>
        <el-form-item label="角色名称" prop="role">
          <el-radio-group v-model="newUserForm.role">
            <el-radio label="管理员">管理员</el-radio>
            <el-radio label="测试员">测试员</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="登录名称" prop="loginName">
          <el-input v-model="newUserForm.loginName" placeholder="单行输入" />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" @click="closeAddDialogSubmitForm(newUserFormRef)">确定</el-button>
          <el-button @click="closeAddDialogNoSubmitForm(newUserFormRef)">取消</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      addDialogVisible: false,
      newUserForm: {
        userName: '',
        role: '',
        loginName: ''
      }
    };
  },
  methods: {
    showAddDialog() {
      this.addDialogVisible = true;
    },
    closeAddDialogSubmitForm(formRef) {
      formRef.validate((valid) => {
        if (valid) {
          // TODO: 处理提交逻辑
          console.log('提交表单', this.newUserForm);
          this.addDialogVisible = false;
          // 清空表单
          this.$nextTick(() => {
            formRef.resetFields();
          });
        }
      });
    },
    closeAddDialogNoSubmitForm(formRef) {
      // 清空表单
      formRef.resetFields();
      this.addDialogVisible = false;
    }
  }
};
</script>


  <!-- 表格主体 -->
  <el-table :data="users" stripe style="width: 100%">
    <el-table-column prop="userName" label="用户名称"></el-table-column>
    <el-table-column prop="roleName" label="角色名称"></el-table-column>
    <el-table-column prop="loginName" label="登录名称"></el-table-column>
    <el-table-column prop="userStatus" label="用户状态">
      <template #default="{ row }">
        <span>{{ row.userStatus === 'enabled' ? '启用' : '禁用' }}</span>
      </template>
    </el-table-column>
    <el-table-column prop="updatedAt" label="最近更新时间">
      <template #default="{ row }">
        <span>{{ formatUpdateTime(row.updatedAt) }}</span>
      </template>
    </el-table-column>
    <el-table-column label="操作">
  <template #default="{ row }">
    <el-button link type="primary" @click="showEditDialog(row)">
      编辑
    </el-button>
    <el-popconfirm
      title="确认删除？"
      confirm-button-text="确认"
      cancel-button-text="取消"
      @confirm="deleteRow(row)"
    >
      <template #reference>
        <el-button v-if="row.userStatus === 'enabled'" link type="danger">
          删除
        </el-button>
        <el-button v-else link type="danger" disabled>
          删除
        </el-button>
      </template>
    </el-popconfirm>
    <el-button
      link
      :type="row.userStatus === 'enabled' ? 'danger' : 'success'"
      @click="toggleUserStatus(row)"
    >
      {{ row.userStatus === 'enabled' ? '禁用' : '启用' }}
    </el-button>
  </template>
</el-table-column>

  </el-table>
  </template>
