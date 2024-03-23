package com.ssg.meowwms.service.warehouse;

import com.ssg.meowwms.domain.warehouse.WarehouseVO;
import com.ssg.meowwms.dto.warehouse.WarehouseDTO;
import com.ssg.meowwms.mapper.WarehouseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService{
    private final WarehouseMapper warehouseMapper;
    private final ModelMapper modelMapper;

    @Override
    public void register(WarehouseDTO warehouseDTO) {
        log.info("register...!!!");

        WarehouseVO warehouseVO = modelMapper.map(warehouseDTO, WarehouseVO.class);

        log.info("WarehouseVO: " + warehouseVO);

        warehouseMapper.insert(warehouseVO);
    }
}
