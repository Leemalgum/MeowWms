package com.ssg.meowwms.dto.inquiry;

import lombok.*;

import java.util.Date;

//공지사항
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NoticeDTO {
    private int no;
    private String type;
    private String title;
    private String content;
    private String userId;
    private Date noticeDate;

}
