package com.axios.restapi.persistence.user;

import com.axios.restapi.persistence.user.enums.Gender;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String hobby;

    private LocalDateTime createdAt;

    public User(String name,
                Gender gender,
                String hobby) {
        this.name = name;
        this.gender = gender;
        this.hobby = hobby;
        this.createdAt = LocalDateTime.now();
    }
}