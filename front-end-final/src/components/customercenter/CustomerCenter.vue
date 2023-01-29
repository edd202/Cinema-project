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
                <button
                  class="cc_tab_tit active"
                  type="button"
                  style="width: 100%"
                >
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
              <a @click="needLogin">
                <button class="cc_tab_tit" type="button" style="width: 100%" >
                  <span>1:1문의</span>
                </button>
              </a>
            </li>
          </div>
        </div>
      </ul>
    </div>
    <!-- 3대장 끝 -->

    <!-- FAQ 파트 시작 -->
    <div>
      <div class="cc_tab_content">
        <div class="con_top">
          <div class="ico_tit qus">
            더 궁금한 점이 있거나, 이미 문의한 내용과 답변을 확인하려면?
            <a @click="needLogin()">
            <ul class="cc_qna_list_txt">
              <li>
                1:1문의 바로가기
              </li>
            </ul>
          </a>
          </div>
        </div>

        <!-- faq accordion 테이블 시작 -->
        <table class="tb_acc_wrap">
          <thead>
            <tr>
              <th>구분</th>
              <th>질문</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td class="panel-faq-categorie">영화관 이용</td>
              <td class="text-start" width="80%">
                <div class="panel-faq-container">
                  <p class="panel-faq-title">SWEET SPOT이 무엇인가요?</p>
                  <div class="panel-faq-answer">
                    <p>답변 <span style="color: red">↓</span></p>
                    <p>
                      SWEET SPOT이란, 스크린 가운데에서 상영관 뒤 벽까지 직선
                      거리의 2/3 지점으로, 롯데시네마가 추천하는 최적의 화면과
                      사운드를 즐길 수 있는 좌석입니다.
                    </p>
                    <p>별도의 추가 요금은 없습니다.</p>
                  </div>
                </div>
              </td>
            </tr>
            <tr>
              <td class="panel-faq-categorie">결제 이용</td>
              <td class="text-start" width="80%">
                <div class="panel-faq-container">
                  <p class="panel-faq-title">
                    카드로 결제할 경우 환불은 언제 되나요?
                  </p>
                  <div class="panel-faq-answer">
                    <p>답변 <span style="color: red">↓</span></p>
                    <div>
                      ■ 환불안내<br />&nbsp;<br />ㆍ 신용카드<br />&nbsp;- 결제
                      후 3일 이내 취소시 승인취소 가능합니다.<br />&nbsp;<font
                        color="#ff0000"
                        >- 3일 이후 예매 취소 시 영업일 기준 3일 ~7일 이내
                        카드사에서 환불됩니다.</font
                      ><br />&nbsp;<br />ㆍ 체크카드<br />&nbsp;- 결제 후 3일
                      이내 취소 시 당일 카드사에서 환불 처리됩니다.<br />&nbsp;<font
                        color="#ff0000"
                        >- 3일 이후 예매 취소 시 카드사에 따라 3일 ~ 10일 이내
                        카드사에서 환불됩니다.<br /></font
                      >&nbsp;<br />ㆍ 휴대폰 결제<br />&nbsp;<font
                        color="#ff0000"
                        >- 결제 일자 기준 당월(1일~말일까지) 취소만
                        가능합니다.<br /></font
                      >&nbsp;- 익월 취소의 경우 롯데시네마 고객센터(1544-8855)로
                      문의 주시기 바랍니다.<br /><br />ㆍ카카오페이 간편결제<br />&nbsp;-
                      카카오페이머니를 사용하신 경우 카카오페이머니 잔액으로
                      원복됩니다.<br />&nbsp;- 카드 결제를 한 경우 카드사 정책에
                      따라 승인취소가 진행되며,<br />&nbsp;&nbsp;&nbsp; 3일 이후
                      매입 취소 시 영업일 기준 3~10일 소요됩니다.
                      <br /><br />ㆍ페이코 간편결제<br />&nbsp; - PAYCO
                      쿠폰/포인트를 사용하신 경우 각각의 쿠폰/포인트로
                      원복됩니다.&nbsp;<br />&nbsp; - 카드 결제 금액은 카드사
                      정책에 따라 승인취소가 진행되며,<br />&nbsp;&nbsp;&nbsp;
                      3일 이후 매입취소 시 영업일 기준 3~10일 소요됩니다.
                    </div>
                  </div>
                </div>
              </td>
            </tr>
            <tr>
              <td class="panel-faq-categorie">예매 이용</td>
              <td class="text-start" width="80%">
                <div class="panel-faq-container">
                  <p class="panel-faq-title">
                    영화티켓 예매 및 취소는 어떻게 하나요?
                  </p>
                  <div class="panel-faq-answer">
                    <p>답변 <span style="color: red">↓</span></p>
                    <div>
                      <p>
                        ■ 티켓예매 (결제 방법)<br />ㆍ 홈페이지, 모바일 앱/웹
                        예매는 영화시작시간 10분 후까지 예매 가능합니다.<br />ㆍ
                        영화관 현장 예매는 &nbsp; 영화시작시간 직전까지 예매
                        가능합니다.
                      </p>
                      <p>&nbsp;</p>
                      <p>
                        ■ 티켓취소 <br />ㆍ 홈페이지, 모바일 앱/웹 예매는
                        영화시작시간 20분 전까지 취소 가능합니다.<br />ㆍ&nbsp;영화관
                        현장 취소는&nbsp;영화시작시간 직전까지 취소 가능합니다.
                      </p>
                      <p>&nbsp;</p>
                      <p>
                        ※ 현장에서 티켓으로 출력을 진행한 경우 온라인 취소가
                        불가능하며,
                      </p>
                      <p>
                        &nbsp;&nbsp;&nbsp;&nbsp; 영화관 매표소 / 스위트샵을 통해
                        상영시간 전까지 취소 가능합니다.
                      </p>
                      <p>
                        ※ 무대인사가 포함된 영화의 경우 행사의 원활한 진행을
                        위해 상영시간 24시간 이전부터는 취소가 불가합니다.
                      </p>
                    </div>
                  </div>
                </div>
              </td>
            </tr>
            <tr>
              <td class="panel-faq-categorie">예매 이용</td>
              <td class="text-start" width="80%">
                <div class="panel-faq-container">
                  <p class="panel-faq-title">비회원으로 예매가 가능한가요?</p>
                  <div class="panel-faq-answer">
                    <p>답변 <span style="color: red">↓</span></p>
                    <p>
                      비회원 약관 동의 후에 기초 개인정보
                      (성명+생년월일+휴대폰번호+비밀번호 4자리) 설정 후 예매
                      가능합니다.
                    </p>
                  </div>
                </div>
              </td>
            </tr>
            <tr>
              <td class="panel-faq-categorie">이용 안내</td>
              <td class="text-start" width="80%">
                <div class="panel-faq-container">
                  <p class="panel-faq-title">관람등급 안내</p>
                  <div class="panel-faq-answer">
                    <p>답변 <span style="color: red">↓</span></p>
                    <div>
                      <p>
                        롯데시네마는 영화 및 비디오 진흥에 관한 법률(이하
                        영비법)을 준수합니다.
                      </p>
                      <p>
                        ■ 등급 기준<br />ㆍ 전체 관람가 : 모든 연령의 관람객이
                        관람할 수 있는 영화<br />ㆍ 12세 관람가 : 만 12세 미만의
                        관람객은 관람할 수 없는 영화(보호자 동반 시 12세 미만
                        관람가)<br />ㆍ 15세 관람가 : 만 15세 미만의 관람객은
                        관람할 수 없는 영화(보호자 동반 시 15세 미만 관람가)<br />ㆍ
                        청소년 관람불가 : 만 18세 미만의 관람객은 관람할 수 없는
                        영화
                      </p>
                      <p>
                        영비법 29조에 따르면 [만 12세 이상 관람가/ 만 15세 이상
                        관람가]의 등급이라도 <br />부모 등 보호자를 동반하는
                        경우 어린이(유아) 동반이 가능합니다.<br />반드시
                        보호자의 동반이 필요함을 양지하여 주시기 바랍니다.
                      </p>
                      <p>
                        <font color="#ff0000"
                          ><strong
                            >단, 청소년 관람불가 영화는 보호자 동반과 관계없이
                            <br />만 18세 미만이거나 연령 조건을 만족하여도
                            초중고 재학 중인 청소년 및 영유아 관람이 절대
                            불가합니다.</strong
                          >
                        </font>
                      </p>
                      <p>
                        <font color="#ff0000"
                          ><strong
                            >영화관 현장에서 연령확인 불가 시 입장이 제한될 수
                            있습니다.</strong
                          >
                        </font>
                      </p>
                      <p>
                        <font color="#ff0000"
                          ><strong
                            >또한 상영등급에 맞지 않는 영화를 관람하거나
                            무단입장을 하는 경우 형사처벌 및 손해 배상의 대상이
                            될 수 있습니다.</strong
                          ></font
                        >
                      </p>
                    </div>
                  </div>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
        <!-- faq accordion 테이블 끝 -->
      </div>
    </div>
    <!-- FAQ 파트 끝 -->
  </div>
</template>

<script>
import NavBlack from "@/components/common/NavBlack.vue";
import initCustom from "@/assets/js/custom.js";
import accTable from "@/assets/js/accordion.js";

export default {
  data() {
    return {
      searchSelect: "제목",
      loginPopup: false,
    };
  },
  components: {
    NavBlack,
  },
  mounted() {
    initCustom();
    accTable();
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
  /* margin-bottom: -16px; */
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
  height: auto;
}
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

.con_top {
  position: relative;
  background-color: #f8f8f8;
  padding: 35px 0 10px 0;
  text-align: center;
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
.con_top .ico_tit.qus {
  background-image: url(@/assets/images/customer/ic_qus.png);
  background-position: top 5% left;
}
.con_top .ico_tit {
  display: inline-block;
  background-repeat: no-repeat;
  background-position: top 50% left;
  min-width: 250px;
  min-height: 35px;
  line-height: 1.4;
  padding-left: 55px;
  text-align: left;
  font-size: 15px;
  font-weight: 600;
}
.cc_qna_list_txt {
  font-size: 13px;
  color: #707070;
  margin-top: 10px;
  text-decoration: underline;
}
</style>
