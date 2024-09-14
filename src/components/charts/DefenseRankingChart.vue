<script setup lang="ts">
import { use } from "echarts/core";
import { BarChart } from "echarts/charts";
import { TitleComponent, GridComponent } from "echarts/components";
import { CanvasRenderer } from "echarts/renderers";
import VChart, { THEME_KEY } from "vue-echarts";
import { ref, provide } from "vue";
provide(THEME_KEY, "dark");
use([TitleComponent, GridComponent, BarChart, CanvasRenderer]);

const DUMMY_DATA = Array.from({ length: 5 }, (_, i) => ({
  name: `镜像 ${i + 1}`,
  value: Math.floor(Math.random() * 200),
})).sort((a, b) => a.value - b.value);

const option = ref({
  title: {
    text: "TOP5 防御情况",
  },
  xAxis: {
    type: "value",
  },
  yAxis: {
    type: "category",
    data: DUMMY_DATA.map((item) => item.name),
  },
  series: [
    {
      type: "bar",
      data: DUMMY_DATA.map((item) => item.value),
    },
  ],
});
</script>

<template>
  <VChart :option="option" autoresize />
</template>
