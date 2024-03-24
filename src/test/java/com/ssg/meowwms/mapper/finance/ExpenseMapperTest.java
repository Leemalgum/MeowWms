package com.ssg.meowwms.mapper.finance;

import com.ssg.meowwms.domain.finance.ExpenseVO;
import com.ssg.meowwms.dto.search.OptionDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
class ExpenseMapperTest {

    @Autowired
    private ExpenseMapper expenseMapper;

    @Test
    public void testInsertExpense() {
        Date date = new Date();
        ExpenseVO expense = ExpenseVO.builder()
                .cost(10000)
                .type("창고 사용료")
                .warehouseId(1)
                .expenseDate(date)
                .build();

        expenseMapper.insertExpense(expense);
    }

    @Test
    void selectExpenseById() {
        // 예시 ID, 실제 ID에 맞게 변경해야 합니다.
        int id = 6;
        ExpenseVO expense = expenseMapper.selectExpenseById(id);
        assertThat(expense).isNotNull();
        assertThat(expense.getId()).isEqualTo(id);
    }
    @Test
    void deleteExpense() {
        int id = 6;
        expenseMapper.deleteExpense(6);
        assertThat(expenseMapper.selectExpenseById(6)).isNull();
    }

    @Test
    void updateExpense() {
        // 테스트를 위한 예시 데이터.
        Date date = new Date();
        ExpenseVO expense = ExpenseVO.builder()
                .id(6)
                .cost(10000)
                .type("Update")
                .warehouseId(1)
                .expenseDate(date)
                .build();
        expenseMapper.updateExpense(expense);
        ExpenseVO updatedExpense = expenseMapper.selectExpenseById(expense.getId());
        assertThat(updatedExpense.getType()).isEqualTo("Update");
        // 필요한 필드 검증
    }

    @Test
    void selectAll(){
        List<OptionDTO> optionList = new ArrayList<>();

        // 지출 날짜 범위 검색 조건 추가
        optionList.add(new OptionDTO("startDate", "2024-01-01"));
        optionList.add(new OptionDTO("endDate", "2024-12-31"));

        optionList.add(new OptionDTO("type", "유지보수"));

        // 지출 금액 범위 검색 조건 추가
        optionList.add(new OptionDTO("minCost", 200000));
        optionList.add(new OptionDTO("maxCost", 500000));

        List<ExpenseVO> expenses = expenseMapper.selectAll(optionList);
        System.out.println(expenses);
    }

    @Test
    void sumExpenses(){
        List<OptionDTO> optionList = new ArrayList<>();

        // 지출 날짜 범위 검색 조건 추가
        optionList.add(new OptionDTO("startDate", "2024-01-01"));
        optionList.add(new OptionDTO("endDate", "2024-12-31"));

        // 지출 금액 범위 검색 조건 추가
        optionList.add(new OptionDTO("minCost", 200000));
        optionList.add(new OptionDTO("maxCost", 500000));
        int sum = expenseMapper.sumExpenses(optionList);
        List<ExpenseVO> expenses = expenseMapper.selectAll(optionList);
        int sum1 = expenses.stream()
                .mapToInt(ExpenseVO::getCost)
                .sum();
        assertThat(sum).isEqualTo(sum1);
    }
}