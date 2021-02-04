<template>
  <ul class="context-menu" :style="positionValue" v-show="show">
    <li
      class="menu-item"
      v-for="(option, index) in items"
      :key="index"
      @click="clickEvent($event, option.action)"
      @contextmenu="stopContextMenuEvent"
    >
      {{ option.title }}
    </li>
  </ul>
</template>

<script lang="ts">
import { computed, defineComponent } from 'vue'
import { useStore } from '@/store'

export default defineComponent({
  setup() {
    // 注入vuex state
    const store = useStore()
    const show = computed(() => store.state.contextMenu.show)
    const positionValue = computed(() => store.state.contextMenu.positionStyle)
    const items = computed(() => store.state.contextMenu.items)

    return {
      show,
      positionValue,
      items,
      maxItemWidth() {
        let width = 3
        items.value.forEach((item) => {
          width = item.title.length > width ? item.title.length : width
        })
        return width * 13 // 字符数 * 13，13为font-size的值
      },
      clickEvent(event: Event, action: () => void) {
        event.stopPropagation()
        action()
        store.commit('hide')
      },
      stopContextMenuEvent(event: Event) {
        // 阻止菜单内右键事件
        event.preventDefault()
        event.stopPropagation()
      },
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
  height: 27px;
  line-height: 27px;
  font-size: 13px;
  padding: 0 20px;
}

li.menu-item:hover {
  background-color: #ddd;
  cursor: context-menu;
}
</style>
