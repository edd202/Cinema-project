package com.example.project_movie_backEnd.repository;

import com.example.project_movie_backEnd.dto.admin.MovieListDto;
import com.example.project_movie_backEnd.dto.movie.*;
import com.example.project_movie_backEnd.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName : repository
 * fileName : MovieRepository
 * author : L
 * date : 2022-12-13
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022-12-13         L          최초 생성
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    // 영화 첫 페이지에서 보여줄 상영중인 영화들(개봉작)     TODO : 작동 테스트 완료
    @Query(value = "select * from (select m.mid, m.mtitle , \n" +
            "    i.MCUMULATIVE_AUDIENCE as mcumulativeAudience, \n" +
            "    case when i.maudience_rate = 0 or i.MCUMULATIVE_AUDIENCE =0 then 0 else  round(i.maudience_rate / i.MCUMULATIVE_AUDIENCE,2) end as audiencesRate , \n" +
            "    i.MCRITIC_RATE as mcriticsRate,\n" +
            "    RANK() OVER (ORDER BY i.MCUMULATIVE_AUDIENCE  DESC) as rank,\n" +
            "    case when i.MCUMULATIVE_AUDIENCE = 0 or i.MCUMULATIVE_AUDIENCE = 0 then 0 else round(to_binary_float(i.MCUMULATIVE_AUDIENCE) / (select sum(i.MCUMULATIVE_AUDIENCE) from tmp_movie_info i)*100,2) end as ticketingPer ,\n" +
            "    round(TO_DATE(m.MRELEASE_DATE,'YYYYMMDD')-sysdate,0) as dday\n" +
            "from tmp_movie m, tmp_movie_info i\n" +
            "where m.mid = i.mid and mrelease_date <= :nowDate and MCLOSING_DATE > :nowDate and m.DELETE_YN='N' and i.DELETE_YN='N')\n" +
            "where rank<=:mRank",
            countQuery = "select count(*) from (select m.mid, m.mtitle , \n" +
                    "   i.MCUMULATIVE_AUDIENCE as mcumulativeAudience, \n" +
                    "   case when i.maudience_rate = 0 or i.MCUMULATIVE_AUDIENCE =0 then 0 else  round(i.maudience_rate / i.MCUMULATIVE_AUDIENCE,2) end as audiencesRate , \n" +
                    "   i.MCRITIC_RATE as mcriticsRate,\n" +
                    "   RANK() OVER (ORDER BY i.MCUMULATIVE_AUDIENCE  DESC) as rank,\n" +
                    "   case when i.MCUMULATIVE_AUDIENCE = 0 or i.MCUMULATIVE_AUDIENCE = 0 then 0 else round(to_binary_float(i.MCUMULATIVE_AUDIENCE) / (select sum(i.MCUMULATIVE_AUDIENCE) from tmp_movie_info i)*100,2) end as ticketingPer ,\n" +
                    "   round(TO_DATE(m.MRELEASE_DATE,'YYYYMMDD')- sysdate,0) as dday\n" +
                    "   from tmp_movie m, tmp_movie_info i\n" +
                    "   where m.mid = i.mid and mrelease_date <= :nowDate and MCLOSING_DATE > :nowDate and m.DELETE_YN='N' and i.DELETE_YN='N')\n" +
                    "where rank<=:mRank",
            nativeQuery = true)
    Page<MovieMainDto> findMainScreeningListV2(@Param("mRank") int Rank, @Param("nowDate") String nowDate, Pageable pageable);

    // 영화 첫 페이지에서 보여줄 상영예정 영화들(개봉예정작)     TODO : 작동 테스트 완료
    @Query(value = "select * from (select m.mid, m.mtitle , \n" +
            "    i.MCUMULATIVE_AUDIENCE as mcumulativeAudience, \n" +
            "    case when i.maudience_rate = 0 or i.MCUMULATIVE_AUDIENCE =0 then 0 else  round(i.maudience_rate / i.MCUMULATIVE_AUDIENCE,2) end as audiencesRate , \n" +
            "    i.MCRITIC_RATE as mcriticsRate,\n" +
            "    RANK() OVER (ORDER BY i.MCUMULATIVE_AUDIENCE  DESC) as rank,\n" +
            "    case when i.MCUMULATIVE_AUDIENCE = 0 or i.MCUMULATIVE_AUDIENCE = 0 then 0 else round(to_binary_float(i.MCUMULATIVE_AUDIENCE) / (select sum(i.MCUMULATIVE_AUDIENCE) from tmp_movie_info i)*100,2) end as ticketingPer ,\n" +
            "    round(TO_DATE(m.MRELEASE_DATE,'YYYYMMDD')- sysdate,0) as dday\n" +
//            "    m.mposter, round(TO_DATE(m.MRELEASE_DATE,'YYYYMMDD')- sysdate,0) as dday\n" +
            "from tmp_movie m, tmp_movie_info i\n" +
            "where m.mid = i.mid and mrelease_date > :nowDate and m.DELETE_YN='N' and i.DELETE_YN='N')\n" +
            "where rank<=:mRank",
            countQuery = "select count(*) from (select m.mid, m.mtitle , \n" +
                    "   i.MCUMULATIVE_AUDIENCE as mcumulativeAudience, \n" +
                    "   case when i.maudience_rate = 0 or i.MCUMULATIVE_AUDIENCE =0 then 0 else  round(i.maudience_rate / i.MCUMULATIVE_AUDIENCE,2) end as audiencesRate , \n" +
                    "   i.MCRITIC_RATE as mcriticsRate,\n" +
                    "   RANK() OVER (ORDER BY i.MCUMULATIVE_AUDIENCE  DESC) as rank,\n" +
                    "   case when i.MCUMULATIVE_AUDIENCE = 0 or i.MCUMULATIVE_AUDIENCE = 0 then 0 else round(to_binary_float(i.MCUMULATIVE_AUDIENCE) / (select sum(i.MCUMULATIVE_AUDIENCE) from tmp_movie_info i)*100,2) end as ticketingPer ,\n" +
                    "   round(TO_DATE(m.MRELEASE_DATE,'YYYYMMDD')- sysdate,0) as dday\n" +
                    "   from tmp_movie m, tmp_movie_info i\n" +
                    "   where m.mid = i.mid and mrelease_date > :nowDate and m.DELETE_YN='N' and i.DELETE_YN='N')\n" +
                    "where rank<=:mRank",
            nativeQuery = true)
    Page<MovieMainDto> findMainNotScreeningListV2(@Param("mRank") int Rank, @Param("nowDate") String nowDate, Pageable pageable);

    // 현재 상영중인 영화 페이지      TODO : 작동 테스트 완료
    @Query(value = "select m.mid, m.mtitle , \n" +
            "i.MCUMULATIVE_AUDIENCE as mcumulativeAudience, \n" +
            "case when i.maudience_rate = 0 or i.MCUMULATIVE_AUDIENCE =0 then 0 else  round(i.maudience_rate / i.MCUMULATIVE_AUDIENCE,2) end as audiencesRate , \n" +
            "i.MCRITIC_RATE as mcriticsRate, \n" +
            "RANK() OVER (ORDER BY i.MCUMULATIVE_AUDIENCE  DESC) as rank, \n" +
            "case when i.MCUMULATIVE_AUDIENCE = 0 or i.MCUMULATIVE_AUDIENCE = 0 then 0 else round(to_binary_float(i.MCUMULATIVE_AUDIENCE) / (select sum(i.MCUMULATIVE_AUDIENCE) from tmp_movie_info i)*100,2) end as ticketingPer, \n" +
            "round(TO_DATE(m.MRELEASE_DATE,'YYYYMMDD')- sysdate,0) as dday \n" +
            "from tmp_movie m, tmp_movie_info i \n" +
            "where m.mid = i.mid and m.mclosing_date > :nowDate and m.mrelease_date < :nowDate and m.DELETE_YN='N' and i.DELETE_YN='N' \n" +
            "order by m.MRELEASE_DATE",
            countQuery = "select count(*) \n" +
                    "from tmp_movie m, tmp_movie_info i \n" +
                    "where m.mid = i.mid and m.mclosing_date > :nowDate and m.mrelease_date < :nowDate and m.DELETE_YN='N' and i.DELETE_YN='N' \n" +
                    "order by m.MRELEASE_DATE",
            nativeQuery = true)
    Page<MovieMainDto> findScreeningList(@Param("nowDate") String nowDate, Pageable pageable);

    // 상영 예정중인 영화 페이지      TODO : 작동 테스트 완료
    @Query(value = "select m.mid, m.mtitle , \n" +
            "i.MCUMULATIVE_AUDIENCE as mcumulativeAudience, \n" +
            "case when i.maudience_rate = 0 or i.MCUMULATIVE_AUDIENCE =0 then 0 else  round(i.maudience_rate / i.MCUMULATIVE_AUDIENCE,2) end as audiencesRate ,\n" +
            "i.MCRITIC_RATE as mcriticsRate, \n" +
            "RANK() OVER (ORDER BY i.MCUMULATIVE_AUDIENCE  DESC) as rank, \n" +
            "case when i.MCUMULATIVE_AUDIENCE = 0 or i.MCUMULATIVE_AUDIENCE = 0 then 0 else round(to_binary_float(i.MCUMULATIVE_AUDIENCE) / (select sum(i.MCUMULATIVE_AUDIENCE) from tmp_movie_info i)*100,2) end as ticketingPer, \n" +
            "round(TO_DATE(m.MRELEASE_DATE,'YYYYMMDD')- sysdate,0) as dday \n" +
            "from tmp_movie m, tmp_movie_info i \n" +
            "where m.mid = i.mid and m.mclosing_date > :nowDate and m.mrelease_date > :nowDate and m.DELETE_YN='N' and i.DELETE_YN='N' \n" +
            "order by m.MRELEASE_DATE",
            countQuery = "select count(*) \n" +
                    "from tmp_movie m, tmp_movie_info i \n" +
                    "where m.mid = i.mid and m.mclosing_date > :nowDate and m.mrelease_date > :nowDate and m.DELETE_YN='N' and i.DELETE_YN='N' \n" +
                    "order by m.MRELEASE_DATE",
            nativeQuery = true)
    Page<MovieMainDto> findNotScreeningList(@Param("nowDate") String nowDate, Pageable pageable);



    @Query(value = "select m.mid, m.mtitle , \n" +
            "i.MCUMULATIVE_AUDIENCE as mcumulativeAudience, \n" +
            "case when i.maudience_rate = 0 or i.MCUMULATIVE_AUDIENCE =0 then 0 else  round(i.maudience_rate / i.MCUMULATIVE_AUDIENCE,2) end as audiencesRate ,\n" +
            "i.MCRITIC_RATE as mcriticsRate,\n" +
            "RANK() OVER (ORDER BY i.MCUMULATIVE_AUDIENCE  DESC) as rank,\n" +
            "case when i.MCUMULATIVE_AUDIENCE = 0 or i.MCUMULATIVE_AUDIENCE = 0 then 0 else round(to_binary_float(i.MCUMULATIVE_AUDIENCE) / (select sum(i.MCUMULATIVE_AUDIENCE) from tmp_movie_info i)*100,2) end as ticketingPer ,\n" +
            "round(TO_DATE(m.MRELEASE_DATE,'YYYYMMDD')- sysdate,0) as dday\n" +
            "from tmp_movie m, tmp_movie_info i\n" +
            "where m.mid = i.mid and m.mtitle like %:mTitle%  and m.DELETE_YN='N' and i.DELETE_YN='N'\n" +
            "order by m.MRELEASE_DATE",
    countQuery = "select count(*) \n" +
            "from tmp_movie m, tmp_movie_info i \n" +
            "where m.mid = i.mid and m.mtitle like %:mTitle%  and m.DELETE_YN='N' and i.DELETE_YN='N'\n" +
            "order by m.MRELEASE_DATE",
    nativeQuery = true)
    Page<MovieMainDto> findByMovieTitle(@Param("mTitle")String mTitle, Pageable pageable);

    @Query(value = "select m.mid, m.mtitle , \n" +
            "i.MCUMULATIVE_AUDIENCE as mcumulativeAudience, \n" +
            "case when i.maudience_rate = 0 or i.MCUMULATIVE_AUDIENCE =0 then 0 else  round(i.maudience_rate / i.MCUMULATIVE_AUDIENCE,2) end as audiencesRate , \n" +
            "i.MCRITIC_RATE as mcriticsRate,\n" +
            "RANK() OVER (ORDER BY i.MCUMULATIVE_AUDIENCE  DESC) as rank,\n" +
            "case when i.MCUMULATIVE_AUDIENCE = 0 or i.MCUMULATIVE_AUDIENCE = 0 then 0 else round(to_binary_float(i.MCUMULATIVE_AUDIENCE) / (select sum(i.MCUMULATIVE_AUDIENCE) from tmp_movie_info i)*100,2) end as ticketingPer ,\n" +
            "round(TO_DATE(m.MRELEASE_DATE,'YYYYMMDD')- sysdate,0) as dday\n" +
            "from tmp_movie m, tmp_movie_info i\n" +
            "where m.mid = i.mid and m.mcode like %:mCode%  and m.DELETE_YN='N' and i.DELETE_YN='N'\n" +
            "order by m.MRELEASE_DATE",
    countQuery = "select count(*) \n" +
            "from tmp_movie m, tmp_movie_info i \n" +
            "where m.mid = i.mid and m.mcode like %:mCode%  and m.DELETE_YN='N' and i.DELETE_YN='N'\n" +
            "order by m.MRELEASE_DATE",
    nativeQuery = true)
    Page<MovieMainDto> findByMovieCode(@Param("mCode")String mCode, Pageable pageable);

    @Query(value = "select m.mid, m.mtitle , \n" +
            "i.MCUMULATIVE_AUDIENCE as mcumulativeAudience, \n" +
            "case when i.maudience_rate = 0 or i.MCUMULATIVE_AUDIENCE =0 then 0 else  round(i.maudience_rate / i.MCUMULATIVE_AUDIENCE,2) end as audiencesRate , \n" +
            "i.MCRITIC_RATE as mcriticsRate,\n" +
            "RANK() OVER (ORDER BY i.MCUMULATIVE_AUDIENCE  DESC) as rank,\n" +
            "case when i.MCUMULATIVE_AUDIENCE = 0 or i.MCUMULATIVE_AUDIENCE = 0 then 0 else round(to_binary_float(i.MCUMULATIVE_AUDIENCE) / (select sum(i.MCUMULATIVE_AUDIENCE) from tmp_movie_info i)*100,2) end as ticketingPer ,\n" +
            "round(TO_DATE(m.MRELEASE_DATE,'YYYYMMDD')- sysdate,0) as dday\n" +
            "from tmp_movie m, tmp_movie_info i\n" +
            "where m.mid = i.mid and m.mdirector like %:mDirector%  and m.DELETE_YN='N' and i.DELETE_YN='N'\n" +
            "order by m.MRELEASE_DATE",
            countQuery = "select count(*) \n" +
                    "from tmp_movie m, tmp_movie_info i \n" +
                    "where m.mid = i.mid and m.mdirector like %:mDirector%  and m.DELETE_YN='N' and i.DELETE_YN='N'\n" +
                    "order by m.MRELEASE_DATE",
            nativeQuery = true)
    Page<MovieMainDto> findByMovieDirector(@Param("mDirector")String mDirector, Pageable pageable);

    @Query(value = "select m.mid, m.mtitle , \n" +
            "i.MCUMULATIVE_AUDIENCE as mcumulativeAudience, \n" +
            "case when i.maudience_rate = 0 or i.MCUMULATIVE_AUDIENCE =0 then 0 else  round(i.maudience_rate / i.MCUMULATIVE_AUDIENCE,2) end as audiencesRate , \n" +
            "i.MCRITIC_RATE as mcriticsRate,\n" +
            "RANK() OVER (ORDER BY i.MCUMULATIVE_AUDIENCE  DESC) as rank,\n" +
            "case when i.MCUMULATIVE_AUDIENCE = 0 or i.MCUMULATIVE_AUDIENCE = 0 then 0 else round(to_binary_float(i.MCUMULATIVE_AUDIENCE) / (select sum(i.MCUMULATIVE_AUDIENCE) from tmp_movie_info i)*100,2) end as ticketingPer ,\n" +
            "round(TO_DATE(m.MRELEASE_DATE,'YYYYMMDD')- sysdate,0) as dday\n" +
            "from tmp_movie m, tmp_movie_info i\n" +
            "where m.mid = i.mid and m.mactor like %:mActor%  and m.DELETE_YN='N' and i.DELETE_YN='N'\n" +
            "order by m.MRELEASE_DATE",
            countQuery = "select count(*) \n" +
                    "from tmp_movie m, tmp_movie_info i \n" +
                    "where m.mid = i.mid and m.mactor like %:mActor%  and m.DELETE_YN='N' and i.DELETE_YN='N'\n" +
                    "order by m.MRELEASE_DATE",
            nativeQuery = true)
    Page<MovieMainDto> findByMovieActor(@Param("mActor")String mActor, Pageable pageable);

    @Query(value = "select m.mid, m.mtitle , \n" +
            "i.MCUMULATIVE_AUDIENCE as mcumulativeAudience, \n" +
            "case when i.maudience_rate = 0 or i.MCUMULATIVE_AUDIENCE =0 then 0 else  round(i.maudience_rate / i.MCUMULATIVE_AUDIENCE,2) end as audiencesRate , \n" +
            "i.MCRITIC_RATE as mcriticsRate,\n" +
            "RANK() OVER (ORDER BY i.MCUMULATIVE_AUDIENCE  DESC) as rank,\n" +
            "case when i.MCUMULATIVE_AUDIENCE = 0 or i.MCUMULATIVE_AUDIENCE = 0 then 0 else round(to_binary_float(i.MCUMULATIVE_AUDIENCE) / (select sum(i.MCUMULATIVE_AUDIENCE) from tmp_movie_info i)*100,2) end as ticketingPer ,\n" +
            "round(TO_DATE(m.MRELEASE_DATE,'YYYYMMDD')- sysdate,0) as dday\n" +
            "from tmp_movie m, tmp_movie_info i\n" +
            "where m.mid = i.mid and m.mage like %:mAge%  and m.DELETE_YN='N' and i.DELETE_YN='N'\n" +
            "order by m.MRELEASE_DATE",
            countQuery = "select count(*) \n" +
                    "from tmp_movie m, tmp_movie_info i \n" +
                    "where m.mid = i.mid and m.mage like %:mAge%  and m.DELETE_YN='N' and i.DELETE_YN='N'\n" +
                    "order by m.MRELEASE_DATE",
            nativeQuery = true)
    Page<MovieMainDto> findByMovieAge(@Param("mAge")String mAge, Pageable pageable);

    @Query(value = "select * from tmp_movie where mtitle=:title and DELETE_YN='N'",
            nativeQuery = true)
    List<Movie> findByTitle(@Param("title") String title);



    // 백업본

    @Query(value = "select * from (select m.mid, m.mtitle , \n" +
            "    i.MCUMULATIVE_AUDIENCE as mcumulativeAudience, \n" +
            "    case when i.maudience_rate = 0 or i.MCUMULATIVE_AUDIENCE =0 then 0 else  round(i.maudience_rate / i.MCUMULATIVE_AUDIENCE,2) end as audiencesRate , \n" +
            "    i.MCRITIC_RATE as mcriticsRate,\n" +
            "    RANK() OVER (ORDER BY i.MCUMULATIVE_AUDIENCE  DESC) as rank,\n" +
            "    case when i.MCUMULATIVE_AUDIENCE = 0 or i.MCUMULATIVE_AUDIENCE = 0 then 0 else round(to_binary_float(i.MCUMULATIVE_AUDIENCE) / (select sum(i.MCUMULATIVE_AUDIENCE) from tmp_movie_info i)*100,2) end as ticketingPer ,\n" +
            "    round(TO_DATE(m.MRELEASE_DATE,'YYYYMMDD')-sysdate,0) as dday\n" +
//            "    m.mposter, round(TO_DATE(m.MRELEASE_DATE,'YYYYMMDD')-sysdate,0) as dday\n" +
            "from tmp_movie m, tmp_movie_info i\n" +
            "where m.mid = i.mid and mrelease_date <= :nowDate and m.DELETE_YN='N' and i.DELETE_YN='N')\n" +
            "where rank<=:mRank",
            nativeQuery = true)
    List<MovieMainDto> findMainScreeningList(@Param("mRank") int Rank, @Param("nowDate") String nowDate); // 영화 첫 페이지에서 보여줄 상영중인 영화들(개봉작)

    @Query(value = "select * from (select m.mid, m.mtitle , \n" +
            "    i.MCUMULATIVE_AUDIENCE as mcumulativeAudience, \n" +
            "    case when i.maudience_rate = 0 or i.MCUMULATIVE_AUDIENCE =0 then 0 else  round(i.maudience_rate / i.MCUMULATIVE_AUDIENCE,2) end as audiencesRate , \n" +
            "    i.MCRITIC_RATE as mcriticsRate,\n" +
            "    RANK() OVER (ORDER BY i.MCUMULATIVE_AUDIENCE  DESC) as rank,\n" +
            "    case when i.MCUMULATIVE_AUDIENCE = 0 or i.MCUMULATIVE_AUDIENCE = 0 then 0 else round(to_binary_float(i.MCUMULATIVE_AUDIENCE) / (select sum(i.MCUMULATIVE_AUDIENCE) from tmp_movie_info i)*100,2) end as ticketingPer ,\n" +
            "    round(TO_DATE(m.MRELEASE_DATE,'YYYYMMDD')- sysdate,0) as dday\n" +
//            "    m.mposter, round(TO_DATE(m.MRELEASE_DATE,'YYYYMMDD')- sysdate,0) as dday\n" +
            "from tmp_movie m, tmp_movie_info i\n" +
            "where m.mid = i.mid and mrelease_date > :nowDate and m.DELETE_YN='N' and i.DELETE_YN='N')\n" +
            "where rank<=:mRank",
            nativeQuery = true)
    List<MovieMainDto> findMainNotScreeningList(@Param("mRank") int Rank, @Param("nowDate") String nowDate); // 영화 첫 페이지에서 보여줄 상영예정 영화들(개봉예정작)

    @Query(value = "select  mid, mtitle, mrunning_time as mrunningTime, mcode, " +
            "        mrelease_date as mreleaseDate, mclosing_date as mclosingDate, \n" +
            "        mdirector,mactor,msimple_info,mtrailer_link as mtrailerLink, mage, delete_yn as deleteYn," +
            "        insert_time as insertTime,update_time as updateTime, delete_time as deleteTime \n" +
            "from tmp_movie \n" +
            "order by mid desc",
    countQuery = "select count(*) from tmp_movie order by mid desc",
    nativeQuery = true)
    Page<MovieListDto> findMoviewithoutImages(Pageable pageable);

    @Query(value = "select  mid, mtitle, mrunning_time as mrunningTime, mcode,\n" +
            "                    mrelease_date as mreleaseDate, mclosing_date as mclosingDate, \n" +
            "                    mdirector,mactor,msimple_info,mtrailer_link as mtrailerLink, mage, delete_yn as deleteYn,\n" +
            "                    insert_time as insertTime,update_time as updateTime, delete_time as deleteTime \n" +
            "            from tmp_movie\n" +
            "            where mtitle like %:title% and delete_yn ='N' \n" +
            "            order by mid desc",
            countQuery = "select count(*) from tmp_movie where mtitle like %:title% and delete_yn ='N' ",
            nativeQuery = true)
    Page<MovieListDto> findMoviewithoutImagesSearchTitle(@Param("title")String mtitle, Pageable pageable);
    @Query(value = "select m.mid, m.mtitle, m.mrunning_time as mrunningTime, \n" +
            "       case when i.maudience_rate =0 or i.mcumulative_audience=0 then 0 else round(i.maudience_rate/i.mcumulative_audience,1) end as rate, m.mcode,\n" +
            "       m.mrelease_date as mreleaseDate, m.mclosing_date as mclosingDate,\n" +
            "       m.mdirector,m.mactor,m.msimple_info as msimpleInfo, m.mtrailer_link as mtrailerLink, m.mage, m.delete_yn as deleteYn,\n" +
            "       m.insert_time as insertTime,m.update_time as updateTime, m.delete_time as deleteTime\n" +
            "from tmp_movie m, tmp_movie_info i\n" +
            "where m.mid=i.mid and m.mclosing_date > :nowDate and m.mrelease_date <= :nowDate and m.DELETE_YN='N' and i.DELETE_YN='N' \n" +
            "order by rate desc",
    countQuery = "select count(*) from tmp_movie m, tmp_movie_info i where m.mid=i.mid and m.mclosing_date > :nowDate and m.mrelease_date <= :nowDate and m.DELETE_YN='N' and i.DELETE_YN='N' ",
    nativeQuery = true)
    Page<MovieDto> findScreeningMovieByRate(@Param("nowDate")String nowDate, Pageable pageable);

    @Query(value = "select  a.rc as reviewcounter, m.mid, m.mtitle, m.mrunning_time as mrunningTime, case when i.maudience_rate =0 or i.mcumulative_audience=0 then 0 else round(i.maudience_rate/i.mcumulative_audience,1) end as rate ,m.mcode,\n" +
            "        m.mrelease_date as mreleaseDate, m.mclosing_date as mclosingDate,\n" +
            "        m.mdirector,m.mactor,m.msimple_info as msimpleInfo, m.mtrailer_link as mtrailerLink, m.mage, m.delete_yn as deleteYn,\n" +
            "        m.insert_time as insertTime,m.update_time as updateTime, m.delete_time as deleteTime\n" +
            "from\n" +
            "(select m.mid, count(*) as rc from tmp_audience a, tmp_movie m where m.mid=a.mid and m.mclosing_date > :nowDate and m.mrelease_date <= :nowDate and m.DELETE_YN='N' and a.DELETE_YN='N' group by m.mid )a , \n" +
            "tmp_movie m, tmp_movie_info i \n" +
            "where a.mid = m.mid and m.mid=i.mid \n" +
            "order by a.rc desc",
    countQuery = "select  count(*) \n" +
            "from\n" +
            "(select m.mid, count(*) as rc from tmp_audience a, tmp_movie m where m.mid=a.mid and m.mclosing_date > :nowDate and m.mrelease_date <= :nowDate and m.DELETE_YN='N' and a.DELETE_YN='N' group by m.mid )a , \n" +
            "tmp_movie m, tmp_movie_info i \n" +
            "where a.mid = m.mid and m.mid=i.mid\n" +
            "order by a.rc desc",
    nativeQuery = true)
    Page<MovieReviewCount> findScreeningMovieByReviewCount(@Param("nowDate")String nowDate, Pageable pageable);

    @Query(value = "select case when i.MCUMULATIVE_AUDIENCE = 0 or i.MCUMULATIVE_AUDIENCE = 0 then 0 else round(to_binary_float(i.MCUMULATIVE_AUDIENCE) / (select sum(i.MCUMULATIVE_AUDIENCE) from tmp_movie_info i)*100,2) end as ticketingPers,\n" +
            "       m.mid, m.mtitle, m.mrunning_time as mrunningTime,case when i.maudience_rate =0 or i.mcumulative_audience=0 then 0 else round(i.maudience_rate/i.mcumulative_audience,1) end as rate, m.mcode,\n" +
            "       m.mrelease_date as mreleaseDate, m.mclosing_date as mclosingDate,\n" +
            "       m.mdirector,m.mactor,m.msimple_info as msimpleInfo, m.mtrailer_link as mtrailerLink, m.mage, m.delete_yn as deleteYn,\n" +
            "       m.insert_time as insertTime,m.update_time as updateTime, m.delete_time as deleteTime\n" +
            "from tmp_movie m, tmp_movie_info i\n" +
            "where m.mid=i.mid and m.mclosing_date > :nowDate and m.mrelease_date <= :nowDate and m.DELETE_YN='N' and i.DELETE_YN='N' \n" +
            "order by ticketingPers desc",
    countQuery = "select count(*) \n" +
            "from tmp_movie m, tmp_movie_info i\n" +
            "where m.mid=i.mid and m.mclosing_date > :nowDate and m.mrelease_date <= :nowDate and m.DELETE_YN='N' and i.DELETE_YN='N'",
    nativeQuery = true)
    Page<MovieTicketingDto> findScreeningMovieByTicketingPer(@Param("nowDate")String nowDate, Pageable pageable);





    @Query(value = "select round(TO_DATE(m.MRELEASE_DATE,'YYYYMMDD')- sysdate,0) as dday,\n" +
            "       m.mid, m.mtitle, m.mrunning_time as mrunningTime,case when i.maudience_rate =0 or i.mcumulative_audience=0 then 0 else round(i.maudience_rate/i.mcumulative_audience,1) end as rate, m.mcode,\n" +
            "       m.mrelease_date as mreleaseDate, m.mclosing_date as mclosingDate,\n" +
            "       m.mdirector,m.mactor,m.msimple_info as msimpleInfo, m.mtrailer_link as mtrailerLink, m.mage, m.delete_yn as deleteYn,\n" +
            "       m.insert_time as insertTime,m.update_time as updateTime, m.delete_time as deleteTime\n" +
            "from tmp_movie m, tmp_movie_info i\n" +
            "where m.mid=i.mid and m.mclosing_date > :nowDate and m.mrelease_date > :nowDate and m.DELETE_YN='N' and i.DELETE_YN='N' \n" +
            "order by dday desc",
    countQuery = "select count(*) \n" +
            "from tmp_movie m, tmp_movie_info i\n" +
            "where m.mid=i.mid and m.mclosing_date > :nowDate and m.mrelease_date < :nowDate and m.DELETE_YN='N' and i.DELETE_YN='N' \n" +
            "      and round(TO_DATE(m.MRELEASE_DATE,'YYYYMMDD')- sysdate,0)>0",
    nativeQuery = true)
    Page<MovieDdayDto> findNotScreeningMovieByDday(@Param("nowDate")String nowDate, Pageable pageable);


    @Query(value = "select case when i.MCUMULATIVE_AUDIENCE = 0 or i.MCUMULATIVE_AUDIENCE = 0 then 0 else round(to_binary_float(i.MCUMULATIVE_AUDIENCE) / (select sum(i.MCUMULATIVE_AUDIENCE) from tmp_movie_info i)*100,2) end as ticketingPers,\n" +
            "       m.mid, m.mtitle, m.mrunning_time as mrunningTime,case when i.maudience_rate =0 or i.mcumulative_audience=0 then 0 else round(i.maudience_rate/i.mcumulative_audience,1) end as rate, m.mcode,\n" +
            "       m.mrelease_date as mreleaseDate, m.mclosing_date as mclosingDate, round(TO_DATE(m.MRELEASE_DATE,'YYYYMMDD')- sysdate,0) as dday, \n" +
            "       m.mdirector,m.mactor,m.msimple_info as msimpleInfo, m.mtrailer_link as mtrailerLink, m.mage, m.delete_yn as deleteYn,\n" +
            "       m.insert_time as insertTime,m.update_time as updateTime, m.delete_time as deleteTime\n" +
            "from tmp_movie m, tmp_movie_info i\n" +
            "where m.mid=i.mid and m.mclosing_date > :nowDate and m.mrelease_date > :nowDate and m.DELETE_YN='N' and i.DELETE_YN='N' \n" +
            "order by ticketingPers desc",
    countQuery = "select count(*) \n" +
            "from tmp_movie m, tmp_movie_info i\n" +
            "where m.mid=i.mid and m.mclosing_date > :nowDate and m.mrelease_date > :nowDate and m.DELETE_YN='N' and i.DELETE_YN='N'",
    nativeQuery = true)
    Page<MovieTicketingDto> findNotScreeningMovieByTicketingPer(@Param("nowDate")String nowDate, Pageable pageable);
}
