package ru.kpfu.itis.iskander.mysound.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.iskander.mysound.dto.TrackForm;
import ru.kpfu.itis.iskander.mysound.exceptions.AudioInvalidException;
import ru.kpfu.itis.iskander.mysound.exceptions.PosterInvalidException;
import ru.kpfu.itis.iskander.mysound.exceptions.TrackNotFound;
import ru.kpfu.itis.iskander.mysound.exceptions.UndefinedServerProblemException;
import ru.kpfu.itis.iskander.mysound.helpers.interfaces.TrackBuilder;
import ru.kpfu.itis.iskander.mysound.models.Track;
import ru.kpfu.itis.iskander.mysound.models.User;
import ru.kpfu.itis.iskander.mysound.repositories.TracksRepository;
import ru.kpfu.itis.iskander.mysound.repositories.UsersRepository;
import ru.kpfu.itis.iskander.mysound.services.interfaces.TrackFilesService;
import ru.kpfu.itis.iskander.mysound.services.interfaces.TrackService;

import java.util.List;

@Component
public class TrackServiceImpl implements TrackService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TracksRepository tracksRepository;

    @Autowired
    private TrackFilesService trackFilesService;

    @Autowired
    private TrackBuilder trackBuilder;

    @Override
    public void create(TrackForm form, String username)
            throws UndefinedServerProblemException, AudioInvalidException, PosterInvalidException {
        saveTrack(username, form, null);
    }

    @Override
    public void update(TrackForm form, String username, Long trackId)
            throws PosterInvalidException, AudioInvalidException, UndefinedServerProblemException {
        saveTrack(username, form, trackId);
    }

    @Override
    public boolean delete(Long id, String username) {
        Track track = tracksRepository.findById(id).orElse(null);
        if (track == null)
            return false;

        try {
            trackFilesService.deletePoster(track.getPoster());
            trackFilesService.deleteAudio(track.getAudio());
            tracksRepository.deleteById(id);
            return true;
        } catch (UndefinedServerProblemException e) {
            return false;
        }
    }

    @Override
    public Track getMyTrack(Long id, String username) throws TrackNotFound {
        return tracksRepository.findByIdAndUserUsername(id, username).orElseThrow(TrackNotFound::new);
    }

    @Override
    public boolean isUserAuthor(Long trackId, String username) {
        return tracksRepository.existsByIdAndUserUsername(trackId, username);
    }

    @Override
    public List<Track> getList(String username) {
        return trackBuilder.build(tracksRepository.getAllByUserUsername(username));
    }

    @Override
    public Track getTrack(Long id) throws TrackNotFound {
        return trackBuilder.build(tracksRepository.findById(id).orElseThrow(TrackNotFound::new));
    }

    @Override
    public List<Track> getAll() {
        return trackBuilder.build(tracksRepository.findAll());
    }

    @Override
    public List<Track> getPopular() {
        return trackBuilder.build(tracksRepository.findAllByOrderByNumberOfListensDesc());
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
