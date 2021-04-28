package ru.kpfu.itis.iskander.mysound.services.interfaces;

import ru.kpfu.itis.iskander.mysound.dto.EditProfileForm;
import ru.kpfu.itis.iskander.mysound.exceptions.AvatarInvalidException;
import ru.kpfu.itis.iskander.mysound.exceptions.CoverInvalidException;
import ru.kpfu.itis.iskander.mysound.exceptions.UndefinedServerProblemException;
import ru.kpfu.itis.iskander.mysound.exceptions.UsernameNotUniqueException;

public interface EditProfileService {

    void editProfile(EditProfileForm form, String authUsername) throws UndefinedServerProblemException,
            AvatarInvalidException, CoverInvalidException, UsernameNotUniqueException;

}
