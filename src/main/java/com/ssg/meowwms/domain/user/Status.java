package com.ssg.meowwms.domain.user;

/**
 * 사용자의 상태를 정의하는 클래스 로 활동, 탈퇴, 강제탈퇴, 가입요청 상태를 가지고 있습니다
 */
public enum Status {
    ACTIVE,
    INACTIVE,
    BANNED,
    REQUEST;

    public boolean isActive() {
        return this == ACTIVE;
    }

    public boolean isInactive() {
        return this == INACTIVE;
    }

    public boolean isBanned() {
        return this == BANNED;
    }

    public boolean isRequest() {
        return this == REQUEST;
    }
}
