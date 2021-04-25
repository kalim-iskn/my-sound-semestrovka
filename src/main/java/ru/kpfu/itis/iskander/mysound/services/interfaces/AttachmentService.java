package ru.kpfu.itis.iskander.mysound.services.interfaces;

import ru.kpfu.itis.iskander.mysound.dto.AttachmentDTO;
import ru.kpfu.itis.iskander.mysound.exceptions.InvalidAttachmentException;

import java.io.IOException;

public interface AttachmentService {
    String uploadFile(AttachmentDTO attachment) throws InvalidAttachmentException, IOException;
}
