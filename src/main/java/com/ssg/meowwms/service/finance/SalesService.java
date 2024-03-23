package com.ssg.meowwms.service.finance;

import com.ssg.meowwms.dto.search.OptionDTO;
import com.ssg.meowwms.dto.finance.SalesDTO;
import com.ssg.meowwms.dto.finance.SalesMonthDTO;

import java.util.List;

public interface SalesService {
    void insertSales(SalesDTO salesDTO);

    SalesDTO selectSalesById(int id);

    List<SalesDTO> selectAll(List<OptionDTO> optionList);

    void deleteSales(int id);

    void updateSales(SalesDTO salesDTO);

    int sumSales(List<OptionDTO> optionList);

    List<SalesMonthDTO> sumSalesByYear(int warehouseId, String year);

}
