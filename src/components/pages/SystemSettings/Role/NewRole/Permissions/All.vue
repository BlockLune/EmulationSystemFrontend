<template>
  <EmulationRangeCheckboxGroup
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
    @change="handleChange"
  />
  <el-checkbox v-model="permissionsStatus.attackDefense" @change="handleChange"
    >攻防演练</el-checkbox
  >
  <el-checkbox v-model="permissionsStatus.loopholes" @change="handleChange"
    >漏洞库管理</el-checkbox
  >
  <el-checkbox v-model="permissionsStatus.system" @change="handleChange"
    >系统管理</el-checkbox
  >
</template>

<script lang="ts" setup>
import EmulationRangeCheckboxGroup from "./EmulationRangeCheckboxGroup.vue";
import { ref, onBeforeMount } from "vue";

interface PermissionsStatus {
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

const emit = defineEmits(["update:permissions"]);

const permissionsStatus = ref<PermissionsStatus>({
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
  // console.log(permissionsStatus.value);
  emit(
    "update:permissions",
    permissionsStatus.value.range,
    permissionsStatus.value.container,
    permissionsStatus.value.mirror,
    permissionsStatus.value.attackDefense,
    permissionsStatus.value.loopholes,
    permissionsStatus.value.system
  );
};
</script>
