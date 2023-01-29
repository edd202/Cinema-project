package com.example.project_movie_backEnd.repository;

import com.example.project_movie_backEnd.dto.admin.*;
import com.example.project_movie_backEnd.model.Schedule;
import com.example.project_movie_backEnd.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query(value = "select * from tmp_ticket where tk_mem_yn='N' and tk_phone = :phone and tk_pw = :pw and DELETE_YN= 'N' order by INSERT_TIME desc",
    countQuery = "select count(*) from tmp_ticket where tk_mem_yn='N' and tk_phone = :phone and tk_pw = :pw and DELETE_YN= 'N' order by INSERT_TIME desc",
    nativeQuery = true)
    Page<Ticket> findNonMemberTicket(@Param("phone")String tkPhone, @Param("pw")String tkPw, Pageable pageable);
    @Query(value = "select * from tmp_ticket where tk_mem_yn='Y' and tk_mem_id = :id and DELETE_YN= 'N' order by INSERT_TIME desc",
    countQuery = "select * from tmp_ticket where tk_mem_yn='Y' and tk_mem_id = :id and DELETE_YN= 'N' order by INSERT_TIME desc",
    nativeQuery = true)
    Page<Ticket> findMemberTicket(@Param("id")String memId, Pageable pageable);

    @Query(value = "select nvl(a.cinema, :cinemaName) as cinema, b.temp_date as month, nvl(a.price,0) as sales  from\n" +
            "    (select substr(tk_theater, 0, instr(tk_theater,' : ')-1) as cinema,\n" +
            "            substr(t.insert_time,0,7) as month,\n" +
            "            sum(tk_price) as price\n" +
            "    from tmp_ticket t \n" +
            "    where DELETE_YN= 'N' and substr(t.insert_time,0,4)='2023' and substr(tk_theater,0,instr(tk_theater,' : ')-1) = :cinemaName\n" +
            "    group by substr(tk_theater, 0, instr(tk_theater,' : ')-1), substr(t.insert_time,0,7)) a\n" +
            "right join temp_date b on(b.temp_date = a.month)\n" +
            "order by month",
    nativeQuery = true)
    List<CinemaSalesDto> findCinemaSales(@Param("cinemaName")String cinemaName);

    @Query(value = "select t.tk_title as title ,sum(t.tk_price) as sales \n" +
            "from tmp_ticket t, tmp_movie m \n" +
            "where t.tk_title in (select m.mtitle from tmp_movie where m.mclosing_date > sysdate) \n" +
            "group by tk_title",
    nativeQuery = true)
    List<movieSales> findMovieSales();

    @Query(value = "select count(*) as userCount from tmp_ticket where delete_yn='N' and tk_mem_yn='N'",
            nativeQuery = true)
    List<userCountDto> nonMemberReservasionCount();

    @Query(value = "select count(*) as userCount from tmp_ticket where delete_yn='N' and tk_mem_yn='Y'",
            nativeQuery = true)
    List<userCountDto> memberReservasionCount();

    @Query(value = "select count(*) as userCount from tmp_ticket where delete_yn='N' and substr(insert_time,3,2)= substr(to_char(sysdate),0,2)",
            nativeQuery = true)
    List<userCountDto> yearlyReservationCount();

    @Query(value = "select substr(insert_time,0,4) as year, substr(insert_time,6,2) as month, sum(tk_price) as sales \n" +
            "from tmp_ticket  \n" +
            "group by substr(insert_time,0,4), substr(insert_time,6,2)\n" +
            "order by year, month",
            nativeQuery = true)
    List<monthlySales> monthlySales();

    @Query(value = "select count(*) as vistorCount, substr(insert_time,6,2) as month,sum(tk_price) as sales \n" +
            "from tmp_ticket  \n" +
            "where  substr(insert_time,0,4)=2023 \n" +
            "group by substr(insert_time,6,2)\n" +
            "order by month",
            nativeQuery = true)
    List<monthlyVistor> monthlyVistor();

    //리스트로 티켓 가져오기
    List<Ticket> findAllByTkMemId(Integer memId);
}
