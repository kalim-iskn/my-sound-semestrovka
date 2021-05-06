package ru.kpfu.itis.iskander.mysound.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "track", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Listener> listeners;

    @ManyToMany
    @JoinTable(name = "likes",
            joinColumns = {@JoinColumn(name = "track_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    private List<User> likes;

    @OneToMany(mappedBy = "track", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @ColumnDefault("0")
    @Builder.Default
    @Column(nullable = false)
    private Long numberOfListens = 0L;

    @ColumnDefault("0")
    @Builder.Default
    @Column(nullable = false)
    private Long numberOfLikes = 0L;

    @ColumnDefault("0")
    @Builder.Default
    @Column(nullable = false)
    private Long numberOfComments = 0L;

    @Column(nullable = false)
    private String audio;

    @Column(nullable = false)
    private String poster;

    @Column(nullable = false, updatable = false)
    @Builder.Default
    private LocalDate createdAt = LocalDate.from(LocalDate.now());

    @Transient
    private String posterUrl;

    @Transient
    private String audioUrl;

}
