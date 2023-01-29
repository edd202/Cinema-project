<template>
  <div>
    <!-- 헤더 컴포넌트 -->
    <NavBlack></NavBlack>

    <!-- 대형 포스터 시작 -->
    <div class="img-bg rellax">
        <div class="container">
        <img
          src="@/assets/images/header/AA_1920420.jpg"
          alt="Image"
          class="img-fluid"
        />
        </div>
      </div>
      <!-- 대형 포스터 끝 -->

    <div id="contents" class="contents_movie_home">
      <h2 class="hidden" v-if="false">영화</h2>
      <div class="movi_current_list">
        <h3 class="tit_type1">
          현재 상영작 <strong class="ty2 eng">TOP 5</strong>
        </h3>
        <ul class="movie_list">
          <li class="" v-for="(screening, index) in screeningList" :key="index">
            <!-- v-if="index < 5" : 5개만 출력  -->
            <div class="top_info" v-if="index < 5">
              <span class="poster_info"
                ><img :src="screening.mposter" :alt="screening.mtitle" /><em
                  class="num_info"
                  >{{ screening.rank }}</em
                ></span>
              <div class="over_box">
                <div class="inner" style="margin-top: -33px">
                  <a
                    href="/reservation"
                    class="btn_col3 ty3"
                    >예매하기</a
                  ><router-link
                    :to="'/movie/movieinfo/info/' + screening.mid" :key="$route.fullPath"
                    class="btn_col3 ty3"
                    >상세정보</router-link
                  >
                </div>
                <!-- :to="'/movie/movieinfo/' + currentScreen.mid" :key="$route.fullPath" -->
              </div>
            </div>
            <div class="btm_info" v-if="index < 5">
              <strong class="tit_info">{{ screening.mtitle }}</strong
              ><span class="sub_info1"
                ><span class="rate_info"
                  >예매율 <em>{{ screening.ticketingPer }}%</em></span
                ><span class="star_info">{{
                  screening.audiencesRate
                }}</span></span
              >
            </div>
          </li>
        </ul>

        <router-link
          to="/movie-list/current"
          class="btn_txt_more ty2"
          >더보기</router-link
        >
      </div>
      <div class="movi_pre_list">
        <h3 class="tit_type1">
          상영 예정작 <strong class="ty2 eng">TOP 5</strong>
        </h3>
        <ul class="movie_list">
          <li
            class=""
            v-for="(notScreening, index) in notScreeningList"
            :key="index"
          >
            <div class="top_info" v-if="index < 5">
              <span class="poster_info"
                ><img
                  :src="notScreening.mposter"
                  :alt="notScreening.mtitle" /><em class="num_info"></em
                ></span>
              <div class="over_box">
                <div class="inner" style="margin-top: -33px">
                  <a
                    href="/reservation"
                    class="btn_col3 ty3"
                    >예매하기</a
                  ><router-link
                    :to="'/movie/movieinfo/info/' + notScreening.mid" :key="$route.fullPath" 
                    class="btn_col3 ty3"
                    >상세정보</router-link
                  >
                </div>
              </div>
            </div>
            <div class="btm_info" v-if="index < 5">
              <strong class="tit_info">{{ notScreening.mtitle }}</strong
              ><span class="sub_info1"
                ><span class="rate_info"
                  >예매율 <em>{{ notScreening.ticketingPer }}%</em></span
                ><span class="remain_info"
                  >D-{{ notScreening.dday }}</span
                ></span
              >
            </div>
          </li>
        </ul>

        <router-link
          to="/movie-list/expected"
          class="btn_txt_more ty2"
          >더보기</router-link
        >
      </div>
    </div>
  </div>
</template>

<script>
import NavBlack from "@/components/common/NavBlack.vue";
import MainHomeService from "@/services/MainHomeService.js";

// const screeningList = [
//   {
//     mposter:
//       "https://caching.lottecinema.co.kr//Media/MovieFile/MovieImg/202212/18755_103_1.jpg",
//     mtitle: "아바타 : 물의 길",
//     audiencesRate: 9.5,
//     rank: 1,
//     ticketingPer: 78.9,
//   },
//   {
//     mposter:
//       "https://caching.lottecinema.co.kr//Media/MovieFile/MovieImg/202212/19224_103_1.jpg",
//     mtitle: "영웅",
//     audiencesRate: 9.4,
//     rank: 2,
//     ticketingPer: 13.5,
//   },
//   {
//     mposter:
//       "https://caching.lottecinema.co.kr//Media/MovieFile/MovieImg/202212/19300_103_1.jpg",
//     mtitle: "신비아파트 극장판 차원도깨비와 7개의 세계",
//     audiencesRate: 9.8,
//     rank: 3,
//     ticketingPer: 1.6,
//   },
//   {
//     mposter:
//       "https://caching.lottecinema.co.kr//Media/MovieFile/MovieImg/202211/19162_103_1.jpg",
//     mtitle: "올빼미",
//     audiencesRate: 9.7,
//     rank: 4,
//     ticketingPer: 1.2,
//   },
//   {
//     mposter:
//       "https://caching.lottecinema.co.kr//Media/MovieFile/MovieImg/202211/19256_103_1.jpg",
//     mtitle: "오늘 밤, 세계에서 이 사랑이 사라진다 해도",
//     audiencesRate: 9.2,
//     rank: 5,
//     ticketingPer: 0.8,
//   },
//   {
//     mposter:
//       "https://caching.lottecinema.co.kr//Media/MovieFile/MovieImg/202212/19262_103_1.jpg",
//     mtitle: "핑크퐁 시네마 콘서트 2: 원더스타 콘서트 대작전",
//     audiencesRate: 10.0,
//     rank: 6,
//     ticketingPer: 0.4,
//   },
// ];
// const notScreeningList = [
//   {
//     mposter: "https://caching.lottecinema.co.kr//Media/MovieFile/MovieImg/202212/19330_103_1.jpg",
//     mtitle: "젠틀맨",
//     ticketingPer: 0.8,
//     dday: 6
//   },
//   {
//     mposter: "https://caching.lottecinema.co.kr//Media/MovieFile/MovieImg/202212/19378_103_1.jpg",
//     mtitle: "크레이지 컴페티션",
//     ticketingPer: 0.1,
//     dday: 6
//   },
//   {
//     mposter: "https://caching.lottecinema.co.kr//Media/MovieFile/MovieImg/202212/19374_103_1.jpg",
//     mtitle: "메모리아",
//     ticketingPer: 0,
//     dday: 7
//   },
//   {
//     mposter: "https://caching.lottecinema.co.kr//Media/MovieFile/MovieImg/202212/19375_103_1.jpg",
//     mtitle: "라스트필름",
//     ticketingPer: 0,
//     dday: 7
//   },
//   {
//     mposter: "https://caching.lottecinema.co.kr//Media/MovieFile/MovieImg/202212/19345_103_1.jpg",
//     mtitle: "이태석",
//     ticketingPer: 0,
//     dday: 7
//   },
//   {
//     mposter: "https://caching.lottecinema.co.kr//Media/MovieFile/MovieImg/202212/19345_103_1.jpg",
//     mtitle: "이태석",
//     ticketingPer: 0,
//     dday: 7
//   },
// ];
export default {
  data() {
    return {
      screeningList: [],
      notScreeningList: [],
      currentScreen: null,
      currentIndex: -1,
    };
  },
  components: {
    NavBlack,
  },
  methods: {
    // axios , 모든 영화 정보 조회 요청 함수
    retrieveScreen() {
      // getAll() -> get(dname, page, size)
      MainHomeService.getAll()
        // 성공하면 .then() 결과가 전송됨
        .then((response) => {
          // this.dog = response.data -> const{dog,totalItems}= response.data
          // let(const) {속성명, 속성명2} = 데이터 객체 배열 (모던자바분법: 구조분해할당)
          const{ScreeningList,NotScreeningList}= response.data; // springboot 의 전송된 맵 정보
          this.screeningList = ScreeningList          // 현재상영작
          this.notScreeningList = NotScreeningList   // 상영예정작
          // 디버깅 콘솔에 정보 출력
          console.log(response.data);
        })
        // 실패하면 .catch() 에 에러가 전송됨
        .catch((e) => {
          console.log(e);
        });
    },
  },
  mounted() {
    this.retrieveScreen(); // 화면 로딩시 전체 조회함수 실행
  },
};
</script>

<style>
ul {
  list-style: none;
}
a {
  text-decoration: none;
}
.movi_current_list,
.movi_pre_list {
  position: relative;
  margin-bottom: 37px;
}
.contents_movie_home .movi_pre_list .btn_txt_more,
.contents_movie_home .movi_current_list .btn_txt_more {
  position: absolute;
  top: 8px;
  right: 0;
}
.tit_type1 .eng {
  font-family: "Roboto", "Noto Sans KR";
}
.movie_list {
  overflow: hidden;
  margin-bottom: 50px;
  margin: 0 -5px;
}
.movie_list li {
  float: left;
  position: relative;
  width: 175px;
  margin: 0 5px;
  text-align: center;
}
.movie_list li .top_info {
  overflow: hidden;
  position: relative;
  height: 250px;
  border-radius: 4px;
}
.movie_list li .top_info .poster_info {
  display: block;
  height: 262px;
}
.movie_list li .top_info .poster_info img {
  width: 100%;
}
.movie_list li .top_info .poster_info .num_info {
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
.movie_list li .top_info .poster_info .ic_grade {
  position: absolute;
  top: 10px;
  right: 8px;
}
/* .movie_list li .top_info .over_box {overflow:hidden; position:absolute; width:100%; height:0;} */
/* .movie_list li.active .top_info .over_box {overflow:inherit; position:absolute; z-index:1; top:0; left:0; width:100%; height:100%;} */
/* .movie_list li.active .top_info .over_box:before {content:''; position:absolute; top:0; left:0; width:100%; height:100%; background:#000; opacity:0.7;} */
/* .movie_list li.active .top_info .over_box .inner {position:absolute; z-index:1; top:50%; left:0; right:0; padding:0 18px;} */
/* .movie_list li .top_info .over_box .btn_col3 {display:block; line-height:26px; margin-top:10px; background:transparent; color:#fff !important; border-color:#fff; outline-color: white;} */
/* .movie_list li .top_info .over_box .btn_col3:first-child {margin-top:0} */
/* .movie_list li .top_info .over_box .hall_info_box {position:absolute; top:0; left:0; width:100%; background:red} */
/* .movie_list li .top_info .over_box .btn_end {border-color:#666; color:#666 !important} */
.movie_list li .top_info .over_box {
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.7);
  line-height: 200px;
  position: absolute;
  top: 0;
  left: 0;
  opacity: 0;
  transition: 0.3s;
}
.movie_list li .top_info .over_box .inner {
  position: absolute;
  z-index: 1;
  top: 50%;
  left: 0;
  right: 0;
  padding: 0 18px;
}
.movie_list li .top_info .over_box .btn_col3 {
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
.movie_list li .top_info:hover .over_box,
.movie_list li .top_info:focus .over_box {
  opacity: 1;
}
.movie_list li .btm_info {
  display: block;
  margin-top: 14px;
}
.movie_list li .btm_info .tit_info {
  display: block;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  margin-bottom: 6px;
  font-size: 13px;
}
.movie_list li .btm_info .sub_info1 {
  display: block;
  margin-bottom: 10px;
  font-size: 11px;
}
.movie_list li .btm_info .sub_info1 > span {
  position: relative;
  margin-left: 8px;
  font-family: "Roboto", "Noto Sans KR";
}
.movie_list li .btm_info .sub_info1 > span:before {
  content: "";
  display: inline-block;
  position: absolute;
  top: 2px;
  left: 0;
  width: 1px;
  height: 8px;
  background: #b2b2b2;
}
.movie_list li .btm_info .sub_info1 > span:first-child {
  margin-left: 0;
}
.movie_list li .btm_info .sub_info1 > span:first-child:before {
  display: none;
}
.movie_list li .btm_info .sub_info1 .rate_info em {
  font-weight: bold;
}
.movie_list li .btm_info .sub_info1 .star_info {
  padding-left: 22px;
  font-weight: bold;
  background: url(https://www.lottecinema.co.kr/NLCHS/Content/images/movie/ic_star.png)
    no-repeat 9px 20%;
}
.movie_list li .btm_info .sub_info1 .remain_info {
  padding-left: 10px;
  color: #ed4c6b;
}
.movie_list li .btm_info .sub_info1 .heart_info {
  display: inline-block;
  padding-left: 10px;
}
.movie_list li .btm_info .sub_info1 .heart_info .btn_heart_rnd {
  margin-top: -4px;
}
.movie_list li .btm_info .sub_info2 {
  display: block;
}
.movie_list li .btm_info .sub_info2 > span {
  display: inline-block;
  padding: 4px 0 4px 21px;
  font-family: "Roboto", "Noto Sans KR";
  font-size: 11px;
}
.tit_type1 {
  margin-top: 5px;
  margin-bottom: 12px;
  font-size: 18px;
}
.tit_type1 strong {
  display: block;
  margin-bottom: 8px;
}
.tit_type1 strong.ty2 {
  display: inline;
  margin-bottom: 0;
  color: #ed4c6b;
}
.tit_type1 .eng {
  font-family: "Roboto", "Noto Sans KR";
}
.tit_type1 em {
  display: block;
  font-size: 13px;
  color: #999;
}
</style>
