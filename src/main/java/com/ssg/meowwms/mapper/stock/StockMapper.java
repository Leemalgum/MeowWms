package com.ssg.meowwms.mapper.stock;

import com.ssg.meowwms.domain.stock.ProductStatusVO;
import com.ssg.meowwms.domain.stock.StockVO;
import com.ssg.meowwms.domain.stock.WarehouseStatusVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StockMapper {
    List<StockVO> selectAllStock();
    List<StockVO> selectStockByMainCategory(int categoryId);
    List<StockVO> selectStockByMiddleCategory(int categoryId);
    List<StockVO> selectStockBySubCategory(int categoryId);

    List<ProductStatusVO> selectProductStatusList();
    List<WarehouseStatusVO> selectWarehouseStatusList();


}
