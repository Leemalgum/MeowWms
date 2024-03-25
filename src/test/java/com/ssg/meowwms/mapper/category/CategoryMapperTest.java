package com.ssg.meowwms.mapper.category;

import com.ssg.meowwms.domain.category.CategoryVO;
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
}
