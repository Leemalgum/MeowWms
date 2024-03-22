package com.ssg.meowwms.domain;

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
