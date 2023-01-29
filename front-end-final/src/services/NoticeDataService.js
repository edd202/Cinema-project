// QnaDataService.js
// 목적 : Qna 정보를 axios 로 CRUD 를 하는 공통 함수들 정의
import http from "../http-common";

class NoticeDataService {

  // 일반유저용 - 생성/수정/삭제

  // 모든 공지정보 조회 요청 함수
  getAll(ntcTitle, page, size) {
    return http.get(`/movie/notice?ntcTitle=${ntcTitle}&page=${page}&size=${size}`);
  }

  // 공지번호로 조회 요청 함수
  // get 방식 통신 요청 -> @GetMapping("/movie/notice/{ntcNo}"), @PathVariable
  get(ntcNo) {
    return http.get(`/movie/notice/${ntcNo}`);
  }
}

export default new NoticeDataService();
