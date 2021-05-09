package ru.kpfu.itis.iskander.mysound.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import ru.kpfu.itis.iskander.mysound.dto.oauth.vk.UserInfoVk;
import ru.kpfu.itis.iskander.mysound.helpers.interfaces.JsonHelper;

public class JsonToUserInfoVkDto implements Converter<String, UserInfoVk> {

    @Autowired
    private JsonHelper jsonHelper;

    @Override
    public UserInfoVk convert(String source) {
        return jsonHelper.convertFromJson(source, UserInfoVk.class, "response");
    }

}
