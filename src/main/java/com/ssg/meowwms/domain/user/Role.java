package com.ssg.meowwms.domain.user;

/**
 * 사용자의 역할을 정의하는 클래스 로 총관리자, 창고 관리자, 일반 회원, 비회원 을 가지고 있습니다
 */
public enum Role {
    ADMIN,
    WAREHOUSEMANAGER,
    USER,
    NONUSER;

    public boolean isAdmin() {
        return this == ADMIN;
    }

    public boolean isWarehouseManager() {
        return this == WAREHOUSEMANAGER;
    }

    public boolean isUser() {
        return this == USER;
    }

    public boolean isNonUser() {
        return this == NONUSER;
    }
}
