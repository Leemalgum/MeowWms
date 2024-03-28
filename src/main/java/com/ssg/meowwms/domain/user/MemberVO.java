package com.ssg.meowwms.domain.user;

import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * 사용자의 쇼핑몰 정보를 다루는 도메인 클래스 입니다
 */
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
