<template>
  <div>
    <!-- 헤더 component -->
    <NavBlack></NavBlack>

    <!-- 상단 carosal 시작 -->
    <div class="slideImg">
      <div class="container">
        <ImageSlider :slideImg="slideImg" />
      </div>
    </div>
    <!-- 상단 carosal 끝 -->

    <!-- content 시작 -->
    <div id="contents">
      <!-- detail top 시작 -->
      <div class="detail_top_wrap new22">
        <div class="poster_info">
          <img :src="currentScreen.mposter" :alt="currentScreen.mtitle" />
        </div>
        <div class="tit_info">
          <span
            :class="{
              'ic_grade gr_12': currentScreen.mage == '12',
              'ic_grade gr_15': currentScreen.mage == '15',
              'ic_grade gr_18': currentScreen.mage == '18',
              'ic_grade gr_all': currentScreen.mage == 'all',
              'ic_grade gr_null': currentScreen.mage == '미정',
            }"
            >만{{ currentScreen.mage }}세이상관람가 </span
          ><strong>{{ currentScreen.mtitle }}</strong>
        </div>
        <ul class="detail_info1">
          <li class="sub_info1" style="cursor: pointer">
            <em>관람객 평점</em
            ><strong class="txt_ic_score ty2"
              ><em>평점</em
              ><strong>{{ currentScreen.audiencesRate }} </strong></strong
            >
          </li>
          <li class="sub_info2">
            <em>예매율 {{ currentScreen.rank }}위 </em
            ><strong>{{ currentScreen.ticketingPer }}% </strong>
          </li>
          <li class="sub_info3">
            <em>누적관객수</em
            ><strong
              >{{
                Number(currentScreen.mcumulativeAudience).toLocaleString("en")
              }}
              <em>명</em> <span class="date_info"> </span
            ></strong>
          </li>
        </ul>
        <ul class="detail_info2">
          <li class="sub_info1">
            <em>장르</em
            ><strong
              ><em>{{ currentScreen.mcode }}</em
              ><em>{{ changeDateString(currentScreen.mreleaseDate) }} 개봉</em
              ><em class="time_info">{{
                currentScreen.mrunningTime
              }}</em></strong
            >
          </li>
          <li class="sub_info2">
            <em>감독</em
            ><strong class="line_type">{{ currentScreen.mdirector }}</strong>
          </li>
          <li class="sub_info3">
            <em>출연</em
            ><strong class="line_type">{{
              replaceAll(currentScreen.mactor, "//", ", ")
            }}</strong>
          </li>
        </ul>
        <div class="spacial_hall_info">
          <span
            ><a href="/4d" title="샤롯데바로가기"
              ><img
                src="https://caching.lottecinema.co.kr/images/cinema/special/special_theatergray_300.png"
                alt="샤롯데바로가기" /></a></span
          ><span
            ><a href="/color" title="수퍼플렉스 바로가기"
              ><img
                src="https://caching.lottecinema.co.kr/images/cinema/special/special_theatergray_940_20221117.png"
                alt="수퍼플렉스 바로가기" /></a></span
          ><span
            ><a href="/comfort" title="수퍼S 바로가기"
              ><img
                src="https://caching.lottecinema.co.kr/images/cinema/special/special_theatergray_980.png"
                alt="수퍼S 바로가기" /></a
          ></span>
        </div>
        <div class="movie_detail_aside_menu type2">
          <ul>
            <li>
              <button type="button" class="btn_ic_share" @click="sharePopUp()">
                <em>공유하기</em>
              </button>
              <div
                class="modal-bg"
                :class="{ active: isActive2 }"
                @click="btnCloseLayer()"
              ></div>
              <div
                id="layerShare"
                class="layer_wrap layer_share"
                :class="{ active: isActive2 }"
                tabindex="0"
                style="
                  left: 50%;
                  top: 50%;
                  margin-top: -122.5px;
                  margin-left: -225px;
                "
              >
                <strong class="hidden" v-if="false">레이어 팝업 시작</strong>
                <div class="layer_header">
                  <h4 class="tit" style="height: 54px; line-height: 58px">
                    공유하기
                  </h4>
                  <button
                    type="button"
                    class="btn_close btnCloseLayer"
                    @click="btnCloseLayer()"
                  >
                    팝업 닫기
                  </button>
                </div>
                <div class="layer_contents">
                  <ul class="list_share" style="margin-left: -32px">
                    <li>
                      <a
                        href="https://ko-kr.facebook.com/facebook/"
                        class="btn_share_fb"
                        tabindex="0"
                        >페이스북</a
                      >
                    </li>
                    <li>
                      <a
                        href="https://story.kakao.com"
                        class="btn_share_ks"
                        tabindex="0"
                        >카카오스토리</a
                      >
                    </li>
                  </ul>
                  <div class="bx_share_copy">
                    <label
                      ><span class="hidden" v-if="false">url 주소 복사</span
                      ><input
                        type="text"
                        disabled=""
                        :value="currentUrl" /></label
                    ><button type="button" class="btn_col2 ty6">
                      URL 복사
                    </button>
                  </div>
                </div>
                <strong class="hidden txtTabIndex" tabindex="0" v-if="false"
                  >레이어 팝업 끝</strong
                >
              </div>
            </li>
            <li>
              <button
                type="button"
                id="mylikemovie"
                @click="likeMovie()"
                class="btn_ic_wish"
                :class="{ active: isActive }"
              >
                <strong>좋아요</strong>
              </button>
              <!-- 로그인 필요 팝업 시작 -->
              <div :class="{ dim200: loginPopup == true }"></div>
              <div
                id="layerCommonAlert"
                class="layer_wrap layer_alert layer_wrap200 active"
                v-if="loginPopup"
                tabindex="0"
                style="
                  left: 50%;
                  top: 50%;
                  margin-top: -96.5px;
                  margin-left: -190px;
                "
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
                <strong class="hidden txtTabIndex" tabindex="0"
                  >레이어 팝업 끝</strong
                >
              </div>
            </li>
            <li class="area_reserve">
              <a href="/reservation" class="btn_col1 ty7 rnd"
                >예매하기</a
              >
            </li>
          </ul>
        </div>
      </div>
      <!-- detail top 끝 -->
      <!-- moviedetailbar 시작 -->
      <ul class="tab_wrap outer moviedetailbar actionmovingbar new22">
        <!-- 영화정보 tab 시작 -->
        <li class="" :class="{ active: isInfoTab }">
          <button
            type="button"
            class="tab_tit"
            @click="btnInfo()"
            style="width: 470px; left: 50%; float: left"
          >
            <router-link
              :to="'/movie/movieinfo/info/' + currentScreen.mid"
              style="color: #666666"
              >영화정보</router-link
            >
          </button>
        </li>
        <li class="" :class="{ active: !isInfoTab }">
          <button
            type="button"
            class="tab_tit"
            @click="btnReview()"
            style="width: 470px; left: 50%; float: left"
          >
            <router-link
              :to="'/movie/movieinfo/review/' + currentScreen.mid"
              style="color: #666666"
              >평점 및 관람평 ({{ userCount }})</router-link
            >
          </button>
        </li>
        <!-- 영화정보 tab 끝 -->
        <!-- 평점 및 관람평 시작 -->
        <li class=""></li>
        <!-- 평점 및 관람평 끝 -->
        <li class="wrap_nav_underline"><span class="nav_underline"></span></li>
        <router-view />
      </ul>
      <!-- moviedetailbar 끝 -->
    </div>
    <!-- content 끝 -->

    <!-- 푸터 component -->
  </div>
</template>

<script>
import initCustom from "@/assets/js/custom";
import ImageSlider from "@/components/carosalSlider/ImageSlider.vue";
import NavBlack from "@/components/common/NavBlack.vue";
import MainHomeService from "@/services/MainHomeService.js";

// const slideImg = [
//   {
//     img: "https://caching.lottecinema.co.kr//Media/MovieFile/MovieImg/202212/18755_105_13.jpg",
//   },
//   {
//     img: "https://caching.lottecinema.co.kr//Media/MovieFile/MovieImg/202212/18755_105_14.jpg",
//   },
//   {
//     img: "https://caching.lottecinema.co.kr//Media/MovieFile/MovieImg/202212/18755_105_15.jpg",
//   },
// ];

export default {
  name: "MovieDetail",
  data() {
    return {
      slideImg: [],
      currentScreen: null,
      userFavorit: null,
      message: "",
      isActive: false, // 하트 버튼 토글
      isActive2: false, // 공유 버튼 토글
      isInfoTab: true, // 영화 상세정보 <-> 관람평
      currentUrl: "", // url 가져오기
      userCount: 0, // 총 댓글 수
      page: 0,
      size: 10,
      // 로그인 필요 서비스 알림 팝업
      loginPopup: false,
    };
  },
  methods: {
    getScreen(mid) {
      // alert("MainHomeService.get(mid)")
      // axios 공통함수 호출
      MainHomeService.get(mid)
        // 성공하면 .then() 결과가 리턴됨
        .then((response) => {
          // springboot 결과를 리턴함(부서 객체)
          const { movieInfo, userFavorit } = response.data;
          this.currentScreen = movieInfo[0];
          console.log(this.currentScreen.mimage1);
          console.log(this.currentScreen.mimage2);
          console.log(this.currentScreen.mimage3);
          console.log(this.currentScreen.mimage4);
          console.log(this.currentScreen.mimage5);
          this.slideImg = [
            {
              img: this.currentScreen.mimage1,
            },
            {
              img: this.currentScreen.mimage2,
            },
            {
              img: this.currentScreen.mimage3,
            },
            {
              img: this.currentScreen.mimage4,
            },
            {
              img: this.currentScreen.mimage5,
            },
          ];
          this.userFavorit = userFavorit;
          // 콘솔 로그 출력
          console.log(response.data);
        })
        // 실패하면 .catch() 에러메세지가 리턴됨
        .catch((e) => {
          console.log(e);
        });
    },
    // 개봉일 날짜에 구분자(.) 넣어주기 위한 함수
    changeDateString(date) {
      let year = date.substr(0, 4);
      let month = date.substr(4, 2);
      let day = date.substr(6, 2);
      return year + "." + month + "." + day;
    },
    // 하트 버튼 토글
    likeMovie() {
      // 로그인 체크
      console.log(
        "회원로그인여부:" + this.loggedIn + "비회원로그인여부:" + this.loggedIn2
      );
      // 회원&비회원 로그인 여부로 함수 실행
      if (!this.loggedIn && !this.loggedIn2) {
        this.loginPopup = true;
      } else {
        this.isActive = !this.isActive;
        alert("관심 영화에 추가되었습니다");
      }
    },
    sharePopUp() {
      this.isActive2 = !this.isActive2;
    },
    btnCloseLayer() {
      this.isActive2 = false;
    },
    btnInfo() {
      this.isInfoTab = true;
    },
    btnReview() {
      this.isInfoTab = false;
    },
    // 총 댓글 수 가져오기 함수, service의 댓글불러오기(최신순) 함수 중 일부만 사용
    getUserCount(mid) {
      MainHomeService.getReviewRecent(mid, this.page, this.size)
        .then((response) => {
          const { ReviewCount } = response.data;
          this.userCount = ReviewCount.userCount;
          // 콘솔 로그 출력
          console.log(ReviewCount);
        })
        // 실패하면 .catch() 에러메세지가 리턴됨
        .catch((e) => {
          console.log(e);
        });
    },
    // 문자열 바꾸기 함수
    replaceAll(str, searchStr, replaceStr) {
      if(str==null){
        return ""; 
      }
      return str.split(searchStr).join(replaceStr);
    },
  },
  mounted() {
    // alert(this.$route.params.mid)
    this.currentUrl = window.location.href;
    this.getScreen(this.$route.params.mid);
    this.getUserCount(this.$route.params.mid);
    initCustom();
  },
  components: {
    NavBlack,
    ImageSlider,
  },
  computed:{
     
     // 로그인여부 가져오는 함수
     loggedIn() {
       return this.$store.state.auth.initialState.status.loggedIn;
     },
     // 비회원로그인여부 가져오는 함수
     loggedIn2() {
       return this.$store.state.auth.initialState2.status.loggedIn2;
     },
 
   },
};
</script>

<style>
ul {
  list-style-type: none;
}
.hidden {
  overflow: hidden;
  position: absolute !important;
  width: 1px;
  height: 1px;
  clip: rect(1px, 1px, 1px, 1px);
}
.slideImg {
  height: 485px;
  margin: 0 auto;
  background-color: black;
}
.slideImg2 {
  width: 948px;
  height: 457px;
  margin: 0 auto;
  background-color: black;
  margin-top: 35px;
}
#contents {
  width: 980px;
  margin: 0 auto;
  padding: 50px 0 0 0;
  padding-top: 35px;
}
.modal-bg.active {
  display: block;
  width: 100%;
  height: 100%;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  background: rgba(0, 0, 0, 0.6);
  z-index: 99;
}
.layer_wrap {
  overflow: hidden;
  position: absolute;
  z-index: -1;
  width: 0px;
  height: 0px;
  border: none;
}
.layer_wrap.active {
  display: block;
  position: fixed;
  z-index: 100;
  height: auto;
  border-radius: 10px;
  background-color: #fff;
}
.layer_wrap.active.layer_share {
  width: 450px;
  font-family: "Noto Sans KR", "Roboto", "dotum", "sans-serif";
  font-size: 12px;
}
.layer_wrap .layer_header {
  position: relative;
  height: 54px;
  line-height: 58px;
  border-bottom: 1px solid #ccc;
  color: #fff;
  text-align: center;
}
.layer_wrap .layer_header .tit {
  font-size: 18px;
  color: #000;
}
.layer_wrap .layer_header strong {
  font-size: 18px;
}
.layer_wrap .layer_header .btn_close {
  position: absolute;
  right: 15px;
  top: 15px;
  width: 19px;
  height: 19px;
  border: none;
  text-indent: -9999em;
  background: transparent
    url(https://www.lottecinema.co.kr/NLCHS/Content/images/icon/close_19.png)
    no-repeat 0 0;
  outline-color: black;
}
.layer_wrap .layer_header .btn_close.wht,
.layer_wrap .layer_contents .btn_close.wht {
  position: absolute;
  right: 15px;
  top: 15px;
  width: 19px;
  height: 19px;
  border: none;
  text-indent: -9999em;
  background: transparent
    url(https://www.lottecinema.co.kr/NLCHS/Content/images/icon/close_19_wht.png)
    no-repeat 0 0;
}
.layer_wrap .layer_header .tit > img {
  vertical-align: middle;
}
.layer_wrap .layer_contents {
  padding: 30px 25px;
}
.layer_wrap .layer_contents.overflowtype1 {
  overflow: hidden;
  overflow-y: scroll;
  height: 600px;
}
.layer_wrap .layer_contents.pad0 {
  padding: 0;
}
.layer_wrap .layer_footer {
  clear: both;
  border-top: 1px solid #ddd;
  text-align: center;
  background-color: #f8f8f8;
}
.layer_wrap .layer_footer ul:after {
  content: "";
  display: block;
  clear: both;
}
.layer_wrap .layer_footer ul li {
  position: relative;
  float: left;
  width: 50%;
}
.layer_wrap .layer_footer ul li:before {
  content: "";
  position: absolute;
  left: 0;
  top: 50%;
  height: 25px;
  margin-top: -12px;
  border-left: 1px solid #ddd;
}
.layer_wrap .layer_footer ul li:first-child:before {
  display: none;
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
}
.layer_wrap .layer_footer.ty2 ul li {
  width: 100%;
}
.layer_wrap .tit_type2 {
  font-size: 13px;
  padding-bottom: 10px;
  line-height: 1.5;
}
.contgapline1 {
  display: block;
  height: 1px;
  margin: 15px 0;
  background-color: #eee;
}
.list_share:after {
  content: "";
  display: block;
  clear: both;
}
.list_share li {
  float: left;
  margin-left: -32px;
}
.list_share li:first-child {
  margin-left: 0;
}
.list_share li a {
  display: block;
  color: #000;
  height: 35px;
  line-height: 35px;
  padding-left: 45px;
  font-size: 15px;
  white-space: nowrap;
  background-repeat: no-repeat;
  background-position: 0 center;
}
.list_share li a.btn_share_fb {
  background-image: url(https://www.lottecinema.co.kr/NLCHS/Content/images/icon/share_facebook.png);
}
.list_share li a.btn_share_ks {
  background-image: url(https://www.lottecinema.co.kr/NLCHS/Content/images/icon/share_kstory.png);
}
.list_share li a.btn_share_kt {
  background-image: url(https://www.lottecinema.co.kr/NLCHS/Content/images/icon/share_ktalk.png);
}
.bx_share_copy {
  margin: 30px 0 20px;
}
.bx_share_copy label input {
  width: 300px;
}
.layer_wrap input[type="password"],
.layer_wrap input[type="text"],
.layer_wrap input[type="password"] {
  height: 45px;
  line-height: 45px;
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
.btn_col1.ty6,
.btn_col2.ty6,
.btn_col3.ty6,
.btn_col4.ty6,
.btn_col5.ty6,
.btn_col6.ty6,
.btn_col7.ty6,
.btn_col8.ty6,
.btn_col9.ty6,
.btn_col10.ty6 {
  height: 45px;
  line-height: 43px;
  padding: 0 18px;
}
.btn_col1.disabled,
.btn_col2.disabled,
.btn_col3.disabled,
.btn_col4.disabled,
.btn_col5.disabled,
.btn_col6.disabled,
.btn_col7.disabled,
.btn_col8.disabled,
.btn_col9.disabled,
.btn_col10.disabled {
  border-color: #adadad;
  color: #ffffff !important;
  cursor: default;
  pointer-events: none;
  background-color: #adadad;
}
.btn_col1.disabled.active,
.btn_col2.disabled.active,
.btn_col3.disabled.active,
.btn_col4.disabled.active,
.btn_col5.disabled.active,
.btn_col6.disabled.active,
.btn_col7.disabled.active,
.btn_col8.disabled.active,
.btn_col9.disabled.active,
.btn_col10.disabled.active {
  cursor: pointer;
  pointer-events: inherit;
}
.detail_top_wrap {
  position: relative;
  min-height: 240px;
  padding-left: 245px;
  margin-bottom: 15px;
}
.detail_top_wrap .poster_info {
  position: absolute;
  /* top: -66px; */
  left: 0;
  width: 205px;
  height: 305px;
  z-index: 1;
}
.detail_top_wrap .poster_info img {
  width: 100%;
  border-radius: 4px;
  height: 100%;
}
.detail_top_wrap .tit_info {
  margin: -2px 0 15px 0;
  font-size: 26px;
}
.detail_top_wrap .ic_grade {
  width: 36px;
  height: 36px;
  margin: 2px 4px 0 0;
  line-height: 36px;
  vertical-align: top;
}
.detail_top_wrap .ic_grade:before {
  font-size: 13px;
}
.detail_top_wrap .detail_info1 {
  overflow: hidden;
  padding-bottom: 17px;
  margin-bottom: 20px;
  font-size: 15px;
  border-bottom: 1px solid #ddd;
}
.detail_top_wrap .detail_info1 > li {
  float: left;
  margin-right: 25px;
}
.detail_top_wrap .detail_info1 > li > em {
  margin-right: 10px;
  vertical-align: middle;
}
.detail_top_wrap .detail_info1 > li strong {
  font-size: 20px;
  font-family: "Roboto", "Noto Sans KR";
  vertical-align: middle;
  margin-top: -2px;
}
.detail_top_wrap .detail_info1 > li strong em {
  font-family: "Noto Sans KR";
}
.detail_top_wrap .detail_info1 > li strong .date_info {
  font-size: 14px;
  color: #666;
}
.detail_top_wrap .detail_info2 {
  margin-bottom: 17px;
  font-size: 13px;
}
.detail_top_wrap .detail_info2 > li {
  margin-top: 11px;
}
.detail_top_wrap .detail_info2 > li:first-child {
  margin-top: -10px;
}
.detail_top_wrap .detail_info2 > li em {
  margin-right: 10px;
}
.detail_top_wrap .detail_info2 > li strong {
  color: #666;
  margin-top: -4px;
}
.detail_top_wrap .detail_info2 > li.sub_info3 strong {
  line-height: 24px;
}
.detail_top_wrap .detail_info2 .sub_info1 strong > em {
  position: relative;
  padding-left: 15px;
}
.detail_top_wrap .detail_info2 .sub_info1 strong > em:first-child {
  padding-left: 0;
}
.detail_top_wrap .detail_info2 .sub_info1 strong > em:before {
  content: "";
  display: block;
  position: absolute;
  top: 5px;
  left: 0;
  width: 1px;
  height: 9px;
  background: #ddd;
}
.detail_top_wrap .detail_info2 .sub_info1 strong > em:first-child:before {
  display: none;
}
.detail_top_wrap .detail_info2 .sub_info2 .line_type a,
.detail_top_wrap .detail_info2 .sub_info3 .line_type a {
  border-bottom: 1px solid #666;
  color: #666;
}
.detail_top_wrap .detail_info3 li strong {
  margin-top: -3px;
}
.detail_top_wrap .spacial_hall_info {
  float: left;
  position: relative;
  width: 400px;
}
.detail_top_wrap .spacial_hall_info::after {
  content: "";
  display: block;
  clear: both;
}
.detail_top_wrap .spacial_hall_info span {
  display: inline-block;
  min-width: 82px;
  padding: 0 5px;
  margin-bottom: 10px;
  text-align: center;
}
.detail_top_wrap .spacial_hall_info span img {
  height: 20px;
}
.detail_top_wrap.new22 .ic_grade:before {
  margin: 7px 0 0 7px;
}
.movie_detail_aside_menu {
  padding-left: 430px;
}
.movie_detail_aside_menu ul:after {
  content: "";
  display: block;
  clear: both;
}
.movie_detail_aside_menu ul > li {
  float: left;
  margin-left: 32px;
}
.movie_detail_aside_menu ul > li:first-child {
  margin-left: 0;
}
.movie_detail_aside_menu ul > li .btn_ic_share {
  display: block;
  position: relative;
  width: 28px;
  height: 28px;
  padding: 0 10px;
  background: url("../assets/images/icon/ic_share.png") no-repeat 50% 0;
  border: 0;
  background-size: cover;
}
.movie_detail_aside_menu ul > li .btn_ic_share em {
  overflow: hidden;
  position: absolute !important;
  width: 1px;
  height: 1px;
  clip: rect(1px, 1px, 1px, 1px);
}
.movie_detail_aside_menu ul > li .btn_ic_wish {
  display: block;
  position: relative;
  width: 39px;
  height: 28px;
  padding-left: 10px;
  background: url("../assets/images/icon/heart_off.png") no-repeat 10px 0;
  border: none;
  background-size: contain;
}
.movie_detail_aside_menu ul > li .btn_ic_wish strong {
  overflow: hidden;
  position: absolute !important;
  width: 1px;
  height: 1px;
  clip: rect(1px, 1px, 1px, 1px);
}
.movie_detail_aside_menu ul > li .btn_ic_wish em {
  display: block;
  min-width: 20px;
  margin-left: 0px;
  position: absolute;
  top: 5px;
  left: 50%;
  padding: 1px 0;
  background: #fff;
  font-size: 11px;
  line-height: 11px;
  font-family: "Roboto", "Noto Sans KR";
  text-align: center;
}
.movie_detail_aside_menu ul > li .btn_ic_wish em.max:after {
  content: "+";
}
.movie_detail_aside_menu ul > li .btn_ic_wish.active {
  background: url("../assets/images/icon/heart_full.png") no-repeat 10px 0;
  filter: opacity(0.4) drop-shadow(0 0 0 #ff243e);
  background-size: contain;
}
.movie_detail_aside_menu ul > li.area_reserve {
  margin-top: -15px;
}
.movie_detail_aside_menu ul > li.area_reserve .btn_col1 {
  min-width: 120px;
  font-size: 18px;
  font-family: "Roboto", "Noto Sans KR";
}
.detail_top_wrap.new22 .movie_detail_aside_menu.type1 {
  padding-top: 15px;
}
.detail_top_wrap.new22 .movie_detail_aside_menu.type2 {
  padding-top: 25px;
}
/* 평점 :유형1 ========== */
.txt_ic_score {
  display: inline-block;
  vertical-align: middle;
}
.txt_ic_score em {
  overflow: hidden;
  display: inline-block;
  vertical-align: middle;
  text-indent: -9999em;
  background: url("../assets/images/icon/star_10.png") no-repeat 0 0;
}
.txt_ic_score strong {
  display: inline-block;
  font-family: "Roboto";
  vertical-align: middle;
}
/* ty2 (사이즈 수정 해도됨, 수정 후 이 문구 지우삼) */
.txt_ic_score.ty2 em {
  width: 14px;
  height: 14px;
  margin: -4px 3px 0 0;
  background: url("../assets/images/icon/star_14.png") no-repeat 0 0;
  background-size: cover;
}
.txt_ic_score.ty2 strong {
  font-size: 15px;
}
.movi_tab_info1 {
  overflow: hidden;
  margin: 0px 0 45px 0;
}
.contents_movie_detail .tit_info {
  display: block;
  margin-bottom: 20px;
  font-size: 15px;
}
.contents_movie_detail .tit_info strong {
  font-size: 26px;
}
.contents_movie_detail .banner_wrap {
  margin-top: 60px;
}
.contents_movie_detail .no_data {
  padding: 140px 0;
  background: #f8f8f8;
  text-align: center;
  font-size: 15px;
  border-top: 1px solid #ccc;
}
.contents_movie_detail .list_bdr_box {
  margin-top: 60px;
}
.contents_movie_detail .list_txt {
  margin-left: 10px;
}
.movi_tab_info1 .left_con {
  float: left;
  width: 450px;
}
.movi_tab_info1 .left_con .mCustomScrollbar {
  height: 115px;
  overflow: hidden;
  font-family: "Roboto", "Noto Sans KR";
}
.movi_tab_info1 .left_con .txt_info {
  padding-right: 80px;
  font-size: 13px;
  line-height: 1.8;
  font-family: "Roboto", "Noto Sans KR";
}
.movi_tab_info1 .right_con {
  float: right;
  width: 480px;
  font-family: "Roboto", "Noto Sans KR";
}
.movi_tab_info1 .group_graph:after {
  content: "";
  display: block;
  clear: both;
}
.movi_tab_info1 .group_graph .bx_graph01 {
  float: left;
  position: relative;
  width: 32%;
  height: 76px;
  margin-top: 49px;
}
.movi_tab_info1 .group_graph .bx_graph01 .bg {
  position: absolute;
  z-index: 1;
  top: -9px;
  width: 100%;
  height: 51px;
  background: url(https://www.lottecinema.co.kr/NLCHS/Content/images/movie/graph_bgx2.png)
    no-repeat center 0;
  background-size: 111px auto;
}
.movi_tab_info1 .group_graph .bx_graph01 dl {
  position: relative;
  height: 51px;
  border-bottom: 1px solid #666666;
}
.movi_tab_info1 .group_graph .bx_graph01 dl dt {
  position: absolute;
  bottom: -20px;
  width: 50%;
  font-size: 10px;
  color: #666;
  text-align: center;
  white-space: nowrap;
}
.movi_tab_info1 .group_graph .bx_graph01 dl dt.mal {
  left: 0;
}
.movi_tab_info1 .group_graph .bx_graph01 dl dt.fem {
  left: 50%;
}
.movi_tab_info1 .group_graph .bx_graph01 dl dd {
  position: absolute;
  left: 50%;
  top: 0;
  bottom: 0;
}
.movi_tab_info1 .group_graph .bx_graph01 dl dd.mal {
  width: 17px;
  margin-left: -36px;
}
.movi_tab_info1 .group_graph .bx_graph01 dl dd.fem {
  width: 28px;
  margin-left: 9px;
}
.movi_tab_info1 .group_graph .bx_graph01 dl dd .bar {
  position: absolute;
  bottom: 0;
  width: 100%;
}
.movi_tab_info1 .group_graph .bx_graph01 dl dd.mal .bar {
  background-color: #8be2fc;
}
.movi_tab_info1 .group_graph .bx_graph01 dl dd.fem .bar {
  background-color: #fb5b95;
}
.movi_tab_info1 .group_graph .bx_graph01 dl dd strong {
  position: absolute;
  width: 38px;
  z-index: 2;
  top: -3px;
  font-family: "Roboto";
  font-size: 12px;
  white-space: nowrap;
}
.movi_tab_info1 .group_graph .bx_graph01 dl dd strong span {
  font-size: 10px;
}
.movi_tab_info1 .group_graph .bx_graph01 dl dd.mal strong {
  right: -36px;
  color: #8be2fc;
}
.movi_tab_info1 .group_graph .bx_graph01 dl dd.fem strong {
  right: -29px;
  color: #fb5b95;
}
.movi_tab_info1 .group_graph .bx_graph02 {
  float: right;
  width: 280px;
  height: 110px;
  padding-top: 15px;
}
.movi_tab_info1 .group_graph .bx_graph02 dl {
  position: relative;
  height: 85px;
  border-bottom: 1px solid #666666;
}
.movi_tab_info1 .group_graph .bx_graph02 dl dt {
  position: absolute;
  bottom: -20px;
  width: 50px;
  font-size: 12px;
  color: #666;
  text-align: center;
  white-space: nowrap;
}
.movi_tab_info1 .group_graph .bx_graph02 dl dt.gen10 {
  left: 36px;
}
.movi_tab_info1 .group_graph .bx_graph02 dl dt.gen20 {
  left: 86px;
}
.movi_tab_info1 .group_graph .bx_graph02 dl dt.gen30 {
  left: 138px;
}
.movi_tab_info1 .group_graph .bx_graph02 dl dt.gen40 {
  left: 188px;
}
.movi_tab_info1 .group_graph .bx_graph02 dl dd {
  position: absolute;
  bottom: 0;
  width: 50px;
}
.movi_tab_info1 .group_graph .bx_graph02 dl dd.gen10 {
  left: 36px;
}
.movi_tab_info1 .group_graph .bx_graph02 dl dd.gen20 {
  left: 86px;
}
.movi_tab_info1 .group_graph .bx_graph02 dl dd.gen30 {
  left: 138px;
}
.movi_tab_info1 .group_graph .bx_graph02 dl dd.gen40 {
  left: 188px;
}
.movi_tab_info1 .group_graph .bx_graph02 dl dd .bar {
  display: block;
  width: 8px;
  height: 100%;
  margin: 0 auto;
  border-radius: 8px 8px 0 0;
  background-color: #8b8b8b;
}
.movi_tab_info1 .group_graph .bx_graph02 dl dd strong {
  position: absolute;
  top: 0;
  width: 100%;
  margin-top: -15px;
  font-family: "Roboto";
  font-size: 12px;
  text-align: center;
  white-space: nowrap;
}
.movi_tab_info1 .group_graph .bx_graph02 dl dd strong span {
  font-size: 10px;
}
.movi_tab_info1 .group_graph .bx_graph02 dl dd.top .bar {
  background-color: #f51641;
}
.movi_tab_info1 .group_graph .bx_graph02 dl dd.top strong {
  color: #f51641;
}
.movi_tab_info2 {
  position: relative;
  margin-bottom: 60px;
}
.movi_tab_info2 .tit_info {
  position: relative;
  margin: 0 50px -20px 0;
  border-bottom: 1px dashed #d9d9d9;
  font-weight: bold;
}
.movi_tab_info2 .swiper-wrapper {
  padding-top: 37px;
}
.movi_tab_info3 .tit_info {
  position: relative;
  margin: 0 50px -20px 0;
  border-bottom: 1px dashed #d9d9d9;
  font-weight: bold;
}
.tab_wrap.outer.new22 > li > .tab_tit {
  font-size: 18px;
  font-family: "Noto Sans KR", "Roboto";
  background-color: #fff;
  border: none;
  border-bottom: 1px solid #666666;
}
.tab_wrap.outer.new22 > li.active > .tab_tit {
}
.tab_wrap.outer.new22 > li .wrap_reviewstarscore {
  padding: 30px;
  margin-top: -30px;
  background: #f8f8f8;
}
.tab_wrap.outer.new22 .movi_review_box {
  margin-bottom: 0;
  background: #fff;
  font-family: "Noto Sans KR", "Roboto";
}
.tab_wrap.outer.new22 .movi_review_box .review_write_box {
  width: 771px;
  background: #fff;
}
.tab_wrap.outer.new22 .movi_review_box .btn_submit {
  background: #414141;
}
.tab_wrap.outer.new22 .movi_review_box .review_write_box .byte_info {
  margin: 0 10px 5px 0;
  margin-top: -3px;
  background: #fff;
}
.tab_wrap.outer.new22 .wrap_reviewstarscore .star_score_box {
  margin-top: 50px;
}
.tab_wrap.outer.new22 .wrap_reviewstarscore .star_score_box .h4_tit {
  padding: 20px 0;
  font-size: 18px;
  text-align: center;
  margin-top: 20px;
}
.tab_wrap.outer.new22 .wrap_reviewstarscore .star_score_box .star_info {
  display: flex;
  align-items: center;
  justify-content: center;
}
.tab_wrap.outer.new22 .wrap_reviewstarscore .star_score_box .score_info {
  width: 70px;
  padding-left: 5px;
  font-family: "Roboto";
  font-size: 46px;
}
.tab_wrap.outer.new22 .wrap_reviewstarscore .star_score_box .txt_info {
  padding: 15px 0 40px;
  font-size: 15px;
  color: #999;
  text-align: center;
}
.tab_wrap.outer.new22 .wrap_reviewstarscore .star_score_box .txt_info .num {
  color: #666;
  font-family: "Roboto";
}
.tab_wrap.outer.new22 .movi_review_list {
  padding-top: 30px;
}
.tab_wrap.outer.new22 .movi_review_list .review_top {
  display: flex;
  align-items: center;
  margin-bottom: 8px;
}
.tab_wrap.outer.new22 .movi_review_list .review_top .tit {
  font-family: "Roboto";
  color: #999;
}
.tab_wrap.outer.new22 .movi_review_list .review_top .tit .txt_ic_score {
  font-size: 12px;
  vertical-align: baseline;
}
.tab_wrap.outer.new22
  .movi_review_list
  .review_top
  .tit
  .txt_ic_score
  .starimg {
  position: relative;
  padding-left: 40px;
  font-family: "Noto Sans KR";
  font-size: 13px;
}
.tab_wrap.outer.new22
  .movi_review_list
  .review_top
  .tit
  .txt_ic_score
  .starimg:before {
  content: "";
  display: block;
  position: absolute;
  left: 10px;
  top: 50%;
  margin-top: -13px;
  width: 26px;
  height: 26px;
  background: url(https://www.lottecinema.co.kr/NLCHS/Content/images/icon/icon_star_new22.svg)
    no-repeat 0 0;
  background-size: 100%;
}
.tab_wrap.outer.new22 .movi_review_list .review_top .tit .txt_ic_score .num {
  padding-left: 8px;
  font-family: "Roboto";
  font-size: 18px;
  color: #333;
}
.tab_wrap.outer.new22 .movi_review_list .review_top .wrap_sort_right {
  display: flex;
  align-items: center;
  margin-left: auto;
}
.tab_wrap.outer.new22
  .movi_review_list
  .review_top
  .wrap_sort_right
  .total_num {
  font-size: 15px;
}
.tab_wrap.outer.new22
  .movi_review_list
  .review_top
  .wrap_sort_right
  .total_num
  em {
  font-family: "Roboto";
  font-weight: bold;
}
.sort_list:after {
  content: "";
  display: block;
  clear: both;
}
.sort_list > li {
  float: left;
  margin-left: 20px;
}
.sort_list > li button {
  display: block;
  position: relative;
  color: #666;
  font-size: 15px;
  border: 0;
  padding: 0;
}
.sort_list > li button:before {
  content: "";
  display: inline-block;
  width: 7px;
  height: 7px;
  margin: -2px 8px 0 0;
  background: #ddd;
  border-radius: 50%;
  vertical-align: middle;
}
.sort_list > li.active button:before {
  background: #ff7787;
}
.tab_wrap.outer.new22
  .movi_review_list
  .review_top
  .wrap_sort_right
  .sort_list {
  position: relative;
  margin-left: 5px;
}
.tab_wrap.outer.new22
  .movi_review_list
  .review_top
  .wrap_sort_right
  .sort_list:before {
  content: "";
  display: block;
  position: absolute;
  left: 5px;
  top: 2px;
  width: 1px;
  height: 18px;
  background-color: #eee;
}
.tab_wrap.outer.new22
  .movi_review_list
  .review_top
  .wrap_sort_right
  .sort_list
  > li {
  margin-left: 15px;
}
.tab_wrap.outer.new22
  .movi_review_list
  .review_top
  .wrap_sort_right
  .sort_list
  > li
  button {
  color: #999;
}
.tab_wrap.outer.new22
  .movi_review_list
  .review_top
  .wrap_sort_right
  .sort_list
  > li.active
  button {
  color: #000;
}
.tab_wrap.outer.new22
  .movi_review_list
  .review_top
  .wrap_sort_right
  .sort_list
  > li
  button.latest:before {
  content: "";
  display: inline-block;
  width: 10px;
  height: 11px;
  font-size: 15px;
  margin: 0;
  margin-right: 4px;
  border: 0;
  vertical-align: -1px;
  background: url(https://www.lottecinema.co.kr/NLCHS/Content/images/icon/icon_sort_latest.svg)
    no-repeat 0 0;
  background-size: 100%;
  opacity: 0.5;
}
.tab_wrap.outer.new22
  .movi_review_list
  .review_top
  .wrap_sort_right
  .sort_list
  > li
  button.empathy:before {
  content: "";
  display: inline-block;
  width: 11px;
  height: 17px;
  font-size: 15px;
  margin: 0;
  margin-right: 4px;
  border: 0;
  vertical-align: -4px;
  background: url(https://www.lottecinema.co.kr/NLCHS/Content/images/icon/icon_sort_empathy.svg)
    no-repeat 0 0;
  background-size: 100%;
  opacity: 0.5;
}
.tab_wrap.outer.new22
  .movi_review_list
  .review_top
  .wrap_sort_right
  .sort_list
  > li.active
  button:before {
  opacity: 1;
}
.tab_wrap.outer.new22 .movi_review_list .review_con_list .review_info {
  margin-bottom: 0;
  line-height: 20px;
}
.tab_wrap.outer.new22 .movi_review_list .review_con_list .top_info {
  position: relative;
  margin-bottom: 6px;
}
.tab_wrap.outer.new22 .movi_review_list .review_con_list .top_info .name {
  display: block;
  font-size: 14px;
  padding-bottom: 8px;
}
.tab_wrap.outer.new22 .movi_review_list .review_con_list .top_info .date_info {
  position: absolute;
  top: 0;
  right: 10px;
  font-family: "Roboto";
  font-size: 14px;
  color: #999;
}
.tab_wrap.outer.new22
  .movi_review_list
  .review_con_list
  .top_info
  .txt_ic_score {
  position: relative;
}
.tab_wrap.outer.new22
  .movi_review_list
  .review_con_list
  .top_info
  .txt_ic_score
  .gradenum {
  font-size: 14px;
  font-family: "Roboto";
  vertical-align: 3px;
}
.tab_wrap.outer.new22
  .movi_review_list
  .review_con_list
  .top_info
  .txt_ic_score
  .stargradebg {
  display: inline-block;
  width: 78px;
  height: 14px;
  background: url(https://www.lottecinema.co.kr/NLCHS/Content/images/icon/ic_star_00.svg)
    no-repeat 0 0;
  background-size: 100%;
}
.tab_wrap.outer.new22
  .movi_review_list
  .review_con_list
  .top_info
  .txt_ic_score
  .stargradebg
  .stargradecolor {
  display: inline-block;
  width: 78px;
  height: 14px;
  background: url(https://www.lottecinema.co.kr/NLCHS/Content/images/icon/ic_star_10.svg)
    no-repeat 0 0;
  background-size: 100%;
}
.tab_wrap.outer.new22
  .movi_review_list
  .review_con_list
  .top_info
  .txt_ic_score
  .stargradebg
  .blindbg {
  overflow: hidden;
  display: inline-block;
}
.tab_wrap.outer.new22
  .movi_review_list
  .review_con_list
  .top_info
  .txt_ic_score
  .stargradebg
  .blindbg.grade10 {
  width: 78px;
}
.tab_wrap.outer.new22
  .movi_review_list
  .review_con_list
  .top_info
  .txt_ic_score
  .stargradebg
  .blindbg.grade9 {
  width: 71px;
}
.tab_wrap.outer.new22
  .movi_review_list
  .review_con_list
  .top_info
  .txt_ic_score
  .stargradebg
  .blindbg.grade8 {
  width: 63px;
}
.tab_wrap.outer.new22
  .movi_review_list
  .review_con_list
  .top_info
  .txt_ic_score
  .stargradebg
  .blindbg.grade7 {
  width: 55px;
}
.tab_wrap.outer.new22
  .movi_review_list
  .review_con_list
  .top_info
  .txt_ic_score
  .stargradebg
  .blindbg.grade6 {
  width: 47px;
}
.tab_wrap.outer.new22
  .movi_review_list
  .review_con_list
  .top_info
  .txt_ic_score
  .stargradebg
  .blindbg.grade5 {
  width: 39px;
}
.tab_wrap.outer.new22
  .movi_review_list
  .review_con_list
  .top_info
  .txt_ic_score
  .stargradebg
  .blindbg.grade4 {
  width: 31px;
}
.tab_wrap.outer.new22
  .movi_review_list
  .review_con_list
  .top_info
  .txt_ic_score
  .stargradebg
  .blindbg.grade3 {
  width: 23px;
}
.tab_wrap.outer.new22
  .movi_review_list
  .review_con_list
  .top_info
  .txt_ic_score
  .stargradebg
  .blindbg.grade2 {
  width: 15px;
}
.tab_wrap.outer.new22
  .movi_review_list
  .review_con_list
  .top_info
  .txt_ic_score
  .stargradebg
  .blindbg.grade1 {
  width: 7px;
}
.tab_wrap.outer.new22 .movi_review_list .review_con_list .btm_info {
  display: flex;
  align-items: center;
  padding-right: 10px;
}
.tab_wrap.outer.new22
  .movi_review_list
  .review_con_list
  .btm_info
  .btn_ic_good {
  margin-left: auto;
  padding: 0;
}
.tab_wrap.outer.new22
  .movi_review_list
  .review_con_list
  .btm_info
  .edit_review
  .btn_review_edit,
.tab_wrap.outer.new22
  .movi_review_list
  .review_con_list
  .btm_info
  .edit_review
  .btn_review_delete {
  position: relative;
  padding-left: 4px;
  font-size: 11px;
  color: #767676;
}
.tab_wrap.outer.new22
  .movi_review_list
  .review_con_list
  .btm_info
  .edit_review
  .btn_review_edit:before {
  content: "";
  display: inline-block;
  width: 15px;
  height: 15px;
  padding-right: 1px;
  vertical-align: -3px;
}
.tab_wrap.outer.new22
  .movi_review_list
  .review_con_list
  .btm_info
  .edit_review
  .btn_review_delete:before {
  content: "";
  display: inline-block;
  width: 15px;
  height: 15px;
  padding-right: 2px;
  vertical-align: -3px;
}
.tab_wrap.outer.new22 .movi_review_list .review_con_list > li:last-child {
  margin-bottom: 15px;
  border-bottom: 1px solid #eee;
}
.tab_wrap.outer.new22
  .movi_review_list
  .review_con_list#review_con_list1
  > li:last-child {
  border-bottom: 0;
}
.star_rate.type5 .starR1 {
  background: url(https://www.lottecinema.co.kr/NLCHS/Content/images/icon/ico_star50_off.png)
    no-repeat 0 0;
  background-size: auto 100%;
  width: 25px;
  height: 50px;
  float: left;
  cursor: pointer;
  padding: 0;
}
.star_rate.type5 .starR2 {
  background: url(https://www.lottecinema.co.kr/NLCHS/Content/images/icon/ico_star50_off.png)
    no-repeat 100% 0;
  background-size: auto 100%;
  width: 25px;
  height: 50px;
  float: left;
  cursor: pointer;
  padding: 0;
}
.star_rate.type5 .starR1.on {
  background: url(https://www.lottecinema.co.kr/NLCHS/Content/images/icon/ico_star50_on.png)
    no-repeat 0 0;
  background-size: auto 100%;
  width: 25px;
  height: 50px;
}
.star_rate.type5 .starR2.on {
  background: url(https://www.lottecinema.co.kr/NLCHS/Content/images/icon/ico_star50_on.png)
    no-repeat 100% 0;
  background-size: auto 100%;
  width: 25px;
  height: 50px;
}
.star_rate.type5 .star {
  margin-right: 0;
}
.star_rate.type5 .star.starR2 {
  margin-right: 10px;
}
.review_con_list > li {
  position: relative;
  padding: 20px 0 15px 68px;
  border-top: 1px solid #eee;
}
.review_con_list > li:first-child {
  border-color: #ccc;
}
.review_con_list > li.my_review {
  background: #f8f8f8;
}
.review_con_list > li .img_info {
  display: block;
  position: absolute;
  top: 15px;
  left: 10px;
  overflow: hidden;
  width: 42px;
  height: 42px;
  border-radius: 50%;
}
.review_con_list > li .img_info img {
  width: 100%;
  border-radius: 50%;
}
.review_con_list .top_info {
  margin-bottom: 12px;
}
.review_con_list .top_info .name_info {
  position: relative;
  padding-right: 11px;
  margin-right: 9px;
  font-size: 12px;
}
.review_con_list .top_info .name_info:before {
  content: "";
  display: block;
  position: absolute;
  top: 3px;
  right: 0;
  width: 1px;
  height: 12px;
  background: #eeeeee;
}
.review_con_list .top_info .txt_ic_score {
  font-size: 14px;
  margin-right: 11px;
}
.review_con_list .txt_cpn2 {
  height: 13px;
  line-height: 13px;
}
.review_con_list .review_info {
  margin-bottom: 11px;
  font-size: 13px;
}
.review_con_list .btm_info .date_info {
  font-size: 11px;
  color: #666;
}
.review_con_list .btn_info {
  position: absolute;
  top: 15px;
  right: 0;
}
.review_con_list .btn_info .btn_box {
  display: none;
}
.review_con_list .btn_info.active .btn_box {
  display: inline-block;
}
.review_con_list .btn_col4 {
  height: 25px !important;
  margin-left: 4px;
  line-height: 25px !important;
  font-size: 11px;
}
.movi_review_box {
  height: 97px;
  margin-bottom: 55px;
  border: 1px solid #ccc;
  box-sizing: border-box;
}
.movi_review_box .star_score_box {
  float: left;
  width: 320px;
  height: 88px;
  background: #f8f8f8;
  text-align: center;
  border-right: 1px solid #ccc;
}
.movi_review_box .star_score_box .score_info {
  display: block;
  margin: 20px 0 7px 0;
  font-size: 13px;
}
.movi_review_box .star_score_box .score_info em {
  font-family: "Roboto", "Noto Sans KR";
  font-size: 28px;
}
.movi_review_box .review_write_box {
  float: left;
  width: 538px;
}
.movi_review_box .review_write_box textarea {
  height: 70px;
  border: none;
  width: 100%;

  box-sizing: border-box;
  resize: none;
  font-size: 14px;
  padding: 13px 18px;
}
.movi_review_box .review_write_box textarea:disabled {
  background: none;
}
.movi_review_box .review_write_box .byte_info {
  display: block;
  margin: 0 10px 10px 0;
  text-align: right;
  font-size: 10px;
  color: #666;
  font-family: "Roboto", "Noto Sans KR";
}
.movi_review_box .review_write_box .byte_info strong {
  color: #000;
  font-weight: bold;
}
.movi_review_box .btn_submit {
  float: left;
  width: 175px;
  height: 97px;
  border: none;
  margin: -1px -1px 0 0;
  padding: 0;
  background: #666;
  color: #fff;
  font-size: 15px;
}
.movi_review_box .btn_col4 {
  background: none;
}
.movi_review_list .review_top {
  overflow: hidden;
  width: 100%;
  margin-bottom: 14px;
}
.movi_review_list .review_top .total_info {
  float: left;
  font-size: 15px;
}
.movi_review_list .review_top .total_info em {
  font-weight: bold;
  font-family: "Roboto", "Noto Sans KR";
}
.movi_review_list .review_top .sort_list {
  overflow: hidden;
  float: right;
}
.btn_ic_good {
  font-size: 12px;
  font-family: "Roboto", "Noto Sans KR";
  line-height: 24px;
  padding: 5px;
  background: none;
  border: none;
}
.btn_ic_good em {
  display: inline-block;
  width: 20px;
  height: 20px;
  vertical-align: top;
  text-indent: -9999px;
  background: url(https://www.lottecinema.co.kr/NLCHS/Content/images/icon/ic_review_good.png)
    no-repeat center center;
}
.btn_ic_good.active em {
  background: url(https://www.lottecinema.co.kr/NLCHS/Content/images/icon/ic_review_good.png)
    no-repeat center center;
}
.tab_wrap.outer.new22
  .movi_review_list
  .review_con_list
  .btm_info
  .btn_ic_good {
  margin-left: auto;
  padding: 0;
}
.tab_wrap.outer {
  padding-top: 80px;
}
.tab_wrap.outer > li > .tab_tit {
  height: 50px;
  line-height: 50px;
  border-bottom: 1px solid #ccc;
  font-size: 15px;
  color: #7f7f7f;
}
.tab_wrap.outer > li > .tab_tit.ty2 {
  line-height: 1.3;
}
.tab_wrap.outer > li.active > .tab_tit {
  border-bottom: 2px solid #111;
  color: #000;
}
/* 팝업 */
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
}
.ic_grade.gr_null {
  background-color: transparent;
}
.ic_grade.gr_12 {
  background-color: transparent;
}
.ic_grade {
  display: inline-block;
  width: 22px;
  height: 22px;
  line-height: 22px;
  border-radius: 50%;
  font-size: 0;
  text-align: center;
  vertical-align: middle;
}
.ic_grade.gr_null:before {
  background: #dd7430 url("@/assets/images/common/grade_15.png") 0 0 no-repeat;
  background-size: 100%;
}
.ic_grade.gr_12:before {
  background: #e9b630 url("@/assets/images/common/grade_12.png") 0 0 no-repeat;
  background-size: 100%;
}
.ic_grade.gr_all:before,
.ic_grade.gr_12:before,
.ic_grade.gr_15:before,
.ic_grade.gr_18:before,
.ic_grade.gr_null:before,
.ic_grade:before {
  content: "";
  display: block;
  width: 22px;
  height: 22px;
  border-radius: 3px;
}
.ic_grade:before {
  margin-left: -1px;
  font-size: 13px;
  font-weight: bold;
  color: #fff;
  white-space: nowrap;
}
.ic_grade.gr_all {
  background-color: transparent;
}
.ic_grade.gr_12 {
  background-color: transparent;
}
.ic_grade.gr_15 {
  background-color: transparent;
}
.ic_grade.gr_18 {
  background-color: transparent;
}
.ic_grade.gr_all:before,
.ic_grade.gr_12:before,
.ic_grade.gr_15:before,
.ic_grade.gr_18:before,
.ic_grade.gr_null:before,
.ic_grade:before {
  content: "";
  display: block;
  width: 22px;
  height: 22px;
  border-radius: 3px;
}
.ic_grade:before {
  margin-left: -1px;
  font-weight: bold;
  color: #fff;
  white-space: nowrap;
}
.ic_grade.gr_all:before {
  font-size: 11px;
}

.ic_grade.gr_12:before {
  background: #e9b630 url("@/assets/images/common/grade_12.png") 0 0 no-repeat;
  background-size: 100%;
}
.ic_grade.gr_15:before {
  background: #e98930 url("@/assets/images/common/grade_15.png") 0 0 no-repeat;
  background-size: 100%;
}
.ic_grade.gr_18:before {
  background: #e93030 url("@/assets/images/common/grade_18.png") 0 0 no-repeat;
  background-size: 100%;
}
.ic_grade.gr_15:before {
  background: #dd7430 url("@/assets/images/common/grade_15.png") 0 0 no-repeat;
  background-size: 100%;
}

.ic_grade.gr_all:before {
  background: #229c56 url("@/assets/images/common/grade_all.png") 0 0 no-repeat;
  background-size: 100%;
}

.ic_grade.gr_12:before {
  background: #e9b630 url("@/assets/images/common/grade_12.png") 0 0 no-repeat;
  background-size: 100%;
}
.btn_col1 {display:inline-block; box-sizing:border-box; border-radius:4px; border:1px solid #FF243E; font-size:14px; color:#ffffff !important; text-align:center; vertical-align:middle; background-color:#FF243E;}
.btn_col1.ty7, .btn_col2.ty7, .btn_col3.ty7, .btn_col4.ty7, .btn_col5.ty7, .btn_col6.ty7, .btn_col7.ty7, .btn_col8.ty7, .btn_col9.ty7, .btn_col10.ty7 {height:52px; line-height:50px; padding:0 24px;}
.btn_col1.rnd, .btn_col2.rnd, .btn_col3.rnd, .btn_col4.rnd, .btn_col5.rnd, .btn_col6.rnd, .btn_col7.rnd, .btn_col8.rnd, .btn_col9.rnd, .btn_col10.rnd {border-radius:52px;}
.movie_detail_aside_menu ul > li.area_reserve .btn_col1 {min-width:140px; font-size:18px;}
</style>
