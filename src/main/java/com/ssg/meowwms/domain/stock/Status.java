package com.ssg.meowwms.domain.stock;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 재고 실사 상태값을 나타내주는 ENUM 값
 */
public enum Status {
    @JsonProperty("완료")
    완료,
    @JsonProperty("미완료")
    미완료,
    @JsonProperty("오류")
    오류
}
