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
    @DisplayName("창고 1개 조회")
    void selectOne() {
        String name = "운양동 우유 창고";

        log.info(warehouseMapper.selectOne(name));
    }

    @Test
    @DisplayName("창고 등록")
    void insert() {
        WarehouseVO warehouseVO = WarehouseVO.builder()
                .name("야옹 창고")
                .category("냉장 식품 과일")
                .managerId("WarehouseManager")
                .volume(20)
                .address("서울 동작구 노량진동 49-12")
                .build();

        warehouseMapper.insert(warehouseVO);
    }

//    @Test
//    @DisplayName("창고 조회")
//    void selectAll() {
//        List<OptionDTO> optionList = new ArrayList<>();
//
//        // 창고 이름 검색
//        optionList.add(new OptionDTO("categoryId", 1));
//
//        // 창고 위치 검색
//        optionList.add(new OptionDTO("latitude", 37.522057531502));
//        optionList.add(new OptionDTO("longitude", 126.89528677963));
//
//        List<WarehouseVO> warehouseVOList = warehouseMapper.selectAll(optionList);
//
//        log.info(warehouseVOList);
//    }

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
        String category = "냉장 | 식품 | 육류";

        List<WarehouseDTO> warehouseList = warehouseMapper.selectWarehouseWithCategory(category);

        log.info(warehouseList);
    }

    @Test
    @DisplayName("주어진 이름에 해당하는 창고 아이디 반환")
    void selectIdByName() {
        String name = "운양동 우유 창고";

        int warehouseId = warehouseMapper.selectIdByName(name).orElse(0);

        log.info(warehouseId);
    }

    @Test
    @DisplayName("모든 창고아이디 반환")
    public void selectAllWarehouseId(){
        log.info(warehouseMapper.selectAllWarehouseId());
    }
}