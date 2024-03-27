package com.ssg.meowwms.controller.stock;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssg.meowwms.dto.stock.*;
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


    @GetMapping("/list")
    public String showStockList(Model model) {
        model.addAttribute("stockList", stockService.stockList());
        return "views/stock/Stock";
    }





    @GetMapping("/getMainCategories")
    public ResponseEntity<List<StockDTO>> getMainCategories() {
        try {
            List<StockDTO> mainCategories = stockService.getStockByMainCategory();
            log.info(mainCategories);
            return new ResponseEntity<>(mainCategories, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while fetching main categories: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getMiddleCategories")
    public ResponseEntity<List<StockDTO>> getMiddleCategories(@RequestParam String mainCategory) {
        try {
            List<StockDTO> middleCategories = stockService.getStockByMiddleCategory(mainCategory);
            log.info(middleCategories);
            return new ResponseEntity<>(middleCategories, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while fetching middle categories: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getSubCategories")
    public ResponseEntity<List<StockDTO>> getSubCategories(@RequestParam String middleCategory) {
        try {
            List<StockDTO> subCategories = stockService.getStockBySubCategory(middleCategory);
            log.info(subCategories);
            return new ResponseEntity<>(subCategories, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while fetching small categories: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/productStatus")
    public String showProductStatus(Model model)
//            ,
//                                    @RequestParam(required = false) Date from,
//                                    @RequestParam(required = false) Date to,
//                                    @RequestParam(required = false) String searchTerm,
//                                    @RequestParam(required = false) String mainCategory,
//                                    @RequestParam(required = false) String middleCategory,
//                                    @RequestParam(required = false) String subCategory)
    {
//        model.addAttribute("productList", stockService.getProductStatusList(
//                from, to, searchTerm
//                , mainCategory, middleCategory, subCategory
//        ));

        model.addAttribute("productList", stockService.getProductStatusList());
        return "views/stock/ProductStatus";
    }

    @GetMapping("/stock/productStatus")
    public ResponseEntity<Object> getProductStatusList(
//            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String from,
//            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String to,
//            @RequestParam(required = false) String searchTerm,
//            @RequestParam(required = false) String mainCategory,
//            @RequestParam(required = false) String middleCategory,
//            @RequestParam(required = false) String subCategory
    ) {
        try {
            // Convert string dates to Date objects
            Date fromDate = null;
            Date toDate = null;

//            if (from != null && !from.isEmpty()) {
//                fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(from);
//            }
//
//            if (to != null && !to.isEmpty()) {
//                toDate = new SimpleDateFormat("yyyy-MM-dd").parse(to);
//            }

            // Proceed with fetching data based on the provided date range
//            List<ProductStatusDTO> productStatusList = stockService.getProductStatusList(
//                    fromDate, toDate, searchTerm
//                    , mainCategory, middleCategory, subCategory);
//            );
            List<ProductStatusDTO> productStatusList = stockService.getProductStatusList();
            log.info("pp" + productStatusList);
            return new ResponseEntity<>(productStatusList, HttpStatus.OK);
        } catch (Exception e) {
            // Handle any exceptions
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @GetMapping("/warehouseStatus")
    public String showWarehouseStatus(Model model) {
        model.addAttribute("warehouseList", stockService.getWarehouseStatusList());
        return "views/stock/WarehouseStatus";
    }


    @GetMapping("/stock/warehouseStatus")
    public ResponseEntity<Object> getWarehouseStatusList(

    ) {
        try {
            List<WarehouseStatusDTO> warehouseStatusList = stockService.getWarehouseStatusList();
            log.info("pp" + warehouseStatusList);
            return new ResponseEntity<>(warehouseStatusList, HttpStatus.OK);
        } catch (Exception e) {
            // Handle any exceptions
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
