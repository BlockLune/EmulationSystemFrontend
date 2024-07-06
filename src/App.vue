<template>
  <RouterView />
</template>

<script lang="ts" setup>
import { ref, provide } from "vue";
const EMULATION_SYSTEM_NAME = ref<string>("边缘设备检测及近场通信系统");
provide("EMULATION_SYSTEM_NAME", EMULATION_SYSTEM_NAME);
</script>
