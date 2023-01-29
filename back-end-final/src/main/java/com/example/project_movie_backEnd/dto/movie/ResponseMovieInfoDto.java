package com.example.project_movie_backEnd.dto.movie;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMovieInfoDto {
    private Integer mid;
    private String mtitle;
    private String mcode;
    private String mreleaseDate;
    private String mdirector;
    private String mactor;

    private String mrunningTime;
    private String msimpleInfo;
    private String mtrailerLink;
    private String mage;
    private String mposter;
    private String mimage1;
    private String mimage2;
    private String mimage3;
    private String mimage4;
    private String mimage5;

    private Integer mcumulativeAudience; // 누적관객수
    private Float mcriticsRate; // 평론가 평점

    // SQL 함수를 이용해 만든 컬럼이름
    private Float audiencesRate; // 관객평점(누적관객수총량/누적관객평점)
    private Integer rank; // 예매율 순위
    private Float ticketingPer; // 예매비율

}
