package ru.kpfu.itis.iskander.mysound.services;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.iskander.mysound.dto.AttachmentDto;
import ru.kpfu.itis.iskander.mysound.exceptions.InvalidAttachmentException;
import ru.kpfu.itis.iskander.mysound.services.interfaces.AttachmentService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.ZonedDateTime;
import java.util.Arrays;

@Component
public class AttachmentServiceImpl implements AttachmentService {

    @Override
    public String uploadFile(AttachmentDto attachment) throws InvalidAttachmentException, IOException {

        MultipartFile file = attachment.getFile();

        Tika tika = new Tika();
        String mimeType = tika.detect(file.getBytes());

        boolean isValidMimeType = Arrays.asList(attachment.getRequiredMimes()).contains(mimeType);

        if (file.getSize() <= attachment.getRequiredSize() && isValidMimeType) {
            String name = file.getOriginalFilename() != null ? file.getOriginalFilename() : "";

            String fileName = DigestUtils.md5DigestAsHex(
                    (ZonedDateTime.now().toInstant().toEpochMilli() + name).getBytes()
            ) + "." + attachment.getFileExtension();

            try (InputStream inputStream = file.getInputStream()) {
                Path uploadPath = Paths.get(attachment.getUploadDir());
                Path filePath = uploadPath.resolve(fileName);

                if (!Files.exists(uploadPath))
                    Files.createDirectory(uploadPath);

                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                return fileName;
            } catch (IOException ioe) {
                //TODO add to logs
                throw new IOException("Could not save image file: " + fileName, ioe);
            }
        } else {
            throw new InvalidAttachmentException();
        }
    }

    @Override
    public boolean deleteFile(String filename, String directory) throws IOException {
        Path path = Paths.get(directory, filename);
        return Files.deleteIfExists(path);
    }

}
