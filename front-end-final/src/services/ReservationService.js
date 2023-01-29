// DeptDataService.js
// 목적 : Dept 정보를 axios 로 CRUD 를 하는 공통 함수들 정의
import http from "../http-common";

class ReservationService {

    // 모든 상영 지역 정보 조회 요청 함수
    getAll() {
        return http.get(`/movie/movies/Ticketing`); 
    }

    
    // 특정영화관의 영회 조회 요청 함수
    getCinema(cid) {
        return http.get(`/movie/movies/Ticketing/cinema?cid=${cid}`); 
    }

  

    // TODO: 결제완료시 회원용
    getMovieReservationMem(scid,seatId,price,memNo) {
        console.log("보낸 값 scid seatId , price , memNo"+scid+','+seatId+','+price+','+memNo  )
        return http.get(`/movie/movies/Ticketing/Member/reserve?scid=${scid}&seatId=${seatId}&price=${price}&memNo=${memNo}`); 
    }
    // /movies/Ticketing/Member/reserve?scid=3&seatId=0101#0401&price=30000&memNo=유저번호 
     

    // TODO: 결제완료시 비회원용
    getMovieReservationNonmem(scid,seatId,price,tkPhone,tkPw) {
        return http.get(`/movie/movies/Ticketing/NonMember/reserve?scid=${scid}&seatId=${seatId}&price=${price}&tkPhone=${tkPhone}&tkPw=${tkPw}`); 
    }
    

    // 상영관 조회
    // 쿼리스트링 방식 url : ?변수명=값&변수명2=값2....
    getCinemaMovies(mtitle, cid) {
        return http.get(`/movie/movies/Ticketing/theater?title=${mtitle}&cid=${cid}`);
    }

    // 좌석 조회
    getScheduleSeat(thid, thdate, thtime){
        return http.get(`/movie/movies/Ticketing/seat?thid=${thid}&thdate=${thdate}&thtime=${thtime}`)
    }



}

export default new ReservationService();