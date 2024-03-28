package com.ssg.meowwms.mapper.stock;

import com.ssg.meowwms.domain.stock.ProductStatusVO;
import com.ssg.meowwms.domain.stock.StockVO;
import com.ssg.meowwms.domain.stock.WarehouseStatusVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Mapper
public interface StockMapper {
    List<StockVO> selectAllStock();
    List<StockVO> selectStockByMainCategory();
    List<StockVO> selectStockByMiddleCategory(String middleCategory);
    List<StockVO> selectStockBySubCategory(String subCategory);

    // TODO:: replace with VO?
    List<ProductStatusVO> selectProductStatusList(
//            @Param("from") Date from,
//            @Param("to") Date to,
//            @Param("searchTerm") String searchTerm,
//            @Param("mainCategory") String mainCategory,
//            @Param("middleCategory") String middleCategory,
//            @Param("subCategory") String subCategory
    );
    List<WarehouseStatusVO> selectWarehouseStatusList(
//            @Param("warehouseName") String warehouseName,
//            @Param("mainCategory") String mainCategory,
//            @Param("middleCategory") String middleCategory,
//            @Param("subCategory") String subCategory
    );

    void insertStock(StockVO stockVO);

    int selectMaxStockId();

    void deleteStock(int stockId);
}
