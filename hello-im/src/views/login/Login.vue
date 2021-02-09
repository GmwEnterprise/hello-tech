<template>
  <div class="login-container">
    <el-form
      :model="formData"
      :rules="formRules"
      ref="loginForm"
      class="login-form"
      size="mini"
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
      <el-form-item
        label="确认密码"
        prop="passwordReinput"
        v-if="state === 'sign-on'"
      >
        <el-input
          type="password"
          v-model="formData.passwordReinput"
          autocomplete="off"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submit">提交</el-button>
        <el-button type="primary" @click="switchState"
          >切换到{{ state === 'sign-in' ? '注册' : '登录' }}</el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template>

<script lang="ts">
import { parameterValidation, HttpResponse, signOn, signIn } from '@/api'
import router from '@/router'
import { useStore } from '@/store'
import { ElMessage } from 'element-plus'
import { defineComponent, reactive, readonly, ref } from 'vue'

export default defineComponent({
  name: 'Login',
  beforeCreate() {
    if (useStore().state.userMsg.token) {
      router.push('/')
    }
  },
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
    const state = ref('sign-in')
    const submit = (): void => {
        if (state.value === 'sign-in') {
          // 提交登录
          signIn(formData)
            .then(() => router.push('/'))
            .catch((error: HttpResponse) => ElMessage.error(error.message))
        } else {
          // 提交注册
          signOn(formData)
            .then(() => router.push('/'))
            .catch((error: HttpResponse) => ElMessage.error(error.message))
        }
      },
      switchState = (): void => {
        state.value === 'sign-in'
          ? (state.value = 'sign-on')
          : (state.value = 'sign-in')
      }
    return { formData, formRules, submit, state, switchState }
  },
})
</script>

<style scoped>
.login-container {
  height: 100%;
  display: grid;
  place-items: center;
}
.login-form {
  width: 270px;
  box-sizing: content-box;
  padding: 30px 50px 70px;
  background-color: white;
  box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
}
</style>
