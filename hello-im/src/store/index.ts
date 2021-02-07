import { ContextMenuState, MenuItem } from '@/types'
import { InjectionKey } from 'vue'
import { createStore, Store, useStore as baseUseStore } from 'vuex'

export const Mutations = {
  updateAndShow: 'updateAndShow',
  hide: 'hide',
  tokenUpdate: 'tokenUpdate',
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
  },
})

// define your own `useStore` composition function
export function useStore() {
  return baseUseStore(key)
}
