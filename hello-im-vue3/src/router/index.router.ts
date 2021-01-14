import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";

const routes: RouteRecordRaw[] = [
  {
    path: "/",
    redirect: "/components",
  },
  {
    path: "/components",
    component: () => import("@/views/Components.vue"),
  },
  {
    path: "/login",
    component: () => import("@/views/Login.vue"),
  },
  {
    path: "/main",
    component: () => import("@/views/MainPage.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
