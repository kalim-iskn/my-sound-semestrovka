package ru.kpfu.itis.iskander.mysound.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 30, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Column(nullable = false)
    private String pseudonym;

    private String avatar;

    private String cover;

    @Column(length = 500)
    private String bio;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isVerified = false;

    @Column(unique = true)
    private Long vkUserId;

    @Transient
    private String avatarUrl;

    @Transient
    private String coverUrl;

}
