package com.ssg.meowwms.dto.dispatch;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DispatchDTO {
    private int id;
    private String vehicle_num;
    private String warehouse_id;
    private String vehicle_status;
    private LocalDateTime requeste_date;
    private LocalDateTime dispatch_date;
}
