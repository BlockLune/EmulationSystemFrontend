<template>
  <el-config-provider namespace="ep">
    <BaseHeader />
    <el-container class="main-container">
      <BaseSide @changeDisplay="handleDisplayChange" />
      <el-container class="content-container">
        <Home v-if="display === '1'" />
        <range v-if="display === '3-4'" />
        <LoopHoleDig v-if="display === '3-5'" />
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

.main-container {
  height: calc(100vh - var(--ep-menu-item-height) - 3px);
}

.content-container {
  padding: 20px;
}
</style>

<script lang="ts" setup>
import { ref, provide } from "vue";

const display = ref("");
const handleDisplayChange = (value: string) => {
  display.value = value;
};

const EMULATION_SYSTEM_NAME = ref<string>("边缘设备检测及近场通信系统");
provide("EMULATION_SYSTEM_NAME", EMULATION_SYSTEM_NAME);
</script>
