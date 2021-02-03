<template>
  <div id="app-content">
    <router-view />
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'App',
})
</script>

<style>
#app {
  font-family: 'Helvetica Neue', Helvetica, 'PingFang SC', 'Hiragino Sans GB',
    'Microsoft YaHei', '微软雅黑', Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  position: relative;
  height: 100vh;
  width: 100vw;
  overflow: hidden;
  display: grid;
  place-items: center;
  z-index: 1;
}

#app::after {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-image: url('./assets/images/background.jpg');
  background-repeat: no-repeat;
  background-size: cover;
  background-position: top center;
  filter: blur(7px);
  margin: -30px;
  /* background-attachment: fixed; */
  z-index: -1;
}

#app-content {
  width: 100vw;
  height: 100vh;
  max-width: 960px;
  max-height: 800px;
  box-sizing: border-box;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  background-color: rgba(255 255 255 / 80%);
}
</style>
