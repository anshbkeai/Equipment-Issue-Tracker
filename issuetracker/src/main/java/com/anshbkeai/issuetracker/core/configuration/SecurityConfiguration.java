package com.anshbkeai.issuetracker.core.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.anshbkeai.issuetracker.core.controller.OAuthHandler;
import com.anshbkeai.issuetracker.core.service.JWTService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {


    private final JWTService jwtService;
    @Lazy
    private final OAuthHandler oAuthHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
        JwtFilter jwtFilter = new JwtFilter(jwtService);

        httpSecurity.csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers("/auth/**" , "/test/**" , "/login").permitAll();
 
                auth.anyRequest().authenticated();
            })
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .logout(logout -> {
                logout.logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID","token");

            })
            .oauth2Login(oauth -> {
                oauth.loginPage("/login");
                oauth.successHandler(oAuthHandler);
            })
            
            ;

        return httpSecurity.build();
    }
}
