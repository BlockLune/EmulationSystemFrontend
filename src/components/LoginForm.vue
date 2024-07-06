<template>
  <el-form
    ref="formRef"
    :model="form"
    :rules="rules"
    label-width="70px"
    label-position="top"
  >
    <el-form-item label="用户名" prop="loginName">
      <el-input
        v-model="form.loginName"
        placeholder="输入用户名"
        maxlength="16"
        show-word-limit
      />
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input
        v-model="form.password"
        placeholder="输入密码"
        type="password"
        show-password
      />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm(formRef)"> 登录 </el-button>
      <el-button @click="clearForm(formRef)"> 清除 </el-button>
    </el-form-item>
  </el-form>
</template>

<script lang="ts" setup>
import { ref, reactive } from "vue";
import type { FormInstance } from "element-plus";
import { getTokenAndStore } from "~/utils/handleToken";

const formRef = ref<FormInstance>();
const form = reactive({
  loginName: "",
  password: "",
});

// validate if loginName contains whitespace
const loginNameValidator = (
  rule: any,
  value: string,
  callback: (error?: Error) => void
) => {
  if (/\s/.test(value)) {
    callback(new Error("用户名不能包含空白字符"));
  } else {
    callback();
  }
};

const rules = {
  username: [
    { required: true, message: "请输入合法的用户名", trigger: "blur" },
    { validator: loginNameValidator, trigger: "blur" },
  ],
  password: [{ required: true, message: "请输入密码", trigger: "blur" }],
};

const loginRequest = async (loginName: string, password: string) => {
  try {
    await getTokenAndStore(loginName, password);
    console.log("登录成功！");
  } catch (error) {
    console.error("登录失败！", error);
    return;
  }
};

const clearForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.resetFields();
};

const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  await formEl.validate((valid, fields) => {
    console.log("登录参数: ", fields);
    if (valid) {
      loginRequest(form.loginName, form.password);
    } else {
      console.error("登录参数错误！");
      clearForm(formEl);
    }
  });
};
</script>
