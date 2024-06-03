<template>
  <el-config-provider namespace="ep">
    <BaseHeader />
    <el-container
      style="height: calc(100vh - var(--ep-menu-item-height) - 3px)"
    >
      <div class="flex flex-row">
        <BaseSide @changeDisplay="handleDisplayChange" />
        <div class="flex-grow">
          <RouterView />
        </div>
      </div>
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

const display = ref("");
const handleDisplayChange = (value: string) => {
  display.value = value;
};

const EMULATION_SYSTEM_NAME = ref<string>("边缘设备检测及近场通信系统");
provide("EMULATION_SYSTEM_NAME", EMULATION_SYSTEM_NAME);
</script>
