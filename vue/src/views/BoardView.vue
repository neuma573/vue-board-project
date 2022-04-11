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
                <form action="/board/fileDownload" method="post">
                    파일이름:
                    {{file.fileRealName}} 크기:
                    {{file.fileSize}} byte
                    <input name="fileRandomNo" type="hidden" v-model="file.fileRandomNo">
                    <input name="brdNo" type="hidden" v-model="boardVO.brdNo">
                    <input type="submit" value="버튼" @click="fnDownload">
                </form>
            </div>
            <br />
        </td>
    </tr>
</table>
<table style="width: 900px;">
    <th>
        <form action="#" method="post">
            <input type="hidden" value="modify" name="action" />
            <input type="hidden" value="${board.brdNo}" name="id" />
            <input type="submit" value="수정" />
        </form>
    </th>
    <th>
        <form action="/page/board/boardWrite" method="post">
            <input type="hidden" value="reply" name="action" />
            <input type="hidden" value="${board.brdNo}" name="id" />
            <input type="hidden" value="${board.brdOrigin}" name="origin" />
            <input type="submit" value="답글" />
        </form>
    </th>
    <th>
        <button id="delete" onclick="location.href='boardDelete?id=${board.brdNo}'">삭제</button>
    </th>
    <th>
        <a href="/board">돌아가기</a>
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
      ]

    }
  },
  beforeCreate () {},
  created () {
    console.log(this.$route.params.id)
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
      console.info(parameter)
      axios
        .get('http://localhost:8080/board/boardview?id=' + parameter)
        .then((res) => {
          console.log(res.staus)
          console.log(res.data)
          this.boardVO = res.data.boardVO
          this.rePlyList = res.data.rePlyList
          this.pageVO = res.data.pageVO
          this.file = res.data.file
          console.log('겟액션')
          this.paging()
        })
        .catch((error) => {
          console.log(error)
        })
        .finally(() => {
          console.log('항상 마지막에 실행')
        })
    },
    fnDownload () {
      this.file.fileBrdNo = this.boardVO.brdNo
      axios
        .post('http://localhost:8080/board/fileDownload', this.file)
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
    }
  }
}
</script>
<style scope>

</style>
