package ru.kpfu.itis.iskander.mysound.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.iskander.mysound.config.ProjectProperties;
import ru.kpfu.itis.iskander.mysound.dto.AttachmentDto;
import ru.kpfu.itis.iskander.mysound.exceptions.InvalidAttachmentException;
import ru.kpfu.itis.iskander.mysound.exceptions.UndefinedServerProblemException;
import ru.kpfu.itis.iskander.mysound.services.interfaces.MediaService;
import ru.kpfu.itis.iskander.mysound.services.interfaces.UserPhotosService;

import java.io.IOException;

@Component
public class UserPhotosServiceImpl implements UserPhotosService {

    @Autowired
    private ProjectProperties projectProperties;

    @Autowired
    private MediaService mediaService;

    public String uploadAvatar(MultipartFile file) throws IOException, InvalidAttachmentException {
        return uploadImage(
                file,
                projectProperties.getAvatarsMaxSize(),
                projectProperties.getAvatarsDirectory()
        );
    }

    public String uploadCover(MultipartFile file) throws IOException, InvalidAttachmentException {
        return uploadImage(
                file,
                projectProperties.getCoverMaxSize(),
                projectProperties.getCoversDirectory()
        );
    }

    private String uploadImage(
            MultipartFile file,
            float maxSize,
            String directory
    ) throws IOException, InvalidAttachmentException {
        AttachmentDto attachmentDTO = AttachmentDto.builder()
                .file(file)
                .requiredMimes(projectProperties.getRequiredImgMimes())
                .requiredSize(maxSize)
                .uploadDir(directory)
                .fileExtension("jpg")
                .build();

        return mediaService.uploadFile(attachmentDTO);
    }

    @Override
    public void deleteAvatar(String avatar) throws UndefinedServerProblemException {
        try {
            mediaService.deleteFile(avatar, projectProperties.getAvatarsDirectory());
        } catch (IOException e) {
            throw new UndefinedServerProblemException();
        }
    }

    @Override
    public void deleteCover(String cover) throws UndefinedServerProblemException {
        try {
            mediaService.deleteFile(cover, projectProperties.getCoversDirectory());
        } catch (IOException e) {
            throw new UndefinedServerProblemException();
        }
    }

}
