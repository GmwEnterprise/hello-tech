import { createApp } from 'vue';
import App from './App.vue';
// import HelloWorld from './components/HelloWorld.vue';

// const img = require('./assets/logo.png'); // e**slint-disable-line

/* const App = defineComponent({
  render: () =>
    h('div', { id: 'app' }, [
      h('img', { alt: 'Vue logo', src: img }),
      h(HelloWorld, {
        msg: 'Welcome to Your Vue.js + TypeScript App',
        age: 24,
      }),
    ]),
}); */

createApp(App).mount('#app');
