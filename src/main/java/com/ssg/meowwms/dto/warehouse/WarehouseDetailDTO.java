package com.ssg.meowwms.dto.warehouse;

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
     * 창고의 대분류 입니다.
     * Category 테이블을 참조합니다.
     */
    private String mainCategory;

    /**
     * 창고의 중뷴류 입니다.
     * Category 테이블을 참조합니다.
     */
    private String middleCategory;

    /**
     * 창고의 소분류 입니다.
     * Category 테이블을 참조합니다.
     */
    private String subCategory;

    /**
     * 창고의 위치 중 위도를 나타납니다.
     */
    private double latitude;

    /**
     * 창고의 위치 중 경도를 나타냅니다.
     */
    private double longitude;

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
