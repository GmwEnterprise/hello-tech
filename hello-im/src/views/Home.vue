<template>
  <nav class="sidebar">
    <div class="icon-button-box">
      <el-button icon="el-icon-search" circle></el-button>
    </div>
    <div class="icon-button-box">
      <el-button icon="el-icon-search" circle></el-button>
    </div>
  </nav>
</template>

<script lang="ts">
import { defineComponent, onMounted } from 'vue'

export default defineComponent({
  name: 'Home',
  setup() {
    onMounted(() => {
      //
      console.log('onMounted')
    })
    return {}
  },
})
</script>

<style scoped>
.sidebar {
  width: 50px;
  height: 100%;
  background-color: #303133;
}

.icon-button-box {
  width: 50px;
  height: 50px;
  display: grid;
  place-items: center;
}
</style>
