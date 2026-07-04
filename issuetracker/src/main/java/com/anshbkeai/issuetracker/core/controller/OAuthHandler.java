package com.anshbkeai.issuetracker.core.controller;

import java.io.IOException;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.anshbkeai.issuetracker.core.model.AppUser;
import com.anshbkeai.issuetracker.core.model.AuthMode;
import com.anshbkeai.issuetracker.core.model.Role;
import com.anshbkeai.issuetracker.core.service.AppUserAuthService;
import com.anshbkeai.issuetracker.core.service.AppUserService;
import com.anshbkeai.issuetracker.core.service.JWTService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class OAuthHandler  implements AuthenticationSuccessHandler{

    private final AppUserService appUserService;
    private final JWTService jwtService ;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        // TODO Auto-generated method stub
       OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
       OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        
       String clientName = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

       //TO-DO use inherticane and decpuple the things 
       String username = null;
       AuthMode authMode = null;
       String profileUrl = null;
       if("github".equals(clientName)) {
            username = oAuth2User.getAttribute("email");
            authMode = AuthMode.GITHUB;
            profileUrl  = oAuth2User.getAttribute("avatar_url");
       }
       else {
            username = oAuth2User.getAttribute("email");  
            profileUrl = oAuth2User.getAttribute("picture");
            authMode = AuthMode.GOOGLE;
       }
       AppUser user = appUserService.findByUsername(username) ;
       if(user== null) {
           user = AppUser.builder()
                .userId(UUID.randomUUID().toString())
                .username(username)
                .role(Role.USER)
                .authMode(authMode)
                .profileUrl(profileUrl)
                .build();
          appUserService.saveUser(user);
       }

     String token = jwtService.generateJwt(username, Role.USER);
     Cookie cookie = new Cookie("token", token);
     cookie.setHttpOnly(true);
     cookie.setPath("/");
     response.addCookie(cookie);

     log.info(request.getRequestURI() );
     response.sendRedirect("/v1/test/next");

    }

}
