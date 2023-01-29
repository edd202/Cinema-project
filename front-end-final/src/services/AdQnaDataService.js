import http from "../http-common";
import authHeader from "./userServices/auth-header";

class AdNoticeDataService {

  // 문의제목 검색 요청 함수
  getAll(subject, page, size) {
    return http.get(`/admin/qna?subject=${subject}&page=${page}&size=${size}`, {
      headers: authHeader(),
    });
  }

  // 문의번호로 조회 요청 함수
  get(qid) {
    return http.get(`/admin/qna/${qid}`, { headers: authHeader() });
  }

  // 문의 수정 요청 함수 - 답변용
  update(qid, data) {
    return http.put(`/admin/qna/${qid}`, data, { headers: authHeader() });
  }

  // 문의 삭제 요청 함수
  delete(qid) {
    return http.delete(`/admin/qna/delete/${qid}`, { headers: authHeader() });
  }
}

export default new AdNoticeDataService();
