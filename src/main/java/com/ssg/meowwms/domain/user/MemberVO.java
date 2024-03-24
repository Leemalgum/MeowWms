package com.ssg.meowwms.domain.user;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MemberVO {
    @NotNull
    private String uid;
    @NotNull
    private String shopName;
    @NotNull
    private String coRegNum;
    @NotNull
    private String shopAddress;
    @NotNull
    private String businessType;
    @NotNull
    private int onlineBusinessNumber;
    private String fax;
}
