import 'normalize.css'
import '@/assets/css/global.css'
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import { store, key } from './store'
import useElement from './plugins/element-plus'
import vContextMenu from './instructions/v-context-menu'

const app = createApp(App)
app.use(store, key) // 只有在组件中才能使用 useStore，其他地方要使用vuex 必须直接引入store实例
app.use(router)

// 引用插件、指令
useElement(app)
vContextMenu(app)

// 挂载
app.mount('#app')
