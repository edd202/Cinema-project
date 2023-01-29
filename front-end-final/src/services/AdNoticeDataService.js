import http from "../http-common";
import authHeader from "./userServices/auth-header";

class AdNoticeDataService {

  // 공지제목 검색 요청 함수
  getAll(ntcTitle, page, size) {
    return http.get(`/admin/notice?ntcTitle=${ntcTitle}&page=${page}&size=${size}`, {
      headers: authHeader(),
    });
  }

  // 공지번호로 조회 요청 함수
  get(ntcNo) {
    return http.get(`/admin/notice/${ntcNo}`, { headers: authHeader() });
  }

  // 공지 추가 요청 함수
  create(data) {
    console.log(data);
    return http.post("/admin/notice", data, { headers: authHeader() });
  }

  // 공지 수정 요청 함수
  update(ntcNo, data) {
    return http.put(`/admin/notice/${ntcNo}`, data, { headers: authHeader() });
  }

  // 공지 삭제 요청 함수
  delete(ntcNo) {
    return http.delete(`/admin/notice/delete/${ntcNo}`, { headers: authHeader() });
  }
}

export default new AdNoticeDataService();
