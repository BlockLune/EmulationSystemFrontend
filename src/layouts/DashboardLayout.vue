<template>
  <el-config-provider namespace="ep">
    <div class="flex flex-col h-screen">
      <BaseHeader />
      <div class="flex flex-1 overflow-hidden">
        <BaseSide />
        <main class="flex-1 p-4 overflow-y-auto">
          <RouterView />
        </main>
      </div>
    </div>
  </el-config-provider>
</template>
