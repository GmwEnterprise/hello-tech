<template>
  <router-view />
</template>

<script>
import http from './plugins/axios'
import { message } from 'ant-design-vue'
import { computed, provide } from 'vue'
export default {
  name: 'App',
  setup() {
    // 定义全局使用的数据
    let academyList = []

    // 初始化数据
    http.get('/academy/list').then((resp) => {
      academyList = resp.data
    })

    // 面向子组件提供
    provide(
      'academyList',
      computed(() => academyList),
    )
    provide('errorHandler', (error) => {
      let msg
      if (error instanceof Error) {
        msg = error.message
      } else {
        console.error(error)
        msg = '请查看控制栏错误信息'
      }
      message.error(msg)
    })
  },
}
</script>
