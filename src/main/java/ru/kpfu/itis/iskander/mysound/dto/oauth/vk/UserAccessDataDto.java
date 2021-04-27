package ru.kpfu.itis.iskander.mysound.dto.oauth.vk;

import lombok.Data;

@Data
public class UserAccessDataDto {

    private String accessToken;
    private String expiresIn;
    private Long userId;
    private String email;

    public boolean isNull() {
        return accessToken == null || expiresIn == null || userId == null || email == null;
    }

}
