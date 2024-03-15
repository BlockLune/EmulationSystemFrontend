<template>
  <BriefLoopHoleController v-if="showBrief === true" v-model="showBrief" />
  <DetailedLoopHoleController v-if="showBrief === false" />
</template>

<script lang="ts" setup>
import { ref } from 'vue';

const showBrief = ref(true);

</script>