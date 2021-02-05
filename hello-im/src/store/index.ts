import { ContextMenuState, MenuItem } from '@/types'
import { InjectionKey } from 'vue'
import { createStore, Store, useStore as baseUseStore } from 'vuex'

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
  },
  mutations: {
    updateAndShow(state: State, payload: ContextMenuState) {
      console.debug('mutations.updateAndShow')
      state.contextMenu.show = payload.show
      state.contextMenu.positionStyle = payload.positionStyle
      state.contextMenu.items = payload.items
    },
    hide(state: State) {
      console.debug('mutations.hide')
      state.contextMenu.show = false
    },
  },
})

// define your own `useStore` composition function
export function useStore() {
  return baseUseStore(key)
}
