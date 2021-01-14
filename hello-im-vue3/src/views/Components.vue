<template>
  <div id="components-container">组件示例</div>
</template>

<script lang="ts">
import { defineComponent, onMounted } from "vue";

export default defineComponent({
  name: "Components",
  setup() {
    const init = () => {
      console.log("init函数");
    };
    onMounted(init);
    return { init };
  },
});
</script>
