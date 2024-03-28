package com.ssg.meowwms.dto.warehouse;

import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * 창고 상세 정보
 */
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseDetailDTO {

    /**
     * 창고의 이름입니다.
     */
    private String name;

    /**
     * 창고 카테고리 입니다.
     */
    @NotNull
    private String category;

    /**
     * 창고 위치입니다. (지번 주소)
     */
    @NotNull
    private String address;

    /**
     * 창고 관리자 아이디입니다.
     * User 테이블을 참조합니다.
     */
    private String managerId;

    /**
     * 창고에 저장된 상품의 아이디입니다.
     */
    private String productId;

    /**
     * 창고에 저장된 상품의 이름입니다.
     */
    private String productName;

    /**
     * 창고에 저장된 상품의 재고량 입니다.
     */
    private String stockQuantity;

    /**
     * 창고에 저장된 상품이 차지하는 용량입니다.
     */
    private String productVolume;
}
