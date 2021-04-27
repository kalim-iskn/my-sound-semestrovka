package ru.kpfu.itis.iskander.mysound.services.interfaces;

import ru.kpfu.itis.iskander.mysound.dto.SignUpForm;
import ru.kpfu.itis.iskander.mysound.exceptions.AvatarInvalidException;
import ru.kpfu.itis.iskander.mysound.exceptions.CoverInvalidException;
import ru.kpfu.itis.iskander.mysound.exceptions.PasswordsNotMatchException;
import ru.kpfu.itis.iskander.mysound.models.User;

public interface SignUpService {

    void setVkOptions(Long vkId);

    User signUp(SignUpForm signUpForm) throws PasswordsNotMatchException, AvatarInvalidException, CoverInvalidException;

}
