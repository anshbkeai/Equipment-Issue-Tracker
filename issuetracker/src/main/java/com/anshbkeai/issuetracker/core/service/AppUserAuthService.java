package com.anshbkeai.issuetracker.core.service;

import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.anshbkeai.issuetracker.core.dto.AuthRequest;
import com.anshbkeai.issuetracker.core.model.AppUser;
import com.anshbkeai.issuetracker.core.model.AuthMode;
import com.anshbkeai.issuetracker.core.model.Role;
import com.anshbkeai.issuetracker.core.repository.AppUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppUserAuthService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder encoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;


    public String signup(AuthRequest authRequest , AuthMode authMode) {
        var user = appUserRepository.findByUsername(authRequest.username());
        if (user.isPresent()) {
            throw new RuntimeException("User already exists");
        }

        AppUser newUser = AppUser.builder()
                .userId(UUID.randomUUID().toString())
                .username(authRequest.username())
                .password(encoder.encode(authRequest.password()))
                .role(Role.USER)
                .authMode(authMode)
                .build();
        appUserRepository.save(newUser);

        return jwtService.generateJwt(newUser.getUsername(), newUser.getRole());
    }

    public String login(AuthRequest authRequest) {
        var user = appUserRepository.findByUsername(authRequest.username()).orElseThrow(() -> new RuntimeException("User Does not exist"));

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.username() ,  authRequest.password() ));
        
        if(!authentication.isAuthenticated()){
            throw new RuntimeException("Invalid Credentials");
        }

        return jwtService.generateJwt(user.getUsername(), user.getRole());
    }



}
