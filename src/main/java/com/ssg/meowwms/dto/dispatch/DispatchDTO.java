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
    private String vehicleNum;
    private int warehouseId;
    private LocalDateTime requestDate;
    private LocalDateTime dispatchDate;
}
