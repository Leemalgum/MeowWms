package com.ssg.meowwms.service.stock;

import com.ssg.meowwms.dto.stock.ProductStatusDTO;
import com.ssg.meowwms.dto.stock.StockDTO;
import com.ssg.meowwms.dto.stock.WarehouseStatusDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


public interface StockService {
    List<StockDTO> stockList();
    List<StockDTO> getStockByMainCategory(String mainCategory);
    List<StockDTO> getStockByMiddleCategory(String middleCategory);
    List<StockDTO> getStockBySubCategory(String subCategory);
    List<ProductStatusDTO> getProductStatusList(
            Date from,
            Date to,
            String searchTerm,
            String mainCategory,
            String middleCategory,
            String subCategory,
            int productId,
            int warehouseId
    );
    List<WarehouseStatusDTO> getWarehouseStatusList(
            String warehouseName,
            String mainCategory,
            String middleCategory,
            String subCategory
    );

}
