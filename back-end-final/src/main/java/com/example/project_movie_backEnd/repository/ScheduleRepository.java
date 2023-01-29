package com.example.project_movie_backEnd.repository;

import com.example.project_movie_backEnd.model.Schedule;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    @Query(value = "select s.* from tmp_schedule s where s.thid= :thId and s.thdate= :thDate and s.thtime= :thTime and s.DELETE_YN= 'N' ",
            countQuery = "select count(*) from tmp_schedule s where s.thid= :thId and s.thdate= :thDate and s.thtime= :thTime and s.DELETE_YN= 'N' ",
            nativeQuery = true)
    Page<Schedule> findMovieSeatbyMovieSchedule(@Param("thId") int thid, @Param("thDate") String thdate, @Param("thTime") String thtime, Pageable pageable);

    @Query(value = "select s.* from tmp_schedule s where s.scid= :SchdeduleId",
            nativeQuery = true)
    List<Schedule> findSeatReserved(@Param("SchdeduleId") int scid);

//    @Modifying
//    @Transactional
//    @Query(value = "UPDATE TMP_SCHEDULE SET :seatColumn = :seatNum  WHERE SCID = :scid",
//    nativeQuery = true)
//    int seatReserve(@Param("seatColumn")String seatColumn, @Param("seatNum")String seatNum);

    @Query(value = "select s.* \n" +
            "from tmp_theater t, tmp_cinema_site c, tmp_schedule s \n" +
            "where s.thid=t.thid and c.cid=t.cid and t.cid= :cid  and t.DELETE_YN='N' and c.DELETE_YN='N' and s.DELETE_YN='N'",
            nativeQuery = true)
    List<Schedule> findCinemaSchedule(@Param("cid") int cid);

    @Query(value = "select s.* \n" +
            "from tmp_theater t, tmp_schedule s \n" +
            "where s.thid=t.thid  and t.thid= :thid  and t.DELETE_YN='N' and s.DELETE_YN='N'",
            nativeQuery = true)
    List<Schedule> findTheaterSchedule(@Param("thid") int thid);

    boolean existsByThid(int thid);

    @Modifying
    @Query(value = "delete from tmp_schedule where thid = :thid and DELETE_YN='N'",
            nativeQuery = true)
    void delByThid(@Param("thid") int thid);

    @Query(value = "select * from tmp_schedule where thid = :thid and mtitle= :Title and thdate= :thdate and thtime= :thtime",
            nativeQuery = true)
    List<Schedule> findMovieRevokingData(@Param("thid")int thid,@Param("Title") String mtitle, @Param("thdate") String thdate, @Param("thtime") String thtime);
}
