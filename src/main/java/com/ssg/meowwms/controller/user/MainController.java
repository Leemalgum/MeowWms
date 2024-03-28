package com.ssg.meowwms.controller.user;

import com.ssg.meowwms.dto.search.OptionDTO;
import com.ssg.meowwms.dto.warehouse.WarehouseDTO;
import com.ssg.meowwms.service.finance.ExpenseService;
import com.ssg.meowwms.service.finance.SalesService;
import com.ssg.meowwms.service.inquiry.InquiryService;
import com.ssg.meowwms.service.storage.StorageService;
import com.ssg.meowwms.service.user.UserService;
import com.ssg.meowwms.service.warehouse.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class MainController {
    private final ExpenseService expenseService;
    private final WarehouseService warehouseService;
    private final SalesService salesService;
    private final InquiryService inquiryService;
    private final UserService userService;
    private final StorageService storageService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model){
        List<String> years = expenseService.getAllYears();
        List<Integer> warehouseNumbers = warehouseService.getAllWarehouseId();
        List<WarehouseDTO> warehouseList = warehouseService.getAllWarehouse();
        int warehouseVolume = warehouseService.getSumOfVolume();
        int storageVolume = storageService.getSumOfVolume();

        System.out.println(warehouseVolume);
        System.out.println(storageVolume);

        // 년도가 null인 경우, 사용 가능한 가장 최근의 년도를 기본값으로 설정합니다.
        String year = years.stream()
                    .max(Comparator.naturalOrder())
                    .orElse(String.valueOf(LocalDate.now().getYear())); // 최대값을 찾지 못한 경우 현재 연도를 기본값으로 사용

        List<OptionDTO> currentOptionList = new ArrayList<>();
        currentOptionList.add(new OptionDTO("year", year));

        List<OptionDTO> lastYearOptionList = new ArrayList<>();
        int lastYear = Integer.parseInt(year) - 1;
        String lastYearString = String.valueOf(lastYear);
        lastYearOptionList.add(new OptionDTO("year", lastYearString));

        model.addAttribute("years", years);
        model.addAttribute("warehouseNumbers", warehouseNumbers);
        model.addAttribute("warehouseList", warehouseList);

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
        int noResponseInquiry = inquiryService.selectDoNotResponseInquiry();

//        double warehouseUseRate = (double) (storageVolume * 100) / warehouseVolume;
        double warehouseUseRate = ((double) Math.round(((double) storageVolume / warehouseVolume) * 1000) / 10);
        System.out.println(warehouseUseRate);

        model.addAttribute("sumSales", sumSales); // 당해년도 매출
        model.addAttribute("sumExpense", sumExpense); // 당해년도 지출
        model.addAttribute("sumSettlement", currentSettlement); // 당해년도 순이익
        model.addAttribute("profitChangeRate", Math.round(profitChangeRate)); // 당기순이익 변동률 (전년도와 비교)
        model.addAttribute("noResponseInquiry", inquiryService.selectDoNotResponseInquiry());
        model.addAttribute("sumUser", userService.totalUserCount());
        model.addAttribute("sumInActiveUser", userService.nonUserRequest());
        model.addAttribute("warehouseUseRate", warehouseUseRate);

        return "views/user/dashboard";
    }

    @GetMapping("/dash-user")
    public String showUserDash(Model model){
        return "views/user/dash-user";
    }
}
