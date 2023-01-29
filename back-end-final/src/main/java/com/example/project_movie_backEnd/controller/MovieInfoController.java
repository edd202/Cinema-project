package com.example.project_movie_backEnd.controller;

import com.example.project_movie_backEnd.dto.admin.userCountDto;
import com.example.project_movie_backEnd.dto.movie.AudienceReviewDto;
import com.example.project_movie_backEnd.dto.movie.FavoritDto;
import com.example.project_movie_backEnd.dto.movie.MovieMovieinfoDto;
import com.example.project_movie_backEnd.dto.movie.ResponseMovieInfoDto;
import com.example.project_movie_backEnd.model.Audience;
import com.example.project_movie_backEnd.model.MovieInfo;
import com.example.project_movie_backEnd.service.MovieInfoService;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@Slf4j
@RequestMapping("/api/movie")
public class MovieInfoController {
    @Autowired
    MovieInfoService service;
    ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/movieinfo")
    public ResponseEntity<Object> getMovieDetail(@RequestParam String title) {
        try {
            String nowDate = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            List<MovieMovieinfoDto> list = service.movieDetail(title, nowDate);
            if (list.isEmpty() == false) {
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/movieInfo/{mid}")
    public ResponseEntity<Object> getMovieInfo(@PathVariable int mid) {
        try {
            Pageable pageable = PageRequest.of(0, 1);
            AtomicReference<String> mTitle = new AtomicReference<>("");
            log.debug("쿼리 처리전");
            Page<ResponseMovieInfoDto> filePage = service
                    .movieInfo(mid, pageable)
                    .map(dbFile -> {

                        String mposter = ServletUriComponentsBuilder
                                .fromCurrentContextPath()
                                .path("/api/movie/movies/poster/")
                                .path(dbFile.getmid().toString())
                                .toUriString();

                        String mimage1 = ServletUriComponentsBuilder
                                .fromCurrentContextPath()
                                .path("/api/movie/movies/image1/")
                                .path(dbFile.getmid().toString())
                                .toUriString();

                        String mimage2 = ServletUriComponentsBuilder
                                .fromCurrentContextPath()
                                .path("/api/movie/movies/image2/")
                                .path(dbFile.getmid().toString())
                                .toUriString();

                        String mimage3 = ServletUriComponentsBuilder
                                .fromCurrentContextPath()
                                .path("/api/movie/movies/image3/")
                                .path(dbFile.getmid().toString())
                                .toUriString();

                        String mimage4 = ServletUriComponentsBuilder
                                .fromCurrentContextPath()
                                .path("/api/movie/movies/image4/")
                                .path(dbFile.getmid().toString())
                                .toUriString();

                        String mimage5 = ServletUriComponentsBuilder
                                .fromCurrentContextPath()
                                .path("/api/movie/movies/image5/")
                                .path(dbFile.getmid().toString())
                                .toUriString();

                        ResponseMovieInfoDto dto = modelMapper.map(dbFile, ResponseMovieInfoDto.class);
                        dto.setMposter(mposter);
                        dto.setMimage1(mimage1);
                        dto.setMimage2(mimage2);
                        dto.setMimage3(mimage3);
                        dto.setMimage4(mimage4);
                        dto.setMimage5(mimage5);
                        mTitle.set(dto.getMtitle());
                        return dto;
                    });
            List<FavoritDto> list = service.userFavorit(String.valueOf(mTitle));

            Map<String, Object> res = new HashMap<>();
            res.put("movieInfo", filePage.getContent());
            res.put("userFavorit", list.get(0));

            if (filePage.isEmpty() == false) {
                return new ResponseEntity<>(res, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/movieInfo/comment") // 영화 코멘트&별점 등록
    public ResponseEntity<Object> saveAudienceReview(@RequestBody Audience audience) {
        try {
            Audience audience1 = audience;
            audience1.setLikeCount(0);
            audience1.setLikeUser("/");
            audience1 = service.saveAudienceCritic(audience); // 코멘트 등록
            int rate = audience1.getAurate(); // 코멘트에서 준 별점을 추출
            Optional<MovieInfo> infos = service.movieInfoList(audience1.getMid()); // 코멘트의 mid로 별점을 추가할 영화를 불러옴
            if (infos.isPresent()) {
                infos.get().setMaudienceRate(infos.get().getMaudienceRate() + rate); // 불러온 영화의 별점에 코멘트의 별점을 더함
                service.save(infos.get()); // 별점을 수정한 영화를 테이블에 다시 저장
                return new ResponseEntity<>(audience1, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/movieInfo/comment/order/insertTime") // 코멘트 출력 등록시간순으로 정렬
    public ResponseEntity<Object> getReviewOrderByInsertTime(@RequestParam int mid,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<AudienceReviewDto> page1 = service.findReviewOrderByInsertTime(mid, pageable);
            List<userCountDto> count = service.totalReviewCount(mid);
            Map<String, Object> response = new HashMap<>();
            response.put("Review", page1.getContent());
            response.put("ReviewCount", count.get(0));
            response.put("currentPage", page1.getNumber());
            response.put("totalItems", page1.getTotalElements());
            response.put("totalPages", page1.getTotalPages());
            if (response.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/movieInfo/comment/order/like") // 코멘트 출력 좋아요순으로 정렬
    public ResponseEntity<Object> getReviewOrderByLike(@RequestParam int mid,
                                                       @RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<AudienceReviewDto> page1 = service.findReviewOrderByLike(mid, pageable);
            List<userCountDto> count = service.totalReviewCount(mid);
            Map<String, Object> response = new HashMap<>();
            response.put("Review", page1.getContent());
            response.put("ReviewCount", count.get(0));
            response.put("currentPage", page1.getNumber());
            response.put("totalItems", page1.getTotalElements());
            response.put("totalPages", page1.getTotalPages());
            if (response.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/movieInfo/comment/like") // 코멘트 좋아요
    public ResponseEntity<Object> commentLike(@RequestParam int audno,
                                              @RequestParam String memId) {
        try {
            Optional<Audience> audience = service.findByAudno(audno);
            if (audience.isPresent()) {
                List<String> likeUserList = Arrays.asList(audience.get().getLikeUser().split("/"));
                for (int i = 0; i < likeUserList.size(); i++) {
                    if (likeUserList.get(i).equals(memId)) {
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                    }
                }
                audience.get().setLikeCount(audience.get().getLikeCount() + 1);
                audience.get().setLikeUser(audience.get().getLikeUser() + "/" + memId);
                service.saveAudienceCritic(audience.get());
                return new ResponseEntity<>(audience, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/movieInfo/comment/dislike") // 코멘트 좋아요 취소
    public ResponseEntity<Object> commentDislike(@RequestParam int audno,
                                                 @RequestParam String memId) {
        try {
            Optional<Audience> audience = service.findByAudno(audno);
            if (audience.isPresent()) {
                List<String> likeUserList = Arrays.asList(audience.get().getLikeUser().split("/"));
                for (int i = 0; i < likeUserList.size(); i++) {
                    if (likeUserList.get(i).equals(memId)) {
                        audience.get().setLikeCount(audience.get().getLikeCount() - 1);
                        audience.get().setLikeUser(audience.get().getLikeUser().replace("/" + memId, ""));
                        service.saveAudienceCritic(audience.get());
                        return new ResponseEntity<>(audience, HttpStatus.OK);
                    }
                }
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
