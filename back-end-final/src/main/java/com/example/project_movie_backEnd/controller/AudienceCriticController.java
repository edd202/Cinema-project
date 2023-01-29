package com.example.project_movie_backEnd.controller;

import com.example.project_movie_backEnd.model.Audience;
import com.example.project_movie_backEnd.model.Movie;
import com.example.project_movie_backEnd.model.MovieInfo;
import com.example.project_movie_backEnd.service.CriticService;
import com.example.project_movie_backEnd.service.MovieInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/movie")
public class AudienceCriticController {
    @Autowired
    CriticService service;
    @Autowired
    MovieInfoService movieInfoService;

    @PostMapping("audience/critic")
    public ResponseEntity<Object> saveAuCritic(@RequestBody Audience audience) {
        try {
            Audience audience1 = service.saveAuCritic(audience);
            int rate = audience1.getAurate();
            Optional<MovieInfo> infos = movieInfoService.movieInfoList(audience1.getMid());
            if(infos.isPresent()){
                infos.get().setMaudienceRate(infos.get().getMaudienceRate()+rate);
                movieInfoService.save(infos.get());
                return new ResponseEntity<>(audience1, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/movie/critic/{mid}")
    public ResponseEntity<Object> findAuCriticByMid(@PathVariable int mid,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            log.debug("111");
            Page<Audience> audiencePage = service.findAuCriticByMid(mid, pageable);
            log.debug("222");
            Map<String, Object> res = new HashMap<>();
            res.put("AudienceCritic", audiencePage.getContent());
            res.put("currentPage", audiencePage.getNumber());
            res.put("totalItems", audiencePage.getTotalElements());
            res.put("totalPages", audiencePage.getTotalPages());

            if (audiencePage.isEmpty() == false) {
                return new ResponseEntity<>(res, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/user/critic/{memNo}") // 유저가 내린 평점들
    public ResponseEntity<Object> findAuCriticBymemNo(@PathVariable int memNo,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Audience> audiencePage = service.findAuCriticBymemNo(memNo, pageable);
            Map<String, Object> res = new HashMap<>();
            res.put("AudienceCritic", audiencePage.getContent());
            res.put("currentPage", audiencePage.getNumber());
            res.put("totalItems", audiencePage.getTotalElements());
            res.put("totalPages", audiencePage.getTotalPages());

            if (audiencePage.isEmpty() == false) {
                return new ResponseEntity<>(res, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/user/delete/critic/{audno}")
    public ResponseEntity<Object> deleteCriticByaudNo(@PathVariable int audno){
        try {
            Optional<Audience> list = service.findbyId(audno);
            if(list.isPresent()){
                int audRate = list.get().getAurate();
                int mid = list.get().getMid();
                Optional<MovieInfo> movieInfo = movieInfoService.movieInfoList(mid);
                movieInfo.get().setMaudienceRate(movieInfo.get().getMaudienceRate()-audRate);
                movieInfoService.save(movieInfo.get());
            }
            boolean bSuccess = service.deleteAuCritic(audno);
            if (bSuccess) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
