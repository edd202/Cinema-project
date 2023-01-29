package com.example.project_movie_backEnd.controller;

import com.example.project_movie_backEnd.dto.reservation.*;
import com.example.project_movie_backEnd.model.*;
import com.example.project_movie_backEnd.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.*;

import static java.lang.Integer.parseInt;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("/api/movie")
public class ReservationController {
    @Autowired
    ReservationService service;
    ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/movies/Ticketing") // 영화관
    public ResponseEntity<Object> getCinemaList() {
        try {
            List<CinemaDto> list = service.findCinema();

            if (list.isEmpty() == false) {
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/movies/Ticketing/cinema") // 영화관에서 상영중은 영화들
    public ResponseEntity<Object> getMovieList(@RequestParam int cid) {
        try {
            List<TheaterDto> list = service.findMoviebyCinema(cid);
            if (list.isEmpty() == false) {
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/movies/Ticketing/theater")  // 상영관 스케쥴
    public ResponseEntity<Object> findMovieSchedulebyMovieName(@RequestParam String title,
                                                               @RequestParam int cid) {
        try {
            List<TheaterScheduleDto> list = service.findMovieSchedulebyMovieName(title, cid);
            if (list.isEmpty() == false) {
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/movies/Ticketing/seat") // 상영관의 좌석 현황
    public ResponseEntity<Object> findMovieSeatbyMovieSchedule(@RequestParam int thid,
                                                               @RequestParam String thdate,
                                                               @RequestParam String thtime) {
        try {
            Pageable pageable = PageRequest.of(0, 1);
            Page<ResponseMovieSchedule> page = service
                    .findMovieSeatbyMovieSchedule(thid, thdate, thtime, pageable)
                    .map(dbFile -> {
                        String mtitle = service.findMovieSeatbyMovieSchedule(thid, thdate, thtime, pageable).getContent().get(0).getMtitle();
                        int mid = service.findByTitle(mtitle).get(0).getMid();

                        String mPosterUri = ServletUriComponentsBuilder
                                .fromCurrentContextPath()
                                .path("/api/movie/movies/poster/")
                                .path(String.valueOf(mid))
                                .toUriString();

                        ResponseMovieSchedule dto = modelMapper.map(dbFile, ResponseMovieSchedule.class);
                        dto.setMposterUri(mPosterUri);

                        return dto;
                    });

            if (page.isEmpty() == false) {
                return new ResponseEntity<>(page.getContent().get(0), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/movies/Ticketing/Member/reserve") // 회원 예매
    public ResponseEntity<Object> MemberticketReserve(@RequestParam int scid, // 예약할 영화의 예약번호
                                                      @RequestParam String seatId,// 예약할 좌석번호 0105T1010 식으로 'T'를 사용해서 좌석을구분
                                                      @RequestParam int price,
                                                      @RequestParam int memNo) {
        log.info("받은값" + scid + seatId + price + memNo);
        try {
            // 좌석의 예매 여부를 체크하기 위하여 영화 일정을 불러옴
            List<Schedule> list = service.findSeatReserved(scid);

            // 예약 불가능한 좌석을 에러와 함께 돌려 보내기위해 만든 List
            List<String> notAllowdSeatNum = new ArrayList<String>();
            List<String> AllowedSeatNum = new ArrayList<String>();

            // 받은 좌석번호들을 T를 기준으로 잘라서 배열에 저장
            String[] arr = seatId.split("T");
            // 받은 좌석번호가 예약된 혹은 예약불가좌석이라면 flag를 true로하고, notExceptionableSeatNum에 불가능한 좌석번호를 넣음
            boolean seatFlag = false;

            // 받은 예약할 좌석들의 예약가능 여부를 체크하여서 불가능한 좌석이면 좌석번호와 함께 406에러를 돌려보냄
            for (int i = 0; i < (seatId.length() + 1) / 5; i++) {
                // 받은 seatID를 앞부분과 뒷부분으로 분리해서 열과 행으로 분류 ex) P열 9번자리 1609로 받아서 Column=16, Row=9로 분리
                int Column = parseInt(arr[i].substring(0, 2));
                int Row = parseInt(arr[i].substring(2, 4));
                String num = "";
                //분리된 행과열을 사용해서 해당좌석의 OXA 상태를 num에 저장
                switch (Column) {
                    case 1:
                        num = list.get(0).getSeatA().substring(Row - 1, Row);
                        break;
                    case 2:
                        num = list.get(0).getSeatB().substring(Row - 1, Row);
                        break;
                    case 3:
                        num = list.get(0).getSeatC().substring(Row - 1, Row);
                        break;
                    case 4:
                        num = list.get(0).getSeatD().substring(Row - 1, Row);
                        break;
                    case 5:
                        num = list.get(0).getSeatE().substring(Row - 1, Row);
                        break;
                    case 6:
                        num = list.get(0).getSeatF().substring(Row - 1, Row);
                        break;
                    case 7:
                        num = list.get(0).getSeatG().substring(Row - 1, Row);
                        break;
                    case 8:
                        num = list.get(0).getSeatH().substring(Row - 1, Row);
                        break;
                    case 9:
                        num = list.get(0).getSeatI().substring(Row - 1, Row);
                        break;
                    case 10:
                        num = list.get(0).getSeatJ().substring(Row - 1, Row);
                        break;
                    case 11:
                        num = list.get(0).getSeatK().substring(Row - 1, Row);
                        break;
                    case 12:
                        num = list.get(0).getSeatL().substring(Row - 1, Row);
                        break;
                    case 13:
                        num = list.get(0).getSeatM().substring(Row - 1, Row);
                        break;
                    case 14:
                        num = list.get(0).getSeatN().substring(Row - 1, Row);
                        break;
                    case 15:
                        num = list.get(0).getSeatO().substring(Row - 1, Row);
                        break;
                    case 16:
                        num = list.get(0).getSeatP().substring(Row - 1, Row);
                        break;
                    default:
                        return new ResponseEntity<>("not acceptable seat num", HttpStatus.NOT_ACCEPTABLE);
                }

                if (num.equals("X") || num.equals("A")) {
                    seatFlag = true;
                    notAllowdSeatNum.add(arr[i]);
                } else {
                    AllowedSeatNum.add(arr[i]);
                }
            }
            // for문을 통과하고, seatFlag가 true(불가능한 좌석이있음)가 되면 406에러와 불가능한 좌석번호를 보냄
            if (seatFlag) {
                Map<String, Object> response = new HashMap<>();
                response.put("AllowedSeatNum", AllowedSeatNum);
                response.put("notAllowedSeatNum", notAllowdSeatNum);
                return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
            }
            String seatAData = list.get(0).getSeatA(); //특정 열의 좌석상태
            String seatBData = list.get(0).getSeatB(); //특정 열의 좌석상태
            String seatCData = list.get(0).getSeatC(); //특정 열의 좌석상태
            String seatDData = list.get(0).getSeatD(); //특정 열의 좌석상태
            String seatEData = list.get(0).getSeatE(); //특정 열의 좌석상태
            String seatFData = list.get(0).getSeatF(); //특정 열의 좌석상태
            String seatGData = list.get(0).getSeatG(); //특정 열의 좌석상태
            String seatHData = list.get(0).getSeatH(); //특정 열의 좌석상태
            String seatIData = list.get(0).getSeatI(); //특정 열의 좌석상태
            String seatJData = list.get(0).getSeatJ(); //특정 열의 좌석상태
            String seatKData = list.get(0).getSeatK(); //특정 열의 좌석상태
            String seatLData = list.get(0).getSeatL(); //특정 열의 좌석상태
            String seatMData = list.get(0).getSeatM(); //특정 열의 좌석상태
            String seatNData = list.get(0).getSeatN(); //특정 열의 좌석상태
            String seatOData = list.get(0).getSeatO(); //특정 열의 좌석상태
            String seatPData = list.get(0).getSeatP(); //특정 열의 좌석상태

            Schedule schedule = list.get(0);
            // 예약하려는 좌석들을 스케줄 테이블에서 예약된상태로 만듬
            for (int i = 0; i < (seatId.length() + 1) / 5; i++) {
                int Column = parseInt(arr[i].substring(0, 2)); // 좌석의 열번호
                int Row = parseInt(arr[i].substring(2, 4)); // 좌석의 행번호
                switch (Column) {
                    case 1:
                        seatAData = seatAData.substring(0, Row - 1) + 'X' + seatAData.substring(Row);
                        break;
                    case 2:
                        seatBData = seatBData.substring(0, Row - 1) + 'X' + seatBData.substring(Row);
                        break;
                    case 3:
                        seatCData = seatCData.substring(0, Row - 1) + 'X' + seatCData.substring(Row);
                        break;
                    case 4:
                        seatDData = seatDData.substring(0, Row - 1) + 'X' + seatDData.substring(Row);
                        break;
                    case 5:
                        seatEData = seatEData.substring(0, Row - 1) + 'X' + seatEData.substring(Row);
                        break;
                    case 6:
                        seatFData = seatFData.substring(0, Row - 1) + 'X' + seatFData.substring(Row);
                        break;
                    case 7:
                        seatGData = seatGData.substring(0, Row - 1) + 'X' + seatGData.substring(Row);
                        break;
                    case 8:
                        seatHData = seatHData.substring(0, Row - 1) + 'X' + seatHData.substring(Row);
                        break;
                    case 9:
                        seatIData = seatIData.substring(0, Row - 1) + 'X' + seatIData.substring(Row);
                        break;
                    case 10:
                        seatJData = seatJData.substring(0, Row - 1) + 'X' + seatJData.substring(Row);
                        break;
                    case 11:
                        seatKData = seatKData.substring(0, Row - 1) + 'X' + seatKData.substring(Row);
                        break;
                    case 12:
                        seatLData = seatLData.substring(0, Row - 1) + 'X' + seatLData.substring(Row);
                        break;
                    case 13:
                        seatMData = seatMData.substring(0, Row - 1) + 'X' + seatMData.substring(Row);
                        break;
                    case 14:
                        seatNData = seatNData.substring(0, Row - 1) + 'X' + seatNData.substring(Row);
                        break;
                    case 15:
                        seatOData = seatOData.substring(0, Row - 1) + 'X' + seatOData.substring(Row);
                        break;
                    case 16:
                        seatPData = seatPData.substring(0, Row - 1) + 'X' + seatPData.substring(Row);
                        break;
                }
            }
            schedule.setSeatA(seatAData);
            schedule.setSeatB(seatBData);
            schedule.setSeatC(seatCData);
            schedule.setSeatD(seatDData);
            schedule.setSeatE(seatEData);
            schedule.setSeatF(seatFData);
            schedule.setSeatG(seatGData);
            schedule.setSeatH(seatHData);
            schedule.setSeatI(seatIData);
            schedule.setSeatJ(seatJData);
            schedule.setSeatK(seatKData);
            schedule.setSeatL(seatLData);
            schedule.setSeatM(seatMData);
            schedule.setSeatN(seatNData);
            schedule.setSeatO(seatOData);
            schedule.setSeatP(seatPData);
            service.save(schedule);
            // 3. 티켓 테이블에 들어갈 데이터 작성
            // 영화 이름
            String mtitle = list.get(0).getMtitle();
            // 영화관 명 + 상영관
            List<CinemaTheaterDto> name = service.findMovieLocatebyTheaterId(list.get(0).getThid());
            String theater = name.get(0).getcinemaName() + " : " + name.get(0).getthname();
            // 가격 - 프런트에서 받아오는것으로 결정
//                int price = 12000;
            // 좌석위치
            String seat = "";
            // 좌석 열의 이름을 1~16에서 A~P로 변환하고, 좌석번호를 붙임
            for (int i = 0; i < (seatId.length() + 1) / 5; i++) {

                int Column = parseInt(arr[i].substring(0, 2));
                String Row = String.valueOf(parseInt(arr[i].substring(2, 4)) - 1);
                if (Integer.parseInt(Row) < 10) {
                    Row = "0" + Row;
                }
                switch (Column) {
                    case 1:
                        seat = seat + "A열 " + Row + "번 좌석 \n";
                        break;
                    case 2:
                        seat = seat + "B열 " + Row + "번 좌석 \n";
                        break;
                    case 3:
                        seat = seat + "C열 " + Row + "번 좌석 \n";
                        break;
                    case 4:
                        seat = seat + "D열 " + Row + "번 좌석 \n";
                        break;
                    case 5:
                        seat = seat + "E열 " + Row + "번 좌석 \n";
                        break;
                    case 6:
                        seat = seat + "F열 " + Row + "번 좌석 \n";
                        break;
                    case 7:
                        seat = seat + "G열 " + Row + "번 좌석 \n";
                        break;
                    case 8:
                        seat = seat + "H열 " + Row + "번 좌석 \n";
                        break;
                    case 9:
                        seat = seat + "I열 " + Row + "번 좌석 \n";
                        break;
                    case 10:
                        seat = seat + "J열 " + Row + "번 좌석 \n";
                        break;
                    case 11:
                        seat = seat + "K열 " + Row + "번 좌석 \n";
                        break;
                    case 12:
                        seat = seat + "L열 " + Row + "번 좌석 \n";
                        break;
                    case 13:
                        seat = seat + "M열 " + Row + "번 좌석 \n";
                        break;
                    case 14:
                        seat = seat + "N열 " + Row + "번 좌석 \n";
                        break;
                    case 15:
                        seat = seat + "O열 " + Row + "번 좌석 \n";
                        break;
                    case 16:
                        seat = seat + "P열 " + Row + "번 좌석 \n";
                        break;
                }
            }

            // 영화 시작시간
            String startTime = list.get(0).getThdate().substring(0, 2) + "년 " + list.get(0).getThdate().substring(2, 4) + "월 "
                    + list.get(0).getThdate().substring(4, 6) + "일 "
                    + list.get(0).getThtime().substring(0, 2) + "시 " + list.get(0).getThtime().substring(2, 4) + "분";

            // 영화 종료시간 - 영화나 스케쥴테이블에 기입하지 않아서 DB에서 불러온 데이터로 시간을 만들어서 내보냄
            //  1.영화명으로 영화테이블에서 상영시간을 긁어옴
            List<Movie> end = service.findByTitle(list.get(0).getMtitle());
            //  2.스케줄 테이블에서 시작시간을 가져옴
            String sTime = list.get(0).getThtime();
            //  3.상영시간이 스트링으로 되어있고 'xxx분' 형태로 되어 있기에, 마지막 '분'을 빼도록 작업
            int running = parseInt(end.get(0).getMrunningTime().substring(0, end.get(0).getMrunningTime().length() - 1));
            //  4.시작시간을 시간과 분으로 분리함
            String hour = sTime.substring(0, 2);
            String min = sTime.substring(2, 4);
            //  5.시작시간을 초로 변환함
            int tmpTime = (parseInt(hour) * 3600) + (parseInt(min) * 60);
            //  6.시작시간 + 러닝타임(초 단위로 환산하여 계산)
            int etime = tmpTime + (running * 60);
            //  7.계산된 값을 스트링형태로 저장
            hour = String.valueOf((etime / 3600));
            min = String.valueOf((etime % 3600) / 60);
            String endTime = hour + min;
            //  8.종료예정 시간이 날을 넘기면 25시로 표시될 수 있기에, 2400을빼서 시간을 내놓음
            if (parseInt(endTime) > 2400) {
                int tmp = parseInt(endTime) - 2400;
                endTime = 0 + String.valueOf(tmp);
                // 9. 종료예정 시간이 10시 이전이면 표기의 편의를 위하여 0을붙여서 네자리로 만듬
            } else if (parseInt(endTime) < 1000) {
                endTime = 0 + endTime;
            }
            endTime = endTime.substring(0, 2) + "시 " + endTime.substring(2, 4) + "분";

            Ticket ticket = Ticket.builder()
                    .tkTitle(mtitle)
                    .tkTheater(theater)
                    .tkPrice(price)
                    .tkSeat(seat)
                    .tkStartTime(startTime)
                    .tkEndTime(endTime)
                    .tkMemYn("Y")
                    .tkMemId(memNo)
                    .build(); // 티켓 생성
            service.saveTicket(ticket); // 티켓 저장

            //멤버 인포에 본영화를 추가하고, 포인트를 가격의 1% 적립
            Optional<MemberInfo> memberInfos = service.findBymemNo(memNo);

            if (memberInfos.isPresent()) { // 기존에 멤버인포에 등록되어 있을경우
                MemberInfo memberInfo = memberInfos.get();
                memberInfo.setMemWatched(memberInfo.getMemWatched() + "//" + mtitle);
                memberInfo.setMemPoint(memberInfo.getMemPoint() + (price / 100));
                service.saveMemInfo(memberInfo);
            } else { // 기존에 멤버인포에 등록되지 않은 회원(=가입하고 아직영화를 보지않은 회원)
                MemberInfo memberInfo = MemberInfo.builder()
                        .memNo(memNo)
                        .memWatched(mtitle)
                        .memPoint(price / 100)
                        .build();
                service.saveMemInfo(memberInfo);
            }

            List<Movie> movies = service.findByTitle(mtitle); // 타이틀로 영화를 불러옴
            Movie movie = movies.get(0);
            int mid = movie.getMid(); // 불러온 영화정보의 Mid추출

            Optional<MovieInfo> movieInfos = service.findByMid(mid); // mid로 영화 정보 테이블을 불러옴
            MovieInfo movieInfo = movieInfos.get();
            movieInfo.setMcumulativeAudience(movieInfo.getMcumulativeAudience() + 1); // 불러온 영화정보에서 누적관객수에 +1
            service.saveMovieInfo(movieInfo); // 영화정보 테이블에 관객수 +1 저장

            // 만든 데이터를 기반으로 티켓 발행 + 영화정보테이블의 누적 관람객수 한명 추가 + 멤버인포에 본 영화 추가
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/movies/Ticketing/NonMember/reserve") // 비회원 예매
    public ResponseEntity<Object> NonMemberticketReserve(@RequestParam int scid, // 예약할 영화의 예약번호
                                                         @RequestParam String seatId,// 예약할 좌석번호 0105T1010 식으로 'T'를 사용해서 좌석을구분
                                                         @RequestParam int price,
                                                         @RequestParam String tkPhone,
                                                         @RequestParam String tkPw) {
        try {
            // 좌석의 예매 여부를 체크하기 위하여 영화 일정을 불러옴
            List<Schedule> list = service.findSeatReserved(scid);

            // 예약 불가능한 좌석을 에러와 함께 돌려 보내기위해 만든 List
            List<String> notAllowdSeatNum = new ArrayList<String>();
            List<String> AllowedSeatNum = new ArrayList<String>();

            // 받은 좌석번호들을 T를 기준으로 잘라서 배열에 저장
            String[] arr = seatId.split("T");
            // 받은 좌석번호가 예약된 혹은 예약불가좌석이라면 flag를 true로하고, notExceptionableSeatNum에 불가능한 좌석번호를 넣음
            boolean seatFlag = false;

            // 받은 예약할 좌석들의 예약가능 여부를 체크하여서 불가능한 좌석이면 좌석번호와 함께 406에러를 돌려보냄
            for (int i = 0; i < (seatId.length() + 1) / 5; i++) {
                // 받은 seatID를 앞부분과 뒷부분으로 분리해서 열과 행으로 분류 ex) P열 9번자리 1609로 받아서 Column=16, Row=9로 분리
                int Column = parseInt(arr[i].substring(0, 2));
                int Row = parseInt(arr[i].substring(2, 4));
                String num = "";
                //분리된 행과열을 사용해서 해당좌석의 OXA 상태를 num에 저장
                switch (Column) {
                    case 1:
                        num = list.get(0).getSeatA().substring(Row - 1, Row);
                        break;
                    case 2:
                        num = list.get(0).getSeatB().substring(Row - 1, Row);
                        break;
                    case 3:
                        num = list.get(0).getSeatC().substring(Row - 1, Row);
                        break;
                    case 4:
                        num = list.get(0).getSeatD().substring(Row - 1, Row);
                        break;
                    case 5:
                        num = list.get(0).getSeatE().substring(Row - 1, Row);
                        break;
                    case 6:
                        num = list.get(0).getSeatF().substring(Row - 1, Row);
                        break;
                    case 7:
                        num = list.get(0).getSeatG().substring(Row - 1, Row);
                        break;
                    case 8:
                        num = list.get(0).getSeatH().substring(Row - 1, Row);
                        break;
                    case 9:
                        num = list.get(0).getSeatI().substring(Row - 1, Row);
                        break;
                    case 10:
                        num = list.get(0).getSeatJ().substring(Row - 1, Row);
                        break;
                    case 11:
                        num = list.get(0).getSeatK().substring(Row - 1, Row);
                        break;
                    case 12:
                        num = list.get(0).getSeatL().substring(Row - 1, Row);
                        break;
                    case 13:
                        num = list.get(0).getSeatM().substring(Row - 1, Row);
                        break;
                    case 14:
                        num = list.get(0).getSeatN().substring(Row - 1, Row);
                        break;
                    case 15:
                        num = list.get(0).getSeatO().substring(Row - 1, Row);
                        break;
                    case 16:
                        num = list.get(0).getSeatP().substring(Row - 1, Row);
                        break;
                    default:
                        return new ResponseEntity<>("not acceptable seat num", HttpStatus.NOT_ACCEPTABLE);
                }

                if (num.equals("X") || num.equals("A")) {
                    seatFlag = true;
                    notAllowdSeatNum.add(arr[i]);
                } else {
                    AllowedSeatNum.add(arr[i]);
                }
            }
            // for문을 통과하고, seatFlag가 true(불가능한 좌석이있음)가 되면 406에러와 불가능한 좌석번호를 보냄
            if (seatFlag) {
                Map<String, Object> response = new HashMap<>();
                response.put("AllowedSeatNum", AllowedSeatNum);
                response.put("notAllowedSeatNum", notAllowdSeatNum);
                return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
            }
            String seatAData = list.get(0).getSeatA(); //특정 열의 좌석상태
            String seatBData = list.get(0).getSeatB(); //특정 열의 좌석상태
            String seatCData = list.get(0).getSeatC(); //특정 열의 좌석상태
            String seatDData = list.get(0).getSeatD(); //특정 열의 좌석상태
            String seatEData = list.get(0).getSeatE(); //특정 열의 좌석상태
            String seatFData = list.get(0).getSeatF(); //특정 열의 좌석상태
            String seatGData = list.get(0).getSeatG(); //특정 열의 좌석상태
            String seatHData = list.get(0).getSeatH(); //특정 열의 좌석상태
            String seatIData = list.get(0).getSeatI(); //특정 열의 좌석상태
            String seatJData = list.get(0).getSeatJ(); //특정 열의 좌석상태
            String seatKData = list.get(0).getSeatK(); //특정 열의 좌석상태
            String seatLData = list.get(0).getSeatL(); //특정 열의 좌석상태
            String seatMData = list.get(0).getSeatM(); //특정 열의 좌석상태
            String seatNData = list.get(0).getSeatN(); //특정 열의 좌석상태
            String seatOData = list.get(0).getSeatO(); //특정 열의 좌석상태
            String seatPData = list.get(0).getSeatP(); //특정 열의 좌석상태

            Schedule schedule = list.get(0);
            // 예약하려는 좌석들을 스케줄 테이블에서 예약된상태로 만듬
            for (int i = 0; i < (seatId.length() + 1) / 5; i++) {
                int Column = parseInt(arr[i].substring(0, 2)); // 좌석의 열번호
                int Row = parseInt(arr[i].substring(2, 4)); // 좌석의 행번호
                switch (Column) {
                    case 1:
                        seatAData = seatAData.substring(0, Row - 1) + 'X' + seatAData.substring(Row);
                        break;
                    case 2:
                        seatBData = seatBData.substring(0, Row - 1) + 'X' + seatBData.substring(Row);
                        break;
                    case 3:
                        seatCData = seatCData.substring(0, Row - 1) + 'X' + seatCData.substring(Row);
                        break;
                    case 4:
                        seatDData = seatDData.substring(0, Row - 1) + 'X' + seatDData.substring(Row);
                        break;
                    case 5:
                        seatEData = seatEData.substring(0, Row - 1) + 'X' + seatEData.substring(Row);
                        break;
                    case 6:
                        seatFData = seatFData.substring(0, Row - 1) + 'X' + seatFData.substring(Row);
                        break;
                    case 7:
                        seatGData = seatGData.substring(0, Row - 1) + 'X' + seatGData.substring(Row);
                        break;
                    case 8:
                        seatHData = seatHData.substring(0, Row - 1) + 'X' + seatHData.substring(Row);
                        break;
                    case 9:
                        seatIData = seatIData.substring(0, Row - 1) + 'X' + seatIData.substring(Row);
                        break;
                    case 10:
                        seatJData = seatJData.substring(0, Row - 1) + 'X' + seatJData.substring(Row);
                        break;
                    case 11:
                        seatKData = seatKData.substring(0, Row - 1) + 'X' + seatKData.substring(Row);
                        break;
                    case 12:
                        seatLData = seatLData.substring(0, Row - 1) + 'X' + seatLData.substring(Row);
                        break;
                    case 13:
                        seatMData = seatMData.substring(0, Row - 1) + 'X' + seatMData.substring(Row);
                        break;
                    case 14:
                        seatNData = seatNData.substring(0, Row - 1) + 'X' + seatNData.substring(Row);
                        break;
                    case 15:
                        seatOData = seatOData.substring(0, Row - 1) + 'X' + seatOData.substring(Row);
                        break;
                    case 16:
                        seatPData = seatPData.substring(0, Row - 1) + 'X' + seatPData.substring(Row);
                        break;
                }
            }
            schedule.setSeatA(seatAData);
            schedule.setSeatB(seatBData);
            schedule.setSeatC(seatCData);
            schedule.setSeatD(seatDData);
            schedule.setSeatE(seatEData);
            schedule.setSeatF(seatFData);
            schedule.setSeatG(seatGData);
            schedule.setSeatH(seatHData);
            schedule.setSeatI(seatIData);
            schedule.setSeatJ(seatJData);
            schedule.setSeatK(seatKData);
            schedule.setSeatL(seatLData);
            schedule.setSeatM(seatMData);
            schedule.setSeatN(seatNData);
            schedule.setSeatO(seatOData);
            schedule.setSeatP(seatPData);
            service.save(schedule);
            // 3. 티켓 테이블에 들어갈 데이터 작성
            // 영화 이름
            String mtitle = list.get(0).getMtitle();
            // 영화관 명 + 상영관
            List<CinemaTheaterDto> name = service.findMovieLocatebyTheaterId(list.get(0).getThid());
            String theater = name.get(0).getcinemaName() + " : " + name.get(0).getthname();
            // 가격 - 프런트에서 받아오는것으로 결정
//                int price = 12000;
            // 좌석위치
            String seat = "";
            // 좌석 열의 이름을 1~16에서 A~P로 변환하고, 좌석번호를 붙임
            for (int i = 0; i < (seatId.length() + 1) / 5; i++) {
                int Column = parseInt(arr[i].substring(0, 2));
                String Row = String.valueOf(parseInt(arr[i].substring(2, 4)) - 1);
                if (Integer.parseInt(Row) < 10) {
                    Row = "0" + Row;
                }
                switch (Column) {
                    case 1:
                        seat = seat + "A열 " + Row + "번 좌석 \n";
                        break;
                    case 2:
                        seat = seat + "B열 " + Row + "번 좌석 \n";
                        break;
                    case 3:
                        seat = seat + "C열 " + Row + "번 좌석 \n";
                        break;
                    case 4:
                        seat = seat + "D열 " + Row + "번 좌석 \n";
                        break;
                    case 5:
                        seat = seat + "E열 " + Row + "번 좌석 \n";
                        break;
                    case 6:
                        seat = seat + "F열 " + Row + "번 좌석 \n";
                        break;
                    case 7:
                        seat = seat + "G열 " + Row + "번 좌석 \n";
                        break;
                    case 8:
                        seat = seat + "H열 " + Row + "번 좌석 \n";
                        break;
                    case 9:
                        seat = seat + "I열 " + Row + "번 좌석 \n";
                        break;
                    case 10:
                        seat = seat + "J열 " + Row + "번 좌석 \n";
                        break;
                    case 11:
                        seat = seat + "K열 " + Row + "번 좌석 \n";
                        break;
                    case 12:
                        seat = seat + "L열 " + Row + "번 좌석 \n";
                        break;
                    case 13:
                        seat = seat + "M열 " + Row + "번 좌석 \n";
                        break;
                    case 14:
                        seat = seat + "N열 " + Row + "번 좌석 \n";
                        break;
                    case 15:
                        seat = seat + "O열 " + Row + "번 좌석 \n";
                        break;
                    case 16:
                        seat = seat + "P열 " + Row + "번 좌석 \n";
                        break;
                }
            }

            // 영화 시작시간
            String startTime = list.get(0).getThdate().substring(0, 2) + "년 " + list.get(0).getThdate().substring(2, 4) + "월 "
                    + list.get(0).getThdate().substring(4, 6) + "일 "
                    + list.get(0).getThtime().substring(0, 2) + "시 " + list.get(0).getThtime().substring(2, 4) + "분";

            // 영화 종료시간 - 영화나 스케쥴테이블에 기입하지 않아서 DB에서 불러온 데이터로 시간을 만들어서 내보냄
            //  1.영화명으로 영화테이블에서 상영시간을 긁어옴
            List<Movie> end = service.findByTitle(list.get(0).getMtitle());
            //  2.스케줄 테이블에서 시작시간을 가져옴
            String sTime = list.get(0).getThtime();
            //  3.상영시간이 스트링으로 되어있고 'xxx분' 형태로 되어 있기에, 마지막 '분'을 빼도록 작업
            int running = parseInt(end.get(0).getMrunningTime().substring(0, end.get(0).getMrunningTime().length() - 1));
            //  4.시작시간을 시간과 분으로 분리함
            String hour = sTime.substring(0, 2);
            String min = sTime.substring(2, 4);
            //  5.시작시간을 초로 변환함
            int tmpTime = (parseInt(hour) * 3600) + (parseInt(min) * 60);
            //  6.시작시간 + 러닝타임(초 단위로 환산하여 계산)
            int etime = tmpTime + (running * 60);
            //  7.계산된 값을 스트링형태로 저장
            hour = String.valueOf((etime / 3600));
            min = String.valueOf((etime % 3600) / 60);
            String endTime = hour + min;
            //  8.종료예정 시간이 날을 넘기면 25시로 표시될 수 있기에, 2400을빼서 시간을 내놓음
            if (parseInt(endTime) > 2400) {
                int tmp = parseInt(endTime) - 2400;
                endTime = 0 + String.valueOf(tmp);
                // 9. 종료예정 시간이 10시 이전이면 표기의 편의를 위하여 0을붙여서 네자리로 만듬
            } else if (parseInt(endTime) < 1000) {
                endTime = 0 + endTime;
            }
            endTime = endTime.substring(0, 2) + "시 " + endTime.substring(2, 4) + "분";

            Ticket ticket = Ticket.builder()
                    .tkTitle(mtitle)
                    .tkTheater(theater)
                    .tkPrice(price)
                    .tkSeat(seat)
                    .tkStartTime(startTime)
                    .tkEndTime(endTime)
                    .tkMemYn("N")
                    .tkPhone(tkPhone)
                    .tkPw(tkPw)
                    .build(); // 티켓 생성
            service.saveTicket(ticket); // 티켓 저장

            List<Movie> movies = service.findByTitle(mtitle); // 타이틀로 영화를 불러옴
            Movie movie = movies.get(0);
            int mid = movie.getMid(); // 불러온 영화정보의 Mid추출

            Optional<MovieInfo> movieInfos = service.findByMid(mid); // mid로 영화 정보 테이블을 불러옴
            MovieInfo movieInfo = movieInfos.get();
            movieInfo.setMcumulativeAudience(movieInfo.getMcumulativeAudience() + 1); // 불러온 영화정보에서 누적관객수에 +1
            service.saveMovieInfo(movieInfo); // 영화정보 테이블에 관객수 +1 저장

            // 만든 데이터를 기반으로 티켓 발행 + 영화정보테이블의 누적 관람객수 한명 추가
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/Ticket/NonMember")
    public ResponseEntity<Object> NonMemberTicketCheck(@RequestParam String tkPhone,
                                                       @RequestParam String tkPw,
                                                       @RequestParam(defaultValue = "0") int page) {
        try {
            Pageable pageable = PageRequest.of(page, 1);
            Page<Ticket> list = service.findNonMemberTicket(tkPhone, tkPw, pageable); // 티켓정보 불러오기

            List<Movie> movies = service.findByTitle(list.getContent().get(0).getTkTitle());

            String PosterDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/movie/movies/poster/")
                    .path(movies.get(0).getMid().toString())
                    .toUriString();

            Map<String, Object> response = new HashMap<>();
            response.put("Ticket", list.getContent());
            response.put("PosterUri", PosterDownloadUri);
            response.put("currentPage", list.getNumber());
            response.put("totalPages", list.getTotalPages());

            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/Ticket/Member")
    public ResponseEntity<Object> MemberTicketCheck(@RequestParam String memId,
                                                    @RequestParam(defaultValue = "0") int page) {
        try {
            Pageable pageable = PageRequest.of(page, 1);
            Page<Ticket> list = service.findMemberTicket(memId, pageable);

            List<Movie> movies = service.findByTitle(list.getContent().get(0).getTkTitle());

            String PosterDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/movie/movies/poster/")
                    .path(movies.get(0).getMid().toString())
                    .toUriString();

            Map<String, Object> response = new HashMap<>();
            response.put("Ticket", list.getContent());
            response.put("PosterUri", PosterDownloadUri);
            response.put("currentPage", list.getNumber());
            response.put("totalPages", list.getTotalPages());

            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/Ticket/revoke/{tkId}")
    public ResponseEntity<Object> ticketRevoke(@PathVariable int tkId) {
        try {
            boolean bSuccess = service.revokeReservation(tkId); // 해당 티켓번호가 존재하는지 체크
            List<String> tkSeat; // 티켓에서 좌석번호를 꺼내기위한 배열
            if (bSuccess) { // 티켓이 존재하면 티켓에서 좌석정보를 추출해 영화스케쥴에서 좌석정보를 원래대로 되돌림
                Optional<Ticket> list = service.findBytkId(tkId); // 티켓 정보를 가져옴
                if (list.isPresent()) {
                    tkSeat = Arrays.asList(list.get().getTkSeat().split(" \n")); // 영화 좌석을 배열로 저장

                    String title = list.get().getTkTitle(); // 스케쥴을 검색하기 위한 정보1

                    String thdate = list.get().getTkStartTime().substring(0, 2) + // 스케쥴을 검색하기 위한 정보2
                            list.get().getTkStartTime().substring(4, 6) +
                            list.get().getTkStartTime().substring(8, 10);

                    String thtime = list.get().getTkStartTime().substring(12, 14) + // 스케쥴을 검색하기 위한 정보3
                            list.get().getTkStartTime().substring(16, 18);

                    //스케쥴을 검색하기 위해 티켓에 없는 상영관 정보를 찾아오기 위해 티켓에 적힌 상영관정보(xxx지점 : xx관)을 지점과 관으로 쪼갬
                    List<String> cinemaName = Arrays.asList(list.get().getTkTheater().split(" : "));

                    List<CinemaSite> list1 = service.findCinemaId(cinemaName.get(0)); // 상영관 정보를 찾기 위해 영화관명으로 영화관id검색
                    List<Theater> list2 = service.findThid(list1.get(0).getCid(), cinemaName.get(1));// 찾은 영화관id와 상영관명으로 상영관id 검색

                    int thid = list2.get(0).getThid();
                    List<Schedule> list3 = service.findMovieRevokingData(thid, title, thdate, thtime); // 찾아온 정보들로 해당 티켓의 스케쥴을 불러옴
                    log.debug("스케쥴 정보 조회");
                    String seatAData = list3.get(0).getSeatA(); //특정 열의 좌석상태
                    String seatBData = list3.get(0).getSeatB(); //특정 열의 좌석상태
                    String seatCData = list3.get(0).getSeatC(); //특정 열의 좌석상태
                    String seatDData = list3.get(0).getSeatD(); //특정 열의 좌석상태
                    String seatEData = list3.get(0).getSeatE(); //특정 열의 좌석상태
                    String seatFData = list3.get(0).getSeatF(); //특정 열의 좌석상태
                    String seatGData = list3.get(0).getSeatG(); //특정 열의 좌석상태
                    String seatHData = list3.get(0).getSeatH(); //특정 열의 좌석상태
                    String seatIData = list3.get(0).getSeatI(); //특정 열의 좌석상태
                    String seatJData = list3.get(0).getSeatJ(); //특정 열의 좌석상태
                    String seatKData = list3.get(0).getSeatK(); //특정 열의 좌석상태
                    String seatLData = list3.get(0).getSeatL(); //특정 열의 좌석상태
                    String seatMData = list3.get(0).getSeatM(); //특정 열의 좌석상태
                    String seatNData = list3.get(0).getSeatN(); //특정 열의 좌석상태
                    String seatOData = list3.get(0).getSeatO(); //특정 열의 좌석상태
                    String seatPData = list3.get(0).getSeatP(); //특정 열의 좌석상태
                    log.debug("좌석 정보 컨트롤러에 불러옴");
                    for (int i = 0; i < tkSeat.size(); i++) {
                        log.debug((i + 1) + "번째 for문 회전");
                        String Column = tkSeat.get(i).substring(0, 1);
                        log.debug("Column : " + Column);
                        log.debug("Row 를 만들 데이터 : " + tkSeat.get(i).substring(3, 5) + 1);
                        int Row = Integer.parseInt(tkSeat.get(i).substring(3, 5)) + 1;
                        log.debug("Row : " + Row);
                        switch (Column) {
                            case "A":
                                log.debug("케이스 A");
                                seatAData = seatAData.substring(0, Row - 1) + 'O' + seatAData.substring(Row);
                                break;
                            case "B":
                                log.debug("케이스 B");
                                seatBData = seatBData.substring(0, Row - 1) + 'O' + seatBData.substring(Row);
                                break;
                            case "C":
                                log.debug("케이스 C");
                                seatCData = seatCData.substring(0, Row - 1) + 'O' + seatCData.substring(Row);
                                break;
                            case "D":
                                log.debug("케이스 D");
                                seatDData = seatDData.substring(0, Row - 1) + 'O' + seatDData.substring(Row);
                                break;
                            case "E":
                                log.debug("케이스 E");
                                seatEData = seatEData.substring(0, Row - 1) + 'O' + seatEData.substring(Row);
                                break;
                            case "F":
                                log.debug("케이스 F");
                                seatFData = seatFData.substring(0, Row - 1) + 'O' + seatFData.substring(Row);
                                break;
                            case "G":
                                log.debug("케이스 G");
                                seatGData = seatGData.substring(0, Row - 1) + 'O' + seatGData.substring(Row);
                                break;
                            case "H":
                                log.debug("케이스 H");
                                seatHData = seatHData.substring(0, Row - 1) + 'O' + seatHData.substring(Row);
                                break;
                            case "I":
                                log.debug("케이스 I");
                                seatIData = seatIData.substring(0, Row - 1) + 'O' + seatIData.substring(Row);
                                break;
                            case "J":
                                log.debug("케이스 J");
                                seatJData = seatJData.substring(0, Row - 1) + 'O' + seatJData.substring(Row);
                                break;
                            case "K":
                                log.debug("케이스 K");
                                seatKData = seatKData.substring(0, Row - 1) + 'O' + seatKData.substring(Row);
                                break;
                            case "L":
                                log.debug("케이스 L");
                                seatLData = seatLData.substring(0, Row - 1) + 'O' + seatLData.substring(Row);
                                break;
                            case "M":
                                log.debug("케이스 M");
                                seatMData = seatMData.substring(0, Row - 1) + 'O' + seatMData.substring(Row);
                                break;
                            case "N":
                                log.debug("케이스 N");
                                seatNData = seatNData.substring(0, Row - 1) + 'O' + seatNData.substring(Row);
                                break;
                            case "O":
                                log.debug("케이스 O");
                                seatOData = seatOData.substring(0, Row - 1) + 'O' + seatOData.substring(Row);
                                break;
                            case "P":
                                log.debug("케이스 P");
                                seatPData = seatPData.substring(0, Row - 1) + 'O' + seatPData.substring(Row);
                                break;
                        }
                    }

                    Schedule schedule = list3.get(0); // 기존에 좌석 상태에 취소한후의 좌석상태를 저장함
                    schedule.setSeatA(seatAData);
                    schedule.setSeatB(seatBData);
                    schedule.setSeatC(seatCData);
                    schedule.setSeatD(seatDData);
                    schedule.setSeatE(seatEData);
                    schedule.setSeatF(seatFData);
                    schedule.setSeatG(seatGData);
                    schedule.setSeatH(seatHData);
                    schedule.setSeatI(seatIData);
                    schedule.setSeatJ(seatJData);
                    schedule.setSeatK(seatKData);
                    schedule.setSeatL(seatLData);
                    schedule.setSeatM(seatMData);
                    schedule.setSeatN(seatNData);
                    schedule.setSeatO(seatOData);
                    schedule.setSeatP(seatPData);
                    log.debug("스케쥴반영직전");
                    service.save(schedule); //

                    service.ticketCancle(tkId); // 티켓취소
                }
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //티켓 리스트로 받기
    @GetMapping("/TicketList/Member")
    public ResponseEntity<Object> MemberTicketList(@RequestParam Integer memId) {
        try {

            List<Ticket> list = service.findMemberTicketList(memId);

            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}