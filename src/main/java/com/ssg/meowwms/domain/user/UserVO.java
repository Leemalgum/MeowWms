package com.ssg.meowwms.domain.user;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * 사용자 정보를 다루는 도메인 클래스 입니다.
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserVO {
    /**
     * 사용자 아이디이자 PK
     */
    @NotNull
    private String uid;
    /**
     * 사용자 이름
     */
    @NotNull
    private String uname;
    /**
     * 사용자 생년월일
     */
    @NotNull
    private LocalDate birth;
    /**
     * 사용자 비밀번호
     */
    @NotNull
    private String upw;
    /**
     * 사용자 이메일
     */
    @NotNull
    private String email;
    /**
     * 사용자 전화번호
     */
    @NotNull
    private String tel;
    /**
     * 사용자가 가지고 있는 권한으로 회원 가입시 4 = NonUser(비회원) 를 기본값으로 가지고 있습니다
     */
    private int rid;
    /**
     * 사용자의 현재 계정 상태로 회원 가잆시 4 = REQUEST(회원가입요청) 를 기본값으로 가지고 있습니다
     */
    @NotNull
    private int sid;
    /**
     * 사용자 회원 가입일
     */
    private LocalDate joinDate;
}
