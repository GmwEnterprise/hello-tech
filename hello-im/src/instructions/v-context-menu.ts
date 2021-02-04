import { App } from 'vue'
import ContextMenu from '@/components/ContextMenu.vue'
import { ContextMenuState, MenuItem } from '@/common.type'
import { store } from '@/store'

export default function vContextMenu(app: App<Element>) {
  app.component('v-context-menu', ContextMenu)
  app.directive('context-menu', {
    mounted(el: HTMLElement, { value }) {
      el.oncontextmenu = (mouseEvent: MouseEvent) => {
        mouseEvent.stopPropagation()
        mouseEvent.preventDefault()

        // 计算右键点击位置
        const left = mouseEvent.clientX,
          right = window.innerWidth - left,
          top = mouseEvent.clientY,
          bottom = window.innerHeight - top

        // 计算当前contextMenu宽度，单位为px
        let width = 0
        const items = value as MenuItem[]
        items.forEach((item) => {
          width = item.title.length > width ? item.title.length : width
        })
        width = width * 13 + 40

        // 计算高度
        const height = (items.length > 0 ? items.length : 1) * 27

        // 整合计算
        const showTop = height >= bottom,
          showLeft = width >= right
        const result: ContextMenuState = {
          show: true,
          positionStyle: {
            [showTop ? 'bottom' : 'top']: (showTop ? bottom : top) + 'px',
            [showLeft ? 'right' : 'left']: (showLeft ? right : left) + 'px',
          },
          items,
        }

        // FIXME 为什么在这里无法使用 useStore 方法，只能使用 store
        store.commit('updateAndShow', result)
      }
    },
  })

  // 添加隐藏菜单指令
  const eventAction = () => {
    if (store.state.contextMenu.show) {
      store.commit('hide')
    }
  }
  document.addEventListener('click', eventAction, false)
  document.addEventListener('contextmenu', eventAction, false)
}
