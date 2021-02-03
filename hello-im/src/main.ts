import 'normalize.css'
import '@/assets/css/global.css'
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import useElement from './utils/element-plus'
import vContextMenu from './utils/v-context-menu'

const app = createApp(App)
app.use(store)
app.use(router)

// 引用插件、指令
useElement(app)
vContextMenu(app)

// 挂载
app.mount('#app')
