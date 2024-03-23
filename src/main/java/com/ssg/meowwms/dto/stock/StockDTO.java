package com.ssg.meowwms.dto.stock;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.*;


/**
 * 재고 필드값 (재고아이디, 창고아이디, 제품아이디, 수량)
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockDTO {

    /**
     * 재고 아이디 : 1 이상, Auto Increment
     */
    private int stockId;

    /**
     * 창고아이디 : 1 이상, Auto Increment
     */
    private int warehouseId;

    /**
     * 제품 아이디 : 1 이상, Auto Increment
     */
    private int productId;

    /**
     * 재고 수량 : 0 이상
     */
    private int quantity;

}
