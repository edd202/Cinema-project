package com.example.project_movie_backEnd.repository;

import com.example.project_movie_backEnd.dto.admin.userCountDto;
import com.example.project_movie_backEnd.dto.movie.AudienceReviewDto;
import com.example.project_movie_backEnd.model.Audience;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
public interface AudienceRepository extends JpaRepository<Audience, Integer> {

    @Query(value = "select * from tmp_audience where mid= :mid and DELETE_YN= 'N'",
            countQuery = "select count(*) from tmp_audience where mid= :mid and DELETE_YN= 'N'",
            nativeQuery = true)
    Page<Audience> findCriticBymid(@Param("mid") int mid, Pageable pageable);


    @Query(value = "select * from tmp_audience where memno= :memNo and DELETE_YN= 'N'",
            countQuery = "select count(*) from tmp_audience where memno= :memNo and DELETE_YN= 'N'",
            nativeQuery = true)
    Page<Audience> findCriticBymemNo(@Param("memNo") int memNo, Pageable pageable);

    @Query(value = "SELECT AUDNO, AURATE, AUREVIEW, MEM_ID as memId, MEM_NAME as memName, LIKE_COUNT as likeCount, INSERT_TIME as insertTime FROM TMP_AUDIENCE WHERE DELETE_YN='N' and MID= :mid ORDER BY LIKE_COUNT",
            countQuery = "SELECT COUNT(*) FROM TMP_AUDIENCE WHERE DELETE_YN='N' and MID= :mid ORDER BY LIKE_COUNT",
            nativeQuery = true)
    Page<AudienceReviewDto> findReviewOrderByLike(@Param("mid") int mid, Pageable pageable);

    @Query(value = "SELECT AUDNO, AURATE, AUREVIEW, MEM_ID as memId, MEM_NAME as memName, LIKE_COUNT as likeCount, INSERT_TIME as insertTime FROM TMP_AUDIENCE WHERE DELETE_YN='N' and MID= :mid ORDER BY INSERT_TIME",
            countQuery = "SELECT COUNT(*) FROM TMP_AUDIENCE WHERE DELETE_YN='N' and MID= :mid ORDER BY INSERT_TIME",
            nativeQuery = true)
    Page<AudienceReviewDto> findReviewOrderByInsertTime(@Param("mid") int mid, Pageable pageable);

    @Query(value = "SELECT COUNT(*) as userCount FROM TMP_AUDIENCE WHERE DELETE_YN='N' AND MID = :mid",
            nativeQuery = true)
    List<userCountDto> totalReviewCount(@Param("mid") int mid);


}