package ru.kpfu.itis.iskander.mysound.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.iskander.mysound.models.Listener;
import ru.kpfu.itis.iskander.mysound.models.Track;

public interface ListenersRepository extends JpaRepository<Listener, Long> {

    boolean existsByTrackAndIp(Track track, String ip);

}
