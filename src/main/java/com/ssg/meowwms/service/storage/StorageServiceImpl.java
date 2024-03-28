package com.ssg.meowwms.service.storage;

import com.google.zxing.WriterException;
import com.ssg.meowwms.domain.stock.StockVO;
import com.ssg.meowwms.domain.storage.ProductVO;
import com.ssg.meowwms.domain.storage.StockMovementVO;
import com.ssg.meowwms.dto.storage.ProductDTO;
import com.ssg.meowwms.dto.storage.StockMovementDTO;
import com.ssg.meowwms.dto.stock.StockDTO;
import com.ssg.meowwms.mapper.storage.ProductMapper;
import com.ssg.meowwms.mapper.storage.QrMapper;
import com.ssg.meowwms.mapper.storage.StockMovementMapper;
import com.ssg.meowwms.util.QRCodeGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor

public class StorageServiceImpl implements StorageService {
    private final ProductMapper productMapper;
    private final ModelMapper modelMapper;
    private final StockMovementMapper stockMovementMapper;
    private final JdbcTemplate jdbcTemplate; // jdbcTemplate 추가
    private final QRCodeGenerator qrCodeGenerator;
    private final QrMapper qrMapper;


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

    @Override
    public String generateQrCodeContent(StockMovementDTO stockMovementDTO, ProductDTO productDTO) {
        String content = "요청 정보 \n ------------------------"
                + "\n사용자 아이디: " + stockMovementDTO.getUserId()
                + "\n창고 아이디: " //+ stockMovementDTO.getWarehouseId()
                + "\n제품 아이디: " + stockMovementDTO.getProductId()
                + "\n요청 날짜: " + stockMovementDTO.getRequestDatetime()
                + "\n승인 날짜: " + stockMovementDTO.getApprovedDatetime()
                + "\n상품 정보 \n ------------------------"
                + "\n상품명: " + productDTO.getName()
                + "\n수량: " + productDTO.getQuantity()
                + "\n종류: " + productDTO.getCategoryId();
        return content;
    }

    @Override
    public void generateAndSaveQRCode(int productId, String qrCodeContent) {
        try {
            byte[] qrCodeImage = qrCodeGenerator.generateQRCodeImage(qrCodeContent);
            qrMapper.saveQrCodeImage(productId, qrCodeImage, LocalDate.now());
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getQrCode(byte[] imageData, String filePath)  {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(imageData);
            log.info("이미지가 성공적으로 저장되었습니다.");
        } catch (IOException e) {
            log.info("이미지를 저장하는 동안 오류가 발생했습니다: " + e.getMessage());
        }
    }
}
