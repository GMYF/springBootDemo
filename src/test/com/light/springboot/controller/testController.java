package com.light.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {
    @GetMapping("helloWorld")
    public String helloWorld(){
        return "helloworld";
    }

}
