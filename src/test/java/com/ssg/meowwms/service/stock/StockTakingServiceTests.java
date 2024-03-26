package com.ssg.meowwms.service.stock;

import com.ssg.meowwms.domain.stock.Status;
import com.ssg.meowwms.dto.stock.StockTakingDTO;
import com.ssg.meowwms.service.stock.StockTakingService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Log4j2
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class StockTakingServiceTests {

    @Autowired
    StockTakingService stockTakingService;

    @Test
    @Transactional
    public void testInsertStocktaking(){
        StockTakingDTO stockTakingDTO = StockTakingDTO.builder()
                .stockTakingId(99)
                .stockId(1)
                .plannedDate(new Date())
                .inspector("김태진")
                .status(Status.완료)
                .statusDetail("")
                .build();

        try {
            stockTakingService.insertStocktaking(stockTakingDTO);
            log.info("추가 됨");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @Test
    @Transactional
    public void testUpdateStocktaking(){
        StockTakingDTO stockTakingDTO = StockTakingDTO.builder()
                .stockTakingId(1)
                .stockId(1)
                .plannedDate(new Date())
                .inspector("김태진")
                .status(Status.완료)
                .statusDetail("")
                .build();
        try {
            stockTakingService.selectOneStocktaking(stockTakingDTO.getStockTakingId());
            stockTakingService.updateStocktaking(stockTakingDTO);
            log.info("업뎃됨");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    @Transactional
    public void testDeleteStocktaking(){
        try {
            stockTakingService.deleteStocktaking(1);
            log.info("지워짐");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testSelectAllStocktaking(){
        log.info(stockTakingService.selectAllStocktaking());
        stockTakingService.selectAllStocktaking();
    }
    @Test
    public void testSelectOneStocktaking(){
        log.info(stockTakingService.selectOneStocktaking(1));
        stockTakingService.selectOneStocktaking(1);
    }

    @Test
    public void testSelectSTDetail() {
        log.info(stockTakingService.selectSTDetail(1));
        stockTakingService.selectSTDetail(1);
    }

}
