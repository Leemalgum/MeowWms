package com.ssg.meowwms.domain.user;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserVO {
    @NotNull
    private String uid;
    @NotNull
    private String uname;
    @NotNull
    private LocalDate birth;
    @NotNull
    private String upw;
    @NotNull
    private String email;
    @NotNull
    private String tel;
    private int rid;
    @NotNull
    private int sid;
    private LocalDate joinDate;
}
