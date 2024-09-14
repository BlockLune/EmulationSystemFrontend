<script lang="ts" setup>
import { inject, onMounted } from "vue";
import LoginForm from "~/components/LoginForm.vue";
import { useDarkStore } from "~/stores/dark";

const EMULATION_SYSTEM_NAME = inject<string>("EMULATION_SYSTEM_NAME");

/**
 * 初次加载登录页面时可能无法正确触发主题切换，
 * 在这里手动触发一次，以确保主题切换功能正常。
 */
useDarkStore(); // trigger the useDark function
onMounted(() => {
  // do nothing, just trigger the useDark function
});
</script>

<template>
  <div
    class="w-full min-h-screen bg-gray-100 dark:bg-slate-800 flex items-center justify-center"
  >
    <div
      class="flex w-full max-w-3xl flex-col md:flex-row items-center md:items-stretch"
    >
      <!-- 紫色区域 -->
      <div
        class="w-5/6 sm:w-2/3 md:w-1/2 bg-indigo-500 dark:bg-indigo-700 p-12 text-white flex flex-col justify-center"
      >
        <h1 class="text-4xl font-bold">欢迎使用</h1>
        <p class="text-2xl">{{ EMULATION_SYSTEM_NAME }}</p>
      </div>

      <!-- 表单区域 -->
      <div class="w-5/6 sm:w-2/3 md:w-1/2 bg-white dark:bg-slate-900 p-12">
        <LoginForm />
      </div>
    </div>
  </div>
</template>
