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
import SituationAwareness from "./components/pages/SituationAwareness.vue";
import SimulationRangeMirror from "./components/pages/SimulationRange/Mirror.vue";
import SimulationRangeContainer from "./components/pages/SimulationRange/Container.vue";
import SimulationRangeAttackDefenseExercise from "./components/pages/SimulationRange/AttackDefenseExercise.vue";
import SimulationRangeRange from "./components/pages/SimulationRange/Range.vue";
import SimulationRangeDig from "./components/pages/SimulationRange/LoopHoleDig.vue";
import SimulationRangeTask from "./components/pages/SimulationRange/Task.vue";
import Loophole from "./components/pages/Loophole/LoopHoleController.vue";
import SettingsRole from "./components/pages/SystemSettings/Role.vue";
import SettingsUser from "./components/pages/SystemSettings/User.vue";
import SettingsConfig from "./components/pages/SystemSettings/Config.vue";


const routes = [
  { path: "/", component: Home },
  { path: "/situation_awareness", component: SituationAwareness },
  { path: "/simulation_range/mirror", component: SimulationRangeMirror },
  { path: "/simulation_range/container", component: SimulationRangeContainer },
  { path: "/simulation_range/attack_defense_exercise", component: SimulationRangeAttackDefenseExercise },
  { path: "/simulation_range/range", component: SimulationRangeRange },
  { path: "/simulation_range/dig", component: SimulationRangeDig },
  { path: "/simulation_range/task", component: SimulationRangeTask },
  { path: "/loophole", component: Loophole },
  { path: "/settings/role", component: SettingsRole },
  { path: "/settings/user", component: SettingsUser },
  { path: "/settings/config", component: SettingsConfig },
];
const router = createRouter({
  history: createMemoryHistory(),
  routes,
});

const app = createApp(App);
// app.use(ElementPlus);
app.use(router);
app.mount("#app");
