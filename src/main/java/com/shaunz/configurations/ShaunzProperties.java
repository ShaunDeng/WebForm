package com.shaunz.configurations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix = "shaunzwork")
@Getter
@Setter
public class ShaunzProperties {
    private String home;
    private String tmpFolder;
    private String imageFolder;
}
