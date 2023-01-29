package com.example.project_movie_backEnd.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * packageName : model
 * fileName : MemberInfo
 * author : L
 * date : 2022-12-12
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022-12-12         L          최초 생성
 */

@Entity
@Table(name = "TMP_MEMBER_INFO")
@SequenceGenerator(
        name= "SQ_MEMBER_INFO_GENERATOR"
        , sequenceName = "SQ_MEMBER_INFO"
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
@SQLDelete(sql="UPDATE TMP_MEMBER_INFO SET DELETE_YN = 'Y', DELETE_TIME = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE MEM_NO = ?")
public class MemberInfo extends BaseTimeEntity{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_MEMBER_INFO_GENERATOR")
    private Integer memNo;

    @Column
    private String memWatched;

    @Column
    private Integer memPoint;

    @Column
    private String memGrade;
}
