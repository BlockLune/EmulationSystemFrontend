<template>
    <el-button type="primary" @click="showAddDialog">新增</el-button>
    <el-dialog v-model="addDialogVisible" title="新增角色" width="30%">
        <el-form ref="newRoleFormRef" :model="newRoleForm" label="70px" label-position="left">
            <el-form-item label="角色名称" prop="name">
                <el-input v-model="newRoleForm.name" placeholder="单行输入" />
            </el-form-item>
            <el-form-item label="权限">
                <All />
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button type="primary" @click="closeAddDialogSubmitForm(newRoleFormRef)">
                    确定
                </el-button>
                <el-button @click="closeAddDialogNoSubmitForm(newRoleFormRef)">取消</el-button>
            </span>
        </template>
    </el-dialog>
</template>

<script lang="ts" setup>
import All from './Permissions/All.vue';
import { ref } from 'vue';

const addDialogVisible = ref(false);
const showAddDialog = () => {
    addDialogVisible.value = true;
};
</script>
