package ru.kpfu.itis.iskander.mysound.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.iskander.mysound.validators.annotations.UniqueUsername;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditProfileForm {

    @Pattern(
            regexp = "^(?=.{2,30}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$",
            message = "{validation.username.requirements}"
    )
    @UniqueUsername(onePossible = true)
    private String username;

    @NotBlank(message = "{validation.field.required}")
    @Size(max = 255, message = "{validation.pseudonym.max_length}")
    private String pseudonym;

    @Size(max = 500, message = "{validation.bio.max_length}")
    private String bio;

    private MultipartFile avatar;

    private MultipartFile cover;

}
