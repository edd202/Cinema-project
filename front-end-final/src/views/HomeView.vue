<template>
  <div>
    <!-- header 시작 : 공용 header 와 디자인이 달라 별도로 사용 -->
    <!-- mobile-menu 시작 -->
    <div class="site-mobile-menu site-navbar-target">
      <div class="site-mobile-menu-header">
        <div class="site-mobile-menu-close">
          <span class="icofont-close js-menu-toggle"></span>
        </div>
      </div>
      <div class="site-mobile-menu-body"></div>
    </div>
    <!-- mobile-menu 끝 -->

    <!-- nav bar 시작 -->
    <nav class="site-nav">
      <div class="container">
        <div class="site-navigation">
          <!-- 상단 메뉴 : 로고 , 소메뉴 -->
          <div class="row">
            <div class="col-6 col-lg-3"></div>
            <!-- 로고 시작 -->
            <div
              class="
                col-lg-6
                d-none d-lg-inline-block
                text-center
                nav-center-wrap
              "
              style="height: 50px"
            >
              <ul class="js-clone-nav text-center site-menu p-0 m-0">
                <li class="logo_ds">
                  <img
                    src="../assets/images/nav/logo_transparent.png"
                    width="150px"
                    style="margin-top: -60px"
                    alt=""
                  />
                </li>
              </ul>
            </div>
            <!-- 로고 끝 -->
          </div>
          <!-- 상단 메뉴 끝 -->
          <!-- 하단 메뉴 : 메인메뉴 , 소메뉴 -->
          <div
            class="row"
            style="
              width: 980px;
              margin: 0 auto;
              border-top: 0.5px solid rgba(255, 255, 255, 0.3);
            "
          >
            <!-- 빈 공간 -->
            <div class="col-6 col-lg-3"></div>
            <!-- 메인메뉴 시작 -->
            <div
              class="
                col-lg-6
                d-none d-lg-inline-block
                text-center
                nav-center-wrap
              "
            >
              <ul class="js-clone-nav text-center site-menu p-0 m-0">
                <li class="has-children">
                  <router-link to="/reservation">예매</router-link>
                  <ul class="dropdown">
                    <li>
                      <router-link to="/reservation">예매하기</router-link>
                    </li>
                    <li>
                      <a @click="checkLogin()">예매확인</a>
                    </li>
                  </ul>
                </li>
                <li class="has-children">
                  <router-link to="/movie-home">영화</router-link>
                  <ul class="dropdown">
                    <li>
                      <router-link to="/movie-list/current"
                        >현재상영작</router-link
                      >
                    </li>
                    <li>
                      <router-link to="/movie-list/expected"
                        >상영예정작</router-link
                      >
                    </li>
                  </ul>
                </li>
                <li class="has-children">
                  <router-link to="/theaters">스페셜관</router-link>
                  <ul class="dropdown">
                    <li>
                      <router-link to="/comfort">씨네 컴포트관</router-link>
                    </li>
                    <li><router-link to="/color">컬러리움관</router-link></li>
                    <li><router-link to="/4d">수퍼4D관</router-link></li>
                    <li><router-link to="/s">수퍼S관</router-link></li>
                  </ul>
                </li>
                <li>
                  <router-link to="/event-premiere"
                    >시사회/무대인사</router-link
                  >
                </li>
                <li class="has-children" v-show="adminRole">
                  <router-link to="/admin-movie">관리자</router-link>
                  <ul class="dropdown">
                    <li>
                      <router-link to="/admin-movie">영화목록</router-link>
                    </li>
                    <li>
                      <router-link to="/admin-notice">공지사항</router-link>
                    </li>
                    <li><router-link to="/admin-qna">1:1문의</router-link></li>
                    <li v-show="masterRole">
                      <router-link to="/chart">매출 통계</router-link>
                    </li>
                  </ul>
                </li>
              </ul>
            </div>
            <!-- 메인메뉴 끝 -->
            <!-- 소메뉴 시작 -->
            <div v-if="!currentUser" class="col-6 col-lg-3 text-lg-end">
              <ul
                class="js-clone-nav d-none d-lg-inline-block text-end mini-menu"
                style="width: 240px"
              >
                <li><router-link to="/login">로그인</router-link></li>
                <li><router-link to="/signup">회원가입</router-link></li>
                <li>
                  <router-link to="/customer-center">고객센터</router-link>
                </li>
              </ul>
              <a
                href="#"
                class="
                  burger
                  ms-auto
                  float-end
                  site-menu-toggle
                  js-menu-toggle
                  d-inline-block d-lg-none
                  light
                "
                data-toggle="collapse"
                data-target="#main-navbar"
              >
                <span></span>
              </a>
            </div>

            <div v-if="currentUser" class="col-6 col-lg-3 text-lg-end">
              <ul
                class="js-clone-nav d-none d-lg-inline-block text-end mini-menu"
              >
                <li v-show="onlyMember">
                  <router-link to="/mypage">
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      width="16"
                      height="16"
                      fill="currentColor"
                      class="bi bi-file-earmark-break-fill"
                      viewBox="0 0 16 16"
                    >
                      <path
                        d="M4 0h5.293A1 1 0 0 1 10 .293L13.707 4a1 1 0 0 1 .293.707V9H2V2a2 2 0 0 1 2-2zm5.5 1.5v2a1 1 0 0 0 1 1h2l-3-3zM2 12h12v2a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2v-2zM.5 10a.5.5 0 0 0 0 1h15a.5.5 0 0 0 0-1H.5z"
                      />
                    </svg>
                    마이페이지</router-link
                  >
                </li>
                <li>
                  <a
                    @click="logOut()"
                    style="text-decoration: none; cursor: pointer"
                    >로그아웃</a
                  >
                </li>
                <li>
                  <router-link to="/customer-center">고객센터</router-link>
                </li>
              </ul>
              <a
                href="#"
                class="
                  burger
                  ms-auto
                  float-end
                  site-menu-toggle
                  js-menu-toggle
                  d-inline-block d-lg-none
                  light
                "
                data-toggle="collapse"
                data-target="#main-navbar"
              >
                <span></span>
              </a>
            </div>
            <!-- 소메뉴 끝 -->
          </div>
        </div>
      </div>
    </nav>
    <!-- nav bar 끝 -->
    <!-- header 끝 -->

    <!-- 메인 홈 시작 -->
    <div class="hero overlay">
      <!-- 대형 포스터 시작 -->
      <div class="img-bg rellax">
        <div
          class=""
          style="
            margin: 0 auto;
            width: 1600px;
            box-shadow: inset 0 0 1000px #000;
          "
        >
          <img
            src="../assets/images/header/AA_1920774.jpg"
            alt="Image"
            class=""
            style="width: 1600px; display: block; margin: 0 auto"
          />
        </div>
      </div>
      <!-- 대형 포스터 끝 -->

      <!-- 인기영화 carosal 시작 -->
      <div class="slideList">
        <AppSlider :screeningList="screeningList" />
      </div>
      <!-- 인기영화 carosal 끝 -->

      <div id="contents">
        <!-- 스페셜관 시작 -->
        <div class="specialCinema">
          <div class="container">
            <div class="sec_tit">스페셜관</div>
            <ul class="special_wrap2">
              <li class="col-3" style="list-style: none">
                <router-link to="/4d"
                  ><img
                    src="https://caching.lottecinema.co.kr//Media/WebAdmin/cd746f8b4a544f33988d6810ba2934cd.png"
                    alt="샤롯데바로가기"
                /></router-link>
              </li>
              <li class="col-3" style="list-style: none">
                <router-link to="/color"
                  ><img
                    src="https://caching.lottecinema.co.kr//Media/WebAdmin/0df5043330d7485b8081dc2d1bebaa3a.png"
                    alt="수퍼플렉스 바로가기"
                /></router-link>
              </li>
              <li class="col-3" style="list-style: none">
                <router-link to="/comfort"
                  ><img
                    src="https://caching.lottecinema.co.kr//Media/WebAdmin/cf8b080e65254806a2bd54aa4324920b.png"
                    alt="수퍼S 바로가기"
                /></router-link>
              </li>
              <li class="col-3" style="list-style: none">
                <router-link to="/color">
                  <img
                    src="https://caching.lottecinema.co.kr//Media/WebAdmin/74093cf4217c4e89a582a24d5cf9dee2.png"
                    alt="컬러리움"
                  />
                </router-link>
              </li>
            </ul>
          </div>
        </div>
        <!-- 스페셜관 끝 -->

        <!-- 시사회 시작 -->
        <div class="main_cont_wrap premiere">
          <div class="container">
            <div class="sec_tit">시사회/무대인사</div>
            <ul class="premiere_wrap">
              <li>
                <router-link to="/event-detail2">
                  <img
                    src="https://caching.lottecinema.co.kr//Media/Event/4c1ee91aa243496ab82410c930f3cdf7.jpg"
                    alt="<해시태그 시그네> GV"
                /></router-link>
              </li>
              <li>
                <router-link to="/event-detail3">
                  <img
                    src="https://caching.lottecinema.co.kr//Media/Event/b3737c2ae7e5465e8cac1d5a3b77bf1f.jpg"
                    alt="<오늘 밤, 세계에서> 앵콜 상영회"
                /></router-link>
              </li>
              <li>
                <router-link to="/event-detail4">
                  <img
                    src="https://caching.lottecinema.co.kr//Media/Event/2a8c891b0b0d4d9796afb8c3360d6687.jpg"
                    alt="<장화신은 고양이> 무대인사"
                /></router-link>
              </li>
            </ul>
          </div>
        </div>
        <!-- 시사회 끝 -->

        <!-- 공지사항 시작 -->
        <div class="main_cont_wrap notice">
          <div class="container">
            <div class="sec_tit">공지사항</div>

            <div class="roll_notice">
              <TextSlide :txt="msg" data-key="a" />
            </div>
            <router-link to="/notice">
              <button type="button" class="btn_txt_more ty2">
                더보기
                <span>
                  <img src="@/assets/images/icon/arr_rg_6.png" alt="" />
                </span>
              </button>
            </router-link>
          </div>
        </div>
        <!-- 공지사항 끝 -->
      </div>
    </div>
    <!-- 메인 홈 끝 -->

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
import { TextSlide } from "vue-text-slide";
import initCustom from "../assets/js/custom";
import AppSlider from "@/components/carosalSlider/AppSlider.vue";
import MainHomeService from "@/services/MainHomeService.js";

const MESSAGES = [
  "회원약관 및 개인정보처리방침 개정 안내",
  "VS시네마 영상정보처리기기 운영 및 관리방침 개정 안내",
  "<젠틀맨> 회원시사회 당첨자 발표",
  "관람 요금 변경 안내",
  "개인정보 취급방침 변경에 따른 안내",
];

export default {
  data() {
    return {
      screeningList: [],
      currentScreen: null,
      // adminRole: false,
      // masterRole: false,
      onlyMember: false,
      masterRole:false,
      adminRole:false,
      msg: "",
      imsg: -1,

      loginPopup: false,
    };
  },
  components: {
    AppSlider,
    TextSlide,
  },
  methods: {
    // 메인화면에 영화리스트 띄우기 함수
    retrieveScreen() {
      // getAll() -> get(dname, page, size)
      MainHomeService.getAll()
        // 성공하면 .then() 결과가 전송됨
        .then((response) => {
          // this.dog = response.data -> const{dog,totalItems}= response.data
          // let(const) {속성명, 속성명2} = 데이터 객체 배열 (모던자바분법: 구조분해할당)
          const { ScreeningList } = response.data; // springboot 의 전송된 맵 정보
          let findmid = Number(ScreeningList[0].mid);
          console.log(findmid);
          ScreeningList[0].mid = findmid;
          console.log("findmid: " + findmid);
          let findmid1 = Number(ScreeningList[1].mid);
          // console.log(findmid1)
          ScreeningList[1].mid = findmid1;
          // console.log("findmid1: " +findmid1)
          let findmid2 = Number(ScreeningList[2].mid);
          // console.log(findmid2)
          ScreeningList[2].mid = findmid2;
          // console.log("findmid2: " +findmid2)
          let findmid3 = Number(ScreeningList[3].mid);
          // console.log(findmid3)
          ScreeningList[3].mid = findmid3;
          // console.log("findmid3: " +findmid3)
          let findmid4 = Number(ScreeningList[4].mid);
          // console.log(findmid4)
          ScreeningList[4].mid = findmid4;
          // console.log("findmid4: " +findmid4)
          console.log(ScreeningList);
          this.screeningList = [
            ScreeningList[0],
            ScreeningList[1],
            ScreeningList[2],
            ScreeningList[3],
            ScreeningList[4],
          ];
          // 디버깅 콘솔에 정보 출력
          console.log(response.data);
          console.log(this.screeningList);
        })
        // 실패하면 .catch() 에 에러가 전송됨
        .catch((e) => {
          console.log(e);
        });
    },

    logOut() {
      this.$store.dispatch("auth/logout");
      this.$store.dispatch("auth/nonUserLogout");
      this.$router.go();
    },
    next() {
      this.imsg = this.getIndex(this.imsg, MESSAGES.length);
      this.msg = MESSAGES[this.imsg];
    },
    getIndex(i, len) {
      return i === len - 1 ? 0 : i + 1;
    },

    checkLogin() {
      console.log(
        "회원로그인여부:" + this.loggedIn + "비회원로그인여부:" + this.loggedIn2
      );
      if (!this.loggedIn && !this.loggedIn2) {
        this.loginPopup = true;
      } else {
        this.$router.push("/reservation-check");
      }
    },
  },
  computed: {
    currentUser() {
      // console.log("값은"+this.$store.state.auth.initialState.user+this.$store.state.auth.initialState2.nonUser)
      if (
        !this.$store.state.auth.initialState.user &&
        !this.$store.state.auth.initialState2.nonUser
      ) {
        return false;
      } else {
        return true;
      }
    },
    // 로그인여부 가져오는 함수
    loggedIn() {
      return this.$store.state.auth.initialState.status.loggedIn;
    },
    // 비회원로그인여부 가져오는 함수
    loggedIn2() {
      return this.$store.state.auth.initialState2.status.loggedIn2;
    },
  
  },
  mounted() {
    // if(this.loggedIn){
    //   if(JSON.parse(localStorage.getItem("user")).roles[0].name == "ROLE_MASTER"){
    //     this.masterRole=true;
    //   }else{
    //     this.masterRole=false;
    //   }
    //   if(JSON.parse(localStorage.getItem("user")).roles[0].name == "ROLE_ADMIN"|| JSON.parse(localStorage.getItem("user")).roles[0].name == "ROLE_MASTER"){
    //     this.adminRole=true;
    //   }else{
    //     this.adminRole=false;
    //   }
    // }
    this.next();
    setInterval(() => this.next(), 2000);
    this.retrieveScreen(); // 화면 로딩시 전체 조회함수 실행
    initCustom();
  },
  
  created() {
    if(this.$store.state.auth.initialState.user){
      this.onlyMember=true;
    if(JSON.parse(localStorage.getItem("user")).roles=="ROLE_ADMIN"||JSON.parse(localStorage.getItem("user")).roles=="ROLE_MASTER"){
      if(JSON.parse(localStorage.getItem("user")).roles=="ROLE_MASTER"){
         this.adminRole=true,this.masterRole=true;
         
      }else{
         this.adminRole=true;
      }
   }else{
     this.adminRole=false;
   }
  }else{
     this.masterRole=false,this.adminRole=false;
  }
  console.log("login"+ this.masterRole,this.adminRole);

    this.retrieveScreen();

}
  
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

.container-lg,
.container-md,
.container-sm,
.container {
  max-width: 960px;
  height: 100px;
  margin-top: 50px;
}

.sec_tit {
  border-bottom: 1px dashed #d9d9d9;
  padding-bottom: 10px;
  position: relative;
  font-size: 17px;
  margin-bottom: 10px;
}
.sec_tit2 {
  padding-bottom: 10px;
  position: relative;
  font-size: 17px;
  margin-bottom: 10px;
}

.hero.overlay .main_cont_wrap.notice .sec_tit {
  display: inline-block;
  margin: 0;
  font-size: 15px;
  vertical-align: middle;
}

.hero.overlay .main_cont_wrap.notice .sec_tit:after {
  content: "";
  display: inline-block;
  width: 1px;
  height: 24px;
  background: #707070;
  margin: 0 20px;
  vertical-align: middle;
}
.hero.overlay .main_cont_wrap {
  position: relative;
  margin-bottom: 0px;
  width: 980px;
}
.special_wrap2 {
  list-style: none;
  height: 40px;
}
.hero.overlay .main_cont_wrap.premiere .premiere_wrap > li > a {
  display: block;
  overflow: hidden;
  border-radius: 4px;
  width: 280px;
}
.main_cont_wrap.premiere .premiere_wrap > li > a {
  display: block;
  overflow: hidden;
  border-radius: 4px;
  width: 270px;
}

.main_cont_wrap.premiere .premiere_wrap > li + li {
  margin-left: 10px;
}

.main_cont_wrap.premiere .premiere_wrap > li > a > img {
  width: 270px;
}

.hero.overlay .main_cont_wrap.notice .rolling_menu_wrap .rolling_menu > li {
  height: 15px;
  line-height: 15px;
  list-style-type: none;
}
.hero.overlay .main_cont_wrap.notice .rolling_menu_wrap .rolling_menu > li > a {
  font-size: 13px;
  line-height: 15px;
  color: #000;
}

.main_cont_wrap.event .event_wrap {
  position: relative;
  margin-bottom: 0;
}
.main_cont_wrap.event .event_wrap .img_lst_wrap {
  margin-left: -11px;
}
.main_cont_wrap.event .event_wrap .img_lst_wrap > li {
  height: auto;
  margin-left: 11px;
  margin-bottom: 11px;
} /* 접근성수정 2020.10.30 */
.main_cont_wrap.event .event_wrap .img_lst_wrap > li.img_w_01 {
  width: 183px;
  height: 250px;
}
.main_cont_wrap.event .event_wrap .img_lst_wrap > li.img_w_02 {
  width: 286px;
  height: 250px;
}
.main_cont_wrap.event .event_wrap .img_lst_wrap > li.img_w_03 {
  width: 295px;
  height: 511px;
}
.main_cont_wrap.event .event_wrap .img_lst_wrap > li.img_w_04 {
  width: 478px;
  height: 250px;
}
.main_cont_wrap.event .event_wrap .img_lst_wrap > li > a {
  display: block;
  width: 100%;
  height: 100%;
}
.main_cont_wrap.event .event_wrap .img_lst_wrap > li > a > img {
  display: block;
  width: 100%;
  height: 100%;
}
/* .btn_txt_more {width:100%; padding:8px 0; border:1px solid #ECECEC; background-color:#ffffff;}
 */
.btn_txt_more {
  width: 100%;
  padding: 8px 0;
  border: 1px solid #ececec;
  background-color: rgba(247, 242, 242, 1);
}
/* .btn_txt_more.ty2 {display:inline-block; background:#66666600; border:0 none; width:auto; padding:0 10px 0 0; color:#666666; font-size:10px;} */
.btn_txt_more.ty2 {
  display: inline-block;
  border: 0 none;
  width: auto;
  padding: 0 10px 0 0;
  color: #666666;
  font-size: 10px;
}

.main_cont_wrap .btn_txt_more {
  position: absolute;
  right: 0;
  top: -13px;
  background-position: right 3.5px;
  font-size: 14px;
}
.main_cont_wrap.premiere .premiere_wrap:after {
  content: "";
  display: block;
  clear: both;
}
.main_cont_wrap.premiere .premiere_wrap > li {
  float: left;
}

.site-nav .site-navigation .mini-menu {
  margin-bottom: 0;
  margin-top: 4px;
  /* padding-right: 20px; */
  padding-left: 10px;
}
.site-nav .site-navigation .mini-menu > li .btn_my {
  padding-left: 15px;
  background-size: auto;
  background: url(https://www.lottecinema.co.kr/NLCHS/Content/images/icon/nav_side_my.png)
    0 center;
  background-repeat: no-repeat;
  background-position-y: 11px;
}

.site-nav .site-navigation .mini-menu > li .btn_regist {
  padding-left: 15px;
  background: url(https://www.lottecinema.co.kr/NLCHS/Content/images/icon/nav_side_reserve.png)
    0 center;
  background-repeat: no-repeat;
  background-position-y: 11px;
}

.site-nav .site-navigation .mini-menu > li {
  display: inline-block;
}

.site-nav .site-navigation .mini-menu > li > a {
  font-size: 10px;
  padding: 10px 8px;
  display: inline-block;
  text-decoration: none !important;
  color: rgba(255, 255, 255, 0.7);
}
.site-nav .site-navigation .mini-menu > li > a:hover {
  color: #f17945;
}
.site-nav .site-navigation .mini-menu > li.cta-button a {
  padding: 6px 20px;
  border-radius: 30px;
  background: #f17945;
  border: 2px solid #f17945;
}
.site-nav .site-navigation .mini-menu > li.cta-button a:hover {
  color: #fff;
  background: transparent;
  border: 2px solid rgba(255, 255, 255, 0.2);
}
.site-nav .site-navigation .mini-menu > li.active > a {
  color: #fff;
}

.roll_notice {
  display: inline-block;
}
.bl-animate-value-box {
  font-size: 14px;
  height: 20px;
  width: 800px;
}
</style>
