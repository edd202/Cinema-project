package com.example.project_movie_backEnd.controller;

import com.example.project_movie_backEnd.model.Member;
import com.example.project_movie_backEnd.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/movie")
public class MemberController {
    @Autowired
    MemberService memberService; //멤버 서비스 연결

    @GetMapping("/Member/{memNo}")//멤버 no로 검색(로그인용 아이디 x 고유번호 o)
    public ResponseEntity<Object> getMemberId(@PathVariable int memNo) {
        try {
            Optional<Member> optionalMember = memberService.findById(memNo);
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

    @PostMapping("/Member")
    public ResponseEntity<Object> createMember(@RequestBody Member Member) {
        try {
            Member Member2 = memberService.save(Member);
            return new ResponseEntity<>(Member2, HttpStatus.OK);
        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/Member/{memNo}")
    public ResponseEntity<Object> updateMember(@PathVariable int memNo,
                                             @RequestBody Member Member) {
        try {
            Member Member2 = memberService.save(Member);
            return new ResponseEntity<>(Member2, HttpStatus.OK);
        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
