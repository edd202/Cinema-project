package com.example.project_movie_backEnd.repository;

import com.example.project_movie_backEnd.dto.admin.userCountDto;
import com.example.project_movie_backEnd.dto.movie.FavoritDto;
import com.example.project_movie_backEnd.model.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName : repository
 * fileName : MemberRepository
 * author : L
 * date : 2022-12-13
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022-12-13         L          최초 생성
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Page<Member> findAllByMemIdContaining(String memId, Pageable pageable);

    @Query(value = "SELECT\n" +
            "    CASE WHEN AGE10>0 AND AGE20>0 AND AGE30>0 AND AGE40UP>0 THEN ROUND((AGE10/(AGE10+AGE20+AGE30+AGE40UP) * 100), 2) ELSE 0 END AS AGE10_PER,\n" +
            "    CASE WHEN AGE10>0 AND AGE20>0 AND AGE30>0 AND AGE40UP>0 THEN ROUND((AGE20/(AGE10+AGE20+AGE30+AGE40UP) * 100), 2) ELSE 0 END AS AGE20_PER,\n" +
            "    CASE WHEN AGE10>0 AND AGE20>0 AND AGE30>0 AND AGE40UP>0 THEN ROUND((AGE30/(AGE10+AGE20+AGE30+AGE40UP) * 100), 2) ELSE 0 END AS AGE30_PER,\n" +
            "    CASE WHEN AGE10>0 AND AGE20>0 AND AGE30>0 AND AGE40UP>0 THEN ROUND((AGE40UP/(AGE10+AGE20+AGE30+AGE40UP) * 100), 2) ELSE 0 END AS age40up_per,\n" +
            "    CASE WHEN WOMAN>0 AND MAN>0 THEN ROUND(((MAN/(MAN+WOMAN)) * 100), 2 ) WHEN WOMAN=0 AND MAN>1 THEN 100 ELSE 0 END AS MANS_PER,\n" +
            "    CASE WHEN WOMAN>0 AND MAN>0 THEN ROUND(((WOMAN/(MAN+WOMAN))* 100), 2) WHEN MAN=0 AND WOMAN>1 THEN 100 ELSE 0 END AS WOMANS_PER\n" +
            "FROM\n" +
            "    (select count(*) AS AGE10 from (SELECT FLOOR(ROUND((SYSDATE - TO_DATE(M.birth_date, 'yyyy-mm-dd'))/365,0) /10) as age10 from TB_USER M, TMP_TICKET T WHERE M.ID = T.TK_MEM_ID AND T.TK_TITLE = :mTitle  and T.DELETE_YN= 'N') where age10=1),\n" +
            "    (select count(*) AS AGE20 from (SELECT FLOOR(ROUND((SYSDATE - TO_DATE(M.birth_date, 'yyyy-mm-dd'))/365,0) /10) as age20 from TB_USER M, TMP_TICKET T WHERE M.ID = T.TK_MEM_ID AND T.TK_TITLE = :mTitle  and T.DELETE_YN= 'N') where age20=2),\n" +
            "    (select count(*) AS AGE30 from (SELECT FLOOR(ROUND((SYSDATE - TO_DATE(M.birth_date, 'yyyy-mm-dd'))/365,0) /10) as age30 from TB_USER M, TMP_TICKET T WHERE M.ID = T.TK_MEM_ID AND T.TK_TITLE = :mTitle  and T.DELETE_YN= 'N') where age30=3),\n" +
            "    (select count(*) AS AGE40UP from (SELECT FLOOR(ROUND((SYSDATE - TO_DATE(M.birth_date, 'yyyy-mm-dd'))/365,0) /10) as age40up_per from TB_USER M, TMP_TICKET T WHERE M.ID = T.TK_MEM_ID AND T.TK_TITLE = :mTitle  and T.DELETE_YN= 'N') where age40up_per>=4),\n" +
            "    (SELECT COUNT(M.GENDER) AS WOMAN FROM TB_USER M , TMP_TICKET T WHERE T.TK_MEM_ID = M.ID AND M.GENDER='F' AND T.TK_TITLE = :mTitle and T.DELETE_YN= 'N'),\n" +
            "    (SELECT COUNT(M.GENDER) AS MAN FROM TB_USER M , TMP_TICKET T WHERE T.TK_MEM_ID = M.ID AND M.GENDER='M' AND T.TK_TITLE = :mTitle and T.DELETE_YN= 'N')"
            , nativeQuery = true)
    List<FavoritDto> userFavorit(@Param("mTitle")String mTitle);

    @Query(value = "select count(*) as userCount from tmp_member where delete_yn='N'",
            nativeQuery = true)
    List<userCountDto> userCount();

}