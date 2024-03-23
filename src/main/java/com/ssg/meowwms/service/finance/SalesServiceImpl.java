package com.ssg.meowwms.service.finance;

import com.ssg.meowwms.domain.finance.SalesVO;
import com.ssg.meowwms.dto.OptionDTO;
import com.ssg.meowwms.dto.finance.SalesDTO;
import com.ssg.meowwms.dto.finance.SalesMonthDTO;
import com.ssg.meowwms.mapper.finance.SalesMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalesServiceImpl implements SalesService {

    private final SalesMapper salesMapper;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public void insertSales(SalesDTO salesDTO) {
        SalesVO salesVO = modelMapper.map(salesDTO, SalesVO.class);
        salesMapper.insertSales(salesVO);
    }

    @Override
    public SalesDTO selectSalesById(int id) {
        SalesVO sales = salesMapper.selectSalesById(id);
        return modelMapper.map(sales, SalesDTO.class);
    }

    @Override
    public List<SalesDTO> selectAll(List<OptionDTO> optionList) {
        List<SalesVO> salesList = salesMapper.selectAll(optionList);
        return salesList.stream().map(sales -> modelMapper.map(sales, SalesDTO.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteSales(int id) {
        salesMapper.deleteSales(id);
    }

    @Override
    @Transactional
    public void updateSales(SalesDTO salesDTO) {
        SalesVO salesVO = modelMapper.map(salesDTO, SalesVO.class);
        salesMapper.updateSales(salesVO);
    }

    @Override
    public int sumSales(List<OptionDTO> optionList) {
        return salesMapper.sumSales(optionList);
    }

    @Override
    public List<SalesMonthDTO> sumSalesByYear(int warehouseId, String year) {
        return salesMapper.sumSalesByYear(warehouseId, year);
    }
}
