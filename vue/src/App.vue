<template>
  <nav>
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link> |
    <router-link to="/board">BBS</router-link> |
    <router-link  v-show="this.userId==null" to="/login">Login</router-link>
    <a v-show="this.userId!=null" @click=logOut()>Logout</a>
  </nav>
  <router-view/>
</template>
<script>
import VueCookies from 'vue-cookies'
export default {
  data () {
    return {
      userId: VueCookies.get('token'),
      menuName: 'Login'
    }
  },
  beforeCreate () {},
  created () {},
  beforeMount () {},
  mounted () {},
  beforeUpdate () {},
  updated () {},
  beforeUnmount () {},
  unmounted () {},
  methods: {
    logOut () {
      alert('로그인 해주세요')
      VueCookies.remove('token')
    }
  }
}
</script>
