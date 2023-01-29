package com.example.project_movie_backEnd.service;

import com.example.project_movie_backEnd.dto.movie.*;
import com.example.project_movie_backEnd.model.Movie;
import com.example.project_movie_backEnd.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MovieService {
    @Autowired
    MovieRepository repository;

    // 이미지 다운로드 서비스
    public Optional<Movie> getFile(int mid) {
        Optional<Movie> optional = repository.findById(mid);
        return optional;
    }


    public Page<MovieMainDto> findMainScreeningListV2(int Rank, String nowDate, Pageable pageable) { //영화 첫 페이지 상영작리스트(상위3개)
        Page<MovieMainDto> list = repository.findMainScreeningListV2(Rank, nowDate, pageable);
        return list;
    }

    public Page<MovieMainDto> findMainNotScreeningListV2(int Rank, String nowDate, Pageable pageable) { //영화 첫 페이지 상영예정리스트(상위3개)
        Page<MovieMainDto> list = repository.findMainNotScreeningListV2(Rank, nowDate, pageable);
        return list;
    }

    public Page<MovieMainDto> findScreeningList(String nowDate, Pageable pageable) { // 영화 - 상영작 페이지
        Page<MovieMainDto> page = repository.findScreeningList(nowDate, pageable);
        return page;
    }
    public Page<MovieMainDto> findNotScreeningList(String nowDate, Pageable pageable) { // 영화 -상영예정작 페이지
        Page<MovieMainDto> page = repository.findNotScreeningList(nowDate, pageable);
        return page;
    }



    public Page<MovieMainDto> findByTitle(String mTitle, Pageable pageable) {
        Page<MovieMainDto> page = repository.findByMovieTitle(mTitle, pageable);
        return page;
    }

    public Page<MovieMainDto> findByCode(String mCode, Pageable pageable) {
        Page<MovieMainDto> page = repository.findByMovieCode(mCode, pageable);
        return page;
    }

    public Page<MovieMainDto> findByDirector(String mDirector, Pageable pageable) {
        Page<MovieMainDto> page = repository.findByMovieDirector(mDirector, pageable);
        return page;
    }

    public Page<MovieMainDto> findByActor(String mActor, Pageable pageable) {
        Page<MovieMainDto> page = repository.findByMovieActor(mActor, pageable);
        return page;
    }

    public Page<MovieMainDto> findByAge(String mAge, Pageable pageable) {
        Page<MovieMainDto> page = repository.findByMovieAge(mAge, pageable);
        return page;
    }

    public Page<MovieDto> findScreeningMovieByRate(String nowDate, Pageable pageable){
        Page<MovieDto> page = repository.findScreeningMovieByRate(nowDate, pageable);
        return page;
    }

    public Page<MovieReviewCount> findScreeningMovieByReviewCount(String nowDate, Pageable pageable){
        Page<MovieReviewCount> page = repository.findScreeningMovieByReviewCount(nowDate, pageable);
        return page;
    }

    public Page<MovieTicketingDto> findScreeningMovieByTicketingPer(String nowDate, Pageable pageable){
        Page<MovieTicketingDto> page = repository.findScreeningMovieByTicketingPer(nowDate, pageable);
        return page;
    }

    public Page<MovieDdayDto> findNotScreeningMovieByDday(String nowDate, Pageable pageable){
        Page<MovieDdayDto> page = repository.findNotScreeningMovieByDday(nowDate, pageable);
        return page;
    }

    public Page<MovieTicketingDto> findNotScreeningMovieByTicketingPer(String nowDate, Pageable pageable){
        Page<MovieTicketingDto> page = repository.findNotScreeningMovieByTicketingPer(nowDate, pageable);
        return page;
    }
}
