package com.ssg.meowwms.domain.retrieval;

import com.fasterxml.jackson.annotation.JsonFormat;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

/**
 * 출고지시서 데이터를 담고 있는 필드
 * (출고지시서 아이디, 출고 요청자 아이디, 우편번호, 도로명주소, 지번주소, 상세 주소, 출고 희망일 )
 */
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ShippingOrdersVO {
    /**
     * 출고 지시서 아이디 : 1 이상, Auto Increment
     */
    @NotNull
    @Min(value = 1, message = "출고 지시서 ID 는 1이상 이여야 합니다")
    private int id;

    /**
     * 사용자 아이디 : 1 이상, Auto Increment
     */
    @NotNull
    @Min(value = 1, message = "사용자 ID 는 1이상 이여야 합니다")
    private String uid;

    /**
     * 우편 번호 : 6자 제한
     */
    @NotNull
    @Length(max = 6, message = "우편번호는 6자 이하여야 합니다")
    private String postcode;

    /**
     * 도로명 주소 : 255자 제한
     */
    private String streetNameAddress;

    /**
     * 지번 주소 : 255자 제한
     */
    private String streetNumberAddress;

    /**
     * 상세 주소 : 255자 제한
     */
    @NotNull
    @Length(max = 255, message = "상세 주소는 255자 이하여야 합니다")
    private String detailAddress;
    
    /**
     * 출고 희망일 : yyyy-MM-dd 형식, 한국(서울) 시간
     */
    @NotNull
    @JsonFormat(pattern ="yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate expectedDate;
}
