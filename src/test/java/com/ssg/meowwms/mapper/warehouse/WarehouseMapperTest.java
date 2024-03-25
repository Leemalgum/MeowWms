package com.ssg.meowwms.mapper.warehouse;

import com.ssg.meowwms.domain.warehouse.WarehouseVO;
import com.ssg.meowwms.dto.search.OptionDTO;
import com.ssg.meowwms.dto.warehouse.WarehouseDTO;
import com.ssg.meowwms.dto.warehouse.WarehouseDetailDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@Log4j2
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

    @Test
    @DisplayName("창고 조회")
    void selectAll() {
        List<OptionDTO> optionList = new ArrayList<>();

        // 창고 이름 검색
        optionList.add(new OptionDTO("categoryId", 1));
        
        // 창고 위치 검색
        optionList.add(new OptionDTO("latitude", 37.522057531502));
        optionList.add(new OptionDTO("longitude", 126.89528677963));

        List<WarehouseVO> warehouseVOList = warehouseMapper.selectAll(optionList);

        log.info(warehouseVOList);
    }

    @Test
    @DisplayName("창고 상세")
    void selectWarehouseDetail() {
        int warehouseId = 1;

        List<WarehouseDetailDTO> warehouseDetailList = warehouseMapper.selectWarehouseDetail(warehouseId);

        log.info(warehouseDetailList);
    }

    @Test
    @DisplayName("주어진 카테고리 아이디에 해당하는 창고 목록 반환")
    void selectWarehouseWithCategory() {
        int categoryId = 5;

        List<WarehouseDTO> warehouseList = warehouseMapper.selectWarehouseWithCategory(categoryId);

        log.info(warehouseList);
    }
}