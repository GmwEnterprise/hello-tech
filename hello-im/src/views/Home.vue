<template>
  <div id="home">
    <!-- <div id="home-content">
      <h1>Hello, vue</h1>
      <p>
        Creating a Single-page Application with Vue + Vue Router feels natural:
        with Vue.js, we are already composing our application with components.
        When adding Vue Router to the mix, all we need to do is map our
        components to the routes and let Vue Router know where to render them.
        Here's a basic example:
      </p>
    </div> -->
  </div>
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
#home {
  position: relative;
  height: 100vh;
  width: 100vw;
  overflow: hidden;
  display: grid;
  place-items: center;
  z-index: 1;
}

#home::after {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-image: url('../assets/images/background.jpg');
  background-repeat: no-repeat;
  background-size: cover;
  background-position: top center;
  filter: blur(5px);
  margin: -30px;
  /* background-attachment: fixed; */
  z-index: -1;
}

/* #home-content {
  width: 90%;
  max-width: 800px;
  height: 80%;
  max-height: 700px;
  box-sizing: border-box;
  padding: 15px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
} */
</style>
