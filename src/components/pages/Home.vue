<template>
  <div class="flex justify-center items-center w-full">
    <el-form
      ref="loginFormRef"
      :model="loginForm"
      label-width="70px"
      label-position="left"
    >
      <el-form-item>
        <el-text>{{ EMULATION_SYSTEM_NAME }} 登录界面</el-text>
      </el-form-item>
      <el-form-item label="用户名" prop="username">
        <el-input v-model="loginForm.username" placeholder="输入用户名" />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input
          v-model="loginForm.password"
          placeholder="输入密码"
          type="password"
          show-password
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm(loginFormRef)">
          登录
        </el-button>
        <el-button @click="clearForm(loginFormRef)"> 清除 </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script lang="ts" setup>
import { ref, reactive, inject } from "vue";
import type { FormInstance } from "element-plus";
import { loginReq } from "../../services/api";

const loginFormRef = ref<FormInstance>();
const loginForm = reactive({
  username: "",
  password: "",
});

const submitForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  loginReq(loginForm.username, loginForm.password);
};

const clearForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.resetFields();
};

const EMULATION_SYSTEM_NAME = inject<string>("EMULATION_SYSTEM_NAME");
</script>
