package com.ssg.meowwms.service.finance;

import com.ssg.meowwms.domain.finance.SalesVO;
import com.ssg.meowwms.dto.finance.ExpenseMonthDTO;
import com.ssg.meowwms.dto.finance.SettlementMonthDTO;
import com.ssg.meowwms.dto.search.OptionDTO;
import com.ssg.meowwms.dto.finance.SalesDTO;
import com.ssg.meowwms.dto.finance.SalesMonthDTO;
import com.ssg.meowwms.mapper.finance.ExpenseMapper;
import com.ssg.meowwms.mapper.finance.SalesMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalesServiceImpl implements SalesService {

    private final SalesMapper salesMapper;
    private final ExpenseMapper expenseMapper;
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

    @Override
    public List<String> getAllYears() {
        return salesMapper.getAllYears();
    }

    public List<SettlementMonthDTO> sumSettlementByYear(int warehouseId, String year) {
        List<SettlementMonthDTO> settlementMonthDTOList = new ArrayList<>();
        List<SalesMonthDTO> salesMonthDTOList = salesMapper.sumSalesByYear(warehouseId, year);
        List<ExpenseMonthDTO> expenseMonthDTOList = expenseMapper.sumExpensesByYear(warehouseId, year);

        // 각 월의 순이익을 0으로 초기화
        Map<Integer, Integer> netProfitByMonth = new HashMap<>();
        for (int month = 1; month <= 12; month++) {
            netProfitByMonth.put(month, 0);
        }

        // 각 월의 판매액 계산
        for (SalesMonthDTO sales : salesMonthDTOList) {
            netProfitByMonth.put(sales.getMonth(), netProfitByMonth.getOrDefault(sales.getMonth(),0) + sales.getSalesMonth());
        }

        // 각 월의 지출 빼기
        for (ExpenseMonthDTO expense : expenseMonthDTOList) {
            netProfitByMonth.put(expense.getMonth(), netProfitByMonth.getOrDefault(expense.getMonth(),0) - expense.getExpenseMonth());
        }

        // settlementMonthDTOList에 순이익 데이터 채우기
        for (Map.Entry<Integer, Integer> entry : netProfitByMonth.entrySet()) {
            settlementMonthDTOList.add(new SettlementMonthDTO(entry.getKey(), entry.getValue()));
        }

        return settlementMonthDTOList;
    }

    public List<SettlementMonthDTO> sumAllSettlementByYear(String year) {
        List<SettlementMonthDTO> settlementMonthDTOList = new ArrayList<>();
        List<SalesMonthDTO> salesMonthDTOList = salesMapper.sumAllSalesByYear(year);
        List<ExpenseMonthDTO> expenseMonthDTOList = expenseMapper.sumAllExpensesByYear(year);

        // 각 월의 순이익을 0으로 초기화
        Map<Integer, Integer> netProfitByMonth = new HashMap<>();
        for (int month = 1; month <= 12; month++) {
            netProfitByMonth.put(month, 0);
        }

        // 각 월의 판매액 계산
        for (SalesMonthDTO sales : salesMonthDTOList) {
            netProfitByMonth.put(sales.getMonth(), netProfitByMonth.getOrDefault(sales.getMonth(),0) + sales.getSalesMonth());
        }

        // 각 월의 지출 빼기
        for (ExpenseMonthDTO expense : expenseMonthDTOList) {
            netProfitByMonth.put(expense.getMonth(), netProfitByMonth.getOrDefault(expense.getMonth(),0) - expense.getExpenseMonth());
        }

        // settlementMonthDTOList에 순이익 데이터 채우기
        for (Map.Entry<Integer, Integer> entry : netProfitByMonth.entrySet()) {
            settlementMonthDTOList.add(new SettlementMonthDTO(entry.getKey(), entry.getValue()));
        }

        return settlementMonthDTOList;
    }



}
