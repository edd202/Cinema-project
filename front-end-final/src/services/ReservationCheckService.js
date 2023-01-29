// DeptDataService.js
// 목적 : Dept 정보를 axios 로 CRUD 를 하는 공통 함수들 정의
import http from "../http-common";

class ReservationCheckService {



    // TODO: 결제완료시 회원용
    getMovieReservationCheckMem(memId) {
        console.log("보낸 값 memId"+memId  )
        return http.get(`/movie/Ticket/Member?memId=${memId}`);
    }
     
    getTicketListMem(memId) {
        console.log("보낸 값 memId"+memId  )
        return http.get(`/movie/TicketList/Member?memId=${memId}`);
    }
    // TODO: 결제완료시 비회원용
    getMovieReservationCheckNonmem(tkPhone,tkPw) {
        return http.get(`/movie/Ticket/NonMember?tkPhone=${tkPhone}&tkPw=${tkPw}`);
    }
    

    // 예약티켓 삭제
    cancelReservationTicket(tkId){
        return http.delete(`/movie/Ticket/revoke/${tkId}`);
    }


}

export default new ReservationCheckService();