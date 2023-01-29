<template>
  <div>
    <div class="tab_con">
      <h4 class="" style="color: #fff">영화정보</h4>
      <div class="movi_tab_info1">
        <div class="left_con">
          <strong class="tit_info">시놉시스</strong>
          <div
            class="mCustomScrollbar _mCS_1 mCS-autoHide"
            data-mcs-theme="minimal-dark"
            style="position: relative; overflow: auto"
          >
            <p class="txt_info">
              <span style="font-family: 'Roboto', 'Noto Sans KR'">{{
                currentScreen.msimpleInfo
              }}</span>
            </p>
          </div>
        </div>
        <div class="right_con">
          <strong class="tit_info">관람 선호도</strong>
          <div class="group_graph">
            <div class="bx_graph01">
              <div class="bg"></div>
              <dl>
                <dt class="mal">남성</dt>
                <dd class="mal">
                  <span
                    class="bar"
                    :style="{ height: userFavorit.mans_per + '%' }"
                  ></span
                  ><strong>{{ userFavorit.mans_per }}<span>%</span></strong>
                </dd>
                <dt class="fem">여성</dt>
                <dd class="fem">
                  <span
                    class="bar"
                    :style="{ height: userFavorit.womans_per + '%' }"
                  ></span
                  ><strong>{{ userFavorit.womans_per }}<span>%</span></strong>
                </dd>
              </dl>
            </div>
            <div class="bx_graph02">
              <dl>
                <dt class="gen10">10대</dt>
                <dd
                  class="gen10 undefined"
                  :style="{ height: userFavorit.age10_per + '%' }"
                >
                  <span class="bar"></span
                  ><strong>{{ userFavorit.age10_per }}<span>%</span></strong>
                </dd>
                <dt class="gen20">20대</dt>
                <dd
                  class="gen20 top"
                  :style="{ height: userFavorit.age20_per + '%' }"
                >
                  <span class="bar"></span
                  ><strong>{{ userFavorit.age20_per }}<span>%</span></strong>
                </dd>
                <dt class="gen30">30대</dt>
                <dd
                  class="gen30 undefined"
                  :style="{ height: userFavorit.age30_per + '%' }"
                >
                  <span class="bar"></span
                  ><strong>{{ userFavorit.age30_per }}<span>%</span></strong>
                </dd>
                <dt class="gen40">40대</dt>
                <dd
                  class="gen40 undefined"
                  :style="{ height: userFavorit.age40up_per + '%' }"
                >
                  <span class="bar"></span
                  ><strong>{{ userFavorit.age40up_per }}<span>%</span></strong>
                </dd>
              </dl>
            </div>
          </div>
        </div>
      </div>
      <div class="movi_tab_info2" id="movie_trailer">
        <h5 class="tit_info">트레일러{{ urlmessage }}</h5>
        <div class="embed-responsive embed-responsive-16by9">
          <iframe
            class="embed-responsive-item"
            :src="
              'https://www.youtube.com/embed/' + movieURL + '?autoplay=1&mute=1'
            "
            frameborder="0"
            allowfullscreen
          ></iframe>
        </div>
      </div>
      <div class="movi_tab_info3" id="movie_poster">
        <h5 class="tit_info">포스터 · 스틸컷</h5>
        <div class="slideImg2">
          <div class="container">
            <ImageSlider :slideImg="slideImg" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ImageSlider from "@/components/carosalSlider/ImageSlider.vue";
import MainHomeService from "@/services/MainHomeService.js";
import initCustom from "@/assets/js/custom";
export default {
  data() {
    return {
      slideImg: [],
      currentScreen: null,
      userFavorit: null,
      message: "",
      urlmessage: "",
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
          // console.log(this.currentScreen.mimage1)
          // console.log(this.currentScreen.mimage2)
          // console.log(this.currentScreen.mimage3)
          // console.log(this.currentScreen.mimage4)
          // console.log(this.currentScreen.mimage5)
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
          if (this.currentScreen.mtrailerLink == null) {
            this.urlmessage = ": 준비중입니다.";
          }
          // 콘솔 로그 출력
          console.log(response.data);
        })
        // 실패하면 .catch() 에러메세지가 리턴됨
        .catch((e) => {
          console.log(e);
        });
    },
    // 개봉일 날짜에 구분자(.) 넣어주기 위한 함수
    //  changeDateString(date){
    //   let year = date.substr(0,4);
    //   let month = date.substr(4,2);
    //   let day = date.substr(6,2);
    //   return year + "." + month + "." + day
    // }
  },
  mounted() {
    // alert(this.$route.params.mid)
    this.getScreen(this.$route.params.mid);
    initCustom();
  },
  components: {
    ImageSlider,
  },
  computed: {
    // youtube 동영상 지원 형식에 맞추기 위한 함수
    movieURL: function () {
      if (this.currentScreen.mtrailerLink == null) {
        return "";
      }
      return this.currentScreen.mtrailerLink.split("/")[3];
    },
  },
};
</script>

<style scoped>
iframe {
  height: 533px;
  width: 948px;
  margin-top: 30px;
  margin-bottom: 30px;
}
</style>
