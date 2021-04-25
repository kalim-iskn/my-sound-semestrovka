package ru.kpfu.itis.iskander.mysound.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.iskander.mysound.validators.annotations.UniqueEmail;
import ru.kpfu.itis.iskander.mysound.validators.annotations.UniqueUsername;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class SignUpForm {

    @Pattern(
            regexp = "^(?=.{2,30}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$",
            message = "{validation.username.requirements}"
    )
    @UniqueUsername
    private String username;

    @NotEmpty(message = "{validation.field.required}")
    private String pseudonym;

    private String bio;

    @Pattern(
            regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "{validation.email.regexp}"
    )
    @UniqueEmail
    private String email;

    @Length(min = 8, message = "{validation.password.requirements}")
    private String password;

    @NotEmpty(message = "{validation.field.required}")
    private String rePassword;

    private MultipartFile avatar;

    private MultipartFile cover;

}
