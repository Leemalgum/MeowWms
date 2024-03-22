package com.ssg.meowwms.dto.retrieval;

import lombok.*;

@ToString
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShippingOrdersDetailDTO {
    private int id;
    private int shippingOrdersId;
    private int productId;
    private int quantity;
}
