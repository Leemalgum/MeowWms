package com.ssg.meowwms.domain.stock;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 재고 실사 상태값을 나타내주는 ENUM 값
 */
public enum Status {
    @JsonProperty("Completed")
    완료,
    @JsonProperty("NotCompleted")
    미완료,
    @JsonProperty("Error:CheckDetail")
    오류
}
