package com.ssg.meowwms.service.stock;

import com.ssg.meowwms.domain.stock.ProductStatusVO;
import com.ssg.meowwms.domain.stock.StockVO;
import com.ssg.meowwms.domain.stock.WarehouseStatusVO;
import com.ssg.meowwms.dto.stock.ProductStatusDTO;
import com.ssg.meowwms.dto.stock.StockDTO;
import com.ssg.meowwms.dto.stock.WarehouseStatusDTO;
import com.ssg.meowwms.mapper.stock.StockMapper;
import com.ssg.meowwms.mapper.stock.StockTakingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockMapper stockMapper;
    private final StockTakingMapper stockTakingMapper;
    private final ModelMapper modelMapper;

    @Override
    public List<StockDTO> stockList() {
        log.info("/StockService/stockList()...");
        List<StockVO> stockVOList = stockMapper.selectAllStock();
        return stockVOList.stream()
                .map(stock ->  modelMapper.map(stock, StockDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<StockDTO> getStockByMainCategory(String mainCategory) {
        log.info("/StockService/getStockByMainCategory()...");
        List<StockVO> stockVOList = stockMapper.selectStockByMainCategory(mainCategory);

        return stockVOList.stream()
                .map(stock ->  modelMapper.map(stock, StockDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<StockDTO> getStockByMiddleCategory(String middleCategory) {
        log.info("/StockService/getStockByMiddleCategory()...");
        List<StockVO> stockVOList = stockMapper.selectStockByMiddleCategory(middleCategory);

        return stockVOList.stream()
                .map(stock ->  modelMapper.map(stock, StockDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<StockDTO> getStockBySubCategory(String subCategory) {
        log.info("/StockService/getStockBySubCategory()...");
        List<StockVO> stockVOList = stockMapper.selectStockBySubCategory(subCategory);

        return stockVOList.stream()
                .map(stock ->  modelMapper.map(stock, StockDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductStatusDTO> getProductStatusList(Date from, Date to, String searchTerm, String mainCategory, String middleCategory, String subCategory, int productId, int warehouseId) {
        log.info("/StockService/getProductStatusList()...");
        List<ProductStatusVO> productStatusVOList = stockMapper.selectProductStatusList(
                from, to, searchTerm, mainCategory, middleCategory, subCategory, productId, warehouseId
        );

        return productStatusVOList.stream()
                .map(product ->  modelMapper.map(product, ProductStatusDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<WarehouseStatusDTO> getWarehouseStatusList(String warehouseName, String mainCategory, String middleCategory, String subCategory) {
        log.info("/StockService/getWarehouseStatusList()...");
        List<WarehouseStatusVO> warehouseStatusVOList = stockMapper.selectWarehouseStatusList(
                warehouseName, mainCategory, middleCategory, subCategory
        );

        return warehouseStatusVOList.stream()
                .map(warehouse ->  modelMapper.map(warehouse, WarehouseStatusDTO.class))
                .collect(Collectors.toList());
    }
}
