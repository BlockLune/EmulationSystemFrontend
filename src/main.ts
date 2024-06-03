import { createApp } from "vue";
import App from "./App.vue";

// import "~/styles/element/index.scss";

// import ElementPlus from "element-plus";
// import all element css, uncommented next line
// import "element-plus/dist/index.css";

// or use cdn, uncomment cdn link in `index.html`

import "~/styles/index.scss";
import "uno.css";

// If you want to use ElMessage, import it.
import "element-plus/theme-chalk/src/message.scss";

import { createMemoryHistory, createRouter } from "vue-router";
import Home from "./components/pages/Home.vue";
const routes = [
  { path: "/", component: Home },
  { path: "", component: },
  { path: "", component: },
  { path: "", component: },
  { path: "", component: },
  { path: "", component: },
];
const router = createRouter({
  history: createMemoryHistory(),
  routes,
});

const app = createApp(App);
// app.use(ElementPlus);
app.use(router);
app.mount("#app");
