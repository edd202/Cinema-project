package com.example.project_movie_backEnd.dto.movie;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Lob;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMovieDto {
    private Integer mid;
    private String mtitle;
    private String mrunningTime;
    private String mcode;
    private String mreleaseDate;
    private String mclosingDate;
    private String mdirector;
    private String mactor;
    private String msimpleInfo;
    private String mtrailerLink;
    private String mage;


    private Integer mposterSize;
    private String mposterUrl;

    private Integer mimage1Size;
    private String mimage1Url;

    private Integer mimage2Size;
    private String mimage2Url;

    private Integer mimage3Size;
    private String mimage3Url;

    private Integer mimage4Size;
    private String mimage4Url;

    private Integer mimage5Size;
    private String mimage5Url;
}
