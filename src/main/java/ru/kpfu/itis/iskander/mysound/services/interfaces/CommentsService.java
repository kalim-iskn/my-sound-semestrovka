package ru.kpfu.itis.iskander.mysound.services.interfaces;

import ru.kpfu.itis.iskander.mysound.dto.CommentDto;
import ru.kpfu.itis.iskander.mysound.exceptions.TrackNotFoundException;

public interface CommentsService {

    void add(CommentDto commentDto) throws TrackNotFoundException;

}
