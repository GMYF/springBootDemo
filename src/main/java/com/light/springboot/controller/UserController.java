package com.light.springboot.controller;


import com.light.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author add00
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/detail/{id}")
    public ModelAndView getUserDetail(@PathVariable int id){
        ModelAndView view = new ModelAndView("index");
        Map<String,Object> data = new HashMap<>();
        List<Map> user = userService.findById(id);
        if(user!=null){
            data.put("user",user);
        }
        view.addObject(data);
        return view;
    }
}
