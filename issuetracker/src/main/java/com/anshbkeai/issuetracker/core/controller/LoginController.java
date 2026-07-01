package com.anshbkeai.issuetracker.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anshbkeai.issuetracker.core.dto.AuthRequest;
import com.anshbkeai.issuetracker.core.service.AppUserAuthService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AppUserAuthService appUserAuthService;

    @GetMapping
    public String getMethodName() {
        return "login";
    }

    @PostMapping
    public String postMethodName(@RequestParam String username,
                    @RequestParam String password,
                    HttpServletResponse response) {
        //TODO: process POST request
        AuthRequest authRequest =new AuthRequest(username, password);
        String token = appUserAuthService.login(authRequest);
        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);

        response.setHeader("Authorization", "Bearer "+token);

        response.getHeaderNames().stream().forEach(x -> System.out.println(response.getHeader(x)));

        // 4. redirect
        return "redirect:/v1/test";
    }
    
    
}
