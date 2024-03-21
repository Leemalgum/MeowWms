package com.ssg.meowwms.domain;

import lombok.*;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserVO {
    private String uid;
    private String uname;
    private Date birth;
    private String upw;
    private String email;
    private String tel;
    private int rid;
    private int sid;
}
