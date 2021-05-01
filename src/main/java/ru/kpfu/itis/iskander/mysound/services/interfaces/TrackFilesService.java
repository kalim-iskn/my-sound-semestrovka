package ru.kpfu.itis.iskander.mysound.services.interfaces;

import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.iskander.mysound.exceptions.AudioInvalidException;
import ru.kpfu.itis.iskander.mysound.exceptions.PosterInvalidException;
import ru.kpfu.itis.iskander.mysound.exceptions.UndefinedServerProblemException;

public interface TrackFilesService {

    String uploadAudio(MultipartFile file) throws AudioInvalidException;

    String uploadPoster(MultipartFile file) throws PosterInvalidException;

    void deleteAudio(String audio) throws UndefinedServerProblemException;

    void deletePoster(String poster) throws UndefinedServerProblemException;

}
