package com.ssg.meowwms.service.finance;

import com.ssg.meowwms.domain.finance.ExpenseVO;
import com.ssg.meowwms.dto.search.OptionDTO;
import com.ssg.meowwms.dto.finance.ExpenseDTO;
import com.ssg.meowwms.dto.finance.ExpenseMonthDTO;
import com.ssg.meowwms.mapper.finance.ExpenseMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseMapper expenseMapper;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public void insertExpense(ExpenseDTO expenseDTO) {
        ExpenseVO expenseVO = modelMapper.map(expenseDTO, ExpenseVO.class);
        expenseMapper.insertExpense(expenseVO);
    }

    @Override
    public ExpenseDTO selectExpenseById(int id) {
        ExpenseVO expense = expenseMapper.selectExpenseById(id);
        return modelMapper.map(expense, ExpenseDTO.class);
    }

    @Override
    public List<ExpenseDTO> selectAll(List<OptionDTO> optionList) {
        List<ExpenseVO> expenses = expenseMapper.selectAll(optionList);
        return expenses.stream()
                .map(expense -> modelMapper.map(expense, ExpenseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteExpense(int id) {
        expenseMapper.deleteExpense(id);
    }

    @Override
    @Transactional
    public void updateExpense(ExpenseDTO expenseDTO) {
        ExpenseVO expenseVO = modelMapper.map(expenseDTO, ExpenseVO.class);
        expenseMapper.updateExpense(expenseVO);
    }

    @Override
    public int sumExpenses(List<OptionDTO> optionList) {
        return expenseMapper.sumExpenses(optionList);
    }

    @Override
    public List<ExpenseMonthDTO> sumExpensesByYear(int warehouseId, String year) {
        return expenseMapper.sumExpensesByYear(warehouseId, year);
    }

}
