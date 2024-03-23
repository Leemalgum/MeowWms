package com.ssg.meowwms.mapper.finance;

import com.ssg.meowwms.domain.finance.SalesVO;
import com.ssg.meowwms.dto.OptionDTO;
import com.ssg.meowwms.dto.finance.SalesMonthDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SalesMapper {
    void insertSales(SalesVO salesVO);

    SalesVO selectSalesById(int id);

    List<SalesVO> selectAll(@Param("optionList") List<OptionDTO> optionList);

    void deleteSales(int id);

    void updateSales(SalesVO salesVO);

    int sumSales(@Param("optionList") List<OptionDTO> optionList);

    List<SalesMonthDTO> sumSalesByYear(@Param("warehouseId") int warehouseId, @Param("year") String year);
}
