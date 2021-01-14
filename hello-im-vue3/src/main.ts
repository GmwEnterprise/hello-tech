import { createApp } from "vue";
import App from "./App.vue";
import router from "./router/index.router";
import store from "./store/index.store";

createApp(App)
  .use(store)
  .use(router)
  .mount("#app");
