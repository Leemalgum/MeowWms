package com.ssg.meowwms.service.warehouse;

import com.ssg.meowwms.mapper.WarehouseMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl {
    private final WarehouseMapper warehouseMapper;
    private final ModelMapper modelMapper;
}
