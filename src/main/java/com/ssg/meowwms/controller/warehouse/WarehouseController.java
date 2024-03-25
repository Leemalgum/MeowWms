package com.ssg.meowwms.controller.warehouse;

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
 * 창고 관리
 */
@Controller
@RequestMapping("/warehouse")
@RequiredArgsConstructor
public class WarehouseController {
    private final CategoryService categoryService;

    /**
     * 창고 등록 페이지를 불러옵니다.
     *
     * @param model
     * @return
     */
    @GetMapping("/register")
    public String getRegister(Model model) {
        List<String> mainCategories = new ArrayList<>();

        List<MainCategoryDTO> mainCategoryDTOList = categoryService.getMainCategories();
        mainCategoryDTOList.forEach(mainCategoryDTO -> mainCategories.add(mainCategoryDTO.getMainCategory()));

        model.addAttribute("mainCategories", mainCategories);

        return "views/warehouse/register";
    }

    /**
     * 창고 목록 페이지를 불러옵니다.
     *
     * @return
     */
    @GetMapping("/list")
    public String getList() {
        return "views/warehouse/list";
    }

    /**
     * 창고 상세 페이지를 불러옵니다.
     *
     * @return
     */
    @GetMapping("/read")
    public String getRead() {
        return "views/warehouse/read";
    }
}
