<template>
  <el-menu mode="vertical" :router="true" :default-openeds="openeds">
    <el-menu-item index="/dashboard/situation_awareness">
      <template #title>
        <el-icon><InfoFilled /></el-icon>
        <span>态势感知</span>
      </template>
    </el-menu-item>
    <el-sub-menu index="/dashboard/simulation_range">
      <template #title>
        <el-icon><Management /></el-icon>
        <span>仿真靶场管理</span>
      </template>
      <el-menu-item index="/dashboard/simulation_range/image">
        <template #title>
          <el-icon>
            <div class="i-fluent:cd-16-filled"></div>
          </el-icon>
          <span>镜像管理</span>
        </template>
      </el-menu-item>
      <el-menu-item index="/dashboard/simulation_range/container">
        <template #title>
          <el-icon>
            <div class="i-clarity:container-solid"></div>
          </el-icon>
          <span>容器管理</span>
        </template>
      </el-menu-item>
      <el-menu-item index="/dashboard/simulation_range/attack_defense_exercise">
        <template #title>
          <el-icon>
            <div class="i-material-symbols:swords"></div>
          </el-icon>
          <span>攻防演练</span>
        </template>
      </el-menu-item>
      <el-menu-item index="/dashboard/simulation_range/range">
        <template #title>
          <el-icon><Flag /></el-icon>
          <span>靶场管理</span>
        </template>
      </el-menu-item>
      <el-menu-item index="/dashboard/simulation_range/dig">
        <template #title>
          <el-icon>
            <div class="i-mingcute:shovel-fill"></div>
          </el-icon>
          <span>设备漏洞挖掘</span>
        </template>
      </el-menu-item>
      <el-menu-item index="/dashboard/simulation_range/task">
        <template #title>
          <el-icon>
            <div class="i-mingcute:task-2-fill"></div>
          </el-icon>
          <span>任务管理</span>
        </template>
      </el-menu-item>
    </el-sub-menu>

    <el-menu-item index="/dashboard/loophole">
      <template #title>
        <el-icon><WarnTriangleFilled /></el-icon>
        <span>漏洞库管理</span>
      </template>
    </el-menu-item>

    <el-sub-menu index="/dashboard/settings">
      <template #title>
        <el-icon><Tools /></el-icon>
        <span>系统管理</span>
      </template>
      <el-menu-item index="/dashboard/settings/role">
        <el-icon><div class="i-mingcute:lock-fill"></div></el-icon>
        <span>角色管理</span>
      </el-menu-item>
      <el-menu-item index="/dashboard/settings/user">
        <el-icon><UserFilled /></el-icon>
        <span>用户管理</span>
      </el-menu-item>
      <el-menu-item index="/dashboard/settings/config">
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
  InfoFilled,
  Management,
  Tools,
  UserFilled,
  WarnTriangleFilled,
} from "@element-plus/icons-vue";

const openeds = ["/dashboard/simulation_range", "/dashboard/settings"];
</script>
