package ru.kpfu.itis.iskander.mysound.services.interfaces;

import ru.kpfu.itis.iskander.mysound.dto.ListenerDto;
import ru.kpfu.itis.iskander.mysound.exceptions.ListenerAlreadyExistException;
import ru.kpfu.itis.iskander.mysound.exceptions.TrackNotFoundException;

public interface ListenerService {

    void addToTrack(ListenerDto listenerDto) throws TrackNotFoundException, ListenerAlreadyExistException;

}
