import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: '/login',
    },
    {
      path: '/home',
      name: 'Home',
      component: () => import('../views/Home.vue'),
      children: [
        {
          path: 'students',
          component: () => import('../views/cruds/Students.vue'),
        },
        {
          path: 'courses',
          component: () => import('../views/cruds/Courses.vue'),
        },
        {
          path: 'courseSelection',
          component: () => import('../views/cruds/CourseSelection.vue'),
        },
        {
          path: 'graduationAudit',
          component: () => import('../views/cruds/GraduationAudit.vue'),
        },
      ],
    },
    {
      path: '/login',
      name: 'Login',
      component: () => import('../views/Login.vue'),
    },
    {
      path: '/about',
      name: 'About',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () =>
        import(/* webpackChunkName: "about" */ '../views/About.vue'),
    },
  ],
})

export default router
