package ru.kpfu.itis.iskander.mysound.services.interfaces;

import ru.kpfu.itis.iskander.mysound.exceptions.UserNotFoundException;
import ru.kpfu.itis.iskander.mysound.models.User;

import java.util.List;

public interface UsersService {

    User getUser(String username) throws UserNotFoundException;

    List<User> getAll();

}
