package ru.kpfu.itis.iskander.mysound.services.interfaces;

import ru.kpfu.itis.iskander.mysound.dto.TrackForm;
import ru.kpfu.itis.iskander.mysound.exceptions.AudioInvalidException;
import ru.kpfu.itis.iskander.mysound.exceptions.PosterInvalidException;
import ru.kpfu.itis.iskander.mysound.exceptions.TrackNotFound;
import ru.kpfu.itis.iskander.mysound.exceptions.UndefinedServerProblemException;
import ru.kpfu.itis.iskander.mysound.models.Track;

import java.util.List;

public interface TrackService {

    void create(TrackForm form, String username)
            throws UndefinedServerProblemException, AudioInvalidException, PosterInvalidException;

    void update(TrackForm form, String username, Long trackId)
            throws PosterInvalidException, AudioInvalidException, UndefinedServerProblemException;

    boolean delete(Long id, String username);

    Track getMyTrack(Long id, String username) throws TrackNotFound;

    boolean isUserAuthor(Long trackId, String username);

    List<Track> getList(String username);

    Track getTrack(Long id) throws TrackNotFound;

}
