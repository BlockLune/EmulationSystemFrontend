<template>
  <el-form :model="briefLoopHoleForm" label="70px" label-position="left">
    <el-form-item label="漏洞名称" prop="name">
      <el-input v-model="briefLoopHoleForm.name" placeholder="单行输入" />
    </el-form-item>
    <el-form-item label="漏洞等级" prop="level">
      <el-select-v2 v-model="briefLoopHoleForm.level" :options="loopHoleLevels" placeholder="请选择"></el-select-v2>
    </el-form-item>
    <el-button @click="showDetailedSwitcher">进入明细页</el-button>
    <LoopHoleCard name="程序设计错误" level="1 级" related="102" description="..." from="靶场仿真/固件挖掘"
      v-if="showLoopCard === true" v-model="showBrief" />
  </el-form>
</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue';
const briefLoopHoleForm = reactive({
  name: '',
  level: '',
});

const loopHoleLevels = [
  { value: 'loophole_level_1', label: '等级 1' },
  { value: 'loophole_level_2', label: '等级 2' },
  { value: 'loophole_level_3', label: '等级 3' },
];

const showLoopCard = ref(true);

const showBrief = defineModel();

const showDetailedSwitcher = () => {
  showBrief.value = false;
};
</script>
