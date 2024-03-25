package com.ssg.meowwms.mapper.storage;

import com.ssg.meowwms.domain.storage.StockMovementVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface StockMovementMapper {
    int insertStock(StockMovementVO stockMovementVO);
    void updateStockMovementStatus(@Param("requestId") int requestId, @Param("statusCode") String statusCode);
    void updateStockMovement(@Param("requestId") int requestId, @Param("requestDatetime") LocalDate requestDatetime);
    List<StockMovementVO> selectAllStockMovements();
    List<StockMovementVO> selectMovementByStatus(String statusCode);
    List<StockMovementVO> selectStockMovementsById(int productId);

}
