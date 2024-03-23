package com.ssg.meowwms.domain.finance;

import lombok.*;

import java.util.Date;
//지출

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExpenseVO {
    private int id;
    private int warehouseId;
    private String type;
    private Date expenseDate;
    private int cost;
}
