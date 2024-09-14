<script setup lang="ts">
import { use } from "echarts/core";
import { PieChart } from "echarts/charts";
import {
  TitleComponent,
  TooltipComponent,
  LegendComponent,
} from "echarts/components";
import { CanvasRenderer } from "echarts/renderers";
import VChart from "vue-echarts";
import { ref } from "vue";

use([
  TitleComponent,
  TooltipComponent,
  LegendComponent,
  PieChart,
  CanvasRenderer,
]);

const DUMMY_DATA = [
  { value: 300, name: "一般风险" },
  { value: 150, name: "中等风险" },
  { value: 107, name: "高危风险" },
];

const option = ref({
  title: {
    text: "风险指数",
    padding: 20,
  },
  tooltip: {
    trigger: "item",
  },
  legend: {
    orient: "vertical",
    left: "right",
    padding: 20,
  },
  series: [
    {
      name: "风险等级",
      type: "pie",
      radius: ["40%", "70%"],
      avoidLabelOverlap: false,
      label: {
        show: false,
        position: "center",
      },
      emphasis: {
        label: {
          show: true,
          fontSize: "30",
          fontWeight: "bold",
        },
      },
      labelLine: {
        show: false,
      },
      data: DUMMY_DATA,
    },
  ],
});
</script>

<template>
  <VChart class="h-64" :option="option" autoresize />
</template>
