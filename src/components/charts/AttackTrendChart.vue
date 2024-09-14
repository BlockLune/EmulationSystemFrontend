<script setup lang="ts">
import { use } from "echarts/core";
import { LineChart } from "echarts/charts";
import {
  TitleComponent,
  TooltipComponent,
  LegendComponent,
  ToolboxComponent,
  GridComponent,
} from "echarts/components";
import { CanvasRenderer } from "echarts/renderers";
import VChart, { THEME_KEY } from "vue-echarts";
import { ref, provide } from "vue";
provide(THEME_KEY, "dark");
use([
  TitleComponent,
  TooltipComponent,
  LegendComponent,
  ToolboxComponent,
  GridComponent,
  LineChart,
  CanvasRenderer,
]);

const DUMMY_DATA = Array.from({ length: 7 }, (_, i) => ({
  date: `1 月 ${19 + i} 日`,
  low: Math.floor(Math.random() * 100),
  mid: Math.floor(Math.random() * 100),
  high: Math.floor(Math.random() * 100),
}));

const option = ref({
  title: {
    text: "危险事件发展态势",
  },
  tooltip: {
    trigger: "axis",
  },
  legend: {
    data: ["一般风险", "中等风险", "高危风险"],
  },
  grid: {
    left: "3%",
    right: "4%",
    bottom: "3%",
    containLabel: true,
  },
  xAxis: {
    type: "category",
    boundaryGap: false,
    data: DUMMY_DATA.map((item) => item.date),
  },
  yAxis: {
    type: "value",
  },
  series: [
    {
      name: "一般风险",
      type: "line",
      data: DUMMY_DATA.map((item) => item.low),
    },
    {
      name: "中等风险",
      type: "line",
      data: DUMMY_DATA.map((item) => item.mid),
    },
    {
      name: "高危风险",
      type: "line",
      data: DUMMY_DATA.map((item) => item.high),
    },
  ],
});
</script>

<template>
  <VChart :option="option" autoresize />
</template>
