package com.ssg.meowwms.dto.stock;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import groovyjarjarantlr4.v4.runtime.misc.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.validator.constraints.Length;

/**
 * 창고현황 리스트에 출력 될 필드 (창고id, 창고명, 창고분류, 제품명, 상세정보, 재고수량, 입고수량, 출고수량)
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseStatusDTO {

    /**
     * 창고 아이디 : 1 이상, Auto Increment
     */
    private int warehouseId;

    /**
     * 창고 이름 : 45자 제한
     */
    private String warehouseName;

    /**
     * 창고 소분류 이름 : 45자 제한
     */
    private int warehouseSubCategory;

    /**
     * 제품 이름 : 45자 제한
     */
    private String productName;

    /**
     * 제품 상세정보 : 빈값 가능
     */
    private String detail;

    /**
     * 재고 수량 : 0 이상
     */
    private int stockQuantity;

    /**
     * 입고 수량 : 0 이상
     * product.quantity 입고수량
     */
    private int inboundQuantity;

    /**
     * 출고 수량 : 0 이상
     * shoppingOrderDetail.quantity 출고수량
     */
    private int outboundQuantity;

}
