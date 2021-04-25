package ru.kpfu.itis.iskander.mysound.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.iskander.mysound.config.ProjectProperties;
import ru.kpfu.itis.iskander.mysound.models.User;

@Component
public class UrlsSetter {

    @Autowired
    private ProjectProperties projectProperties;

    public void setToUser(User user) {
        if (user != null) {
            user.setAvatarUrl("/" + projectProperties.getAvatarsDirectory() + "/" + user.getAvatar());
            user.setCoverUrl("/" + projectProperties.getCoversDirectory() + "/" + user.getCover());
        }
    }

}
