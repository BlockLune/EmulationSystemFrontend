<template>
  <el-menu mode="horizontal">
    <el-menu-item index="0">
      <span>{{ EMULATION_SYSTEM_NAME }}</span>
    </el-menu-item>
    <el-menu-item index="1" @click="toggleDark()">
      <button
        class="border-none w-full bg-transparent cursor-pointer"
        style="height: var(--ep-menu-item-height)"
      >
        <i inline-flex i="dark:ep-moon ep-sunny"></i>
      </button>
    </el-menu-item>
    <el-menu-item index="2">
      <el-link
        :underline="false"
        href="https://modao.cc/app/6XdQIh9Es69j5wU7F934Ol"
        target="_blank"
      >
        <span>原型</span>
      </el-link>
    </el-menu-item>
    <el-menu-item index="3" @click="handleOpen">
      <button
        class="border-none w-full bg-transparent cursor-pointer"
        style="height: var(--ep-menu-item-height)"
      >
        退出登录
      </button>
      <Teleport to="body">
        <el-dialog v-model="showModal" width="300px">
          <span>确认退出登录？</span>
          <template #footer>
            <el-button @click="handleCancel">取消</el-button>
            <el-button type="primary" @click="handleConfirm">确认</el-button>
          </template>
        </el-dialog>
      </Teleport>
    </el-menu-item>
  </el-menu>
</template>

<script lang="ts" setup>
import { inject, ref } from "vue";
import { useRouter } from "vue-router";
import { useDarkStore } from "~/stores/dark";
import { deleteStoredToken } from "~/utils/handleToken.ts";

const { toggleDark } = useDarkStore();
const EMULATION_SYSTEM_NAME = inject<string>("EMULATION_SYSTEM_NAME");
const router = useRouter();

const showModal = ref(false);
function handleOpen() {
  showModal.value = true;
}
function handleCancel() {
  showModal.value = false;
}
function handleConfirm() {
  showModal.value = false;
  deleteStoredToken();
  router.push("/login");
}
</script>

<style>
.el-menu--horizontal > .el-menu-item:nth-child(1) {
  margin-right: auto;
}
</style>
