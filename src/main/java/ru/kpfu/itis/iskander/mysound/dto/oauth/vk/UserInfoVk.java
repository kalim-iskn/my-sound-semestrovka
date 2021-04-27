package ru.kpfu.itis.iskander.mysound.dto.oauth.vk;

import lombok.Data;

@Data
public class UserInfoVk {

    private Long id;
    private String firstName;
    private String lastName;
    private String screenName;
    private String photoBig;
    private String email;
    private String about;

    /**
     * Check only important fields
     *
     * @return boolean
     */
    public boolean isNull() {
        return id == null || firstName == null || lastName == null || screenName == null || email == null;
    }

    public String getPseudonym() {
        return firstName + " " + lastName;
    }

}
