<template>
  <nav>
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link> |
    <router-link to="/board">BBS</router-link> |
    <router-link  v-show="!this.$store.state.isLogin" to="/login">Login</router-link>
    <a v-show="this.$store.state.isLogin" @click="logOut">로그아웃</a>
  </nav>
    <router-view/>
</template>
<script>
import VueCookies from 'vue-cookies'
export default {
  data () {
    return {
      userId: sessionStorage.getItem('token')
    }
  },
  beforeCreate () {},
  created () {},
  beforeMount () {},
  mounted () {
    this.userId = sessionStorage.getItem('token')
  },
  beforeUpdate () {},
  updated () {},
  beforeUnmount () {},
  unmounted () {},
  methods: {
    logOut () {
      this.$store.userId = ''
      this.$store.token = ''
      this.$store.isLogin = false
      VueCookies.remove('token')
      alert('로그아웃 되었습니다')
      this.$router.push(
        {
          name: 'logout'
        }
      )
    }
  }
}
</script>
