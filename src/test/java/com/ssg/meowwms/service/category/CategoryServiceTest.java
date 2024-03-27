package com.ssg.meowwms.service.category;

import com.ssg.meowwms.dto.category.CategoryDTO;
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
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    @DisplayName("카테고리 전체 조회 서비스 테스트")
    void getAllCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();

        log.info(categories);
    }

    @Test
    @DisplayName("대분류만 중복 없이 조회")
    void getMainCategories() {
        List<MainCategoryDTO> mainCategories = categoryService.getMainCategories();

        log.info(mainCategories);
    }

    @Test
    @DisplayName("주어진 대분류와 일치하는 중분류를 중복 없이 조회")
    void getMiddleCategories() {
        String mainCategory = "상온";

        List<MiddleCategoryDTO> middleCategories = categoryService.getMiddleCategories(mainCategory);

        log.info(middleCategories);
    }

    @Test
    @DisplayName("주어진 대분류, 중분류와 일치하는 소분류를 중복 없이 조회")
    void getSubCategories() {
        String mainCategory = "상온";
        String middleCategory = "식품";

        List<SubCategoryDTO> subCategories = categoryService.getSubCategories(mainCategory, middleCategory);

        log.info(subCategories);
    }

    @Test
    @DisplayName("주어진 대분류, 중분류, 소분류와 일치하는 데이터를 조회")
    void getWithCategories() {
        String mainCategory = "상온";
        String middleCategory = "식품";
        String subCategory = "과자";

        CategoryDTO categoryDTO = categoryService.getWithCategories(mainCategory, middleCategory, subCategory);

        log.info(categoryDTO);
    }
}
