package com.ssg.meowwms.service.stock;

import com.ssg.meowwms.domain.stock.StockVO;
import com.ssg.meowwms.dto.stock.ProductStatusDTO;
import com.ssg.meowwms.dto.stock.StockDTO;
import com.ssg.meowwms.dto.stock.WarehouseStatusDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface StockService {
    List<StockDTO> stockList();
    List<StockDTO> getStockByMainCategory();
    List<StockDTO> getStockByMiddleCategory(String middleCategory);
    List<StockDTO> getStockBySubCategory(String subCategory);
    void insertStock(StockVO StockVO);

    int selectMaxStockId();

    void deleteStock(int stockId);

//    List<ProductStatusDTO> getProductStatusList(
//            Date from,
//            Date to,
//            String searchTerm
//            ,
//            String mainCategory,
//            String middleCategory,
//            String subCategory
//    );
    List<ProductStatusDTO> getProductStatusList();

    List<WarehouseStatusDTO> getWarehouseStatusList(
//            String warehouseName,
//            String mainCategory,
//            String middleCategory,
//            String subCategory
    );

}
