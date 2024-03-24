package com.ssg.meowwms.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private LocalDate joinDate;
}
