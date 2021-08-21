package com.practice.r2dbc.db.common;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.r2dbc.common")
@Getter
@Setter
public class CommonConfigurationProperties {

    private String url;

    private String username;

    private String password;
}
