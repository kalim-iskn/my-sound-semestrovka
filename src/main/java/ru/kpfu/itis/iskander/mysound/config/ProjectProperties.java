package ru.kpfu.itis.iskander.mysound.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

@ConfigurationProperties("app")
@Data
public class ProjectProperties {

    private String staticDirectory = "static";

    private String mediaDirectory = "media";

    private String avatarsDirectory = "avatars";

    private String coversDirectory = "covers";

    private String postersDirectory = "posters";

    private String audiosDirectory = "audios";

    private long avatarsMaxSize = 3145728;

    private long coverMaxSize = 3145728;

    private long posterMaxSize = 3145728;

    private long audioMaxSize = 15728640;

    private String[] requiredAudioMimes = new String[]{"audio/mpeg"};

    private String[] requiredImgMimes = new String[]{"image/jpeg", "image/png"};

    private String vkApiVersion = "5.103";

    private long vkApiClientId = 7839199;

    private String vkApiClientSecret = "kDRfYCSfFR2sAg7NsJWa";

    private String vkRedirectUri = "http://127.0.0.1:8080/mysound/oauth/vk";

    private Locale locale = Locale.ENGLISH;

    private String noAvatarUrl = "img/no-photo.jpg";

}
