<template>
  <div>
    <!-- 상단 메뉴  -->
    <NavBlack></NavBlack>

    <!-- 대제목 시작 -->
    <div class="cc_title_wrap">
      <div class="cc_title_top">
        <h2 class="cc_tit">고객센터</h2>
      </div>
    </div>
    <!-- 대제목 끝 -->

    <!-- 3대장 시작 -->
    <div class="container col-6">
      <ul class="cc_tab_wrap cc_outer">
        <div class="row">
          <div class="col">
            <li class="nav-item">
              <router-link to="/customer-center">
                <button class="cc_tab_tit" type="button" style="width: 100%">
                  <span>FAQ</span>
                </button>
              </router-link>
            </li>
          </div>
          <div class="col">
            <li class="nav-item">
              <router-link to="/notice">
                <button class="cc_tab_tit" type="button" style="width: 100%">
                  <span>공지사항</span>
                </button>
              </router-link>
            </li>
          </div>
          <div class="col">
            <li class="nav-item">
              <router-link to="/qna">
                <button
                  class="cc_tab_tit active"
                  type="button"
                  style="width: 100%"
                >
                  <span>1:1문의</span>
                </button>
              </router-link>
            </li>
          </div>
        </div>
      </ul>
    </div>
    <!-- 3대장 끝 -->
    
    <div v-if="currentQna">
      <!-- QnA 세부내용 시작 -->
      <div class="cc_tab_content mt-5">
        <div v-if="!modify">
          <table class="tb_acc_wrap">
            <thead>
              <tr>
                <!-- QnA - subject 들어갈 자리(수정가능) -->
                <th scope="col">
                  {{ currentQna.subject }}
                </th>
              </tr>
            </thead>
            <tbody>
              <tr class="tb_info">
                <td>
                  <span class="tit">등록일</span>
                  <span class="cont">{{ currentQna.insertTime }}</span>
                  <!-- <span class="tit">작성자ID</span> -->
                  <!-- <span class="cont">{{ currentUser.userID }}</span> -->
                </td>
              </tr>
              <tr class="tb_content">
                <td style="padding: 5px 0 !important;">
                  <div class="qna_textarea">
                    <textarea 
                    style="font-size: 17px; text-align: start; padding: 5px !important; height: 500px; border:1px solid lightgray; border-radius: 0 !important;"
                    cols="10"
                    rows="10"
                    id="iDcontents"
                    disabled
                    v-model="currentQna.text"
                    >
                  </textarea>
                  </div>
                </td>
              </tr>
              <tr class="tb_content">
                <td>
                  <div class="qna_textarea">
                    <pre>{{ currentQna.answer }}</pre>
                  </div>
                </td>
              </tr>
            </tbody>
          </table>
          <div class="btn_btm_wrap">
            <button @click="deleteQna" class="btn_col3 ty6">문의취소</button>
            <button @click="modifyQna" class="btn_col2 ty6">수정하기</button>
            <router-link to="/qna"
              ><button class="btn_col2 ty6">목록</button></router-link
            >
          </div>
        </div>

        <div v-else>
          <table class="tb_acc_wrap">
            <thead>
              <tr>
                <th scope="col">
                  <input
                    type="text"
                    class="ty2 w_full"
                    id="subject"
                    required
                    name="subject"
                    v-model="currentQna.subject"
                  />
                </th>
              </tr>
            </thead>
            <tbody>
              <tr class="tb_info">
                <td>
                  <span class="tit">등록일</span>
                  <!-- QnA - insertTime -->
                  <span class="cont">{{ currentQna.insertTime }}</span>
                  <!-- <span class="tit">작성자ID</span> -->
                  <!-- <span class="cont">{{ currentUser.userID }}</span> -->
                </td>
              </tr>
              <tr class="tb_content">
                <td>
                  <div class="qna_textarea">
                    <textarea
                      class="ty2"
                      cols="10"
                      rows="10"
                      id="text"
                      v-model="currentQna.text"
                      required
                    ></textarea>
                  </div>
                </td>
              </tr>
              <tr class="tb_content">
                <td>
                  <div class="qna_textarea">
                    <pre>{{ currentQna.answer }}</pre>
                  </div>
                </td>
              </tr>
            </tbody>
          </table>
          <div class="btn_btm_wrap">
            <button @click="deleteQna" class="btn_col3 ty6">문의취소</button>
            <button @click="updateQna" class="btn_col2 ty6">저장</button>
            <router-link to="/qna"
              ><button class="btn_col2 ty6">목록</button></router-link
            >
          </div>
          <div class="alert alert-success" role="alert" v-if="message">
            {{ message }}
          </div>
        </div>
      </div>
      <!-- 공지사항 세부내용 끝 -->
    </div>
  </div>
</template>

<script>
import NavBlack from "@/components/common/NavBlack.vue";
// axios 공통함수
import QnaDataService from "@/services/QnaDataService";

export default {
  data() {
    return {
      currentQna: null,
      message: "",
      modify: false,
    };
  },
  components: {
    NavBlack,
  },
  methods: {
    // 부서번호(qid)로 조회 요청하는 함수
    getQna(qid) {
      // axios 공통함수 호출
      QnaDataService.get(qid)
        // 성공하면 .then() 결과가 리턴됨
        .then((response) => {
          // springboot 결과를 리턴함
          this.currentQna = response.data;
          // 콘솔 로그 출력
          console.log(response.data);
        })
        // 실패하면 .catch() 에러메세지가 리턴됨
        .catch((e) => {
          console.log(e);
        });
    },
    modifyQna() {
      this.modify = true;
    },
    
    updateQna() {
      // axios 공통함수 호출
      QnaDataService.update(this.currentQna.qid, this.currentQna)
        // 성공하면 then() 결과가 전송됨
        .then((response) => {
          console.log(response.data);
          this.message = "문의내용을 성공적으로 수정하였습니다.";
          alert(this.message)
          this.modify=false;
        })
        // 실패하면 .catch() 에러메세지가 전송됨
        .catch((e) => {
          console.log(e);
        });
    },
    // 부서정보를 삭제 요청하는 함수
    deleteQna() {
      // axios 공통함수 호출
      QnaDataService.delete(this.currentQna.qid)
        // 성공하면 then() 결과가 전송됨
        .then((response) => {
          console.log(response.data);
          // 첫페이지(전체목록_조회_페이지) 강제 이동 : /qna
          alert("게시물 삭제에 성공하였습니다.")
          this.$router.push("/qna");
        })
        // 실패하면 .catch() 에러메세지가 전송됨
        .catch((e) => {
          console.log(e);
        });
    },
  },
  // 화면이 뜨자 마자 실행되는 이벤트
  mounted() {
    this.message = "";
    this.getQna(this.$route.params.qid);
  },
};
</script>

<style scoped>
/* 포괄 css */
button {
  border: 0px solid #ddd;
  cursor: pointer;
  background-color: #fff;
}
input[type="password"],
input[type="text"],
input[type="number"],
input[type="tel"] {
  height: 40px;
  line-height: 40px;
  padding: 0 18px;
  border-radius: 4px;
  -webkit-border-radius: 4px;
  -moz-border-radius: 4px;
  -ms-border-radius: 4px;
  -o-border-radius: 4px;
  background-color: #fff;
  font-size: 14px;
  box-sizing: border-box;
  border: 1px solid #ddd;
}
input,
select,
button {
  vertical-align: middle;
  appearance: none;
  -webkit-appearance: none;
  -moz-appearance: none;
}
a,
a:link,
a:visited,
a:active,
a:hover {
  color: #000;
  text-decoration: none;
  cursor: pointer;
}
ul,
ol {
  list-style: none;
}

/* 3가지 버튼 css */
.cc_title_wrap {
  margin-top: 50px;
}
.cc_title_top {
  width: 980px;
  margin: auto;
  height: 50px;
  border-bottom: 1px solid #eee;
}
.cc_title_top .cc_tit {
  float: left;
  font-size: 24px;
  color: #000;
  line-height: 50px;
}
.cc_tab_wrap.cc_outer {
  padding-top: 20px;
}
.cc_tab_wrap {
  position: relative;
}
.cc_tab_tit {
  height: 50px;
  color: #000;
}
.cc_tab_tit.active {
  border-bottom: 2px solid #111;
}
.cc_tab_content {
  overflow: inherit;
  position: relative;
  width: 980px;
  height: auto;
  margin: 40px auto;
}
/* Table css */
.tb_acc_wrap {
  width: 980px;
  margin: 5px auto;
  border-collapse: collapse;
  border-spacing: 0;
  border: 0;
}
.tb_acc_wrap th,
.tb_acc_wrap td {
  position: relative;
  border-bottom: 1px solid #eeeeee;
  padding: 15px 20px;
  font-size: 15px;
  line-height: 24px;
}
.tb_acc_wrap thead th {
  border-top: 1px solid #bbbbbb;
  border-bottom-color: #bbbbbb;
  font-weight: bold;
}
.tb_info > td {
  background: #f8f8f8;
  padding-left: 61px;
}
.tit {
  color: #666666;
}
.tb_info > td .tit:after {
  content: "";
  display: inline-block;
  background: #666;
  width: 1px;
  height: 14px;
  margin: 0 20px;
  vertical-align: middle;
}
.tb_info > td .cont + .tit {
  margin-left: 120px;
}
.tb_content > td {
  padding: 30px 60px;
}
#divNoticeContents p {
  min-height: 26px;
}
.tb_content > td p {
  line-height: 22px;
}
.tb_link > td {
  padding: 0;
}
.tb_link > td a {
  display: block;
  padding: 18px 20px 18px 61px;
  color: #666666;
}
.tb_link > td a .tb_next {
  background-image: url(@/assets/images/icon/arr_up_13.png);
}
.tb_link > td a .tb_prev {
  background-image: url(@/assets/images/icon/arr_dw_13.png);
}
.tb_link > td a .tb_next,
.tb_link > td a .tb_prev {
  background-repeat: no-repeat;
  background-position: 100% 54%;
  margin-right: 30px;
  padding-right: 24px;
  color: #000;
}

/* 목록 버튼 css */
.btn_btm_wrap {
  margin: 60px 0;
  text-align: center;
}
.btn_col2.ty6,
.btn_col3.ty6 {
  height: 45px;
  line-height: 43px;
  padding: 0 18px;
}
.btn_btm_wrap a,
.btn_btm_wrap button {
  margin: 0 3px;
  min-width: 160px;
}
.btn_col2 {
  display: inline-block;
  box-sizing: border-box;
  border-radius: 4px;
  border: 1px solid #414141;
  font-size: 14px;
  color: #ffffff !important;
  text-align: center;
  vertical-align: middle;
  background-color: #414141;
}
.btn_col3 {
  display: inline-block;
  box-sizing: border-box;
  border-radius: 4px;
  border: 1px solid #ff243e;
  font-size: 14px;
  color: #fff !important;
  text-align: center;
  vertical-align: middle;
  background-color: #ff243e;
}

.w_full {
  width: 100%;
}
.qna_textarea {
  position: relative;
}
.qna_textarea textarea {
  margin-bottom: 5px;
}
textarea.ty2 {
  background-color: #f8f8f8;
  border-color: #ddd;
}
textarea {
  width: 100%;
  line-height: 1.5;
  box-sizing: border-box;
  padding: 13px 18px;
  border: 1px solid #ddd;
  resize: none;
  font-size: 14px;
  border-radius: 4px;
  -webkit-border-radius: 4px;
  -moz-border-radius: 4px;
  -ms-border-radius: 4px;
  -o-border-radius: 4px;
}
</style>
