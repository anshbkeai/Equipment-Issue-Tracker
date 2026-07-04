package com.anshbkeai.issuetracker.issuetracker.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.anshbkeai.issuetracker.issuetracker.dto.WTPartFrom;
import com.anshbkeai.issuetracker.issuetracker.service.WTPartService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/part")
@RequiredArgsConstructor
public class WTPartController {

    private final WTPartService partService;
    @GetMapping
    public String getMethodName(Model model) {
        model.addAttribute("parts",  partService.getAllParts());
       
        return "part/allparts";
    }

    @PostMapping
    public String postMethodName(@Valid @ModelAttribute WTPartFrom from , BindingResult bindingResult ,
            Authentication authentication
     ) {
        //TODO: process POST request
        if (bindingResult.hasErrors()) {
           
            return "part/createPart";
        }
        partService.createProduct(from, authentication.getName());
        return "redirect:/part";  
    }

    @GetMapping("/create")
    public String createPart(Model model ) {
        model.addAttribute("username",  SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName());

        model.addAttribute("form", new WTPartFrom());
        
        return "part/createPart";
    }
    
    
    
}
