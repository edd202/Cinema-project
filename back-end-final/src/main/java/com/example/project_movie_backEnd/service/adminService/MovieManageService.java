package com.example.project_movie_backEnd.service.adminService;

import com.example.project_movie_backEnd.dto.admin.CineTheaterNameDto;
import com.example.project_movie_backEnd.dto.admin.MovieListDto;
import com.example.project_movie_backEnd.dto.reservation.CinemaDto;
import com.example.project_movie_backEnd.model.*;
import com.example.project_movie_backEnd.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MovieManageService {
    @Autowired
    CinemaSiteRepository cinemaSiteRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    MovieInfoRepository movieInfoRepository;
    @Autowired
    NoticeRepository noticeRepository;
    @Autowired
    QnaRepository qnaRepository;
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    TheaterRepository theaterRepository;

    // Movie Admin

//    영화 생성 컴포넌트
    public Movie createUploadFile(String mtitle, String mrunningTime, String mcode,
                                  String mreleaseDate, String mclosingDate, String mdirector,
                                  String mactor, String msimpleInfo, String mtrailerLink, String mage,
                                  MultipartFile mposter, MultipartFile mimage1, MultipartFile mimage2, MultipartFile mimage3,
                                  MultipartFile mimage4, MultipartFile mimage5) throws IOException {

        //            업로드 파일에서 파일명
        String mposterFilename = StringUtils.cleanPath(mposter.getOriginalFilename());
        String mimageFilename1 = StringUtils.cleanPath(mimage1.getOriginalFilename());
        String mimageFilename2 = StringUtils.cleanPath(mimage2.getOriginalFilename());
        String mimageFilename3 = StringUtils.cleanPath(mimage3.getOriginalFilename());
        String mimageFilename4 = StringUtils.cleanPath(mimage4.getOriginalFilename());
        String mimageFilename5 = StringUtils.cleanPath(mimage5.getOriginalFilename());

//        Movie movie = Movie.builder()
//                .mtitle(mtitle)
//                .mrunningTime(mrunningTime)
//                .mcode(mcode)
//                .mposterFilename(mposterFilename)
//                .mimageFilename1(mimageFilename1)
//                .mimageFilename2(mimageFilename2)
//                .mimageFilename3(mimageFilename3)
//                .mimageFilename4(mimageFilename4)
//                .mimageFilename5(mimageFilename5)
//                .mreleaseDate(mreleaseDate)
//                .mclosingDate(mclosingDate)
//                .mdirector(mdirector)
//                .mactor(mactor)
//                .msimpleInfo(msimpleInfo)
//                .mtrailerLink(mtrailerLink)
//                .mage(mage)
//                .mposter(mposter.getBytes())
//                .mimage1(mimage1.getBytes())
//                .mimage2(mimage2.getBytes())
//                .mimage3(mimage3.getBytes())
//                .mimage4(mimage4.getBytes())
//                .mimage5(mimage5.getBytes())
//                .build();

//        Movie createMovie = movieRepository.save(movie);
//        return createMovie;
        Movie movie = new Movie(mtitle, mrunningTime, mcode,
                mreleaseDate, mclosingDate, mdirector, mactor,
                msimpleInfo, mtrailerLink, mage,
                mposter.getBytes(), mposterFilename, mposter.getContentType(),
                mimage1.getBytes(), mimageFilename1, mimage1.getContentType(),  // 영화 모델을 생성
                mimage2.getBytes(), mimageFilename2, mimage2.getContentType(),  // 영화 모델을 생성
                mimage3.getBytes(), mimageFilename3, mimage3.getContentType(), // 영화 모델을 생성
                mimage4.getBytes(), mimageFilename4, mimage4.getContentType(),  // 영화 모델을 생성
                mimage5.getBytes(), mimageFilename5, mimage5.getContentType()); // 영화 모델을 생성

        movieRepository.save(movie); // 생성한 모델을 저장
        //영화모델의 mid를 이용하여 영화정보도 같이 생성
        MovieInfo movieInfo = new MovieInfo(movie.getMid(), 0, 0, 0);
        movieInfoRepository.save(movieInfo);
        return movie;
    }

//    영화정보 수정 컴포넌트
    public Movie updateUploadFile(int mid, String mtitle, String mrunningTime, String mcode,
                                  String mreleaseDate, String mclosingDate, String mdirector,
                                  String mactor, String msimpleInfo, String mtrailerLink, String mage,
                                  MultipartFile mposter, MultipartFile mimage1, MultipartFile mimage2, MultipartFile mimage3,
                                  MultipartFile mimage4, MultipartFile mimage5) throws IOException {

        //            업로드 파일에서 파일명 얻기
        String mposterFilename = StringUtils.cleanPath(mposter.getOriginalFilename());
        String mimageFilename1 = StringUtils.cleanPath(mimage1.getOriginalFilename());
        String mimageFilename2 = StringUtils.cleanPath(mimage2.getOriginalFilename());
        String mimageFilename3 = StringUtils.cleanPath(mimage3.getOriginalFilename());
        String mimageFilename4 = StringUtils.cleanPath(mimage4.getOriginalFilename());
        String mimageFilename5 = StringUtils.cleanPath(mimage5.getOriginalFilename());

        Movie movie = Movie.builder()
                .mid(mid)
                .mtitle(mtitle)
                .mrunningTime(mrunningTime)
                .mcode(mcode)
                .mposterFilename(mposterFilename)
                .mimageFilename1(mimageFilename1)
                .mimageFilename2(mimageFilename2)
                .mimageFilename3(mimageFilename3)
                .mimageFilename4(mimageFilename4)
                .mimageFilename5(mimageFilename5)
                .mreleaseDate(mreleaseDate)
                .mclosingDate(mclosingDate)
                .mdirector(mdirector)
                .mactor(mactor)
                .msimpleInfo(msimpleInfo)
                .mtrailerLink(mtrailerLink)
                .mage(mage)
                .mposter(mposter.getBytes())
                .mimage1(mimage1.getBytes())
                .mimage2(mimage2.getBytes())
                .mimage3(mimage3.getBytes())
                .mimage4(mimage4.getBytes())
                .mimage5(mimage5.getBytes())
                .build();

        Movie updateMovie = movieRepository.save(movie);
        return updateMovie;
//        Movie movie = new Movie(mid, mtitle, mrunningTime, mcode,
//                mreleaseDate, mclosingDate, mdirector, mactor,
//                msimpleInfo, mtrailerLink, mage,
//                mposter.getBytes(), mposterFilename, mposter.getContentType(),
//                mimage1.getBytes(), mimageFilename1, mimage1.getContentType(),
//                mimage2.getBytes(), mimageFilename2, mimage2.getContentType(),
//                mimage3.getBytes(), mimageFilename3, mimage3.getContentType(),
//                mimage4.getBytes(), mimageFilename4, mimage4.getContentType(),
//                mimage5.getBytes(), mimageFilename5, mimage5.getContentType());
//        return movieRepository.save(movie);
    }

//    영화 삭제
    public boolean removeByMid(int mid) {
        if (movieRepository.existsById(mid) && movieInfoRepository.existsById(mid) == true) {
            movieRepository.deleteById(mid);
            movieInfoRepository.deleteById(mid);
            return true;
        }
        return false;
    }

//    이미지 없는 영화목록 검색
    public Page<MovieListDto> findMoviewithoutImagesSearchTitle(String mtitle, Pageable pageable) {
        Page<MovieListDto> page = movieRepository.findMoviewithoutImagesSearchTitle(mtitle, pageable);
        return page;
    }

//    특정 영화정보 불러오기
    public Optional<Movie> getMovie(int mid) {
        Optional<Movie> optionalMovie = movieRepository.findById(mid);

        return optionalMovie;
    }


    // 영화정보추가  영화정보수정    영화정보삭제
    // TODO:영화정보는 영화와 티켓을 추가,삭제할때 영향을 받도록 하기에 만들 필요가 없음. but, 평론가 평점테이블이 완성되지 않았고,
    // TODO:추후에 하지않는 것으로 가닥을 잡는다면 영화정보수정을 만들어 평론가 점수만 따로 수정하도록 구현한다.


    // 영화일정추가  영화일정수정  영화일정삭제 TODO:진행중

    // TODO:영화일정 추가루틴 - 1.영화관 검색 -> 2.영화관 ID로 영화관이가진 상영관검색 -> 3.상영관 ID를 찾아서 해당 상영관에 영화일정 추가
    // 영화관 검색
    public List<CinemaDto> findCinema() {
        List<CinemaDto> list = cinemaSiteRepository.findCinemasite();
        return list;
    }

    // 영화관의 cid를 받아서 해당영화관이 가진 상영관 검색
    public List<CineTheaterNameDto> findTheater(int cid) {
        List<CineTheaterNameDto> list = cinemaSiteRepository.findTheater(cid);
        return list;
    }

    // 영화스케쥴 추가 및 수정
    public Schedule saveSchedule(Schedule schedule) {
        Schedule schedule1 = scheduleRepository.save(schedule);
        return schedule1;
    }

    // 영화스케쥴 삭제
    public boolean removeScheduleByScid(int scid) {
        if (scheduleRepository.existsById(scid)) {
            scheduleRepository.deleteById(scid);
            return true;
        }
        return false;
    }

    // 모든 영화 스케쥴 검색
    public List<Schedule> findAllSchedule() {
        List<Schedule> list = scheduleRepository.findAll();
        return list;
    }

    public List<Schedule> findCinemaSchedule(int cid) {
        List<Schedule> list = scheduleRepository.findCinemaSchedule(cid);
        return list;
    }

    public List<Schedule> findTheaterSchedule(int thid) {
        List<Schedule> list = scheduleRepository.findTheaterSchedule(thid);
        return list;
    }
}
