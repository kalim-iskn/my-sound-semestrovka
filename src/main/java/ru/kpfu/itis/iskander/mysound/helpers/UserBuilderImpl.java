package ru.kpfu.itis.iskander.mysound.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.iskander.mysound.config.ProjectProperties;
import ru.kpfu.itis.iskander.mysound.helpers.interfaces.UserBuilder;
import ru.kpfu.itis.iskander.mysound.models.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserBuilderImpl implements UserBuilder {

    @Autowired
    private ProjectProperties projectProperties;

    public User build(User user) {
        String mediaDir = "/" + projectProperties.getMediaDirectory() + "/";
        if (user != null) {
            String avatar;

            if (user.getAvatar() == null)
                avatar = projectProperties.getNoAvatarUrl();
            else
                avatar = mediaDir + projectProperties.getAvatarsDirectory() + "/" + user.getAvatar();

            user.setAvatarUrl(avatar);
            user.setCoverUrl(mediaDir + projectProperties.getCoversDirectory() + "/" + user.getCover());
        }
        return user;
    }

    @Override
    public List<User> build(List<User> users) {
        return users.stream().map(this::build).collect(Collectors.toList());
    }

}
