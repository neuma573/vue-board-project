<template>
<div class="wrap_inner">
                      접속유저 : {{ userId }} | {{ sortType }}
    <main id="container">
<section class="board_section">
    <header>
        <div class="board_head">
            <div class="fl">
                <h2><em>테스트 게시판 </em></h2>

                </div>
                </div>
        </header>
<article>

        <div class="wrap_button">

            <div class="button_group">
    <label><input type="radio" v-bind:value="sortByOrder" v-model="sortType"> 계층순으로 정렬 </label>
    <label><input type="radio" v-bind:value="sortByRegDt" v-model="sortType"> 생성순으로 정렬 </label>
            </div>
<div class="right_box">
<input type="number" v-model.number="pageVO.displayRowCount" /> <button type="button" @click="postDisplayRowCount">전송</button>
</div>
        </div>

<table class="board_list">
    <colgroup>
        <col style="width:7%">
        <col>
        <col style="width:18%">
        <col style="width:10%">
        <col style="width:6%">
    </colgroup>
    <thead>
        <tr>
            <th scope="col">번호</th>
            <th scope="col">제목</th>
            <th scope="col">글쓴이</th>
            <th scope="col">작성일</th>
            <th scope="col">조회</th>
        </tr>
    </thead>
    <tbody>
        <tr :key="i" v-for="(board, i) in boardList">
            <td class="board_num"> {{ pageVO.displayRowCount * (pageVO.page-1)+i+1 }} </td>
            <td class="board_title">
                <a @click="fnDetail(board.brdNo)">{{ board.brdTitle }}</a>
                <a class="reply_cnt" href="#">
                    <span class="reply_num">[{{ board.brdReCnt }}]
                    </span>
                </a>
            </td>
            <td class="board_writer">
                <span class="user_id in"><em>{{ board.brdWriter }}</em></span>
            </td>
            <td>{{ board.brdRegDt }}
            </td>
            <td>{{ board.brdHit }}
            </td>
          </tr>
<tr v-if="boardList.length == 0">
<td colspan="4">데이터가 없습니다.</td>
</tr>
    </tbody>
</table>
        <div class="wrap_button">
            <div class="button_group">
              <button a @click="fnAdd" class="off">글쓰기</button>
            </div>
        </div>

<div v-show="pageVO.totPage>1" class="bottom_paging_wrap">
<div class="bottom_paging_box iconpaging">
<a v-show="pageVO.hasPrev" href="#" class="sp_pagingicon">☜</a>
        <a :key="i" v-for="(pageVO, i) in (pageVO.pageStart, pageVO.pageEnd)" @click="getPage(i+1)">{{ i+1 }}</a>
</div>
</div>
</article>
</section>
    </main>
</div>
<!-- ㅋㅋ -->
</template>
<script>
import axios from 'axios'
export default {
  components: {},
  data () {
    return {
      sortByOrder: '게시글 계층 정렬',
      sortByRegDt: '게시글 최신순 정렬',
      userId: '',
      boardList: [
        { brdTitle: '테스트 글제목', brdWriter: 'foo', brdRegDt: '2022-03-31', brdHit: '3', brdReCnt: '0', brdNo: '-1' },
        { brdTitle: '테스트 글제목', brdWriter: 'foo', brdRegDt: '2022-03-31', brdHit: '3', brdReCnt: '0', brdNo: '-1' },
        { brdTitle: '테스트 글제목', brdWriter: 'foo', brdRegDt: '2022-03-31', brdHit: '3', brdReCnt: '0', brdNo: '-1' }
      ],
      sortType: '게시글 계층 정렬',
      selectChild: '자식선택',
      selectParents: '부모선택',
      boardSelectByLvl: [],
      pageVO: [],
      rowData: '',
      keyword: '',
      pageNum: ''
    }
  },
  beforeCreate () {},
  created () {
    let a = 1
    console.log(a)
    a = this.$route.params.page
    console.log(a)
    this.getData(a)
  },
  beforeMount () {},
  mounted () {},
  beforeUpdate () {},
  updated () {},
  beforeUnmount () {},
  unmounted () {},
  methods: {

    getData (parameter) {
      console.log(parameter)
      axios
        .get('http://localhost:8080/board?page=' + parameter)
        .then((res) => {
          console.log(res.staus)
          console.log(res.data)
          this.boardList = res.data.boardList
          this.userId = res.data.userId
          this.pageVO = res.data.pageVO
          console.log('겟액션')
        })
        .catch((error) => {
          console.log(error)
        })
        .finally(() => {
          console.log('항상 마지막에 실행')
        })
    },
    getPage (i) {
      console.log(i)
      this.getData(i)
      this.$router.push(
        {
          name: 'boardListPaging',
          params:
        {
          page: i
        }
        }
      )
    },
    fnAdd () {
      this.$router.push('/board/write')
    },
    fnDetail (index) {
      console.log(index)
      this.$router.push(
        {
          name: 'boardview',
          params:
        {
          id: index
        }
        }
      )
    }

  }
}
</script>
<style scope>
.right_box {
    float: right;
    display: -ms-grid;
    min-width: 90px;
    height: 21px;
    width: max-content;
    width: -moz-max-content;
    -ms-grid-columns: max-content;
    vertical-align: top;
}
.wrap_inner {
  position: relative;
  width: 1450px;
  margin: 0 auto;
}
#container {
  margin: 20px auto 0;
  width: 1160px;
}
.board_section {
    width: 100%;
}
.board_head {
    height: 37px;
    margin-bottom: 3px;
    padding-top: 4px;
}
.board_head h2 {
    margin: -2px 8px 0 3px;
}
.board_head h2 {
    font-size: 24px;
    font-family: 'Nanum Gothic', sans-serif;
    letter-spacing: -1px;
    margin: 2px 8px 0 3px;
    max-width: 416px;
    text-overflow: ellipsis;
    overflow: hidden;
    white-space: nowrap;
    color: #29367c;
}
.board_head h2 em {
    color: #29367c;
    font-style: normal;
}
.fl {
    float: left;
}
.wrap_button {
    display: table;
    width: 100%;
    height: 32px;
    margin: 15px 0 10px;
}
.button_group {
    display: table-cell;
    width: 1%;
    white-space: nowrap;
    vertical-align: top;
}

.button_group button.on {
    border: 1px solid #29367c;
    background: #3b4890;
    color: #fff;
    text-shadow: 0px -1px #1d2761;
}
.button_group button:first-child {
    margin-left: 0;
}
.button_group button {
    width: 82px;
    height: 32px;
    border: 1px solid #ccc;
    border-radius: 2px;
    font-size: 14px;
    font-weight: bold;
    color: #333;
    margin-left: -2px;
    background: #fff;
    padding-bottom: 2px;
}
input, select, textarea, button {
    border-radius: 0;
    vertical-align: middle;
}
th {
    height: 37px;
    border-width: 2px 0 1px;
    border-style: solid;
    border-color: #29367c;
    vertical-align: middle;
    text-align: center;
    color: #333;
    font-family: -apple-system,BlinkMacSystemFont,"Apple SD Gothic Neo","Malgun Gothic","맑은 고딕",arial,Dotum,돋움,sans-serif;
}
table {
    border-collapse: collapse;
    border-spacing: 0;
    table-layout: fixed;
    font-size: 12px;
}
.board_list {
    width: 100%;
    border-bottom: 1px solid #29367c;
    font-family: -apple-system,BlinkMacSystemFont,"Apple SD Gothic Neo","Malgun Gothic","맑은 고딕",arial,굴림,Gulim,sans-serif;
}
.board_list td {
    height: 25px;
}
.board_list .board_num, .board_list .board_date, .board_list board_count {
    font-family: tahoma,sans-serif;
    font-size: 11px;
    padding-top: 1px;
}
.board_list td {
    padding: 0px 4px;
}
.board_title a {
    padding-top: 1px;
    line-height: 100%;
}
.board_title {
    position: relative;
    text-align: left;
    font-size: 13px;
}
.board_title a {
    display: inline-block;
    max-width: 82%;
    vertical-align: middle;
    text-overflow: ellipsis;
    white-space: nowrap;
    overflow: hidden;
}
a {
    color: #333;
    text-decoration: none;
}
td {
    padding: 2px 4px;
    border-top: 1px solid #eee;
    vertical-align: middle;
    text-align: center;
    color: #333;
}
.board_title a.reply_cnt {
    width: auto;
    vertical-align: text-bottom;
    letter-spacing: -0.05em;
}
.reply_cnt {
    font-size: 12px;
    color: #999;
}
.board_list td {
    height: 25px;
}
.board_writer {
    padding-top: 2px;
}
.user_id.in {
    font-style: normal;
    max-width: 81%;
}
.user_id {
    display: inline-block;
    max-width: 64%;
    vertical-align: top;
    text-overflow: ellipsis;
    overflow: hidden;
    white-space: nowrap;
    font-style: normal;
}
.user_id.in em {
    line-height: 13px;
    font-style: normal;
}
.bottom_paging_wrap {
    display: inline-block;
    width: 100%;
    margin-top: 5px;
    position: relative;
    padding: 0 121px;
    box-sizing: border-box;
    border: 0;
    vertical-align: baseline;
    background: transparent;
    font-style: normal;
    margin-bottom: 5px;
}
.bottom_paging_box {
    position: relative;

    text-align: center;
    line-height: 20px;
    margin-top: 3px;
    padding-top: 6px;
}
.bottom_paging_box em {
    text-decoration: underline;
    color: #d31900;
    display: inline-block;
    font-size: 14px;
    font-weight: bold;
    font-style: normal;
}
.bottom_paging_box a, .bottom_paging_box em, .bottom_paging_box span {
    display: inline-block;
    margin-left: 9px;
    font-size: 14px;
    font-weight: bold;
    font-style: normal;
}
a {
    color: #333;
    text-decoration: none;
}
.sp_pagingicon {
    background-position: -1px -24px;
    margin: 0 10px 0 19px;
    width: 12px;
    height: 14px;
    padding: 0;
}
</style>
