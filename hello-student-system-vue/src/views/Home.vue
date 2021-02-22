<template>
  <a-layout>
    <a-layout-sider class="home-layout-sider">
      <div class="logo" />
      <a-menu theme="dark" mode="inline" v-model:selectedKeys="selectedKeys">
        <a-menu-item v-for="menuItem in menuItems" :key="menuItem.link">
          <user-outlined />
          <span class="nav-text">{{ menuItem.name }}</span>
        </a-menu-item>
      </a-menu>
    </a-layout-sider>
    <a-layout :style="{ marginLeft: '200px' }">
      <a-layout-header :style="{ background: '#fff', padding: 0 }">
        <h3 :style="{ marginLeft: '1.2em', fontWeight: 'bold' }">
          学籍信息管理系统
        </h3>
      </a-layout-header>
      <a-layout-content :style="{ margin: '24px 16px 0', overflow: 'initial' }">
        <div
          :style="{ padding: '24px', background: '#fff', textAlign: 'center' }"
        >
          <router-view />
        </div>
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>

<script>
import { readonly, ref, watchEffect } from 'vue'
import { UserOutlined } from '@ant-design/icons-vue'
import router from '../router'

export default {
  name: 'Home',
  setup() {
    const menuItems = readonly([
      {
        link: '/home/students',
        name: '学生信息管理',
      },
      {
        link: '/home/courses',
        name: '课程信息管理',
      },
      {
        link: '/home/courseSelection',
        name: '学生选课管理',
      },
      {
        link: '/home/graduationAudit',
        name: '毕业审核',
      },
    ])

    const selectedKeys = ref([menuItems[0].link])

    watchEffect(() => {
      router.push(selectedKeys.value[0])
    })

    return {
      menuItems,
      selectedKeys,
    }
  },
  components: {
    UserOutlined,
  },
}
</script>

<style scoped>
.home-layout-sider {
  position: fixed;
  left: 0;
  height: 100vh;
}
.logo {
  height: 32px;
  background: rgba(255, 255, 255, 0.2);
  margin: 16px;
  overflow-y: auto;
}
</style>
