package com.example.project_movie_backEnd.dto.reservation;

public interface TheaterDto {
//    select DISTINCT t.thid, s.mtitle, s.mage, c.cid
//    from tmp_cinema_site c, tmp_theater t, tmp_schedule s
//    where c.cid=t.cid and t.thid=s.thid and c.cid=1;

    String getmtitle();
    String getmage();
    String getcid();

}
