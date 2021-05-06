package ru.kpfu.itis.iskander.mysound.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.iskander.mysound.dto.CommentForm;
import ru.kpfu.itis.iskander.mysound.exceptions.TrackNotFoundException;
import ru.kpfu.itis.iskander.mysound.models.Comment;
import ru.kpfu.itis.iskander.mysound.models.Track;
import ru.kpfu.itis.iskander.mysound.models.User;
import ru.kpfu.itis.iskander.mysound.repositories.CommentsRepository;
import ru.kpfu.itis.iskander.mysound.repositories.TracksRepository;
import ru.kpfu.itis.iskander.mysound.services.interfaces.CommentsService;
import ru.kpfu.itis.iskander.mysound.services.interfaces.TrackService;
import ru.kpfu.itis.iskander.mysound.services.interfaces.UsersService;

@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private UsersService usersService;

    @Autowired
    private TrackService trackService;

    @Autowired
    private TracksRepository tracksRepository;

    @Override
    public void add(CommentForm commentForm) throws TrackNotFoundException {
        Track track = trackService.getTrack(commentForm.getTrackId());
        User user = usersService.getUser(commentForm.getUsername());

        Comment comment = Comment.builder()
                .text(commentForm.getText())
                .track(track)
                .user(user)
                .build();

        commentsRepository.save(comment);
        track.setNumberOfComments(track.getNumberOfComments() + 1);
        tracksRepository.save(track);
    }

}
