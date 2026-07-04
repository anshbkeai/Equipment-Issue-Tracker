package com.anshbkeai.issuetracker.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anshbkeai.issuetracker.core.dto.AuthRequest;
import com.anshbkeai.issuetracker.core.dto.AuthResponse;
import com.anshbkeai.issuetracker.core.model.AuthMode;
import com.anshbkeai.issuetracker.core.service.AppUserAuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AppUserAuthService appUserAuthService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody  @Valid AuthRequest entity) {
        //TODO: process POST request
        String token = appUserAuthService.signup(entity , AuthMode.EMAIL);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody  @Valid AuthRequest entity) {
        //TODO: process POST request
        String token = appUserAuthService.login(entity);
        return ResponseEntity.ok(new AuthResponse(token));
    }
    

}
