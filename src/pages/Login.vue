<template>
  <div class="flex flex-row w-full">
    <div style="width: 70%; margin-top: 5%; margin-left: 1%">
      <h1>{{ EMULATION_SYSTEM_NAME }}</h1>
    </div>
    <div class="w-40%" style="height: 99.5vh">
      <el-card
        style="width: 99.5%; height: 99.9%"
        body-style="margin-top: 40%;"
      >
        <div style="width: 80%; margin-left: 10%">
          <h3>账户登录</h3>
          <login-form />
        </div>
      </el-card>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { inject, onMounted } from "vue";

const EMULATION_SYSTEM_NAME = inject<string>("EMULATION_SYSTEM_NAME");

onMounted(async () => {});
</script>
