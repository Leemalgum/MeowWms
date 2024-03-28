package com.ssg.meowwms.mapper.storage;

import com.ssg.meowwms.domain.storage.ProductVO;
import com.ssg.meowwms.domain.storage.StockMovementVO;
import com.ssg.meowwms.dto.storage.MergeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface StockMovementMapper {
    int insertStock(StockMovementVO stockMovementVO);
    void updateStockMovementStatus(@Param("requestId") int requestId, @Param("statusCode") String statusCode);
    void updateStockMovement(@Param("requestId") int requestId, @Param("requestDatetime") LocalDate requestDatetime);
    List<StockMovementVO> selectAllStockMovements();
    List<StockMovementVO> selectMovementByStatus(String statusCode);
    List<StockMovementVO> selectStockMovementsById(int productId);
    List<StockMovementVO> searchStockMovements(@Param("searchKeyword") String searchKeyword);
    List<MergeDTO> searchMergedLists(@Param("params") Map<String, Object> params);

    @Update("UPDATE Stock_Movement SET approved_datetime = NOW() WHERE product_id = #{requestId}")
    void updateApprovedDatetime(@Param("requestId") int requestId);



}
