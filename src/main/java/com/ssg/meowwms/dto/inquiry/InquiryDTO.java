package com.ssg.meowwms.dto.inquiry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InquiryDTO {
    private int postNum;
    private String postType;
    private String postTitle;
    private String userId;
    private String postContent;
    private Date postDate;
    private String response;
}
