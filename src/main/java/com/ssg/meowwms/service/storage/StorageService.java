package com.ssg.meowwms.service.storage;

import com.ssg.meowwms.dto.storage.ProductDTO;
import com.ssg.meowwms.dto.storage.StockMovementDTO;
import com.ssg.meowwms.dto.stock.StockDTO;

import java.sql.Blob;
import java.util.List;

public interface StorageService {
     int registerProduct(ProductDTO productDTO);
    void registerStorage(StockMovementDTO stockMovementDTO);
    void approveStorageRequest(int requestId);
    void cancelStorageRequest(int requestId);
    int modifyProduct(int requestId, ProductDTO productDTO);
    void modifyStorageRequest(int requestId);
    List<StockMovementDTO> getStorageList();
    List<StockMovementDTO> selectMovementByStatus(String statusCode);
    List<StockMovementDTO> selectStockMovementsById(int productId);
    String generateQrCodeContent(StockMovementDTO stockMovementDTO, ProductDTO productDTO);
    void generateAndSaveQRCode(int productId, String qrCodeContent);
    void getQrCode(byte[] imageData, String filePath);
}
