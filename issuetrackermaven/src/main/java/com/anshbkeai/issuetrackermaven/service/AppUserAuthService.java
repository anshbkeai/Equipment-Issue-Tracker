package com.anshbkeai.issuetrackermaven.service;

import com.anshbkeai.issuetrackermaven.repository.AppUserRepository;

public class AppUserAuthService {

    private final AppUserRepository appUserRepository;

    public AppUserAuthService() {
        appUserRepository = new AppUserRepository();
    }

    public String signup(String username, String password , String role , String authMode) {
        appUserRepository.signup(username, password , role , authMode);
        return "Signup successful";
    }

    public String login(String username, String password) {
        return appUserRepository.login(username, password);
    }
}
