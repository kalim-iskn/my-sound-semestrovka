package ru.kpfu.itis.iskander.mysound.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.iskander.mysound.helpers.interfaces.UserBuilder;
import ru.kpfu.itis.iskander.mysound.models.User;
import ru.kpfu.itis.iskander.mysound.repositories.UsersRepository;
import ru.kpfu.itis.iskander.mysound.services.interfaces.UsersService;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserBuilder userBuilder;

    @Override
    public User getUser(String username) throws NotFoundException {
        User user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User with username " + username + " not found"));
        return userBuilder.build(user);
    }

    @Override
    public List<User> getAll() {
        return userBuilder.build(usersRepository.findAllByOrderByIsVerifiedDesc());
    }

}
