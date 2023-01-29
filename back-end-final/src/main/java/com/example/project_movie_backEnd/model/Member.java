package com.example.project_movie_backEnd.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * packageName : model
 * fileName : Member
 * author : L
 * date : 2022-12-12
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022-12-12         L          최초 생성
 */

@Entity
@Table(name = "TMP_MEMBER")
@SequenceGenerator(
        name= "SQ_MEMBER_GENERATOR"
        , sequenceName = "SQ_MEMBER"
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
@SQLDelete(sql="UPDATE TMP_MEMBER SET DELETE_YN = 'Y', DELETE_TIME = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE MEM_NO = ?")
public class Member extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_MEMBER_GENERATOR"
    )
    private Integer memNo;

    @Column(columnDefinition = "VARCHAR2(255)")
    private String memId;

    @Column(columnDefinition = "VARCHAR2(500)")
    private String memPw;

    @Column(columnDefinition = "VARCHAR2(255)")
    private String memName;

    @Column(columnDefinition = "VARCHAR2(1)")
    private String memGender;

    @Column(columnDefinition = "VARCHAR2(255)")
    private String memPhone;

    @Column(columnDefinition = "VARCHAR2(255)")
    private String memEmail;

    @Column(columnDefinition = "VARCHAR2(1000)")
    private String memAddress;

    @Column(columnDefinition = "VARCHAR2(255)")
    private String memBirthdate;
}
