package ru.kpfu.itis.iskander.mysound.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.iskander.mysound.models.Track;

import java.util.List;
import java.util.Optional;

public interface TracksRepository extends JpaRepository<Track, Long> {

    Optional<Track> findByIdAndUserUsername(Long id, String username);

    boolean existsByIdAndUserUsername(Long id, String username);

    List<Track> getAllByUserUsername(String username);

    List<Track> findAllByOrderByNumberOfListensDesc();

    @Modifying
    @Transactional(propagation = Propagation.REQUIRED)
    @Query("UPDATE Track track set track.numberOfListens = track.numberOfListens + 1 WHERE track.id = :id")
    void incrementListenersNumber(@Param("id") Long id);

}
