package com.ssg.meowwms.dto.retrieval;

import lombok.*;

@ToString
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShippingOrdersStatusDTO {
    private int id;
    private int approvedStatus;
    private int allocatedStatus;
}
