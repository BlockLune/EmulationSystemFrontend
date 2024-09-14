<script lang="ts" setup>
import { use } from "echarts/core";
import { BarChart } from "echarts/charts";
import { TitleComponent, GridComponent } from "echarts/components";
import { CanvasRenderer } from "echarts/renderers";
import VChart from "vue-echarts";
import { ref } from "vue";

use([TitleComponent, GridComponent, BarChart, CanvasRenderer]);

const DUMMY_NAMES = [
  "设计错误",
  "输入验证错误",
  "边界条件错误",
  "访问验证错误",
  "竞争条件错误",
];

const DUMMY_DATA = DUMMY_NAMES.map((name) => ({
  name,
  value: Math.floor(Math.random() * 200),
}));

const option = ref({
  title: {
    text: "TOP5 潜在攻击类型",
    padding: 20,
  },
  xAxis: {
    type: "value",
  },
  yAxis: {
    type: "category",
    data: DUMMY_DATA.map((item) => item.name),
    inverse: true,
  },
  series: [
    {
      type: "bar",
      data: DUMMY_DATA.map((item) => item.value),
      realtimeSort: true,
      label: {
        show: true,
        position: "right",
      },
    },
  ],
});
</script>

<template>
  <VChart class="h-64" :option="option" autoresize />
</template>
