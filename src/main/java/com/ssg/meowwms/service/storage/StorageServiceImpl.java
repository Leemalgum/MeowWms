package com.ssg.meowwms.service.storage;

import com.ssg.meowwms.domain.stock.StockVO;
import com.ssg.meowwms.domain.storage.ProductVO;
import com.ssg.meowwms.domain.storage.StockMovementVO;
import com.ssg.meowwms.dto.storage.ProductDTO;
import com.ssg.meowwms.dto.storage.StockMovementDTO;
import com.ssg.meowwms.dto.stock.StockDTO;
import com.ssg.meowwms.mapper.storage.ProductMapper;
import com.ssg.meowwms.mapper.storage.StockMovementMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor

public class StorageServiceImpl implements StorageService {
    private final ProductMapper productMapper;
    private final ModelMapper modelMapper;
    private final StockMovementMapper stockMovementMapper;
    private final JdbcTemplate jdbcTemplate; // jdbcTemplate 추가


    @Override
    public int registerProduct(ProductDTO productDTO) {
        ProductVO productVO = modelMapper.map(productDTO, ProductVO.class);
        productMapper.productInsert(productVO);
        Long lastInsertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        return lastInsertedId != null ? lastInsertedId.intValue() : -1;
    }

    @Override
    public void registerStorage(StockMovementDTO stockMovementDTO) {
        StockMovementVO stockMovementVO = modelMapper.map(stockMovementDTO, StockMovementVO.class);
        stockMovementMapper.insertStock(stockMovementVO);
    }

    @Override
    public void approveStorageRequest(int requestId) {
        stockMovementMapper.updateStockMovementStatus(requestId, "IC");

    }

    @Override
    @Transactional
    public void cancelStorageRequest(int requestId) {
        stockMovementMapper.updateStockMovementStatus(requestId, "ID");
    }

    @Override
    public int modifyProduct(int requestId, ProductDTO productDTO) {
        ProductVO productVO = modelMapper.map(productDTO, ProductVO.class);
        productMapper.productUpdate(requestId, productVO);
        return requestId;
    }

    @Override
    public void modifyStorageRequest(int requestId) {
        stockMovementMapper.updateStockMovement(requestId, LocalDate.now());
    }

    @Override
    public List<StockMovementDTO> getStorageList() {
        List<StockMovementVO> stockMovementVOList = stockMovementMapper.selectAllStockMovements();

        return stockMovementVOList.stream()
                .map(stockMovementVO -> modelMapper.map(stockMovementVO,StockMovementDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public List<StockMovementDTO> selectMovementByStatus(String statusCode){
        List<StockMovementVO> stockMovementVOList = stockMovementMapper.selectMovementByStatus(statusCode);
        return stockMovementVOList.stream()
                .map(stockMovementVO ->  modelMapper.map(stockMovementVO, StockMovementDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public List<StockMovementDTO> selectStockMovementsById(int productId){
        List<StockMovementVO> stockMovementVOList = stockMovementMapper.selectStockMovementsById(productId);
        return stockMovementVOList.stream()
                .map(stockMovementVO ->  modelMapper.map(stockMovementVO, StockMovementDTO.class))
                .collect(Collectors.toList());
    }


    //qr 해결안됨
    @Override
    public String generateQrCodeContent(StockDTO stockDTO, StockMovementDTO stockMovementDTO, ProductDTO productDTO) {
        String content = "요청 정보 \n ------------------------"
                + "\n사용자 아이디: " + stockMovementDTO.getUserId()
                + "\n창고 아이디: " + stockDTO.getWarehouseId()
                + "\n제품 아이디: " + stockDTO.getProductId()
                + "\n요청 날짜: " + stockMovementDTO.getRequestDatetime()
                + "\n승인 날짜: " + stockMovementDTO.getApprovedDatetime()
                + "\n상품 정보 \n ------------------------"
                + "\n상품명: " + productDTO.getName()
                + "\n수량: " + productDTO.getQuantity()
                + "\n종류: " + productDTO.getCategoryId();
        return content;
    }

    @Override
    public Blob convertBase64ToBlob(String base64Image) {
        try {
            byte[] byteArray = Base64.getDecoder().decode(base64Image);
            return new SerialBlob(byteArray);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public void insertQrContent(String base64Image, StockDTO stockDTO, StockMovementDTO stockMovementDTO, ProductDTO productDTO) {
        String qrContent = generateQrCodeContent(stockDTO, stockMovementDTO, productDTO);
        //saveQrCodeToDatabase(base64Image, qrContent)

    }
    @Override
    public void getQrCode() {


    }
}
