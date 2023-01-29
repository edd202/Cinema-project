package com.example.project_movie_backEnd.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "TMP_THEATER")
@SequenceGenerator(
        name= "SQ_THEATER_GENERATOR"
        , sequenceName = "SQ_THEATER"
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
@SQLDelete(sql="UPDATE TMP_THEATER SET DELETE_YN = 'Y', DELETE_TIME = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE thid = ?")
public class Theater extends BaseTimeEntity{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_THEATER_GENERATOR")
    private Integer thid;

    @Column
    private Integer cid;

    @Column
    private String thname;

    @Column
    private String thtype;
}
