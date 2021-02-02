<template>
  <ul class="context-menu" v-show="show">
    <li v-for="(item, index) in items" :key="index" @click="item.action()">
      {{ item.title }}
    </li>
  </ul>
</template>

<script lang="ts">
import { defineComponent, PropType } from 'vue'
import { MenuItem, ShowOption } from '@/interfaces'

export default defineComponent({
  name: 'ContextMenu',
  props: {
    items: {
      type: Array as PropType<MenuItem[]>,
      required: true,
    },
    showOption: {
      type: Object as PropType<ShowOption>,
      required: false,
    },
  },
  setup() {
    return {
      show: false,
    }
  },
})
</script>
