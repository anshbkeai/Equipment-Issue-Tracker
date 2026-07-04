package com.anshbkeai.issuetracker.core.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("/logout/v1")
@Controller
public class LogoutController {

    @PostMapping
    public String logout(HttpServletResponse response , HttpServletRequest request) throws IOException {

        Arrays.stream(request.getCookies()).map(cookie -> {
            cookie.setMaxAge(0);
            cookie.setValue("");
            return cookie;
        }).toList().stream().forEach(cookie -> response.addCookie(cookie));

        System.out.println("Logut called");

       return "redirect:test";

        
    }
    
}
