<template>
  <div class="sessions-view">
    <div class="session-list">
      <div
        v-for="(item, index) in sessionList"
        :key="index"
        @click="markAsCurrent(index)"
        @contextmenu="contextMenu($event, index)"
        :class="{ session: true, 'session-active': item.active }"
      >
        <context-menu
          :showOption="item.contextMenuOption"
          :items="[
            { title: '标为已读', action: () => {} },
            { title: '置顶', action: () => {} },
            { title: '删除聊天', action: () => {} },
          ]"
        />
        <img src="@/assets/images/me.jpg" />
        <div class="session-title">
          <div class="session-title-head">
            <span class="session-title-head-name">{{ item.sessionName }}</span>
            <span class="session-title-head-newmessage-time">下午 4:58</span>
          </div>
        </div>
      </div>
    </div>
    <div class="message-area"></div>
    <div class="input-area"></div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue'
import ContextMenu from '@/components/ContextMenu.vue'
import { data } from './session-data'

export default defineComponent({
  name: 'SessionsView',
  components: { ContextMenu },
  setup() {
    // 会话数据
    const sessionList = ref(data)
    // 当前会话数组索引
    const currentIndex = ref(-1)
    // 标记点击会话为当前会话
    const markAsCurrent = (index: number): void => {
      sessionList.value[index].active = true
      if (currentIndex.value > -1) {
        sessionList.value[currentIndex.value].active = false
      }
      currentIndex.value = index
    }
    // 移除当前会话
    const removeSession = (index: number): void => {
      if (index === currentIndex.value) {
        currentIndex.value = -1
        sessionList.value.splice(index, 1)
      }
    }
    const contextMenu = (e: MouseEvent, index: number): void => {
      e.preventDefault() // 阻止浏览器默认右键菜单行为
      console.debug(`右键点击了[${sessionList.value[index].sessionName}]`)
      console.debug(
        `left: ${e.clientX}, top: ${e.clientY}, right: ${window.innerWidth -
          e.clientX}, bottom: ${window.innerHeight - e.clientY}`,
      )

      sessionList.value.forEach((session, i) => {
        i !== index && (session.contextMenuOption = { show: false })
      })

      sessionList.value[index].contextMenuOption = {
        show: true,
        clientLeft: e.clientX,
        clientRight: window.innerWidth - e.clientX,
        clientTop: e.clientY,
        clientBottom: window.innerHeight - e.clientY,
      }
    }
    return {
      sessionList,
      currentIndex,
      markAsCurrent,
      removeSession,
      contextMenu,
    }
  },
})
</script>

<style scoped>
.sessions-view {
  display: grid;
  grid-template-rows: 1fr minmax(200px, 25%);
  grid-template-columns: 250px 1fr;
  /* height: 100%;
  width: 100%; */
}

.session-list {
  grid-row: 1 / span 2;
  background-color: #f2f6fc;
  width: 100%;
  height: 100%;
}

.session {
  height: 65px;
  display: grid;
  grid-template-columns: 65px 1fr;
  place-items: center;
}

.session:hover {
  background-color: #e4e7ed;
}

.session.session-active {
  background-color: #dcdfe6;
}

.session > img {
  /* 头像 */
  width: 45px;
  height: 45px;
}

.session-title {
  width: 100%;
  height: 45px;
  padding-right: 10px;
  box-sizing: border-box;
}

.session-title-head {
  width: 100%;
  height: 50%;
  overflow: hidden;
  text-align: start;
  display: flex;
  justify-content: space-between;
}

.session-title-head-name {
  width: 60%;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-size: 14px;
  color: #000;
}

.session-title-head-newmessage-time {
  font-size: 12px;
  color: #909399;
}
</style>
