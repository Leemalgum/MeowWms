package com.ssg.meowwms.service.warehouse;

import com.ssg.meowwms.dto.search.OptionDTO;
import com.ssg.meowwms.dto.warehouse.WarehouseDTO;
import com.ssg.meowwms.dto.warehouse.WarehouseDetailDTO;
import com.ssg.meowwms.service.warehouse.WarehouseService;
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
public class WarehouseServiceTest {

    @Autowired
    private WarehouseService warehouseService;

    @Test
    @DisplayName("창고 등록 서비스 테스트")
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

    @Test
    @DisplayName("창고 조회 서비스 테스트")
    void selectAll() {
        List<OptionDTO> optionList = new ArrayList<>();

        // 창고 이름 검색
        optionList.add(new OptionDTO("categoryId", 1));

        // 창고 위치 검색
        optionList.add(new OptionDTO("latitude", 37.522057531502));
        optionList.add(new OptionDTO("longitude", 126.89528677963));

        List<WarehouseDTO> warehouseDTOList = warehouseService.selectAll(optionList);

        log.info(warehouseDTOList);
    }

    @Test
    @DisplayName("창고 상세 조회 테스트")
    void getWarehouseDetail() {
        int warehouseId = 1;

        List<WarehouseDetailDTO> warehouseDetailList = warehouseService.getWarehouseDetail(warehouseId);

        log.info(warehouseDetailList);
    }

    @Test
    @DisplayName("주어진 카테고리 아이디에 해당하는 창고 목록 조회 테스트")
    void getWarehouseWithCategory() {
        int categoryId = 5;

        List<WarehouseDTO> warehouseList = warehouseService.getWarehouseWithCategory(categoryId);

        log.info(warehouseList);
    }

    @Test
    @DisplayName("주어진 이름에 해당하는 창고 아이디 조회 테스트")
    void getWarehouseIdByName() {
        String name = "운양동 우유 창고";

        int warehouseId = warehouseService.getWarehouseIdByName(name);

        log.info(warehouseId);
    }
}
