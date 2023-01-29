package com.example.project_movie_backEnd.repository;

import com.example.project_movie_backEnd.model.Critics;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName : repository
 * fileName : CriticsRepository
 * author : L
 * date : 2022-12-13
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022-12-13         L          최초 생성
 */
@Repository
public interface CriticsRepository extends JpaRepository<Critics, Integer> {
}
