package com.ssg.meowwms.dto.warehouse;

import lombok.*;

/**
 * 창고 테이블
 * 필드값 (아이디, 카테고리 아이디, 이름, 위도, 경도, 용량, 관리자 아이디)
 */
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseDTO {

    /**
     * 창고 아이디 입니다.
     * Auto Increment
     */
    private int id;

    /**
     * 창고 카테고리 아이디입니다.
     * Category 테이블을 참조합니다.
     */
    private int categoryId;

    /**
     * 창고 이름입니다.
     */
    private String name;

    /**
     * 창고 위치 중 위도를 나타냅니다.
     */
    private double latitude;

    /**
     * 창고 위치 중 경도를 나타냅니다.
     */
    private double longitude;

    /**
     * 창고 용량을 나타냅니다.
     * 단위 = 세제곱 미터
     * 최소 10 이상입니다.
     */
    private Long volume;

    /**
     * 창고 관리자 아이디 입니다.
     * User 테이블을 참조합니다.
     */
    private String managerId;
}
