package com.evently.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "username",
            unique = true, nullable = false)
    private String username;

    @Column(name = "email",
            unique = true, nullable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "city")
    private String city;

    @Column(name = "profile_description")
    private String profileDescription;

    @Column(name = "profile_links")
    @ElementCollection
    private List<String> profileLinks;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role", nullable = false)
    @Builder.Default
    private Role role = Role.USER;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Column(name = "is_deleted", nullable = false)
    private final boolean isDeleted = false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    public Collection<? extends GrantedAuthority> getAuthoritiesForLimitedAccess() {
        return List.of(new SimpleGrantedAuthority("role:user"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !isDeleted;
    }
}