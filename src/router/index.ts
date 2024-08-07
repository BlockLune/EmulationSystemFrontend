import { createRouter, createWebHashHistory } from "vue-router";

import DashboardLayout from "~/layouts/DashboardLayout.vue";
import LoginLayout from "~/layouts/LoginLayout.vue";

import Login from "~/pages/Login.vue";
import SituationAwareness from "~/pages/SituationAwareness.vue";
import Image from "~/pages/SimulationRange/Image.vue";
import Container from "~/pages/SimulationRange/Container.vue";
import AttackDefenseExercise from "~/pages/SimulationRange/AttackDefenseExercise.vue";
import Range from "~/pages/SimulationRange/Range.vue";
import LoopHoleDig from "~/pages/SimulationRange/LoopHoleDig.vue";
import Task from "~/pages/SimulationRange/Task.vue";
import LoopHoleController from "~/pages/LoopHole/LoopHoleController.vue";
import Role from "~/pages/SystemSettings/Role.vue";
import User from "~/pages/SystemSettings/User.vue";
import Config from "~/pages/SystemSettings/Config.vue";

const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    component: LoginLayout,
    children: [
      {
        path: '',
        component: Login
      }
    ]
  },
  {
    path: '/dashboard',
    component: DashboardLayout,
    redirect: '/dashboard/situation_awareness',
    children: [
      {
        path: 'situation_awareness',
        component: SituationAwareness
      },
      {
        path: 'simulation_range',
        redirect: 'simulation_range/image',
        children: [
          {
            path: 'image',
            component: Image
          },
          {
            path: 'container',
            component: Container
          },
          {
            path: 'attack_defense_exercise',
            component: AttackDefenseExercise
          },
          {
            path: 'range',
            component: Range
          },
          {
            path: 'dig',
            component: LoopHoleDig
          },
          {
            path: 'task',
            component: Task
          }
        ]
      },
      {
        path: 'loophole',
        component: LoopHoleController
      },
      {
        path: 'settings',
        redirect: 'settings/role',
        children: [
          {
            path: 'role',
            component: Role
          },
          {
            path: 'user',
            component: User
          },
          {
            path: 'config',
            component: Config
          }
        ]
      }
    ]
  }
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
