package com.ssg.meowwms.mapper.stock;

import com.ssg.meowwms.domain.stock.StockTakingVO;

public interface StockTakingMapper {
    void insertStocktaking(StockTakingVO stockTakingVO);
    void updateStocktaking(StockTakingVO stockTakingVO);
    void deleteStocktaking(int stockTakingId);
    void selectAllStocktaking();
    void selectOneStocktaking(int stockTakingId);
}
