<template>
  <div id="login">
    <a-form
      :model="formState"
      @finish="handleFinish"
      @finishFailed="handleFinishFailed"
    >
      <a-form-item>
        <a-input v-model:value="formState.user" placeholder="管理员账户">
          <template #prefix
            ><UserOutlined style="color: rgba(0, 0, 0, 0.25)"
          /></template>
        </a-input>
      </a-form-item>
      <a-form-item>
        <a-input
          v-model:value="formState.password"
          type="password"
          placeholder="密码"
        >
          <template #prefix
            ><LockOutlined style="color: rgba(0, 0, 0, 0.25)"
          /></template>
        </a-input>
      </a-form-item>
      <a-form-item>
        <a-button
          type="primary"
          html-type="submit"
          :disabled="formState.user === '' || formState.password === ''"
        >
          Log in
        </a-button>
      </a-form-item>
    </a-form>
  </div>
</template>
<script>
import { UserOutlined, LockOutlined } from '@ant-design/icons-vue'
import { message } from 'ant-design-vue'
import { defineComponent, reactive } from 'vue'
import router from '../router'
export default defineComponent({
  setup() {
    const formState = reactive({
      user: '',
      password: '',
    })

    const handleFinish = () => {
      if (formState.user === 'admin' && formState.password === 'admin') {
        message.success('登录成功')
        router.push('/home')
      } else {
        message.error('登录失败，请输入正确的账户密码')
      }
    }

    const handleFinishFailed = (errors) => {
      console.log(errors)
    }

    return {
      formState,
      handleFinish,
      handleFinishFailed,
    }
  },

  components: {
    UserOutlined,
    LockOutlined,
  },
})
</script>

<style scoped>
div#login {
  display: grid;
  place-content: center;
  width: 100%;
  height: 80vh;
}
</style>
