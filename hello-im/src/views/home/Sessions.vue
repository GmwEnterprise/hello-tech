<template>
  <div class="sessions-view">
    <div class="session-list">
      <div
        v-for="(session, index) in sessionList"
        :key="index"
        @click="activeSession(index)"
        :class="session.active ? 'session session-active' : 'session'"
      >
        <img src="@/assets/images/me.jpg" />
        <div class="session-title">
          <div class="session-title-head">
            <span class="session-title-head-name">{{
              session.senderUsername
            }}</span>
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

enum MessageDirection {
  FROM_OTHER_SIDE,
  FROM_ME,
}

interface Msg {
  // 消息是发给对方的 还是对方发来的
  direction: MessageDirection
  // 消息发送时间
  sendTime: Date
  // 消息接收时间
  receiveTime: Date
  // 消息文本内容
  wordContent: string
}

interface Session {
  // 用户ID
  senderUserId: number
  // 用户名
  senderUsername: string
  // 用户头像链接
  senderHeadPortraitUrl: string
  // 会话是否激活
  active: boolean
  // 未读消息数目
  unreadCount: number
  // 消息
  msgList: Array<Msg>
}

export default defineComponent({
  name: 'SessionsView',
  setup() {
    const sessionList = ref([
      {
        senderUserId: 1,
        senderUsername: '阿甘',
        senderHeadPortraitUrl: '',
        active: false,
      },
      {
        senderUserId: 2,
        senderUsername: '童童',
        senderHeadPortraitUrl: '',
        active: false,
      },
    ] as Array<Session>)
    const activeSession = (index: number) =>
      sessionList.value.forEach((item, i) => {
        item.active = i === index
      })
    return {
      sessionList,
      activeSession,
    }
  },
})
</script>

<style scoped>
.sessions-view {
  display: grid;
  grid-template-rows: 1fr minmax(200px, 25%);
  grid-template-columns: 250px 1fr;
}

.session-list {
  grid-row: 1 / span 2;
  background-color: #f2f6fc;
  width: 100%;
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
