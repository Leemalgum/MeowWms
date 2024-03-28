package com.ssg.meowwms.security;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("ROLE_ADMIN", 1),
    WAREHOUSE_MANAGER("ROLE_WAREHOUSE_MANAGER", 2),
    USER("ROLE_USER", 3),
    NON_USER("ROLE_NON_USER", 4);

    private final String value;
    private final int rid;

    UserRole(String value, int rid) {
        this.value = value;
        this.rid = rid;
    }
}
