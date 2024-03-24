package com.ssg.meowwms.service.category;

import com.ssg.meowwms.dto.category.CategoryDTO;
import com.ssg.meowwms.dto.category.MainCategoryDTO;
import com.ssg.meowwms.dto.category.MiddleCategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();
    List<MainCategoryDTO> getMainCategories();
    List<MiddleCategoryDTO> getMiddleCategories(String mainCategory);
}
