package com.saasapp.business_management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {


    @GetMapping("/")
    public String helloWorld(){
        return "Hello, Saas is running";
    }

}
