<template>
  <el-checkbox
    v-model="isAll"
    :indeterminate="isIndeterminate"
    @change="handleAllChange"
    >仿真靶场管理</el-checkbox
  >
  <el-checkbox v-model="permissionRange" @change="updateRange"
    >靶场管理</el-checkbox
  >
  <el-checkbox v-model="permissionContainer" @change="updateContainer"
    >容器管理</el-checkbox
  >
  <el-checkbox v-model="permissionMirror" @change="updateMirror"
    >镜像管理</el-checkbox
  >
</template>

<script lang="ts" setup>
import { ref, defineProps, defineEmits, onBeforeMount } from "vue";
import { CheckboxValueType } from "element-plus";

const props = defineProps<{
  range: boolean;
  container: boolean;
  mirror: boolean;
}>();
const emit = defineEmits(["update:range", "update:container", "update:mirror"]);
const updateRange = () => {
  emit("update:range", permissionRange.value);
  handleIndeterminateChange();
};
const updateContainer = () => {
  emit("update:container", permissionContainer.value);
  handleIndeterminateChange();
};
const updateMirror = () => {
  emit("update:mirror", permissionMirror.value);
  handleIndeterminateChange();
};

const isAll = ref(false);
const isIndeterminate = ref(false);

// const options = ref({
//     range: {
//         cnName: '靶场管理',
//         value: false,
//     },
//     container: {
//         cnName: '容器管理',
//         value: false,
//     },
//     mirror: {
//         cnName: '镜像管理',
//         value: false,
//     },
//     attackDefense: {
//         cnName: '攻防演练',
//         value: false,
//     },
//     loopholes: {
//         cnName: '漏洞库管理',
//         value: false,
//     },
//     system: {
//         cnName: '系统管理',
//         value: false,
//     },
// });

const permissionRange = ref(false);
const permissionContainer = ref(false);
const permissionMirror = ref(false);

const checkedOptions = ref([] as string[]);
const allOptions: string[] = ["靶场管理", "容器管理", "镜像管理"];

const clearCheckedOptions = () => {
  checkedOptions.value = checkedOptions.value.filter((option) => {
    checkedOptions.value.indexOf(option) === -1;
  });
};
const updateArrayBasedOnTruthValues = () => {
  clearCheckedOptions();
  if (permissionRange.value) {
    checkedOptions.value.push("靶场管理");
  }
  if (permissionContainer.value) {
    checkedOptions.value.push("容器管理");
  }
  if (permissionMirror.value) {
    checkedOptions.value.push("镜像管理");
  }
};
const updateTruthValuesBasedOnArray = () => {
  permissionRange.value = false;
  permissionContainer.value = false;
  permissionMirror.value = false;

  if (checkedOptions.value.indexOf("靶场管理") !== -1) {
    permissionRange.value = true;
  }
  if (checkedOptions.value.indexOf("容器管理") !== -1) {
    permissionContainer.value = true;
  }
  if (checkedOptions.value.indexOf("镜像管理") !== -1) {
    permissionMirror.value = true;
  }
};

onBeforeMount(() => {
  if (props.range) permissionRange.value = true;
  if (props.container) permissionContainer.value = true;
  if (props.mirror) permissionMirror.value = true;

  if (props.range) checkedOptions.value.push("靶场管理");
  if (props.container) checkedOptions.value.push("容器管理");
  if (props.mirror) checkedOptions.value.push("镜像管理");

  if (checkedOptions.value.length === allOptions.length) {
    isAll.value = true;
    isIndeterminate.value = false;
  } else if (checkedOptions.value.length === 0) {
    isAll.value = false;
    isIndeterminate.value = false;
  } else {
    isAll.value = false;
    isIndeterminate.value = true;
  }
});

const handleAllChange = (val: CheckboxValueType) => {
  checkedOptions.value = val ? allOptions : ([] as string[]);
  isIndeterminate.value = false;
  updateTruthValuesBasedOnArray();
};
const handleIndeterminateChange = () => {
  updateArrayBasedOnTruthValues();
  const checkedCount = checkedOptions.value.length;
  const allCount = allOptions.length;
  isAll.value = checkedCount === allCount;
  isIndeterminate.value = checkedCount > 0 && checkedCount < allCount;
};
</script>
