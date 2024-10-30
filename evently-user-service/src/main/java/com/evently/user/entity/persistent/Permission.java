package com.evently.user.entity.persistent;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {

    ACCESS("access"),
    ADMIN_DELETE("admin:delete"),
    ADMIN_UPDATE("admin:update");

    private final String name;

    public String toString() {
        return name;
    }
}
