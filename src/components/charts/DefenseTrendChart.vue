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

const option = ref({
  title: {
    text: "安全事件防御态势",
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
  toolbox: {
    feature: {
      saveAsImage: {},
    },
  },
  xAxis: {
    type: "category",
    boundaryGap: false,
    data: [
      "1月19日",
      "1月20日",
      "1月21日",
      "1月22日",
      "1月23日",
      "1月24日",
      "1月25日",
    ],
  },
  yAxis: {
    type: "value",
  },
  series: [
    {
      name: "一般风险",
      type: "line",
      stack: "Total",
      data: [100, 140, 230, 100, 130, 174, 121],
    },
    {
      name: "中等风险",
      type: "line",
      stack: "Total",
      data: [150, 100, 200, 140, 100, 211, 187],
    },
    {
      name: "高危风险",
      type: "line",
      stack: "Total",
      data: [194, 186, 120, 228, 133, 193, 105],
    },
  ],
});
</script>

<template>
  <VChart :option="option" />
</template>
