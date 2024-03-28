package com.ssg.meowwms.dto.retrieval;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ToString
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShippingOrderDetailsDTO {

    private int index;
    /**
     * from ShippingOrdersVO
     */
    private int id;
    private String uid;
    private String postcode;
    private String streetNameAddress;
    private String streetNumberAddress;
    private String detailAddress;
    private LocalDate expectedDate;

    /**
     * from ShippingOrdersDetailVO
     */
    private int productId;
    private int quantity;

    /**
     * from ProductVO
     */
    private int categoryId;
    private String name;
    private String brand;
    private int price;
    private int salePrice;
    private int volume;

    /**
     * from ShippingOrdersStatusVO
     */
    private int allocatedStatus;
    private int approvedStatus;

    /**
     * from RetrievalTimelineVO
     * */
    private LocalDateTime requestTime;
    private LocalDateTime orderTime;
    private LocalDateTime workingTime;
    private LocalDateTime finishedTime;
}
