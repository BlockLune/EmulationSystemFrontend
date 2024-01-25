<template>
    <EmulationRange />
    <el-checkbox v-model="permissionsStatus.AttackDefense" @change="handleChange">攻防演练</el-checkbox>
    <el-checkbox v-model="permissionsStatus.Loopholes" @change="handleChange">漏洞库管理</el-checkbox>
    <el-checkbox v-model="permissionsStatus.System" @change="handleChange">系统管理</el-checkbox>
</template>

<script lang="ts" setup>
import EmulationRange from './EmulationRange.vue';
import { ref, toRefs, reactive } from 'vue';

const props = defineProps <
    permissions: string[],
> ();

const permissionsStatus = reactive({
    AttackDefense: false,
    Loopholes: false,
    System: false,
});

const handleChange = () => {
    console.log(permissionsStatus);
};

</script>
