<template>
  <div class="hello">
    <h1>{{ age + ':' + msg }}</h1>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'HelloWorld',
  props: {
    msg: String,
    age: {
      type: Number,
      required: true,
    },
  },
});
</script>
