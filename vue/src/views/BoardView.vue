<template>
<table style="width: 900px;" border="1">
    <tr>
        <td>게시글ID</td>
        <td>
            {{boardVO.brdNo}}
        </td>
    </tr>
    <tr>
        <td>작성일</td>
        <td>
            {{boardVO.brdRegDt}}
        </td>
    </tr>
    <tr>
        <td>작성자</td>
        <td>
            {{boardVO.brdWriter}}
        </td>
    </tr>
    <tr>
        <td>조회수</td>
        <td>
            {{boardVO.brdHit}}
        </td>
    </tr>
    <tr>
        <td>제목</td>
        <td>
            {{boardVO.brdTitle}}
        </td>
    </tr>

    <tr>
        <td>내용</td>
        <td>
             {{ boardVO.brdContent }}

        </td>
    </tr>
    <tr>
        <td>첨부파일</td>
        <td>
            <div v-if="file!=null">
                    파일이름:
                    {{file.fileRealName}} 크기:
                    {{file.fileSize}} byte
                    <input type="button" value="버튼" @click="fnDownload">
            </div>
            <br />
        </td>
    </tr>
</table>
<table style="width: 900px;">
    <th>
<form v-on:submit="fnModify">
            <input type="hidden" v-model="modify" name="action" />
            <input type="hidden" v-model="boardVO.brdNo" name="id" />
            <input type="button" value="수정"/>
        </form>
    </th>
    <th>
<button  @click="fnReply" >답글</button>
    </th>
    <th>
        <button a @click="fnDelete" class="off">삭제</button>
    </th>
    <th>
        <button a @click="fnGoBack">돌아가기</button>
    </th>
</table>
<hr style="width: 900px; float:left;" />
<!-- 게시글 본문 끝 -->
<br>
<!-- 댓글 리스트 영역 -->
<!-- <c:forEach var="li" items="${list}" varStatus="status">
    <div style="border: 1px solid gray; width: 900px; padding: 5px; margin-top: 5px;
                  margin-left: <c:out value=" ${20*li.reDepth}" />px; display: inline-block">
    <c:out value="${li.reWriter}" />
    <c:out value="${li.reRegDt}" />
    <a id="delete" href='commentDelete?id=${li.reNo}&bid=${board.brdNo}&reUser=${li.reWriter}'>삭제</a>
    <a id="reply" href='/page/board/commentReply?id=${li.reNo}&bid=${board.brdNo}'>답글</a>
    <br />
    <c:out value="${li.reContent}" escapeXml="false" />
    </div>
    <br />
</c:forEach> -->
<!-- 댓글 리스트 끝 -->
<!-- 댓글 입력 영역 -->
<div style="border: 1px solid gray; width: 900px; padding: 5px; margin-top: 5px;
         display: inline-block">
    <form action="/page/board/commentPost" method="post">
        <input TYPE="hidden" NAME="reBrdNo" SIZE=10 value='<c:out value="${board.brdNo}" />'>
        <div class="form-group">
        </div>
        <textarea class="form-control" rows="5" cols="70" id="reContent" name="reContent" placeholder="댓글입력"
            required="required"></textarea>
        <button type="submit">등록</button>
    </form>
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
        brdNo: '-1',
        brdWriter: 'unknown_user',
        brdTitle: 'unknow_title',
        brdOrigin: '-1',
        brdParents: '-1',
        brdDepth: '-1',
        brdOrder: '-1',
        brdContent: 'unknown_textarea',
        brdRegDt: '2022-04-11 00:00:00.000',
        brdHit: '-1',
        brdReCnt: '-1'
      },
      rePlyList: [
        { brdTitle: '테스트 글제목', brdWriter: 'foo', brdRegDt: '2022-03-31', brdHit: '3', brdReCnt: '0' },
        { brdTitle: '테스트 글제목', brdWriter: 'foo', brdRegDt: '2022-03-31', brdHit: '3', brdReCnt: '0' },
        { brdTitle: '테스트 글제목', brdWriter: 'foo', brdRegDt: '2022-03-31', brdHit: '3', brdReCnt: '0' }
      ],
      id:
        { id: '' },
      file: [
        {
          fileName: '', fileNo: '', fileRandomNo: '', fileRealName: '', fileSize: '', fileBrdNo: ''
        }
      ],
      result: ''
    }
  },
  beforeCreate () {},
  created () {
    this.id.id = this.$route.params.id
    this.getBoardDetail(this.id.id)
  },
  beforeMount () {},
  mounted () {},
  beforeUpdate () {},
  updated () {},
  beforeUnmount () {},
  unmounted () {},
  methods: {
    getBoardDetail (parameter) {
      console.info(parameter + '번 게시글 불러오기 성공')
      axios
        .get('http://localhost:8080/board/boardview?id=' + parameter)
        .then((res) => {
          this.boardVO = res.data.boardVO
          this.rePlyList = res.data.rePlyList
          this.pageVO = res.data.pageVO
          this.file = res.data.file
          console.log(this.file)
          console.log('겟액션')
        })
        .catch((error) => {
          console.log(error)
        })
        .finally(() => {
          console.log('항상 마지막에 실행')
        })
    },
    fnDownload () {
      const FileDownload = require('js-file-download')
      this.file.fileBrdNo = this.boardVO.brdNo
      axios
        .post('http://localhost:8080/board/fileDownload', this.file, { responseType: 'blob' })
        .then((res) => {
          console.log(res.staus)
          console.log(res.data)
          this.boardList = res.data.boardList
          this.userId = res.data.userId
          this.pageVO = res.data.pageVO
          FileDownload(res.data, this.file.fileRealName)
          console.log('겟액션')
        })
        .catch((error) => {
          console.log(error)
        })
        .finally(() => {
          console.log('항상 마지막에 실행')
        })
    },
    fnDelete () {
      axios
        .post('http://localhost:8080/board/boarddelete?id=' + this.boardVO.brdNo, this.$store.state)
        .then((res) => {
          console.log(res.data)
          this.result = res.data.status
        })
        .catch((error) => {
          console.log(error)
        })
        .finally(() => {
          switch (this.result) {
            case 'success':
              this.$router.push('/board')
              alert('삭제가 성공적으로 완료됐습니다')
              break
            case 'tokenNotMatch':
              alert('토큰이 검증되지 않았습니다. 로그인 페이지로 이동합니다')
              this.$store.token = ''
              this.$store.isLogin = false
              VueCookies.remove('token')
              this.$router.push('/logout')
              break
            case 'noAuthDelete' :
              alert('삭제할 수 있는 권한이 없습니다.')
              break
            case 'childExsist' :
              alert('최하위 레벨의 게시글만 삭제가 가능합니다. 자식이 존재하여 삭제할 수 없습니다.')
              break
          }
          console.log('항상 마지막에 실행')
        })
    },
    fnGoBack () {
      this.$router.go(-1)
    },
    fnModify () {
      this.$router.push(
        {
          name: 'write',
          params:
        {
          id: this.boardVO.brdNo,
          origin: this.boardVO.origin,
          action: 'modify'
        }
        }
      )
    },
    fnReply () {
      this.$router.push(
        {
          name: 'write',
          params:
        {
          id: this.boardVO.brdNo,
          origin: this.boardVO.origin,
          action: 'reply'
        }
        }
      )
    }
  }
}
</script>
<style scope>

</style>
