package com.example.project_movie_backEnd.repository;

import com.example.project_movie_backEnd.dto.reservation.CinemaTheaterDto;
import com.example.project_movie_backEnd.dto.reservation.TheaterDto;
import com.example.project_movie_backEnd.dto.reservation.TheaterScheduleDto;
import com.example.project_movie_backEnd.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Integer> {
    @Query(value = "select DISTINCT s.mtitle as mtitle, s.mage as mage, c.cid as cid\n" +
            "            from tmp_cinema_site c, tmp_theater t, tmp_schedule s\n" +
            "            where c.cid=t.cid and t.thid=s.thid and c.cid= :cineId and c.DELETE_YN= 'N'\n" +
            "            group by s.mtitle, s.mage, c.cid",
            nativeQuery = true)
    List<TheaterDto> findMoviebyCinema(@Param("cineId") int cid);

    @Query(value = "SELECT s.thid as thid, s.mtitle as mtitle, m.mage as mage, t.thname as thname, t.thtype as thtype, s.thdate as thdate, s.thtime as thtime, m.mclosing_date as mclosingDate, \n" +
            "             NVL(LENGTH(REPLACE(s.SEAT_A,'A','')),0) + NVL(LENGTH(REPLACE(s.SEAT_B,'A','')),0) + NVL(LENGTH(REPLACE(s.SEAT_C,'A','')),0) +\n" +
            "             NVL(LENGTH(REPLACE(s.SEAT_D,'A','')),0) + NVL(LENGTH(REPLACE(s.SEAT_E,'A','')),0) + NVL(LENGTH(REPLACE(s.SEAT_F,'A','')),0) +\n" +
            "             NVL(LENGTH(REPLACE(s.SEAT_G,'A','')),0) + NVL(LENGTH(REPLACE(s.SEAT_H,'A','')),0) + NVL(LENGTH(REPLACE(s.SEAT_I,'A','')),0) +\n" +
            "             NVL(LENGTH(REPLACE(s.SEAT_J,'A','')),0) + NVL(LENGTH(REPLACE(s.SEAT_K,'A','')),0) + NVL(LENGTH(REPLACE(s.SEAT_L,'A','')),0) +\n" +
            "             NVL(LENGTH(REPLACE(s.SEAT_M,'A','')),0) + NVL(LENGTH(REPLACE(s.SEAT_N,'A','')),0) + NVL(LENGTH(REPLACE(s.SEAT_O,'A','')),0) +\n" +
            "             NVL(LENGTH(REPLACE(s.SEAT_P,'A','')),0) AS totalSeat,\n" +
            "             NVL(REGEXP_COUNT(s.SEAT_A,'O'),0) + NVL(REGEXP_COUNT(s.SEAT_B,'O'),0) + NVL(REGEXP_COUNT(s.SEAT_C,'O'),0) + NVL(REGEXP_COUNT(s.SEAT_D,'O'),0) +\n" +
            "             NVL(REGEXP_COUNT(s.SEAT_E,'O'),0) + NVL(REGEXP_COUNT(s.SEAT_F,'O'),0) + NVL(REGEXP_COUNT(s.SEAT_G,'O'),0) + NVL(REGEXP_COUNT(s.SEAT_H,'O'),0) +\n" +
            "             NVL(REGEXP_COUNT(s.SEAT_I,'O'),0) + NVL(REGEXP_COUNT(s.SEAT_J,'O'),0) + NVL(REGEXP_COUNT(s.SEAT_K,'O'),0) + NVL(REGEXP_COUNT(s.SEAT_L,'O'),0) +\n" +
            "             NVL(REGEXP_COUNT(s.SEAT_M,'O'),0) + NVL(REGEXP_COUNT(s.SEAT_N,'O'),0) + NVL(REGEXP_COUNT(s.SEAT_O,'O'),0) + NVL(REGEXP_COUNT(s.SEAT_P,'O'),0) AS leftSeat\n" +
            "from tmp_movie m, tmp_cinema_site c, tmp_theater t, tmp_schedule s\n" +
            "where m.mtitle = s.MTITLE and c.cid=t.cid and t.thid=s.thid and s.mtitle = :movieTitle and c.cid= :cinemaId and m.DELETE_YN= 'N' and c.DELETE_YN= 'N' and t.DELETE_YN= 'N' and s.DELETE_YN= 'N' \n"+
            "order by  s.thdate , s.thtime",
            nativeQuery = true)
    List<TheaterScheduleDto> findMovieSchedulebyMovieName(@Param("movieTitle") String movieTitle, @Param("cinemaId") int cinemaId);

    @Query(value = "select  c.cinema_name as cinemaName, t.thname as thname \n" +
            "from tmp_cinema_site c, tmp_theater t\n" +
            "where c.cid = t.cid and t.thid= :TheaterId and c.DELETE_YN= 'N' and t.DELETE_YN= 'N' ",
            nativeQuery = true)
    List<CinemaTheaterDto> findMovieLocatebyTheaterId(@Param("TheaterId")int id);

    boolean existsByCid(int cid);

    List<Theater> findAllByCidContaining(Integer cid);

    @Query(value = "select * from tmp_theater where cid like %:cid% and DELETE_YN= 'N'"
            ,nativeQuery = true)
    List<Theater> findByCid(@Param("cid") Integer cid);

    @Modifying
    @Query(value = "delete from tmp_theater where thid = :thid and DELETE_YN= 'N'",
            nativeQuery = true)
    void delScheduleByThid(@Param("thid") int thid);

    @Query(value = "select * from tmp_theater where cid= :cid and thname= :thname",
    nativeQuery = true)
    List<Theater> findThid(@Param("cid")int cid, @Param("thname")String thname);
}
