package com.ssg.meowwms.service.storage;

import com.ssg.meowwms.dto.storage.ProductDTO;
import com.ssg.meowwms.dto.storage.StockMovementDTO;
import com.ssg.meowwms.dto.stock.StockDTO;

import java.sql.Blob;
import java.util.List;

public interface StorageService {
    String generateQrCodeContent(StockDTO stockDTO, StockMovementDTO stockMovementDTO, ProductDTO productDTO);
    Blob convertBase64ToBlob(String base64Image);
    int registerProduct(ProductDTO productDTO);
    void registerStorage(StockMovementDTO stockMovementDTO);
    void approveStorageRequest(int requestId);
    void cancelStorageRequest(int requestId);
    void modifyStorageRequest(StockMovementDTO stockMovementDTO);
    List<StockMovementDTO> getStorageList();
    void insertQrContent(String base64Image, StockDTO stockDTO, StockMovementDTO stockMovementDTO, ProductDTO productDTO);
    void getQrCode();
}
