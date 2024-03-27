package com.ssg.meowwms.mapper.stock;

import com.ssg.meowwms.domain.stock.StockTakingDetailVO;
import com.ssg.meowwms.domain.stock.StockTakingVO;
import com.ssg.meowwms.domain.stock.StockVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface StockTakingMapper {
    /**
     * TODO::Duplicate Key 때 오류문 생성 필요
     * @param stockTakingVO
     */
    void insertStocktaking(StockTakingVO stockTakingVO);
    void updateStocktaking(StockTakingVO stockTakingVO);
    void deleteStocktaking(int stockTakingId);
    List<StockTakingVO> selectAllStocktaking();
    StockTakingVO selectOneStocktaking(int stockTakingId);

    List<StockTakingDetailVO> selectSTDetail(int stockId);

    StockTakingDetailVO selectActualStock(int warehouseId);

    int selectMaxStockTakingId();
}
