package com.ssg.meowwms.controller.stock;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssg.meowwms.domain.stock.StockTakingVO;
import com.ssg.meowwms.domain.stock.StockVO;
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
@RequestMapping("/stockTaking")
@RequiredArgsConstructor
public class StockTakingController {

    private final StockService stockService;
    private final StockTakingService stockTakingService;

    @GetMapping("/list")
    public String showStockTaking(Model model) {
        model.addAttribute("stList", stockTakingService.selectAllStocktaking());
        return "views/stock/StockTaking";
    }

    @ResponseBody
    @GetMapping("/details")
    public ResponseEntity<List<StockTakingDetailDTO>> getStockTakingDetails(@RequestParam("stockTakingId") int stockTakingId) {
        try {
            List<StockTakingDetailDTO> details = stockTakingService.selectSTDetail(stockTakingId);
            return new ResponseEntity<>(details, HttpStatus.OK);
        } catch (Exception e) {
            // 로그 기록, 오류 처리 등
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateStockTaking")
    public ResponseEntity<String> updateStockTaking(@RequestBody StockTakingDTO stockTakingDTO) {
        try {
            stockTakingService.updateStocktaking(stockTakingDTO);
            return ResponseEntity.ok("Stocktaking data updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update stocktaking data: " + e.getMessage());
        }
    }

    @PostMapping("/insertStockTaking")
    public ResponseEntity<String> insertStockTaking(@RequestBody StockTakingDTO stockTakingDTO) {
        try {

            stockTakingDTO.setStockId(stockService.selectMaxStockId() + 1);
            stockTakingService.insertStocktaking(stockTakingDTO);

            StockVO stockVO = StockVO.builder()
                    .stockId(stockTakingDTO.getStockId())
                    .warehouseId(stockTakingDTO.getWarehouseId())
                    .productId(1)
                    .quantity(100)
                    .build();
            stockService.insertStock(stockVO);
            return ResponseEntity.ok("Stocktaking data inserted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to insert stocktaking data: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{stockTakingId}")
    public ResponseEntity<String> deleteStockTaking(@PathVariable int stockTakingId) {
        try {
            int stockId = stockTakingService.selectOneStocktaking(stockTakingId).getStockId();
            stockTakingService.deleteStocktaking(stockTakingId);
            stockService.deleteStock(stockId);

            return ResponseEntity.ok("Stocktaking data deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete stocktaking data: " + e.getMessage());
        }
    }


}
