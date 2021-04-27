package ru.kpfu.itis.iskander.mysound.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.iskander.mysound.config.ProjectProperties;
import ru.kpfu.itis.iskander.mysound.models.User;

@Component
public class UserBuilder {

    @Autowired
    private ProjectProperties projectProperties;

    public User build(User user) {
        if (user != null) {
            String avatar;

            if (user.getAvatar() == null)
                avatar = projectProperties.getNoAvatarUrl();
            else
                avatar = "/" + projectProperties.getAvatarsDirectory() + "/" + user.getAvatar();

            user.setAvatarUrl(avatar);
            user.setCoverUrl("/" + projectProperties.getCoversDirectory() + "/" + user.getCover());
        }
        return user;
    }

}
