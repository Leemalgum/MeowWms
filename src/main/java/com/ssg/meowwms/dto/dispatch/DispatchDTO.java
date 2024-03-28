package com.ssg.meowwms.dto.dispatch;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DispatchDTO {
    private int id;
    private int shippingOrdersId;
    private String vehicleNum;
//    private int warehouseId;
    private LocalDateTime requestDate;
    private LocalDateTime dispatchDate;
}
