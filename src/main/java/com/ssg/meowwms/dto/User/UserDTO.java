package com.ssg.meowwms.dto.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private String uid;
    private String uname;
    private LocalDate birth;
    private String upw;
    private String email;
    private String tel;
    private int rid;
    private int sid;
}
