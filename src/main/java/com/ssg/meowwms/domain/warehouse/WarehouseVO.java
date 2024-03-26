package com.ssg.meowwms.domain.warehouse;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
public class WarehouseVO {

    /**
     * 창고 아이디 입니다.
     * Auto Increment
     */
    @NotNull
    private int id;


    /**
     * 창고 카테고리 입니다.
     */
    @NotNull
    private String category;

    /**
     * 창고 이름입니다.
     */
    @NotNull
    private String name;

    /**
     * 창고 용량을 나타냅니다.
     * 단위 = cell
     * 최소 1 이상입니다.
     */
    @NotNull
    @Min(value = 1, message = "창고 용량은 최소 10 이상 입니다.")
    private int volume;

    /**
     * 창고 관리자 아이디 입니다.
     * User 테이블을 참조합니다.
     */
    @NotNull
    private String managerId;

    /**
     * 창고 위치입니다. (지번 주소)
     */
    @NotNull
    private String location;
}
