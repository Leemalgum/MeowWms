package com.ssg.meowwms.controller.stock;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssg.meowwms.dto.stock.ProductStatusDTO;
import com.ssg.meowwms.dto.stock.StockTakingDTO;
import com.ssg.meowwms.dto.stock.StockTakingDetailDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import com.ssg.meowwms.service.stock.StockService;
import com.ssg.meowwms.service.stock.StockTakingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Log4j2
@Controller
@RequestMapping("/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;
    private final StockTakingService stockTakingService;

    @GetMapping("/stockTaking")
    public String showStockTaking(Model model) {
        model.addAttribute("stList", stockTakingService.selectAllStocktaking());
        return "views/stock/StockTaking";
    }

    @ResponseBody
    @GetMapping("/stockTaking/details")
    public ResponseEntity<List<StockTakingDetailDTO>> getStockTakingDetails(@RequestParam("stockTakingId") int stockTakingId) {
        try {
            List<StockTakingDetailDTO> details = stockTakingService.selectSTDetail(stockTakingId);
            return new ResponseEntity<>(details, HttpStatus.OK);
        } catch (Exception e) {
            // 로그 기록, 오류 처리 등
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/productStatus")
    public String showProductStatus(Model model,
                                    @RequestParam(required = false) Date from,
                                    @RequestParam(required = false) Date to,
                                    @RequestParam(required = false) String searchTerm,
                                    @RequestParam(required = false) String mainCategory,
                                    @RequestParam(required = false) String middleCategory,
                                    @RequestParam(required = false) String subCategory) {
        model.addAttribute("productList", stockService.getProductStatusList(
                from, to, searchTerm, mainCategory, middleCategory, subCategory
        ));
        return "views/stock/ProductStatus";
    }

    @GetMapping("/stock/productStatus")
    public ResponseEntity<Object> getProductStatusList(
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String from,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String to,
            @RequestParam(required = false) String searchTerm,
            @RequestParam(required = false) String mainCategory,
            @RequestParam(required = false) String middleCategory,
            @RequestParam(required = false) String subCategory
    ) {
        try {
            // Convert string dates to Date objects
//            Date fromDate = null;
//            Date toDate = null;

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fromDate = dateFormat.parse(from);
            Date toDate = dateFormat.parse(to);
//            if (from != null && !from.isEmpty()) {
//                fromDate = Date.from(LocalDate.parse(from).atStartOfDay(ZoneId.systemDefault()).toInstant());
//            }
//
//            if (to != null && !to.isEmpty()) {
//                toDate = Date.from(LocalDate.parse(to).atStartOfDay(ZoneId.systemDefault()).toInstant());
//            }

            // Proceed with fetching data based on the provided date range
            List<ProductStatusDTO> productStatusList = stockService.getProductStatusList(fromDate, toDate, searchTerm, mainCategory, middleCategory, subCategory);
            return new ResponseEntity<>(productStatusList, HttpStatus.OK);
        } catch (Exception e) {
            // Handle any exceptions
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/warehouseStatus")
    public String showWarehouseStatus(Model model) {
        model.addAttribute(stockTakingService.selectAllStocktaking());
        return "views/stock/WarehouseStatus";
    }
}
