package com.example.project_movie_backEnd.dto.movie;

public interface MovieMainDto {

    // Movie 테이블 정보
    String getmid(); // 영화 넘버
    String getmtitle(); // 영화제목
    byte[] getmposter(); // blob

    // MovieInfo 테이블 정보
    Integer getmcumulativeAudience(); // 누적관객수
    Float getmcriticsRate(); // 평론가 평점 변경전 필드명 getmcriticRate()

    // SQL문을 이용해 만든 컬럼이름
    Float getaudiencesRate(); // 관객평점(누적관객수총량/누적관객평점)
    Integer getrank(); // 예매율 순위
    Float getticketingPer(); // 예매비율
    Integer getdday(); // 개봉일까지 남은 기간


}
