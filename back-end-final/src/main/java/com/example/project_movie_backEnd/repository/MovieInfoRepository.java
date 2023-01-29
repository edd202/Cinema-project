package com.example.project_movie_backEnd.repository;

import com.example.project_movie_backEnd.dto.movie.MovieMovieinfoDto;
import com.example.project_movie_backEnd.model.MovieInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName : repository
 * fileName : MovieInfoRepository
 * author : L
 * date : 2022-12-13
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022-12-13         L          최초 생성
 */
@Repository
public interface MovieInfoRepository extends JpaRepository<MovieInfo, Integer> {
//    현재 컬럼명이 2단어 이상인 경우, DTO랑 연결이 잘 되지 않으므르 컬럼명뒤에 AS로 DTO에서의 컬럼명에서 get을 빼고 기입하면 작동함
//    @Query(value = "select m.MID, m.MTITLE, m.MCODE, m.MRELEASE_DATE as mreleaseDate, m.MDIRECTOR, m.MACTOR, m.MRUNNING_TIME as mrunningTime, m.MSIMPLE_INFO as msimpleInfo, m.MTRAILER_LINK as mtrailerLink,\n" +
//            "       m.MAGE, m.MPOSTER, m.MIMAGE1, m.MIMAGE2, m.MIMAGE3, m.MIMAGE4, m.MIMAGE5,\n" +
//            "       i.MCUMULATIVE_AUDIENCE as mcumulativeAudience, i.MAUDIENCE_RATE as maudienceRate, i.MCRITIC_RATE as mcriticRate\n" +
//            "from TMP_MOVIE m, TMP_MOVIE_INFO i\n" +
//            "where m.MID = i.MID AND m.MTITLE = :movietitle"
//            ,nativeQuery = true)
//    List<MovieMovieinfoDto> movieDetail(@Param("movietitle") String title);

    @Query(value = "select * from(select m.MID, m.MTITLE , m.MCODE, m.MRELEASE_DATE as mreleaseDate, m.MDIRECTOR, m.MACTOR, \n" +
            "m.MRUNNING_TIME as mrunningTime, m.MSIMPLE_INFO as msimpleInfo, m.MTRAILER_LINK as mtrailerLink,\n" +
            "        m.MAGE, m.MPOSTER, m.MIMAGE1, m.MIMAGE2, m.MIMAGE3, m.MIMAGE4, m.MIMAGE5,\n" +
            "        case when i.maudience_rate = 0 or i.MCUMULATIVE_AUDIENCE =0 then 0 else  round(i.maudience_rate / i.MCUMULATIVE_AUDIENCE,2) end as audiencesRate, i.MCRITIC_RATE as mcriticRate,\n" +
            "                RANK() OVER (ORDER BY i.MCUMULATIVE_AUDIENCE  DESC) as rank,\n" +
            "                round(to_binary_float(i.MCUMULATIVE_AUDIENCE) / (select sum(i.MCUMULATIVE_AUDIENCE) from tmp_movie_info i)*100,2) as ticketingPer\n" +
            "            from tmp_movie m, tmp_movie_info i\n" +
            "            where m.mid = i.mid and MCLOSING_DATE > :nowDate and i.DELETE_YN='N' and m.DELETE_YN='N' )\n" +
            "            where MTITLE like %:movietitle%"
            ,nativeQuery = true)
    List<MovieMovieinfoDto> movieDetail(@Param("movietitle") String title,@Param("nowDate") String nowDate);

    @Query(value = "select  m.mid, m.mtitle, m.mcode,\n" +
            "        m.mrelease_date as mreleaseDate, m.mdirector, m.mactor,\n" +
            "        m.mrunning_time as mrunningTime, m.msimple_info as msimpleInfo, m.mtrailer_link as mtrailerLink,\n" +
            "        m.mage, i.mcumulative_audience as mcumulativeAudience, i.mcritic_rate as mcriticsRate,\n" +
            "        case when i.maudience_rate = 0 or i.MCUMULATIVE_AUDIENCE =0 then 0 else  round(i.maudience_rate / i.MCUMULATIVE_AUDIENCE,2) end as audiencesRate,\n" +
            "        mr.rank, round(to_binary_float(i.MCUMULATIVE_AUDIENCE) / (select sum(i.MCUMULATIVE_AUDIENCE) from tmp_movie_info i)*100,2) as ticketingPer\n" +
            "from tmp_movie m, tmp_movie_info i,(select mid, RANK() OVER (ORDER BY i.MCUMULATIVE_AUDIENCE  DESC) as rank from tmp_movie_info i) mr\n" +
            "where m.mid=i.mid and m.mid=mr.mid and m.mid=:mid and m.DELETE_YN='N' and i.DELETE_YN='N'",
            countQuery = "select count(*)\n" +
                    "from tmp_movie m, tmp_movie_info i,(select mid, RANK() OVER (ORDER BY i.MCUMULATIVE_AUDIENCE  DESC) as rank from tmp_movie_info i) mr \n"+
                    "where m.mid=i.mid and m.mid=mr.mid and m.mid=:mid and m.DELETE_YN='N' and i.DELETE_YN='N'",
            nativeQuery = true)
    Page<MovieMovieinfoDto> movieInfo(@Param("mid")int mid, Pageable pageable);
}