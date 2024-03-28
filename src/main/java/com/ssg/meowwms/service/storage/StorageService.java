package com.ssg.meowwms.service.storage;

import com.ssg.meowwms.dto.storage.MergeDTO;
import com.ssg.meowwms.dto.storage.ProductDTO;
import com.ssg.meowwms.dto.storage.StockMovementDTO;
import com.ssg.meowwms.dto.stock.StockDTO;

import java.sql.Blob;
import java.util.List;
import java.util.Map;

public interface StorageService {
     int registerProduct(ProductDTO productDTO);
    void registerStorage(StockMovementDTO stockMovementDTO);
    void approveStorageRequest(int requestId);
    void cancelStorageRequest(int requestId);
    int modifyProduct(int requestId, ProductDTO productDTO);
    void modifyStorageRequest(int requestId);

    List<ProductDTO> getProductList();
    List<StockMovementDTO> getStorageList();
    List<StockMovementDTO> selectMovementByStatus(String statusCode);
    List<StockMovementDTO> selectStockMovementsById(int productId);
    List<MergeDTO> mergeLists();
    List<ProductDTO> searchProducts(String searchKeyword);
    List<StockMovementDTO> searchStockMovements(String searchKeyword);

    List<MergeDTO> searchMergedLists(Map<String, Object> searchParams);


    String generateQrCodeContent(StockMovementDTO stockMovementDTO, ProductDTO productDTO);
    void generateAndSaveQRCode(int productId, String qrCodeContent);
    void getQrCode(byte[] imageData, String filePath);
}
