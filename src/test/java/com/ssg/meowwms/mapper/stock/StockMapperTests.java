package com.ssg.meowwms.mapper.stock;

import com.ssg.meowwms.mapper.stock.StockMapper;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;


@Log4j2
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class StockMapperTests {
    @Autowired(required = false)
    private StockMapper stockMapper;

    @Test
    public void testSelectAllStock() {
        log.info("/test select all stock...");
        log.info(stockMapper.selectAllStock());
        stockMapper.selectAllStock();
    }

    @Test
    public void testSelectStockByMainCategory() {
        log.info("/test select stock by Main Category...");
        log.info(stockMapper.selectStockByMainCategory("냉장"));
        stockMapper.selectStockByMainCategory("냉장");
    }

    @Test
    public void testSelectStockByMiddleCategory() {
        log.info("/test select stock by middle Category...");
        log.info(stockMapper.selectStockByMiddleCategory("식품"));
        stockMapper.selectStockByMiddleCategory("식품");
    }

    @Test
    public void testSelectStockBySubCategory() {
        log.info("/test select stock by sub Category...");
        log.info(stockMapper.selectStockBySubCategory("유제품"));
        stockMapper.selectStockBySubCategory("유제품");
    }

    @Test
    public void testSelectProductStatusList() {
        log.info("/test select Product Status List...");
        // 현재 없는 데이터 테스팅
        log.info(stockMapper.selectProductStatusList(new Date(), new Date(), "ahhh", "냉장", "식품", "유제품", 1, 1));
        // 있는 데이터 테스팅
        log.info(stockMapper.selectProductStatusList(new Date(), new Date(), "", "냉장", "식품", "유제품", 1, 1));
        // 실제 코드
        stockMapper.selectProductStatusList(new Date(), new Date(), "1", "냉장", "식품", "유제품", 1, 1);
    }
}
