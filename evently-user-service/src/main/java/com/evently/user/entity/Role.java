package com.evently.user.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public enum Role {

    USER("user", Set.of(Permission.ACCESS)),
    ADMIN("admin", Set.of(Permission.ACCESS, Permission.ADMIN_DELETE, Permission.ADMIN_UPDATE));

    private final String name;
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        final List<SimpleGrantedAuthority> authorities = new ArrayList<>(getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.toString()))
                .toList());
        authorities.add(new SimpleGrantedAuthority("role:" + this.toString()));

        return authorities;
    }

    @Override
    public String toString() {
        return name;
    }

    @JsonValue
    public String getName() {
        return name().toLowerCase();
    }
}
