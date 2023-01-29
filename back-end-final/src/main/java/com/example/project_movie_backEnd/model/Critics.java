package com.example.project_movie_backEnd.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
/**
 * packageName : model
 * fileName : Critic
 * author : L
 * date : 2022-12-12
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022-12-12         L          최초 생성
 */
@Entity
@Table(name = "TMP_CRITICS")
@SequenceGenerator(
        name= "SQ_CRITICS_GENERATOR"
        , sequenceName = "SQ_CRITICS"
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
@SQLDelete(sql="UPDATE TMP_CRITICS SET DELETE_YN = 'Y', DELETE_TIME = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE CRT_NO = ?")
public class Critics extends BaseTimeEntity{
//    비평가 점수
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CRITICS_GENERATOR")
    private Integer crtNo;

    @Column
    private Integer mId;

    @Column
    private String crtCritics;

    @Column
    private Integer crtRate;
}
