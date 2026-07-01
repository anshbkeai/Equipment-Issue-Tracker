package com.anshbkeai.issuetracker.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/v1/test")
@Controller
public class TestV1Controller {

    @GetMapping
    public String getMethodName(Model model) {
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return  "test";
    }
    
}
