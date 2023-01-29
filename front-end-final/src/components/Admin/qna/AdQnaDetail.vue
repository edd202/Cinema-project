<template>
  <div>
    <!-- 상단 메뉴  -->
    <NavBlack></NavBlack>

    <div class="admin-background">
      <!-- 대제목 시작 -->
      <div class="cc_title_wrap">
        <div class="cc_title_top">
          <h2 class="cc_tit">관리자 - 1:1문의</h2>
        </div>
      </div>
      <!-- 대제목 끝 -->

      <!-- 3대장 시작 -->
      <div class="container col-6">
        <ul class="cc_tab_wrap cc_outer">
          <div class="row">
            <div class="col">
              <li class="nav-item">
                <router-link to="/admin-movie">
                  <button class="cc_tab_tit" type="button" style="width: 100%">
                    <span>영화목록 관리</span>
                  </button>
                </router-link>
              </li>
            </div>
            <div class="col">
              <li class="nav-item">
                <router-link to="/admin-notice">
                  <button class="cc_tab_tit" type="button" style="width: 100%">
                    <span>공지사항 관리</span>
                  </button>
                </router-link>
              </li>
            </div>
            <div class="col">
              <li class="nav-item">
                <router-link to="/admin-qna">
                  <button
                    class="cc_tab_tit active"
                    type="button"
                    style="width: 100%"
                  >
                    <span>1:1문의 관리</span>
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
          <table class="tb_acc_wrap">
            <thead>
              <tr>
                <!-- TODO: QnA - Title 들어갈 자리 -->
                <th scope="col">
                  {{ currentQna.subject }}
                </th>
              </tr>
            </thead>
            <tbody>
              <tr class="tb_info">
                <td>
                  <span class="tit">등록일</span>
                  <!-- QnA - insertTime -->
                  <span class="cont">{{ currentQna.insertTime }}</span>
                  <span class="tit">질문자 ID</span>
                  <!-- 질문자의 id 혹은 이메일이 보여지는 부분 -->
                  <span class="cont">{{ user.username }}</span>
                </td>
              </tr>
              <tr class="tb_content">
                <td>
                  <div id="divNoticeContents">
                    <pre>{{ currentQna.text }}</pre>
                  </div>
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
                      placeholder="위 문의에 대한 답변을 작성해주세요."
                      v-model="currentQna.answer"
                      required
                    ></textarea>
                  </div>
                </td>
              </tr>
            </tbody>
          </table>

          <div class="success_message">
            <div class="mb-1 alert alert-success" role="alert" v-if="message">
              {{ message }}
            </div>
          </div>

          <div class="btn_btm_wrap">
            <button @click="deleteQna" class="btn_col3 ty6" style="background:red !important ; color:white  !important;">문의삭제</button>
            <button @click="updateQna" class="btn_col2 ty6" style="background:white !important ; color:black  !important;">답변완료</button>
            <router-link to="/admin-qna"
              ><button class="btn_col2 ty6">목록</button></router-link
            >
          </div>
        </div>
        <!-- QnA 세부내용 끝 -->
      </div>
    </div>
  </div>
</template>

<script>
import NavBlack from "@/components/common/NavBlack.vue";
import initCustom from "@/assets/js/custom";
import AdQnaDataService from "@/services/AdQnaDataService";
import User from "@/models/user";
import userService from "@/services/userServices/user.service";
export default {
  data() {
    return {
      currentQna: null,
      message: "",
      user: new User("", "", "", "", "", "", "", "", ""),

    };
  },
  components: {
    NavBlack,
  },
  methods: {
    getUser(id) {
      userService
        .getUserProfile2(id)
        .then((reponse) => {
          this.user = reponse.data;
          // console.log(reponse.data);

          // console.log("가져오는 유저 양식은:" + JSON.stringify(this.geteduser));
          // console.log("수정용 유저 양식은:" + JSON.stringify(this.user));
        })
        .catch((e) => {
          console.log(e);
        });
    },
    // 부서번호(qid)로 조회 요청하는 함수
    getQna(qid) {
      // axios 공통함수 호출
      AdQnaDataService.get(qid)
        // 성공하면 .then() 결과가 리턴됨
        .then((response) => {
          // springboot 결과를 리턴함(부서 객체)
          this.currentQna = response.data;
          this.getUser(this.currentQna.writer)
          console.log(""+this.user.username)
          // 콘솔 로그 출력
          console.log(response.data);
        })
        // 실패하면 .catch() 에러메세지가 리턴됨
        .catch((e) => {
          console.log(e);
        });
    },
    // 부서정보를 수정 요청하는 함수
    updateQna() {
      // axios 공통함수 호출
      AdQnaDataService.update(this.currentQna.qid, this.currentQna)
        // 성공하면 then() 결과가 전송됨
        .then((response) => {
          console.log(response.data);
          this.message = "문의에 대한 답변을 성공적으로 등록하였습니다!";
        })
        // 실패하면 .catch() 에러메세지가 전송됨
        .catch((e) => {
          console.log(e);
        });
    },
    // 부서정보를 삭제 요청하는 함수
    deleteQna() {
      // axios 공통함수 호출
      AdQnaDataService.delete(this.currentQna.qid)
        // 성공하면 then() 결과가 전송됨
        .then((response) => {
          console.log(response.data);
          // 첫페이지(전체목록_조회_페이지) 강제 이동 : /qna
          this.$router.push("/admin-qna");
        })
        // 실패하면 .catch() 에러메세지가 전송됨
        .catch((e) => {
          console.log(e);
        });
    },
  },
  // 화면이 뜨자 마자 실행되는 이벤트
  mounted() {
    initCustom();
    this.message = "";
    this.getQna(this.$route.params.qid);
   
  },
};
</script>

<style scoped>
/* 포괄 css */
button {
  border: 0px solid #000;
  cursor: pointer;
  background-color: #000;
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
.admin-background {
  background-color: #000;
}

/* 대제목 css */
.cc_title_top {
  width: 980px;
  margin: auto;
  height: 105px;
  border-bottom: 1px solid #eee;
}
.cc_title_top .cc_tit {
  float: left;
  font-size: 24px;
  color: #fff;
  line-height: 50px;
}
.cc_tit {
  margin-top: 50px;
}

/* 3가지 버튼 css */
.cc_tab_wrap.cc_outer {
  padding-top: 20px;
}
.cc_tab_wrap {
  position: relative;
}
.cc_tab_tit {
  height: 50px;
  color: #fff;
}
.cc_tab_tit.active {
  border-bottom: 2px solid #fff;
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
  color: #fff;
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
  background: #292929;
  color: #fff;
  padding-left: 61px;
}
.tit {
  color: #fff;
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
  color: #fff;
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

/* 버튼 css */
.btn_btm_wrap {
  margin-top: 60px;
  padding-bottom: 60px;
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
  border: 1px solid #000000;
  font-size: 14px;
  color: #000000 !important;
  text-align: center;
  vertical-align: middle;
  background-color: #ffffff;
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
