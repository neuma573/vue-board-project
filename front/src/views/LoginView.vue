<template>
<div id="top" class="width868 login_wrap">
  <main id="container">
    <div class="content login">
      <article>
        <section>
          <div class="con_box login_page kap_codewrap">
            <div class="login_inputbox">
              <div class="inner">
                <form action="#" name="login" method="post" accept-charset="utf-8">
                  <fieldset>

                    <div>
                      <input type="text" id="id" name="user_id" value="" class="int id bg" title="아이디" placeholder="아이디" maxlength="20">
                      <input type="password" id="pw" name="pw" class="int pw bg" title="비밀번호 입력" placeholder="비밀번호" maxlength="40">
                    </div>
                    <button type="submit" class="btn_blue small btn_wfull">로그인</button>
                  </fieldset>
                </form>
              </div>
            </div>
          </div>
        </section>
      </article>
    </div>
  </main>
</div>
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
<style scope>
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
    width: 868px;
    margin: 20px auto 0;
}
.login_wrap #container {
    margin-top: 37px;
    margin-bottom: 37px;
}
.content.login {
    margin: 205px auto 233px;
}
.login_wrap .login_page.kap_codewrap {
    height: 300px;
}
.login_wrap .login_page {
    width: 700px;
    height: 300px;
    margin: 0 auto;
    border: 2px #29367c solid;
    border-radius: 3px;
}
.login_inputbox {
    float: unset;
    background: #fff;
}
.login_inputbox .inner {
    width: 50;
    padding: 50px 50px 50px 56px;
}
fieldset {
    margin-top: 10px;
    display: block;
    margin-inline-start: 2px;
    margin-inline-end: 2px;
    padding-block-start: 0.35em;
    padding-inline-start: 0.75em;
    padding-inline-end: 0.75em;
    padding-block-end: 0.625em;
    min-inline-size: min-content;
    border-width: 2px;
    border-style: groove;
    border-color: threedface;
    border-image: initial;
}

.login_inputbox .int.bg {
    height: 41px;
    border: none;
    line-height: 41px;
}
.int {
    width: 100%;
    height: 35px;
    padding: 0px 9px 0 9px;
    line-height: 35px;
    border: 1px #cecdce solid;
    box-sizing: border-box;
    font-size: 14px;
    color: #555;
}
input.int.bg:-webkit-autofill, input.int.bg:-webkit-autofill:hover, input.int.bg:-webkit-autofill:focus, input.int.bg:-webkit-autofill:active {
    -webkit-box-shadow: 0 0 0 100px #f3f3f3 inset !important;
    -webkit-text-fill-color: #555 !important;
}
.login_inputbox .int.bg {
    height: 41px;
    border: none;
    line-height: 41px;
}
.login_inputbox .inner input, .bg_box .form_box input {
    margin-top: 7px;
}
.int.bg {
    background: #f3f3f3;
}
.btn_blue.small.btn_wfull {
    border-radius: 0;
    width: 100%;
    height: 41px;
    line-height: 39px;
    margin: 7px 0 0 0;
    font-size: 14px;
    overflow: hidden;
}
.btn_blue {
    background: #3b4890;
    border-color: #29367c;
    text-shadow: 0px -1px #1d2761;
    color: #fff;
}
</style>
