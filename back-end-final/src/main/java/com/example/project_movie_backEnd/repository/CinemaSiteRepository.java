package com.example.project_movie_backEnd.repository;

import com.example.project_movie_backEnd.dto.admin.CineTheaterNameDto;
import com.example.project_movie_backEnd.dto.reservation.CinemaDto;
import com.example.project_movie_backEnd.model.CinemaSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaSiteRepository extends JpaRepository<CinemaSite, Integer> {

    @Query(value = "SELECT CID as cid, CINEMA_NAME as cinemaname FROM TMP_CINEMA_SITE where DELETE_YN='N' ",nativeQuery = true)
    List<CinemaDto> findCinemasite();

    @Query(value = "select s.cid, t.thid, s.cinema_name, t.thname \n" +
            "from tmp_cinema_site s, tmp_theater t \n" +
            "where s.cid=t.cid and s.cid= :cid and s.DELETE_YN='N' and t.DELETE_YN='N'",
    nativeQuery = true)
    List<CineTheaterNameDto> findTheater(@Param("cid")int cid);

    List<CinemaSite> findAllByCinemaNameContaining(String cinemaName);
}
