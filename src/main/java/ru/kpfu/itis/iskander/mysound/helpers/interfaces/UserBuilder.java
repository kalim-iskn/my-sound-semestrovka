package ru.kpfu.itis.iskander.mysound.helpers.interfaces;

import ru.kpfu.itis.iskander.mysound.models.User;

import java.util.List;

public interface UserBuilder {

    User build(User user);

    List<User> build(List<User> users);

}
