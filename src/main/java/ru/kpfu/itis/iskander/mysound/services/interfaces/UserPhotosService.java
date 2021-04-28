package ru.kpfu.itis.iskander.mysound.services.interfaces;

import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.iskander.mysound.exceptions.InvalidAttachmentException;
import ru.kpfu.itis.iskander.mysound.exceptions.UndefinedServerProblemException;

import java.io.IOException;

public interface UserPhotosService {

    String uploadCover(MultipartFile file) throws IOException, InvalidAttachmentException;

    String uploadAvatar(MultipartFile file) throws IOException, InvalidAttachmentException;

    void deleteAvatar(String avatar) throws UndefinedServerProblemException;

    void deleteCover(String cover) throws UndefinedServerProblemException;

}
