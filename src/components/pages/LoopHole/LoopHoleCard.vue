<template>
  <el-card>
    <template #header>
      {{ props.name }}
    </template>
    <div>
      <p>漏洞等级: {{ props.level }}</p>
      <p>相关漏洞数: {{ props.related }}</p>
      <p>漏洞说明: {{ props.description }}</p>
      <p>漏洞来源: {{ props.from }}</p>
    </div>
    <template #footer>
      <el-button @click="showDetailedSwitcher">查看明细</el-button>
    </template>
  </el-card>
</template>

<script lang="ts" setup>
const props = defineProps<{
  name: string;
  level: string;
  related: string;
  description: string;
  from: string;
}>();

const showBrief = defineModel();

const showDetailedSwitcher = () => {
  showBrief.value = false;
};
</script>