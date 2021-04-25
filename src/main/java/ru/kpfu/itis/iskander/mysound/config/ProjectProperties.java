package ru.kpfu.itis.iskander.mysound.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app")
@Data
public class ProjectProperties {

    private String avatarsDirectory = "avatars";

    private float avatarsMaxSize = 3145728;

    private String[] requiredImgMimes = new String[]{"image/jpeg", "image/png"};

    private String staticDirectory = "static";

    private String coversDirectory = "covers";

    private float coverMaxSize = 3145728;

}
