<!-- 仿真靶场管理相关权限多选框组 -->
<template>
    <el-checkbox v-model="checkAll" :indeterminate="isIndeterminate" @change="handleCheckAllChange">仿真靶场管理</el-checkbox>
    <el-checkbox-group v-model="checkedOptions" @change="handleCheckedOptionsChange">
        <el-checkbox v-for="option in allOptions" :key="option" :label="option">{{ option }}</el-checkbox>
    </el-checkbox-group>
</template>

<script lang="ts" setup>
import { CheckboxValueType } from 'element-plus';
import { ref } from 'vue'

const checkAll = ref(false);
const isIndeterminate = ref(false);

const checkedOptions = ref([] as string[]);
const allOptions = ['靶场管理', '容器管理', '镜像管理'];

const handleCheckAllChange = (val: CheckboxValueType) => {
    checkedOptions.value = val ? allOptions : [] as string[];
    isIndeterminate.value = false;
}
const handleCheckedOptionsChange = (value: CheckboxValueType[]) => {
    const checkedCount = value.length;
    const allCount = allOptions.length;
    isIndeterminate.value = checkedCount > 0 && checkedCount < allCount;
    checkAll.value = checkedCount === allCount;
}
</script>
