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
                  @click="needLogin()"
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

    <!-- 1:1문의(QnA) 시작 -->
    <div class="cc_tab_content">
      <!-- QnA 검색 시작 -->
      <fieldset class="cc_search_wrap ty2">
        <input
          type="text"
          placeholder="검색어를 입력해주세요."
          id="searchKeyword"
          title="검색어를 입력해주세요"
          v-model="searchSubject"
        />
        <button
          type="button"
          class="cc_btn_col2"
          @click="
            page = 1;
            getQnaByUsername();
          "
        >
          검색
        </button>
      </fieldset>
      <!-- QnA 검색 끝 -->

      <!-- TODO: QnA 는 로그인 서비스로, 일치하는 id 로 목록 나타내게.. -->

      <div v-if="user">
        <table class="tb_acc_wrap">
          <thead>
            <tr>
              <th>등록일</th>
              <th>제목</th>
            </tr>
          </thead>
          <tbody v-for="(data, index) in qna" :key="index">
            <tr>
              <td width="15%">{{ data.insertTime }}</td>
              <td class="text-start tb_title" width="auto">
                <router-link :to="'/qna/' + data.qid"
                  ><span class="cc_noti_title">{{
                    data.subject
                  }}</span></router-link
                >
              </td>
            </tr>
          </tbody>
        </table>

        <!--    Todo : page 바 시작 -->
        <div class="btn_btm_wrap">
          <b-pagination
            v-model="page"
            :total-rows="count"
            :per-page="pageSize"
            @change="handlePageChange"
          ></b-pagination>
        </div>
        <!-- <!—    Todo : page 바 끝 —> -->
      </div>
      <!-- </div> -->

      <div v-else>
        <p class="text-center mt-5">고객님의 문의 내역이 존재하지 않습니다.</p>
      </div>

      <div>
        <router-link to="/add/qna">
          <button class="cc_qna_btn mt-5">문의하기</button>
        </router-link>
      </div>
    </div>
    <!-- 1:1문의(QnA) 끝 -->

    <!-- 로그인 필요 팝업 시작 -->
    <div :class="{ dim200: loginPopup == true }"></div>
    <div
      id="layerCommonAlert"
      class="layer_wrap layer_alert layer_wrap200 active"
      v-if="loginPopup"
      tabindex="0"
      style="left: 50%; top: 50%; margin-top: -96.5px; margin-left: -190px"
    >
      <strong class="hidden">레이어 팝업 시작</strong>
      <div class="layer_contents">
        <div class="bx_alert1">
          <p class="txt_para ty2">로그인이 필요한 서비스 입니다.</p>
        </div>
      </div>
      <div class="layer_footer ty2">
        <ul>
          <li>
            <button
              type="button"
              class="btn_close btnCloseLayer"
              @click="loginPopup = false"
            >
              <router-link to="/login">확인</router-link>
            </button>
          </li>
        </ul>
      </div>
      <strong class="hidden txtTabIndex" tabindex="0">레이어 팝업 끝</strong>
    </div>
    <!-- 로그인 필요 팝업 끝 -->

  </div>
</template>

<script>
import NavBlack from "@/components/common/NavBlack.vue";
import initCustom from "@/assets/js/custom";
import QnaDataService from "@/services/QnaDataService";
import User from "@/models/user";
import userService from "@/services/userServices/user.service";

export default {
  data() {
    return {
  
      user:new User('','','','','','','','',''),
      qna: [],
      searchSubject: "",
      username:"",
      // 페이징을 위한 변수 정의
      page: 1, // 현재 페이지
      count: 0, // 전체 데이터 건수
      pageSize: 5, // 한페이지당 몇개를 화면에 보여줄지 결정하는 변수

      loginPopup: false,
    };
  },
  components: {
    NavBlack,
  },
  methods: {
    // 로그인여부 확인 클릭이벤트
    needLogin() {
      if(!this.loggedIn){
        this.loginPopup=true;
        // alert("로그인이 필요한 서비스 입니다.")
        // this.$router.push("/login")
      }else{
        this.$router.push("/qna")
      }},

    
    // select box 값 변경시 실행되는 함수(재조회)
    handlePageSizeChange(event) {
      this.pageSize = event.target.value;
      this.page = 1;
      // 재조회 함수 호출
      this.getQnaByUsername();
    },
    // 페이지 번호 변경시 실행되는 함수(재조회)
    handlePageChange(value) {
      this.page = value; // 매개변수값으로 현재페이지 변경
      // 재조회 함수 호출
      this.getQnaByUsername();
    },
    // 유저정보 불러오는 함수
    getUser(username) {
      userService
        .getUserProfile(username)
        .then((reponse) => {
          this.user = reponse.data;
          localStorage.setItem("user",JSON.stringify(this.user))
          this.getQnaByUsername();
        })
        .catch((e) => {
          console.log(e);
        });
    },
    getQnaByUsername(){
      QnaDataService.getByUserUsername(this.user.id,this.page-1,this.pageSize)
      .then((response)=>{
        console.log(response.data)
        this.qna=response.data.qna
      })
      .catch((e)=>{
        console.log(e)
      })
    }
  },
  mounted() {
    initCustom();
    
  },

  // 이 페이지 접근시 로그인 여부 확인
  created() {
    console.log(
      "회원로그인여부:" + this.loggedIn + "비회원로그인여부:" + this.loggedIn2
    );
    if (!this.loggedIn && !this.loggedIn2) {
      this.$router.push("/");
    }
    if (this.loggedIn2) {
      this.$router.push("/");
    }
    if (JSON.parse(localStorage.getItem("user"))) {
      this.username = JSON.parse(localStorage.getItem("user")).username;
      this.getUser(this.username);
    }
   
  },

  computed: {
    loggedIn() {
      return this.$store.state.auth.initialState.status.loggedIn;
    },
    loggedIn2() {
      return this.$store.state.auth.initialState2.status.loggedIn2;
    },
  },
};
</script>

<style scoped>

/*   팝업  */

.layer_wrap.active {
  display: block;
  position: fixed;
  z-index: 100;
  height: auto;
  border-radius: 10px;
  background-color: #fff;
}
.layer_wrap .layer_contents {
  padding: 30px 25px;
}
.txt_para {
  line-height: 1.4;
  text-align: center;
}
.layer_wrap .layer_footer {
  clear: both;
  border-top: 1px solid #ddd;
  text-align: center;
  background-color: #f8f8f8;
}

.hidden {
  overflow: hidden;
  position: absolute !important;
  width: 1px;
  height: 1px;
  clip: rect(1px, 1px, 1px, 1px);
}
strong {
  font-weight: bold;
}
ol,
ul,
dl {
  margin-top: 0;
  margin-bottom: 1rem;
  list-style: none;
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

.layer_wrap.active.layer_alert {
  width: 380px;
}

.layer_wrap200 {
  z-index: 200 !important;
}

.bx_alert1 {
  padding: 30px 0;
}
.txt_para {
  line-height: 1.4;
  text-align: center;
}

.txt_para.ty2 {
  font-size: 16px;
}
.layer_wrap .layer_footer {
  clear: both;
  border-top: 1px solid #ddd;
  text-align: center;
  background-color: #f8f8f8;
}
.layer_wrap .layer_footer.ty2 ul li {
  width: 100%;
}
.layer_wrap .layer_footer ul li {
  position: relative;
  float: left;
  width: 50%;
}
.layer_wrap .layer_footer ul li a,
.layer_wrap .layer_footer ul li button {
  display: block;
  width: 100%;
  height: 50px;
  line-height: 50px;
  border: none;
  font-size: 16px;
  background-color: #f8f8f8;
  margin-left: -12px;
}

.dim200 {
  position: fixed;
  top: 0;
  right: 0;
  bottom: -1px;
  left: 0;
  z-index: 199;
  background: rgba(0, 0, 0, 0.5);
  overflow-y: scroll;
}





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

/* 검색창 css */
.cc_search_wrap {
  position: relative;
  border: 1px solid #dddddd;
  border-radius: 4px;
  background: #f8f8f8;
  padding: 22px 22px;
  -webkit-border-radius: 4px;
  -moz-border-radius: 4px;
  -ms-border-radius: 4px;
  -o-border-radius: 4px;
  font-size: 0;
}
.cc_search_wrap.ty2 input[type="text"] {
  width: 440px;
  margin-right: 8px;
}
.cc_search_wrap [class^="btn_col"] {
  width: 86px;
  height: 38px;
  line-height: 34px;
  vertical-align: middle;
}
.cc_btn_col2 {
  display: inline-block;
  box-sizing: border-box;
  border-radius: 4px;
  width: 60px;
  height: 38px;
  border: 1px solid #414141;
  font-size: 14px;
  color: #ffffff !important;
  text-align: center;
  vertical-align: middle;
  background-color: #414141;
}

/* accordion css */
.panel-faq-container {
  margin-bottom: -16px;
}
.panel-faq-title {
  padding-left: 10px;
  color: #000;
  cursor: pointer;
  font-weight: 500;
}
.panel-faq-no,
.panel-faq-categorie {
  font-weight: 500;
}
.panel-faq-answer {
  height: 0;
  overflow: hidden;
  transition: all 1s;
  background-color: #f8f8f8;
  padding-left: 10px;
}
.acc_active {
  display: block;
  height: 300px;
}
/* Table css */
.tb_acc_wrap {
  width: 980px;
  margin: 5px auto;
  border-collapse: collapse;
  border-spacing: 0;
  border: 0;
}
.tb_acc_wrap thead th {
  border-bottom-color: #bbbbbb;
  font-size: 15px;
  font-weight: 900;
}
.tb_acc_wrap th,
.tb_acc_wrap td {
  position: relative;
  border-bottom: 1px solid #eeeeee;
  padding: 15px 0;
  font-size: 15px;
  text-align: center;
  line-height: 24px;
}
.tb_acc_wrap .tb_title {
  padding-left: 50px;
}

/* 공지사항 css */
.form-select {
  display: inline-block;
}
.form-select:focus {
  border-color: #000;
  outline: 0;
  box-shadow: 0 0 0 0rem rgba(7, 13, 89, 0.25);
}
.cc_noti_select {
  width: 120px;
  height: 40px;
  margin-right: 8px;
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
.cc_noti_title,
.cc_qna_title {
  padding-left: 10px;
}

/* qna css */
.cc_qna_btn {
  display: block;
  box-sizing: border-box;
  border-radius: 4px;
  width: 100px;
  margin: auto;
  height: 38px;
  border: 1px solid #414141;
  font-size: 14px;
  color: #ffffff !important;
  text-align: center;
  background-color: #414141;
}

.btn_btm_wrap {
  margin-top: 50px;
  text-align: center;
}
.pagination {
  justify-content: center;
}
</style>
