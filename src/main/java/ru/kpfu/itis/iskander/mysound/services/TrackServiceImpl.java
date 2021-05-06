package ru.kpfu.itis.iskander.mysound.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.iskander.mysound.dto.LikeDto;
import ru.kpfu.itis.iskander.mysound.dto.TrackForm;
import ru.kpfu.itis.iskander.mysound.exceptions.*;
import ru.kpfu.itis.iskander.mysound.helpers.interfaces.TrackBuilder;
import ru.kpfu.itis.iskander.mysound.models.Track;
import ru.kpfu.itis.iskander.mysound.models.User;
import ru.kpfu.itis.iskander.mysound.repositories.TracksRepository;
import ru.kpfu.itis.iskander.mysound.repositories.UsersRepository;
import ru.kpfu.itis.iskander.mysound.services.interfaces.TrackFilesService;
import ru.kpfu.itis.iskander.mysound.services.interfaces.TrackService;

import java.util.List;
import java.util.Map;

@Service
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
            tracksRepository.deleteById(id);
            trackFilesService.deletePoster(track.getPoster());
            trackFilesService.deleteAudio(track.getAudio());
            return true;
        } catch (UndefinedServerProblemException e) {
            return false;
        }
    }

    @Override
    public Track getMyTrack(Long id, String username) throws TrackNotFoundException {
        return tracksRepository.findByIdAndUserUsername(id, username).orElseThrow(TrackNotFoundException::new);
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
    public Track getTrack(Long id) throws TrackNotFoundException {
        return trackBuilder.build(tracksRepository.findById(id).orElseThrow(TrackNotFoundException::new));
    }

    @Override
    public List<Track> getAll() {
        return trackBuilder.build(tracksRepository.findAll());
    }

    @Override
    public List<Track> getPopular() {
        return trackBuilder.build(tracksRepository.findAllByOrderByNumberOfListensDesc());
    }

    @Override
    public void likeTrack(LikeDto likeDto) throws TrackNotFoundException, UserNotFoundException {
        Track track = tracksRepository.findById(likeDto.getTrackId()).orElseThrow(TrackNotFoundException::new);
        User user = usersRepository.findByUsername(likeDto.getUsername()).orElseThrow(UserNotFoundException::new);

        List<User> list = track.getLikes();
        if (!list.contains(user)) {
            list.add(user);
            track.setLikes(list);
            track.setNumberOfLikes(track.getNumberOfLikes() + 1);
            tracksRepository.save(track);
        }
    }

    @Override
    public Map<String, Long> getStatistic(Long userId) {
        return tracksRepository.getStatistic(userId);
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
