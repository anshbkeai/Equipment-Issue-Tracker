package com.anshbkeai.issuetracker.core.controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anshbkeai.issuetracker.core.dto.AppUserDTO;
import com.anshbkeai.issuetracker.core.model.AppUser;


@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();


        model.addAttribute("username", username);

        // SAMPLE DATA (just for demo)
        List<String> tasks = List.of(
                "Fix bugs",
                "Write API",
                "Deploy app"
        );

        model.addAttribute("tasks", tasks);

        return "dashboard";
    }
    
}
