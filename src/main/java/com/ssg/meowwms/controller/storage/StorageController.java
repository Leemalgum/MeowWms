package com.ssg.meowwms.controller.storage;

import com.ssg.meowwms.dto.category.MainCategoryDTO;
import com.ssg.meowwms.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * 입고 관리
 */
@Controller
@RequestMapping("/storage")
@RequiredArgsConstructor
public class StorageController {
    private final CategoryService categoryService;

    /**
     * 입고 요청 페이지를 불러옵니다.
     *
     * @return
     */
    @GetMapping("/request")
    public String getRequest(Model model) {
        List<String> mainCategories = new ArrayList<>();

        List<MainCategoryDTO> mainCategoryDTOList = categoryService.getMainCategories();
        mainCategoryDTOList.forEach(mainCategoryDTO -> mainCategories.add(mainCategoryDTO.getMainCategory()));

        model.addAttribute("mainCategories", mainCategories);

        return "views/storage/request";
    }
}
