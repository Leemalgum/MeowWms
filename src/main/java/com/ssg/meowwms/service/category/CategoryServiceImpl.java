package com.ssg.meowwms.service.category;

import com.ssg.meowwms.domain.category.CategoryVO;
import com.ssg.meowwms.dto.category.CategoryDTO;
import com.ssg.meowwms.dto.category.MainCategoryDTO;
import com.ssg.meowwms.dto.category.MiddleCategoryDTO;
import com.ssg.meowwms.mapper.category.CategoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryMapper categoryMapper;
    private final ModelMapper modelMapper;

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<CategoryVO> categories = categoryMapper.selectAll();

        return categories.stream()
                .map(categoryVO -> modelMapper.map(categoryVO, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<MainCategoryDTO> getMainCategories() {
        return categoryMapper.selectMainCategories();
    }

    @Override
    public List<MiddleCategoryDTO> getMiddleCategories(String mainCategory) {
        return categoryMapper.selectMiddleCategories(mainCategory);
    }
}
