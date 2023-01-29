package com.example.project_movie_backEnd.repository;



import com.example.project_movie_backEnd.model.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
public interface NoticeRepository extends JpaRepository<Notice, Integer> {
    Page<Notice> findAllByNtcTitleContainingOrderByNtcNoDesc(String NtcTitle, Pageable pageable);

    @Query(value = "select * from tmp_notice where ntc_title like %:title% and DELETE_YN='N'",
            countQuery = "select count(*) from tmp_notice where ntc_title like %:title% and DELETE_YN='N'",
            nativeQuery = true)
    Page<Notice> findNoticeByTitle(@Param("title") String title, Pageable pageable);


}
