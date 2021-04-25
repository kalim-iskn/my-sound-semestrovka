package ru.kpfu.itis.iskander.mysound.services.interfaces;

import org.springframework.security.acls.model.NotFoundException;
import ru.kpfu.itis.iskander.mysound.models.User;

public interface UsersService {

    User getUser(String username) throws NotFoundException;

}
