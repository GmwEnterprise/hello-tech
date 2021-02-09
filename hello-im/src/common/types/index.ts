export enum IconName {
  SESSION_LIST = 'sessionList',
  FRIEND_LIST = 'friendList',
  SETTINGS = 'settings',
  LOG_OUT = 'logOut',
  SEARCH = 'search',
}

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

export interface ContextMenuState {
  // 是否显示
  show: boolean
  // 坐标
  positionStyle: object
  // 菜单选项
  items: MenuItem[]
}

export interface MenuItem {
  // 选择项标题
  title: string
  // 操作
  action: () => void
  // 是否可点击
  available?: boolean
}
