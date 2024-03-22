package com.ssg.meowwms.domain.stock;

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
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductStatusVO {
    /**
     * 입고날짜 : yyyy-MM-dd 형식, 한국(서울) 시간
     */
    @NotNull
    @JsonFormat(pattern ="yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date approvedDatetime;

    /**
     * 재고 아이디 : 1 이상, Auto Increment
     */
    @NotNull
    @Min(value = 1, message = "재고 ID 는 1이상 이여야 합니다")
    private int stockId;

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
     * 제품 아이디 : 1 이상, Auto Increment
     */
    @NotNull
    @Min(value = 1, message = "제품 ID 는 1이상 이여야 합니다")
    private String productId;

    /**
     * 제품 이름 : 45자 제한
     */
    @NotNull
    @Length(max = 45, message = "제품 이름은 45자 이하여야 합니다")
    private String productName;

    /**
     * 재고 수량 : 0 이상
     */
    @NotNull
    @Positive(message = "재고수량은 0 이상이여야 합니다.")
    private int stockQuantity;

    /**
     * 상품 가격 : 0 이상
     */
    @NotNull
    @Positive(message = "상품 가격은 0 이상이여야 합니다.")
    private int productPrice;
}
