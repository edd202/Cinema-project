package com.example.project_movie_backEnd.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "TMP_TICKET")
@SequenceGenerator(
        name= "SQ_TICKET_GENERATOR"
        , sequenceName = "SQ_TICKET"
        , initialValue = 1
        , allocationSize = 1
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Where(clause = "DELETE_YN = 'N'")
@SQLDelete(sql="UPDATE TMP_TICKET SET DELETE_YN = 'Y', DELETE_TIME = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE TK_ID = ?")
public class Ticket extends BaseTimeEntity{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TICKET_GENERATOR")
    private Integer tkId;

    @Column
    private String tkTitle;

    @Column
    private String tkTheater;

    @Column
    private Integer tkPrice;

    @Column
    private String tkSeat;

    @Column
    private String tkStartTime;

    @Column
    private String tkEndTime;

    @Column
    private String tkMemYn;  // Y = 회원 N = 비회원

    @Column
    private Integer tkMemId; // 회원은 회원id

    @Column
    private String tkPhone; // 비회원만 기입 - 휴대폰 번호

    @Column
    private String tkPw; // 비회원만 기입 - 비회원 예매 번호

    //비회원용
    public Ticket(String tkTitle, String tkTheater, Integer tkPrice, String tkSeat, String tkStartTime, String tkEndTime, String tkMemYn, Integer tkMemId, String tkPhone, String tkPw) {
        this.tkTitle = tkTitle;
        this.tkTheater = tkTheater;
        this.tkPrice = tkPrice;
        this.tkSeat = tkSeat;
        this.tkStartTime = tkStartTime;
        this.tkEndTime = tkEndTime;
        this.tkMemYn = tkMemYn;
        this.tkMemId = tkMemId;
        this.tkPhone = tkPhone;
        this.tkPw = tkPw;
    }

    //회원용
    public Ticket(String tkTitle, String tkTheater, Integer tkPrice, String tkSeat, String tkStartTime, String tkEndTime, String tkMemYn, Integer tkMemId) {
        this.tkTitle = tkTitle;
        this.tkTheater = tkTheater;
        this.tkPrice = tkPrice;
        this.tkSeat = tkSeat;
        this.tkStartTime = tkStartTime;
        this.tkEndTime = tkEndTime;
        this.tkMemYn = tkMemYn;
        this.tkMemId = tkMemId;
    }

}
