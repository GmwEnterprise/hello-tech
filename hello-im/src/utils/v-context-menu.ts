import { App, createApp } from 'vue'
import ContextMenu from '@/components/ContextMenu.vue'
import { MenuItem } from '@/common.type'

export default function vContextMenu(app: App<Element>) {
  // 添加一个div容器
  const contextMenuContainer = document.createElement('div')
  contextMenuContainer.id = 'v-context-menu-container'
  document.body.appendChild(contextMenuContainer)

  // 添加一个组件队列
  const components: App<Element>[] = []

  // 添加全局指令
  app.directive('context-menu', {
    mounted(el: HTMLElement, { value }) {
      // 添加右键事件
      el.oncontextmenu = (event: MouseEvent) => {
        event.preventDefault()

        // 关闭先前的菜单
        while (components.length > 0) {
          console.log('close one')
          const menu = components.pop()
          menu?.unmount(contextMenuContainer)
        }

        // 获取鼠标点位
        const left = event.clientX,
          top = event.clientY,
          bottom = window.innerHeight - top,
          right = window.innerWidth - left

        // 右键菜单点击了项目以后就会关闭菜单
        const items = value as MenuItem[]
        items.forEach((item) => {
          item.action = () => {
            item.action()
            // 执行完菜单点击后关闭菜单
            while (components.length > 0) {
              const menu = components.pop()
              menu?.unmount(contextMenuContainer)
            }
          }
        })

        // 创建Menu
        const menu = createApp(ContextMenu, {
          showOptions: {
            top,
            bottom,
            left,
            right,
            items,
          },
        })

        // 显示Menu
        menu.mount(contextMenuContainer)
        components.push(menu)
      }
    },
  })
}
