<template>
<fieldset>
    <legend class="blind">로그인</legend>
    <div>
    <form @submit.prevent="fnLogin()">
        <input type="text" id="id" name="userId" v-model="User.userId" class="int id bg" title="아이디" placeholder="아이디" maxlength="20">
        <input type="password" id="pw" name="userPwd" v-model="User.userPwd" class="int pw bg" title="비밀번호 입력" placeholder="비밀번호" maxlength="40">
        <input type="text" name="userDomain" v-model="User.domain" placeholder="도메인"/>
        <button type="submit" class="btn_blue small btn_wfull">로그인</button>
    </form>
    </div>

</fieldset>
</template>
<script>
import axios from 'axios'
export default {
  components: {},
  data () {
    return {
      User: {
        userId: 'bar',
        userPwd: 'barpwd',
        domain: 'acorp'
      },
      redir: '',
      isSuccess: false
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
    fnLogin () {
      axios
        .post('http://localhost:8080/api/auth/login', this.User)
        .then((res) => {
          console.log(res.staus)
          console.log(res.data)
          this.redir = res.data.data
          this.isSuccess = res.data.success
          console.log('포스트액션')
        })
        .catch((error) => {
          console.log(error)
        })
        .finally((res) => {
          if (this.isSuccess) {
            this.$router.push(this.redir)
          } else {
            alert('로그인에 실패했습니다')
          }
          // this.$router.push('/board')
        })
    }
  }
}
</script>
