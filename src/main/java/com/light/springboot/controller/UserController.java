package com.light.springboot.controller;

import com.light.springboot.dao.TUserMapper;
import com.light.springboot.dto.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private TUserMapper mapper;

    @GetMapping("/user/detail/{id}")
    public ModelAndView getUserDetail(@PathVariable int id){
        ModelAndView view = new ModelAndView("index");
        Map<String,Object> data = new HashMap<>();
        TUser user = mapper.selectByPrimaryKey(id);
        if(user!=null){
            data.put("user",user);
        }
        view.addObject(data);
        return view;
    }
}
