import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import VueCookies from 'vue-cookies'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/login',
    name: 'loginView',
    component: () => import(/* webpackChunkName: "about" */ '../views/LoginView.vue'),
    meta: { unauthorized: true }
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/serverdata',
    name: 'ServerData',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "ServerData" */ '../views/ServerData.vue')
  },
  {
    path: '/board:page',
    name: 'boardListPaging',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "ServerData" */ '../views/BoardList.vue')
  },
  { path: '/board', redirect: '/board1' },
  {
    path: '/board/write',
    name: 'write',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "ServerData" */ '../views/BoardWrite.vue')
  },
  {
    path: '/board/boardview:id',
    name: 'boardview',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "ServerData" */ '../views/BoardView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

router.beforeEach(async (to, from, next) => {
  if (VueCookies.get('token') === null && VueCookies.get('refresh_token') !== null) {
    await VueCookies.refreshToken()
  }

  if (to.matched.some(record => record.meta.unauthorized) || VueCookies.get('token')) {
    return next()
  }

  alert('로그인 해주세요')
  return next('/login')
})

export default router
