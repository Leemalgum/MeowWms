package com.ssg.meowwms.dto.storage;

import lombok.*;

import javax.validation.constraints.Min;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDTO {
    private int id;
    private int categoryId;
    private String name;
    private String brand;
    private int price;
    private int salePrice;
    @Min(value = 0)
    private int quantity;
    private int volume;
    private String userId;
}
