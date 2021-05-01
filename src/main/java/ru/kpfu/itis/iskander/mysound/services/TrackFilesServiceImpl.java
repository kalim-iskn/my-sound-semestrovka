package ru.kpfu.itis.iskander.mysound.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.iskander.mysound.config.ProjectProperties;
import ru.kpfu.itis.iskander.mysound.dto.AttachmentDto;
import ru.kpfu.itis.iskander.mysound.exceptions.AudioInvalidException;
import ru.kpfu.itis.iskander.mysound.exceptions.InvalidAttachmentException;
import ru.kpfu.itis.iskander.mysound.exceptions.PosterInvalidException;
import ru.kpfu.itis.iskander.mysound.exceptions.UndefinedServerProblemException;
import ru.kpfu.itis.iskander.mysound.services.interfaces.MediaService;
import ru.kpfu.itis.iskander.mysound.services.interfaces.TrackFilesService;

import java.io.IOException;

@Component
public class TrackFilesServiceImpl implements TrackFilesService {

    @Autowired
    private ProjectProperties projectProperties;

    @Autowired
    private MediaService mediaService;

    public String uploadAudio(MultipartFile file) throws AudioInvalidException {
        try {
            AttachmentDto attachmentDto = AttachmentDto.builder()
                    .file(file)
                    .fileExtension("mp3")
                    .requiredMimes(projectProperties.getRequiredAudioMimes())
                    .requiredSize(projectProperties.getAudioMaxSize())
                    .uploadDir(projectProperties.getAudiosDirectory())
                    .build();
            return mediaService.uploadFile(attachmentDto);
        } catch (IOException | InvalidAttachmentException e) {
            throw new AudioInvalidException();
        }
    }

    public String uploadPoster(MultipartFile file) throws PosterInvalidException {
        try {
            AttachmentDto attachmentDto = AttachmentDto.builder()
                    .file(file)
                    .fileExtension("jpg")
                    .requiredMimes(projectProperties.getRequiredImgMimes())
                    .requiredSize(projectProperties.getPosterMaxSize())
                    .uploadDir(projectProperties.getPostersDirectory())
                    .build();
            return mediaService.uploadFile(attachmentDto);
        } catch (IOException | InvalidAttachmentException e) {
            throw new PosterInvalidException();
        }
    }

    @Override
    public void deleteAudio(String audio) throws UndefinedServerProblemException {
        try {
            mediaService.deleteFile(audio, projectProperties.getAudiosDirectory());
        } catch (IOException e) {
            throw new UndefinedServerProblemException();
        }
    }

    @Override
    public void deletePoster(String poster) throws UndefinedServerProblemException {
        try {
            mediaService.deleteFile(poster, projectProperties.getPostersDirectory());
        } catch (IOException e) {
            throw new UndefinedServerProblemException();
        }
    }

}
