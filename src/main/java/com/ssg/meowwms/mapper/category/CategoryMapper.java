package com.ssg.meowwms.mapper.category;

import com.ssg.meowwms.domain.category.CategoryVO;
import com.ssg.meowwms.dto.category.MainCategoryDTO;
import com.ssg.meowwms.dto.category.MiddleCategoryDTO;
import com.ssg.meowwms.dto.category.SubCategoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 카테고리 테이블과의 연결 작업을 수행합니다.
 */
@Mapper
public interface CategoryMapper {

    /**
     * 카테고리 테이블에서 모든 정보를 가져와 반환합니다.
     *
     * @return
     */
    List<CategoryVO> selectAll();

    /**
     * 카테고리 테이블에서 main_category를 중복 없이 가져옵니다.
     *
     * @return
     */
    List<MainCategoryDTO> selectMainCategories();

    /**
     * 카테고리 테이블에서 middle_category를 중복 없이 가져옵니다.
     *
     * @param mainCategory
     * @return
     */
    List<MiddleCategoryDTO> selectMiddleCategories(String mainCategory);

    /**
     * 카테고리 테이블에서 sub_category를 중복 없이 가져옵니다.
     *
     * @param mainCategory
     * @param middleCategory
     * @return
     */
    List<SubCategoryDTO> selectSubCategories(String mainCategory, String middleCategory);
}
