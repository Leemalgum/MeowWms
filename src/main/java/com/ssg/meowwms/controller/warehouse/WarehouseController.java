package com.ssg.meowwms.controller.warehouse;

import com.ssg.meowwms.dto.category.MainCategoryDTO;
import com.ssg.meowwms.dto.category.MiddleCategoryDTO;
import com.ssg.meowwms.dto.category.SubCategoryDTO;
import com.ssg.meowwms.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
     * 대분류에 해당하는 중분류 데이터를 가져옵니다.
     *
     * @param mainCategory 대분류
     * @return 중분류 리스트
     */
    @GetMapping("/getMiddleCategories")
    public @ResponseBody List<String> getMiddleCategories(@RequestParam("mainCategory") String mainCategory) {
        List<String> middleCategories = new ArrayList<>();

        List<MiddleCategoryDTO> middleCategoryDTOList = categoryService.getMiddleCategories(mainCategory);
        middleCategoryDTOList.forEach(middleCategoryDTO -> middleCategories.add(middleCategoryDTO.getMiddleCategory()));

        return middleCategories;
    }

    /**
     * 주어진 대분류, 중분류와 일치하는 소분류 데이터를 가져옵니다.
     *
     * @param mainCategory
     * @param middleCategory
     * @return 소분류 리스트
     */
    @GetMapping("/getSubCategories")
    public @ResponseBody List<String> getSubCategories(
            @RequestParam("mainCategory") String mainCategory,
            @RequestParam("middleCategory") String middleCategory) {
        List<String> subCategories = new ArrayList<>();

        List<SubCategoryDTO> subCategoryDTOList = categoryService.getSubCategories(mainCategory, middleCategory);
        subCategoryDTOList.forEach(subCategoryDTO -> subCategories.add(subCategoryDTO.getSubCategory()));

        return subCategories;
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
