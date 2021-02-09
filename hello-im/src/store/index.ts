import { aboutMe } from '@/api'
import { ContextMenuState, MenuItem } from '@/common/types'
import { InjectionKey } from 'vue'
import { createStore, Store, useStore as baseUseStore } from 'vuex'
import { ImUserDto } from '@/common/types/domain.type'

export const Mutations = {
  updateAndShow: 'updateAndShow',
  hide: 'hide',
  tokenUpdate: 'tokenUpdate',
  updateCurrentUser: 'updateCurrentUser',
}

export const Actions = {
  updateCurrentUser: 'updateCurrentUser',
}

// define your typings for the store state
export interface State {
  contextMenu: {
    // 是否显示
    show: boolean
    // 坐标
    positionStyle: object
    // 菜单选项
    items: MenuItem[]
  }
  userMsg: {
    // 令牌
    token: string | null
    detail?: {
      id: number
      username?: string
      bindPhone?: string
      bindEmail?: string
      headPortraitUrl?: string
    }
  }
}

// define injection key
export const key: InjectionKey<Store<State>> = Symbol()

export const store = createStore<State>({
  state: {
    contextMenu: {
      show: false,
      positionStyle: {},
      items: [],
    },
    userMsg: {
      token: localStorage.getItem('token'),
    },
  },
  mutations: {
    [Mutations.updateAndShow](state: State, payload: ContextMenuState) {
      console.debug('mutations.updateAndShow')
      state.contextMenu.show = payload.show
      state.contextMenu.positionStyle = payload.positionStyle
      state.contextMenu.items = payload.items
    },
    [Mutations.hide](state: State) {
      console.debug('mutations.hide')
      state.contextMenu.show = false
    },
    [Mutations.tokenUpdate](state: State, payload: string) {
      console.debug('mutations.tokenUpdate')
      state.userMsg.token = payload
      localStorage.setItem('token', payload)
    },
    [Mutations.updateCurrentUser](state: State, payload: ImUserDto) {
      state.userMsg.detail = {
        id: payload.id,
        username: payload.username,
        bindPhone: payload.bindPhone,
        bindEmail: payload.bindEmail,
        headPortraitUrl: payload.headPortraitUrl,
      }
    },
  },
  actions: {
    [Actions.updateCurrentUser]({ commit }) {
      aboutMe().then((data) => {
        commit(Mutations.updateCurrentUser, data.body)
      })
    },
  },
})

// define your own `useStore` composition function
export function useStore() {
  return baseUseStore(key)
}
