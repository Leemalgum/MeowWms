package com.ssg.meowwms.domain.retrieval;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.validator.constraints.Length;

/**
 * 상세 출고 지시서 데이터를 담고 있는 객체
 * (상세 출고 지시서 아이디, 출고지시서 아이디, 제품 아이디, 출고 상품 수량 )
 */
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ShippingOrdersDetailVO {
    /**
     * 상세 출고 지시서 아이디 : 1 이상, Auto Increment
     */
    @NotNull
    @Min(value = 1, message = "상세 출고 지시서 ID 는 1이상 이여야 합니다")
    private int id;

    /**
     * 출고 지시서 아이디 : 1 이상, Auto Increment
     */
    @NotNull
    @Min(value = 1, message = "출고 지시서 ID 는 1이상 이여야 합니다")
    private int shippingOrdersId;

    /**
     * 제품 아이디 : 1 이상, Auto Increment
     */
    @NotNull
    @Min(value = 1, message = "상품 ID 는 1이상 이여야 합니다")
    private int productId;

    /**
     * 상품 수량 : 0 이상
     */
    @NotNull
    @Positive(message = "상품 수량은 0 이상이여야 합니다.")
    private int quantity;
}
