package com.ssg.meowwms.domain.retrieval;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.springframework.lang.Nullable;

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
    private int shippingOrdersId;

    /**
     * 차량 번호 : 1 이상, Auto Increment
     */
    @Nullable
    @Max(value = 45, message = "차량 번호는 45자 이하여야 합니다")
    private String vehicleNum;

    /**
     * 출발일 : yyyy-MM-dd 형식, 한국(서울) 시간
     */
    @Nullable
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate departureDate;

    /**
     * 도착일 : yyyy-MM-dd 형식, 한국(서울) 시간
     */
    @Nullable
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate arrivalDate;

    /**
     * 운송장 상태 : 0 이상의 수로 표기
     */
    @Nullable
    @Positive(message = "운송장 상태는 0 이상이여야 합니다.")
    private int status;

    /**
     * 회원 아이디 (출고 상품을 공급 받는 자)
     */
    @NotNull
    private String uid;

    /**
     * 창고 아이디 : for 납품 장소
     */
    @Nullable
    private int warehouseId;

    /**
     * 상품 아이디
     */
    @Nullable
    private int productId;

    /**
     * 상세 출고 지시서 아이디
     */
    @Nullable
    private int shippingOrdersDetailId;
}

