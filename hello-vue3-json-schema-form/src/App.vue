<template>
  <img alt="Vue logo" src="/logo.png" />
  <HelloWorld msg="Welcome to Your Vue.js + TypeScript App" />
  <p>{{ name + ' : ' + computedName }}</p>
</template>

<script lang="ts">
import { computed, defineComponent, PropType, ref, watchEffect } from 'vue';
import HelloWorld from './components/HelloWorld.vue';

interface Config {
  name: string;
}

export default defineComponent({
  name: 'App',
  props: {
    age: {
      type: Number as PropType<number>,
    },
    config: {
      type: Object as PropType<Config>,
    },
  },
  components: {
    HelloWorld,
  },
  setup() {
    const name = ref('Mrag');
    const computedName = computed(() => name.value + 2);
    watchEffect(() => console.log('watch: ' + name.value));
    // setInterval(() => (name.value += '_'), 1000);
    return {
      name,
      computedName,
    };
  },
  mounted() {
    console.log('mounted: ' + this.name);
  },
});
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
