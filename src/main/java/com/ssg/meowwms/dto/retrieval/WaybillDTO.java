package com.ssg.meowwms.dto.retrieval;

import lombok.*;

import java.time.LocalDate;

@ToString
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WaybillDTO {
    private int id;
    private int ShippingOrdersId;
    private String vehicleNum;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private int status;

}
