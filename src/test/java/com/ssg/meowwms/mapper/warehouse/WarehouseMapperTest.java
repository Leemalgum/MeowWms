package com.ssg.meowwms.mapper.warehouse;

import com.ssg.meowwms.domain.warehouse.WarehouseVO;
import com.ssg.meowwms.mapper.WarehouseMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class WarehouseMapperTest {

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Test
    @DisplayName("창고 등록")
    void insert() {
        WarehouseVO warehouseVO = WarehouseVO.builder()
                .name("야옹 창고")
                .categoryId(1)
                .managerId("WarehouseManager")
                .volume(20L)
                .latitude(37.517331925853)
                .longitude(127.047377408384)
                .build();

        warehouseMapper.insert(warehouseVO);
    }

}