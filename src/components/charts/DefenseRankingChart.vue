<script setup lang="ts">
import { use } from "echarts/core";
import { BarChart } from "echarts/charts";
import { TitleComponent, GridComponent } from "echarts/components";
import { CanvasRenderer } from "echarts/renderers";
import VChart from "vue-echarts";
import { ref } from "vue";

use([TitleComponent, GridComponent, BarChart, CanvasRenderer]);

const DUMMY_DATA = Array.from({ length: 5 }, (_, i) => ({
  name: `镜像 ${i + 1}`,
  value: Math.floor(Math.random() * 200),
}));

const option = ref({
  title: {
    text: "TOP5 防御情况",
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
