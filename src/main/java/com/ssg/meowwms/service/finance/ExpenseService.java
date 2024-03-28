package com.ssg.meowwms.service.finance;

import com.ssg.meowwms.dto.search.OptionDTO;
import com.ssg.meowwms.dto.finance.ExpenseDTO;
import com.ssg.meowwms.dto.finance.ExpenseMonthDTO;

import java.util.List;

public interface ExpenseService {
    void insertExpense(ExpenseDTO expenseDTO);
    ExpenseDTO selectExpenseById(int id);
    List<ExpenseDTO> selectAll(List<OptionDTO> optionList);
    void deleteExpense(int id);
    void updateExpense(ExpenseDTO expenseDTO);
    int sumExpenses(List<OptionDTO> optionList);
    List<ExpenseMonthDTO> sumExpensesByYear(int warehouseId, String year);
    List<String> getAllYears();
}
