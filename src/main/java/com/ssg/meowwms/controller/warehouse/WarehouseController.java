package com.ssg.meowwms.controller.warehouse;

import com.ssg.meowwms.dto.category.MainCategoryDTO;
import com.ssg.meowwms.dto.category.MiddleCategoryDTO;
import com.ssg.meowwms.dto.category.SubCategoryDTO;
import com.ssg.meowwms.dto.user.UserDTO;
import com.ssg.meowwms.dto.warehouse.WarehouseDTO;
import com.ssg.meowwms.service.category.CategoryService;
import com.ssg.meowwms.service.user.UserService;
import com.ssg.meowwms.service.warehouse.WarehouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.*;

/**
 * 창고 관리
 */
@Controller
@RequestMapping("/warehouse")
@RequiredArgsConstructor
@Log4j2
public class WarehouseController {
    private final CategoryService categoryService;
    private final UserService userService;
    private final WarehouseService warehouseService;

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

        List<UserDTO> warehouseManagerList = userService.getWarehouseManager();

        model.addAttribute("warehouseManagerList", warehouseManagerList);

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
     * 창고 이름 중복 확인을 처리합니다.
     *
     * @param warehouseName 창고 이름
     * @return 중복 여부
     */
    @GetMapping("/checkDuplicate")
    public @ResponseBody Map<String, Boolean> checkDuplicateWarehouseName(
            @RequestParam("warehouseName") String warehouseName) {
        boolean isDuplicate = isWarehouseNameDuplicate(warehouseName);

        Map<String, Boolean> response = new HashMap<>();
        response.put("duplicate", isDuplicate);

        return response;
    }

    /**
     * 창고 이름이 중복되는지 확인합니다.
     *
     * @param warehouseName 창고 이름
     * @return 중복 여부
     */
    private boolean isWarehouseNameDuplicate(String warehouseName) {
        return warehouseService.getWarehouseIdByName(warehouseName).orElse(0) != 0;
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<String> registerWarehouse(
            @RequestParam("warehouseName") String warehouseName,
            @RequestParam("mainType") String mainCategory,
            @RequestParam("middleType") String middleCategory,
            @RequestParam("subType") String subCategory,
            @RequestParam("warehouseManagerId") String warehouseManagerId,
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude,
            @RequestParam("volume") int volume
    ) {
        log.info("창고 등록 컨트롤러...!!!");

        try {

            int categoryId = categoryService.getWithCategories(mainCategory, middleCategory, subCategory).getId();

            WarehouseDTO warehouseDTO = WarehouseDTO.builder()
                    .categoryId(categoryId)
                    .name(warehouseName)
                    .latitude(latitude)
                    .longitude(longitude)
                    .volume(volume)
                    .managerId(warehouseManagerId)
                    .build();

            warehouseService.register(warehouseDTO);

            return ResponseEntity.ok().body("{\"success\": true}");
        } catch (DataAccessException e) {
            log.error("창고 등록 실패");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"success\": false}");
        }
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
