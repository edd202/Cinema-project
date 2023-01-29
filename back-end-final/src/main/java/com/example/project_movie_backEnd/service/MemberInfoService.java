package com.example.project_movie_backEnd.service;

import com.example.project_movie_backEnd.model.MemberInfo;
import com.example.project_movie_backEnd.repository.MemberInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class MemberInfoService {
    @Autowired
    MemberInfoRepository repo; // 멤버정보 리포지토리

    public MemberInfo save(MemberInfo MemberInfo){ // 저장,수정용
        MemberInfo MemberInfo1 = repo.save(MemberInfo);
        return MemberInfo1;
    }
    public Optional<MemberInfo> findById(int memNo){ // 멤버의 id로 memberInfo테이블에서 추가정보를 불러옴
        Optional<MemberInfo> optional = repo.findById(memNo);
        return optional;
    }


}
