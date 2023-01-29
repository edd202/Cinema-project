package com.example.project_movie_backEnd.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "TMP_CINEMA_SITE")
@SequenceGenerator(
        name= "SQ_CINEMA_SITE_GENERATOR"
        , sequenceName = "SQ_CINEMA_SITE"
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
@SQLDelete(sql="UPDATE TMP_CINEMA_SITE SET DELETE_YN = 'Y', DELETE_TIME = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE CID = ?")
public class CinemaSite extends BaseTimeEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CINEMA_SITE_GENERATOR")
    private Integer cid;

    @Column
    private String cinemaName;
}
