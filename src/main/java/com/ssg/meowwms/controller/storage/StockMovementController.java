package com.ssg.meowwms.controller.storage;

import com.ssg.meowwms.dto.category.MainCategoryDTO;
import com.ssg.meowwms.dto.storage.MergeDTO;
import com.ssg.meowwms.dto.storage.ProductDTO;
import com.ssg.meowwms.dto.storage.StockMovementDTO;
import com.ssg.meowwms.mapper.storage.QrMapper;
import com.ssg.meowwms.service.category.CategoryService;
import com.ssg.meowwms.service.storage.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/storage")
@RequiredArgsConstructor
public class StockMovementController {
    private final StorageService storageService;
    private final CategoryService categoryService;
    private final QrMapper qrMapper; // QRMapper를 주입 받음


    @GetMapping("/incoming")
    public String getCurrentIncomingOrSearch(
            @RequestParam(value = "searchType", required = false) String searchType,
            @RequestParam(value = "searchKeyword", required = false) String searchKeyword,
            Model model) {
        List<MergeDTO> mergeLists;

        // 검색어가 제공된 경우 검색 결과를 반환
        if (searchType != null && searchKeyword != null && !searchKeyword.trim().isEmpty()) {
            Map<String, Object> searchParams = new HashMap<>();
            searchParams.put(searchType, "%" + searchKeyword + "%");
            mergeLists = storageService.searchMergedLists(searchParams);
        } else {
            // 검색어가 제공되지 않은 경우 기본 리스트 반환
            mergeLists = storageService.mergeLists();
        }

        model.addAttribute("mergeLists", mergeLists);

        List<String> mainCategories = new ArrayList<>();
        List<MainCategoryDTO> mainCategoryDTOList = categoryService.getMainCategories();
        mainCategoryDTOList.forEach(mainCategoryDTO -> mainCategories.add(mainCategoryDTO.getMainCategory()));
        model.addAttribute("mainCategories", mainCategories);

        return "views/storage/incoming";
    }
    @PostMapping("/incoming")
    public ResponseEntity<Map<String, String>> approveSelectedRequests(@RequestBody List<Integer> productIds) {
        try {
            productIds.forEach(storageService::approveStorageRequest);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Selected requests have been approved successfully.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error approving requests: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/download-qr-code")
    public ResponseEntity<byte[]> downloadQRCode(@RequestParam("productId") int productId) {
        // QR 코드 이미지 데이터를 데이터베이스에서 조회하는 로직
        byte[] imageData = qrMapper.getQrCodeImageByProductId(productId);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"qrcode.png\"")
                .body(imageData);
    }



}
