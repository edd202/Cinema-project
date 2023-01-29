package com.example.project_movie_backEnd.service;

import com.example.project_movie_backEnd.model.Audience;
import com.example.project_movie_backEnd.model.Movie;
import com.example.project_movie_backEnd.repository.AudienceRepository;
import com.example.project_movie_backEnd.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CriticService {
    @Autowired
    AudienceRepository audienceRepository;

    public Audience saveAuCritic(Audience audience) {
        Audience audience1 = audienceRepository.save(audience);
        return audience1;
    }

    public boolean deleteAuCritic(int audno) {
        if (audienceRepository.existsById(audno)) {
//            audienceRepository.deleteCritic(audno);
            audienceRepository.deleteById(audno);
            return true;
        }
        return false;
    }

    public Page<Audience> findAuCriticByMid(int mid, Pageable pageable) {
        Page<Audience> list = audienceRepository.findCriticBymid(mid, pageable);
        return list;
    }

    public Page<Audience> findAuCriticBymemNo(int memNo, Pageable pageable) {
        Page<Audience> page = audienceRepository.findCriticBymemNo(memNo, pageable);
        return page;
    }

    public Optional<Audience> findbyId(int audno){
        Optional<Audience> list = audienceRepository.findById(audno);
        return list;
    }

}
