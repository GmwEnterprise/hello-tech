<template>
  <!-- 背景 -->
  <div id="g-bg">
    <!-- 主窗体 -->
    <div id="main-container">
      <router-view></router-view>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  name: "App",
});
</script>

<style scoped>
#g-bg {
  background: url("~@/assets/Akl.jpg") no-repeat top center;
  background-size: cover;
  width: 100vw;
  height: 100vh;
  display: grid;
  place-items: center center;
  overflow: hidden;
}
#main-container {
  width: 800px;
  height: 95%;
  max-height: 600px;
  background-color: #fff;
}
</style>
