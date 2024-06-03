<template>
  <Home v-if="showHome" />
  <el-config-provider namespace="ep" v-if="!showHome">
    <BaseHeader />
    <el-container>
      <BaseSide @changeDisplay="handleDisplayChange" />
      <el-container class="p-4">
        <RouterView />
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
import { RouterView } from "vue-router";
import { showHome } from "./services/api";

const EMULATION_SYSTEM_NAME = ref<string>("边缘设备检测及近场通信系统");
provide("EMULATION_SYSTEM_NAME", EMULATION_SYSTEM_NAME);
</script>
