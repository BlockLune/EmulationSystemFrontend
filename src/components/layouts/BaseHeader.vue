<template>
  <el-menu mode="horizontal">
    <el-menu-item index="1">
      <span>{{ EMULATION_SYSTEM_NAME }}</span>
    </el-menu-item>
    <el-menu-item index="2">
      <a href="https://modao.cc/app/6XdQIh9Es69j5wU7F934Ol#screen=slqm0sbodm347ro" target="_blank">原型</a>
    </el-menu-item>
    <el-menu-item index="3" disabled>
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
  </el-menu>
</template>

<style scoped>
a {
  text-decoration-line: none;
}
</style>

<script lang="ts" setup>
import { toggleDark } from "~/composables";
import { inject } from "vue";

const EMULATION_SYSTEM_NAME = inject<string>('EMULATION_SYSTEM_NAME');
</script>
