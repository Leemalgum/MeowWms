package com.ssg.meowwms.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 사용자의 쇼핑몰 정보를 전달하기 위한 데이터 전송 객체 클래스 입니다.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO {
    private String uid;
    private String shopName;
    private String coRegNum;
    private String shopAddress;
    private String businessType;
    private int onlineBusinessNumber;
    private String fax;
}
