<script lang="ts" setup>
import { use } from "echarts/core";
import { BarChart } from "echarts/charts";
import { TitleComponent, GridComponent } from "echarts/components";
import { CanvasRenderer } from "echarts/renderers";
import VChart, { THEME_KEY } from "vue-echarts";
import { ref, provide } from "vue";

use([TitleComponent, GridComponent, BarChart, CanvasRenderer]);
provide(THEME_KEY, "dark");

const option = ref({
  title: {
    text: "漏洞挖掘态势",
  },
  xAxis: {
    type: "category",
    data: ["1/16", "1/17", "1/18", "1/19", "1/20", "1/21", "1/22"],
  },
  yAxis: {
    type: "value",
  },
  series: [
    {
      type: "bar",
      data: Array.from({ length: 7 }, (_, i) =>
        Math.floor(Math.random() * 250)
      ), // dummy data
    },
  ],
});
</script>

<template>
  <VChart :option="option" />
</template>
