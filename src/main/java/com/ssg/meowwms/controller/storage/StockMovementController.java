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
    @GetMapping("/mergeLists")
    public List<MergeDTO> getMergedList(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate requestDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate approvedDate,
            @RequestParam(required = false) String adminID,
            @RequestParam(required = false) String userID,
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) Integer productCategory,
            @RequestParam(required = false) Integer warehouseId) {

        List<MergeDTO> mergedList = storageService.mergeLists();

        // 필터링 조건에 따라 데이터를 필터링
        if (requestDate != null) {
            mergedList = mergedList.stream()
                    .filter(dto -> dto.getMovementRequestDate().equals(requestDate))
                    .collect(Collectors.toList());
        }

        if (approvedDate != null) {
            mergedList = mergedList.stream()
                    .filter(dto -> dto.getMovementApprovedDate().equals(approvedDate))
                    .collect(Collectors.toList());
        }

        if (adminID != null) {
            mergedList = mergedList.stream()
                    .filter(dto -> dto.getMovementUserId().equals(adminID))
                    .collect(Collectors.toList());
        }

        if (userID != null) {
            mergedList = mergedList.stream()
                    .filter(dto -> dto.getMovementUserId().equals(userID))
                    .collect(Collectors.toList());
        }

        if (productName != null) {
            mergedList = mergedList.stream()
                    .filter(dto -> dto.getProductName().contains(productName))
                    .collect(Collectors.toList());
        }

        if (productCategory != null) {
            mergedList = mergedList.stream()
                    .filter(dto -> dto.getProductCategory() == productCategory)
                    .collect(Collectors.toList());
        }

        if (warehouseId != null) {
            mergedList = mergedList.stream()
                    .filter(dto -> dto.getMovementWarehouseId() == warehouseId)
                    .collect(Collectors.toList());
        }

        return mergedList;
    }
}
