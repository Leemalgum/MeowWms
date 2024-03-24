package com.ssg.meowwms.mapper.category;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Log4j2
public class CategoryMapperTest {

    @Autowired
    private CategoryMapper categoryMapper;
}
