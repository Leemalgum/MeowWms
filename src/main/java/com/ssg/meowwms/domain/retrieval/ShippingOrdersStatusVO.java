package com.ssg.meowwms.domain.retrieval;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.*;

/**
 * 출고지시서 상태 데이터를 담고 있는 객체
 * (출고지시서 아이디, 출고 승인 상태, 배차 지정 상태 )
 */
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ShippingOrdersStatusVO {
    /**
     * 출고 지시서 아이디 : 1 이상, Auto Increment
     */
    @NotNull
    @Min(value = 1, message = "출고 지시서 ID 는 1이상 이여야 합니다")
    private int id;

    /**
     * 출고 지시서 아이디 : 1 이상, Auto Increment
     */
    @NotNull
    @Min(value = 1, message = "출고 지시서 ID 는 1이상 이여야 합니다")
    private int shippingOrdersId;

    /**
     * 배차 상태 : 0 배차되지 않음, 1 배차됨, 2 배차취소
     */
    @NotNull
    @Positive(message = "배차 상태는 0 이상이여야 합니다.")
    private int allocatedStatus;

    /**
     * 출고 승인 상태 : 0 미승인, 1 승인, 2 취소
     */
    @NotNull
    @Positive(message = "승인 상태는 0 이상이여야 합니다.")
    private int approvedStatus;

    /**
     * 운송장 등록 상태 : 0 미등록, 1 등록, 2 취소
     */
    @NotNull
    @Positive(message = "운송장 상태는 0 이상이여야 합니다.")
    private int waybillStatus;
}
