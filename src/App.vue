<template>
  <Home v-if="showHome" />
  <el-config-provider namespace="ep" v-if="!showHome">
    <BaseHeader />
    <el-container class="main-container">
      <BaseSide @changeDisplay="handleDisplayChange" />
      <el-container class="content-container">
        <!--        <Home v-if="display === '1'" />-->
        <SituationAwareness v-if="display === '2'" />
        <range v-if="display === '3-4'" />
        <LoopHoleDig v-if="display === '3-5'" />
        <Task v-if="display === '3-6'" />
        <container v-if="display === '3-2'" />
        <Mirror v-if="display === '3-1'" />
        <AttackDefenceExercise v-if="display === '3-3'" />
        <LoopHoleController v-if="display === '5'" />
        <Role v-if="display === '6-1'" />
        <User v-if="display === '6-2'" />
        <Config v-if="display === '6-3'" />
      </el-container>
    </el-container>
  </el-config-provider>
</template>

<style>
#app {
  color: var(--ep-text-color-primary);
}
</style>

<script lang="ts" setup>
import { ref, provide } from "vue";
import { showHome } from "~/services/api";

const display = ref("2");
const handleDisplayChange = (value: string) => {
  display.value = value;
};
// const showHome = ref(sessionStorage.getItem('Authorization') === null);
const EMULATION_SYSTEM_NAME = ref<string>("边缘设备检测及近场通信系统");
provide("EMULATION_SYSTEM_NAME", EMULATION_SYSTEM_NAME);
</script>
