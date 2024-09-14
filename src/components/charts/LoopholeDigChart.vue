<script lang="ts" setup>
import { use } from "echarts/core";
import { BarChart } from "echarts/charts";
import { TitleComponent, GridComponent } from "echarts/components";
import { CanvasRenderer } from "echarts/renderers";
import VChart from "vue-echarts";
import { ref } from "vue";

use([TitleComponent, GridComponent, BarChart, CanvasRenderer]);

const DUMMY_DATA = Array.from({ length: 7 }, (_, i) => ({
  date: `1 月 ${19 + i} 日`,
  value: Math.floor(Math.random() * 250),
}));

const option = ref({
  title: {
    text: "漏洞挖掘态势",
    padding: 20,
  },
  xAxis: {
    type: "category",
    data: DUMMY_DATA.map((item) => item.date),
  },
  yAxis: {
    type: "value",
  },
  series: [
    {
      type: "bar",
      data: DUMMY_DATA.map((item) => item.value),
      label: {
        show: true,
        position: "top",
      },
    },
  ],
});
</script>

<template>
  <VChart class="h-64" :option="option" autoresize />
</template>
