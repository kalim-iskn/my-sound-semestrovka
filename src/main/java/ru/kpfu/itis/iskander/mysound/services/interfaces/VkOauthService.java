package ru.kpfu.itis.iskander.mysound.services.interfaces;

import ru.kpfu.itis.iskander.mysound.exceptions.ConnectionToUrlException;
import ru.kpfu.itis.iskander.mysound.exceptions.EmailAlreadyExistException;
import ru.kpfu.itis.iskander.mysound.exceptions.UndefinedServerProblemException;
import ru.kpfu.itis.iskander.mysound.models.User;

public interface VkOauthService {

    String getLink();

    User authorize(String code) throws ConnectionToUrlException, UndefinedServerProblemException, EmailAlreadyExistException;

}
