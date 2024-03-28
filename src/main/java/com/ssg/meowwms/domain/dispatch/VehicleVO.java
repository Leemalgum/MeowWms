package com.ssg.meowwms.domain.dispatch;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VehicleVO {
    @NotNull
    private String num;
    @NotNull
    private String type;
    @NotNull
    private String dname;
    @NotNull
    private String dphone;
    @NotNull
    private int status;
}
