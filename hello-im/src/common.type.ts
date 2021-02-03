export enum MessageDirection {
  FROM_OTHER_SIDE,
  FROM_ME,
}

export interface Msg {
  // 消息是发给对方的 还是对方发来的
  direction: MessageDirection
  // 消息发送时间
  sendTime: Date
  // 消息接收时间
  receiveTime: Date
  // 消息文本内容
  wordContent: string
}

export interface Session {
  // 会话ID
  sessionId: number
  // 会话名
  sessionName: string
  // 会话头像链接
  sessionHeadPortraitUrl: string
  // 会话是否激活
  active: boolean
  // 未读消息数目
  unreadCount?: number
  // 消息
  msgList?: Array<Msg>
}

export interface MenuItem {
  // 选择项标题
  title: string
  // 是否带有图标
  icon?: string
  // 操作
  action: () => void
}

export interface ShowOptions {
  // 点击位置距浏览器可视区域左边缘位置
  left?: number
  // 点击位置距浏览器可视区域右边缘位置
  right?: number
  // 点击位置距浏览器可视区域上边缘位置
  top?: number
  // 点击位置距浏览器可视区域下边缘位置
  bottom?: number
  // 右键菜单选项
  items: MenuItem[]
}
