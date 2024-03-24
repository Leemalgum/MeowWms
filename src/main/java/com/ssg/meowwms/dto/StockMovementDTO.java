package com.ssg.meowwms.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StockMovementDTO {
    private int id;
    private int productId;
    private String userId;
    private String statusCode;
    private LocalDate requestDatetime;
    private Date approvedDatetime;
}
