// QnaDataService.js
// 목적 : Qna 정보를 axios 로 CRUD 를 하는 공통 함수들 정의
import http from "../http-common";

class MainHomeDataService {
  // 모든 영화정보 조회
  getAll() {
    return http.get(`/movie/movies/main`);
  }

  // 영화 제목으로 영화상세 정보 요청 함수
  // get 방식 통신 요청 -> @GetMapping("/api/qna/{qno}"), @PathVariable
  get(mid) {
    return http.get(`movie/movieInfo/${mid}`);
  }

  // 현재상영중인 영화 조회 함수
  getScreening() {
    return http.get(`/movie/movies/screening`);
  }

  // 상영예정작 조회 함수
  getScheduled() {
    return http.get(`/movie/movies/scheduled`);
  }   

  // 댓글불러오기(최신순)
  getReviewRecent(mid,page,size) {
    return http.get(`movie/movieInfo/comment/order/insertTime?mid=${mid}&page=${page}&size=${size}`);
  }
//   getAll(searchSelect, searchKeyword, page, size) {
//     return http.get(
//       `/qna?searchSelect=${searchSelect}&searchKeyword=${searchKeyword}&page=${page}&size=${size}`
//     );
//   }

  // 댓글불러오기(좋아요순)
  getReviewLike(mid,page,size) {
    return http.get(`movie/movieInfo/comment/order/like?mid=${mid}&page=${page}&size=${size}`);
  }

  // 댓글,별점 생성(insert) 요청 함수
  // post 방식 통신 요청 -> @PostMapping("/movie/qna"), @RequestBody
  saveAudienceReview(data) {
    console.log(data);
    return http.post("/movie/movieInfo/comment", data);
  }

  // 댓글 좋아요 함수
  commentLike(audno,memId){
    return http.put(`/movie/movieInfo/comment/like?audno=${audno}&memId=${memId}`);
  }

  // 정렬(예매순) 함수
  ticketingGet(page,size){
    return http.get(`movie/movies/ticketing?page=${page}&size=${size}`);
  }
  
  // 정렬(평점순) 함수
  gradeGet(page,size){
    return http.get(`movie/movies/rating?page=${page}&size=${size}`);
  }

  // 정렬(리뷰많은순) 함수
  reviewGet(page,size){
    return http.get(`movie/movies/reviewCount?page=${page}&size=${size}`);
  }

  // 상영예정작 정렬(개봉일순) 함수
  ddayGet(page,size){
    return http.get(`movie/movies/notScreening/dday?page=${page}&size=${size}`);
  }

  // 상영예정작 정렬(예매순) 함수
  ticketingNotScreen(page,size){
    return http.get(`movie/movies/notScreening/ticket?page=${page}&size=${size}`);
  }
}

export default new MainHomeDataService();
