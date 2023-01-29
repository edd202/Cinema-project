package com.example.project_movie_backEnd.service;

import com.example.project_movie_backEnd.model.Member;
import com.example.project_movie_backEnd.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;

/**
 * packageName : service
 * fileName : MemberService
 * author : L
 * date : 2022-12-13
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022-12-13         L          최초 생성
 */
@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    public Member save(Member member){
        Member member1 = memberRepository.save(member);
        return member1;
    }

    public Optional<Member> findById(int memNo){
        Optional<Member> optional = memberRepository.findById(memNo);
        return optional;
    }

    public boolean removeById(int memNo){
        if(memberRepository.existsById(memNo)==true){
            memberRepository.deleteById(memNo);
            return  true;
        }
        return false;
    }


}
