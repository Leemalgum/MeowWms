package com.ssg.meowwms.domain.retrieval;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

/**
 * 운송장 데이터를 담고 있는 객체
 * (화물 운송장 아이디, 출고지시서 아이디, 차량 번호, 출발일, 도착일, 현황 (상태) )
 */
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WaybillVO {
    /**
     * 운송장 아이디 : 1 이상, Auto Increment
     */
    @NotNull
    @Min(value = 1, message = "운송장 ID 는 1이상 이여야 합니다")
    private int id;

    /**
     * 출고 지시서 아이디 : 1 이상, Auto Increment
     */
    @NotNull
    @Min(value = 1, message = "출고 지시서 ID 는 1이상 이여야 합니다")
    private int ShippingOrdersId;

    /**
     * 차량 번호 : 1 이상, Auto Increment
     */
    @NotNull
    @Max(value = 45, message = "차량 번호는 45자 이하여야 합니다")
    private String vehicleNum;

    /**
     * 출발일 : yyyy-MM-dd 형식, 한국(서울) 시간
     */
    @JsonFormat(pattern ="yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate departureDate;

    /**
     * 도착일 : yyyy-MM-dd 형식, 한국(서울) 시간
     */
    @JsonFormat(pattern ="yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate arrivalDate;

    /**
     * 운송장 상태 : 0 이상의 수로 표기
     */
    @NotNull
    @Positive(message = "운송장 상태는 0 이상이여야 합니다.")
    private int status;

}
