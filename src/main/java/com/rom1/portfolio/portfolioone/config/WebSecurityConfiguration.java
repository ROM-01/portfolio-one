package com.rom1.portfolio.portfolioone.config;

import javax.management.RuntimeErrorException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.rom1.portfolio.portfolioone.web.services.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration {

    @SuppressWarnings({ "deprecation", "removal" })
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests(request -> {
                try {
                    request
                            .requestMatchers("/", "/home", "/index").permitAll()
                            .requestMatchers("/acp/**").hasAnyRole("DEVELOPER", "OWNER")
                            .requestMatchers("/profile").hasRole("USER")
                            .anyRequest().authenticated()
                            .and()
                                .formLogin().permitAll()
                            .and()
                                .logout().permitAll()
                    ;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        http.headers().frameOptions().disable();

        return http.build();
    }

    @Bean
    public UserDetailsService getUserDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        var authProvider = new DaoAuthenticationProvider();
            authProvider.setUserDetailsService(getUserDetailsService());
            authProvider.setPasswordEncoder(getBCryptPasswordEncoder());
        return authProvider;
    }
}