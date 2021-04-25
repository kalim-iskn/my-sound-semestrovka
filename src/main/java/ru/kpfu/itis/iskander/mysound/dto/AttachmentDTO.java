package ru.kpfu.itis.iskander.mysound.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class AttachmentDTO {

    private MultipartFile file;
    private String[] requiredMimes;
    private float requiredSize;
    private String uploadDir;
    private String fileExtension;

}
