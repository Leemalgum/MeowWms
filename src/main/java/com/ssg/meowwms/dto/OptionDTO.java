package com.ssg.meowwms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionDTO {
    private String type;
    private Object value;

    // 생성자, getter, setter 생략
}