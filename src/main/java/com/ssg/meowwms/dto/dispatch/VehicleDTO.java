package com.ssg.meowwms.dto.dispatch;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VehicleDTO {
    private String num;
    private String type;
    private String dname;
    private String dphone;
    private int status;
}
