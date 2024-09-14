<script setup lang="ts">
import { ref, provide, watch, onMounted } from "vue";
import { THEME_KEY } from "vue-echarts";
import { storeToRefs } from "pinia";
import { useDarkStore } from "~/stores/dark";

const { isDark } = storeToRefs(useDarkStore());
const theme = ref("light");

onMounted(() => {
  theme.value = isDark.value ? "dark" : "light";
});

watch(isDark, (value) => {
  theme.value = value ? "dark" : "light";
});

provide(THEME_KEY, theme);
</script>

<template>
  <div class="grid grid-cols-2 gap-2 w-full">
    <RiskIndexChart />
    <LoopholeDigChart />
    <AttackTrendChart />
    <AttackRankingChart />
    <DefenseTrendChart />
    <DefenseRankingChart />
  </div>
</template>
