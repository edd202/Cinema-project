package com.example.project_movie_backEnd.service;

import com.example.project_movie_backEnd.dto.admin.userCountDto;
import com.example.project_movie_backEnd.dto.movie.AudienceReviewDto;
import com.example.project_movie_backEnd.dto.movie.FavoritDto;
import com.example.project_movie_backEnd.dto.movie.MovieMovieinfoDto;
import com.example.project_movie_backEnd.model.Audience;
import com.example.project_movie_backEnd.model.MemberInfo;
import com.example.project_movie_backEnd.model.MovieInfo;
import com.example.project_movie_backEnd.repository.AudienceRepository;
import com.example.project_movie_backEnd.repository.MemberRepository;
import com.example.project_movie_backEnd.repository.MovieInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MovieInfoService {
    @Autowired
    MovieInfoRepository repository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    AudienceRepository audienceRepository;

    public List<MovieMovieinfoDto> movieDetail(String title,String nowDate){
        List<MovieMovieinfoDto> list = repository.movieDetail(title,nowDate);
        return list;
    }

    public Page<MovieMovieinfoDto> movieInfo(int mid, Pageable pageable){
        log.debug("db처리 직전");
        Page<MovieMovieinfoDto> list = repository.movieInfo(mid, pageable);
        return list;
    }

    public Optional<MovieInfo> movieInfoList(int mid){
        Optional<MovieInfo> optionalMovieInfo = repository.findById(mid);
        return optionalMovieInfo;
    }

    public MovieInfo save(MovieInfo movieInfo){
        MovieInfo movieInfo1 = repository.save(movieInfo);
        return movieInfo1;
    }

    public List<FavoritDto> userFavorit(String mTitle){
        List<FavoritDto> list = memberRepository.userFavorit(mTitle);
        return list;
    }

    public Audience saveAudienceCritic(Audience audience) {
        Audience audience1 = audienceRepository.save(audience);
        return audience1;
    }

    public Page<AudienceReviewDto> findReviewOrderByLike(int mid, Pageable pageable){
        Page<AudienceReviewDto> page = audienceRepository.findReviewOrderByLike(mid,pageable);
        return page;
    }
    public Page<AudienceReviewDto> findReviewOrderByInsertTime(int mid, Pageable pageable){
        Page<AudienceReviewDto> page = audienceRepository.findReviewOrderByInsertTime(mid, pageable);
        return page;
    }

    public List<userCountDto> totalReviewCount(int mid){
        List<userCountDto> list = audienceRepository.totalReviewCount(mid);
        return list;
    }

    public Optional<Audience> findByAudno(int audno){
        Optional<Audience> list = audienceRepository.findById(audno);
        return list;
    }
}