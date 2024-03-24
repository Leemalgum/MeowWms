package com.ssg.meowwms.service.category;

import com.ssg.meowwms.mapper.category.CategoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl {
    private final CategoryMapper categoryMapper;
    private final ModelMapper modelMapper;
}
