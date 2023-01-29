package com.example.project_movie_backEnd.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "TMP_SCHEDULE")
@SequenceGenerator(
        name= "SQ_SCHEDULE_GENERATOR"
        , sequenceName = "SQ_SCHEDULE"
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
@SQLDelete(sql="UPDATE TMP_SCHEDULE SET DELETE_YN = 'Y', DELETE_TIME = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE SCID = ?")
public class Schedule extends BaseTimeEntity{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_SCHEDULE_GENERATOR")
    private Integer scid;

    @Column
    private Integer thid;

    @Column
    private String mtitle;

    @Column
    private String mage;

    @Column
    private String thdate;

    @Column
    private String thtime;

    @Column(name = "SEAT_A")
    private String seatA;

    @Column(name = "SEAT_B")
    private String seatB;

    @Column(name = "SEAT_C")
    private String seatC;

    @Column(name = "SEAT_D")
    private String seatD;

    @Column(name = "SEAT_E")
    private String seatE;

    @Column(name = "SEAT_F")
    private String seatF;

    @Column(name = "SEAT_G")
    private String seatG;

    @Column(name = "SEAT_H")
    private String seatH;

    @Column(name = "SEAT_I")
    private String seatI;

    @Column(name = "SEAT_J")
    private String seatJ;

    @Column(name = "SEAT_K")
    private String seatK;

    @Column(name = "SEAT_L")
    private String seatL;

    @Column(name = "SEAT_M")
    private String seatM;

    @Column(name = "SEAT_N")
    private String seatN;

    @Column(name = "SEAT_O")
    private String seatO;

    @Column(name = "SEAT_P")
    private String seatP;
}
