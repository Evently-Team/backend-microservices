package com.evently.user.entity.persistent;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "cities", nullable = false)
    private String cities;

    @Column(name = "description")
    private String description;

    @Column(name = "pronouns")
    private String pronouns;

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "friends_count", nullable = false)
    private int friendsCount;
}
