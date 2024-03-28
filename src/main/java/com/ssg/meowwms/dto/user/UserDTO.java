package com.ssg.meowwms.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 사용자 정보를 전달하기 위한 데이터 전송 객체 클래스 입니다.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    /**
     * 사용자 아이디
     */
    private String uid;
    /**
     * 사용자 이름
     */
    private String uname;
    /**
     * 사용자 생년월일
     */
    private LocalDate birth;
    /**
     * 사용자 비밀번호
     */
    private String upw;
    /**
     * 사용자 이메일
     */
    private String email;
    /**
     * 사용자 전화번호
     */
    private String tel;
    /**
     * 사용자의 권한
     */
    private int rid;
    /**
     * 사용자의 계정 상태
     */
    private int sid;
    /**
     * 사용자 회원 가입일
     */
    private LocalDate joinDate;
}
