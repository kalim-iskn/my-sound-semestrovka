package ru.kpfu.itis.iskander.mysound.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.iskander.mysound.helpers.UrlsSetter;
import ru.kpfu.itis.iskander.mysound.models.User;
import ru.kpfu.itis.iskander.mysound.repositories.UsersRepository;
import ru.kpfu.itis.iskander.mysound.services.interfaces.UsersService;

@Component
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UrlsSetter urlsSetter;

    @Override
    public User getUser(String username) throws NotFoundException {
        User user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User with username " + username + " not found"));
        user.setCoverUrl("erq");
        urlsSetter.setToUser(user);
        return user;
    }

}