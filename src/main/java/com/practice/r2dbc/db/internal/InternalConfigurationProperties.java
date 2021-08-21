package com.practice.r2dbc.db.internal;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.r2dbc.internal")
@Getter
@Setter
public class InternalConfigurationProperties {

    private String url;

    private String username;

    private String password;
}
