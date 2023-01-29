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
                <button
                  class="cc_tab_tit active"
                  type="button"
                  style="width: 100%"
                >
                  <span>공지사항</span>
                </button>
              </router-link>
            </li>
          </div>
          <div class="col">
            <li class="nav-item">
              <a @click="needLogin()">
                <button class="cc_tab_tit" type="button" style="width: 100%">
                  <span>1:1문의</span>
                </button>
              </a>
            </li>
          </div>
        </div>
      </ul>
    </div>
    <!-- 3대장 끝 -->

    <div v-if="currentNotice">
      <!-- 공지사항 세부내용 시작 -->
      <div class="cc_tab_content mt-5">
        <table class="tb_acc_wrap">
          <thead>
            <tr>
              <!-- TODO: notice - Title 들어갈 자리 -->
              <th scope="col">{{ currentNotice.ntcTitle }}</th>
            </tr>
          </thead>
          <tbody>
            <tr class="tb_info">
              <td>
                <span class="tit">영화관</span>
                <!-- notice - 구분(Categorie) -->
                <span class="cont">전체</span>
                <span class="tit">등록일</span>
                <!-- notice - insertTime -->
                <span class="cont">{{ currentNotice.insertTime}}</span>
                <!-- <span class="tit">조회수</span> -->
                <!-- <span class="cont">110</span> -->
              </td>
            </tr>
            <tr class="tb_content">
              <td>
                <div id="divNoticeContents">
                  <!-- notice - content -->
                  <pre>{{ currentNotice.ntcContent}}</pre>
                </div>
              </td>
            </tr>

            
            <!-- https://kimfk567.tistory.com/45 참고 -->

            <!-- <tr class="tb_link">
              <td>
                <a href="#" class="" id="2486">
                  <span class="tb_next">다음글</span>
                  <span>[용산관] &lt;크리스마스 캐럴&gt; 무대인사 안내</span>
                </a>
              </td>
            </tr>
            <tr class="tb_link">
              <td>
                <a href="#" class="" id="2482">
                  <span class="tb_prev">이전글</span>
                  <span>L.PAY 경남은행, 우리은행 시스템 작업 안내</span>
                </a>
              </td>
            </tr> -->
          </tbody>
        </table>
        <div class="btn_btm_wrap">
          <router-link to="/notice" class="btn_col2 ty6">목록</router-link>
        </div>
      </div>
    </div>
    <!-- 공지사항 세부내용 끝 -->
  </div>
</template>

<script>
import NavBlack from "@/components/common/NavBlack.vue";
import NoticeDataService from "@/services/NoticeDataService";

export default {
  components: {
    NavBlack,
  },
  data() {
    return {
      currentNotice: null,
      message: "",
      loginPopup: false,
    };
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

    // 공지번호(ntcNo)로 조회 요청하는 함수
    getNotice(ntcNo) {
      // axios 공통함수 호출
      NoticeDataService.get(ntcNo)
        // 성공하면 .then() 결과가 리턴됨
        .then((response) => {
          // springboot 결과를 리턴함(부서 객체)
          this.currentNotice = response.data;
          // 콘솔 로그 출력
          console.log(response.data);
        })
        // 실패하면 .catch() 에러메세지가 리턴됨
        .catch((e) => {
          console.log(e);
        });
    },
  },
  // 화면이 뜨자 마자 실행되는 이벤트
  mounted() {
    this.message = "";
    this.getNotice(this.$route.params.ntcNo);
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
.cc_tit_sub {
  float: left;
  font-size: 16px;
  line-height: 50px;
  padding-top: 4px;
  font-weight: 500;
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
.btn_col2.ty6 {
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
</style>
