import { createStore } from 'vuex'
import { auth } from './auth.module'

export default createStore({
  state: {
    userId: '',
    token: '',
    isLogin: false
  },
  getters: {
    userId: state => state.userId,
    token: state => state.token,
    isLogin: state => state.isLogin
  },
  mutations: {
    // memberId를 설정합니다.
    setUserId (state, userId) {
      state.userId = userId
    },
    // accessToken를 설정합니다.
    setToken (state, token) {
      state.token = token
    },
    // 초기화시킵니다.
    reset (state) {
      state.userId = ''
      state.token = ''
      state.isLogin = false
    }
  },
  actions: {
  },
  modules: {
    auth
  }
})
