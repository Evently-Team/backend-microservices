package com.evently.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_profiles")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "description")
    private String description;

    @Column(name = "pronouns")
    private String pronouns;

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "friends_count", nullable = false)
    private int friendsCount;
}
