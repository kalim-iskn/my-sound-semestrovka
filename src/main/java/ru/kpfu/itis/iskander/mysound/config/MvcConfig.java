package ru.kpfu.itis.iskander.mysound.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.kpfu.itis.iskander.mysound.converters.JsonToUserAccessDataDtoConverter;
import ru.kpfu.itis.iskander.mysound.converters.JsonToUserInfoVkDto;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private ProjectProperties projectProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String mediaDir = projectProperties.getMediaDirectory();
        registry.addResourceHandler("/" + mediaDir + "/**").addResourceLocations("/" + mediaDir + "/");
    }

    /*private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get(dirName);
        String uploadPath = uploadDir.toFile().getAbsolutePath();

        if (dirName.startsWith("../"))
            dirName = dirName.replace("../", "");

        registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/" + uploadPath + "/");
    }*/

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
