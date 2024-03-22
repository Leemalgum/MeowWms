package com.ssg.meowwms.domain.stock;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.validation.constraints.Min;
import lombok.*;



@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StockVO {

    @NotNull
    @Min(value = 1)
    private int stockId;
    @NotNull
    private int warehouseId;
    @NotNull
    private int productId;
    @NotNull
    private int quantity;

}
