package com.ssg.meowwms.service.warehouse;

import com.ssg.meowwms.dto.search.OptionDTO;
import com.ssg.meowwms.dto.warehouse.WarehouseDTO;

import java.util.List;

public interface WarehouseService {
    void register(WarehouseDTO warehouseDTO);
    List<WarehouseDTO> selectAll(List<OptionDTO> optionList);
}
