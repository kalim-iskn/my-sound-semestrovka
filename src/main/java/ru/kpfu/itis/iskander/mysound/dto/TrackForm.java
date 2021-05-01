package ru.kpfu.itis.iskander.mysound.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrackForm {

    @NotBlank(message = "{validation.field.required}", groups = {AddTrackForm.class, EditTrackForm.class})
    private String name;

    @Size(
            max = 1000,
            message = "{validation.track.description.max}",
            groups = {AddTrackForm.class, EditTrackForm.class}
    )
    private String description;

    @NotNull(message = "{validation.field.required}", groups = {AddTrackForm.class})
    private MultipartFile poster;

    @NotNull(message = "{validation.field.required}", groups = {AddTrackForm.class})
    private MultipartFile audio;

}
