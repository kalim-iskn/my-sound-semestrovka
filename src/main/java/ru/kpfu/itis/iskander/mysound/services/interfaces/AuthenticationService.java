package ru.kpfu.itis.iskander.mysound.services.interfaces;

/**
 * Should be used for programmatic authentication (on registration or oauth)
 */
public interface AuthenticationService {

    void authenticate(String username);

}
