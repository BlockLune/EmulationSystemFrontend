<template>
  <el-form
    ref="formRef"
    :model="form"
    :rules="rules"
    label-width="70px"
    label-position="left"
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
import { ElMessage } from "element-plus";
import useAuthStore from "~/stores/auth";

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
  loginName: [
    { required: true, message: "请输入合法的用户名", trigger: "blur" },
    { validator: loginNameValidator, trigger: "blur" },
  ],
  password: [{ required: true, message: "请输入密码", trigger: "blur" }],
};

const loginRequest = async (loginName: string, password: string) => {
  try {
    const { login } = useAuthStore();
    await login(loginName, password);
    ElMessage.success("登录成功");
  } catch (error) {
    ElMessage.error("登录失败，请检查用户名和密码");
    clearForm(formRef.value);
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
    if (valid) {
      console.log("登录参数：", form);
      loginRequest(form.loginName, form.password);
    } else {
      console.error("登录参数错误！");
      clearForm(formEl);
    }
  });
};
</script>
