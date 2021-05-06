package ru.kpfu.itis.iskander.mysound.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.iskander.mysound.dto.PostForm;
import ru.kpfu.itis.iskander.mysound.models.Post;
import ru.kpfu.itis.iskander.mysound.models.User;
import ru.kpfu.itis.iskander.mysound.repositories.PostsRepository;
import ru.kpfu.itis.iskander.mysound.services.interfaces.PostsService;
import ru.kpfu.itis.iskander.mysound.services.interfaces.UsersService;

import java.util.List;

@Service
public class PostsServiceImpl implements PostsService {

    @Autowired
    private UsersService usersService;

    @Autowired
    private PostsRepository postsRepository;

    @Override
    public void add(PostForm postForm) {
        User user = usersService.getUser(postForm.getUsername());
        Post post = Post.builder()
                .user(user)
                .text(postForm.getText())
                .build();
        postsRepository.save(post);
    }

    @Override
    public List<Post> getSorted(User user) {
        return postsRepository.findAllByUserOrderByIdDesc(user);
    }

}
