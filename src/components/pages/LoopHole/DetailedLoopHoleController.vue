<template>
  <el-form :model="detailedLoopHoleForm" label="70px" label-position="left">
    <el-form-item label="漏洞名称" prop="name">
      <el-input v-model="detailedLoopHoleForm.name" placeholder="单行输入" />
    </el-form-item>
    <el-form-item label="漏洞发现时间" prop="time">
      <el-time-select v-model="detailedLoopHoleForm.time" start="08:30" step="00:15" end="18:30" placeholder="时间选择" />
    </el-form-item>
  </el-form>
  <el-table :data="tableData">
    <el-table-column prop="dir" label="漏洞目录" />
    <el-table-column prop="name" label="漏洞名称" />
    <el-table-column prop="level" label="漏洞等级" />
    <el-table-column prop="meta" label="元数据" />
    <el-table-column prop="time" label="漏洞发现时间" />
    <el-table-column prop="source" label="漏洞源" />
  </el-table>
</template>

<script lang="ts" setup>
import { reactive } from 'vue';

const detailedLoopHoleForm = reactive({
  name: '',
  time: '',
});

const tableData = [
  { dir: '程序设计错误', name: '', level: '', meta: '', time: '', source: '' },
  { dir: '隐私保护错误', name: '', level: '', meta: '', time: '', source: '' },
  { dir: '数据泄露', name: '', level: '', meta: '', time: '', source: '' },
  { dir: '未分类错误', name: '', level: '', meta: '', time: '', source: '' },
]
</script>