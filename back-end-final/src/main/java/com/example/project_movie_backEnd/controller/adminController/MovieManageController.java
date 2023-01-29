package com.example.project_movie_backEnd.controller.adminController;

import com.example.project_movie_backEnd.dto.admin.CineTheaterNameDto;
import com.example.project_movie_backEnd.dto.admin.MovieListDto;
import com.example.project_movie_backEnd.dto.movie.ResponseMovieDto;
import com.example.project_movie_backEnd.dto.reservation.CinemaDto;
import com.example.project_movie_backEnd.model.Movie;
import com.example.project_movie_backEnd.model.Qna;
import com.example.project_movie_backEnd.model.Schedule;
import com.example.project_movie_backEnd.service.adminService.MovieManageService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/admin")
public class MovieManageController {
    @Autowired
    MovieManageService service;
    ModelMapper modelMapper = new ModelMapper();

    // TODO : 영화와 영화정보는 하나의 객체나 마찬가지이므로, 같이 생성되고 같이 삭제되는 시퀀스로 진행됨.
    // TODO : 단, 영화정보의 수정은 티케팅과 평점에 의해서만 수정되게 설계(아직 미구현)되어있음 추후 수정컨트롤러가 필요하다면 그 떄 구현

    //    영화 생성 컨트롤러
    @PostMapping("/movie")
    public ResponseEntity<Object> createUploadFile(
            @RequestParam("mtitle") String mtitle,
            @RequestParam("mrunningTime") String mrunningTime,
            @RequestParam("mcode") String mcode,
            @RequestParam("mreleaseDate") String mreleaseDate,
            @RequestParam("mclosingDate") String mclosingDate,
            @RequestParam("mdirector") String mdirector,
            @RequestParam("mactor") String mactor,
            @RequestParam("msimpleInfo") String msimpleInfo,
            @RequestParam("mtrailerLink") String mtrailerLink,
            @RequestParam("mage") String mage,
            @RequestParam(defaultValue = "mposter") MultipartFile mposter,
            @RequestParam(defaultValue = "mimage1") MultipartFile mimage1,
            @RequestParam(defaultValue = "mimage2") MultipartFile mimage2,
            @RequestParam(defaultValue = "mimage3") MultipartFile mimage3,
            @RequestParam(defaultValue = "mimage4") MultipartFile mimage4,
            @RequestParam(defaultValue = "mimage5") MultipartFile mimage5
    ) {

        try {
            service.createUploadFile(mtitle, mrunningTime, mcode, mreleaseDate, mclosingDate,
                    mdirector, mactor, msimpleInfo, mtrailerLink, mage,
                    mposter, mimage1, mimage2, mimage3, mimage4, mimage5);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    영화 수정 처리 : image update 포함
    @PutMapping("/movie/{mid}")
    public ResponseEntity<Object> updateUploadFile(@PathVariable int mid,
                                                   @RequestParam("mtitle") String mtitle,
                                                   @RequestParam("mrunningTime") String mrunningTime,
                                                   @RequestParam("mcode") String mcode,
                                                   @RequestParam("mreleaseDate") String mreleaseDate,
                                                   @RequestParam("mclosingDate") String mclosingDate,
                                                   @RequestParam("mdirector") String mdirector,
                                                   @RequestParam("mactor") String mactor,
                                                   @RequestParam("msimpleInfo") String msimpleInfo,
                                                   @RequestParam("mtrailerLink") String mtrailerLink,
                                                   @RequestParam("mage") String mage,
                                                   @RequestParam("mposter") MultipartFile mposter,
                                                   @RequestParam("mimage1") MultipartFile mimage1,
                                                   @RequestParam("mimage2") MultipartFile mimage2,
                                                   @RequestParam("mimage3") MultipartFile mimage3,
                                                   @RequestParam("mimage4") MultipartFile mimage4,
                                                   @RequestParam("mimage5") MultipartFile mimage5) {
        String message = "";

        try {
            service.updateUploadFile(mid, mtitle, mrunningTime, mcode, mreleaseDate, mclosingDate,
                    mdirector, mactor, msimpleInfo, mtrailerLink, mage,
                    mposter, mimage1, mimage2, mimage3, mimage4, mimage5);

            message = "Uploaded the file successfully: ";
            return new ResponseEntity<Object>(message, HttpStatus.CREATED);
        } catch (Exception e) {
            message = "Could not upload the file!";
            return new ResponseEntity<Object>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //      파일 다운로드 함수  ----- poster 부터 이미지 1~5까지. 이게 있어야 화면에 이미지가 나타남
    @GetMapping("/movie/poster/{mid}")
    public ResponseEntity<byte[]> getPoster(@PathVariable int mid) {

//        id 로 조회 함수
        Movie movie = service.getMovie(mid).get();

//        첨부파일 다운로드 : url Content-Type 규칙
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + movie.getMposterFilename() + "\"")
                .body(movie.getMposter());
    }

    @GetMapping("/movie/image1/{mid}")
    public ResponseEntity<byte[]> getImage1(@PathVariable int mid) {

//        id 로 조회 함수
        Movie movie = service.getMovie(mid).get();

//        첨부파일 다운로드 : url Content-Type 규칙
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + movie.getMimageFilename1() + "\"")
                .body(movie.getMimage1());
    }

    @GetMapping("/movie/image2/{mid}")
    public ResponseEntity<byte[]> getImage2(@PathVariable int mid) {

//        id 로 조회 함수
        Movie movie = service.getMovie(mid).get();

//        첨부파일 다운로드 : url Content-Type 규칙
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + movie.getMimageFilename2() + "\"")
                .body(movie.getMimage2());
    }

    @GetMapping("/movie/image3/{mid}")
    public ResponseEntity<byte[]> getImage3(@PathVariable int mid) {

//        id 로 조회 함수
        Movie movie = service.getMovie(mid).get();

//        첨부파일 다운로드 : url Content-Type 규칙
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + movie.getMimageFilename3() + "\"")
                .body(movie.getMimage3());
    }

    @GetMapping("/movie/image4/{mid}")
    public ResponseEntity<byte[]> getImage4(@PathVariable int mid) {

//        id 로 조회 함수
        Movie movie = service.getMovie(mid).get();

//        첨부파일 다운로드 : url Content-Type 규칙
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + movie.getMimageFilename4() + "\"")
                .body(movie.getMimage4());
    }

    @GetMapping("/movie/image5/{mid}")
    public ResponseEntity<byte[]> getImage5(@PathVariable int mid) {

//        id 로 조회 함수
        Movie movie = service.getMovie(mid).get();

//        첨부파일 다운로드 : url Content-Type 규칙
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + movie.getMimageFilename5() + "\"")
                .body(movie.getMimage5());
    }

//      이미지 전체 포함 영화 테이블 불러오는 컨트롤러

    //    @GetMapping("/movie") // 영화테이블 불러오기 작동 확인 완료
//    public ResponseEntity<Object> getAllMovies(@RequestParam(required = false) String mtitle,
//                                               @RequestParam(defaultValue = "0") int page,
//                                               @RequestParam(defaultValue = "9") int size) {
//        try {
//            Pageable pageable = PageRequest.of(page, size);
//            Page<ResponseMovieDto> filePage = service
//                    .findAllByMtitleContaining(mtitle, pageable)
//                    .map(dbFile -> {
//
//                        String mPosterDownloadUri = ServletUriComponentsBuilder
//                                .fromCurrentContextPath()
//                                .path("/api/admin/movie/poster/")
//                                .path(dbFile.getMid().toString())
//                                .toUriString();
//
//                        String mImage1DownloadUri = ServletUriComponentsBuilder
//                                .fromCurrentContextPath()
//                                .path("/api/admin/movie/image1/")
//                                .path(dbFile.getMid().toString())
//                                .toUriString();
//
//                        String mImage2DownloadUri = ServletUriComponentsBuilder
//                                .fromCurrentContextPath()
//                                .path("/api/admin/movie/image2/")
//                                .path(dbFile.getMid().toString())
//                                .toUriString();
//
//                        String mImage3DownloadUri = ServletUriComponentsBuilder
//                                .fromCurrentContextPath()
//                                .path("/api/admin/movie/image3/")
//                                .path(dbFile.getMid().toString())
//                                .toUriString();
//
//                        String mImage4DownloadUri = ServletUriComponentsBuilder
//                                .fromCurrentContextPath()
//                                .path("/api/admin/movie/image4/")
//                                .path(dbFile.getMid().toString())
//                                .toUriString();
//
//                        String mImage5DownloadUri = ServletUriComponentsBuilder
//                                .fromCurrentContextPath()
//                                .path("/api/admin/movie/image5/")
//                                .path(dbFile.getMid().toString())
//                                .toUriString();
//
//                        ResponseMovieDto fileDto = modelMapper.map(dbFile,ResponseMovieDto.class);
//
//                        fileDto.setMposterUrl(mPosterDownloadUri);
//                        fileDto.setMimage1Url(mImage1DownloadUri);
//                        fileDto.setMimage2Url(mImage2DownloadUri);
//                        fileDto.setMimage3Url(mImage3DownloadUri);
//                        fileDto.setMimage4Url(mImage4DownloadUri);
//                        fileDto.setMimage5Url(mImage5DownloadUri);
//                        return fileDto;
//                    });
//
//            Map<String, Object> response = new HashMap<>();
//            response.put("movie", filePage.getContent());
//            response.put("currentPage", filePage.getNumber());
//            response.put("totalItems", filePage.getTotalElements());
//            response.put("totalPages", filePage.getTotalPages());
//
//            if (filePage.isEmpty() == false) {
//                return new ResponseEntity<>(response, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//        } catch (Exception e) {
//            log.debug(e.getMessage());
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


//    -----------------------  이미지없이 영화 테이블 불러오는 컨트롤러(제목 검색 포함)  -------------------------------------

    @GetMapping("/movie")
    public ResponseEntity<Object> getMovieListOrderByTitle(@RequestParam String mtitle,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<MovieListDto> filePage = service.findMoviewithoutImagesSearchTitle(mtitle, pageable);
            Map<String, Object> response = new HashMap<>();
            response.put("movie", filePage.getContent());
            response.put("currentPage", filePage.getNumber());
            response.put("totalItems", filePage.getTotalElements());
            response.put("totalPages", filePage.getTotalPages());

            if (filePage.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //      특정 영화(Mid) 불러오는 컨트롤러
    @GetMapping("/movie/{mid}")
    public ResponseEntity<Object> getMovieId(@PathVariable int mid) {
        try {
            //            Vue에서 전송한 매개변수 데이터 확인
            log.info("mid {}", mid);

            Optional<Movie> Optionalmovie = service.getMovie(mid);

            String mPosterDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/admin/movie/poster/")
                    .path(Optionalmovie.get().getMid().toString())
                    .toUriString();

            String mImage1DownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/admin/movie/image1/")
                    .path(Optionalmovie.get().getMid().toString())
                    .toUriString();

            String mImage2DownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/admin/movie/image2/")
                    .path(Optionalmovie.get().getMid().toString())
                    .toUriString();

            String mImage3DownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/admin/movie/image3/")
                    .path(Optionalmovie.get().getMid().toString())
                    .toUriString();

            String mImage4DownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/admin/movie/image4/")
                    .path(Optionalmovie.get().getMid().toString())
                    .toUriString();

            String mImage5DownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/admin/movie/image5/")
                    .path(Optionalmovie.get().getMid().toString())
                    .toUriString();

//                modelMapper 이용한 model to Dto 변환 ( 사전 라이브러리 설치 필요 )
            ResponseMovieDto responseMovieDto = modelMapper.map(Optionalmovie.get(), ResponseMovieDto.class);

//                아래 2개 속성은 가공된 데이터 이므로 setter 를 이용해 저장
            int mposterSize = (Optionalmovie.get().getMposter() != null) ? Optionalmovie.get().getMposter().length : 0;
            responseMovieDto.setMposterSize(mposterSize);
            responseMovieDto.setMposterUrl(mPosterDownloadUri);

            int mimage1Size = (Optionalmovie.get().getMimage1() != null) ? Optionalmovie.get().getMimage1().length : 0;
            responseMovieDto.setMimage1Size(mimage1Size);
            responseMovieDto.setMimage1Url(mImage1DownloadUri);

            int mimage2Size = (Optionalmovie.get().getMimage2() != null) ? Optionalmovie.get().getMimage2().length : 0;
            responseMovieDto.setMimage2Size(mimage2Size);
            responseMovieDto.setMimage2Url(mImage2DownloadUri);

            int mimage3Size = (Optionalmovie.get().getMimage3() != null) ? Optionalmovie.get().getMimage3().length : 0;
            responseMovieDto.setMimage3Size(mimage3Size);
            responseMovieDto.setMimage3Url(mImage3DownloadUri);

            int mimage4Size = (Optionalmovie.get().getMimage4() != null) ? Optionalmovie.get().getMimage4().length : 0;
            responseMovieDto.setMimage4Size(mimage4Size);
            responseMovieDto.setMimage4Url(mImage4DownloadUri);

            int mimage5Size = (Optionalmovie.get().getMimage5() != null) ? Optionalmovie.get().getMimage5().length : 0;
            responseMovieDto.setMimage5Size(mimage5Size);
            responseMovieDto.setMimage5Url(mImage5DownloadUri);

            if (Optionalmovie.isPresent()) {
                return new ResponseEntity<Object>(responseMovieDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/movie/delete/{mid}") //영화삭제 + 영화정보 삭제 -- 작동 확인 완료
    public ResponseEntity<Object> deleteMovie(@PathVariable int mid) {
        try {
            boolean bSuccess = service.removeByMid(mid);
            if (bSuccess == true) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // TODO : 영화스케쥴
    @GetMapping("/schedule/add") //작동 확인 완료
    public ResponseEntity<Object> getCinemaId() {
        try {
            List<CinemaDto> list = service.findCinema();
            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/schedule/add/{cid}") //작동 확인 완료
    public ResponseEntity<Object> getTheaterId(@PathVariable int cid) {
        try {
            List<CineTheaterNameDto> list = service.findTheater(cid);
            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/schedule/add") // 작동 확인 완료
    public ResponseEntity<Object> addMovieSchedule(@RequestBody Schedule schedule) {
        try {
            Schedule schedule1 = service.saveSchedule(schedule);
            return new ResponseEntity<>(schedule1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/schedule/edit/{scid}") // 작동 확인 완료
    public ResponseEntity<Object> editMovieSchedule(@PathVariable int scid, @RequestBody Schedule schedule) {
        try {
            Schedule schedule1 = service.saveSchedule(schedule);
            return new ResponseEntity<>(schedule1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/schedule/delete/{scid}") // 작동 확인 완료
    public ResponseEntity<Object> deleteMovieSchedule(@PathVariable int scid) {
        try {
            boolean bSuccess = service.removeScheduleByScid(scid);
            if (bSuccess) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/schedule") // 작동 확인 완료
    public ResponseEntity<Object> findAllSchedule() {
        try {
            List<Schedule> list = service.findAllSchedule();
            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/schedule/cinema/{cid}") // 작동 확인 완료
    public ResponseEntity<Object> findCinemaSchedule(@PathVariable int cid) {
        try {
            List<Schedule> list = service.findCinemaSchedule(cid);
            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/schedule/theater/{thid}") // 작동 확인 완료
    public ResponseEntity<Object> findTheaterSchedule(@PathVariable int thid) {
        try {
            List<Schedule> list = service.findTheaterSchedule(thid);
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
