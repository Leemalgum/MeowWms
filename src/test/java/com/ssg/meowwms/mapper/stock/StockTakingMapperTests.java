package com.ssg.meowwms.mapper.stock;

import com.ssg.meowwms.domain.stock.Status;
import com.ssg.meowwms.domain.stock.StockTakingVO;
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
public class StockTakingMapperTests {
    @Autowired(required = false)
    private StockTakingMapper stockTakingMapper;

    @Test
    @Transactional
    public void testInsertStocktaking() {
        log.info("/test Insert StockTaking...");

        StockTakingVO stockTakingVO = new StockTakingVO().builder()
                .stockTakingId(99)
                .stockId(1)
                .plannedDate(new Date())
                .inspector("김태진")
                .status(Status.완료)
                .statusDetail("검사 완료")
                .build();

        try {
            stockTakingMapper.insertStocktaking(stockTakingVO);
            log.info("Insertion made");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @Transactional
    public void testSelectOneStocktaking() {
        int stockTakingId = 1;
        log.info(stockTakingMapper.selectOneStocktaking(stockTakingId));
        stockTakingMapper.selectOneStocktaking(stockTakingId);
    }

    @Test
    @Transactional
    public void testUpdateStocktaking() {
        log.info("/test Update StockTaking...");
        StockTakingVO stockTakingVO = new StockTakingVO().builder()
                .stockTakingId(1)
                .stockId(1)
                .plannedDate(new Date())
                .inspector("김태진")
                .status(Status.완료)
                .statusDetail("검사 완료")
                .build();
        try {
            if(stockTakingMapper.selectOneStocktaking(stockTakingVO.getStockTakingId()) != null) {
                stockTakingMapper.updateStocktaking(stockTakingVO);
                log.info("Update done");
            } else {
                log.error("Update Fail : Following StockTaking Does Not Exist");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void testDeleteStocktaking() {
        int stockTakingId = 99;
        try {
            if(stockTakingMapper.selectOneStocktaking(stockTakingId) != null) {
                stockTakingMapper.deleteStocktaking(stockTakingId);
                log.info("Deletion Made");
            } else {
                log.error("Deletion Fail : StockTaking Not Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void testSelectAllStockTaking() {
        log.info("StockTaking Select All...");
        log.info(stockTakingMapper.selectAllStocktaking());
        stockTakingMapper.selectAllStocktaking();
    }

    @Test
    public void testSelectSTDetail() {
        int stid= 1;
        log.info("StockTaking select ST detail...");
        log.info(stockTakingMapper.selectSTDetail(stid));
        stockTakingMapper.selectSTDetail(stid);
    }
}
