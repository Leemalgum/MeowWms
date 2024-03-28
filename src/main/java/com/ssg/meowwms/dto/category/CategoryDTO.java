package com.ssg.meowwms.dto.category;

import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * 카테고리 테이블
 * 필드값 (아이디, 대분류, 중분류, 소분류)
 */
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

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
