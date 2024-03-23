package com.ssg.meowwms.domain.inquiry;

import lombok.*;

import java.util.Date;

//문의글
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InquiryVO {
    private int postNum;
    private String postType;
    private String postTitle;
    private String userId;
    private String postContent;
    private Date postDate;
    private String response;
}
