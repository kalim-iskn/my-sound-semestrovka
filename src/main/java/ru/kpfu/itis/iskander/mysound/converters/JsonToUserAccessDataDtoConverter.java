package ru.kpfu.itis.iskander.mysound.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import ru.kpfu.itis.iskander.mysound.dto.oauth.vk.UserAccessDataDto;
import ru.kpfu.itis.iskander.mysound.helpers.interfaces.JsonHelper;

public class JsonToUserAccessDataDtoConverter implements Converter<String, UserAccessDataDto> {

    @Autowired
    private JsonHelper jsonHelper;

    @Override
    public UserAccessDataDto convert(String source) {
        return jsonHelper.convertFromJson(source, UserAccessDataDto.class, null);
    }

}
