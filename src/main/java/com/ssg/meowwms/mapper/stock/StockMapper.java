package com.ssg.meowwms.mapper.stock;

import com.ssg.meowwms.domain.stock.ProductStatusVO;
import com.ssg.meowwms.domain.stock.StockVO;
import com.ssg.meowwms.domain.stock.WarehouseStatusVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface StockMapper {
    List<StockVO> selectAllStock();
    List<StockVO> selectStockByMainCategory(String mainCategory);
    List<StockVO> selectStockByMiddleCategory(String middleCategoryId);
    List<StockVO> selectStockBySubCategory(String subCategoryId);

    List<ProductStatusVO> selectProductStatusList(
            Date from,
            Date to,
            String searchTerm,
            String mainCategory,
            String middleCategory,
            String subCategory,
            int productId,
            int warehouseId
    );
    List<WarehouseStatusVO> selectWarehouseStatusList();


}
