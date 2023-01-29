package com.example.project_movie_backEnd.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * packageName : model
 * fileName : Notice
 * author : Chozy93
 * date : 22-12-15(015)
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 22-12-15(015)         Chozy93          최초 생성
 */

@Entity
@Table(name = "TMP_NOTICE")
@SequenceGenerator(
        name= "SQ_NOTICE_GENERATOR"
        , sequenceName = "SQ_NOTICE"
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
@SQLDelete(sql = "UPDATE TMP_NOTICE SET DELETE_YN = 'Y', DELETE_TIME = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE NTC_NO = ?")
public class Notice extends BaseTimeEntity{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_NOTICE_GENERATOR")
    private Integer ntcNo;

    @Column
    private String ntcTitle;

    @Column
    private String ntcContent;

    @Column
    @Lob
    private byte[] ntcImage;

    @Column
    private String ntcImageFilename;

    @Column
    private String ntcImageType;

    public Notice(String ntcTitle, String ntcContent, byte[] ntcImage, String ntcImageFilename, String ntcImageType) {
        this.ntcTitle = ntcTitle;
        this.ntcContent = ntcContent;
        this.ntcImage = ntcImage;
        this.ntcImageFilename = ntcImageFilename;
        this.ntcImageType = ntcImageType;
    }
}
