package com.ssg.meowwms.service.category;

import com.ssg.meowwms.dto.category.CategoryDTO;
import com.ssg.meowwms.dto.category.MainCategoryDTO;
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
}
