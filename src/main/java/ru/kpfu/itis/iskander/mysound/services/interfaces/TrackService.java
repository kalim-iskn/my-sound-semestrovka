package ru.kpfu.itis.iskander.mysound.services.interfaces;

import ru.kpfu.itis.iskander.mysound.dto.LikeDto;
import ru.kpfu.itis.iskander.mysound.dto.TrackForm;
import ru.kpfu.itis.iskander.mysound.exceptions.*;
import ru.kpfu.itis.iskander.mysound.models.Track;

import java.util.List;
import java.util.Map;

public interface TrackService {

    void create(TrackForm form, String username)
            throws UndefinedServerProblemException, AudioInvalidException, PosterInvalidException;

    void update(TrackForm form, String username, Long trackId)
            throws PosterInvalidException, AudioInvalidException, UndefinedServerProblemException;

    boolean delete(Long id, String username);

    Track getMyTrack(Long id, String username) throws TrackNotFoundException;

    boolean isUserAuthor(Long trackId, String username);

    List<Track> getList(String username);

    Track getTrack(Long id) throws TrackNotFoundException;

    List<Track> getAll();

    List<Track> getAllWithQuery(String query);

    List<Track> getPopular();

    void likeTrack(LikeDto likeDto) throws TrackNotFoundException, UserNotFoundException;

    Map<String, Long> getStatistic(Long userId);

}
