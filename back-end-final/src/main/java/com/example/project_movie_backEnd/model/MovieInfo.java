package com.example.project_movie_backEnd.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
/**
 * packageName : model
 * fileName : MovieInfo
 * author : L
 * date : 2022-12-12
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022-12-12         L          최초 생성
 */

@Entity
@Table(name = "TMP_MOVIE_INFO")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Where(clause = "DELETE_YN = 'N'")
@SQLDelete(sql="UPDATE TMP_MOVIE_INFO SET DELETE_YN = 'Y', DELETE_TIME = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE MID = ?")
public class MovieInfo extends BaseTimeEntity{
    @Id
    @Column(name = "MID")
    private Integer mid;

    @Column
    private Integer mcumulativeAudience;

    @Column
    private Integer maudienceRate;

    @Column
    private Integer mcriticRate;

}
