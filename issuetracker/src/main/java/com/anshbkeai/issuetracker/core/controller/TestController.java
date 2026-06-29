package com.anshbkeai.issuetracker.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;



@RequestMapping("/test")
@RestController
public class TestController {

    @GetMapping
    public String getMethodName() {
        return  "Test Controller GET method called with param: " ;
    }
    
}
