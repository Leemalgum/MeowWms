package com.ssg.meowwms.domain.dispatch;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VehicleVO {
    private String num;
    private String type;
    private String dname;
    private int dphone;
}
