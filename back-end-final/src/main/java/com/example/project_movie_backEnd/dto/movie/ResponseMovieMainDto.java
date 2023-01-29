package com.example.project_movie_backEnd.dto.movie;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMovieMainDto {
    private String mid; // 영화 넘버
    private String mtitle; // 영화제목
    private String mposter; // 포스터 uri

    // MovieInfo 테이블 정보
    private Integer mcumulativeAudience; // 누적관객수
    private Float mcriticsRate; // 평론가 평점 변경전 필드명 mcriticRate

    // SQL문을 이용해 만든 컬럼이름
    private Float audiencesRate; // 관객평점(누적관객수총량/누적관객평점)
    private Integer rank; // 예매율 순위
    private Float ticketingPer; // 예매비율
    private Integer dday; // 개봉일까지 남은 기간
}
