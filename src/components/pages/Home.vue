<template>

<!--  <el-form v-show="showHome"-->
<!--  <el-text>{{ EMULATION_SYSTEM_NAME }} 登录界面</el-text>-->
  <div style="width: 100%; display: flex; flex-direction: row;">
    <div style="width: 70%; margin-top: 5%; margin-left: 1%">
      <h1>{{ EMULATION_SYSTEM_NAME }}</h1>
    </div>
    <div style="width: 30%; height: 99.5vh;">
      <el-card style="width: 99.5%; height: 99.9%;"
               body-style="margin-top: 40%;">
        <div style="width: 80%; margin-left: 10%">
          <h3>账户登陆</h3>
          <el-form
              ref="loginFormRef"
              :model="loginForm"
              label-width="70px"
              label-position="top"
          >
            <el-form-item label="用户名" prop="username">
              <el-input v-model="loginForm.username" placeholder="输入用户名" aria-required="true"/>
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input
                  v-model="loginForm.password"
                  placeholder="输入密码"
                  type="password"
                  show-password aria-required="true"
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

      </el-card>
    </div>
  </div>


</template>

<script lang="ts" setup>
import { ref, reactive, inject } from "vue";
import type { FormInstance } from "element-plus";
import { loginReq, showHome } from "../../services/api";
// import { loginReq } from "../../services/api";

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
