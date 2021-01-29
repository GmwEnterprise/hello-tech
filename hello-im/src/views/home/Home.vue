<template>
  <div class="home-container">
    <nav class="sidebar">
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

export default defineComponent({
  name: 'Home',
  components: { IconComponent, SessionsView, FriendsView },
  setup() {
    const tags = ['sessions-view', 'friends-view'],
      currentTagIndex = ref(0),
      currentTag = computed(() => tags[currentTagIndex.value])
    return {
      // 动态组件
      tags,
      currentTagIndex,
      currentTag,
    }
  },
})
</script>

<style scoped>
.home-container {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-columns: 50px 1fr;
}

.home-container > .sidebar {
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
