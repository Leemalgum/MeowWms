package com.ssg.meowwms.dto.retrieval;

import lombok.*;

import java.time.LocalDateTime;

@ToString
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetrievalTimelineDTO {
    /**
     * 출고지시서 아이디 
     */
    private int id;
    private int shippingOrdersId;
    private LocalDateTime requestTime;
    private LocalDateTime orderTime;
    private LocalDateTime workingTime;
    private LocalDateTime finishedTime;
}
