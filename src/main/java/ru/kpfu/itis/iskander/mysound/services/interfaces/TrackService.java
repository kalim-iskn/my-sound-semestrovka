package ru.kpfu.itis.iskander.mysound.services.interfaces;

import ru.kpfu.itis.iskander.mysound.dto.TrackForm;
import ru.kpfu.itis.iskander.mysound.exceptions.AudioInvalidException;
import ru.kpfu.itis.iskander.mysound.exceptions.PosterInvalidException;
import ru.kpfu.itis.iskander.mysound.exceptions.UndefinedServerProblemException;

public interface TrackService {

    void create(TrackForm form, String username) throws UndefinedServerProblemException, AudioInvalidException,
            PosterInvalidException;

    void update();

    void delete(Long id);

}
