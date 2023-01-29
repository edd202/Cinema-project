package com.example.project_movie_backEnd.dto.reservation;

public interface ReservationDto {
    Integer getthid();
    String getmtitle();
    String getmage();
    Integer getcid();
    String getthname();
    String getthtype();
    String getthdate();
    String getthtime();
    String getcinemaName();


    Integer gettotalSeat();
    Integer getleftSeat();
}
