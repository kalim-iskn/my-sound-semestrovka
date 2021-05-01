package ru.kpfu.itis.iskander.mysound.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.iskander.mysound.dto.ListenerDto;
import ru.kpfu.itis.iskander.mysound.exceptions.ListenerAlreadyExistException;
import ru.kpfu.itis.iskander.mysound.exceptions.TrackNotFound;
import ru.kpfu.itis.iskander.mysound.models.Listener;
import ru.kpfu.itis.iskander.mysound.models.Track;
import ru.kpfu.itis.iskander.mysound.repositories.ListenersRepository;
import ru.kpfu.itis.iskander.mysound.repositories.TracksRepository;
import ru.kpfu.itis.iskander.mysound.services.interfaces.ListenerService;

@Service
public class ListenerServiceImpl implements ListenerService {

    @Autowired
    private ListenersRepository listenersRepository;

    @Autowired
    private TracksRepository tracksRepository;

    @Override
    public void addToTrack(ListenerDto listenerDto) throws TrackNotFound, ListenerAlreadyExistException {
        Track track = tracksRepository.findById(listenerDto.getTrackId()).orElseThrow(TrackNotFound::new);

        if (listenersRepository.existsByTrackAndIp(track, listenerDto.getIp()))
            throw new ListenerAlreadyExistException();

        Listener listener = Listener.builder()
                .track(track)
                .ip(listenerDto.getIp())
                .build();

        listenersRepository.save(listener);
        tracksRepository.incrementListenersNumber(listenerDto.getTrackId());
    }

}
