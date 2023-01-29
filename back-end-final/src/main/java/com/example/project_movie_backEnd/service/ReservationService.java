package com.example.project_movie_backEnd.service;

import com.example.project_movie_backEnd.dto.reservation.CinemaDto;
import com.example.project_movie_backEnd.dto.reservation.CinemaTheaterDto;
import com.example.project_movie_backEnd.dto.reservation.TheaterDto;
import com.example.project_movie_backEnd.dto.reservation.TheaterScheduleDto;
import com.example.project_movie_backEnd.model.*;
import com.example.project_movie_backEnd.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    CinemaSiteRepository cinemaSiteRepository;
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    MovieInfoRepository movieInfoRepository;
    @Autowired
    MemberInfoRepository memberInfoRepository;

    public List<CinemaDto> findCinema() {
        List<CinemaDto> list = cinemaSiteRepository.findCinemasite();
        return list;
    }

    public List<TheaterDto> findMoviebyCinema(int cid) {
        List<TheaterDto> list = theaterRepository.findMoviebyCinema(cid);
        return list;
    }

    public List<TheaterScheduleDto> findMovieSchedulebyMovieName(String title, int cid) {
        List<TheaterScheduleDto> list = theaterRepository.findMovieSchedulebyMovieName(title, cid);
        return list;
    }

    public Page<Schedule> findMovieSeatbyMovieSchedule(int thid, String thdate, String thtime, Pageable pageable) {
        Page<Schedule> list = scheduleRepository.findMovieSeatbyMovieSchedule(thid, thdate, thtime, pageable);
        return list;
    }

    public List<Schedule> findSeatReserved(int scid) {
        List<Schedule> list = scheduleRepository.findSeatReserved(scid);
        return list;
    }

//    public int seatReserve(String seatColumn, String seatNum){
//        int seat = scheduleRepository.seatReserve(seatColumn,seatNum);
//        return seat;
//    }

    public List<CinemaTheaterDto> findMovieLocatebyTheaterId(int thid) {
        List<CinemaTheaterDto> list = theaterRepository.findMovieLocatebyTheaterId(thid);
        return list;
    }

    public List<Movie> findByTitle(String title) {
        List<Movie> list = movieRepository.findByTitle(title);
        return list;
    }

    public Optional<MovieInfo> findByMid(int mid) {
        Optional<MovieInfo> list = movieInfoRepository.findById(mid);
        return list;
    }

    public Optional<MemberInfo> findBymemNo(int memNo) {
        Optional<MemberInfo> optional = memberInfoRepository.findById(memNo);
        return optional;
    }

    public MovieInfo saveMovieInfo(MovieInfo movieInfo) {
        MovieInfo movieInfo1 = movieInfoRepository.save(movieInfo);
        return movieInfo1;
    }

    public Schedule save(Schedule schedule) {
        Schedule schedule1 = scheduleRepository.save(schedule);
        return schedule1;
    }

    public Ticket saveTicket(Ticket ticket) {
        Ticket ticket1 = ticketRepository.save(ticket);
        return ticket;
    }

    public MemberInfo saveMemInfo(MemberInfo memberInfo) {
        MemberInfo memberInfo1 = memberInfoRepository.save(memberInfo);
        return memberInfo1;
    }

    public Page<Ticket> findNonMemberTicket(String tkPhone, String tkPw, Pageable pageable) {
        Page<Ticket> list = ticketRepository.findNonMemberTicket(tkPhone, tkPw, pageable);
        return list;
    }

    public Page<Ticket> findMemberTicket(String memId, Pageable pageable) {
        Page<Ticket> list = ticketRepository.findMemberTicket(memId, pageable);
        return list;
    }

    public boolean revokeReservation(int tkId) {
        if (ticketRepository.existsById(tkId)) {
            return true;
        }
        return false;
    }

    public boolean ticketCancle(int tkId){
        try {
            ticketRepository.deleteById(tkId);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Optional<Ticket> findBytkId(int tkId){
        Optional<Ticket> list = ticketRepository.findById(tkId);
        return list;
    }

    public List<Schedule> findMovieRevokingData(int thid,String mtitle, String thdate, String thtime) {
        List<Schedule> list = scheduleRepository.findMovieRevokingData(thid, mtitle, thdate, thtime);
        return list;
    }

    public List<CinemaSite> findCinemaId(String cinemaName){
        List<CinemaSite> list = cinemaSiteRepository.findAllByCinemaNameContaining(cinemaName);
        return list;
    }

    public List<Theater> findThid(int cid, String thname){
        List<Theater> list = theaterRepository.findThid(cid,thname);
        return list;
    }

    //    티켓 리스트 함수
    public List<Ticket> findMemberTicketList(Integer memId) {
        List<Ticket> list = ticketRepository.findAllByTkMemId(memId);
        return list;
    }


}
