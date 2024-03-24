package com.ssg.meowwms.domain.finance;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.*;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SalesVO {
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
