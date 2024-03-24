package com.ssg.meowwms.dto.finance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesMonthDTO {
    private int salesMonth;
    private int totalSales;

}
