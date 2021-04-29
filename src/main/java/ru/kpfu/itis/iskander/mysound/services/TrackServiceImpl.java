package ru.kpfu.itis.iskander.mysound.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.iskander.mysound.dto.TrackForm;
import ru.kpfu.itis.iskander.mysound.exceptions.AudioInvalidException;
import ru.kpfu.itis.iskander.mysound.exceptions.PosterInvalidException;
import ru.kpfu.itis.iskander.mysound.exceptions.UndefinedServerProblemException;
import ru.kpfu.itis.iskander.mysound.models.Track;
import ru.kpfu.itis.iskander.mysound.models.User;
import ru.kpfu.itis.iskander.mysound.repositories.TracksRepository;
import ru.kpfu.itis.iskander.mysound.repositories.UsersRepository;
import ru.kpfu.itis.iskander.mysound.services.interfaces.TrackFilesService;
import ru.kpfu.itis.iskander.mysound.services.interfaces.TrackService;

@Component
public class TrackServiceImpl implements TrackService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TracksRepository tracksRepository;

    @Autowired
    private TrackFilesService trackFilesService;

    @Override
    public void create(TrackForm form, String username)
            throws UndefinedServerProblemException, AudioInvalidException, PosterInvalidException {
        saveTrack(username, form, null);
    }

    @Override
    public void update() {

    }

    @Override
    public void delete(Long id) {

    }

    private void saveTrack(String username, TrackForm form, Long trackId)
            throws UndefinedServerProblemException, AudioInvalidException, PosterInvalidException {
        boolean isAdding = trackId == null;

        User author = usersRepository.findByUsername(username).orElseThrow(UndefinedServerProblemException::new);

        Track track = isAdding ? new Track() : tracksRepository.getOne(trackId);

        if (isAdding) {
            track.setAudio(trackFilesService.uploadAudio(form.getAudio()));
        }

        if (isAdding || (form.getPoster() != null && !form.getPoster().isEmpty())) {
            track.setPoster(trackFilesService.uploadPoster(form.getPoster()));
        }

        track.setDescription(form.getDescription());
        track.setName(form.getName());

        if (isAdding)
            track.setUser(author);

        tracksRepository.save(track);
    }


}
