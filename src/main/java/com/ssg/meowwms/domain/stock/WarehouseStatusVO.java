package com.ssg.meowwms.domain.stock;

import com.fasterxml.jackson.annotation.JsonFormat;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import groovyjarjarantlr4.v4.runtime.misc.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 창고현황 리스트에 출력 될 필드 (창고id, 창고명, 창고분류, 제품명, 상세정보, 재고수량, 입고수량, 출고수량)
 */
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseStatusVO {

    /**
     * 창고 아이디 : 1 이상, Auto Increment
     */
    @NotNull
    @Min(value = 1, message = "창고 ID 는 1이상 이여야 합니다")
    private int warehouseId;

    /**
     * 창고 이름 : 45자 제한
     */
    @NotNull
    @Length(max = 45, message = "창고 이름은 45자 이하여야 합니다")
    private String warehouseName;

    /**
     * 창고 소분류 이름 : 45자 제한
     */
    @NotNull
    @Length(max = 45, message = "카테고리 이름은 45자 이하여야 합니다")
    private int warehouseSubCategory;

    /**
     * 제품 이름 : 45자 제한
     */
    @NotNull
    @Length(max = 45, message = "제품 이름은 45자 이하여야 합니다")
    private String productName;

    /**
     * 제품 상세정보 : 빈값 가능
     */
    @Nullable
    private String detail;

    /**
     * 재고 수량 : 0 이상
     */
    @NotNull
    private int stockQuantity;

    /**
     * 입고 수량 : 0 이상
     * product.quantity 입고수량
     */
    @NotNull
    @Positive(message = "입고수량은 0 이상이여야 합니다.")
    private int inboundQuantity;

    /**
     * 출고 수량 : 0 이상
     * shoppingOrderDetail.quantity 출고수량
     */
    @NotNull
    @Positive(message = "출고수량은 0 이상이여야 합니다.")
    private int outboundQuantity;



}
