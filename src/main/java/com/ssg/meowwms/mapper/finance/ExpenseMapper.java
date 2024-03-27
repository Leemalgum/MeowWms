package com.ssg.meowwms.mapper.finance;

import com.ssg.meowwms.domain.finance.ExpenseVO;
import com.ssg.meowwms.dto.search.OptionDTO;
import com.ssg.meowwms.dto.finance.ExpenseMonthDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExpenseMapper {
    void insertExpense(ExpenseVO expenseVO);

    ExpenseVO selectExpenseById(int id);

    List<ExpenseVO> selectAll(@Param("optionList")List<OptionDTO> optionList);

    void deleteExpense(int id);

    void updateExpense(ExpenseVO expenseVO);

    int sumExpenses(@Param("optionList")List<OptionDTO> optionList);

    List<ExpenseMonthDTO> sumExpensesByYear(@Param("warehouseId") int warehouseId, @Param("year") String year);

    List<String> getAllYears();

    List<ExpenseMonthDTO> sumAllExpensesByYear(@Param("year") String year);
}
