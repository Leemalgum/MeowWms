package com.ssg.meowwms.dto.category;

import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * 카테고리 테이블에서 대분류만 가져옵니다.
 */
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MainCategoryDTO {

    @NotNull
    private String mainCategory;
}
