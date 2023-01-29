package com.example.project_movie_backEnd.model;


import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * packageName : model
 * fileName : Movie
 * author : L
 * date : 2022-12-12
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022-12-12         L          최초 생성
 */

@Entity
@Table(name = "TMP_MOVIE")
@SequenceGenerator(
        name = "SQ_MOVIE_GENERATOR"
        , sequenceName = "SQ_MOVIE"
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
@SQLDelete(sql = "UPDATE TMP_MOVIE SET DELETE_YN = 'Y', DELETE_TIME = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE MID = ?")
public class Movie extends BaseTimeEntity {
    @Id
    @Column(name = "MID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_MOVIE_GENERATOR")
    private Integer mid;

    @Column
    private String mtitle;

    @Column
    private String mrunningTime;

    @Column
    private String mcode;

    @Column
    private String mreleaseDate;

    @Column
    private String mclosingDate;

    @Column
    private String mdirector;

    @Column
    private String mactor;

    @Column
    private String msimpleInfo;

    @Column
    private String mtrailerLink;

    @Column
    private String mage;

    @Column
    @Lob
    private byte[] mposter;

    @Column
    private String mposterFilename;

    @Column
    private String mposterType;

    @Column
    @Lob
    private byte[] mimage1;

    @Column
    private String mimageFilename1;

    @Column
    private String mimageType1;

    @Column
    @Lob
    private byte[] mimage2;

    @Column
    private String mimageFilename2;

    @Column
    private String mimageType2;

    @Column
    @Lob
    private byte[] mimage3;

    @Column
    private String mimageFilename3;

    @Column
    private String mimageType3;

    @Column
    @Lob
    private byte[] mimage4;

    @Column
    private String mimageFilename4;

    @Column
    private String mimageType4;

    @Column
    @Lob
    private byte[] mimage5;

    @Column
    private String mimageFilename5;

    @Column
    private String mimageType5;

    public Movie(String mtitle, String mrunningTime, String mcode, String mreleaseDate, String mclosingDate, String mdirector, String mactor, String msimpleInfo, String mtrailerLink, String mage, byte[] mposter, String mposterFilename, String mposterType, byte[] mimage1, String mimageFilename1, String mimageType1, byte[] mimage2, String mimageFilename2, String mimageType2, byte[] mimage3, String mimageFilename3, String mimageType3, byte[] mimage4, String mimageFilename4, String mimageType4, byte[] mimage5, String mimageFilename5, String mimageType5) {
        this.mtitle = mtitle;
        this.mrunningTime = mrunningTime;
        this.mcode = mcode;
        this.mreleaseDate = mreleaseDate;
        this.mclosingDate = mclosingDate;
        this.mdirector = mdirector;
        this.mactor = mactor;
        this.msimpleInfo = msimpleInfo;
        this.mtrailerLink = mtrailerLink;
        this.mage = mage;
        this.mposter = mposter;
        this.mposterFilename = mposterFilename;
        this.mposterType = mposterType;
        this.mimage1 = mimage1;
        this.mimageFilename1 = mimageFilename1;
        this.mimageType1 = mimageType1;
        this.mimage2 = mimage2;
        this.mimageFilename2 = mimageFilename2;
        this.mimageType2 = mimageType2;
        this.mimage3 = mimage3;
        this.mimageFilename3 = mimageFilename3;
        this.mimageType3 = mimageType3;
        this.mimage4 = mimage4;
        this.mimageFilename4 = mimageFilename4;
        this.mimageType4 = mimageType4;
        this.mimage5 = mimage5;
        this.mimageFilename5 = mimageFilename5;
        this.mimageType5 = mimageType5;
    }
}
