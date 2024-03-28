package com.ssg.meowwms.service.warehouse;

import com.ssg.meowwms.dto.search.OptionDTO;
import com.ssg.meowwms.dto.warehouse.WarehouseDTO;
import com.ssg.meowwms.dto.warehouse.WarehouseDetailDTO;

import java.util.List;
import java.util.Optional;

public interface WarehouseService {
    void register(WarehouseDTO warehouseDTO);
    List<WarehouseDTO> selectAll(List<OptionDTO> optionList);
    List<WarehouseDetailDTO> getWarehouseDetail(int warehouseId);
    List<WarehouseDTO> getWarehouseWithCategory(String category);
    Optional<Integer> getWarehouseIdByName(String warehouseName);
    List<Integer> getAllWarehouseId();
}
