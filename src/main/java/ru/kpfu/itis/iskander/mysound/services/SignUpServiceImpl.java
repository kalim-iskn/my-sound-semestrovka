package ru.kpfu.itis.iskander.mysound.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.iskander.mysound.dto.SignUpForm;
import ru.kpfu.itis.iskander.mysound.exceptions.AvatarInvalidException;
import ru.kpfu.itis.iskander.mysound.exceptions.CoverInvalidException;
import ru.kpfu.itis.iskander.mysound.exceptions.InvalidAttachmentException;
import ru.kpfu.itis.iskander.mysound.exceptions.PasswordsNotMatchException;
import ru.kpfu.itis.iskander.mysound.models.User;
import ru.kpfu.itis.iskander.mysound.repositories.UsersRepository;
import ru.kpfu.itis.iskander.mysound.services.interfaces.SignUpService;
import ru.kpfu.itis.iskander.mysound.services.interfaces.UserPhotosService;

import java.io.IOException;

@Component
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserPhotosService userPhotosService;

    private Boolean isOauthRegistration = false;
    private Long vkUserId = null;

    public void setVkOptions(Long vkUserId) {
        isOauthRegistration = true;
        this.vkUserId = vkUserId;
    }

    @Override
    public User signUp(SignUpForm signUpForm)
            throws PasswordsNotMatchException, AvatarInvalidException, CoverInvalidException {

        if (signUpForm.getPassword() != null && !signUpForm.getPassword().equals(signUpForm.getRePassword()))
            throw new PasswordsNotMatchException();

        String avatar = null;
        String cover = null;

        if (!isOauthRegistration) {
            try {
                avatar = userPhotosService.uploadAvatar(signUpForm.getAvatar());
            } catch (IOException | InvalidAttachmentException e) {
                throw new AvatarInvalidException();
            }
        }

        if (signUpForm.getCover() != null && !signUpForm.getCover().isEmpty()) {
            try {
                cover = userPhotosService.uploadCover(signUpForm.getCover());
            } catch (IOException | InvalidAttachmentException e) {
                throw new CoverInvalidException();
            }
        }

        User user = User.builder()
                .email(signUpForm.getEmail())
                .username(signUpForm.getUsername())
                .pseudonym(signUpForm.getPseudonym())
                .bio(signUpForm.getBio())
                .avatar(avatar)
                .cover(cover)
                .vkUserId(vkUserId)
                .build();

        if (!isOauthRegistration)
            user.setPassword(passwordEncoder.encode(signUpForm.getPassword()));

        return usersRepository.save(user);
    }

}
