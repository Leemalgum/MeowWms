package com.ssg.meowwms.dto.finance;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SalesDTO {
    @NotNull
    private int id;
    @NotNull
    private int warehouseId;
    @NotNull
    private int amount;
    @NotNull
    private String type;
    @NotNull
    private Date salesDate;
}
