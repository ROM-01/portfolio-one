package com.rom1.portfolio.portfolioone.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties("spring.datasource")
public class DatabaseConfiguration {

    private String url;
    private String driverClassName;
    private String username;
    private String password;  
}
