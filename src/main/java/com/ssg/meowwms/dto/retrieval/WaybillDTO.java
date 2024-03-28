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

    private int shippingOrdersId;
    private String uid;
    private String postcode;
    private String streetNameAddress;
    private String streetNumberAddress;
    private String detailAddress;

    private int productId;
    private int quantity;

    private int categoryId;
    private String productName;
    private String productBrand;
    private String productVolume;

    private String vehicleNum;
    private String driverName;
    private String driverPhone;
    private String vehicleType;

    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private int status;

    private int warehouseId;
    private int shippingOrdersDetailId;
}
