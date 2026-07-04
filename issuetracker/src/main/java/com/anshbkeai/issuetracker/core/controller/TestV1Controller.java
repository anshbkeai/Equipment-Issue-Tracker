package com.anshbkeai.issuetracker.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anshbkeai.issuetracker.core.dto.AppUserDTO;
import com.anshbkeai.issuetracker.core.model.AppUser;
import com.anshbkeai.issuetracker.core.service.AppUserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/v1/test")
@Controller
@RequiredArgsConstructor
public class TestV1Controller {

    private final AppUserService appUserService;

    @GetMapping
    public String getMethodName(Model model, HttpServletRequest request) {
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        AppUser appUser = appUserService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        AppUserDTO appUserDTO = new AppUserDTO(appUser.getUsername(), appUser.getProfileUrl(), appUser.getAuthMode().name());
        model.addAttribute("appUser", appUserDTO);
        //model.addAttribute("request", request.get)
        return  "test";
    }

    @GetMapping("/next")
    public String getMethodName() {
        return "redirect:/v1/test";
    }
    
    
}
