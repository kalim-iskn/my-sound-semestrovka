package ru.kpfu.itis.iskander.mysound.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.iskander.mysound.dto.EditProfileForm;
import ru.kpfu.itis.iskander.mysound.exceptions.*;
import ru.kpfu.itis.iskander.mysound.models.User;
import ru.kpfu.itis.iskander.mysound.repositories.UsersRepository;
import ru.kpfu.itis.iskander.mysound.services.interfaces.EditProfileService;
import ru.kpfu.itis.iskander.mysound.services.interfaces.UserPhotosService;

import java.io.IOException;

@Component
public class EditProfileServiceImpl implements EditProfileService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserPhotosService userPhotosService;

    @Override
    public void editProfile(EditProfileForm form, String authUsername) throws UndefinedServerProblemException,
            AvatarInvalidException, CoverInvalidException, UsernameNotUniqueException {
        User user = usersRepository.findByUsername(authUsername).orElseThrow(UndefinedServerProblemException::new);

        if (!user.getUsername().equals(form.getUsername())) {
            if (usersRepository.existsByUsernameAndIdIsNot(form.getUsername(), user.getId()))
                throw new UsernameNotUniqueException();
        }

        if (form.getAvatar() != null && !form.getAvatar().isEmpty()) {
            try {
                String old = user.getAvatar();
                user.setAvatar(userPhotosService.uploadAvatar(form.getAvatar()));
                if (old != null)
                    userPhotosService.deleteAvatar(old);
            } catch (IOException | InvalidAttachmentException e) {
                throw new AvatarInvalidException();
            }
        }

        if (form.getCover() != null && !form.getCover().isEmpty()) {
            try {
                String old = user.getCover();
                user.setCover(userPhotosService.uploadCover(form.getCover()));
                if (old != null)
                    userPhotosService.deleteCover(old);
            } catch (IOException | InvalidAttachmentException e) {
                throw new CoverInvalidException();
            }
        }

        user.setBio(form.getBio());
        user.setUsername(form.getUsername());
        user.setPseudonym(form.getPseudonym());

        usersRepository.save(user);
    }

}
