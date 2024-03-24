package com.ssg.meowwms.mapper.category;

import com.ssg.meowwms.domain.category.CategoryVO;
import com.ssg.meowwms.dto.category.MainCategoryDTO;
import com.ssg.meowwms.dto.category.MiddleCategoryDTO;
import com.ssg.meowwms.dto.category.SubCategoryDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
@Log4j2
public class CategoryMapperTest {

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    @DisplayName("카테고리 전체 조회")
    void selectAll() {
        List<CategoryVO> categories = categoryMapper.selectAll();

        log.info(categories);
    }

    @Test
    @DisplayName("대분류를 중복 없이 조회")
    void selectMainCategories() {
        List<MainCategoryDTO> mainCategories = categoryMapper.selectMainCategories();

        log.info(mainCategories);
    }

    @Test
    @DisplayName("주어진 대분류와 일치하는 중분류를 중복 없이 조회")
    void selectMiddleCategories() {
        String mainCategory = "상온";

        List<MiddleCategoryDTO> middleCategories = categoryMapper.selectMiddleCategories(mainCategory);

        log.info(middleCategories);
    }

    @Test
    @DisplayName("주어진 대분류, 중분류와 일치하는 소분류를 중복없이 조회")
    void selectSubCategories() {
        String mainCategory = "상온";
        String middleCategory = "식품";

        List<SubCategoryDTO> subCategories = categoryMapper.selectSubCategories(mainCategory, middleCategory);

        log.info(subCategories);
    }
}
