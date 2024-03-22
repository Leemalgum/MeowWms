package com.ssg.meowwms.domain;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MemberVO {
    private String uid;
    private String shopName;
    private int coRegNum;
    private String shopAddress;
    private String businessType;
    private int onlineBusinessNumber;
    private String fax;
}
