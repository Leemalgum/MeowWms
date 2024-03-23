package com.ssg.meowwms.service;

import com.ssg.meowwms.dto.warehouse.WarehouseDTO;
import com.ssg.meowwms.service.warehouse.WarehouseService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class WarehouseServiceTest {

    @Autowired
    private WarehouseService warehouseService;

    @Test
    @DisplayName("창고 서비스 테스트")
    void register() {
        WarehouseDTO warehouseDTO = WarehouseDTO.builder()
                .name("야옹창고")
                .managerId("WarehouseManager")
                .volume(10L)
                .latitude(37.517331925853)
                .longitude(127.047377408384)
                .build();

        warehouseService.register(warehouseDTO);
    }
}
