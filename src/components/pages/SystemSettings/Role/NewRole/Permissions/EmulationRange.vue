
import { progressProps } from 'element-plus';

import { progressProps } from 'element-plus';

import { progressProps } from 'element-plus';

import { valueEquals } from 'element-plus';

import { valueEquals } from 'element-plus';

import { onBeforeMount } from 'vue';

import { onBeforeMount } from 'vue';
<template>
    <el-checkbox v-model="isAll" :indeterminate="isIndeterminate" @change="handleAllChange">仿真靶场管理</el-checkbox>
    <el-checkbox-group v-model="checkedOptions" @change="handleIndeterminateChange">
        <el-checkbox v-for="option in options" :key="option" :label="option">{{
            option
        }}</el-checkbox>
    </el-checkbox-group>
</template>

<script lang="ts" setup>
import { ref, defineProps, defineEmits, onBeforeMount } from 'vue';

const props = defineProps({
    range: boolean,
    container: boolean,
    mirror: boolean,
});
const emit = defineEmits([
    'update:range',
    'update:container',
    'update:mirror',
]);
const updateRange = (value: boolean) => {
    emit('update:range', value);
};
const updateContainer = (value: boolean) => {
    emit('update:container', value);
};
const updateMirror = (value: boolean) => {
    emit('update:mirror', value);
};

const isAll = ref(false);
const isIndeterminate = ref(false);
const checkedOptions = ref([] as string[]);
const options: string[] = ['靶场管理', '容器管理', '镜像管理'];

onBeforeMount(() => {
    if (props.range)
        checkedOptions.value.append('靶场管理');
    if (props.container)
        checkedOptions.value.append('容器管理');
    if (props.mirror)
        checkedOptions.value.append('镜像管理');

    if checkedOptions.value

});

const handleAllChange = (val: boolean) => {
    checkedOptions.value = val ? options : [] as string[];
    isIndeterminate.value = false;
}
const handleIndeterminateChange = (value: string[]) => {
    const checkedCount = value.length;
    const allCount = options.length;
    isAll.value = checkedCount === allCount;
    isIndeterminate.value = checkedCount > 0 && checkedCount < allCount;
}
</script>
