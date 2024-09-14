<script lang="ts" setup>
import { use } from "echarts/core";
import { BarChart } from "echarts/charts";
import { TitleComponent, GridComponent } from "echarts/components";
import { CanvasRenderer } from "echarts/renderers";
import VChart from "vue-echarts";
import { ref } from "vue";

use([TitleComponent, GridComponent, BarChart, CanvasRenderer]);

const option = ref({
  title: {
    text: "漏洞挖掘态势",
    padding: 20,
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
  <VChart class="h-64" :option="option" autoresize />
</template>
