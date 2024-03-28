package com.ssg.meowwms.controller.storage;

import com.ssg.meowwms.dto.category.MainCategoryDTO;
import com.ssg.meowwms.dto.category.MiddleCategoryDTO;
import com.ssg.meowwms.dto.category.SubCategoryDTO;
import com.ssg.meowwms.dto.storage.ProductDTO;
import com.ssg.meowwms.dto.storage.StockMovementDTO;
import com.ssg.meowwms.dto.warehouse.WarehouseDTO;
import com.ssg.meowwms.security.SecurityUtils;
import com.ssg.meowwms.service.category.CategoryService;
import com.ssg.meowwms.service.storage.StorageService;
import com.ssg.meowwms.service.warehouse.WarehouseService;
import com.ssg.meowwms.util.StockMovementStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 입고 관리
 */
@Controller
@RequestMapping("/storage")
@RequiredArgsConstructor
@Log4j2
public class StorageController {
    private final CategoryService categoryService;
    private final WarehouseService warehouseService;
    private final StorageService storageService;

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
     * 대분류, 중뷴류, 소분류 값을 받아 일치하는 창고 목록을 반환합니다.
     *
     * @param mainCategory
     * @param middleCategory
     * @param subCategory
     * @return 창고 목록
     */
    @GetMapping("/getWarehouseWithCategory")
    public @ResponseBody List<WarehouseDTO> getWarehouseWithCategory(
            @RequestParam("mainCategory") String mainCategory,
            @RequestParam("middleCategory") String middleCategory,
            @RequestParam("subCategory") String subCategory) {

        String category = mainCategory + " | " + middleCategory + " | " + subCategory;

        return warehouseService.getWarehouseWithCategory(category);
    }

    /**
     * 입고 요청을 처리합니다.
     *
     * @param productName
     * @param mainCategory
     * @param middleCategory
     * @param subCategory
     * @param warehouseId
     * @param productBrand
     * @param productPrice
     * @param productSalePrice
     * @param productQuantity
     * @param productVolume
     * @return
     */
    @PostMapping("request")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<String> requestStorage(
            @RequestParam("productName") String productName,
            @RequestParam("mainType") String mainCategory,
            @RequestParam("middleType") String middleCategory,
            @RequestParam("subType") String subCategory,
            @RequestParam("warehouseId") int warehouseId,
            @RequestParam("productBrand") String productBrand,
            @RequestParam("productPrice") int productPrice,
            @RequestParam("productSalePrice") int productSalePrice,
            @RequestParam("productQuantity") int productQuantity,
            @RequestParam("productVolume") int productVolume
    ) {

        try {
            int categoryId = categoryService.getWithCategories(mainCategory, middleCategory, subCategory).getId();

            ProductDTO productDTO = ProductDTO.builder()
                    .categoryId(categoryId)
                    .name(productName)
                    .brand(productBrand)
                    .price(productPrice)
                    .salePrice(productSalePrice)
                    .quantity(productQuantity)
                    .volume(productVolume)
                    .userId(SecurityUtils.getCurrentUserDetails().getUsername())
                    .build();

            int productId = storageService.registerProduct(productDTO);

            StockMovementDTO stockMovementDTO = StockMovementDTO.builder()
                    .productId(productId)
                    .userId(SecurityUtils.getCurrentUserDetails().getUsername())
                    .statusCode(StockMovementStatus.REQUESTED.getCode())
                    .requestDatetime(LocalDate.now())
                    .warehouseId(warehouseId)
                    .build();

            storageService.registerStorage(stockMovementDTO);

            return ResponseEntity.ok().body("{\"success\": true}");
        } catch (Exception e) {
            log.error("입고 요청 실패");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"success\": false}");
        }
    }
}