// QnaDataService.js
// 목적 : Qna 정보를 axios 로 CRUD 를 하는 공통 함수들 정의
import http from "../http-common";

class QnaDataService {
    // 모든 부서정보 조회 요청 함수
    getAll(searchSelect, searchKeyword, page, size) {
        // alert(searchSelect)
        // alert(searchKeyword)
        // get 방식 통신 요청 -> @GetMapping("/api/qna")
        return http.get(`/qna?searchSelect=${searchSelect}&searchKeyword=${searchKeyword}&page=${page}&size=${size}`); 
    }
    // user.id 검색함수
    getByUserUsername(id,page,size){
        return http.get(`/movie/qna?id=${id}&page=${page}&size=${size}`); 
    }

 
    // get 방식 통신 요청 -> @GetMapping("/api/qna/{qno}"), @PathVariable
    get(qid) {
        return http.get(`/movie/qna/${qid}`)
    }

 
    // post 방식 통신 요청 -> @PostMapping("/api/qna"), @RequestBody
    create(data) {
        console.log(data);
        return http.post("/movie/qna", data);
    }

    // 부서정보 수정(update) 요청 함수
    // put 방식 통신 요청 -> @PutMapping("/api/qna/{qno}"), @RequestBody
    update(qid, data) {
        return http.put(`/movie/qna/${qid}`, data);
    }

    // delete 방식 통신 요청 -> @DeleteMapping("/api/qna/deletion/{qno}")
    //                        , @PathVariable  
    delete(qid) {
        return http.delete(`/movie/qna/delete/${qid}`);
    }


    // delete 방식 통신 요청 -> @DeleteMapping("/api/qna/all")
    deleteAll() {
        return http.delete("/qna/all")
    }

}

export default new QnaDataService();