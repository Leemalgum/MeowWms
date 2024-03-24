package com.ssg.meowwms.service.stock;

import com.ssg.meowwms.domain.stock.StockTakingVO;
import com.ssg.meowwms.dto.stock.StockTakingDTO;

import java.util.List;

public interface StockTakingService {
    void insertStocktaking(StockTakingDTO stockTakingDTO);
    void updateStocktaking(StockTakingDTO stockTakingDTO);
    void deleteStocktaking(int stockTakingId);
    List<StockTakingDTO> selectAllStocktaking();
    StockTakingDTO selectOneStocktaking(int stockTakingId);
}
