package ru.kpfu.itis.iskander.mysound.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.iskander.mysound.models.Track;

import java.util.List;
import java.util.Optional;

public interface TracksRepository extends JpaRepository<Track, Long> {

    Optional<Track> findByIdAndUserUsername(Long id, String username);

    boolean existsByIdAndUserUsername(Long id, String username);

    List<Track> getAllByUserUsername(String username);

}
