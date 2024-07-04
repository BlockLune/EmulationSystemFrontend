<template>
  <el-menu mode="horizontal">
    <el-menu-item>
      <span>{{ EMULATION_SYSTEM_NAME }}</span>
    </el-menu-item>
    <el-menu-item>
      <el-link
        :underline="false"
        href="https://modao.cc/app/6XdQIh9Es69j5wU7F934Ol"
        target="_blank"
      >
        <span>原型</span>
      </el-link>
    </el-menu-item>
    <el-menu-item disabled>
      <span>关于</span>
    </el-menu-item>
    <el-menu-item @click="toggleDark()">
      <button
        class="border-none w-full bg-transparent cursor-pointer"
        style="height: var(--ep-menu-item-height)"
      >
        <i inline-flex i="dark:ep-moon ep-sunny" />
      </button>
    </el-menu-item>
    <el-menu-item v-if="showClearLocalStorageButton">
      <button
        class="border-none w-full bg-transparent cursor-pointer"
        @click="clearLocalStorage"
      >
        Clear localStorage
      </button>
    </el-menu-item>
  </el-menu>
</template>

<script lang="ts" setup>
import { toggleDark } from "~/composables";
import { inject } from "vue";

const EMULATION_SYSTEM_NAME = inject<string>("EMULATION_SYSTEM_NAME");
const clearLocalStorage = () => {
  localStorage.clear();
  console.log("LocalStorage cleared.");
};
const showClearLocalStorageButton = import.meta.env.DEV;
</script>
