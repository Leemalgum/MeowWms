package com.ssg.meowwms.dto.retrieval;

import lombok.*;
import java.time.LocalDate;

@ToString
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShippingOrdersDTO {
    private int id;
    private String uid;
    private String postcode;
    private String streetNameAddress;
    private String streetNumberAddress;
    private String detailAddress;
    private LocalDate expectedDate;
}
