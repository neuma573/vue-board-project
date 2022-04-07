<template>
<div>
  접속유저 : {{ test }}
</div>
</template>

<script>

import axios from 'axios'
export default {
  data () {
    return {
      test: 'test_id'
    }
  },
  setup () {},
  created () {
    this.getData()
  },
  mounted () {},
  unmounted () {},
  methods: {
    getData () {
      axios
        .get('http://localhost:8080/ServerDataTest')
        .then((res) => {
          console.log(res.staus)
          console.log(res.data)
          this.test = res.data
        })
        .catch((error) => {
          console.log(error)
        })
        .finally(() => {
          console.log('항상 마지막에 실행')
        })
    }
  }
}
</script>
