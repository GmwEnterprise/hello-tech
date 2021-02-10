<template>
  <div class="home-container">
    <nav class="sidebar">
      <div class="icon-button-box">
        <icon-component name="me" type="img" />
      </div>
      <div class="icon-button-box">
        <icon-component
          name="sessionList"
          :active="currentTagIndex === 0"
          @click="currentTagIndex = 0"
        />
      </div>
      <div class="icon-button-box">
        <icon-component
          name="friendList"
          :active="currentTagIndex === 1"
          @click="currentTagIndex = 1"
        />
      </div>
      <div class="icon-button-box" style="position: absolute;bottom: 0;">
        <icon-component name="settings" />
      </div>
      <div class="icon-button-box">
        <icon-component name="search" />
      </div>
      <div class="icon-button-box">
        <icon-component name="logOut" />
      </div>
    </nav>
    <keep-alive><component :is="currentTag"></component></keep-alive>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, computed } from 'vue'
import IconComponent from '@/components/Icon.vue'
import SessionsView from './Sessions.vue'
import FriendsView from './Friends.vue'
import { useStore, Actions } from '@/store'

export default defineComponent({
  name: 'Home',
  components: { IconComponent, SessionsView, FriendsView },
  setup() {
    // 动态标签切换页面
    const tags = ['sessions-view', 'friends-view'],
      currentTagIndex = ref(0),
      currentTag = computed(() => tags[currentTagIndex.value])

    // 用户信息
    const store = useStore(),
      userMsg = computed(() => store.state.userMsg)
    if (!store.state.userMsg.detail) {
      // 向后端发起请求获取当前用户信息 todo
      store.dispatch(Actions.updateCurrentUser)
    }
    return {
      // 动态组件
      tags,
      currentTagIndex,
      currentTag,
      // 用户信息
      userMsg,
    }
  },
})
</script>

<style scoped>
.home-container {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: space-between;
  /* grid-template-columns: 50px 1fr; */
}

.home-container > .sidebar {
  width: 50px;
  background-color: #303133;
  position: relative;
  box-sizing: border-box;
  border-bottom: 0.3em solid #303133;
  border-top: 0.3em solid #303133;
}

.home-container > .sidebar > .icon-button-box {
  width: 50px;
  height: 50px;
  display: grid;
  place-items: center;
}
</style>
