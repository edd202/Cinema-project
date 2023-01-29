package com.example.project_movie_backEnd.controller;

import com.example.project_movie_backEnd.dto.movie.*;
import com.example.project_movie_backEnd.model.Movie;
import com.example.project_movie_backEnd.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api/movie")
public class MovieController {
    @Autowired
    MovieService service;
    ModelMapper modelMapper = new ModelMapper();

    // 영화 포스터, 이미지 다운로드 컨트롤러
    @GetMapping("/movies/poster/{mid}") // 포스터 불러오기    TODO:작동 테스트 완료
    public ResponseEntity<Object> getPoster(@PathVariable int mid) {
        Movie movie = service.getFile(mid).get();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + movie.getMposterFilename() + "\"")
                .body(movie.getMposter());
    }

    @GetMapping("/movies/image1/{mid}") // 이미지1 불러오기    TODO:작동 테스트 완료
    public ResponseEntity<byte[]> getImage1(@PathVariable int mid) {

        Movie movie = service.getFile(mid).get();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + movie.getMimageFilename1() + "\"")
                .body(movie.getMimage1());
    }

    @GetMapping("/movies/image2/{mid}") // 이미지2 불러오기    TODO:작동 테스트 완료
    public ResponseEntity<byte[]> getImage2(@PathVariable int mid) {

        Movie movie = service.getFile(mid).get();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + movie.getMimageFilename2() + "\"")
                .body(movie.getMimage2());
    }

    @GetMapping("/movies/image3/{mid}") // 이미지3 불러오기    TODO:작동 테스트 완료
    public ResponseEntity<byte[]> getImage3(@PathVariable int mid) {

        Movie movie = service.getFile(mid).get();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + movie.getMimageFilename3() + "\"")
                .body(movie.getMimage3());
    }

    @GetMapping("/movies/image4/{mid}") // 이미지4 불러오기    TODO:작동 테스트 완료
    public ResponseEntity<byte[]> getImage4(@PathVariable int mid) {

        Movie movie = service.getFile(mid).get();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + movie.getMimageFilename4() + "\"")
                .body(movie.getMimage4());
    }

    @GetMapping("/movies/image5/{mid}") // 이미지5 불러오기    TODO:작동 테스트 완료
    public ResponseEntity<byte[]> getImage5(@PathVariable int mid) {

        Movie movie = service.getFile(mid).get();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + movie.getMimageFilename5() + "\"")
                .body(movie.getMimage5());
    }


    // 사이트 메인에서 상위 세개 작품 표시할떄 쓰일 컨트롤러
    @GetMapping("/main")
    public ResponseEntity<Object> getMainPage() {
        try {
            String nowDate = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd"));

            Pageable pageable = PageRequest.of(0, 5);
            Page<ResponseMovieMainDto> list = service
                    .findMainScreeningListV2(5, nowDate, pageable)
                    .map(dbFile -> {
                        String mPosterDownloadUri = ServletUriComponentsBuilder
                                .fromCurrentContextPath()
                                .path("/api/movie/movies/poster/")
                                .path(dbFile.getmid().toString())
                                .toUriString();
                        ResponseMovieMainDto dto = modelMapper.map(dbFile, ResponseMovieMainDto.class);
                        dto.setMposter(mPosterDownloadUri);
                        return dto;
                    });

            Map<String, Object> response = new HashMap<>();
            response.put("MainList", list.getContent());

            if (response.isEmpty() == false) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // 영화 페이지에서 사용할 데이터 sql(제목, 누적 관객수, 관객 평점, 평론가 평점, 누적 관객수 랭킹, 누적 관객수로 만든 예매율//상영작+상영예정작)    TODO:작동 테스트 완료
    @GetMapping("/movies/main")
    public ResponseEntity<Object> getMovieMainList() {
        try {
            String nowDate = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd"));

            Pageable pageable = PageRequest.of(0, 6);
            Page<ResponseMovieMainDto> list1 = service
                    .findMainScreeningListV2(6, nowDate, pageable)
                    .map(dbFile -> {
                        String mPosterDownloadUri = ServletUriComponentsBuilder
                                .fromCurrentContextPath()
                                .path("/api/movie/movies/poster/")
                                .path(dbFile.getmid().toString())
                                .toUriString();
                        ResponseMovieMainDto dto = modelMapper.map(dbFile, ResponseMovieMainDto.class);
                        dto.setMposter(mPosterDownloadUri);
                        return dto;
                    });

            Page<ResponseMovieMainDto> list2 = service
                    .findMainNotScreeningListV2(6, nowDate, pageable)
                    .map(dbFile -> {
                        String mPosterDownloadUri = ServletUriComponentsBuilder
                                .fromCurrentContextPath()
                                .path("/api/movie/movies/poster/")
                                .path(dbFile.getmid().toString())
                                .toUriString();
                        ResponseMovieMainDto dto = modelMapper.map(dbFile, ResponseMovieMainDto.class);
                        dto.setMposter(mPosterDownloadUri);
                        return dto;
                    });

            Map<String, Object> response = new HashMap<>();
            response.put("ScreeningList", list1.getContent());
            response.put("NotScreeningList", list2.getContent());

            if (response.isEmpty() == false) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 현재 날짜를 기준으로 상영중인 작품들 검색    TODO:작동 테스트 완료
    @GetMapping("/movies/screening")
    public ResponseEntity<Object> getMovieAllByScreening(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        try {
            String nowDate = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd"));

            Pageable pageable = PageRequest.of(page, size);
            Page<ResponseMovieMainDto> list1 = service
                    .findScreeningList(nowDate, pageable)
                    .map(dbFile -> {
                        String mPosterDownloadUri = ServletUriComponentsBuilder
                                .fromCurrentContextPath()
                                .path("/api/movie/movies/poster/")
                                .path(dbFile.getmid().toString())
                                .toUriString();
                        ResponseMovieMainDto dto = modelMapper.map(dbFile, ResponseMovieMainDto.class);
                        dto.setMposter(mPosterDownloadUri);
                        return dto;
                    });
            Map<String, Object> response = new HashMap<>();
            response.put("ScreeningList", list1.getContent());

            if (response.isEmpty() == false) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 현재날짜를 기준으로 상영예정작 검색    TODO:작동 테스트 완료
    @GetMapping("/movies/scheduled")
    public ResponseEntity<Object> getMovieAllByNotScreen(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        try {
            String nowDate = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd"));

            Pageable pageable = PageRequest.of(page, size);
            Page<ResponseMovieMainDto> list1 = service
                    .findNotScreeningList(nowDate, pageable)
                    .map(dbFile -> {
                        String mPosterDownloadUri = ServletUriComponentsBuilder
                                .fromCurrentContextPath()
                                .path("/api/movie/movies/poster/")
                                .path(dbFile.getmid().toString())
                                .toUriString();
                        ResponseMovieMainDto dto = modelMapper.map(dbFile, ResponseMovieMainDto.class);
                        dto.setMposter(mPosterDownloadUri);
                        return dto;
                    });
            Map<String, Object> response = new HashMap<>();
            response.put("NotScreeningList", list1.getContent());

            if (response.isEmpty() == false) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //아래는 검색용(제목,코드(장르,국가),감독,배우,연령제한) 컨트롤러들

    //    http://주소:포트/요청페이지/요청메소드?메소드매개변수명=검색어 형태로 접근할것
    //    ex)http://localhost:8000/movie/movies?mTitle=아바타
    @GetMapping("/movies/title")//영화 제목으로 검색    TODO:작동 테스트 완료
    public ResponseEntity<Object> getMovieAllByTitle(@RequestParam(required = false) String mTitle,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size
    ) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<ResponseMovieMainDto> list1 = service
                    .findByTitle(mTitle, pageable)
                    .map(dbFile -> {
                        String mPosterDownloadUri = ServletUriComponentsBuilder
                                .fromCurrentContextPath()
                                .path("/api/movie/movies/poster/")
                                .path(dbFile.getmid().toString())
                                .toUriString();
                        ResponseMovieMainDto dto = modelMapper.map(dbFile, ResponseMovieMainDto.class);
                        dto.setMposter(mPosterDownloadUri);
                        return dto;
                    });

            Map<String, Object> response = new HashMap<>();
            response.put("findByTitle", list1.getContent());

            if (response.isEmpty() == false) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 영화 장르,국가로 검색 : 실제 데이터는 '애니메이션, 어드벤쳐 / 프랑스' 같은 형태로 들어가 있음.
    @GetMapping("/movies/code") // TODO : 작동 테스트 완료
    public ResponseEntity<Object> getMovieAllByCode(@RequestParam(required = false) String mCode,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size
    ) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<ResponseMovieMainDto> list1 = service
                    .findByCode(mCode, pageable)
                    .map(dbFile -> {
                        String mPosterDownloadUri = ServletUriComponentsBuilder
                                .fromCurrentContextPath()
                                .path("/api/movie/movies/poster/")
                                .path(dbFile.getmid().toString())
                                .toUriString();
                        ResponseMovieMainDto dto = modelMapper.map(dbFile, ResponseMovieMainDto.class);
                        dto.setMposter(mPosterDownloadUri);
                        return dto;
                    });

            Map<String, Object> response = new HashMap<>();
            response.put("findByTitle", list1.getContent());

            if (response.isEmpty() == false) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 영화 감독으로 검색 : 실제 데이터는 대부분 한명이지만, 두명일때는 '다니엘 콴//다니엘 쉐이너트' 형태로 들어가 있음
    // 이용할떄 받아온 값을 jsp에서 배열 = 받은값.split("//")형태로 가공해서 배열로 저장하여서 사용하기를 권장
    @GetMapping("/movies/director")//    TODO:작동 테스트 완료
    public ResponseEntity<Object> getMovieAllByDirector(@RequestParam(required = false) String mDirector,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size
    ) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<ResponseMovieMainDto> list1 = service
                    .findByDirector(mDirector, pageable)
                    .map(dbFile -> {
                        String mPosterDownloadUri = ServletUriComponentsBuilder
                                .fromCurrentContextPath()
                                .path("/api/movie/movies/poster/")
                                .path(dbFile.getmid().toString())
                                .toUriString();
                        ResponseMovieMainDto dto = modelMapper.map(dbFile, ResponseMovieMainDto.class);
                        dto.setMposter(mPosterDownloadUri);
                        return dto;
                    });

            Map<String, Object> response = new HashMap<>();
            response.put("findByTitle", list1.getContent());

            if (response.isEmpty() == false) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 영화 배우로 검색 :실제 데이터는 '조 샐다나//샘워싱턴//시고니 위버' 처럼 들어가있음
    // 이용할떄 받아온 값을 jsp에서 배열 = 받은값.split("//")형태로 가공해서 배열로 저장하여서 사용하기를 권장
    @GetMapping("/movies/actor")//    TODO:작동 테스트 완료
    public ResponseEntity<Object> getMovieAllByActor(@RequestParam(required = false) String mActor,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size
    ) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<ResponseMovieMainDto> list1 = service
                    .findByActor(mActor, pageable)
                    .map(dbFile -> {
                        String mPosterDownloadUri = ServletUriComponentsBuilder
                                .fromCurrentContextPath()
                                .path("/api/movie/movies/poster/")
                                .path(dbFile.getmid().toString())
                                .toUriString();
                        ResponseMovieMainDto dto = modelMapper.map(dbFile, ResponseMovieMainDto.class);
                        dto.setMposter(mPosterDownloadUri);
                        return dto;
                    });

            Map<String, Object> response = new HashMap<>();
            response.put("findByTitle", list1.getContent());

            if (response.isEmpty() == false) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 영화 연령제한으로 검색( '전체이용가','12세','15세','18세'로 구분됨)
    @GetMapping("/movies/age")//    TODO:작동 테스트 완료
    public ResponseEntity<Object> getMovieAllByAge(@RequestParam(required = false) String mAge,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size
    ) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<ResponseMovieMainDto> list1 = service
                    .findByAge(mAge, pageable)
                    .map(dbFile -> {
                        String mPosterDownloadUri = ServletUriComponentsBuilder
                                .fromCurrentContextPath()
                                .path("/api/movie/movies/poster/")
                                .path(dbFile.getmid().toString())
                                .toUriString();
                        ResponseMovieMainDto dto = modelMapper.map(dbFile, ResponseMovieMainDto.class);
                        dto.setMposter(mPosterDownloadUri);
                        return dto;
                    });

            Map<String, Object> response = new HashMap<>();
            response.put("findByTitle", list1.getContent());

            if (response.isEmpty() == false) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/movies/rating")
    public ResponseEntity<Object> getMoviesByRating(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size) {
        try {
            String nowDate = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            Pageable pageable = PageRequest.of(page, size);
            Page<ResponseMovieByRatingDto> list = service
                    .findScreeningMovieByRate(nowDate,pageable)
                    .map(dbFile -> {
                        String mPosterDownloadUri = ServletUriComponentsBuilder
                                .fromCurrentContextPath()
                                .path("/api/movie/movies/poster/")
                                .path(dbFile.getmid().toString())
                                .toUriString();
                        ResponseMovieByRatingDto dto = modelMapper.map(dbFile, ResponseMovieByRatingDto.class);
                        dto.setMPosterUri(mPosterDownloadUri);
                        return dto;
                    });
            Map<String, Object> response = new HashMap<>();
            response.put("movies", list.getContent());
            response.put("currentPage", list.getNumber());
            response.put("totalItems", list.getTotalElements());
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

    @GetMapping("/movies/reviewCount")
    public ResponseEntity<Object> getMoviesByReviewCount(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        try {
            String nowDate = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            Pageable pageable = PageRequest.of(page, size);
//            Page<MovieReviewCount> page1 = service.findScreeningMovieByReviewCount(pageable);
//            return new ResponseEntity<>(page1,HttpStatus.OK);
//            log.debug(page1.getContent().toString());
            Page<ResponseMovieByReviewCountDto> list = service
                    .findScreeningMovieByReviewCount(nowDate, pageable)
                    .map(dbFile -> {
                        String mPosterDownloadUri = ServletUriComponentsBuilder
                                .fromCurrentContextPath()
                                .path("/api/movie/movies/poster/")
                                .path(dbFile.getmid().toString())
                                .toUriString();
                        ResponseMovieByReviewCountDto dto = modelMapper.map(dbFile, ResponseMovieByReviewCountDto.class);
                        dto.setMPosterUri(mPosterDownloadUri);
                        return dto;
                    });
            Map<String, Object> response = new HashMap<>();
            response.put("movies", list.getContent());
            response.put("currentPage", list.getNumber());
            response.put("totalItems", list.getTotalElements());
            response.put("totalPages", list.getTotalPages());

            if (response.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/movies/ticketing")
    public ResponseEntity<Object> getMoviesByTicketing(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        try {
            String nowDate = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            Pageable pageable = PageRequest.of(page, size);

            Page<ResponseMovieByTicketingPerDto> list = service
                    .findScreeningMovieByTicketingPer(nowDate, pageable)
                    .map(dbFile -> {
                        String mPosterDownloadUri = ServletUriComponentsBuilder
                                .fromCurrentContextPath()
                                .path("/api/movie/movies/poster/")
                                .path(dbFile.getmid().toString())
                                .toUriString();
                        ResponseMovieByTicketingPerDto dto = modelMapper.map(dbFile, ResponseMovieByTicketingPerDto.class);
                        dto.setMPosterUri(mPosterDownloadUri);
                        return dto;
                    });
            Map<String, Object> response = new HashMap<>();
            response.put("movies", list.getContent());
            response.put("currentPage", list.getNumber());
            response.put("totalItems", list.getTotalElements());
            response.put("totalPages", list.getTotalPages());

            if (response.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/movies/notScreening/dday")
    public ResponseEntity<Object> getNotScreeningMoviesByDday(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        try {
            String nowDate = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            Pageable pageable = PageRequest.of(page, size);

            Page<ResponseMovieByDdayDto> list = service
                    .findNotScreeningMovieByDday(nowDate, pageable)
                    .map(dbFile -> {
                        String mPosterDownloadUri = ServletUriComponentsBuilder
                                .fromCurrentContextPath()
                                .path("/api/movie/movies/poster/")
                                .path(dbFile.getmid().toString())
                                .toUriString();
                        ResponseMovieByDdayDto dto = modelMapper.map(dbFile, ResponseMovieByDdayDto.class);
                        dto.setMPosterUri(mPosterDownloadUri);
                        return dto;
                    });
            Map<String, Object> response = new HashMap<>();
            response.put("movies", list.getContent());
            response.put("currentPage", list.getNumber());
            response.put("totalItems", list.getTotalElements());
            response.put("totalPages", list.getTotalPages());

            if (response.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/movies/notScreening/ticket")
    public ResponseEntity<Object> getNotScreeningMoviesByTicketting(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        try {
            String nowDate = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            Pageable pageable = PageRequest.of(page, size);

            Page<ResponseMovieByTicketingPerDto> list = service
                    .findNotScreeningMovieByTicketingPer(nowDate, pageable)
                    .map(dbFile -> {
                        String mPosterDownloadUri = ServletUriComponentsBuilder
                                .fromCurrentContextPath()
                                .path("/api/movie/movies/poster/")
                                .path(dbFile.getmid().toString())
                                .toUriString();
                        ResponseMovieByTicketingPerDto dto = modelMapper.map(dbFile, ResponseMovieByTicketingPerDto.class);
                        dto.setMPosterUri(mPosterDownloadUri);
                        return dto;
                    });
            Map<String, Object> response = new HashMap<>();
            response.put("movies", list.getContent());
            response.put("currentPage", list.getNumber());
            response.put("totalItems", list.getTotalElements());
            response.put("totalPages", list.getTotalPages());

            if (response.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
