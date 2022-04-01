<template>
<fieldset>
    <legend class="blind">로그인</legend>
    <div>
        <input type="text" id="id" name="user_id" value="bar" class="int id bg" title="식별 코드 입력" placeholder="식별 코드" maxlength="20">
        <input type="password" id="pw" name="pw" value="barpwd" class="int pw bg" title="비밀번호 입력" placeholder="비밀번호" maxlength="40">
    </div>
    <button type="submit" class="btn_blue small btn_wfull">로그인</button>
</fieldset>
</template>
<script>
export default {
  components: {},
  data () {
    return {}
  },
  beforeCreate () {},
  created () {},
  beforeMount () {},
  mounted () {},
  beforeUpdate () {},
  updated () {},
  beforeUnmount () {},
  unmounted () {},
  methods: {}
}
</script>
