package com.ssg.meowwms.domain.category;

import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * 분류 테이블
 * 필드값 (아이디, 대분류, 중분류, 소분류)
 */
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    /**
     * 분류 아이디 입니다.
     * Auto Increment
     */
    @NotNull
    private int id;

    /**
     * 대분류 입니다.
     */
    @NotNull
    private String mainCategory;

    /**
     * 중뷴류 입니다.
     */
    @NotNull
    private String middleCategory;

    /**
     * 소분류 입니다.
     */
    @NotNull
    private String subCategory;
}
