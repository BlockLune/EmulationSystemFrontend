<script setup lang="ts">
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
    text: "TOP5防御情况",
  },
  xAxis: {
    type: "value",
  },
  yAxis: {
    type: "category",
    data: ["镜像1", "镜像2", "镜像3", "镜像4", "镜像5"],
  },
  series: [
    {
      data: [30, 45, 60, 80, 160],
      type: "bar",
      itemStyle: { color: "green" },
    },
  ],
});
</script>

<template>
  <VChart :option="option" />
</template>
