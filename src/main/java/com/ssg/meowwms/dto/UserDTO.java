package com.ssg.meowwms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private String uid;
    private String uname;
    private Date birth;
    private String upw;
    private String email;
    private String tel;
    private int rid;
    private int sid;
}
