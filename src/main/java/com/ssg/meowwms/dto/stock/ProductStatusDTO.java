package com.ssg.meowwms.dto.stock;

import com.fasterxml.jackson.annotation.JsonFormat;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 품목현황 리스트에 출력 될 필드 (입고날짜, 재고ID, 창고ID, 창고이름, 창고분류, 제품아이디, 제품명, 재고수량, 판매가격)
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductStatusDTO {
    /**
     * 입고날짜 : yyyy-MM-dd 형식, 한국(서울) 시간
     */

    private Date approvedDatetime;

    /**
     * 재고 아이디 : 1 이상, Auto Increment
     */

    private int stockId;

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
    private String warehouseSubCategory;

    /**
     * 제품 아이디 : 1 이상, Auto Increment
     */
    private int productId;

    /**
     * 제품 이름 : 45자 제한
     */
    private String productName;

    /**
     * 재고 수량 : 0 이상
     */
    private int stockQuantity;

    /**
     * 상품 가격 : 0 이상
     */
    private int productPrice;
}
