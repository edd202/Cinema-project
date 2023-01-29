<template>
  <div>
    <!-- AppSlider.vue : slider(carosal) 통신으로 위한 컴포넌트 -->
    <!-- 인기영화 carosal 시작 -->
    <div class="section section-3" data-aos="fade-up" data-aos-delay="100">
      <!-- carosal(인기영화) 소개 문구, 좌우 버튼 -->
      <!-- 이부분 삭제시 slide 실행 안됨 -->
      <div class="container" style="z-index: -1;">
        <div class="row align-items-center justify-content-between mb-5" style="z-index: -1;">
          <!-- 소개 문구 시작 -->
          <div class="col-lg-5" data-aos="fade-up"></div>
          <!-- 소개 문구 끝 -->
          <!-- 좌우 버튼 시작 -->
          <div
            class="col-lg-5 text-sm-end"
            data-aos="fade-up"
            data-aos-delay="100"
            style="z-index: -1;"
          >
            <div id="destination-controls">
              <span class="" data-controls="prev" >
                <span class="" style="z-index: -1;"></span>
              </span>
              <span class="" data-controls="next" >
                <span class="" style="z-index: -1;"></span>
              </span>
            </div>
          </div>
          <!-- 좌우 버튼 끝 -->
        </div>
      </div>
      <!-- 소개문구, 좌우버튼 끝 -->

      <!-- 인기 영화 리스트 시작 -->
      <div class="col-lg-9 d-lg-inline-block destination-slider-wrap">
        <div class="destination-slider">
          <!-- <div
            class="destination"
            v-for="(slide, index) in screeningList"
            :key="index"
          >
            <div class="thumb" v-on:mouseover="mouseover">
              <img :src="slide.img" alt="Image" class="img-slide" />
              <div class="price">rank</div>
            </div>
            <div class="mt-4">
              <h3>
                <a href="#"> {{ slide.text }} </a>
              </h3>
              <span class="meta">예매율 | 별점</span>
            </div>
          </div> -->
          <div
            class="destination"
            v-for="(screening) in screeningList"
            :key="screening.mid"
          >
            <div class="top_info">
              <span class="poster_info">
                <img
                  :src="screening.mposter"
                  :alt="screening.mtitle"
                  class="img-slide"
                />
                <em class="rank">{{ screening.rank }}</em> </span
              >
              <div class="over_box">
                <div class="inner" style="margin-top: -33px">
                  <nav>
                    <a href="/reservation" class="btn_col3 ty3">예매하기</a
                    ><router-link
                      :to="'/movie/movieinfo/info/' + screening.mid"
                      :key="$route.path"
                      class="btn_col3 ty3"
                      >상세정보</router-link
                    >
                  </nav>
                </div>
              </div>
            </div>
            <div class="btm_info">
              <strong class="tit_info">{{ screening.mtitle }}</strong
              ><span class="sub_info1"
                ><span class="rate_info"
                  >예매율&nbsp;<em>{{ screening.ticketingPer }}%</em></span
                ><span class="star_info" style="cursor: pointer">{{
                  screening.audiencesRate
                }}</span
                ><span class="heart_info"
                  ><button type="button" class="btn_heart_rnd" :class="{ active: isActive }" @click="likeMovie()"></button></span
              ></span>
            </div>
          </div>
        </div>
      </div>
      <!-- 인기 영화 리스트 끝 -->
    </div>
    <!-- 인기영화 carosal 끝 -->
  </div>
</template>

<script>
import initCustom from "../../assets/js/custom";

export default {
  name: "AppSlider",
  data() {
    return {
      // 로그인 필요 서비스 알림 팝업
      loginPopup:false,
      isActive: false, // 하트 버튼 토글
    }
  },
  props: {
    screeningList: Array,
  },
  methods: {
    likeMovie(){
      // this.isActive = !this.isActive;
      // 로그인 체크
    console.log("회원로그인여부:"+this.loggedIn+"비회원로그인여부:"+this.loggedIn2)
    // 회원&비회원 로그인 여부로 함수 실행
    if(!this.loggedIn&&!this.loggedIn2){
      alert("로그인이 필요한 서비스 입니다.")
      this.$router.push("/login");
    }else{
      // this.isActive = !this.isActive;
      alert("관심 영화에 추가되었습니다")
      }
    },
  },
  mounted() {
    initCustom();
  },
  computed :{
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
a {
  text-decoration: none;
}
.destination-slider-wrap{
  margin: 0 auto;
  width: 1000px;
}
.destination-slider .destination {
  color: #fff;
  text-align: center;
  z-index: 100;
  width: 180px;
  margin: 0 7px;
  float: left;
}
.destination-slider .destination .top_info {
  overflow: hidden;
  position: relative;
  height: 100%;
  border-radius: 4px;
}
.destination-slider .destination .top_info .poster_info {
  display: block;
  height: 100%;
}
.destination-slider .destination .top_info .poster_info img {
  width: 180px;

}
.destination-slider .destination .top_info .poster_info .rank {
  display: block;
  position: absolute;
  left: 0;
  right: 0;
  bottom: 0;
  padding-left: 10px;
  color: #fff;
  font-weight: bold;
  font-size: 26px;
  font-style: italic;
  font-family: "Roboto", "Noto Sans KR";
  text-align: left;
  background: -moz-linear-gradient(
    top,
    rgba(0, 0, 0, 0) 0%,
    rgba(0, 0, 0, 0.15) 40%,
    rgba(0, 0, 0, 0.5) 100%
  );
  background: -webkit-linear-gradient(
    top,
    rgba(0, 0, 0, 0) 0%,
    rgba(0, 0, 0, 0.15) 40%,
    rgba(0, 0, 0, 0.5) 100%
  );
  background: linear-gradient(
    to bottom,
    rgba(0, 0, 0, 0) 0%,
    rgba(0, 0, 0, 0.15) 40%,
    rgba(0, 0, 0, 0.5) 100%
  );
  filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#00000000', endColorstr='#80000000',GradientType=0 );
}
.destination-slider .destination .top_info .poster_info .ic_grade {
  position: absolute;
  top: 10px;
  right: 8px;
}
.destination-slider .destination .top_info .over_box {
  width: 180px;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.7);
  line-height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  opacity: 0;
  transition: 0.3s;
}
.destination-slider .destination .top_info .over_box .inner {
  position: absolute;
  z-index: 1;
  top: 50%;
  left: 0;
  right: 0;
  padding: 0 18px;
}
.destination-slider .destination .top_info .over_box .btn_col3 {
  display: block;
  line-height: 26px;
  margin-top: 10px;
  background: transparent;
  color: #fff !important;
  border-color: #fff;
  outline-color: white;
  border: 1px solid;
  border-radius: 5px;
}
.destination-slider .destination .top_info:hover .over_box,
.destination-slider .destination .top_info:focus .over_box {
  opacity: 1;
}
.destination-slider .destination .btm_info {
  display: block;
  margin-top: 10px;
}
.destination-slider .destination .btm_info .tit_info {
  display: block;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  margin-bottom: 6px;
  font-size: 13px;
}
.destination-slider .destination .btm_info .sub_info1 {
  display: block;
  margin-bottom: 10px;
  font-size: 11px;
}
.destination-slider .destination .btm_info .sub_info1 > span {
  position: relative;
  margin-left: 8px;
  font-family: "Roboto", "Noto Sans KR";
}
.destination-slider .destination .btm_info .sub_info1 > span:before {
  content: "";
  display: inline-block;
  position: absolute;
  top: 2px;
  left: 0;
  width: 1px;
  height: 8px;
  background: #b2b2b2;
}
.destination-slider .destination .btm_info .sub_info1 > span:first-child {
  margin-left: 0;
}
.destination-slider
  .destination
  .btm_info
  .sub_info1
  > span:first-child:before {
  display: none;
}
.destination-slider .destination .btm_info .sub_info1 .rate_info em {
  font-weight: bold;
}
.destination-slider .destination .btm_info .sub_info1 .star_info {
  padding-left: 22px;
  font-weight: bold;
  background: url(https://www.lottecinema.co.kr/NLCHS/Content/images/movie/ic_star.png)
    no-repeat 9px 20%;
}
.destination-slider .destination .btm_info .sub_info1 .remain_info {
  padding-left: 10px;
  color: #ed4c6b;
}
.btn_heart_rnd {
  width: 16px;
  height: 16px;
  background-image: url(https://www.lottecinema.co.kr/NLCHS/Content/images/icon/heart_rnd_16_off.png);
  background-color: transparent;
  border: none;
}
.btn_heart_rnd.active {
  width: 16px;
  height: 16px;
  background-image: url("@/assets/images/icon/heart_rnd_on.png");
}
.destination-slider .destination .btm_info .sub_info1 .heart_info {
  display: inline-block;
  padding-left: 10px;
}
.destination-slider
  .destination
  .btm_info
  .sub_info1
  .heart_info
  .btn_heart_rnd {
  margin-top: -3px;
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
    border-top: 1px solid #DDD;
    text-align: center;
    background-color: #F8F8F8;
}
.layer_wrap .layer_footer.ty2 ul li {
    width: 100%;
}
.layer_wrap .layer_footer ul li {
    position: relative;
    float: left;
    width: 50%;
}
.layer_wrap .layer_footer ul li a, .layer_wrap .layer_footer ul li button {
    display: block;
    width: 100%;
    height: 50px;
    line-height: 50px;
    border: none;
    font-size: 16px;
    background-color: #F8F8F8;
}
#destination-controls{
  z-index: -1;
}
</style>
