<template>
  <RouterView />
</template>

<script lang="ts" setup>
import { ref, provide, onMounted } from "vue";
import checkAlive from "~/utils/checkAlive";
import { ElMessage } from "element-plus";
import { useRouter } from "vue-router";
import useAuthStore from "./stores/auth";

const router = useRouter();
const { isTokenAvailable } = useAuthStore();

const EMULATION_SYSTEM_NAME = ref<string>("边缘设备检测及近场通信系统");
provide("EMULATION_SYSTEM_NAME", EMULATION_SYSTEM_NAME);

onMounted(async () => {
  const alive = await checkAlive();
  if (isTokenAvailable() && alive) {
    ElMessage.success("已使用本地 Token 自动登录");
    router.push("/dashboard");
  } else {
    router.push("/login");
  }
});
</script>
