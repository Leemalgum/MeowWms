package com.ssg.meowwms.domain.stock;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.*;


/**
 * 재고 필드값 (재고아이디, 창고아이디, 제품아이디, 수량)
 */
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StockVO {

    /**
     * 재고 아이디 : 1 이상, Auto Increment
     */
    @NotNull
    @Min(value = 1, message = "재고 ID 는 1이상 이여야 합니다")
    private int stockId;

    /**
     * 창고아이디 : 1 이상, Auto Increment
     */
    @NotNull
    @Min(value = 1, message = "창고 ID 는 1이상 이여야 합니다")
    private int warehouseId;

    /**
     * 제품 아이디 : 1 이상, Auto Increment
     */
    @NotNull
    @Min(value = 1, message = "품목 ID 는 1이상 이여야 합니다")
    private int productId;

    /**
     * 재고 수량 : 0 이상
     */
    @NotNull
    @Positive(message = "상품은 0개 이상 이여야 합니다")
    private int quantity;

    private String mainCategory;
    private String middleCategory;
    private String subCategory;
}
