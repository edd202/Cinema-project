<template>
  <div>
    <!-- 상단 메뉴  -->
    <NavBlack></NavBlack>
    <!-- 대제목 시작 -->
    <div class="cc_title_wrap">
      <div class="cc_title_top">
        <h2 class="cc_tit">전체 이벤트</h2>
      </div>
    </div>
    <!-- 대제목 끝 -->

    <!-- 상세 내용 -->
    <div class="cc_title_wrap">
      <div class="cc_title_top">
        <h5>영화</h5>
        <router-link to="/" style="font-size: 11px">더보기 ></router-link>
      </div>
    </div>

    <div class="cc_title_wrap">
      <div class="cc_title_top2">
        <ul>
          <li @mouseover="changeImg1">
            <router-link :to="`/event-detail/${this.imgNum}`">
              <img
                :src="'http://'+currentHost+'/api/movie/event/img/1'"
                width="300px"
                alt=""
              />
              <h6>2023.01.04 ~ 2023.01.10</h6>
            </router-link>
          </li>
          <li @mouseover="changeImg2">
            <router-link :to="`/event-detail/${imgNum}`">
              <img
                :src="'http://'+currentHost+'/api/movie/event/img/2'"
                width="300px"
                alt=""
              />
              <h6>2023.01.04 ~ 2023.01.10</h6>
            </router-link>
          </li>
          <li @mouseover="changeImg3">
            <!-- TODO: 1) imgNum 추가 -->
            <router-link :to="`/event-detail/${imgNum}`">
              <!-- <router-link to="/event-detail/" @mouseover="changeImg3"> -->
              <img
                :src="'http://'+currentHost+'/api/movie/event/img/3'"
                width="300px"
                alt=""
              />
              <h6>2023.01.04 ~ 2023.01.10</h6>
            </router-link>
          </li>
        </ul>
      </div>
    </div>

    <div class="cc_title_wrap">
      <div class="cc_title_top">
        <h5>시사회/무대인사</h5>
      </div>
    </div>

    <div class="cc_title_wrap">
      <div class="cc_title_top2">
        <ul>
          <li>
                <router-link to="/event-detail2">
                <img src="https://caching.lottecinema.co.kr//Media/Event/4c1ee91aa243496ab82410c930f3cdf7.jpg" alt="<해시태그 시그네> GV"></router-link>
              </li>
              <li>
                <router-link to="/event-detail3">
                  <img src="https://caching.lottecinema.co.kr//Media/Event/b3737c2ae7e5465e8cac1d5a3b77bf1f.jpg" alt="<오늘 밤, 세계에서> 앵콜 상영회"></router-link>
              </li>
              <li>
                <router-link to="/event-detail4">
                  <img src="https://caching.lottecinema.co.kr//Media/Event/2a8c891b0b0d4d9796afb8c3360d6687.jpg" alt="<장화신은 고양이> 무대인사"></router-link>
              </li>
        </ul>
      </div>
    </div>
    <!-- 상세 내용 -->
  </div>
</template>

<script>
import NavBlack from "@/components/common/NavBlack.vue";
import initCustom from "../assets/js/custom";
import accTable from "../assets/js/accordion";

export default {
  components: {
    NavBlack,
  },
  data() {
    return {
      imgNum: 3,
      currentHost: "",
    };
  },
  methods: {
    changeImg1() {
      this.imgNum = 1;
    },
    changeImg2() {
      this.imgNum = 2;
    },
    changeImg3() {
      this.imgNum = 3;
    },
    changeImg4() {
      this.imgNum = 4;
    },
    changeImg5() {
      this.imgNum = 5;
    },
    changeImg6() {
      this.imgNum = 6;
    },
  },
  mounted() {
    initCustom();
    accTable();
    this.currentHost = window.location.host;
  },
};
</script>

<style scoped>
/* 포괄 css */
li {
  float: left;
  margin-right: 15px;
}
img {
  margin-top: -50px;
  border-radius: 5px;
  width: 280px;
}
h5 {
  font-size: 18px;
  color: black;
  width: 920px;
  float: left;
}
h6 {
  margin-left: 20px;
  margin-top: 10px;
  font-size: 12px;
}
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
  height: 40px;
  border-bottom: 1px solid #eee;
}
.cc_title_top2 {
  width: 980px;
  margin: auto;
  height: 320px;
  border-bottom: 1px solid #eee;
  padding-top: 10px;
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

/* 공지사항 검색창 css */
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
</style>
