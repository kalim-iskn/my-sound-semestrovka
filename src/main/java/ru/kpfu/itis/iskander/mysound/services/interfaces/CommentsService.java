package ru.kpfu.itis.iskander.mysound.services.interfaces;

import ru.kpfu.itis.iskander.mysound.dto.CommentForm;
import ru.kpfu.itis.iskander.mysound.exceptions.TrackNotFoundException;

public interface CommentsService {

    void add(CommentForm commentForm) throws TrackNotFoundException;

}
