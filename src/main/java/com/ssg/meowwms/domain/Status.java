package com.ssg.meowwms.domain;

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
