package com.ssg.meowwms.domain.storage;

import lombok.*;

import javax.validation.constraints.Min;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductVO {
    private int id;
    private int categoryId;
    private String name;
    private String brand;
    private int price;
    private int salePrice;
    @Min(value = 0)
    private int quantity;
    private int volume;
}
