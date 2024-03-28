package com.ssg.meowwms.service.storage;

import com.google.zxing.WriterException;
import com.ssg.meowwms.domain.storage.ProductVO;
import com.ssg.meowwms.domain.storage.StockMovementVO;
import com.ssg.meowwms.dto.storage.MergeDTO;
import com.ssg.meowwms.dto.storage.ProductDTO;
import com.ssg.meowwms.dto.storage.StockMovementDTO;
import com.ssg.meowwms.mapper.storage.ProductMapper;
import com.ssg.meowwms.mapper.storage.QrMapper;
import com.ssg.meowwms.mapper.storage.StockMovementMapper;
import com.ssg.meowwms.util.QRCodeGenerator;
import com.ssg.meowwms.util.StockMovementStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    @Transactional
    public void approveStorageRequest(int requestId) {
        // 현재 요청의 상태 코드를 조회
        List<StockMovementVO> movements = stockMovementMapper.selectStockMovementsById(requestId);
        String currentStatusCode = movements.get(0).getStatusCode();

        //상태 코드가 "IR"인 경우에만 요청을 승인
        if ("IR".equals(currentStatusCode)) {
            log.info("Storage request approved for requestId: " + requestId);
            stockMovementMapper.updateStockMovementStatus(requestId, StockMovementStatus.APPROVED.getCode()); // "IC"는 승인된 상태를 나타냄
            stockMovementMapper.updateApprovedDatetime(requestId);

        } else {
            // 상태 코드가 "IR"이 아닌 경우, 로그를 남기거나 예외 처리
            log.info("Storage request cannot be approved as it is not in 'IR' status for requestId: " + requestId);
            // 예외 처리를 원할 경우 여기에 예외를 발생시킴
            // throw new IllegalStateException("Request cannot be approved as it is not in 'IR' status.");
        }
    }

    @Override
    @Transactional
    public void cancelStorageRequest(int requestId) {
        // 현재 요청의 상태 코드를 조회
        List<StockMovementVO> movements = stockMovementMapper.selectStockMovementsById(requestId);
        String currentStatusCode = movements.get(0).getStatusCode();

        //상태 코드가 "IR"인 경우에만 요청을 승인
        if ("IR".equals(currentStatusCode)) {
            log.info("Storage request approved for requestId: " + requestId);
            stockMovementMapper.updateStockMovementStatus(requestId, StockMovementStatus.CANCELLED.getCode());
        } else {
            // 상태 코드가 "IR"이 아닌 경우, 로그를 남기거나 예외 처리
            log.info("Storage request cannot be approved as it is not in 'IR' status for requestId: " + requestId);
            // 예외 처리를 원할 경우 여기에 예외를 발생시킴
            // throw new IllegalStateException("Request cannot be approved as it is not in 'IR' status.");
        }
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
    public List<ProductDTO> getProductList() {
        List<ProductVO> productVOList = productMapper.selectAllProducts();

        return productVOList.stream()
                .map(productVO -> modelMapper.map(productVO,ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public int getSumOfVolume() {
        return productMapper.selectSumOfVolume();
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


    public List<MergeDTO> mergeLists() {
        List<MergeDTO> mergedList = new ArrayList<>();

        List<ProductVO> products = productMapper.selectAllProducts();
        List<StockMovementVO> stockMovements = stockMovementMapper.selectAllStockMovements();

        // Product와 StockMovement를 조합하여 하나의 DTO로 만들어 mergedList에 추가하는 로직을 구현하세요.
        for (ProductVO product : products) {
            for (StockMovementVO stockMovement : stockMovements) {
                if (product.getId() == stockMovement.getProductId()) {
                    MergeDTO dto = new MergeDTO();
                    dto.setProductId(product.getId());
                    dto.setMovementUserId(product.getUserId());
                    dto.setProductName(product.getName());
                    dto.setProductCategory(product.getCategoryId());
                    dto.setProductPrice(product.getPrice());
                    dto.setProductSalePrice(product.getSalePrice());
                    dto.setProductQuantity(product.getQuantity());
                    dto.setMovementStatusCode(stockMovement.getStatusCode());
                    dto.setMovementRequestDate(stockMovement.getRequestDatetime());
                    dto.setMovementApprovedDate(stockMovement.getApprovedDatetime());
                    dto.setMovementWarehouseId(stockMovement.getWarehouseId());
                    mergedList.add(dto);
                    break;
                }
            }
        }

        return mergedList;
    }

    public List<MergeDTO> searchMergedLists(Map<String, Object> searchParams) {
        return stockMovementMapper.searchMergedLists(searchParams);
    }


    @Override
    public List<ProductDTO> searchProducts(String searchKeyword) {
        List<ProductVO> productVOs = productMapper.searchProducts(searchKeyword);
        return productVOs.stream().map(vo -> modelMapper.map(vo, ProductDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<StockMovementDTO> searchStockMovements(String searchKeyword) {
        List<StockMovementVO> stockMovementVOs = stockMovementMapper.searchStockMovements(searchKeyword);
        return stockMovementVOs.stream().map(vo -> modelMapper.map(vo, StockMovementDTO.class)).collect(Collectors.toList());
    }


    @Override
    public String generateQrCodeContent(StockMovementDTO stockMovementDTO, ProductDTO productDTO) {
        String content = "요청 정보 \n ------------------------"
                + "\n사용자 아이디: " + stockMovementDTO.getUserId()
                + "\n창고 아이디: " + stockMovementDTO.getWarehouseId()
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
