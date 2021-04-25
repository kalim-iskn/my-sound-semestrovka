package ru.kpfu.itis.iskander.mysound.models;

import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kpfu.itis.iskander.mysound.config.ProjectProperties;

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

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String pseudonym;

    @Column(nullable = false)
    private String avatar;

    private String cover;

    @Column(length = 500)
    private String bio;

    @Column(nullable = false)
    private Boolean isVerified = false;

    @Transient
    private String avatarUrl;

    @Transient
    private String coverUrl;

}
