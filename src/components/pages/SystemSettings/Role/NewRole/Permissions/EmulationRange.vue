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
import { CheckboxValueType } from 'element-plus';

const props = defineProps<{
    range: Boolean,
    container: Boolean,
    mirror: Boolean,
}>();
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
        checkedOptions.value.push('靶场管理');
    if (props.container)
        checkedOptions.value.push('容器管理');
    if (props.mirror)
        checkedOptions.value.push('镜像管理');

    if (checkedOptions.value.length === options.length) {
        isAll.value = true;
        isIndeterminate.value = false;
    } else if (checkedOptions.value.length === 0) {
        isAll.value = false;
        isIndeterminate.value = false;
    } else {
        isAll.value = false;
        isIndeterminate.value = true;
    }
});

const handleAllChange = (val: CheckboxValueType) => {
    checkedOptions.value = val ? options : [] as string[];
    isIndeterminate.value = false;
}
const handleIndeterminateChange = (value: CheckboxValueType[]) => {
    const checkedCount = value.length;
    const allCount = options.length;
    isAll.value = checkedCount === allCount;
    isIndeterminate.value = checkedCount > 0 && checkedCount < allCount;
}
</script>
