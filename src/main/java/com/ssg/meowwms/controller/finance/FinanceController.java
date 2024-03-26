package com.ssg.meowwms.controller.finance;

import com.ssg.meowwms.dto.finance.*;
import com.ssg.meowwms.dto.search.OptionDTO;
import com.ssg.meowwms.dto.search.OptionList;
import com.ssg.meowwms.service.finance.ExpenseService;
import com.ssg.meowwms.service.finance.SalesService;
import com.ssg.meowwms.service.warehouse.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/finance")
@RequiredArgsConstructor
public class FinanceController {
    private final ExpenseService expenseService;

    private final SalesService salesService;

    private final WarehouseService warehouseService;

    // 이미 존재하는 메소드 내에 추가된 부분만 기술합니다.
    @GetMapping("/expense")
    public String showExpense(Model model, @RequestParam(required = false) String year, @RequestParam(required = false) String warehouseId) {
        List<String> years = expenseService.getAllYears();
        model.addAttribute("years", years);
        model.addAttribute("selectedYear", year);
        model.addAttribute("selectedWarehouseId", warehouseId);
        return "views/finance/expense";
    }

    @GetMapping("/sales")
    public String showSales(Model model, @RequestParam(required = false) String year, @RequestParam(required = false) String warehouseId) {
        List<String> years = salesService.getAllYears();
        model.addAttribute("years", years);
        model.addAttribute("selectedYear", year);
        model.addAttribute("selectedWarehouseId", warehouseId);
        // 아직 구현되지 않은 검색 필터 적용 로직
        return "views/finance/sales";
    }
    @PostMapping("/expenses/data")
    public ResponseEntity<?> getExpensesData(@RequestBody List<OptionDTO> searchFilters) {
        // searchFilters를 사용하여 데이터 필터링 로직 처리
        List<ExpenseDTO> expenses = expenseService.selectAll(searchFilters);
        return ResponseEntity.ok(expenses);
    }

    @PostMapping("/sales/data")
    public ResponseEntity<?> getSalesData(@RequestBody List<OptionDTO> searchFilters) {
        // searchFilters를 사용하여 데이터 필터링 로직 처리
        List<SalesDTO> sales = salesService.selectAll(searchFilters);
        return ResponseEntity.ok(sales);
    }


    @PostMapping("/expenses/graph")
    public ResponseEntity<?> getExpenseGraphData(
            @RequestParam(required = false) String year,
            @RequestParam(required = false) Integer warehouseId) {
        // 매개변수로 년도와 창고 번호를 전달
        List<ExpenseMonthDTO> list = expenseService.sumExpensesByYear(warehouseId, year);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/sales/graph")
    public ResponseEntity<?> getSalesGraphData(
            @RequestParam(required = false) String year,
            @RequestParam(required = false) Integer warehouseId) {
        // 매개변수로 년도와 창고 번호를 전달
        List<SalesMonthDTO> list = salesService.sumSalesByYear(warehouseId, year);
        System.out.println(list);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/allfinance")
    public String showAllFinance(Model model, @RequestParam(required = false) String year, @RequestParam(required = false) String warehouseId) {
        List<String> years = expenseService.getAllYears();
        List<Integer> warehouseNumbers = warehouseService.getAllWarehouseId();

        // 년도가 null인 경우, 사용 가능한 가장 최근의 년도를 기본값으로 설정합니다.
        if (year == null) {
            year = years.stream()
                    .max(Comparator.naturalOrder())
                    .orElse(String.valueOf(LocalDate.now().getYear())); // 최대값을 찾지 못한 경우 현재 연도를 기본값으로 사용
        }

        List<OptionDTO> currentOptionList = new ArrayList<>();
        currentOptionList.add(new OptionDTO("year", year));

        List<OptionDTO> lastYearOptionList = new ArrayList<>();
        int lastYear = Integer.parseInt(year) - 1;
        String lastYearString = String.valueOf(lastYear);
        lastYearOptionList.add(new OptionDTO("year", lastYearString));

        model.addAttribute("years", years);
        model.addAttribute("warehouseNumbers", warehouseNumbers);

        int sumSales = salesService.sumSales(currentOptionList);
        int sumExpense = expenseService.sumExpenses(currentOptionList);
        int lastSumSales = salesService.sumSales(lastYearOptionList);
        int lastSumExpense = expenseService.sumExpenses(lastYearOptionList);

        int currentSettlement = sumSales - sumExpense;
        int lastSettlement = lastSumSales - lastSumExpense;
        double profitChangeRate = 0.0;
        if (lastSettlement != 0) {
            profitChangeRate = ((double) (currentSettlement - lastSettlement) / lastSettlement) * 100;
        }

        model.addAttribute("sumSales", sumSales); // 당해년도 매출
        model.addAttribute("sumExpense", sumExpense); // 당해년도 지출
        model.addAttribute("sumSettlement", currentSettlement); // 당해년도 순이익
        model.addAttribute("profitChangeRate", Math.round(profitChangeRate)); // 당기순이익 변동률 (전년도와 비교)

        return "views/finance/allfinance";
    }


    @PostMapping("/allfinance/graph")
    public ResponseEntity<?> getSettlementGraphData(
            @RequestParam(required = false) String year,
            @RequestParam(required = false) Integer warehouseId) {
        List<SettlementMonthDTO> list = null;
        // 매개변수로 년도와 창고 번호를 전달
        if (warehouseId == 0) {
            list = salesService.sumAllSettlementByYear(year);
        } else {
            list = salesService.sumSettlementByYear(warehouseId, year);
        }
        return ResponseEntity.ok(list);
    }


}
