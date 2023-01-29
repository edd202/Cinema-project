package com.example.project_movie_backEnd.dto.movie;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMovieByTicketingPerDto {
    private Float ticketingPers;
    private Integer mid;
    private String mtitle;
    private String mrunningTime;
    private Float rate;
    private String mcode;
    private String mreleaseDate;
    private String mclosingDate;
    private String mdirector;
    private String mactor;
    private String msimpleInfo;
    private String mtrailerLink;
    private String mage;
    private String mPosterUri;
    private Integer dday;
}
