<template>
<section>
    <article id="write_wrap" class="clear">
        <form method="post" name="write" id="write" action="/board/forms/article_submit" autocomplete="off"
            novalidate="novalidate">
            <div class="clear">
                <fieldset>
                    <legend class="blind">글쓰기 정보 입력폼</legend>
                    <div class="input_box input_write_tit">
                        <input id="subject" class="put_inquiry f_blank" type="text" name="subject" maxlength="40" v-model="brdTitle" placeholder="여기를 수정해보세요" />
                    </div>
                </fieldset>
            </div>
            <div class="btn_box write fr">
                <button type="button" class="btn_grey cancle">취소</button>
                <button type="image" @click="fnPost">등록</button>
            </div>
        </form>
    </article>
</section>
</template>

<script>
import axios from 'axios'
export default {
  components: {},
  data () {
    return {
      brdWriter: '1',
      brdTitle: '1',
      brdContent: '테스트 내용'
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
    fnPost () {
      console.log(this.data)
      axios
        .post('http://localhost:8080/board/boardpost', this.data)
        .then((res) => {
          console.log(res.staus)
          console.log(res.data)
          this.boardList = res.data.boardList
          this.userId = res.data.userId
          this.pageVO = res.data.pageVO
          console.log('겟액션')
          this.paging()
        })
        .catch((error) => {
          console.log(error)
        })
        .finally(() => {
          console.log('항상 마지막에 실행')
        })
      this.$router.push('/board/boardview')
    }

  }
}
</script>
<style scoped>
article, aside, figcaption, figure, footer, header, nav, section, main {
    display: block;
}
html, body, div, span, iframe, h1, h2, h3, h4, h5, h6, p, a, em, img, strong, b, u, center, dl, dt, dd, ol, ul, li, fieldset, form, label, legend, table, caption, tbody, tfoot, thead, tr, th, td, article, aside, embed, figure, figcaption, footer, header, nav, section, summary, audio, video, button {
    margin: 0;
    padding: 0;
    border: 0;
    vertical-align: baseline;
    background: transparent;
    font-style: normal;
}
.clear:after {
    clear: both;
    display: block;
    visibility: hidden;
    content: "";
}
body, button, input, select, table, textarea {
    font-size: 12px;
    font-family: -apple-system,BlinkMacSystemFont,"Apple SD Gothic Neo","Malgun Gothic","맑은 고딕",arial,Dotum,돋움,sans-serif;
}
#write_wrap{
    border-color: #484848;
    padding: 33px 68px 40px;
    margin-top: 15px;
    border: 2px solid #d5d5d5;
}
.blind {
    position: absolute;
    overflow: hidden;
    visibility: hidden;
    margin: -1px;
    width: 0px;
    height: 0px;
    top: -9999px;
    font-size: 0;
}
.input_write_tit, .input_box label {
    float: left;
    width: 728px;
    margin: 10px 0 0 0PX;
}
.input_box label {
    font-family: -apple-system,BlinkMacSystemFont,"Apple SD Gothic Neo","Malgun Gothic","맑은 고딕",arial,굴림,Gulim,sans-serif;
    color: #999;
    font-size: 13px;
    font-weight: bold;
}
.txt_placeholder {
    position: absolute;
    left: 13px;
    top: 9px;
}
.put_inquiry {
    width: 204px;
    height: 33px;
    padding: 0 12px;
    line-height: 35px;
    border: 1px solid #cecdce;
    color: #333;
}
.put_inquiry, .input_box label {
    font-family: -apple-system,BlinkMacSystemFont,"Apple SD Gothic Neo","Malgun Gothic","맑은 고딕",arial,굴림,Gulim,sans-serif;
    color: #999;
    font-size: 13px;
    font-weight: bold;
}
input, select, textarea, button {
    border-radius: 0;
    vertical-align: middle;
}
.btn_box.write {
    position: relative;
    margin-top: 6px;
}
.fr {
    float: right;
}
.darkmode .btn_grey, .darkmode .pop_wrap .btn_cancle {
    background: #444;
    border-color: #333;
    text-shadow: 0px -1px #363636;
    color: #d3d3d3;
}
.darkmode .btn_blue, .darkmode .btn_inout, .darkmode .pop_wrap .btn_apply, .darkmode .btn_visit, .darkmode.dgallog .state.on, .darkmode .guide_cont.mng .btn_codeok, .darkmode.dgallog .bluebox {
    background: #333d77;
    border-color: #273272;
    text-shadow: 0px -1px #1b2351;
    color: #d2d4e0;
}
.btn_blue, .btn_lightblue, .btn_grey, .btn_lightgrey, .btn_white, .btn_red, .btn_jeangrey, .btn_mediumgrey {
    width: 85px;
    height: 40px;
    padding-right: 2px;
    line-height: 36px;
    margin-left: 3px;
    border-width: 1px 1px 3px 1px;
    border-style: solid;
    border-radius: 2px;
    font-size: 14px;
    font-weight: bold;
}
.input_write_tit .put_inquiry {
    width: 702px;
}
</style>
