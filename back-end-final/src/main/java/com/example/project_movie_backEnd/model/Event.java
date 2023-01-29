package com.example.project_movie_backEnd.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * packageName : model
 * fileName : Event
 * author : Chozy93
 * date : 22-12-15(015)
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 22-12-15(015)         Chozy93          최초 생성
 */

@Entity
@Table(name = "TMP_EVENT")
@SequenceGenerator(
        name= "SQ_EVENT_GENERATOR"
        , sequenceName = "SQ_EVENT"
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
@SQLDelete(sql="UPDATE TMP_EVENT SET DELETE_YN = 'Y', DELETE_TIME = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE EVT_NO = ?")
public class Event extends BaseTimeEntity{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_EVENT_GENERATOR")
    private Integer evtNo;

    @Column
    private String evtTitle;

    @Column
    private String evtContent;

    @Column
    @Lob
    private byte[] imgThumb;

    @Column
    private String imgThumbFilename;

    @Column
    private String imgThumbType;

    @Column
    @Lob
    private byte[] imgMain;

    @Column
    private String imgMainFilename;


    @Column
    private String imgMainType;

    public Event(String evtTitle, String evtContent, byte[] imgThumb, String imgThumbFilename, String imgThumbType, byte[] imgMain, String imgMainFilename, String imgMainType) {
        this.evtTitle = evtTitle;
        this.evtContent = evtContent;
        this.imgThumb = imgThumb;
        this.imgThumbFilename = imgThumbFilename;
        this.imgThumbType = imgThumbType;
        this.imgMain = imgMain;
        this.imgMainFilename = imgMainFilename;
        this.imgMainType = imgMainType;
    }


}
