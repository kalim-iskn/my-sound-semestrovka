package ru.kpfu.itis.iskander.mysound.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.kpfu.itis.iskander.mysound.converters.JsonToUserAccessDataDtoConverter;
import ru.kpfu.itis.iskander.mysound.converters.JsonToUserInfoVkDto;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private ProjectProperties projectProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String mediaDir = projectProperties.getMediaDirectory();
        registry.addResourceHandler("/" + mediaDir + "/**").addResourceLocations("file:" + mediaDir + "/");
    }

    @Override
    public void addFormatters(FormatterRegistry formatterRegistry) {
        formatterRegistry.addConverter(userAccessDataDtoConverter());
        formatterRegistry.addConverter(userInfoVkDtoConverter());
    }

    @Bean
    public JsonToUserAccessDataDtoConverter userAccessDataDtoConverter() {
        return new JsonToUserAccessDataDtoConverter();
    }

    @Bean
    public JsonToUserInfoVkDto userInfoVkDtoConverter() {
        return new JsonToUserInfoVkDto();
    }

}
