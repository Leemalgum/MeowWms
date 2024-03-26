package com.ssg.meowwms.controller.storage;

import com.ssg.meowwms.dto.category.MainCategoryDTO;
import com.ssg.meowwms.dto.storage.MergeDTO;
import com.ssg.meowwms.service.category.CategoryService;
import com.ssg.meowwms.service.storage.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/storage")
@RequiredArgsConstructor
public class StockMovementController {
    private final StorageService storageService;
    private final CategoryService categoryService;


    @GetMapping("/incoming")
    public String getCurrentIncoming(Model model) {
        model.addAttribute("mergeLists", storageService.mergeLists());

        List<String> mainCategories = new ArrayList<>();
        List<MainCategoryDTO> mainCategoryDTOList = categoryService.getMainCategories();
        mainCategoryDTOList.forEach(mainCategoryDTO -> mainCategories.add(mainCategoryDTO.getMainCategory()));
        model.addAttribute("mainCategories", mainCategories);

        return "views/storage/incoming";
    }
}
