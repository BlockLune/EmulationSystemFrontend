<template>
  <div class="flex flex-row w-full">
    <div style="width: 70%; margin-top: 5%; margin-left: 1%">
      <h1>{{ EMULATION_SYSTEM_NAME }}</h1>
    </div>
    <div class="w-40%" style="height: 99.5vh">
      <el-card style="width: 99.5%; height: 99.9%" body-style="margin-top: 40%;">
        <div style="width: 80%; margin-left: 10%">
          <h3>账户登录</h3>
          <el-form ref="loginFormRef" :model="loginForm" :rules="rules" label-width="70px" label-position="top">
            <el-form-item label="用户名" prop="username">
              <el-input v-model="loginForm.username" placeholder="输入用户名" aria-required="true" />
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input v-model="loginForm.password" placeholder="输入密码" type="password" show-password
                aria-required="true" />
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
import { ref, reactive, inject, onMounted } from "vue";
import type { FormInstance } from "element-plus";
import { loginReq } from "../../services/api";
import { useRouter } from "vue-router";
const router = useRouter();

const loginFormRef = ref<FormInstance>();
const loginForm = reactive({
  username: "",
  password: "",
});

const usernameValidator = (rule: any, value: string, callback: (error?: Error) => void) => {
  if (/\s/.test(value)) {
    callback(new Error("用户名不能包含空白字符"));
  } else {
    callback();
  }
};

const rules = {
  username: [
    { required: true, message: "请输入合法的用户名", trigger: "blur" },
    { validator: usernameValidator, trigger: "blur" },
  ],
  password: [{ required: true, message: "请输入密码", trigger: "blur" }],
};


const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  await formEl.validate((valid, fields) => {
    console.log("登录参数: ", fields);
    if (valid) {
      console.log("正在尝试登录...");
      loginReq(loginForm.username, loginForm.password);
      console.log("登录成功！");
      router.push("/situation_awareness");
    } else {
      console.log("登录参数错误！")
    }
  })
};
const clearForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.resetFields();
};

const EMULATION_SYSTEM_NAME = inject<string>("EMULATION_SYSTEM_NAME");

onMounted(() => {
  if (localStorage.getItem("Authorization") !== null) {
    console.log("Login success!");
    router.push("/situation_awareness");
  }
});
</script>
