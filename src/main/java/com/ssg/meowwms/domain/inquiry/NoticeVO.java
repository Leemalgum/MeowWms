package com.ssg.meowwms.domain.inquiry;

import lombok.*;

import java.util.Date;

//공지사항
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NoticeVO {
    private int no;
    private String type;
    private String title;
    private String content;
    private String userId;
    private Date noticeDate;

}
