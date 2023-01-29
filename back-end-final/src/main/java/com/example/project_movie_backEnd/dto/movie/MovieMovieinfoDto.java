package com.example.project_movie_backEnd.dto.movie;

public interface MovieMovieinfoDto {
    //Movie 테이블
    Integer getmid(); //number pk
    String getmtitle(); // varchar2(255) not null
    String getmcode(); // varchar2(255)
    String getmreleaseDate(); // varchar2(255)

    String getmdirector(); // varchar2(255)
    String getmactor(); // varchar2(255)

    String getmrunningTime(); // varchar2(255)
    String getmsimpleInfo(); // varchar2(4000)
    String getmtrailerLink(); // varchar2(4000)
    String getmage();  // varchar2(30)
    byte[] getmposter(); // blob
    byte[] getmimage1(); // blob
    byte[] getmimage2(); // blob
    byte[] getmimage3(); // blob
    byte[] getmimage4(); // blob
    byte[] getmimage5(); // blob

    //MovieInfo 테이블
    Integer getmcumulativeAudience(); // number
    Float getmcriticsRate(); // number

    //SQL로 만든컬럼
    Float getaudiencesRate(); // 관람객 평점
    Integer getrank(); // 예매율 순위
    Float getticketingPer(); // 예매비율
}