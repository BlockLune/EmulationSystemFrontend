<template>
    <!-- 新增角色 -->
    <el-button type="primary" @click="showAddDialog">新增</el-button>
    <el-dialog
        v-model="addDialogVisible"
        title="新增角色"
        width="30%"
    >
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
                <!-- <el-checkbox v-model=""></el-checkbox> -->
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button type="primary" @click="closeAddDialogSubmit(newRoleFormRef)">
                    确定
                </el-button>
                <el-button @click="closeAddDialogNoSubmit()">取消</el-button>
            </span>
        </template>
    </el-dialog>



    <!-- 表格主体 -->
    <el-table :data="roles" stripe style="width: 100%">
        <el-table-column prop="name" label="角色名称"></el-table-column>
        <el-table-column prop="permissions" label="权限">
        <template #default="{ row }">
            <span>{{ formatPermissions(row.permissions) }}</span>
        </template>
        </el-table-column>
        <el-table-column prop="createdAt" label="创建时间"></el-table-column>
        <el-table-column prop="updatedAt" label="更新时间"></el-table-column>
        <el-table-column label="操作">
        <template #default="{ row }">
            <el-button link type="primary" @click="editRole(row)">编辑</el-button>
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
</template>

<script lang="ts" setup>
import { ref, reactive } from 'vue';
import { FormInstance } from 'element-plus';

interface Role {
  name: string;
  permissions: string[];
  createdAt: string;
  updatedAt: string;
}

interface Permission {
    name: string;
    allowed: boolean;
}

const permissions = ref<Permission[]>([
    {name: '仿真靶场管理', allowed: false}
])

const addDialogVisible = ref(false);
const showAddDialog = () => {
    addDialogVisible.value = true;
}
const closeAddDialogNoSubmit = () => {
    addDialogVisible.value = false;
    console.log("closeAddDialogNoSubmit() called.");
}
const closeAddDialogSubmit = (formEl: FormInstance | undefined) => {
    closeAddDialogNoSubmit();
    if (!formEl) return;
    console.log("closeAddDialogSubmit() called.")
    console.log(newRoleForm.name);
}

const getTime = () => {
    const datetime = new Date();
    return `${datetime.getFullYear()}-${datetime.getMonth()}-${datetime.getDate()}`;
}

const newRoleFormRef = ref<FormInstance>();
const newRoleForm = reactive({
    name: '',
    createdAt: getTime(),
    updatedAt: getTime()
});

const roles = ref<Role[]>([
  {
    name: '角色A',
    permissions: ['仿真管理'],
    createdAt: '2023-12-31',
    updatedAt: '2024-01-01'
  },
  {
    name: '角色B',
    permissions: ['攻防演练', '系统管理'],
    createdAt: '2023-12-30',
    updatedAt: '2024-01-02'
  }
]);
function editRole(role: Role) {
  console.log('编辑角色:', role);
}

const deleteRow = (role: Role) => {
    console.log('删除: ', role)
}

function formatPermissions(permissions: string[]): string {
  return permissions.join('、');
}
</script>