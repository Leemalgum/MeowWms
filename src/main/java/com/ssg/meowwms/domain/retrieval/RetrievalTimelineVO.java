package com.ssg.meowwms.domain.retrieval;

import com.fasterxml.jackson.annotation.JsonFormat;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

/**
 * 출고 타임라인 데이터를 담고 있는 객체
 * (출고지시서 아이디, 출고 요청 시간, 출고 지시 (= 출고 승인) 시간, 출고 작업 시간, 출고 완료 시간(= 출고 일) )
 */
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RetrievalTimelineVO {
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
     * 출고 요청 시간 : yyyy-MM-dd HH:mm:ss 형식, 한국(서울) 시간
     */
    @Nullable
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime requestTime;

    /**
     * 출고 지시 (= 출고 승인) 시간 : yyyy-MM-dd HH:mm:ss 형식, 한국(서울) 시간
     */
    @Nullable
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime orderTime;

    /**
     * 출고 작업 시간 : yyyy-MM-dd HH:mm:ss 형식, 한국(서울) 시간
     */
    @Nullable
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime workingTime;

    /**
     * 출고 완료 시간(= 출고 일) : yyyy-MM-dd HH:mm:ss 형식, 한국(서울) 시간
     */
    @Nullable
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime finishedTime;
}
