package com.ssg.meowwms.dto.finance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseMonthDTO {
    private int month;
    private int expenseMonth;
}
