<script lang="ts" setup>
import { inject } from "vue";
import LoginForm from "~/components/LoginForm.vue";

const EMULATION_SYSTEM_NAME = inject<string>("EMULATION_SYSTEM_NAME");
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
