package com.example.project_movie_backEnd.dto.CustomerCenter;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEventDto {
    private Integer evtNo;
    private String evtTitle;
    private String evtContent;

    private Integer thumbSize;
    private String thumbUrl;
    private Integer mainImgSize;
    private String mainImgUrl;
}
