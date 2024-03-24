package com.ssg.meowwms.service;

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
    public void insertStocktaking(){
        StockTakingDTO stockTakingDTO = StockTakingDTO.builder()
                .stockTakingId(99)
                .stockId(1)
                .plannedDate(new Date())
                .inspector("김태진")
                .status(Status.완료)
                .statusDetail("")
                .build();

        stockTakingService.insertStocktaking(stockTakingDTO);
    }

    @Test
    @Transactional
    public void updateStocktaking(){
        StockTakingDTO stockTakingDTO = StockTakingDTO.builder()
                .stockTakingId(1)
                .stockId(1)
                .plannedDate(new Date())
                .inspector("김태진")
                .status(Status.완료)
                .statusDetail("")
                .build();
        stockTakingService.selectOneStocktaking(stockTakingDTO.getStockTakingId());
        stockTakingService.updateStocktaking(stockTakingDTO);
    }
    @Test
    @Transactional
    public void deleteStocktaking(){
        stockTakingService.deleteStocktaking(1);
    }
    @Test
    public void selectAllStocktaking(){
        stockTakingService.selectAllStocktaking();
    }
    @Test
    public void selectOneStocktaking(){
        stockTakingService.selectOneStocktaking(1);
    }

}
