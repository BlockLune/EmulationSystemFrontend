<template>
  <el-menu mode="vertical" @select="handleMenuSelect">
    <el-menu-item>
      <template #title>
        <RouterLink to="/" class="no-underline">
          <el-icon><HomeFilled /></el-icon>
          <span>主页</span>
        </RouterLink>
      </template>
    </el-menu-item>
    <el-menu-item>
      <template #title>
        <RouterLink to="" class="no-underline"></RouterLink>
        <el-icon><InfoFilled /></el-icon>
        <span>态势感知</span>
      </template>
    </el-menu-item>
    <el-sub-menu index="3">
      <template #title>
        <RouterLink to="" class="no-underline"></RouterLink>
        <el-icon><Management /></el-icon>
        <span>仿真靶场管理</span>
      </template>
      <el-menu-item index="3-1">
        <template #title>
          <RouterLink to="" class="no-underline"></RouterLink>
          <el-icon><Management /></el-icon>
          <span>镜像管理</span>
        </template>
      </el-menu-item>
      <el-menu-item index="3-2">
        <template #title>
          <RouterLink to="" class="no-underline"></RouterLink>
          <el-icon><Management /></el-icon>
          <span>容器管理</span>
        </template>
      </el-menu-item>
      <el-menu-item index="3-3">
        <template #title>
          <RouterLink to="" class="no-underline"></RouterLink>
          <el-icon><Management /></el-icon>
          <span>攻防演练</span>
        </template>
      </el-menu-item>
      <el-menu-item index="3-4">
        <template #title>
          <RouterLink to="" class="no-underline"></RouterLink>
          <el-icon><Flag /></el-icon>
          <span>靶场管理</span>
        </template>
      </el-menu-item>
      <el-menu-item index="3-5">
        <template #title>
          <RouterLink to="" class="no-underline"></RouterLink>
          <el-icon><Management /></el-icon>
          <span>设备漏洞挖掘</span>
        </template>
      </el-menu-item>
    </el-sub-menu>

    <el-menu-item index="5">
      <template #title>
        <RouterLink to="" class="no-underline"></RouterLink>
        <el-icon><WarnTriangleFilled /></el-icon>
        <span>漏洞库管理</span>
      </template>
    </el-menu-item>
    <el-sub-menu index="6">
      <template #title>
        <RouterLink to="" class="no-underline"></RouterLink>
        <el-icon><Tools /></el-icon>
        <span>系统管理</span>
      </template>
      <el-menu-item index="6-1">
        <RouterLink to="" class="no-underline"></RouterLink>
        <el-icon><Lock /></el-icon>
        <span>角色管理</span>
      </el-menu-item>
      <el-menu-item index="6-2">
        <RouterLink to="" class="no-underline"></RouterLink>
        <el-icon><UserFilled /></el-icon>
        <span>用户管理</span>
      </el-menu-item>
      <el-menu-item index="6-3">
        <RouterLink to="" class="no-underline"></RouterLink>
        <el-icon><Histogram /></el-icon>
        <span>配置管理</span>
      </el-menu-item>
    </el-sub-menu>
  </el-menu>
</template>

<script lang="ts" setup>
import {
  Flag,
  Histogram,
  HomeFilled,
  InfoFilled,
  Lock,
  Management,
  Tools,
  UserFilled,
  WarnTriangleFilled,
} from "@element-plus/icons-vue";
import { ref } from "vue";
const emits = defineEmits(["changeDisplay"]);
const selected = ref("");
const handleMenuSelect = (index: string) => {
  selected.value = index;
  emits("changeDisplay", selected.value);
};
</script>
