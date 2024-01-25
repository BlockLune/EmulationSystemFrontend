<template>
    <el-form ref="newRoleFormRef" label-position="left">
        <el-form-item label="角色名称">
            <el-input v-model="newRoleForm.name" placeholder="单行输入" />
        </el-form-item>
        <el-form-item label="权限">
            <All permissions="nameRoleForm.permissions" />
        </el-form-item>
    </el-form>
</template>

<script lang="ts" setup>
import All from './Permissions/All.vue';
import { reactive, ref } from 'vue'
import type { FormProps } from 'element-plus'

const newRoleFormRef = ref<FormInstance>();
const newRoleForm = reactive({
    name: "",
    permissions: [] as string[],
});
</script>
