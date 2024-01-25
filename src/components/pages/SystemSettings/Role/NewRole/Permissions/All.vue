<template>
  <EmulationRange
    v-model:range="permissionsStatus.range"
    v-model:container="permissionsStatus.container"
    v-model:mirror="permissionsStatus.mirror"
    @update:range="
      (range) => {
        permissionsStatus.range = range;
      }
    "
    @update:container="
      (container) => {
        permissionsStatus.container = container;
      }
    "
    @update:mirror="
      (mirror) => {
        permissionsStatus.mirror = mirror;
      }
    "
  />
  <el-checkbox v-model="permissionsStatus.attackDefense">攻防演练</el-checkbox>
  <el-checkbox v-model="permissionsStatus.loopholes">漏洞库管理</el-checkbox>
  <el-checkbox v-model="permissionsStatus.system">系统管理</el-checkbox>
</template>

<script lang="ts" setup>
import EmulationRange from "./EmulationRange.vue";
import { ref, defineProps, onBeforeMount } from "vue";

interface AllPermissions {
  range: boolean;
  container: boolean;
  mirror: boolean;
  attackDefense: boolean;
  loopholes: boolean;
  system: boolean;
}

const props = defineProps<{
  permissions: string[];
}>();

const permissionsStatus = ref<AllPermissions>({
  range: false,
  container: false,
  mirror: false,
  attackDefense: false,
  loopholes: false,
  system: false,
});

onBeforeMount(() => {
  if (props.permissions.indexOf("靶场管理") !== -1) {
    permissionsStatus.value.range = true;
  }
  if (props.permissions.indexOf("容器管理") !== -1) {
    permissionsStatus.value.container = true;
  }
  if (props.permissions.indexOf("镜像管理") !== -1) {
    permissionsStatus.value.mirror = true;
  }
  if (props.permissions.indexOf("攻防管理") !== -1) {
    permissionsStatus.value.attackDefense = true;
  }
  if (props.permissions.indexOf("漏洞库管理") !== -1) {
    permissionsStatus.value.loopholes = true;
  }
  if (props.permissions.indexOf("系统管理") !== -1) {
    permissionsStatus.value.system = true;
  }
});

const handleChange = () => {
  console.log(permissionsStatus.value);
};
</script>
