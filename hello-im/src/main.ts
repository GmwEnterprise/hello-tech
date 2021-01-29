import 'normalize.css'
import '@/assets/css/global.css'
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import useElement from './plugins/element-plus'

const app = createApp(App)
app.use(store)
app.use(router)
useElement(app)
app.mount('#app')
