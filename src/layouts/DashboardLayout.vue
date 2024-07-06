<template>
  <el-config-provider namespace="ep">
    <BaseHeader />
    <el-container>
      <BaseSide />
      <el-container class="p-4">
        <RouterView />
      </el-container>
    </el-container>
  </el-config-provider>
</template>
