<template>
  <ul class="context-menu" :style="contextMenuStyle">
    <li
      v-for="(item, index) in showOptions.items"
      :key="index"
      @click="item.action()"
    >
      {{ item.title }}
    </li>
  </ul>
</template>

<script lang="ts">
import { defineComponent, PropType, reactive } from 'vue'
import { ShowOptions } from '@/common.type'

export default defineComponent({
  name: 'ContextMenu',
  props: {
    showOptions: {
      type: Object as PropType<ShowOptions>,
      required: true,
    },
  },
  setup(props) {
    // 计算右键菜单的宽度与高度
    const contextMenuStyle = reactive({
      position: 'fixed',
      width: '200px',
      height: '300px',
      top: props.showOptions.top + 'px',
      left: props.showOptions.left + 'px',
      'background-color': '#fff',
      border: '1px solid black',
      'z-index': 100,
    })
    return {
      contextMenuStyle,
    }
  },
})
</script>
