package com.ssg.meowwms.service;

import com.ssg.meowwms.dto.storage.ProductDTO;
import com.ssg.meowwms.dto.storage.StockMovementDTO;
import com.ssg.meowwms.service.storage.StorageService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Log4j2
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class StorageServiceTests {

    @Autowired
    private StorageService storageService;


    @Test
    public void testRegisterStorage(){
        // 1. 제품 등록 후 ID 가져오기
        ProductDTO productDTO = ProductDTO.builder()
                .categoryId(4)
                .name("딸기")
                .brand("딸기회사")
                .price(3000)
                .salePrice(4000)
                .quantity(500)
                .volume(20)
                .userId("user6").build();
        int productId = storageService.registerProduct(productDTO);

        // 2. 입고 요청 등록
        StockMovementDTO stockMovementDTO = StockMovementDTO.builder()
                .productId(productId) // 앞서 등록한 제품의 ID를 설정
                .userId("user6")
                .statusCode("IR")
                .requestDatetime(LocalDate.from(LocalDateTime.now()))
                .build();
        storageService.registerStorage(stockMovementDTO);
    }
    @Test
    public void testApproveStorageRequest(){

    }
}
