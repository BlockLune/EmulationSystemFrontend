<template>
  <el-config-provider namespace="ep">
    <BaseHeader />
    <el-container class="main-container">
      <BaseSide />
      <el-container class="content-container">
        <Home />
      </el-container>
    </el-container>
  </el-config-provider>
</template>

<style>
#app {
  text-align: center;
  color: var(--ep-text-color-primary);
}

.main-container {
  height: calc(100vh - var(--ep-menu-item-height) - 3px);
}

.content-container {
  padding: 20px;
}
</style>