package com.ssg.meowwms.mapper.storage;

import com.ssg.meowwms.domain.StockMovementVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StockMovementMapper {
    int insertStock(StockMovementVO stockMovementVO);
    int updateStockMovementStatus(int requestId, String statusCode);
    void updateStockMovement(StockMovementVO stockMovementVO);
    List<StockMovementVO> selectAllStockMovements();
    List<StockMovementVO> selectMovementByStatus(String statusCode);
    List<StockMovementVO> selectStockMovementsById(int productId);

}
