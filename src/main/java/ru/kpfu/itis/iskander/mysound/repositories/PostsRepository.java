package ru.kpfu.itis.iskander.mysound.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.iskander.mysound.models.Post;
import ru.kpfu.itis.iskander.mysound.models.User;

import java.util.List;

public interface PostsRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByUserOrderByIdDesc(User user);

}
