import HomeView from '../views/HomeView.vue'
import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const router = new VueRouter({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'HomeView',
      component: HomeView
    },
    {
      path: '/about',
      name: 'about',
      component: () => import('../views/AboutView.vue')
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/LoginView.vue')
    },
    {
      path: '/boardlist',
      name: 'boardlist',
      component: () => import('../views/BoardList.vue')
    },
    {
      path: '/boardview',
      name: 'boardview',
      component: () => import('../views/BoardView.vue')
    }
  ]
})

export default router
