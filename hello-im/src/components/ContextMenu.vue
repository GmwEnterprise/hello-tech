<template>
  <ul class="context-menu" :style="contextMenuStyle">
    <li
      class="menu-item"
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
      top: props.showOptions.top + 'px',
      left: props.showOptions.left + 'px',
    })
    return {
      contextMenuStyle,
    }
  },
})
</script>

<style scoped>
ul.context-menu {
  margin: 0;
  list-style-type: none;
  padding: 0;
  box-shadow: 0 2px 4px rgb(0 0 0 / 12%), 0 0 6px rgb(0 0 0 / 4%);
  z-index: 100;
  position: fixed;
  background-color: rgb(255, 255, 255);
}

li.menu-item {
  height: 25px;
  line-height: 25px;
  font-size: 14px;
  padding: 0 0.5em;
  border-bottom: 0.5px solid gray;
}

li.menu-item:hover {
  cursor: pointer;
}

li.menu-item:last-child {
  border-bottom: 0;
}
</style>
