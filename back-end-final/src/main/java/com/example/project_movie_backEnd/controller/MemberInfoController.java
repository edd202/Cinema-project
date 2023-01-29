package com.example.project_movie_backEnd.controller;

import com.example.project_movie_backEnd.model.MemberInfo;
import com.example.project_movie_backEnd.service.MemberInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/movie")
public class MemberInfoController {
    @Autowired
    MemberInfoService memberInfoService;

    @GetMapping("/Memberinfo/{memNo}")//멤버 no로 검색(로그인용 아이디 x 고유번호 o)
    public ResponseEntity<Object> getMemberId(@PathVariable int memNo) {
        try {
            Optional<MemberInfo> optionalMember = memberInfoService.findById(memNo);
            if (optionalMember.isPresent()) {
                return new ResponseEntity<>(optionalMember.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/Memberinfo")
    public ResponseEntity<Object> createMember(@RequestBody MemberInfo memberInfo) {
        try {
            MemberInfo Member2 = memberInfoService.save(memberInfo);
            return new ResponseEntity<>(Member2, HttpStatus.OK);
        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/Memberinfo/{memNo}")
    public ResponseEntity<Object> updateMember(@PathVariable int memNo,
                                               @RequestBody MemberInfo memberInfo) {
        try {
            MemberInfo memberInfo1 = memberInfoService.save(memberInfo);
            return new ResponseEntity<>(memberInfo1, HttpStatus.OK);
        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

