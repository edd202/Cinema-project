package com.example.project_movie_backEnd.service.adminService;

import com.example.project_movie_backEnd.dto.admin.*;
import com.example.project_movie_backEnd.dto.reservation.CinemaDto;
import com.example.project_movie_backEnd.model.CinemaSite;
import com.example.project_movie_backEnd.model.Member;
import com.example.project_movie_backEnd.model.Theater;
import com.example.project_movie_backEnd.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterService {
    @Autowired
    CinemaSiteRepository cinemaSiteRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    MovieInfoRepository movieInfoRepository;
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    UserRepository userRepository;

    // Master Admin
    // 영화관별 : 일일매출 주간매출 월간매출 일일관림객수 주간관람객수
    // 영화관전체 : 영화관별관람객수 영화관별매출 총합관람객수 총합매출
    // 영화관추가    영화관수정     영화관폐쇄 TODO:기본서비스진행완료
    // 상영관추가    상영관수정     상영관페쇄 TODO:기본서비스진행완료

    // Master Admin 기능 구현

    public List<CinemaSite> getCinemaSite(String cinemaName){
        List<CinemaSite> list = cinemaSiteRepository.findAllByCinemaNameContaining(cinemaName);
        return list;
    }
    public CinemaSite saveCinemaSite(CinemaSite cinemaSite) {
        CinemaSite cinemaSite1 = cinemaSiteRepository.save(cinemaSite);
        return cinemaSite1;
    }

    public boolean removeCinemaSiteById(int cid) {
        if (cinemaSiteRepository.existsById(cid)) { // 영화관이 있는지 확인
            if (theaterRepository.existsByCid(cid)) { // 영화관이 상영관을 가지고 있는지 확인
                List<Theater> list = theaterRepository.findAllByCidContaining(cid); // 영화관이 가진 상영관갯수 확인
                int thid = 0;
                for (int i = 0; i < list.size(); i++) { //상영관 별로 스케쥴이 있는지확인
                    thid = list.get(i).getThid(); //상영관의 thid를 가져옴
                    if (scheduleRepository.existsByThid(thid)) { // 상영관이 스케쥴을 가진지 확인
                        scheduleRepository.delByThid(thid); // 있는 스케쥴 삭제
                        theaterRepository.deleteById(thid); // 스케쥴 삭제후 상영관 삭제
                    } else {
                        theaterRepository.deleteById(thid); // 스케쥴이 없으니 상영관 바로삭제
                    }
                }
                cinemaSiteRepository.deleteById(cid);//영화관삭제
                return true; //삭제가 끝났으니 true 리턴
            } else {
                cinemaSiteRepository.deleteById(cid);//영화관삭제
                return true;
            }
        }
        return false;
    }

    public List<Theater> getTheaterByCid(Integer cid){
        List<Theater> list = theaterRepository.findByCid(cid);
        return list;
    }

    public Theater saveTheater(Theater theater) {
        Theater theater1 = theaterRepository.save(theater);
        return theater1;
    }

    public boolean removeTheaterById(int thid) {
        if (theaterRepository.existsById(thid)) {
            if (scheduleRepository.existsByThid(thid)) {
                scheduleRepository.delByThid(thid);
                theaterRepository.deleteById(thid);
                return true;
            } else {
                theaterRepository.deleteById(thid);
                return true;
            }
        }
        return false;
    }


    public List<CinemaSalesDto> findCinemaSales(String cinemaName){
        List<CinemaSalesDto> list = ticketRepository.findCinemaSales(cinemaName);
        return list;
    }

    public List<CinemaDto> findCinema() {
        List<CinemaDto> list = cinemaSiteRepository.findCinemasite();
        return list;
    }

    public List<userCountDto> findUserCount(){
        List<userCountDto> list = userRepository.userCount();
        return list;
    }

    public List<movieSales> findMovieSales(){
        List<movieSales> list = ticketRepository.findMovieSales();
        return list;
    }

    public List<userCountDto> nonMemberReservasionCount(){
        List<userCountDto> list = ticketRepository.nonMemberReservasionCount();
        return list;
    }

    public List<userCountDto> memberReservasionCount(){
        List<userCountDto> list = ticketRepository.memberReservasionCount();
        return list;
    }

    public List<userCountDto> yearlyReservationCount(){
        List<userCountDto> list = ticketRepository.yearlyReservationCount();
        return list;
    }

    public List<monthlySales> monthlySales(){
        List<monthlySales> list = ticketRepository.monthlySales();
        return list;
    }

    public List<monthlyVistor> monthlyVistor(){
        List<monthlyVistor> list = ticketRepository.monthlyVistor();
        return list;
    }

}
