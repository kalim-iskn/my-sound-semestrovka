package ru.kpfu.itis.iskander.mysound.services.interfaces;

import ru.kpfu.itis.iskander.mysound.dto.PostForm;
import ru.kpfu.itis.iskander.mysound.models.Post;
import ru.kpfu.itis.iskander.mysound.models.User;

import java.util.List;

public interface PostsService {

    void add(PostForm postForm);

    List<Post> getSorted(User user);

}
