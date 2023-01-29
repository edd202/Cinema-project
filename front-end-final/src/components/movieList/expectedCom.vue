<template>
  <div>
    <ul class="list_lnk" id="movietype2" style="display: block">
      <li class="" :class="{ active: dday }">
        <button @click="ddayBtn()">개봉일순</button> |&nbsp;
      </li>
      <li class="" :class="{ active: ticketing }">
        <button @click="ticketingBtn()">예매순</button>
      </li>
    </ul>
    <ul class="movie_list type2">
      <li
        class="screen_add_box"
        v-for="(notScreening, index) in movies.slice().reverse()"
        :key="index"
      >
        <div class="top_info">
          <span class="poster_info"
            ><img :src="notScreening.mposterUri" :alt="notScreening.mtitle" /><em
              class="num_info"
            ></em
            ></span>
          <div class="over_box">
            <div class="inner" style="margin-top: -33px">
              <a href="/reservation" class="btn_col3 ty3">예매하기</a
              ><router-link
                :to="'/movie/movieinfo/info/' + notScreening.mid"
                :key="$route.fullPath"
                class="btn_col3 ty3"
                >상세정보</router-link
              >
            </div>
          </div>
        </div>
        <div class="btm_info">
          <strong class="tit_info">{{ notScreening.mtitle }}</strong
          ><span class="sub_info1"
            ><span class="rate_info"
              >예매율 <em>{{ notScreening.ticketingPers }}%</em></span
            ><span class="remain_info">{{ changeDateString(notScreening.mreleaseDate) }} 개봉</span></span
          >
        </div>
      </li>
    </ul>
    <b-pagination v-model="page" pills :total-rows="count" @change="handlePageChange" align="center" :per-page="size"></b-pagination>
  </div>
</template>

<script>
import MainHomeService from "@/services/MainHomeService.js";
// const notScreeningList = [
//   {
//     mposter: "https://caching.lottecinema.co.kr//Media/MovieFile/MovieImg/202212/19330_103_1.jpg",
//     mtitle: "젠틀맨",
//     ticketingPer: 0.8,
//     Dday: 6
//   },
//   {
//     mposter: "https://caching.lottecinema.co.kr//Media/MovieFile/MovieImg/202212/19378_103_1.jpg",
//     mtitle: "크레이지 컴페티션",
//     ticketingPer: 0.1,
//     Dday: 6
//   },
//   {
//     mposter: "https://caching.lottecinema.co.kr//Media/MovieFile/MovieImg/202212/19374_103_1.jpg",
//     mtitle: "메모리아",
//     ticketingPer: 0,
//     Dday: 7
//   },
//   {
//     mposter: "https://caching.lottecinema.co.kr//Media/MovieFile/MovieImg/202212/19375_103_1.jpg",
//     mtitle: "라스트필름",
//     ticketingPer: 0,
//     Dday: 7
//   },
//   {
//     mposter: "https://caching.lottecinema.co.kr//Media/MovieFile/MovieImg/202212/19345_103_1.jpg",
//     mtitle: "이태석",
//     ticketingPer: 0,
//     Dday: 7
//   },
// ]
export default {
  data() {
    return {
      movies: [],
      currentScreen: null,
      currentIndex: -1,
      dday: false, //정렬 소메뉴1 : 개봉일순
      ticketing: true, //정렬 소메뉴2 : 예매순
      page: 1,
      size: 8,
      count: 0,
    };
  },
  methods: {
    ddayGet() {
      // axios 공통함수 호출
      MainHomeService.ddayGet(this.page-1, this.size)
        // 성공하면 .then() 결과가 리턴됨
        .then((response) => {
          // springboot 결과를 리턴함(부서 객체)
          const { movies, totalItems } = response.data; // springboot 의 전송된 맵 정보
          this.movies = movies;
          this.count = totalItems;
          // 콘솔 로그 출력
          console.log(response.data);
        })
        // 실패하면 .catch() 에러메세지가 리턴됨
        .catch((e) => {
          console.log(e);
        });
    },
    ticketingNotScreen() {
      // axios 공통함수 호출
      MainHomeService.ticketingNotScreen(this.page-1, this.size)
        // 성공하면 .then() 결과가 리턴됨
        .then((response) => {
          // springboot 결과를 리턴함(부서 객체)
          const { movies, totalItems } = response.data; // springboot 의 전송된 맵 정보
          this.movies = movies;
          this.count = totalItems;
          // 콘솔 로그 출력
          console.log(response.data);
        })
        // 실패하면 .catch() 에러메세지가 리턴됨
        .catch((e) => {
          console.log(e);
        });
    },
    // 예매순 버튼
    ddayBtn() {
      this.dday = true;
      this.ticketing = false;
      this.ddayGet();
    },
    // 별점순 버튼
    ticketingBtn() {
      this.dday = false;
      this.ticketing = true;
      this.ticketingNotScreen();
    },
    // 개봉일 날짜에 구분자(.) 넣어주기 위한 함수
    changeDateString(date) {
      let year = date.substr(0, 4);
      let month = date.substr(4, 2);
      let day = date.substr(6, 2);
      return year + "." + month + "." + day;
    },
    // 페이지 번호 변경시 실행되는 함수(재조회)
    handlePageChange(value) {
      this.page = value; // 매개변수값으로 현재페이지 변경
      // 재조회 함수 호출
      if(this.ticketing==true){
            this.ticketingNotScreen();
          }else{
            this.ddayGet();
          }
    },
  },
  mounted() {
    this.ticketingNotScreen(); // 화면 로딩시 전체 조회함수 실행
  },
};
</script>

<style></style>
