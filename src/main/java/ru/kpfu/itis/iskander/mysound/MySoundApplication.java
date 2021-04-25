package ru.kpfu.itis.iskander.mysound;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.kpfu.itis.iskander.mysound.config.ProjectProperties;

@SpringBootApplication
@EnableConfigurationProperties(ProjectProperties.class)
public class MySoundApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySoundApplication.class, args);
    }

}
