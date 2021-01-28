<template>
  <div class="home-container">
    <nav class="sidebar">
      <div class="icon-button-box">
        <icon-component
          name="sessionList"
          @click="goto('/sessionList')"
          active
        />
      </div>
      <div class="icon-button-box">
        <icon-component name="friendList" @click="goto('/friendList')" />
      </div>
      <div class="icon-button-box" style="position: absolute;bottom: 0;">
        <icon-component name="settings" @click="goto('/settings')" />
      </div>
      <div class="icon-button-box">
        <icon-component name="logOut" @click="goto('/logOut')" />
      </div>
    </nav>
    <div class="listbar"></div>
    <div class="contentarea">
      <div class="message-panel"></div>
      <div class="input-area"></div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import IconComponent from '../components/Icon.vue'

export default defineComponent({
  name: 'Home',
  components: { IconComponent },
  setup() {
    return {
      goto: (routeName: string) => {
        console.log(routeName)
      },
    }
  },
})
</script>

<style scoped>
.home-container {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-columns: 50px minmax(200px, 25%) auto;
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
