<script lang="ts" setup>
import { use } from "echarts/core";
import { BarChart } from "echarts/charts";
import { TitleComponent, GridComponent } from "echarts/components";
import { CanvasRenderer } from "echarts/renderers";
import VChart, { THEME_KEY } from "vue-echarts";
import { ref, provide } from "vue";
provide(THEME_KEY, "dark");
use([TitleComponent, GridComponent, BarChart, CanvasRenderer]);

const option = ref({
  title: {
    text: "TOP5潜在攻击类型",
  },
  xAxis: {
    type: "value",
  },
  yAxis: {
    type: "category",
    data: [
      "设计错误",
      "输入验证错误",
      "边界条件错误",
      "访问验证错误",
      "竞争条件错误",
    ],
  },
  grid: {
    x: 100,
    y: 25,
    x2: 30,
    y2: 35,
  },
  series: [
    {
      data: [50, 80, 100, 140, 230],
      type: "bar",
      itemStyle: {
        color: "blue",
      },
    },
  ],
});
</script>

<template>
  <VChart :option="option" />
</template>
