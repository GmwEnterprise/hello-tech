<template>
  <div class="login-container">
    <el-form
      :model="formData"
      :rules="formRules"
      ref="loginForm"
      class="login-form"
    >
      <el-form-item label="用户名" prop="username">
        <el-input v-model="formData.username" autocomplete="off" />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input
          type="password"
          v-model="formData.password"
          autocomplete="off"
        />
      </el-form-item>
      <el-form-item label="确认密码" prop="passwordReinput">
        <el-input
          type="password"
          v-model="formData.passwordReinput"
          autocomplete="off"
        />
      </el-form-item>
    </el-form>
  </div>
</template>

<script lang="ts">
import { parameterValidation, HttpResponse } from '@/api'
import { defineComponent, reactive, readonly } from 'vue'

export default defineComponent({
  name: 'Login',
  setup() {
    const formData = reactive({
        username: '',
        password: '',
        passwordReinput: '',
      }),
      formRules = readonly({
        username: [
          {
            validator: (
              _rule: unknown,
              value: string,
              callback: (error?: Error) => void,
            ): void => {
              parameterValidation({
                username: value,
              })
                .then(() => callback())
                .catch((error) =>
                  callback(new Error((error as HttpResponse).message)),
                )
            },
            trigger: 'blur',
          },
        ],
        password: [
          {
            validator: (
              _rule: unknown,
              value: string,
              callback: (error?: Error) => void,
            ) => {
              parameterValidation({
                password: value,
              })
                .then(() => callback())
                .catch((error) =>
                  callback(new Error((error as HttpResponse).message)),
                )
            },
            trigger: 'blur',
          },
        ],
        passwordReinput: [
          {
            validator: (
              _rule: unknown,
              value: string,
              callback: (error?: Error) => void,
            ) => {
              value === formData.password
                ? callback()
                : callback(new Error('两次输入密码不一致'))
            },
          },
        ],
      })
    return { formData, formRules }
  },
})
</script>
