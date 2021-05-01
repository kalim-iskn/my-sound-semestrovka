package ru.kpfu.itis.iskander.mysound.services.interfaces;

import ru.kpfu.itis.iskander.mysound.dto.ListenerDto;
import ru.kpfu.itis.iskander.mysound.exceptions.ListenerAlreadyExistException;
import ru.kpfu.itis.iskander.mysound.exceptions.TrackNotFound;

public interface ListenerService {

    void addToTrack(ListenerDto listenerDto) throws TrackNotFound, ListenerAlreadyExistException;

}
