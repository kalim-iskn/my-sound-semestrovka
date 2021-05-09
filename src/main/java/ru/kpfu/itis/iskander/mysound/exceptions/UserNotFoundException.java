package ru.kpfu.itis.iskander.mysound.exceptions;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException(String username) {
        super("User with username " + username + " not found");
    }

    public UserNotFoundException() {
        super("User not found");
    }

}
