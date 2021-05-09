package ru.kpfu.itis.iskander.mysound.config.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.iskander.mysound.exceptions.UserNotFoundException;
import ru.kpfu.itis.iskander.mysound.models.User;
import ru.kpfu.itis.iskander.mysound.services.interfaces.UsersService;

@RestController
public class UserRestController {

    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "api/user/{username}", method = RequestMethod.GET)
    public ResponseEntity<User> get(@PathVariable String username) throws UserNotFoundException {
        return ResponseEntity.ok(usersService.getUser(username));
    }

}
