package com.anshbkeai.issuetracker.core.controller;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anshbkeai.issuetracker.core.dto.AppUserDTO;
import com.anshbkeai.issuetracker.core.model.AppUser;
import com.anshbkeai.issuetracker.issuetracker.service.WTPartService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final WTPartService partService;
    @GetMapping("/dashboard")
    public String dashboard(Model model) {


        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();


        model.addAttribute("username", username);

        // SAMPLE DATA (just for demo)
       Map<String,Long> map = partService.getDashBoardData();

       model.addAllAttributes(map);

        return "dashboard";
    }
    
}
