package ru.kpfu.itis.iskander.mysound.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private ProjectProperties projectProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        exposeDirectory(projectProperties.getMediaDirectory(), registry);
        exposeDirectory(projectProperties.getAudiosDirectory(), registry);
        exposeDirectory(projectProperties.getPostersDirectory(), registry);
        exposeDirectory(projectProperties.getAvatarsDirectory(), registry);
        exposeDirectory(projectProperties.getCoversDirectory(), registry);
    }

    private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get(dirName);
        String uploadPath = uploadDir.toFile().getAbsolutePath();

        if (dirName.startsWith("../"))
            dirName = dirName.replace("../", "");

        registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/" + uploadPath + "/");
    }

}
