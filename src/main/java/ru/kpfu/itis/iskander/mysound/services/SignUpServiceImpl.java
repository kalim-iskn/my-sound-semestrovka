package ru.kpfu.itis.iskander.mysound.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.iskander.mysound.config.ProjectProperties;
import ru.kpfu.itis.iskander.mysound.dto.AttachmentDTO;
import ru.kpfu.itis.iskander.mysound.dto.SignUpForm;
import ru.kpfu.itis.iskander.mysound.exceptions.AvatarInvalidException;
import ru.kpfu.itis.iskander.mysound.exceptions.CoverInvalidException;
import ru.kpfu.itis.iskander.mysound.exceptions.InvalidAttachmentException;
import ru.kpfu.itis.iskander.mysound.exceptions.PasswordsNotMatchException;
import ru.kpfu.itis.iskander.mysound.models.User;
import ru.kpfu.itis.iskander.mysound.repositories.UsersRepository;
import ru.kpfu.itis.iskander.mysound.services.interfaces.AttachmentService;
import ru.kpfu.itis.iskander.mysound.services.interfaces.SignUpService;

import java.io.IOException;

@Component
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProjectProperties projectProperties;

    @Override
    public void signUp(SignUpForm signUpForm)
            throws PasswordsNotMatchException, AvatarInvalidException, CoverInvalidException {

        if (!signUpForm.getPassword().equals(signUpForm.getRePassword()))
            throw new PasswordsNotMatchException();

        String avatar;
        String cover = null;

        try {
            avatar = uploadImage(
                    signUpForm.getAvatar(),
                    projectProperties.getAvatarsMaxSize(),
                    projectProperties.getAvatarsDirectory()
            );
        } catch (IOException | InvalidAttachmentException e) {
            throw new AvatarInvalidException();
        }

        if (!signUpForm.getCover().isEmpty()) {
            try {
                cover = uploadImage(
                        signUpForm.getCover(),
                        projectProperties.getCoverMaxSize(),
                        projectProperties.getCoversDirectory()
                );
            } catch (IOException | InvalidAttachmentException e) {
                throw new CoverInvalidException();
            }
        }

        User user = User.builder()
                .email(signUpForm.getEmail())
                .password(passwordEncoder.encode(signUpForm.getPassword()))
                .username(signUpForm.getUsername())
                .pseudonym(signUpForm.getPseudonym())
                .bio(signUpForm.getBio())
                .avatar(avatar)
                .cover(cover)
                .build();

        System.out.println(user.getIsVerified());

        usersRepository.save(user);
    }

    private String uploadImage(
            MultipartFile file,
            float maxSize,
            String directory
    ) throws IOException, InvalidAttachmentException {
        AttachmentDTO attachmentDTO = AttachmentDTO.builder()
                .file(file)
                .requiredMimes(projectProperties.getRequiredImgMimes())
                .requiredSize(maxSize)
                .uploadDir(directory)
                .fileExtension("jpg")
                .build();

        return attachmentService.uploadFile(attachmentDTO);
    }

}
