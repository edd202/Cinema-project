<template>
  <div>
    <ul class="list_lnk" id="movietype1">
      <li class="" :class="{ active: ticketing }"><button  @click="ticketingBtn();">예매순</button> |&nbsp;</li>
      <li class="" :class="{ active: grade }"><button   @click="gradeBtn();"> 평점순</button> |&nbsp;</li>
      <li class="" :class="{ active: review }"><button  @click="reviewBtn();"> 관람평 많은순</button></li>
    </ul>  
    <ul class="movie_list type2">
      <li
        class="screen_add_box"
        v-for="(screening, index) in movies"
        :key="index"
      >
        <div class="top_info">
          <span class="poster_info"
            ><img :src="screening.mposterUri" :alt="screening.mtitle" /><em
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
                :to="'/movie/movieinfo/info/' + screening.mid" 
                :key="$route.fullPath"
                class="btn_col3 ty3"
                >상세정보</router-link
              >
            </div>
          </div>
        </div>
        <div class="btm_info">
          <strong class="tit_info">{{ screening.mtitle }}</strong
          ><span class="sub_info1"
            ><span class="rate_info"
              >예매율 <em>{{ screening.ticketingPers }}%</em></span
            ><span class="star_info">{{ screening.rate }}</span></span
          >
        </div>
      </li>
    </ul>
    <b-pagination v-model="page" pills :total-rows="count" @change="handlePageChange" align="center" :per-page="size"></b-pagination>
  </div>
</template>

<script>
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
export default {
  data() {
    return {
      movies: [],
      currentScreen: null,
      currentIndex: -1,
      ticketing: true,  //정렬 소메뉴1 : 예매순
      grade: false,     //정렬 소메뉴2 : 평점순
      review: false,    //정렬 소메뉴3 : 리뷰순
      page: 1,
      size: 8,
      count: 0,
    };
  },
  methods: {
    ticketingGet() {
      // 정렬(예매순) 함수
      MainHomeService.ticketingGet(this.page-1, this.size)
        // 성공하면 .then() 결과가 리턴됨
        .then((response) => {
          // springboot 결과를 리턴함(부서 객체)
          const{movies, totalItems}= response.data; // springboot 의 전송된 맵 정보
          this.movies = movies
          this.count = totalItems;    
          // 콘솔 로그 출력
          console.log(response.data);
        })
        // 실패하면 .catch() 에러메세지가 리턴됨
        .catch((e) => {
          console.log(e);
        });
    },
    gradeGet() {
      // 정렬(평점순) 함수
      MainHomeService.gradeGet(this.page-1, this.size)
        // 성공하면 .then() 결과가 리턴됨
        .then((response) => {
          // springboot 결과를 리턴함(부서 객체)
          const{movies, totalItems}= response.data; // springboot 의 전송된 맵 정보
          this.movies = movies
          this.count = totalItems;    
          // 콘솔 로그 출력
          console.log(response.data);
        })
        // 실패하면 .catch() 에러메세지가 리턴됨
        .catch((e) => {
          console.log(e);
        });
    },
    reviewGet() {
      // 정렬(리뷰많은순) 함수
      MainHomeService.reviewGet(this.page-1, this.size)
        // 성공하면 .then() 결과가 리턴됨
        .then((response) => {
          // springboot 결과를 리턴함(부서 객체)
          const{movies, totalItems}= response.data; // springboot 의 전송된 맵 정보
          this.movies = movies
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
    ticketingBtn(){
      this.ticketing= true;
      this.grade= false;     
      this.review= false;    
      this.ticketingGet();
    },
    // 별점순 버튼
    gradeBtn(){
      this.ticketing= false;
      this.grade= true;     
      this.review= false;
      this.gradeGet();    
    },
    // 관람평순 버튼
    reviewBtn(){
      this.ticketing= false;
      this.grade= false;     
      this.review= true;
      this.reviewGet();    
    },
    // 페이지 번호 변경시 실행되는 함수(재조회)
    handlePageChange(value) {
      this.page = value; // 매개변수값으로 현재페이지 변경
      // 재조회 함수 호출
      if(this.ticketing==true){
            this.ticketingGet();
          }else if(this.grade==true){
            this.gradeGet();
          }else{
            this.reviewGet();
          }
    },
  },
  mounted() {
    this.ticketingGet(); // 화면 로딩시 전체 조회함수 실행
  },
};
</script>

<style></style>
