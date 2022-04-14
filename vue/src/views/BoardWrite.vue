<template>
<div id="top" class="dcwrap width1160">
  <main id="container">
    <section>
      <article id="write_wrap" class="clear">
        <h2 class="blind">갤러리 글쓰기 영역</h2>
        <form method="post" name="write" id="write" action="/board/forms/article_submit" autocomplete="off"
          novalidate="novalidate">
          <div class="clear">
            <fieldset>
              <legend class="blind">글쓰기 정보 입력폼</legend>
              <div class="input_box input_write_tit">
                <input id="subject" class="put_inquiry f_blank" type="text" name="subject" maxlength="40" v-model="boardVO.brdTitle" placeholder="" />
              </div>
            </fieldset>
          </div>
          <div class="editor_wrap">
              <div class="tx-editor-container">
              <textarea v-model="boardVO.brdContent"> </textarea>
                </div>
          </div>
          <div class="btn_box write fr">
            <button type="button" class="btn_grey cancle" onclick="$('#leave_confirm_box').show();">취소</button>
            <button type="image" class="btn_blue btn_svc write" @click="fnPost">등록</button>
          </div>
        </form>
      </article>
    </section>
  </main>
</div>
</template>

<script>
import axios from 'axios'
import VueCookies from 'vue-cookies'
export default {
  components: {},
  data () {
    return {
      boardVO: {
        brdTitle: '테스트 글제목',
        brdWriter: VueCookies.get('token'),
        brdContent: '글내용등록함'
      }

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
      console.log(this.boardVO)
      axios
        .post('http://localhost:8080/board/boardpost?action=write', this.boardVO)
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
      this.$router.push('/board')
    },
    fnModify () {
      axios
        .post('http://localhost:8080/board/boardpost?action=modify&brdNo=' + this.boardVO.brdNo, this.boardVO)
        .then((res) => {
        })
        .catch((error) => {
          console.log(error)
        })
        .finally(() => {
          console.log('항상 마지막에 실행')
        })
    },
    fnReply () {
      axios
        .post('http://localhost:8080/board/boardpost?action=reply&brdNo=' + this.boardVO.brdNo + '&brdDepth=' + this.boardVO.brdDepth, this.boardVO)
        .then((res) => {
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
<style scoped>
html, body, div, span, iframe, h1, h2, h3, h4, h5, h6, p, a, em, img, strong, b, u, center, dl, dt, dd, ol, ul, li, fieldset, form, label, legend, table, caption, tbody, tfoot, thead, tr, th, td, article, aside, embed, figure, figcaption, footer, header, nav, section, summary, audio, video, button {
    margin: 0;
    padding: 0;
    border: 0;
    vertical-align: baseline;
    background: transparent;
    font-style: normal;
}
body, button, input, select, table, textarea {
    font-size: 12px;
    font-family: -apple-system,BlinkMacSystemFont,"Apple SD Gothic Neo","Malgun Gothic","맑은 고딕",arial,Dotum,돋움,sans-serif;
}
#container {
    margin: 20px auto 0;
    width: 1160px;
}

#write_wrap {
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
.input_write_tit {
    float: left;
    width: 728px;
    margin: 10px 0 0 0PX;
}
.input_box {
    position: relative;
}
.put_inquiry, .input_box label {
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
.input_write_tit .put_inquiry {
    width: 702px;
}
.put_inquiry {
    width: 204px;
    height: 33px;
    padding: 0 12px;
    line-height: 35px;
    border: 1px solid #cecdce;
    color: #333;
}
.editor_wrap {
    border-top: 2px solid #29367c;
    margin-top: 25px;
}
.tx-editor-container {
    clear: both;
    font-family: "Malgun Gothic","맑은 고딕","Apple SD Gothic Neo",tahoma,sans-serif;
    font-size: 12px;
    line-height: 1.5;
    text-align: left;
    position: relative;
    zoom: 1;
}
.btn_box.write {
    position: relative;
    margin-top: 6px;
}
.fr {
    float: right;
}
.btn_grey {
    background: #666;
    border-color: #444;
    text-shadow: 0px -1px #474747;
    color: #fff;
}
.btn_blue, .btn_grey{
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
.btn_blue {
    background: #3b4890;
    border-color: #29367c;
    text-shadow: 0px -1px #1d2761;
    color: #fff;
}
</style>
