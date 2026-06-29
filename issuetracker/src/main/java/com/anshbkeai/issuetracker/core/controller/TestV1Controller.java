package com.anshbkeai.issuetracker.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/v1/test")
@RestController
public class TestV1Controller {

    @GetMapping
    public String getMethodName() {
        return  "Test V1 Controller GET method called " ;
    }
    
}
