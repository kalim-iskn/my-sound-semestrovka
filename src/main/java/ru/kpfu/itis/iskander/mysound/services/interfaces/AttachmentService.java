package ru.kpfu.itis.iskander.mysound.services.interfaces;

import ru.kpfu.itis.iskander.mysound.dto.AttachmentDto;
import ru.kpfu.itis.iskander.mysound.exceptions.InvalidAttachmentException;

import java.io.IOException;

public interface AttachmentService {

    String uploadFile(AttachmentDto attachment) throws InvalidAttachmentException, IOException;

    boolean deleteFile(String filename, String directory) throws IOException;

}
