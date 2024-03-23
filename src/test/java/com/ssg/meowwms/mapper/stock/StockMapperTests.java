package com.ssg.meowwms.mapper.stock;

import com.ssg.meowwms.mapper.stock.StockMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class StockMapperTests {
    @Autowired(required = false)
    private StockMapper stockMapper;

    @Test
    public void testSelectAllStock() {
        stockMapper.selectAllStock();
    }

    @Test
    public void testSelectStockByMainCategory() {
        stockMapper.selectStockByMainCategory(1);
    }

    @Test
    public void testSelectStockByMiddleCategory() {
        stockMapper.selectStockByMiddleCategory(2);
    }

    @Test
    public void testSelectStockBySubCategory() {
        stockMapper.selectStockBySubCategory(3);
    }

//    @Test
//    public void testSelectProductStatusList() {
//        stockMapper.selectProductStatusList();
//    }
}
