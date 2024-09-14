import 'element-plus/theme-chalk/dark/css-vars.css'
import './styles/index.css'
import 'uno.css'

import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import pinia from "./stores";

const app = createApp(App);
app.use(router);
app.use(pinia);
app.mount("#app");
