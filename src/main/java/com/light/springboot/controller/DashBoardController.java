package com.light.springboot.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/api/dashboard")
public class DashBoardController {

    @GetMapping("/index")
    public String directPage() {
        System.out.println(1 / 0);
        return "index";
    }
}
