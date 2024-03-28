package com.ssg.meowwms.dto.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MergeDTO {
    //@JsonProperty("product_id")
    private int productId;
    //@JsonProperty("user_id")
    private String movementUserId;
    //@JsonProperty("name")
    private String productName;
    //@JsonProperty("category_id")
    private int productCategory;
    //@JsonProperty("price")
    private int productPrice;
    //@JsonProperty("sale_price")
    private int productSalePrice;
    //@JsonProperty("quantity")
    private int productQuantity;
    //@JsonProperty("status_code")
    private String movementStatusCode;
    //@JsonProperty("request_datetime")
    private LocalDate movementRequestDate;
    //@JsonProperty("approved_datetime")
    private Date movementApprovedDate;
    //@JsonProperty("warehouse_id")
    private int movementWarehouseId;
}
